package com.alban42.yazag.game;

import com.alban42.yazag.common.utils.logger.Alban42Logger;
import com.alban42.yazag.game.screens.GameScreen;
import com.alban42.yazag.utils.asset.Asset;
import com.badlogic.gdx.Game;
import com.esotericsoftware.minlog.Log;

public class YazagGame extends Game {
    @Override
    public void create() {
        Log.setLogger(new Alban42Logger());
        Log.INFO();
        Asset.load();
        // setScreen(new SplashScreen(this));
        // setScreen(new MenuScreen(this));
        setScreen(new GameScreen(this));
    }

    @Override
    public void dispose() {
        Asset.dispose();
        super.dispose();
    }
}
