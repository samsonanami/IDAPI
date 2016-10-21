package com.fintech.orion.helper;

import com.jcraft.jsch.SftpProgressMonitor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;

public class FileProgressMonitor implements SftpProgressMonitor {
    ProgressMonitor monitor;
    long count = 0;
    long max = 0;
    private long percent = 1000;

    private final Logger LOGGER = LoggerFactory.getLogger(FileProgressMonitor.class);

    @Override
    public void init(int op, String src, String dest, long max) {
        this.max = max;
        monitor = new ProgressMonitor(null,
                ((op == SftpProgressMonitor.PUT) ?
                        "put" : "get") + ": " + src,
                "", 0, (int) max);
        count = 0;
        percent = -1;
        monitor.setProgress((int) this.count);
        monitor.setMillisToDecideToPopup(1000);
    }

    @Override
    public boolean count(long count) {
        this.count += count;
        percent = this.count * 100 / max;
        LOGGER.info("Completed " + this.count + " byte(s) (" + percent + "%) out of " + max + " byte(s).");
        monitor.setNote("Completed " + this.count + " byte(s) (" + percent + "%) out of " + max + " byte(s).");
        monitor.setProgress((int) this.count);

        return !(monitor.isCanceled());
    }

    @Override
    public void end() {
        monitor.close();
    }
}