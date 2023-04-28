package solutions.threads;

public class Main {

    public static void main(String[] args){

        ChatScheduler chatScheduler = new ChatScheduler();

        Chandler chandler = new Chandler(chatScheduler);
        chandler.setName("Chandler");

        Joey joey = new Joey(chatScheduler);
        joey.setName("Joey");

        Monica monica = new Monica(chatScheduler);
        monica.setName("Monica");

        Phoebe phoebe = new Phoebe(chatScheduler);
        phoebe.setName("Phoebe");

        Rachel rachel = new Rachel(chatScheduler);
        rachel.setName("Rachel");

        Ross ross = new Ross(chatScheduler);
        ross.setName("Ross");

        joey.start();
        chandler.start();
        phoebe.start();
        monica.start();
        rachel.start();
        ross.start();
    }
}
