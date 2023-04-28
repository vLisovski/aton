package solutions.inmemorycache;

import java.util.*;

public class RecordsCache {
    //TODO описать выбор Map
    private int currentMaxRecordsIndex;//Хранит позицию последней добавленной в конец листа записи
    /*
    Для хранения записей выбран ArrayList. Сложность get() по индексу в ArrayList(как массива) - O(1).
    Синглтон. На всю программу можно создать только один лист с записями.
     */
    private List<Record> records;//Лист записей

    private Map<Long, Integer> longListMap;//Мапа с индексами для поиска по полю account.(!) Предполагаю, что поле account уникально для каждой записи (!).
    private Map<String, List<Integer>> stringListMap;//Мапа с листами индексов для поиска по полю name.(!) Предполагаю, что поле name не уникально для каждой записи (!).
    private Map<Double, List<Integer>> doubleListMap;//Мапа с листами индексов для поиска по полю value.(!) Предполагаю, что поле value не уникально для каждой записи (!).

    private RecordsCache() {

        currentMaxRecordsIndex = 0;
        records = new ArrayList<>();
        longListMap = new HashMap<>();
        stringListMap = new HashMap<>();
        doubleListMap = new HashMap<>();

    }

    private RecordsCache instance;

    /**
     * Возвращает объект класса RecordsCache.
     **/
    public RecordsCache getInstance() {//гарантия создания одного кеша на всю программу
        if (instance == null) {
            instance = new RecordsCache();
        }
        return instance;
    }

    /**
     * Добавляет запись, если такой ранее не добавлялось
     * record - запись, которую нужно добавить
     * Возвращает true, если запись была добавлена
     * Возвращает false, если запись не была добавлена
     **/
    public boolean addRecord(Record record) {

        if(longListMap.get(record.getAccount())!=null){//возвращаем false, если такой элемент уже был добавлен ранее(не может быть несколько записей с одинаковым account)
            return false;
        }

        int index;

        if (!records.isEmpty() && records.contains(null)) {//заполняем места, из которых были удалены записи
            index = records.indexOf(null);
        } else {
            index = currentMaxRecordsIndex;//если пробелов(null) в листе нет, добавляем в конец
        }

        records.add(index, record);

        longListMap.put(record.getAccount(), index);

        if(stringListMap.containsKey(record.getName())){
            stringListMap.get(record.getName()).add(index);
        }else {
            ArrayList<Integer> newIndexList = new ArrayList<>();
            newIndexList.add(index);
            stringListMap.put(record.getName(), newIndexList);
        }

        if(doubleListMap.containsKey(record.getValue())){
            doubleListMap.get(record.getValue()).add(index);
        }else {
            ArrayList<Integer> newIndexList = new ArrayList<>();
            newIndexList.add(index);
            doubleListMap.put(record.getValue(), newIndexList);
        }

        if (index == currentMaxRecordsIndex) {//если элемент добавился в конец листа, увеличиваем текущий максимальный индекс листа
            currentMaxRecordsIndex++;
        }

        return true;

    }

    /**
     * Возвращает запись, в которой поле account совпадает с переданным значением, либо null.
     * Сложность получения записи по account - O(1)
     **/
    public Record getRecord(long account) {
        try {
            return records.get(longListMap.get(account));//O(1)+O(1) = O(1)
        }catch (Exception e){
            System.out.println("Account "+account+" не найден. Возвращён null");
            return null;
        }
        //общая сложность алгоритма получения записи по account: O(1)
    }

    /**
     * Возвращает первую запись, в которой поле name совпадает с переданным значением, либо null.
     * Сложность получения записи по name - O(1)
     **/
    public Record getRecord(String name) {
        try {
            return records.get(stringListMap.get(name).get(0));//O(1)+O(1)+O(1) = O(1)
        }catch (Exception e){
            System.out.println("Name "+name+" не найдено. Возвращён null");
            return null;
        }
        //общая сложность алгоритма получения записи по name: O(1)
    }

    /**
     * Возвращает первую запись, в которой поле value совпадает с переданным значением, либо null.
     * Сложность получения записи по value - O(1)
     **/
    public Record getRecord(double value) {
        try{
            return records.get(doubleListMap.get(value).get(0));//O(1)+O(1)+O(1) = O(1)
        }catch (Exception e){
            System.out.println("Value "+value+" не найдено. Возвращён null");
            return null;
        }
        //общая сложность алгоритма получения записи по value: O(1)
    }

    /**
     * Возвращает List всех записей, в которых поле name совпадает с переданной строкой, либо null.
     * Сложность получения листа с записями по name - O(N), где N - число записей с таким name
     **/
    public List<Record> getRecords(String name) {

        List<Record> recordsList = new ArrayList<>();
        List<Integer> indexes;

        try{
            indexes = stringListMap.get(name); //O(1)
        }catch (Exception e){
            System.out.println("Name "+name+" не найдено. Возвращён null");
            return null;
        }

        for (int i : indexes) {//O(N), где N - число записей с таким name
            recordsList.add(records.get(i));//O(1)+O(1) = O(1)
        }
        //общая сложность алгоритма получения листа записей по name: O(1)+O(1)*O(N) = O(N)
        //основная сложность алгоритма - собрать в цикле лист с записями
        return recordsList;

    }

