/**
 *
 */
package com.alban42.yazag.server.world.objects;

import com.alban42.yazag.common.utils.objects.type.WorldObjectType;
import com.alban42.yazag.server.world.WorldManager;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

import java.util.UUID;

/**
 * @author Alban
 */
public class WorldObject {

    protected static int DEFAULT_FRICTION = 4;
    protected static int DEFAULT_TERMINAL_VELOCITY = 10;
    private final String objectID;
    /**
     * Object's constant acceleration in m/s2.
     */
    public Vector2 acceleration;
    /**
     * Set if the object is active in the world or not. If not, the clients will delete this object from their world.
     */
    public boolean active;
    /**
     * The object's bounding box describes the physical body that will be used for collision detection with others
     * objects. The bounding box can be set to any size and is completely independent of the actual dimension of the
     * object in the game world.
     */
    public Rectangle bounds;
    /**
     * The dimensions of the object.
     */
    public Vector2 dimension;
    /**
     * This is an opposing force that slows down the object until it's velocity equals zero. This value is given as a
     * coefficient which is dimensionless. A value of zero means no friction, and thus the object's velocity will not
     * decrease.
     */
    public Vector2 friction;
    /**
     * The origin of the object. By default, the origin is at the center of the box :
     * <code>new Vector2(this.dimension.x / 2, this.dimension.y / 2)</code>;
     */
    public Vector2 origin;
    /**
     * The current position of the object.
     */
    public Vector2 position;
    public Vector2 positionTMP;
    /**
     * The current rotation of the object.
     */
    public float rotation;
    /**
     * The scaling factor of the object in the world.
     */
    public Vector2 scale;
    public float stateTime;
    /**
     * Object's positive and negative maximum speed in m/s
     */
    public Vector2 terminalVelocity;
    public WorldObjectType type;
    /**
     * Object's current speed in m/s
     */
    public Vector2 velocity;
    private Rectangle collisionRectangle;
    private WorldObject worldObjectTMP;

    public WorldObject(final WorldObjectType type, final Vector2 position, final Vector2 dimension, final Vector2 scale) {
        this.objectID = UUID.randomUUID().toString();
        this.active = true;
        this.type = type;
        this.position = position;
        this.positionTMP = new Vector2();
        this.dimension = dimension;
        this.origin = new Vector2(this.dimension.x / 2, this.dimension.y / 2);
        this.scale = scale;

        this.bounds = new Rectangle(0, 0, this.dimension.x * this.scale.x, this.dimension.y * this.scale.y);

        this.rotation = 0;
        this.velocity = new Vector2();
        this.terminalVelocity = new Vector2(DEFAULT_TERMINAL_VELOCITY, DEFAULT_TERMINAL_VELOCITY);
        this.friction = new Vector2(DEFAULT_FRICTION, DEFAULT_FRICTION);
        this.acceleration = new Vector2();
    }

    @Override
    public WorldObject clone() throws CloneNotSupportedException {
        if (this.worldObjectTMP == null) {
            this.worldObjectTMP = new WorldObject(this.type, this.position, this.dimension, this.scale);
        }
        this.worldObjectTMP.type = this.type;
        this.worldObjectTMP.position = this.position;
        this.worldObjectTMP.dimension = this.dimension;
        this.worldObjectTMP.scale = this.scale;
        this.worldObjectTMP.acceleration = this.acceleration;
        this.worldObjectTMP.bounds = this.bounds;
        this.worldObjectTMP.friction = this.friction;
        this.worldObjectTMP.origin = this.origin;
        this.worldObjectTMP.rotation = this.rotation;
        this.worldObjectTMP.stateTime = this.stateTime;
        this.worldObjectTMP.terminalVelocity = this.terminalVelocity;
        this.worldObjectTMP.velocity = this.velocity;
        return this.worldObjectTMP;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final WorldObject other = (WorldObject) obj;
        if (this.acceleration == null) {
            if (other.acceleration != null) {
                return false;
            }
        } else if (!this.acceleration.equals(other.acceleration)) {
            return false;
        }
        if (this.bounds == null) {
            if (other.bounds != null) {
                return false;
            }
        } else if (!this.bounds.equals(other.bounds)) {
            return false;
        }
        if (this.dimension == null) {
            if (other.dimension != null) {
                return false;
            }
        } else if (!this.dimension.equals(other.dimension)) {
            return false;
        }
        if (this.friction == null) {
            if (other.friction != null) {
                return false;
            }
        } else if (!this.friction.equals(other.friction)) {
            return false;
        }
        if (this.origin == null) {
            if (other.origin != null) {
                return false;
            }
        } else if (!this.origin.equals(other.origin)) {
            return false;
        }
        if (this.position == null) {
            if (other.position != null) {
                return false;
            }
        } else if (!this.position.equals(other.position)) {
            return false;
        }
        if (Float.floatToIntBits(this.rotation) != Float.floatToIntBits(other.rotation)) {
            return false;
        }
        if (this.scale == null) {
            if (other.scale != null) {
                return false;
            }
        } else if (!this.scale.equals(other.scale)) {
            return false;
        }
        if (Float.floatToIntBits(this.stateTime) != Float.floatToIntBits(other.stateTime)) {
            return false;
        }
        if (this.terminalVelocity == null) {
            if (other.terminalVelocity != null) {
                return false;
            }
        } else if (!this.terminalVelocity.equals(other.terminalVelocity)) {
            return false;
        }
        if (this.velocity == null) {
            if (other.velocity != null) {
                return false;
            }
        } else if (!this.velocity.equals(other.velocity)) {
            return false;
        }
        return true;
    }

