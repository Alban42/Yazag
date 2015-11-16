package com.alban42.yazag.utils.world;

import com.alban42.yazag.utils.Constants;
import com.alban42.yazag.utils.asset.Asset;
import com.alban42.yazag.utils.objects.AbstractGameObject;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class CameraHelper {

    private static final float INIT_ZOOM = 50.0f;

    private static final String TAG = CameraHelper.class.getName();
    private final OrthographicCamera camera;
    private final OrthogonalTiledMapRenderer mapRenderer;
    private final float MAX_ZOOM_IN = 35f;
    private final float MAX_ZOOM_OUT = 100.0f;
    private final Vector2 position;
    private AbstractGameObject target;
    private float zoom;

    public CameraHelper() {
        this.position = new Vector2();
        this.zoom = INIT_ZOOM;
        this.camera = new OrthographicCamera(Constants.VIEWPORT_WIDTH, Constants.VIEWPORT_HEIGHT);
        this.camera.position.set(0, 0, 0);
        this.camera.update();
        // Map
        this.mapRenderer = new OrthogonalTiledMapRenderer(Asset.map);
    }

    public void addZoom(final float amount) {
        setZoom(this.zoom + amount);
    }

    /**
     * @return the camera
     */
    public OrthographicCamera getCamera() {
        return this.camera;
    }

    public Vector2 getPosition() {
        return this.position;
    }

    public AbstractGameObject getTarget() {
        return this.target;
    }

    public void setTarget(final AbstractGameObject target) {
        this.target = target;
    }

    public float getZoom() {
        return this.zoom;
    }

    public void setZoom(final float zoom) {
        this.zoom = MathUtils.clamp(zoom, this.MAX_ZOOM_IN, this.MAX_ZOOM_OUT);
    }

    public void render() {
        this.camera.position.x = this.position.x;
        this.camera.position.y = this.position.y;
        this.camera.zoom = this.zoom;
        this.camera.update();
        // Map render
        this.mapRenderer.setView(this.camera);
        this.mapRenderer.render();
    }

    public void resize(final int width, final int height) {
        this.camera.viewportWidth = width;
        this.camera.viewportHeight = height;
        this.camera.update();
    }

    public void setPosition(final float x, final float y) {
        this.position.set(x, y);
    }

    public void unproject(final Vector3 vector) {
        if (this.camera != null) {
            this.camera.unproject(vector);
        }
    }

    public void update(final float deltaTime) {
        this.position.x = this.target.position.x + this.target.origin.x;
        this.position.y = this.target.position.y + this.target.origin.y;
    }
}
