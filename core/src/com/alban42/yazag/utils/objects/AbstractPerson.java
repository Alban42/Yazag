package com.alban42.yazag.utils.objects;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;

public abstract class AbstractPerson {

    protected static final float DENSITY = 985;
    protected static final float FRICTION = .5f;
    protected static final int HEIGHT = 10;
    protected static final float RESTITUTION = .1f;
    protected static final int WIDTH = 10;
    private final BodyDef bodyDef;
    private final Body legs, body;

    public AbstractPerson(final World world, final Vector2 position) {

        // Definition of the body
        this.bodyDef = new BodyDef();
        // Dynamic because the person can move
        this.bodyDef.type = BodyType.DynamicBody;
        setPosition(position);

        // The shape is used for collision detections
        final PolygonShape shape = new PolygonShape();
        // Divided by 2 because the setAsBox method needs the half width and the half height
        shape.setAsBox(WIDTH / 2, HEIGHT / 2);

        final FixtureDef fixDef = new FixtureDef();
        fixDef.shape = shape;
        fixDef.density = DENSITY;
        fixDef.restitution = RESTITUTION;
        fixDef.friction = FRICTION;

        // Legs
        this.legs = world.createBody(this.bodyDef);
        this.legs.createFixture(fixDef);

        // Body
        // shape.setAsBox(width / 2 / 5, height / 3);
        fixDef.density /= 500;

        this.body = world.createBody(this.bodyDef);
        this.body.createFixture(fixDef);

        // joint
        final RevoluteJointDef jointDef = new RevoluteJointDef();
        jointDef.bodyA = this.legs;
        jointDef.bodyB = this.body;
        jointDef.localAnchorB.y = -HEIGHT / 3;
        jointDef.enableLimit = true;
        // jointDef.maxMotorTorque = 100;

        world.createJoint(jointDef);

        this.legs.setLinearDamping(3);
        this.legs.setAngularDamping(3);
        this.body.setLinearDamping(3);
        this.body.setAngularDamping(3);
    }

    public Vector2 getPosition() {
        return this.legs.getPosition();
    }

    public void setPosition(final Vector2 position) {
        this.bodyDef.position.set(position.x, position.y);
    }
}
