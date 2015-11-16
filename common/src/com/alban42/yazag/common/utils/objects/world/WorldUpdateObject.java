/**
 *
 */
package com.alban42.yazag.common.utils.objects.world;

import com.alban42.yazag.common.utils.objects.packet.Packet;
import com.alban42.yazag.common.utils.objects.type.WorldObjectType;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Alban
 */
public class WorldUpdateObject extends Packet {

    /**
     * Set if the object is active in the world or not. If not, the clients will delete this object from their world.
     */
    public boolean active;

    /**
     * The dimensions of the object.
     */
    public Vector2 dimension;
    /**
     * The origin of the object. By default, the origin is at the center of the box :
     * <code>new Vector2(this.dimension.x / 2, this.dimension.y / 2)</code>;
     */
    public Vector2 origin;
    /**
     * The current position of the object.
     */
    public Vector2 position;
    /**
     * The current rotation of the object.
     */
    public float rotation;
    /**
     * The scaling factor of the object in the world.
     */
    public Vector2 scale;
    /**
     * The type of the object (used by the client to be able to create a new instance of it)
     */
    public WorldObjectType type;

    /**
     * A unique ID to identify the object.
     */
    public String objectID;

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "WorldUpdateObject [dimension=" + this.dimension + ", origin=" + this.origin + ", position=" + this.position + ", rotation=" + this.rotation + ", scale=" + this.scale + ", type=" + this.type + ", uniqueID=" + this.objectID + "]";
    }

}
