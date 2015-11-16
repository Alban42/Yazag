package com.alban42.yazag.server;

import com.alban42.yazag.common.utils.logger.Alban42Logger;
import com.esotericsoftware.minlog.Log;

/**
 * @author Alban
 */
public class Main {

    /**
     * @param args
     */
    public static void main(final String[] args) {
        Log.setLogger(new Alban42Logger());
        Log.INFO();
        final MainServer server = new MainServer();
        server.start();
    }
}
