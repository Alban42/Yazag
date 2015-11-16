/**
 *
 */
package com.alban42.yazag.utils.asset;

import com.alban42.yazag.game.objects.pixmap.PixmapObject;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;

/**
 * @author Alban
 * @see http://youtu.be/JXThbQir2gU
 */
public class Asset {

    public static final AssetManager manager = new AssetManager();
    private static final int HEIGHT = 32, WIDTH = 32;
    private static final String TAG = Asset.class.getName();
    public static TiledMap map;
    public static Texture pixmapTexture;

    public static void dispose() {
        manager.dispose();
    }

    public static void load() {
        Gdx.app.log(TAG, "Loading assets");
        final Pixmap pixmap = PixmapObject.createPixmap(WIDTH, HEIGHT, Color.RED);
        map = new TmxMapLoader().load("map/map.tmx");
        pixmapTexture = new Texture(pixmap);
        while (!manager.update()) {
            Gdx.app.log(TAG, (manager.getProgress() * 100) + "% loaded");
        }
    }
}
