/**
 *
 */
package com.alban42.yazag.game.world.game;

import com.alban42.yazag.common.utils.objects.action.Action;
import com.alban42.yazag.common.utils.objects.action.ActionEnum;
import com.alban42.yazag.utils.world.AbstractWorldController;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

/**
 * @author Alban
 */
public class GameController extends AbstractWorldController {

    @Override
    public boolean keyDown(final int keycode) {
        switch (keycode) {
            case Keys.Z:
                getActions().addAction(new Action(ActionEnum.WALK_N));
                break;
            case Keys.S:
                getActions().addAction(new Action(ActionEnum.WALK_S));
                break;
            case Keys.Q:
                getActions().addAction(new Action(ActionEnum.WALK_W));
                break;
            case Keys.D:
                getActions().addAction(new Action(ActionEnum.WALK_E));
                break;
            case Keys.SHIFT_LEFT:
                getActions().addAction(new Action(ActionEnum.RUN));
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean keyUp(final int keycode) {
        switch (keycode) {
            case Keys.Z:
                getActions().addAction(new Action(ActionEnum.STOP_WALK_N));
                break;
            case Keys.S:
                getActions().addAction(new Action(ActionEnum.STOP_WALK_S));
                break;
            case Keys.Q:
                getActions().addAction(new Action(ActionEnum.STOP_WALK_W));
                break;
            case Keys.D:
                getActions().addAction(new Action(ActionEnum.STOP_WALK_E));
                break;
            case Keys.SHIFT_LEFT:
                getActions().addAction(new Action(ActionEnum.STOP_RUN));
                break;
            default:
                break;
        }
        return true;
    }

    // @Override
    @Override
    public boolean mouseMoved(final int screenX, final int screenY) {
        // Get the position of the mouse on the world.
        final Vector3 worldCoordinates = new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0);
        // Translate this position into the camera coordinates.
        getCameraHelper().unproject(worldCoordinates);
        // Get the angle between the player position and the mouse to be able to rotate the player with this angle.
        final Vector2 mouseLoc = new Vector2(worldCoordinates.x, worldCoordinates.y);
        final Vector2 direction = mouseLoc.sub(getPlayer().position);
        final float mouseAngle = direction.angle();
        final Action rotate = new Action(ActionEnum.ROTATE);
        rotate.setValue(mouseAngle);
        getActions().addAction(rotate);
        return true;
    }

    @Override
    public boolean touchDown(final int screenX, final int screenY, final int pointer, final int button) {
        Gdx.app.log(TAG,
                    "Click screenX=" + screenX + " screenY=" + screenY + " pointer=" + pointer + " button=" + button);
        return true;
    }
}
