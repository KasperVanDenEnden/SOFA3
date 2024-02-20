package com.sofa.cinema.notificationLibrary;

import java.util.logging.Logger;

public class Sms implements ILibrary {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    public void sendNotification() {
        //Implement logic to send notification to with the correct media
        logger.info("SMS notification will be sent.");
    }
}
