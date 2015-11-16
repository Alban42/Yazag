/**
 *
 */
package com.alban42.yazag.game.objects.pixmap;

import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.alban42.yazag.utils.objects.AbstractGameObject;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * @author Alban
 */
public class PixmapObject extends AbstractGameObject {

    private static final Format FORMAT = Format.RGBA8888;

    /**
     * @param position
     * @param dimension
     * @param scale
     * @param textureRegion
     */
    public PixmapObject(final WorldUpdateObject worldUpdateObject, final TextureRegion textureRegion) {
        super(worldUpdateObject, textureRegion);

    }

    public static Pixmap createPixmap(final int width, final int height, final Color color) {
        final Pixmap pixmap = new Pixmap(width, height, FORMAT);
        // Fill the square with the color at 50% of opacity
        pixmap.setColor(color.r, color.g, color.b, 0.5f);
        pixmap.fill();
        // Draw yellow triangle on the square
        final Color xColor = Color.YELLOW;
        pixmap.setColor(xColor.r, xColor.g, xColor.b, 1);
        pixmap.drawLine(0, 0, width / 2, height);
        pixmap.drawLine(width, 0, width / 2, height);
        return pixmap;
    }

}
