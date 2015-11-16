/**
 *
 */
package com.alban42.yazag.game.screens;

import com.alban42.yazag.common.network.ICaller;
import com.alban42.yazag.common.utils.objects.packet.Packet;
import com.alban42.yazag.common.utils.objects.world.CurrentPlayer;
import com.alban42.yazag.common.utils.objects.world.CurrentWorldState;
import com.alban42.yazag.game.world.game.GameController;
import com.alban42.yazag.game.world.game.GameRenderer;
import com.alban42.yazag.network.Network;
import com.alban42.yazag.utils.screens.AbstractGameScreen;
import com.badlogic.gdx.Game;
import com.esotericsoftware.minlog.Log;

import java.io.IOException;

/**
 * @author Alban
 */
public class GameScreen extends AbstractGameScreen implements ICaller {

    private boolean playerReceived = false;
    private boolean worldStateReceived = false;

    /**
     * @param game
     */
    public GameScreen(final Game game) {
        super(game, new GameController(), new GameRenderer());
        try {
            Log.info(TAG, "Connection with the server");
            Network.INSTANCE.connect();
            while (!Network.INSTANCE.isConnected()) {
                Log.info(TAG, "Waiting for connection...");
                Thread.sleep(100);
            }
            Log.info(TAG, "Connected");
            Network.INSTANCE.sendAndWaitPacketTCP(new CurrentPlayer(), this);
            Log.info(TAG, "Waiting for player...");
            while (!this.playerReceived) {
                Thread.sleep(100);
                Log.info(TAG, "Player ...");
            }

            Network.INSTANCE.sendAndWaitPacketTCP(new CurrentWorldState(), this);
            Log.info(TAG, "Waiting for world state...");
            while (!this.worldStateReceived) {
                Thread.sleep(100);
                Log.info(TAG, "World ...");
            }
            Log.info("load map");

            Log.info(TAG, "World Initialized");
        } catch (final IOException e) {
            e.printStackTrace();
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void responseReceived(final Packet response) {
        if (response instanceof CurrentWorldState) {
            this.worldStateReceived = true;
        } else if (response instanceof CurrentPlayer) {
            this.playerReceived = true;
        }
    }

}
