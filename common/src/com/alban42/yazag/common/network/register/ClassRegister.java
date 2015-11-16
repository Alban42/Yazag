/**
 *
 */
package com.alban42.yazag.common.network.register;

import com.alban42.yazag.common.utils.objects.action.Action;
import com.alban42.yazag.common.utils.objects.action.ActionEnum;
import com.alban42.yazag.common.utils.objects.action.PlayerActions;
import com.alban42.yazag.common.utils.objects.packet.Packet;
import com.alban42.yazag.common.utils.objects.test.Message;
import com.alban42.yazag.common.utils.objects.type.WorldObjectType;
import com.alban42.yazag.common.utils.objects.world.*;
import com.badlogic.gdx.math.Vector2;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author Alban
 */
public class ClassRegister {

    // This registers objects that are going to be sent over the network.
    static public void register(final EndPoint endPoint) {
        final Kryo kryo = endPoint.getKryo();
        // Standard classes
        kryo.register(UUID.class);
        kryo.register(ArrayList.class);
        kryo.register(Vector2.class);
        kryo.register(List.class);
        kryo.register(String.class);

        // Special classes
        kryo.register(Packet.class);
        kryo.register(PlayerActions.class);
        kryo.register(Action.class);
        kryo.register(ActionEnum.class);
        kryo.register(WorldState.class);
        kryo.register(CurrentWorldState.class);
        kryo.register(CurrentPlayer.class);
        kryo.register(WorldObjectType.class);
        kryo.register(WorldUpdateObject.class);
        kryo.register(Message.class);
        kryo.register(RemoveObject.class);

    }
}
