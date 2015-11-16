package com.alban42.yazag.server;

import com.alban42.yazag.common.network.register.ClassRegister;
import com.alban42.yazag.server.listener.MainServerListener;
import com.alban42.yazag.server.logic.GameLogic;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

public class MainServer {
    private static final int DEFAULT_TCP_PORT = 27960;
    private static final int DEFAULT_UDP_PORT = 27961;
    private final GameLogic gameLogic;
    private final Thread gameLogicThread;
    private final Server server;

    /**
     * Default constructor, use the DEFAULT_TCP_PORT and DEFAULT_UDP_PORT constants.
     */
    public MainServer() {
        this(DEFAULT_TCP_PORT, DEFAULT_UDP_PORT);
    }

    public MainServer(final int tcpPort, final int udpPort) {
        this.server = new Server();
        ClassRegister.register(this.server);
        this.server.addListener(new MainServerListener(this.server));
        try {
            this.server.bind(tcpPort, udpPort);
        } catch (final IOException e) {
            e.printStackTrace();
            Log.error(e.getMessage());
        }
        this.gameLogic = new GameLogic(this.server);
        this.gameLogicThread = new Thread(this.gameLogic);
    }

    public void start() {
        this.server.start();
        this.gameLogicThread.start();
        Log.info("[MainServer] Server Started !");
    }

    public void stop() {
        this.server.stop();
        this.gameLogic.stop();
        Log.info("[MainServer] Server Stoped !");
    }
}
