/**
 *
 */
package com.alban42.yazag.server.world.players;

import com.alban42.yazag.common.utils.objects.type.WorldObjectType;
import com.alban42.yazag.server.world.objects.WorldObject;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Alban
 */
public class PlayerObject extends WorldObject {

    private final static Vector2 dimension = new Vector2(1, 1);
    private final static int FRICTION = 150;

    private final static Vector2 scale = new Vector2(32, 32);
    private final static int TERMINAL_VELOCITY = 100;
    private final static WorldObjectType type = WorldObjectType.PLAYER;

    /**
     * @param type
     * @param position
     * @param dimension
     * @param scale
     */
    public PlayerObject(final Vector2 position) {
        super(type, position, dimension, scale);
        this.friction = new Vector2(FRICTION, FRICTION);
        this.terminalVelocity = new Vector2(TERMINAL_VELOCITY, TERMINAL_VELOCITY);

    }

}
