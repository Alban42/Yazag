/**
 *
 */
package com.alban42.yazag.common.utils.objects.action;

/**
 * @author Alban
 */
public class Action {

    private ActionEnum action;
    private float value;

    public Action() {

    }

    /**
     * @param rotate
     */
    public Action(final ActionEnum action) {
        this.action = action;
    }

    /**
     * @return the action
     */
    public ActionEnum getAction() {
        return this.action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(final ActionEnum action) {
        this.action = action;
    }

    /**
     * @return the value
     */
    public float getValue() {
        return this.value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(final float value) {
        this.value = value;
    }
}
