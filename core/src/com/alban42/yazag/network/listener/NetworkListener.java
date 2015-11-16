/**
 *
 */
package com.alban42.yazag.network.listener;

import com.alban42.yazag.common.network.ICaller;
import com.alban42.yazag.common.utils.objects.packet.Packet;
import com.alban42.yazag.common.utils.objects.world.*;
import com.alban42.yazag.network.Network;
import com.alban42.yazag.utils.objects.AbstractGameObject;
import com.alban42.yazag.utils.world.WorldManager;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;

/**
 * This class is the listener of {@link Network}. All the interactions with the server will be catched by this class.
 *
 * @author Alban
 */
public class NetworkListener extends Listener {

    private final Network network;

    /**
     * Constructor.
     *
     * @param network The parent of the listener.
     */
    public NetworkListener(final Network network) {
        this.network = network;
    }

    private void callback(final Packet response) {
        final ICaller caller = this.network.getPackets().get(response.getPacketId());
        if (caller != null) {
            this.network.getPackets().remove(response.getPacketId());
            caller.responseReceived(response);
        }
    }

    @Override
    public void connected(final Connection arg0) {
        Log.info("Connected !");
        this.network.setConnected(true);
    }

    @Override
    public void disconnected(final Connection arg0) {
        Log.info("Disconnected !");
        this.network.setConnected(false);
    }

    @Override
    public void received(final Connection connection, final Object object) {
        if (object instanceof Packet) {
            final Packet response = (Packet) object;

            if (object instanceof CurrentWorldState) {
                Log.info("Packet received ! Type [" + object.getClass().getSimpleName() + "] ID : " + response
                        .getPacketId());
                updateWorldState(((CurrentWorldState) object).worldState);
            } else if (object instanceof WorldState) {
                updateWorldState((WorldState) object);
            } else if (object instanceof CurrentPlayer) {
                Log.info("Packet received ! Type [" + object.getClass().getSimpleName() + "] ID : " + response
                        .getPacketId());
                WorldManager.INSTANCE
                        .setCurrentPlayer(WorldManager.INSTANCE.createNewGameObject(((CurrentPlayer) object).player));
            } else if (object instanceof RemoveObject) {
                Log.info("Remove object " + ((RemoveObject) object).objectId);
                WorldManager.INSTANCE.removeWorlObject(((RemoveObject) object).objectId);
            }

            callback(response);
        }
    }

    private void updateWorldState(final WorldState object) {
        AbstractGameObject gameObjectTMP;
        Log.debug("Game objects size " + object.getGameObjects().size());
        Log.debug("World Objects : " + WorldManager.INSTANCE.getWorldObjects().toString());
        for (final WorldUpdateObject gameObject : object.getGameObjects()) {
            Log.debug("Game object " + gameObject.objectID + " isActive : " + gameObject.active);
            // if the object is active, it will be created or updated to the world
            if (gameObject.active) {
                gameObjectTMP = WorldManager.INSTANCE.getWorldObjects().get(gameObject.objectID);
                Log.debug("gameObjectTMP " + gameObjectTMP);
                if (gameObjectTMP == null) {
                    Log.debug("Create new object ");
                    WorldManager.INSTANCE.createNewGameObject(gameObject);
                } else {
                    Log.debug("Update Object");
                    gameObjectTMP.update(gameObject);
                }
            } else {
                // Else, the object is removed from the world
                WorldManager.INSTANCE.removeWorlObject(gameObject.objectID);
            }
        }
    }
}
