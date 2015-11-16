package com.alban42.yazag.server.listener;

import com.alban42.yazag.common.utils.objects.action.PlayerActions;
import com.alban42.yazag.common.utils.objects.test.Message;
import com.alban42.yazag.common.utils.objects.world.CurrentPlayer;
import com.alban42.yazag.common.utils.objects.world.CurrentWorldState;
import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.alban42.yazag.server.world.WorldManager;
import com.alban42.yazag.server.world.players.Player;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import com.esotericsoftware.minlog.Log;

import java.util.HashMap;
import java.util.Map;

public class MainServerListener extends Listener {

    /**
     * The number of ms to wait until sending the answer. Set it to -1 to disable it.
     */
    private static final int LAG_SIMULATION = -1;
    private static final String TAG = MainServerListener.class.getSimpleName();
    private final Map<Integer, Player> players;
    private final Server server;

    /**
     * @param server
     */
    public MainServerListener(final Server server) {
        super();
        this.server = server;
        this.players = new HashMap<Integer, Player>();
    }

    /**
     * @param connection
     * @param object
     */
    private void addPlayerActions(final Connection connection, final PlayerActions actions) {
        this.players.get(connection.getID()).addPlayerActions(actions);
    }

    @Override
    public void connected(final Connection connection) {
        Log.info(MainServerListener.TAG, "New client connected ! " + connection.getID());
        final Player player = new Player(connection.getID());
        this.players.put(connection.getID(), player);
        new Thread(player).start();
        super.connected(connection);
    }

    @Override
    public void disconnected(final Connection connection) {
        final int connectionId = connection.getID();
        Log.info(MainServerListener.TAG, "Client disconnected ! " + connectionId);
        final Player player = this.players.get(connectionId);
        if (player != null) {
            // stop the player thread
            player.stop();
            // deactivate the object in the world state
            player.deactivatePlayerObject();
            // remove the player from the list of connected players
            this.players.remove(connectionId);
        }
        super.disconnected(connection);
    }

    @SuppressWarnings("unused")
    @Override
    public void received(final Connection connection, final Object object) {
        Log.debug(MainServerListener.TAG, "Packet received");

        if (LAG_SIMULATION > -1) {
            try {
                Thread.sleep(LAG_SIMULATION);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

        if (object instanceof Message) {
            ((Message) object).setMessage(((Message) object).getMessage() + " From the Server !!!");
            connection.sendTCP(object);
        } else if (object instanceof PlayerActions) {
            addPlayerActions(connection, (PlayerActions) object);
        } else if (object instanceof CurrentWorldState) {
            Log.info(MainServerListener.TAG, "CurrentWorldState packet received");
            ((CurrentWorldState) object).worldState = WorldManager.INSTANCE.getCurrentWorldState();
            connection.sendTCP(object);
        } else if (object instanceof CurrentPlayer) {
            Log.info(MainServerListener.TAG, "CurrentPlayer packet received");
            final WorldUpdateObject currentPlayer = WorldManager
                    .createNewUpdateObject(this.players.get(connection.getID()).getPlayerObject());
            ((CurrentPlayer) object).player = currentPlayer;
            connection.sendTCP(object);
        }
    }
}
