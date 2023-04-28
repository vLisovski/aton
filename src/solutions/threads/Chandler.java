package solutions.threads;

import java.util.Stack;

public class Chandler extends Thread {

    private final ChatScheduler chatScheduler;
    private Stack<String> messages;

    public Chandler(ChatScheduler chatScheduler) {
        this.chatScheduler = chatScheduler;
        messages = new Stack<>();
        messages.push(ChatMessages.CHANDLER_5);
        messages.push(ChatMessages.CHANDLER_4);
        messages.push(ChatMessages.CHANDLER_3);
        messages.push(ChatMessages.CHANDLER_2);
        messages.push(ChatMessages.CHANDLER_1);
    }

    @Override
    public void run() {
        while (!messages.empty()) {
            if (chatScheduler.peekCurrentWriter().equals(CurrentWriters.Chandler)) {
                System.out.println(Thread.currentThread().getName() + ": " + messages.pop());
                chatScheduler.popCurrentWriter();
            }
        }
    }
}
