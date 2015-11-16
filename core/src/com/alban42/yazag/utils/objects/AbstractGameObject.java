/**
 *
 */
package com.alban42.yazag.utils.objects;

import com.alban42.yazag.common.utils.objects.world.WorldUpdateObject;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

/**
 * @author Alban
 */
public abstract class AbstractGameObject {

    private final String uniqueID;
    /**
     * The dimensions of the object.
     */
    public Vector2 dimension;
    /**
     * The origin of the object. By default, the origin is at the center of the box :
     * <code>new Vector2(this.dimension.x / 2, this.dimension.y / 2)</code>;
     */
    public Vector2 origin;
    /**
     * The current position of the object.
     */
    public Vector2 position;
    /**
     * The current rotation of the object.
     */
    public float rotation;
    /**
     * The scaling factor of the object in the world.
     */
    public Vector2 scale;
    /**
     * The texture region of the object.
     */
    public TextureRegion textureRegion;

    /**
     * @param objectID
     */
    public AbstractGameObject(final WorldUpdateObject worldUpdateObject, final TextureRegion textureRegion) {
        super();
        this.uniqueID = worldUpdateObject.objectID;
        update(worldUpdateObject);
        this.textureRegion = textureRegion;
    }

    /**
     * @return the uniqueID
     */
    public String getUniqueID() {
        return this.uniqueID;
    }

    public void render(final SpriteBatch batch) {
        batch.draw(this.textureRegion.getTexture(), this.position.x, this.position.y, this.origin.x, this.origin.y,
                   this.dimension.x, this.dimension.y, this.scale.x, this.scale.y, this.rotation,
                   this.textureRegion.getRegionX(), this.textureRegion.getRegionY(),
                   this.textureRegion.getRegionWidth(), this.textureRegion.getRegionHeight(), false, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "AbstractGameObject [dimension=" + this.dimension + ", origin=" + this.origin + ", position=" + this.position + ", rotation=" + this.rotation + ", scale=" + this.scale + ", textureRegion=" + this.textureRegion + ", uniqueID=" + this.uniqueID + "]";
    }

    /**
     * @param gameObject
     */
    public void update(final WorldUpdateObject gameObject) {
        this.dimension = gameObject.dimension;
        this.origin = gameObject.origin;
        this.position = gameObject.position;
        this.rotation = gameObject.rotation;
        this.scale = gameObject.scale;
    }

}
