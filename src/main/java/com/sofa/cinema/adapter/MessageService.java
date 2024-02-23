package com.sofa.cinema.adapter;

import com.sofa.cinema.template.StateMessage;

public class MessageService {

    private INotification notificationAdapter;

    public MessageService(INotification notificationAdapter) {
        this.notificationAdapter = notificationAdapter;
    }

    public void sendMessage(StateMessage message) {
        notificationAdapter.sendMessage(message);
    }
}
