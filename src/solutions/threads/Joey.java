package solutions.threads;

import java.util.Stack;

public class Joey extends Thread {

    private final ChatScheduler chatScheduler;
    private Stack<String> messages;

    public Joey(ChatScheduler chatScheduler) {
        this.chatScheduler = chatScheduler;
        messages = new Stack<>();
        messages.push(ChatMessages.JOEY_5);
        messages.push(ChatMessages.JOEY_4);
        messages.push(ChatMessages.JOEY_3);
        messages.push(ChatMessages.JOEY_2);
        messages.push(ChatMessages.JOEY_1);
    }

    @Override
    public void run() {
        while (!messages.empty()) {
            if (chatScheduler.peekCurrentWriter().equals(CurrentWriters.Joey)) {
                System.out.println(Thread.currentThread().getName() + ": " + messages.pop());
                chatScheduler.popCurrentWriter();
            }
        }
    }
}
