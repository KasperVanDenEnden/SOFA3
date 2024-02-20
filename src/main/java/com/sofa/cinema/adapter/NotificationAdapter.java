package com.sofa.cinema.adapter;

import com.sofa.cinema.Medium;
import com.sofa.cinema.notificationLibrary.Library;

public class NotificationAdapter implements INotification {
    private Library library;

    public NotificationAdapter(Library library) {
        this.library = library;
    }

    @Override
    public void sendMessage(Medium medium) {
        this.library.sendNotification(medium.toString());
    }
}
