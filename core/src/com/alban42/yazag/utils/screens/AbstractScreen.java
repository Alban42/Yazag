/**
 *
 */
package com.alban42.yazag.utils.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;

/**
 * @author Alban
 */
public abstract class AbstractScreen implements Screen {

    protected Game game;
    protected String TAG = AbstractMenuScreen.class.getName();

    public AbstractScreen(final Game game) {
        this.game = game;
    }

    /**
     * @return the game
     */
    public Game getGame() {
        return this.game;
    }

    @Override
    public void hide() {
        Gdx.app.log(this.TAG, "hide");
        dispose();
    }

    @Override
    public void pause() {
        Gdx.app.log(this.TAG, "pause");
    }

    @Override
    public void resize(final int width, final int height) {
        Gdx.app.log(this.TAG, "resize");
    }

    @Override
    public void resume() {
        Gdx.app.log(this.TAG, "resume");
    }

    @Override
    public void show() {
        Gdx.app.log(this.TAG, "show");
    }

}
