package com.sofa.cinema.template;

public class StateMessage {
    private String messageString;
    private String attachment;

    public StateMessage(String messageString) {
        this.messageString = messageString;
    }

    public StateMessage(String messageString, String attachment) {
        this.messageString = messageString;
        this.attachment = attachment;
    }

    public String getMessageString() {
        return messageString;
    }

    public String getAttachment() {
        return attachment;
    }
}
