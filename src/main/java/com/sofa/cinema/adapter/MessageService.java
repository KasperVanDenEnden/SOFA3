package com.sofa.cinema.adapter;

public class MessageService {

    private INotification notificationAdapter;

    public MessageService(INotification notificationAdapter) {
        this.notificationAdapter = notificationAdapter;
    }

    public void sendMessage() {
        notificationAdapter.sendMessage();
    }
}
