package solutions.threads;

import java.util.Stack;

public class Rachel extends Thread {

    private final ChatScheduler chatScheduler;
    private Stack<String> messages;

    public Rachel(ChatScheduler chatScheduler) {
        this.chatScheduler = chatScheduler;
        messages = new Stack<>();
    }

    @Override
    public void run() {
        while (!messages.empty()) {
            if (chatScheduler.peekCurrentWriter().equals(CurrentWriters.Rachel)) {
                System.out.println(Thread.currentThread().getName() + ": " + messages.pop());
                chatScheduler.popCurrentWriter();
            }
        }
    }
}
