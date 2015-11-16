/**
 *
 */
package com.alban42.yazag.server.world.objects;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alban
 */
public class World {

    private final Map<String, WorldObject> worldObjects;

    public World() {
        // Create the map with ConcurrentHashMap to avoid ConcurrentModificationException problems
        this.worldObjects = new ConcurrentHashMap<String, WorldObject>();
    }

    public void addObject(final WorldObject object) {
        this.worldObjects.put(object.getObjectID(), object);
    }

    public WorldObject getObject(final String id) {
        return this.worldObjects.get(id);
    }

    public Vector2 getRandomPostion() {
        final Vector2 position = new Vector2();
        position.x = MathUtils.random(-2.0f, 2.0f);
        position.y = MathUtils.random(-2.0f, 2.0f);
        return position;
    }

    /**
     * @return the worldObjects
     */
    public Map<String, WorldObject> getWorldObjects() {
        return this.worldObjects;
    }

    public WorldObject removeObject(final String id) {
        return this.worldObjects.remove(id);
    }
}
