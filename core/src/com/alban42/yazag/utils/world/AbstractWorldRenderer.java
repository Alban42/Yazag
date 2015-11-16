package com.alban42.yazag.utils.world;

import com.alban42.yazag.utils.Alban42Utils;
import com.alban42.yazag.utils.exceptions.WorldControllerNotSetted;
import com.alban42.yazag.utils.objects.AbstractGameObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class AbstractWorldRenderer implements Disposable {

    private static final String TAG = AbstractWorldRenderer.class.getName();
    private final SpriteBatch batch;
    private AbstractWorldController worldController;

    public AbstractWorldRenderer() {
        this(null);
    }

    public AbstractWorldRenderer(final AbstractWorldController worldController) {
        this.worldController = worldController;
        this.batch = new SpriteBatch();
    }

    @Override
    public void dispose() {
        this.batch.dispose();
    }

    private void isWorldController() {
        if (this.worldController == null) {
            try {
                throw new WorldControllerNotSetted();
            } catch (final WorldControllerNotSetted e) {
                e.printStackTrace();
            }
        }
    }

    public void render() {
        isWorldController();
        Alban42Utils.clearScreen();
        this.worldController.getCameraHelper().render();
        this.batch.setProjectionMatrix(this.worldController.getCameraHelper().getCamera().combined);
        this.batch.begin();
        for (final AbstractGameObject gameObject : WorldManager.INSTANCE.getWorldObjects().values()) {
            gameObject.render(this.batch);
        }
        this.batch.end();
    }

    public void resize(final int width, final int height) {
        isWorldController();
        this.worldController.getCameraHelper().resize(width, height);
    }

    /**
     * @param worldController the worldController to set
     */
    public void setWorldController(final AbstractWorldController worldController) {
        this.worldController = worldController;
    }

}
