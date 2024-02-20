package com.sofa.cinema.adapter;

import com.sofa.cinema.Medium;

public class MessageService {
    
    private INotification notificationAdapter;
    
    public MessageService(INotification notificationAdapter) {
        this.notificationAdapter = notificationAdapter;
    }

    public void sendMessage(Medium medium) {
        notificationAdapter.sendMessage(medium);
    }
}