    /**
     * Возвращает List всех записей, в которых поле value совпадает с переданным значением, либо null.
     * Сложность получения листа с записями по value - O(N), где N - число записей с таким value
     **/
    public List<Record> getRecords(double value) {

        List<Record> recordsList = new ArrayList<>();
        List<Integer> indexes;

        try {
            indexes = doubleListMap.get(value);//O(1)
        }catch (Exception e){
            System.out.println("Value "+value+" не найдено. Возвращён null");
            return null;
        }

        for (int i : indexes) {//O(N), где N - число записей с таким value
            recordsList.add(records.get(i));//O(1)+O(1) = O(1)
        }
        //общая сложность алгоритма получения листа записей по value: O(1)+O(1)*O(N) = O(N)
        //основная сложность алгоритма - собрать в цикле лист с записями
        return recordsList;

    }

    /**
     * Заменяет старую запись, если такая есть, отличной от неё новой.
     * oldRecord - запись, поля которой нужно отредактировать
     * newRecord - запись с отредактированными полями
     * Возвращает true, если редактирование записи прошло успешно.
     * Возвращает false, если редактирование записи не удалось.
     **/
    public boolean updateRecord(Record oldRecord, Record newRecord) {

        if (longListMap.get(oldRecord.getAccount()) == null ||
                oldRecord.equals(newRecord)) {//если переданная старая запись не находится в кеше или она идентична новой, то возвращаем false, потому что менять нечего
            return false;
        }

        int index = longListMap.get(oldRecord.getAccount()); //находим на каком индексе находится запись в листе по аккаунту

        records.remove(index);//удаляем старую запись из листа записей

        if (oldRecord.getAccount() != newRecord.getAccount()) {//если в новой записи изменяется account
            longListMap.remove(oldRecord.getAccount());//удаляем пару <account,List<Integer>> со старым аккаунтом
            longListMap.put(newRecord.getAccount(), index);//создаем пару <account,List<Integer>> с новым аккаунтом
        }

        if (!oldRecord.getName().equals(newRecord.getName())) {//если в новой записи изменяется name
            stringListMap.get(oldRecord.getName()).remove(index);//удаляем index из List<Integer>, принадлежащего name старой записи

            if (stringListMap.containsKey(newRecord.getName())) {
                stringListMap.get(newRecord.getName()).add(index);//если есть пара <name,List<Integer>> с name новой записи, тогда добавляем List<Integer> этого name index
            } else {
                List<Integer> newIndexList = new ArrayList<>();//иначе создаём пару <name,List<Integer>> для нового name и кладём в List<Integer> index
                newIndexList.add(index);
                stringListMap.put(newRecord.getName(), newIndexList);
            }
        }

        if (oldRecord.getValue() != newRecord.getValue()) {//если в новой записи изменяется value
            doubleListMap.get(oldRecord.getValue()).remove(index);//удаляем index из List<Integer>, принадлежащего value старой записи

            if (doubleListMap.containsKey(newRecord.getValue())) {
                doubleListMap.get(newRecord.getValue()).add(index);//если есть пара <value,List<Integer>> с value новой записи, тогда добавляем в List<Integer> этого value index
            } else {
                List<Integer> newIndexList = new ArrayList<>();//иначе создаём пару <value,List<Integer>> для нового value
                newIndexList.add(index);
                doubleListMap.put(newRecord.getValue(), newIndexList);
            }
        }

        records.add(index, newRecord);//добавляем новую запись в records на позицию index

        return true;
    }

    /**
     * Удаляет запись, если такая есть.
     * record - запись, которую нужно удалить.
     * Возвращает true, если удаление записи прошло успешно.
     * Возвращает false, если запись не получилось удалить.
     **/
    public boolean deleteRecord(Record record) {

        if (!longListMap.containsKey(record.getAccount())) {//возвращаем false, если за такой записью не закреплено индекса по аккаунту(такой записи нет в кеше)
            return false;
        }

        int index = longListMap.get(record.getAccount());
        //удаляем переданную запись из кеша вместе с указывающими на неё индексами
        records.remove(index);
        records.add(index, null);//заменяем удалённый элемент null-ом, чтобы не сбивать позиции следующих за ним элементов.
        longListMap.remove(record.getAccount());//удаляем пару <account,List<Integer>>
        stringListMap.get(record.getName()).remove(index);//удаляем index из List<Integer> пары <name,List<Integer>>
        doubleListMap.get(record.getValue()).remove(index);//удаляем index из List<Integer> пары <value,List<Integer>>

        //удаляем из мап пары, в которых теперь содержатся пустые листы с индексами
        if (stringListMap.get(record.getName()).isEmpty()) {
            stringListMap.remove(record.getName());//если после удаления переданной записи её полю name не принадлежит ни один индекс, пара <name, List<Integer> для данного name удаляется из мапы>
        }

        if (doubleListMap.get(record.getValue()).isEmpty()) {
            doubleListMap.remove(record.getValue());//если после удаления переданной записи её полю value не принадлежит ни один индекс, пара <value, List<Integer> для данного value удаляется из мапы>
        }

        return true;
    }
}
