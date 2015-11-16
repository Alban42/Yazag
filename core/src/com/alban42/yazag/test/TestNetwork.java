/**
 *
 */
package com.alban42.yazag.test;

import com.alban42.yazag.common.network.ICaller;
import com.alban42.yazag.common.utils.objects.packet.Packet;
import com.alban42.yazag.common.utils.objects.test.Message;
import com.alban42.yazag.network.Network;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

/**
 * @author Alban
 */
public class TestNetwork implements ICaller {

    private Packet response;
    private boolean responseReceived;

    public TestNetwork() {
        try {
            Network.INSTANCE.connect();
        } catch (final IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(final String[] args) {
        final TestNetwork testNetwork = new TestNetwork();
        testNetwork.sendAndWait("COUCOU !!");
        Log.info("END !");
    }

    @Override
    public void responseReceived(final Packet response) {
        Log.info("ResponseReceived !!");
        this.responseReceived = true;
        this.response = response;
    }

    public void sendAndWait(final String string) {
        final Message message = new Message();
        message.setMessage(string);
        try {
            Network.INSTANCE.sendAndWaitPacketTCP(message, this);
        } catch (final IOException e1) {
            e1.printStackTrace();
            System.exit(0);
        }
        Log.info("Waiting for response ... ");
        while (!this.responseReceived) {
            try {
                Thread.sleep(10);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

        Log.info("Response Received ! ");
        Log.info("And the message is: " + ((Message) this.response).getMessage());
    }
}
