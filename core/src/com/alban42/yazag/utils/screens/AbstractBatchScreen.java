/**
 *
 */
package com.alban42.yazag.utils.screens;

import com.alban42.yazag.utils.Alban42Utils;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * @author Alban
 */
public abstract class AbstractBatchScreen extends AbstractScreen {

    private final SpriteBatch batch;

    /**
     * @param game
     */
    public AbstractBatchScreen(final Game game) {
        super(game);
        this.batch = new SpriteBatch();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }

    public SpriteBatch getBatch() {
        return this.batch;
    }

    @Override
    public void render(final float delta) {
        Alban42Utils.clearScreen();
        this.batch.begin();
        renderExtra(delta);
        this.batch.end();
    }

    /**
     *
     */
    protected abstract void renderExtra(float delta);

}
