package solutions.threads;

public class Main {

    public static void main(String[] args){
        //6 потоков разыгрывают сценку из ситкома.
        //Каждый поток хранит в себе стек реплик, которые ему нужно написать, чтобы отыграть сценку.
        //Очередность написания реплик храниться в классе ChatScheduler в виде стека. В каждом потоке хранится ссылка на объект этого класса.
        //После запуска каждый поток начинает проверять, кому в данный момент нужно печатать реплику в консоль.
        //Тот поток, чья очередь печатать реплику, печатает её, удаляя из своего стека, а затем сменяет очередь в ChatScheduler на следующего путем удаления из стека своей очередности.
        //Таким образом, стек в ChatScheduler в любой момент времени может изменяться только 1 потоком, и печать реплик происходит в нужном порядке каждым отдельным потоком.
        //После того как поток напечатал все свои реплики из стека, он умирает. Если стек реплик изначально пустой, то поток умирает сразу после запуска, ничего не печатая.
        ChatScheduler chatScheduler = new ChatScheduler();

        Chandler chandler = new Chandler(chatScheduler);
        chandler.setName("Chandler");//присвоение имени потоку, так как каждый поток печатает своё имя, а затем реплику

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
