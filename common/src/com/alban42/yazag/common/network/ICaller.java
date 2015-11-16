/**
 *
 */
package com.alban42.yazag.common.network;

import com.alban42.yazag.common.utils.objects.packet.Packet;

/**
 * @author Alban
 */
public interface ICaller {

    public void responseReceived(Packet response);

}
