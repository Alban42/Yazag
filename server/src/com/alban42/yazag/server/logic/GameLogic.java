/**
 *
 */
package com.alban42.yazag.server.logic;

import com.alban42.yazag.common.utils.objects.world.WorldState;
import com.alban42.yazag.server.world.WorldManager;
import com.alban42.yazag.server.world.objects.WorldObject;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.util.Collection;

/**
 * @author Alban
 */
public class GameLogic implements Runnable {

    private static final int UPDATE_DELAY = 50;
    private final float OPTIMAL_TIME = 1000000000.0f;
    private final Server server;
    private final WorldState worldState;
    private boolean stop;

    /**
     * @param server
     */
    public GameLogic(final Server server) {
        this.server = server;
        this.stop = false;
        this.worldState = new WorldState();
    }

    @Override
    public void run() {
        WorldObject worldObjectTmp = null;
        long currentUpdateTime = System.nanoTime();
        long lastUpdateTime = System.nanoTime();
        long lastUpdateSendedTime = System.nanoTime();
        float delta;
        while (!this.stop) {
            currentUpdateTime = System.nanoTime();
            delta = (currentUpdateTime - lastUpdateTime) / this.OPTIMAL_TIME;
            lastUpdateTime = currentUpdateTime;

            // if ((currentUpdateTime - lastUpdateSendedTime) > UPDATE_DELAY) {
            if (!this.worldState.isVoid()) {
                Log.debug("Send to all UDP " + this.worldState.toString());
                this.server.sendToAllUDP(this.worldState);
                this.worldState.reset();
            }
            lastUpdateSendedTime = System.nanoTime();
            // }

            final Collection<WorldObject> currentWorldObjects = WorldManager.INSTANCE.getWorldObjects().values();

            Log.debug("Delta " + delta);
            for (final WorldObject worldObject : currentWorldObjects) {
                try {
                    worldObjectTmp = (WorldObject) worldObject.clone();
                } catch (final CloneNotSupportedException e) {
                    e.printStackTrace();
                    Log.error("GameLogic", e);
                }
                worldObject.update(delta);
                if (!worldObject.equals(worldObjectTmp)) {
                    Log.debug("Changed ! add object ");
                    this.worldState.addGameObject(WorldManager.createNewUpdateObject(worldObject));
                }
            }
        }
    }

    public void stop() {
        this.stop = true;
    }
}
