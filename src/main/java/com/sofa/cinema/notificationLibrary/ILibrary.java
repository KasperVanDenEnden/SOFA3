package com.sofa.cinema.notificationLibrary;

import com.sofa.cinema.template.StateMessage;

public interface ILibrary {
    void sendNotification(StateMessage message);
}
