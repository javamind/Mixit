/*
 * Copyright 2013 Guillaume EHRET
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
public class MenuPeopleFragment extends AbstractMenuFragment {

    @Override
    public int getLayout() {
        return R.layout.fragment_menu2;
    }

    @Override
    public int getIdTitle() {
        return R.id.menuFragmentTitle2;
    }

    @Override
    public int getIdTable() {
        return R.id.menuFragmentTableLayout2;
    }

    @Override
    public int getNameMenu() {
        return R.string.description_people;
    }

    @Override
    public void createElementsMenu() {
        Context context = getActivity().getBaseContext();
        final Map<String, Object> parameters = new HashMap<String, Object>(6);

        createMenu(R.color.green1, context.getString(R.string.description_speakers), false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.speaker.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
        createMenu(R.color.orange, context.getString(R.string.description_staff), false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.staff.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
        createMenu(R.color.pink1, context.getString(R.string.description_sponsor), false, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.sponsor.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
        createMenu(R.color.violet1, context.getString(R.string.description_membres), true, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parameters.put(UIUtils.MESSAGE, TypeFile.members.name());
                UIUtils.startActivity(ParseListeActivity.class, getActivity(), parameters);
            }
        });
    }

}
