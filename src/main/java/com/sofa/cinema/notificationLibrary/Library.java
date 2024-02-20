package com.sofa.cinema.notificationLibrary;

import java.util.logging.Logger;

public class Library {

    private final Logger logger;

    public Library(Logger logger) {
        this.logger = logger;
    }

    public void sendNotification(String medium) {
        //Implement logic to send notification to with the correct media
        logger.info("Notification will be sent with medium: " + medium);
    }
}
