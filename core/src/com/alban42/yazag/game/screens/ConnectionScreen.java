/**
 *
 */
package com.alban42.yazag.game.screens;

import com.alban42.yazag.utils.screens.AbstractMenuScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

/**
 * @author Alban
 */
public class ConnectionScreen extends AbstractMenuScreen {

    protected static final String TAG = ConnectionScreen.class.getName();

    /**
     * @param game
     */
    public ConnectionScreen(final Game game) {
        super(game);
    }

    @Override
    protected void buildStage() {

    }

    @Override
    protected void disposeExtra() {
        // VOID
    }

    private void onGameClicked() {
        this.game.setScreen(new GameScreenTest(this.game));
    }

    @Override
    public void render(final float deltaTime) {
        Gdx.gl.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        if (Gdx.input.isTouched()) {
            onGameClicked();
        }
    }

    @Override
    protected void renderExtra() {
        // TODO Auto-generated method stub

    }

}
