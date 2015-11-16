package com.alban42.yazag.utils.world;

import com.alban42.yazag.common.utils.objects.action.PlayerActions;
import com.alban42.yazag.network.Network;
import com.alban42.yazag.utils.objects.AbstractGameObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;

import java.io.IOException;

public abstract class AbstractWorldController extends InputAdapter {

    protected static final String TAG = AbstractWorldController.class.getName();

    private final PlayerActions actions;
    private final CameraHelper cameraHelper;
    private AbstractGameObject player;

    public AbstractWorldController() {
        Gdx.input.setInputProcessor(this);
        this.actions = new PlayerActions();
        this.cameraHelper = new CameraHelper();
    }

    /**
     * @return the actions
     */
    public PlayerActions getActions() {
        return this.actions;
    }

    /**
     * @return the cameraHelper
     */
    public CameraHelper getCameraHelper() {
        return this.cameraHelper;
    }

    /**
     * The object controlled by the player of the game.
     *
     * @return the player
     */
    public AbstractGameObject getPlayer() {
        return this.player;
    }

    /**
     * The object controlled by the player of the game.
     *
     * @param player the player to set
     */
    public void setPlayer(final AbstractGameObject player) {
        this.player = player;
        this.cameraHelper.setTarget(this.player);
    }

    /**
     * Update the position of all the game objects into {@link AbstractWorldController#gameObjects}.
     *
     * @param deltaTime the delta time.
     */
    public void update(final float deltaTime) {
        this.actions.setDelta(deltaTime);
        try {
            if (getActions().getActions().size() > 0) {
                Network.INSTANCE.sendPacketUDP(getActions());
            }
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.actions.reset();
        this.cameraHelper.update(deltaTime);
    }

}
