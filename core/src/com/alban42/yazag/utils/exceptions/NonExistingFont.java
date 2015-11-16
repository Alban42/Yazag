package com.alban42.yazag.utils.exceptions;

import com.alban42.yazag.utils.Alban42Utils;
import com.alban42.yazag.utils.Constants;
import com.badlogic.gdx.graphics.Color;

public class NonExistingFont extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -2598095633152522375L;
    private final Color color;
    private final int size;

    public NonExistingFont(final Color color, final int size) {
        this.color = color;
        this.size = size;
    }

    @Override
    public String getMessage() {
        final String fontName = Alban42Utils.getFontName(this.color);
        String message = "The font for the color " + fontName;
        String sufix = fontName;
        if (this.size != -1) {
            message += " and size " + this.size;
            sufix += "_" + this.size;
        }

        final String fontFilePath = Constants.getFont(sufix);
        message += " was not found, please, add the font file for those values in " + fontFilePath;
        return message;
    }
}
