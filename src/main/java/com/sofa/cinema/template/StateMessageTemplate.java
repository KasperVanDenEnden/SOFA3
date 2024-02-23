package com.sofa.cinema.template;

public abstract class StateMessageTemplate {
        final void prepareMessage() {
                write();
                send();
        }
        public abstract void write();
        void send(){
            // send message to messageService
        }
}
