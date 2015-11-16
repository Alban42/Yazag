package com.alban42.yazag.utils;

public class Constants {

    public static final boolean DEBUG = true;
    // Sub-Title
    public static final String SUBTITLE = "Yet Another Zombie Apocalypse Game";
    // Title
    public static final String TITLE = "YAZAG";
    // Version
    public static final String VERSION = "a.0.0";
    // GUI Height
    public static final float VIEWPORT_GUI_HEIGHT = 480.0f;
    // GUI Width
    public static final float VIEWPORT_GUI_WIDTH = 800.0f;
    // Visible game world is 5 meters tall
    public static final float VIEWPORT_HEIGHT = 5.0f;
    // Visible game world is 5 meters wide
    public static final float VIEWPORT_WIDTH = 5.0f;
    public static final boolean VSYNCENABLED = true;
    public static final int WINDOW_HEIGHT = 800;
    public static final int WINDOW_WIDTH = 800;
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_TCP_PORT = 27960;
    private static final int DEFAULT_UDP_POR = 27961;
    // The name of the font to use (have to be into the font folder in the asset)
    private static final String FONT = "Bauhaus93";
    // The name of the font folder
    private static final String FONT_FOLDER = "font";

    public static String getFont(final String sufix) {
        return FONT_FOLDER + "/" + FONT + "_" + sufix + ".fnt";
    }

    /**
     * @return
     */
    public static String getHost() {
        return DEFAULT_HOST;
    }

    /**
     * @return
     */
    public static int getTCPPort() {
        return DEFAULT_TCP_PORT;
    }

    /**
     * @return
     */
    public static int getUDPPort() {
        return DEFAULT_UDP_POR;
    }
}
