/**
 *
 */
package com.alban42.yazag.common.utils.objects.world;

import com.alban42.yazag.common.utils.objects.packet.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alban
 */
public class WorldState extends Packet {

    private List<WorldUpdateObject> gameObjects;

    public WorldState() {
        super();
        this.gameObjects = new ArrayList<WorldUpdateObject>();
    }

    public void addGameObject(final WorldUpdateObject gameObject) {
        getGameObjects().add(gameObject);
    }

    /**
     * @return the gameObject
     */
    public List<WorldUpdateObject> getGameObjects() {
        return this.gameObjects;
    }

    /**
     * @param gameObject the gameObject to set
     */
    public void setGameObjects(final List<WorldUpdateObject> gameObject) {
        this.gameObjects = gameObject;
    }

    public boolean isVoid() {
        return this.gameObjects.isEmpty();
    }

    /**
     *
     */
    public void reset() {
        this.gameObjects = new ArrayList<WorldUpdateObject>();
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WorldState [gameObjects=" + this.gameObjects + "]";
    }

}
