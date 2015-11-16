/**
 *
 */
package com.alban42.yazag.utils.screens;

import com.alban42.yazag.utils.Alban42Utils;
import com.alban42.yazag.utils.Constants;
import com.alban42.yazag.utils.exceptions.NonExistingFont;
import com.alban42.yazag.utils.ui.Alban42UiFactory;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Alban
 */
public abstract class AbstractMenuScreen extends AbstractScreen {

    private static final int HEADER_FONT_SCALE = 2;
    private static final String UI_SCREEN_PACK = "ui/uiScreen.pack";
    protected Stage stage;
    private Map<String, BitmapFont> fonts;
    private Skin skin;
    private Table table;
    private TextureAtlas textureAtlas;

    public AbstractMenuScreen(final Game game) {
        super(game);
    }

    public void addHeader(final String text, final Color color) throws NonExistingFont {
        addHeader(text, color, -1);
    }

    public void addHeader(final String text, final Color color, final int size) throws NonExistingFont {
        final Actor label = Alban42UiFactory.createLabel(getFont(color, size), color, HEADER_FONT_SCALE, text);
        addToTable(label);
        addRow();
    }

    public void addRow() {
        getTable().row();
    }

    public void addToTable(final Actor actor) {
        getTable().add(actor);
    }

    protected abstract void buildStage();

    private void createFonts() {
        this.fonts = new HashMap<String, BitmapFont>();
    }

    private void createSkin() {
        this.skin = new Skin(this.textureAtlas);
    }

    private void createTable() {
        this.table = new Table(this.skin);
        this.table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    private void createTexture() {
        this.textureAtlas = new TextureAtlas(UI_SCREEN_PACK);
    }

    @Override
    public void dispose() {
        this.stage.dispose();
        disposeExtra();
    }

    /**
     * Called at the end of the {@link #hide()}.</p>
     * Allow you to dispose more elements than the {@link #stage}.
     */
    protected abstract void disposeExtra();

    public BitmapFont getFont(final Color color) throws NonExistingFont {
        return getFont(color, -1);
    }

    public BitmapFont getFont(final Color color, final int size) throws NonExistingFont {
        BitmapFont font = null;
        String sufix = Alban42Utils.getFontName(color);
        if (size != -1) {
            sufix += "_" + size;
        }
        final String fontFilePath = Constants.getFont(sufix);
        final FileHandle fontFileHandle = Gdx.files.internal(fontFilePath);
        if (fontFileHandle.exists()) {
            font = this.fonts.get(fontFilePath);
            if (font == null) {
                font = new BitmapFont(fontFileHandle, false);
                this.fonts.put(fontFilePath, font);
            }
        } else {
            throw new NonExistingFont(color, size);
        }

        return font;
    }

    public Skin getSkin() {
        return this.skin;
    }

    public Table getTable() {
        return this.table;
    }

    @Override
    public void render(final float delta) {
        Gdx.app.log(this.TAG, "Render");
        Alban42Utils.clearScreen();

//    Table.drawDebug(this.stage);

        this.stage.act(delta);
        this.stage.draw();
        renderExtra();
    }

    protected abstract void renderExtra();

    @Override
    public void resize(final int width, final int height) {
        Gdx.app.log(this.TAG, "resize");
        this.stage.getViewport().update(width, height, true);
    }

    @Override
    public void show() {
        Gdx.app.log(this.TAG, "show");
        createTexture();
        createSkin();
        createFonts();
        createTable();
        this.stage = new Stage();
        Gdx.input.setInputProcessor(this.stage);
        buildStage();
        if (Constants.DEBUG) {
            getTable().debug();
        }
        this.stage.addActor(this.table);
    }
}
