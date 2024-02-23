package com.sofa.cinema.notificationLibrary;

import com.sofa.cinema.template.StateMessage;

import java.util.logging.Logger;

public class Whatsapp implements ILibrary {

    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public void sendNotification(StateMessage message) {
        //Implement logic to send notification to with the correct media
        logger.info(message.getMessageString());
        logger.info(message.getAttachment());
    }
}
