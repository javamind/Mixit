package com.ehret.mixit.ui.fragment;

import android.app.Fragment;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.people.Interet;
import com.ehret.mixit.domain.people.Link;
import com.ehret.mixit.domain.people.Membre;
import com.ehret.mixit.model.MembreFacade;
import com.ehret.mixit.ui.utils.TextViewBuilder;
import com.ehret.mixit.ui.utils.UIUtils;


/**
 * Ce fragment permet d'afficher les différents interets qu'une des personnes référenceés
 * sous Mixit a partage
 */
public class PersonInteretFragment extends Fragment {
    public static final String TAG = "PersonInteretFragment";
    private ViewGroup mRootView;
    private LayoutInflater mInflater;
    private String typePersonne;
    private Long idPerson;
    private LinearLayout linearLayoutRoot;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mInflater = inflater;
        mRootView = (ViewGroup) inflater.inflate(R.layout.fragment_list_tweets, container);
        return mRootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity().getIntent().getExtras() != null) {
            idPerson = getActivity().getIntent().getExtras().getLong(UIUtils.MESSAGE);
            typePersonne = getActivity().getIntent().getExtras().getString(UIUtils.TYPE);
        }
        else{
            //On gere le cas ou on tourne l'écran en restorant les états de la vue
            idPerson= savedInstanceState.getLong("ID_PERSON_LINK");
            typePersonne= savedInstanceState.getString("TYPE_PERSON_LINK");
        }
        //On recupere la personne concernee
        Membre membre = MembreFacade.getInstance().getMembre(getActivity(), typePersonne, idPerson);

        //On affiche les liens que si on a recuperer des choses
        if(membre.getInterests()!=null && !membre.getInterests().isEmpty()){
            linearLayoutRoot = (LinearLayout) mInflater.inflate(R.layout.fragment_linear, mRootView, false);
            //On vide les éléments
            linearLayoutRoot.removeAllViews();

            linearLayoutRoot.addView(new TextViewBuilder()
                    .buildTextView(getActivity())
                    .addText(getString(R.string.description_interet))
                    .addPadding(0, 10, 4)
                    .addBold(true)
                    .addUpperCase()
                    .addSize(TypedValue.COMPLEX_UNIT_SP, getResources().getInteger(R.integer.text_size_cal))
                    .addTextColor(getResources().getColor(R.color.black))
                    .getView());

            StringBuffer interets = new StringBuffer();
            for(final Long iidInteret : membre.getInterests()){
                Interet interet = MembreFacade.getInstance().getInteret(getActivity(),iidInteret);
                if(interet!=null){
                    if(interets.length()>0){
                        interets.append(", ");
                    }
                    interets.append(interet.getName());
                }
            }
            TextView text = new TextViewBuilder()
                    .buildTextView(getActivity())
                    .addText(interets.toString())
                    .addPadding(4, 10, 4)
                    .addSize(TypedValue.COMPLEX_UNIT_SP, getResources().getInteger(R.integer.text_size_cal))
                    .addTextColor(getResources().getColor(R.color.black))
                    .getView();
            text.setSingleLine(false);
            linearLayoutRoot.addView(text);
            mRootView.addView(linearLayoutRoot);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("ID_PERSON_LINK", idPerson);
        outState.putString("TYPE_PERSON_LINK", typePersonne);
    }
}
