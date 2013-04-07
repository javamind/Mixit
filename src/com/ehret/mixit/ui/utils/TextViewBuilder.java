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
package com.ehret.mixit.ui.utils;

import android.content.Context;
import android.graphics.Paint;
import android.view.ViewGroup;
import android.widget.TableRow.LayoutParams;
import android.widget.TextView;

public class TextViewBuilder {
    /**
     * Textview qui ser a construit
     */
    private TextView textView;

    /**
     * Cree une nouvelle instance de {@link #textView}
     *
     * @param text
     * @return
     */
    public TextViewBuilder addText(String text) {
        textView.setText(text);
        return this;
    }

    /**
     * Cree une nouvelle instance de {@link #textView}
     *
     * @param text
     * @return
     */
    public TextViewBuilder addText(int text) {
        textView.setText(text);
        return this;
    }

    /**
     * Aligenemnt de {@link #textView}
     *
     * @param gravity
     * @return
     */
    public TextViewBuilder addAlignement(int gravity) {
        textView.setGravity(gravity);
        return this;
    }

    /**
     * WrapContent {@link #textView}
     *
     * @return
     */
    public TextViewBuilder addStrike() {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        return this;
    }

    public TextViewBuilder addBold(boolean value) {
        textView.getPaint().setFakeBoldText(value);
        return this;
    }

    /**
     * Text barré {@link #textView}
     *
     * @return
     */
    public TextViewBuilder addWrapContent(float weight) {
        getLayoutParams(LayoutParams.WRAP_CONTENT, weight);
        return this;
    }

    /**
     * Cree une nouvelle instance de {@link #textView}
     *
     * @param context
     * @return
     */
    public TextViewBuilder buildTextView(Context context) {
        textView = new TextView(context);
        textView.setPadding(4, 4, 4, 4);
        textView.setSingleLine(true);
        return this;
    }

    /**
     * Fusionne nbcol  {@link #textView}
     *
     * @param nbcol
     * @return
     */
    public TextViewBuilder addSpan(int nbcol) {
        LayoutParams params = getLayoutParams(LayoutParams.MATCH_PARENT);
        params.span = nbcol;
        return this;
    }

    /**
     * Size  {@link #textView}
     *
     * @param size
     * @return
     */
    public TextViewBuilder addSize(int size) {
        textView.setTextSize(size);
        return this;
    }

    /**
     * Size  {@link #textView}
     *
     * @param size
     * @return
     */
    public TextViewBuilder addSize(int unit, int size) {
        textView.setTextSize(unit, size);
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
        ViewGroup.LayoutParams params = textView.getLayoutParams();
        if (params == null || !(params instanceof LayoutParams)) {
            LayoutParams param2s = new LayoutParams(match, LayoutParams.MATCH_PARENT, weight);
            textView.setLayoutParams(param2s);
            return param2s;
        }
        return (LayoutParams) params;
    }

    /**
     * Ajoute des marges internes a  {@link #textView}
     *
     * @param left
     * @param right
     * @return
     */
    public TextViewBuilder addPadding(int left, int right, int bottom) {
        textView.setPadding(left, 4, right, bottom);
        return this;
    }

    /**
     * Specifie la largeur {@link #textView}
     *
     * @param weight
     * @return
     */
    public TextViewBuilder addWeight(float weight) {
        getLayoutParams(LayoutParams.WRAP_CONTENT).weight = weight;
        return this;
    }

    /**
     * Ajoute des bordures a {@link #textView}
     *
     * @param borderLeft
     * @param borderRight
     * @param borderBottom
     * @param borderTop
     * @return
     */
    public TextViewBuilder addBorders(boolean borderLeft, boolean borderRight,
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
    public TextViewBuilder addUpperCase() {
        textView.setAllCaps(true);
        return this;
    }

    /**
     * Ajoute une couleur de fond a {@link #textView}
     *
     * @param color
     * @return
     */
    public TextViewBuilder addBackground(int color) {
        textView.setBackgroundColor(color);
        return this;
    }

    /**
     * Ajoute une couleur de fond a {@link #textView}
     * via un drawable
     *
     * @param drawable
     * @return
     */
    public TextViewBuilder addBackgroundDrawable(int drawable) {
        textView.setBackgroundResource(drawable);
        return this;
    }

    /**
     * Ajoute le nb de lignes acceptées
     *
     * @param nb
     * @return
     */
    public TextViewBuilder addNbLines(int nb) {
        textView.setLines(nb);
        return this;
    }

    /**
     * Ajoute le nb max de lignes acceptées
     *
     * @param nb
     * @return
     */
    public TextViewBuilder addNbMaxLines(int nb) {
        textView.setSingleLine(false);
        textView.setMaxLines(nb);
        return this;
    }

    /**
     * Ajoute une couleur de texte a {@link #textView}
     *
     * @param color
     * @return
     */
    public TextViewBuilder addTextColor(int color) {
        textView.setTextColor(color);
        return this;
    }


    /**
     * Retourne le textView construit
     *
     * @return
     */
    public TextView getView() {
        return textView;
    }
}
