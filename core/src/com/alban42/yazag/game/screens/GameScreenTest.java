/**
 *
 */
package com.alban42.yazag.game.screens;

import com.alban42.yazag.game.objects.Humain;
import com.alban42.yazag.utils.Alban42Utils;
import com.alban42.yazag.utils.Assets;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

/**
 * @author Alban
 */
public class GameScreenTest implements Screen {

    private final float timestep = 1 / 60f;
    private SpriteBatch batch;
    private OrthographicCamera camera;
    private Box2DDebugRenderer debugRenderer;
    private Humain humain;
    private World world;

    /**
     * @param game
     */
    public GameScreenTest(final Game game) {

    }

    @Override
    public void dispose() {
        this.world.dispose();
        this.batch.dispose();
        this.debugRenderer.dispose();
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void render(final float delta) {
        Alban42Utils.clearScreen();

        this.world.step(this.timestep, 8, 3);

        this.camera.position.set(this.humain.getPosition().x, this.humain.getPosition().y, 0);
        this.camera.update();

        this.batch.setProjectionMatrix(this.camera.combined);
        this.batch.begin();
        this.batch.draw(Assets.instance.badlogic, -Assets.instance.badlogic.getWidth() / 2,
                        -Assets.instance.badlogic.getHeight() / 2);
        this.batch.end();

        this.debugRenderer.render(this.world, this.camera.combined);
    }

    @Override
    public void resize(final int width, final int height) {
        this.camera.viewportWidth = width / 25;
        this.camera.viewportHeight = height / 25;
    }

    @Override
    public void resume() {
    }

    @Override
    public void show() {
        this.world = new World(new Vector2(), true);
        this.batch = new SpriteBatch();
        this.debugRenderer = new Box2DDebugRenderer();
        this.camera = new OrthographicCamera();

        this.humain = new Humain(this.world, new Vector2());
    }
}
