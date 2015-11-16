/**
 *
 */
package com.alban42.yazag.game.objects.pixmap;

import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.alban42.yazag.utils.asset.Asset;
import com.alban42.yazag.utils.objects.AbstractGameObjectFactory;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Alban
 */
public class PixmapObjectFactory extends AbstractGameObjectFactory<PixmapObject> {

    public static final PixmapObjectFactory INSTANCE = new PixmapObjectFactory();

    private PixmapObjectFactory() {
        super();
    }

    @Override
    public PixmapObject createNewObject(final WorldUpdateObject worldUpdateObject) {
        return new PixmapObject(worldUpdateObject, new TextureRegion(Asset.pixmapTexture));
    }
}
