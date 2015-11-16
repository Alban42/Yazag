/**
 *
 */
package com.alban42.yazag.game.objects.sprite;

import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.alban42.yazag.utils.objects.AbstractGameObjectFactory;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Alban
 */
public class SpriteObjectFactory extends AbstractGameObjectFactory<SpriteObject> {

    public static final SpriteObjectFactory INSTANCE = new SpriteObjectFactory();
    private Texture texture = null;

    @Override
    public SpriteObject createNewObject(final WorldUpdateObject worldUpdateObject) {
        return new SpriteObject(worldUpdateObject, new TextureRegion(this.texture));
    }

    /**
     * @return the texture
     */
    public Texture getTexture() {
        return this.texture;
    }

    /**
     * @param texture the texture to set
     */
    public void setTexture(final Texture texture) {
        this.texture = texture;
    }
}
