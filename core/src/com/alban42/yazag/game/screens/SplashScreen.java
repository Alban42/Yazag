/**
 *
 */
package com.alban42.yazag.game.screens;

import com.alban42.yazag.utils.screens.AbstractSplashScreen;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;

/**
 * @author Alban
 */
public class SplashScreen extends AbstractSplashScreen {

    private static final float DELAY = 0.5f;
    private static final float DURATION = 1.5f;
    private static final int REPEAT_COUNT = 1;
    private static final String TEXTURE_PATH = "img/splash/badlogic.jpg";

    public SplashScreen(final Game game) {
        super(game);
    }

    @Override
    protected Screen getNextScreen() {
        return new MenuScreen(getGame());
    }

    @Override
    protected float getSplashDelay() {
        return DELAY;
    }

    @Override
    protected float getSplashDuration() {
        return DURATION;
    }

    @Override
    protected int getSplashRepeatCount() {
        return REPEAT_COUNT;
    }

    @Override
    protected String getSplashTextureInternalPath() {
        return TEXTURE_PATH;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

}
