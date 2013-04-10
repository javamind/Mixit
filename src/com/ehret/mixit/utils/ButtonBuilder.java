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
package com.ehret.mixit.utils;

import android.content.Context;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.TableRow.LayoutParams;
import android.widget.Button;

public class ButtonBuilder {
    /**
     * Textview qui ser a construit
     */
    private Button button;

    /**
     * Cree une nouvelle instance de {@link #button}
     *
     * @param text
     * @return
     */
    public ButtonBuilder addText(String text) {
        button.setText(text);
        return this;
    }

    /**
     * Cree une nouvelle instance de {@link #button}
     *
     * @param text
     * @return
     */
    public ButtonBuilder addText(int text) {
        button.setText(text);
        return this;
    }

    /**
     * Aligenemnt de {@link #button}
     *
     * @param gravity
     * @return
     */
    public ButtonBuilder addAlignement(int gravity) {
        button.setGravity(gravity);
        return this;
    }

    /**
     * WrapContent {@link #button}
     *
     * @return
     */
    public ButtonBuilder addStrike() {
        button.setPaintFlags(button.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    public ButtonBuilder addBold(boolean value) {
        button.getPaint().setFakeBoldText(value);
        return this;
    }

    /**
     * Text barré {@link #button}
     *
     * @return
     */
    public ButtonBuilder addWrapContent(float weight) {
        getLayoutParams(LayoutParams.WRAP_CONTENT, weight);
        return this;
    }

    /**
     * Cree une nouvelle instance de {@link #button}
     *
     * @param context
     * @return
     */
    public ButtonBuilder buildButton(Context context) {
        button = new Button(context);
        button.setPadding(4, 4, 4, 4);
        button.setSingleLine(true);
        return this;
    }

    /**
     * Fusionne nbcol  {@link #button}
     *
     * @param nbcol
     * @return
     */
    public ButtonBuilder addSpan(int nbcol) {
        LayoutParams params = getLayoutParams(LayoutParams.MATCH_PARENT);
        params.span = nbcol;
        return this;
    }

    /**
     * Size  {@link #button}
     *
     * @param size
     * @return
     */
    public ButtonBuilder addSize(int size) {
        button.setTextSize(size);
        return this;
    }

    /**
     * Size  {@link #button}
     *
     * @param size
     * @return
     */
    public ButtonBuilder addSize(int unit, int size) {
        button.setTextSize(unit, size);
        return this;
    }

    /**
     * Renvoi le Layout de type TableRow
     *
     * @return
     */
    private LayoutParams getLayoutParams(int match) {
        return getLayoutParams(match, 1f);
    }

    /**
     * Renvoi le Layout de type TableRow
     *
     * @return
     */
    private LayoutParams getLayoutParams(int match, float weight) {
        ViewGroup.LayoutParams params = button.getLayoutParams();
        if (params == null || !(params instanceof LayoutParams)) {
            LayoutParams param2s = new LayoutParams(match, LayoutParams.MATCH_PARENT, weight);
            button.setLayoutParams(param2s);
            return param2s;
        }
        return (LayoutParams) params;
    }

    /**
     * Ajoute des marges internes a  {@link #button}
     *
     * @param left
     * @param right
     * @return
     */
    public ButtonBuilder addPadding(int left, int right, int bottom) {
        button.setPadding(left, 4, right, bottom);
        return this;
    }

    /**
     * Specifie la largeur {@link #button}
     *
     * @param weight
     * @return
     */
    public ButtonBuilder addWeight(float weight) {
        getLayoutParams(LayoutParams.WRAP_CONTENT).weight = weight;
        return this;
    }

    /**
     * Ajoute des bordures a {@link #button}
     *
     * @param borderLeft
     * @param borderRight
     * @param borderBottom
     * @param borderTop
     * @return
     */
    public ButtonBuilder addBorders(boolean borderLeft, boolean borderRight,
                                      boolean borderBottom, boolean borderTop) {

        int bottom = borderBottom ? 1 : 0;
        int right = borderRight ? 1 : 0;
        int left = borderLeft ? 1 : 0;
        int top = borderTop ? 1 : 0;

        LayoutParams params = getLayoutParams(LayoutParams.MATCH_PARENT);
        params.setMargins(left, top, right, bottom);
        return this;
    }

    /**
     * Met les caracteres en majuscules
     *
     * @return
     */
    public ButtonBuilder addUpperCase() {
        button.setAllCaps(true);
        return this;
    }

    /**
     * Ajoute une couleur de fond a {@link #button}
     *
     * @param color
     * @return
     */
    public ButtonBuilder addBackground(int color) {
        button.setBackgroundColor(color);
        return this;
    }

    /**
     * Ajoute une couleur de fond a {@link #button}
     * via un drawable
     *
     * @param drawable
     * @return
     */
    public ButtonBuilder addBackgroundDrawable(int drawable) {
        button.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Ajoute le nb de lignes acceptées
     *
     * @param nb
     * @return
     */
    public ButtonBuilder addNbLines(int nb) {
        button.setLines(nb);
        return this;
    }

    /**
     * Ajoute le nb max de lignes acceptées
     *
     * @param nb
     * @return
     */
    public ButtonBuilder addNbMaxLines(int nb) {
        button.setSingleLine(false);
        button.setMaxLines(nb);
        return this;
    }

    /**
     * Ajoute une couleur de texte a {@link #button}
     *
     * @param color
     * @return
     */
    public ButtonBuilder addTextColor(int color) {
        button.setTextColor(color);
        return this;
    }


    /**
     * Retourne le button construit
     *
     * @return
     */
    public Button getView() {
        return button;
    }
}
