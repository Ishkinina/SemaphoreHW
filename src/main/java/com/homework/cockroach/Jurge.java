package com.homework.cockroach;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Created by eishkinina on 17.03.17.
 */
public class Jurge {
    private final int number;
    private final int distance;
    public Jurge(int number, int distance) {

    this.number = number;
        this.distance = distance;
}



    public void startCompetition() {
        CountDownLatch latch = new CountDownLatch(number+1); // кол-во тараканов +1 на отмашку судье

        ExecutorService runner = Executors.newFixedThreadPool(number); // создаем исполнителя, который выполняет задачи; тредам распределяет задачи;
        List<Future<String>> results = new ArrayList<Future<String>>(); // стринг - тип результата

        for( int i = number; i>0; i --){
             Cockroach cockroach = new Cockroach("Cockroach"+ i, distance, latch);

            Future<String> result = runner.submit(cockroach);  //передаем задачу исполнителю и получаем квиток
             results.add(result); // это добавление фьючера, по которому получу результат для каждого таракана

         }
        while(latch.getCount()!=1){
        } // ждет пока все тараканы дойдут до старта
        latch.countDown();
        for (Future<String> result : results) {
            try {
                System.out.println(result.get()); // гет вернет результат метода колл у таракана

            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }
}
