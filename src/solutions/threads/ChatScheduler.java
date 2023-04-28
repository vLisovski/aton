package solutions.threads;

import java.util.Stack;

public class ChatScheduler {

    private static Stack<CurrentWriters> messagesOrder;

    public ChatScheduler(){
        messagesOrder = new Stack<>();
        messagesOrder.push(CurrentWriters.Joey);
        messagesOrder.push(CurrentWriters.Chandler);
        messagesOrder.push(CurrentWriters.Joey);
        messagesOrder.push(CurrentWriters.Chandler);
        messagesOrder.push(CurrentWriters.Phoebe);

        messagesOrder.push(CurrentWriters.Monica);

        messagesOrder.push(CurrentWriters.Joey);
        messagesOrder.push(CurrentWriters.Phoebe);
        messagesOrder.push(CurrentWriters.Chandler);

        messagesOrder.push(CurrentWriters.Joey);
        messagesOrder.push(CurrentWriters.Chandler);
        messagesOrder.push(CurrentWriters.Phoebe);
        messagesOrder.push(CurrentWriters.Chandler);
        messagesOrder.push(CurrentWriters.Joey);
    }

    public CurrentWriters popCurrentWriter(){
       return messagesOrder.pop();
    }

    public CurrentWriters peekCurrentWriter(){
        return messagesOrder.peek();
    }

}
