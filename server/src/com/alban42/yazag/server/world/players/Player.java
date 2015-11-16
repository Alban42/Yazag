/**
 *
 */
package com.alban42.yazag.server.world.players;

import com.alban42.yazag.common.utils.objects.action.Action;
import com.alban42.yazag.common.utils.objects.action.PlayerActions;
import com.alban42.yazag.server.world.WorldManager;
import com.esotericsoftware.minlog.Log;

import java.util.LinkedList;

/**
 * @author Alban
 */
public class Player implements Runnable {

    private static final int RUN_MULTIPLIER = 2;
    private static final int WALK = 200;
    private final Integer connectionID;
    private final LinkedList<PlayerActions> listActions;
    private final PlayerObject playerObject;
    private int running;

    private boolean stop;

    public Player(final int connectionID) {
        this.connectionID = connectionID;
        this.listActions = new LinkedList<PlayerActions>();
        this.playerObject = WorldManager.INSTANCE.createNewPlayer();
        this.running = 1;
        this.stop = false;
    }

    public void addPlayerActions(final PlayerActions playerActions) {
        getListActions().add(playerActions);
    }

    public void deactivatePlayerObject() {
        this.playerObject.active = false;
    }

    /**
     * @return the connectionID
     */
    public Integer getConnectionID() {
        return this.connectionID;
    }

    /**
     * @return the listActions
     */
    public synchronized LinkedList<PlayerActions> getListActions() {
        return this.listActions;
    }

    /**
     * @return the playerObject
     */
    public PlayerObject getPlayerObject() {
        return this.playerObject;
    }

    public void removePlayerObject() {
        WorldManager.INSTANCE.removeObject(this.playerObject.getObjectID());
    }

    @Override
    public void run() {
        Log.info("Thread started for player " + getConnectionID());
        PlayerActions playerActions;
        while (!this.stop) {
            playerActions = getListActions().pollFirst();
            if (playerActions != null) {

                for (final Action action : playerActions.getActions()) {
                    Log.info("Player " + getConnectionID() + " Action " + action);
                    switch (action.getAction()) {
                        case WALK_N:
                            this.playerObject.acceleration.y = WALK * this.running;
                            break;
                        case WALK_S:
                            this.playerObject.acceleration.y = -WALK * this.running;
                            break;
                        case WALK_E:
                            this.playerObject.acceleration.x = WALK * this.running;
                            break;
                        case WALK_W:
                            this.playerObject.acceleration.x = -WALK * this.running;
                            break;
                        case RUN:
                            this.running = RUN_MULTIPLIER;
                            break;
                        case STOP_WALK_N:
                            this.playerObject.acceleration.y = 0;
                            break;
                        case STOP_WALK_S:
                            this.playerObject.acceleration.y = 0;
                            break;
                        case STOP_WALK_E:
                            this.playerObject.acceleration.x = 0;
                            break;
                        case STOP_WALK_W:
                            this.playerObject.acceleration.x = 0;
                            break;
                        case STOP_RUN:
                            this.running = 1;
                            break;
                        case ROTATE:
                            Log.info("ROTATION : " + action.getValue());
                            this.playerObject.rotation = action.getValue();
                        default:
                            break;
                    }
                }
            }
        }
        Log.info("Thread stoped for player " + getConnectionID());
    }

    public void stop() {
        this.stop = true;
    }
}
