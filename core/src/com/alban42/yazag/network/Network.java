/**
 *
 */
package com.alban42.yazag.network;

import com.alban42.yazag.common.network.ICaller;
import com.alban42.yazag.common.network.register.ClassRegister;
import com.alban42.yazag.common.utils.objects.packet.Packet;
import com.alban42.yazag.network.listener.NetworkListener;
import com.alban42.yazag.utils.Constants;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * This class provide an easy way to send packet to the server.</p>
 * <p/>
 * This class is an Singleton, {@link Network#INSTANCE} must be used to call any method of this class.</p>
 * <p/>
 * The {@link NetworkListener} is used as the listener of the responses of by the server.
 *
 * @author Alban
 */
public class Network {

    public final static Network INSTANCE = new Network();
    private final Client client;
    private final String host = Constants.getHost();
    private final Map<String, ICaller> packets;
    private final int TCPPort = Constants.getTCPPort();
    private final int UDPPort = Constants.getUDPPort();
    private boolean connected;

    private Network() {
        this.connected = false;
        this.client = new Client();
        this.packets = new HashMap<String, ICaller>();
        ClassRegister.register(this.client);
        this.client.addListener(new NetworkListener(this));
    }

    public void connect() throws IOException {
        this.client.start();
        try {
            this.client.connect(5000, this.host, this.TCPPort, this.UDPPort);
        } catch (final IOException e) {
            Log.info("Cannot connect : " + e.getMessage());
            throw e;
        }
        this.connected = true;
    }

    public void disconnect() {
        this.client.stop();
    }

    /**
     * @return the packets
     */
    public Map<String, ICaller> getPackets() {
        return this.packets;
    }

    /**
     * @return the connected
     */
    public boolean isConnected() {
        return this.connected;
    }

    /**
     * @param connected the connected to set
     */
    public void setConnected(final boolean connected) {
        this.connected = connected;
    }

    /**
     * Send a packet through the network (via TCP) and call the {@link ICaller#responseReceived(Packet)} method
     * when a response is received by the server.
     *
     * @param packet the packet to send.
     * @param caller the caller to callback.
     * @return the number of bytes sent.
     * @throws IOException
     */
    public int sendAndWaitPacketTCP(final Packet packet, final ICaller caller) throws IOException {
        this.packets.put(packet.getPacketId(), caller);
        return sendPacketTCP(packet);
    }

    /**
     * Send a packet through the network (via UDP) and call the {@link ICaller#responseReceived(Packet)} method
     * when a response is received by the server.
     *
     * @param packet the packet to send.
     * @param caller the caller to callback.
     * @return the number of bytes sent.
     * @throws IOException
     */
    public int sendAndWaitPacketUDP(final Packet packet, final ICaller caller) throws IOException {
        this.packets.put(packet.getPacketId(), caller);
        return sendPacketUDP(packet);
    }

    /**
     * Send the packet through the network to the server via TCP protocol.
     *
     * @param packet the packet to send
     * @return the number of bytes sent.
     */
    public int sendPacketTCP(final Packet packet) throws IOException {
        if (!isConnected()) {
            this.client.reconnect();
        }
        return this.client.sendTCP(packet);
    }

    /**
     * Send the packet through the network to the server via UDP protocol.
     *
     * @param packet the packet to send
     * @return the number of bytes sent.
     */
    public int sendPacketUDP(final Packet packet) throws IOException {
        if (!isConnected()) {
            this.client.reconnect();
        }
        return this.client.sendUDP(packet);
    }
}
