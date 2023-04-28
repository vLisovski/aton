package solutions.threads;

import java.util.Stack;

public class Phoebe extends Thread {

    private final ChatScheduler chatScheduler;
    private Stack<String> messages;

    public Phoebe(ChatScheduler chatScheduler) {
        this.chatScheduler = chatScheduler;
        messages = new Stack<>();
        messages.push(ChatMessages.PHOEBE_3);
        messages.push(ChatMessages.PHOEBE_2);
        messages.push(ChatMessages.PHOEBE_1);
    }

    @Override
    public void run() {
        while (!messages.empty()) {
            if (chatScheduler.peekCurrentWriter().equals(CurrentWriters.Phoebe)) {
                System.out.println(Thread.currentThread().getName() + ": " + messages.pop());
                chatScheduler.popCurrentWriter();
            }
        }
    }
}
