package solutions.threads;

import java.util.Stack;

public class Monica extends Thread {

    ChatScheduler chatScheduler;
    private Stack<String> messages;

    public Monica(ChatScheduler chatScheduler) {
        this.chatScheduler = chatScheduler;
        messages = new Stack<>();
        messages.push(ChatMessages.MONICA_1);
    }

    @Override
    public void run() {
        while (!messages.empty()) {
            if (chatScheduler.peekCurrentWriter().equals(CurrentWriters.Monica)) {
                System.out.println(Thread.currentThread().getName() + ": " + messages.pop());
                chatScheduler.popCurrentWriter();
            }
        }
    }
}
