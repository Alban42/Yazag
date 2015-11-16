package com.alban42.yazag.utils.screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import com.alban42.yazag.utils.tween.SpriteAccessor;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

public abstract class AbstractSplashScreen extends AbstractBatchScreen {

    private final Sprite sprite;

    private TweenManager tweenManager;

    /**
     * @param game
     */
    public AbstractSplashScreen(final Game game) {
        super(game);
        final Texture texture = new Texture(getSplashTextureInternalPath());
        this.sprite = new Sprite(texture);
        this.sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        configureTween();
    }

    private void configureTween() {
        this.tweenManager = new TweenManager();
        Tween.registerAccessor(Sprite.class, new SpriteAccessor());
        Tween.set(this.sprite, SpriteAccessor.ALPHA).target(0).start(this.tweenManager);
        Tween.to(this.sprite, SpriteAccessor.ALPHA, getSplashDuration()).target(1).
                repeatYoyo(getSplashRepeatCount(), getSplashDelay()).setCallback(getTweenCallback())
             .start(this.tweenManager);
    }

    @Override
    public void dispose() {
        super.dispose();
        this.sprite.getTexture().dispose();
    }

    protected abstract Screen getNextScreen();

    protected abstract float getSplashDelay();

    protected abstract float getSplashDuration();

    protected abstract int getSplashRepeatCount();

    protected abstract String getSplashTextureInternalPath();

    protected TweenCallback getTweenCallback() {
        final TweenCallback callback = new TweenCallback() {

            @Override
            public void onEvent(final int type, final BaseTween<?> source) {
                ((Game) Gdx.app.getApplicationListener()).setScreen(getNextScreen());
            }
        };
        return callback;
    }

    @Override
    protected void renderExtra(final float delta) {
        this.tweenManager.update(delta);
        this.sprite.draw(getBatch());
    }

    @Override
    public void resize(final int width, final int height) {
        this.sprite.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }
}
