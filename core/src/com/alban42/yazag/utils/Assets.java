/**
 *
 */
package com.alban42.yazag.utils;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Disposable;

/**
 * @author Alban
 */
public class Assets implements Disposable {
    public static final Assets instance = new Assets();
    public final AssetManager manager;
    public Texture badlogic;

    private Assets() {
        this.manager = new AssetManager();
        init();
    }

    @Override
    public void dispose() {
        this.manager.dispose();
        this.badlogic.dispose();
    }

    public void init() {
        this.manager.load("img/splash/badlogic.jpg", Texture.class);
        this.manager.finishLoading();
        set();
    }

    public void set() {
        this.badlogic = this.manager.get("img/splash/badlogic.jpg", Texture.class);
    }

    public boolean update() {
        if (this.manager.update()) {
            set();
            return true;
        }
        return false;
    }
}
