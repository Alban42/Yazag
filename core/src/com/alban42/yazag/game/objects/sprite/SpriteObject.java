/**
 *
 */
package com.alban42.yazag.game.objects.sprite;

import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.alban42.yazag.utils.objects.AbstractGameObject;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Alban
 */
public class SpriteObject extends AbstractGameObject {

    /**
     * @param position
     * @param dimension
     * @param scale
     * @param textureRegion
     */
    public SpriteObject(final WorldUpdateObject worldUpdateObject, final TextureRegion textureRegion) {
        super(worldUpdateObject, textureRegion);
    }

}
