package solutions.inmemorycache;

import java.util.List;

public class Main1 {
    public static void main(String[] args) {

        RecordsCache recordsCache = RecordsCache.getInstance();//создаём точку доступа до кеша

        //создаем несколько записей для демонстрации функционала кеша
        Record record1 = new Record(1345345, "Hang Bang Stan", 212.3);
        Record record2 = new Record(1345345, "Mang Lois Dois", 125.2);
        Record record3 = new Record(1345347, "Hang Bang Stan", 212.3);
        Record record4 = new Record(1345348, "Hang Bang Stan", 125.2);
        Record record5 = new Record(1345349, "Hang Li Van", 125.2);
        Record record6 = new Record(1345350, "", 0.02);
        Record record7 = new Record(1345351, "", 0.05);
        //демонстрация:
        System.out.println("Добавляем в кеш запись с account 1345345");
        recordsCache.addRecord(record1);
        System.out.println("Получаем запись с account 1345345:");
        System.out.println(recordsCache.getRecord(1345345));
        System.out.println("Добавляем в кеш запись с таким-же account 1345345");
        recordsCache.addRecord(record2);
        System.out.println("Получаем запись с account 1345345:");
        System.out.println(recordsCache.getRecord(1345345));
        System.out.println("Видим, что вторая запись с уже добавленным ранее account не добавился");

        System.out.println("\n\n");
        System.out.println("Добавляем в кеш запись с account 1345347, в котором name Hang Bang Stan, как в record1");
        recordsCache.addRecord(record3);
        System.out.println("Получаем лист записей с name Hang Bang Stan:");
        System.out.println(recordsCache.getRecords("Hang Bang Stan"));
        System.out.println("Получаем первую запись из имеющихся с name Hang Bang Stan:");
        System.out.println(recordsCache.getRecord("Hang Bang Stan"));

        System.out.println("\n\n");
        System.out.println("Добавляем в кеш запись с value 125.2");
        recordsCache.addRecord(record4);
        System.out.println("Добавляем в кеш запись еще одну запись с value 125.2");
        recordsCache.addRecord(record5);
        System.out.println("Получаем лист записей с value 125.2:");
        System.out.println(recordsCache.getRecords(125.2));
        System.out.println("Получаем первую запись из имеющихся с value 125.2:");
        System.out.println(recordsCache.getRecord(125.2));

        System.out.println("\n\n");
        System.out.println("Добавляем в кеш записи с пустым name");
        recordsCache.addRecord(record6);
        recordsCache.addRecord(record7);
        System.out.println("Получаем лист записей с пустым name:");
        System.out.println(recordsCache.getRecords(""));
        System.out.println("Получаем первую запись из имеющихся с пустым name:");
        System.out.println(recordsCache.getRecord(""));

        System.out.println("\n\n");
        System.out.println("Изменим пару имеющихся записей. Для этого получим две записи по name из кеша:");
        System.out.println("Записи с пустым name и account 1345350 и 1345351 до изменения");
        System.out.println(recordsCache.getRecords(""));
        System.out.println("Получаем лист записей с пустым name и берем оттуда две записи.");

        List<Record> recordsUpdateTest = recordsCache.getRecords("");
        Record record6_1 = recordsUpdateTest.get(0);
        Record record7_1 = recordsUpdateTest.get(1);

        System.out.println("Создаем измененные записи, меняя, например, name");
        Record record6_2 = new Record(record6_1.getAccount(), "San Bi Ju", record6_1.getValue());
        Record record7_2 = new Record(record7_1.getAccount(), "Nan Li Ko", record7_1.getValue());

        System.out.println("Вносим изменения в кеш. Если результат внесения изменения true, то изменения прошло успешно.");
        System.out.println(recordsCache.updateRecord(record6_1, record6_2));
        System.out.println(recordsCache.updateRecord(record7_1, record7_2));

        System.out.println("Получаем записи с пустым name:");
        System.out.println(recordsCache.getRecords(""));
        System.out.println("Получаем запись с измененным name San Bi Ju:");
        System.out.println(recordsCache.getRecord("San Bi Ju"));
        System.out.println("Получаем запись с измененным name Nan Li Ko:");
        System.out.println(recordsCache.getRecord("Nan Li Ko"));

        System.out.println("Попробуем изменить запись в кеше, передав update-методу идентичную изменяемой запись:");
        Record record6_3 = new Record(1345350, "San Bi Ju", 0.02);
        System.out.println(recordsCache.updateRecord(recordsCache.getRecord(1345350), record6_3));
        System.out.println("Метод вернул false, следовательно, изменения не зафиксированы.");


        System.out.println("\n\n");
        System.out.println("Удалим пару имеющихся записей. Для этого получим две записи из кеша,например, по value 212.3:");
        System.out.println("Получаем записи с value 212.3");
        System.out.println(recordsCache.getRecords(212.3));
        System.out.println("Удалим эту запись из кеша. Если метод вернул true, значит запись успешно удалена.");

        List<Record> recordsForDelete = recordsCache.getRecords(212.3);//здесь может вернуться пустой лист, если передан несуществующий value.
        Record recordForDelete1;
        Record recordForDelete2;
        //поэтому нужно проверять лист на наличие объектов в нем или использовать try catch
        try{
            recordForDelete1 = recordsForDelete.get(0);
        }catch (Exception e){
            System.out.println("Лист, из которого Вы пытаетесь получить значение, пустой.");
            recordForDelete1 = null;
        }

        try{
            recordForDelete2 = recordsForDelete.get(1);
        }catch (Exception e){
            System.out.println("Лист, из которого Вы пытаетесь получить значение, пустой.");
            recordForDelete2 = null;
        }

        System.out.println(recordsCache.deleteRecord(recordForDelete1));
        System.out.println(recordsCache.deleteRecord(recordForDelete2));
        System.out.println("Получаем записи с value 212.3");
        System.out.println(recordsCache.getRecords(212.3));

        System.out.println("Попробуем удалить несуществующие записи c value 999.9 и name Dora");

        Record wrongRecordsForDelete1 = recordsCache.getRecord(999.9);
        Record wrongRecordsForDelete2 = recordsCache.getRecord("Dora");

        System.out.println(recordsCache.deleteRecord(wrongRecordsForDelete1));
        System.out.println(recordsCache.deleteRecord(wrongRecordsForDelete2));
        System.out.println("Если метод вернул false, значит удаление не совершилось.");

    }
}
