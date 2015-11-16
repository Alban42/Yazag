/**
 *
 */
package com.alban42.yazag.game.screens;

import com.alban42.yazag.game.screens.listeners.PlayClickListener;
import com.alban42.yazag.utils.exceptions.NonExistingFont;
import com.alban42.yazag.utils.screens.AbstractMenuScreen;
import com.alban42.yazag.utils.ui.Alban42UiFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Alban
 */
public class MenuScreen extends AbstractMenuScreen {

    protected String TAG = MenuScreen.class.getName();

    /**
     * @param game
     */
    public MenuScreen(final Game game) {
        super(game);
    }

    @Override
    protected void buildStage() {
        try {
            addHeader("Menu", Color.WHITE);
            addToTable(Alban42UiFactory.createTextButton(getSkin(), getFont(Color.BLACK), "PLAY",
                                                         new PlayClickListener(getGame())));
            addRow();
            addToTable(Alban42UiFactory.createTextButton(getSkin(), getFont(Color.BLACK), "EXIT", new ClickListener() {
                @Override
                public void clicked(final InputEvent event, final float x, final float y) {
                    Gdx.app.exit();
                }
            }));
        } catch (final NonExistingFont e) {
            e.printStackTrace();
            Gdx.app.exit();
        }
    }

    @Override
    protected void disposeExtra() {
        // VOID
    }

    @Override
    protected void renderExtra() {
    }

}
