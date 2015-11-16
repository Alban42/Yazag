/**
 *
 */
package com.alban42.yazag.utils.objects;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Alban
 */
public class World {

    private final Map<String, AbstractGameObject> worldObjects;

    public World() {
        // Create the map with ConcurrentHashMap to avoid ConcurrentModificationException problems
        this.worldObjects = new ConcurrentHashMap<String, AbstractGameObject>();
    }

    public void addObject(final AbstractGameObject object) {
        this.worldObjects.put(object.getUniqueID(), object);
    }

    public AbstractGameObject getObject(final String id) {
        return this.worldObjects.get(id);
    }

    /**
     * @return the worldObjects
     */
    public Map<String, AbstractGameObject> getWorldObjects() {
        return this.worldObjects;
    }

    public AbstractGameObject removeObject(final String id) {
        return this.worldObjects.remove(id);
    }
}
