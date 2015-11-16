/**
 *
 */
package com.alban42.yazag.utils.screens;

import com.alban42.yazag.utils.world.AbstractWorldController;
import com.alban42.yazag.utils.world.AbstractWorldRenderer;
import com.alban42.yazag.utils.world.WorldManager;
import com.badlogic.gdx.Game;

/**
 * @author Alban
 */
public class AbstractGameScreen extends AbstractScreen {

    protected static final String TAG = AbstractGameScreen.class.getSimpleName();

    private final AbstractWorldController worldController;
    private final AbstractWorldRenderer worldRenderer;

    /**
     * @param game
     */
    public AbstractGameScreen(final Game game, final AbstractWorldController worldController, final AbstractWorldRenderer worldRenderer) {
        super(game);
        // Initialize controller and renderer
        this.worldController = worldController;
        this.worldRenderer = worldRenderer;
        this.worldRenderer.setWorldController(worldController);
        WorldManager.INSTANCE.setWorldController(worldController);
    }

    @Override
    public void dispose() {
        this.worldRenderer.dispose();
    }

    /**
     * @return the worldController
     */
    public AbstractWorldController getWorldController() {
        return this.worldController;
    }

    /**
     * @return the worldRenderer
     */
    public AbstractWorldRenderer getWorldRenderer() {
        return this.worldRenderer;
    }

    @Override
    public void render(final float delta) {
        // Update game world by the time that has passed since last rendered frame.
        this.worldController.update(delta);
        // Render game world to screen
        this.worldRenderer.render();
    }
}
