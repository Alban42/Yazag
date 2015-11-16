/**
 *
 */
package com.alban42.yazag.common.utils.objects.test;

import com.alban42.yazag.common.utils.objects.packet.Packet;

/**
 * @author Alban
 */
public class Message extends Packet {

    private String message;

    /**
     * @return the message
     */
    public String getMessage() {
        return this.message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
        this.message = message;
    }
}
