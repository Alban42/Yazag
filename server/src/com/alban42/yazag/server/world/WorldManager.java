/**
 *
 */
package com.alban42.yazag.server.world;

import com.alban42.yazag.common.utils.objects.world.WorldState;
import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.alban42.yazag.server.world.objects.World;
import com.alban42.yazag.server.world.objects.WorldObject;
import com.alban42.yazag.server.world.players.PlayerObject;

import java.util.Map;

/**
 * @author Alban
 */
public class WorldManager {

    public static final WorldManager INSTANCE = new WorldManager();
    private final World world;

    /**
     * @param world
     */
    private WorldManager() {
        this.world = new World();
    }

    /**
     * @param worldObject
     * @return
     */
    public static WorldUpdateObject createNewUpdateObject(final WorldObject worldObject) {
        final WorldUpdateObject result = new WorldUpdateObject();
        result.dimension = worldObject.dimension;
        result.origin = worldObject.origin;
        result.position = worldObject.position;
        result.rotation = worldObject.rotation;
        result.scale = worldObject.scale;
        result.type = worldObject.type;
        result.objectID = worldObject.getObjectID();
        result.active = worldObject.active;
        return result;
    }

    public void addWorldObject(final WorldObject object) {
        getWorld().addObject(object);
    }

    public PlayerObject createNewPlayer() {
        final PlayerObject playerObject = new PlayerObject(this.world.getRandomPostion());
        this.world.addObject(playerObject);
        return playerObject;

    }

    /**
     * @return
     */
    public WorldState getCurrentWorldState() {
        final WorldState worldState = new WorldState();
        for (final WorldObject object : getWorldObjects().values()) {
            worldState.addGameObject(createNewUpdateObject(object));
        }
        return worldState;
    }

    private synchronized World getWorld() {
        return this.world;
    }

    /**
     * @return
     */
    public Map<String, WorldObject> getWorldObjects() {
        return getWorld().getWorldObjects();
    }

    /**
     * @param playerObject
     */
    public void removeObject(final String objectID) {
        getWorld().removeObject(objectID);
    }

}