    /**
     * @return
     */
    public Rectangle getBounds() {
        this.bounds.x = this.position.x;
        this.bounds.y = this.position.y;
        return this.bounds;
    }

    /**
     * @return the objectID
     */
    public String getObjectID() {
        return this.objectID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((this.acceleration == null) ? 0 : this.acceleration.hashCode());
        result = (prime * result) + ((this.bounds == null) ? 0 : this.bounds.hashCode());
        result = (prime * result) + ((this.dimension == null) ? 0 : this.dimension.hashCode());
        result = (prime * result) + ((this.friction == null) ? 0 : this.friction.hashCode());
        result = (prime * result) + ((this.origin == null) ? 0 : this.origin.hashCode());
        result = (prime * result) + ((this.position == null) ? 0 : this.position.hashCode());
        result = (prime * result) + Float.floatToIntBits(this.rotation);
        result = (prime * result) + ((this.scale == null) ? 0 : this.scale.hashCode());
        result = (prime * result) + Float.floatToIntBits(this.stateTime);
        result = (prime * result) + ((this.terminalVelocity == null) ? 0 : this.terminalVelocity.hashCode());
        result = (prime * result) + ((this.velocity == null) ? 0 : this.velocity.hashCode());
        return result;
    }

    /**
     * @param playerObjectTMP
     */
    public void set(final WorldObject worldObject) {
        this.type = worldObject.type;
        this.position = worldObject.position;
        this.dimension = worldObject.dimension;
        this.scale = worldObject.scale;
        this.acceleration = worldObject.acceleration;
        this.bounds = worldObject.bounds;
        this.friction = worldObject.friction;
        this.origin = worldObject.origin;
        this.rotation = worldObject.rotation;
        this.stateTime = worldObject.stateTime;
        this.terminalVelocity = worldObject.terminalVelocity;
        this.velocity = worldObject.velocity;
    }

    private boolean testCollision(final Vector2 positionTMP) {
        boolean collision = false;
        this.collisionRectangle = getBounds();
        this.collisionRectangle.x = positionTMP.x;
        this.collisionRectangle.y = positionTMP.y;
        // Log.info(" ------------- ");
        for (final WorldObject object : WorldManager.INSTANCE.getWorldObjects().values()) {
            if (!object.equals(this) && object.active) {
                // Log.info("Rectangles " + this.collisionRectangle.toString() + " - " + object.getBounds().toString());
                collision = object.getBounds().overlaps(this.collisionRectangle);
                if (collision) {
                    break;
                }
            }
        }
        return collision;
    }

    public void update(final float deltaTime) {
        this.stateTime += deltaTime;
        updateMotionX(deltaTime);
        updateMotionY(deltaTime);

        // Test collision
        this.positionTMP.x = this.position.x + (this.velocity.x * deltaTime);
        this.positionTMP.y = this.position.y + (this.velocity.y * deltaTime);

        if (!testCollision(this.positionTMP)) {
            this.position.x = this.positionTMP.x;
            this.position.y = this.positionTMP.y;
        } else {
            this.velocity.x = 0;
            this.velocity.y = 0;
        }

        // Move to new position
        // this.position.x += this.velocity.x * deltaTime;
        // this.position.y += this.velocity.y * deltaTime;

    }

    protected void updateMotionX(final float deltaTime) {
        if (this.velocity.x != 0) {
            // Apply friction
            if (this.velocity.x > 0) {
                this.velocity.x = Math.max(this.velocity.x - (this.friction.x * deltaTime), 0);
            } else {
                this.velocity.x = Math.min(this.velocity.x + (this.friction.x * deltaTime), 0);
            }
        }
        // Apply acceleration
        this.velocity.x += this.acceleration.x * deltaTime;
        // Make sure the object's velocity does not exceed the positive or negative terminal velocity
        this.velocity.x = MathUtils.clamp(this.velocity.x, -this.terminalVelocity.x, this.terminalVelocity.x);
    }

    protected void updateMotionY(final float deltaTime) {
        if (this.velocity.y != 0) {
            // Apply friction
            if (this.velocity.y > 0) {
                this.velocity.y = Math.max(this.velocity.y - (this.friction.y * deltaTime), 0);
            } else {
                this.velocity.y = Math.min(this.velocity.y + (this.friction.y * deltaTime), 0);
            }
        }
        // Apply acceleration
        this.velocity.y += this.acceleration.y * deltaTime;
        // Make sure the object's velocity does not exceed the positive or negative terminal velocity
        this.velocity.y = MathUtils.clamp(this.velocity.y, -this.terminalVelocity.y, this.terminalVelocity.y);
    }
}
