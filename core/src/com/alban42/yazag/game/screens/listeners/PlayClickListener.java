/**
 *
 */
package com.alban42.yazag.game.screens.listeners;

import com.alban42.yazag.game.screens.GameScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

/**
 * @author Alban
 */
public class PlayClickListener extends ClickListener {

    private final Game game;

    /**
     * @param game
     */
    public PlayClickListener(final Game game) {
        this.game = game;
    }

    @Override
    public void clicked(final InputEvent event, final float x, final float y) {
        this.game.setScreen(new GameScreen(this.game));
    }

}
