/**
 *
 */
package com.alban42.yazag.utils.world;

import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.alban42.yazag.game.objects.pixmap.PixmapObjectFactory;
import com.alban42.yazag.game.objects.sprite.SpriteObjectFactory;
import com.alban42.yazag.utils.objects.AbstractGameObject;
import com.alban42.yazag.utils.objects.World;
import com.esotericsoftware.minlog.Log;

import java.util.Map;

/**
 * @author Alban
 */
public class WorldManager {

    public static final WorldManager INSTANCE = new WorldManager();
    private final World world;
    private AbstractWorldController worldController;

    /**
     * @param world
     */
    private WorldManager() {
        this.world = new World();
    }

    public void addWorldObject(final AbstractGameObject object) {
        getWorld().addObject(object);
    }

    /**
     * @param gameObject
     */
    public AbstractGameObject createNewGameObject(final WorldUpdateObject gameObject) {
        AbstractGameObject newGameObject = null;
        Log.info("createNewGameObject", "GameObject type : " + gameObject.type + " ID : " + gameObject.objectID);
        switch (gameObject.type) {
            case PLAYER:
                newGameObject = PixmapObjectFactory.INSTANCE.createNewObject(gameObject);
                break;
            case OTHER_PLAYER:
                newGameObject = PixmapObjectFactory.INSTANCE.createNewObject(gameObject);
                break;
            case PIXMAP:
                newGameObject = PixmapObjectFactory.INSTANCE.createNewObject(gameObject);
                break;
            case SPRITE:
                newGameObject = SpriteObjectFactory.INSTANCE.createNewObject(gameObject);
                break;
            default:
                break;
        }

        if (newGameObject != null) {
            getWorld().addObject(newGameObject);
        }

        return newGameObject;
    }

    private synchronized World getWorld() {
        return this.world;
    }

    /**
     * @return
     */
    public Map<String, AbstractGameObject> getWorldObjects() {
        return getWorld().getWorldObjects();
    }

    /**
     * @param objectId
     */
    public void removeWorlObject(final String objectId) {
        getWorld().removeObject(objectId);
    }

    public void setCurrentPlayer(final AbstractGameObject player) {
        Log.info("createNewGameObject", "Set player");
        this.worldController.setPlayer(player);
        Log.info("createNewGameObject", "Player : " + this.worldController.getPlayer().toString());
    }

    /**
     * @param worldController
     */
    public void setWorldController(final AbstractWorldController worldController) {
        this.worldController = worldController;
    }

}
