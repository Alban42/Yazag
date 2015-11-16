package com.alban42.yazag.game.objects;

import com.alban42.yazag.utils.objects.AbstractPerson;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

public class Humain extends AbstractPerson {

    public Humain(final World world, final Vector2 position) {
        super(world, position);
    }

}
