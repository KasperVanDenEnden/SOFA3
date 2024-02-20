package com.sofa.cinema.adapter;

import com.sofa.cinema.notificationLibrary.ILibrary;

public class NotificationAdapter implements INotification {
    private ILibrary ILibrary;

    public NotificationAdapter(ILibrary ILibrary) {
        this.ILibrary = ILibrary;
    }

    @Override
    public void sendMessage() {
        this.ILibrary.sendNotification();
    }
}
