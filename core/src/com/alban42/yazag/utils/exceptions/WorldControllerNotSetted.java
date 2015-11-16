/**
 *
 */
package com.alban42.yazag.utils.exceptions;

/**
 * @author Alban
 */
public class WorldControllerNotSetted extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -4191663844987118024L;

    @Override
    public String getMessage() {
        return "You must set the world controller with the method 'setWorldController(worldController)'.";
    }

}
