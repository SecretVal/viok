package com.secretval.event.events;

import com.secretval.event.Event;

public interface ChatEvents {
    public class ChatSendEvent extends Event {
        private String msg;

        public ChatSendEvent(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }

    public class AllowChatEvent extends Event {
        private String msg;

        public AllowChatEvent(String msg) {
            this.msg = msg;
        }

        public String getMsg() {
            return msg;
        }
    }
}
