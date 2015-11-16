package com.alban42.yazag.game.desktop;

import com.alban42.yazag.game.YazagGame;
import com.alban42.yazag.utils.Constants;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class DesktopLauncher {
    public static void main(String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        config.title = Constants.TITLE + " v" + Constants.VERSION;
        config.vSyncEnabled = Constants.VSYNCENABLED;
        config.height = Constants.WINDOW_HEIGHT;
        config.width = Constants.WINDOW_WIDTH;
        new LwjglApplication(new YazagGame(), config);
    }
}
