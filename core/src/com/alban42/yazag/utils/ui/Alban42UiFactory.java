package com.alban42.yazag.utils.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle;

public class Alban42UiFactory {

    private static final String BUTTON_DOWN = "button_down";
    private static final String BUTTON_UP = "button_up";
    private static final float PAD = 20;

    public static Label createLabel(final BitmapFont font, final Color color, final float fontScale, final String text) {
        Label label = null;

        final LabelStyle style = new LabelStyle(font, color);
        label = new Label(text, style);
        label.setFontScale(fontScale);
        return label;
    }

    public static TextButton createTextButton(final Skin skin, final BitmapFont font, final String text) {
        return createTextButton(skin, font, text, null);
    }

    public static TextButton createTextButton(final Skin skin, final BitmapFont font, final String text, final EventListener listener) {
        TextButton button = null;

        final TextButtonStyle style = new TextButtonStyle();
        style.up = skin.getDrawable(BUTTON_UP);
        style.down = skin.getDrawable(BUTTON_DOWN);
        style.pressedOffsetX = 1;
        style.pressedOffsetY = -1;
        style.font = font;

        button = new TextButton(text, style);
        button.pad(PAD);
        if (listener != null) {
            button.addListener(listener);
        }

        return button;
    }

}
