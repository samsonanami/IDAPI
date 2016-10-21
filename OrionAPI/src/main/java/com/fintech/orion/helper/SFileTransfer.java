package com.fintech.orion.helper;

import com.fintech.orion.model.Configuration;
import org.slf4j.Logger;
import com.jcraft.jsch.*;
import org.slf4j.LoggerFactory;

import java.io.*;

public class SFileTransfer {

    private static final Logger LOGGER = LoggerFactory.getLogger(SFileTransfer.class);

    private static final String TAG = "SFileTransfer: ";

    private Configuration configuration;

    public SFileTransfer(Configuration configuration) {
        this.configuration = configuration;
    }

    public boolean transferFile(String filePath, String sftpWorkingDir) {

        Session session = null;
        Channel channel = null;
        ChannelSftp channelSftp = null;
        File file = new File(filePath);
        try {
            InputStream fileInputStream = new FileInputStream(file.getPath());
            JSch jsch = new JSch();

            session = jsch.getSession(configuration.getSftpUser(), configuration.getSftpHost(), configuration.getSftpPort());
            session.setPassword(configuration.getSftpPass());
            java.util.Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            session.setConfig(config);
            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            channelSftp = (ChannelSftp) channel;
            channelSftp.cd(sftpWorkingDir);
            SftpProgressMonitor sftpMonitor = new FileProgressMonitor();

            channelSftp.put(filePath, file.getName(), sftpMonitor, ChannelSftp.OVERWRITE);
            fileInputStream.close();
            channelSftp.exit();
            channel.disconnect();
            session.disconnect();
            LOGGER.info(TAG + "File Transfer Complete : " + filePath);
            LOGGER.info(TAG + "File Transferred to : " + configuration.getSftpHost());
            return true;

        } catch (IOException | JSchException | SftpException e) {
            LOGGER.error(TAG, e);
            LOGGER.info(TAG + e.getMessage());
            return false;
        }
    }
}
