/**
 *
 */
package com.alban42.yazag.common.utils.objects.action;

import com.alban42.yazag.common.utils.objects.packet.Packet;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alban
 */
public class PlayerActions extends Packet {

    private List<Action> actions;
    private float delta;

    public PlayerActions() {
        reset();
    }

    public void addAction(final Action action) {
        this.actions.add(action);
    }

    /**
     * @return the actions
     */
    public List<Action> getActions() {
        return this.actions;
    }

    /**
     * @return the delta
     */
    public float getDelta() {
        return this.delta;
    }

    /**
     * @param delta the delta to set
     */
    public void setDelta(final float delta) {
        this.delta = delta;
    }

    /**
     *
     */
    public void reset() {
        this.actions = new ArrayList<Action>();
    }
}
