package com.sofa.cinema.adapter;

import com.sofa.cinema.notificationLibrary.ILibrary;
import com.sofa.cinema.template.StateMessage;

public class NotificationAdapter implements INotification {
    private ILibrary ILibrary;

    public NotificationAdapter(ILibrary ILibrary) {
        this.ILibrary = ILibrary;
    }

    @Override
    public void sendMessage(StateMessage message) {
        this.ILibrary.sendNotification(message);
    }
}
