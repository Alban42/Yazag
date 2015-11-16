package com.alban42.yazag.utils;

import com.badlogic.gdx.math.Vector2;

public class Trigo {

    public static double distance(final int x1, final int y1, final int x2, final int y2) {
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }

    public static double getAngle(final Vector2 vec1, final Vector2 vec2) {
        final double angle = Math.atan2(vec1.x - vec2.x, vec2.y - vec1.y) - (Math.PI / 2);
        return Math.toDegrees(angle) - 180;
    }

    public static Vector2 getPoint(final Vector2 center, final double angle, final double distance) {
        // Angles in java are measured clockwise from 3 o'clock.
        final double theta = Math.toRadians(angle);
        final Vector2 p = new Vector2();
        p.x = (float) (center.x + (distance * Math.cos(theta)));
        p.y = (float) (center.y + (distance * Math.sin(theta)));
        return p;
    }
}
