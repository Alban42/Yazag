/**
 *
 */
package com.alban42.yazag.common.utils.logger;

import com.esotericsoftware.minlog.Log.Logger;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Alban
 */
public class Alban42Logger extends Logger {

    private final BufferedWriter bufferedWriter;

    public Alban42Logger() {
        super();
        final DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        final Date date = new Date();
        final String currentDate = dateFormat.format(date);

        final File file = new File("log_" + currentDate + ".log");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }

        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (final IOException e) {
            e.printStackTrace();
        }
        this.bufferedWriter = new BufferedWriter(fw);
    }

    @Override
    protected void print(final String arg0) {
        System.out.println(arg0);
        try {
            this.bufferedWriter.append(arg0 + "\n");
            this.bufferedWriter.flush();
        } catch (final IOException e) {
            e.printStackTrace();
        }

    }
}
