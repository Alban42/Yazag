/**
 *
 */
package com.alban42.yazag.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * @author Alban
 */
public class Alban42Utils {

    public static void clearScreen() {
        Gdx.gl.glClearColor(0.2f, 0.2f, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    /**
     * Return the name (in lower case) of the {@link Color} in parameter (e.g. "black" for {@link Color#BLACK}).
     *
     * @param color the color to get the name
     * @return the name of the color
     */
    public static String getFontName(final Color color) {
        String name = null;
        if (color.equals(Color.BLACK)) {
            name = "black";
        } else if (color.equals(Color.BLUE)) {
            name = "blue";
        } else if (color.equals(Color.CLEAR)) {
            name = "clear";
        } else if (color.equals(Color.CYAN)) {
            name = "cyan";
        } else if (color.equals(Color.DARK_GRAY)) {
            name = "dark_gray";
        } else if (color.equals(Color.GRAY)) {
            name = "gray";
        } else if (color.equals(Color.GREEN)) {
            name = "green";
        } else if (color.equals(Color.LIGHT_GRAY)) {
            name = "light_gray";
        } else if (color.equals(Color.MAGENTA)) {
            name = "magenta";
        } else if (color.equals(Color.ORANGE)) {
            name = "orange";
        } else if (color.equals(Color.PINK)) {
            name = "pink";
        } else if (color.equals(Color.RED)) {
            name = "red";
        } else if (color.equals(Color.WHITE)) {
            name = "white";
        } else if (color.equals(Color.YELLOW)) {
            name = "yellow";
        }
        return name;
    }

}
