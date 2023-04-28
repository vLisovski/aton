package solutions.inmemorycache;

public class Record {

    private final long account;//(!) Предполагаю, что поле account уникально для каждой записи (!)
    private final String name;//(!) Предполагаю, что поле name не уникально для каждой записи (!)
    private final double value;//(!) Предполагаю, что поле value не уникально для каждой записи (!)

    public Record(long account, String name, double value) {
        this.account = account;
        this.name = name;
        this.value = value;
    }

    public long getAccount() {
        return account;
    }

    public String getName() {
        return name;
    }

    public double getValue() {
        return value;
    }

}
