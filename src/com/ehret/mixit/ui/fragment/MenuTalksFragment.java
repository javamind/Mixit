package com.ehret.mixit.ui.fragment;

import android.content.Context;
import android.view.View;
import com.ehret.mixit.R;
import com.ehret.mixit.domain.TypeFile;
import com.ehret.mixit.ui.activity.ParseListeActivity;
import com.ehret.mixit.ui.utils.UIUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Fragment utilise sur la page daccueil pour afficher les talks
 */
public class MenuTalksFragment extends AbstractMenuFragment {

    @Override
    public int getLayout() {
        return R.layout.fragment_menu1;
    }

    @Override
    public int getIdTitle() {
        return R.id.menuFragmentTitle1;
    }

    @Override
    public int getIdTable() {
        return R.id.menuFragmentTableLayout1;
    }

    @Override
    public int getNameMenu() {
        return R.string.description_sessions;
    }

    @Override
    public void createElementsMenu() {
        Context context = getActivity().getBaseContext();
        final Map<String, Object> parameters  = new HashMap<String, Object>(6);

        createMenu(R.color.blue, context.getString(R.string.description_favorite), false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.favorites.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
        createMenu(R.color.yellow1, context.getString(R.string.description_talk), false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.talks.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
        createMenu(R.color.yellow2, context.getString(R.string.description_workshop), false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.workshops.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
        createMenu(R.color.red1, context.getString(R.string.description_ligtningtalk), true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.lightningtalks.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
    }

}
