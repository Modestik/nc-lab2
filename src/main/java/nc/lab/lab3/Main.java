package nc.lab.lab3;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    /**
     * 1. Многопоточное пепемножение матриц
     * 2. Многопоточная сортировка Шелла
     * 3. Система многопоточного обслуживания:
     * • касса(сумма)
     * • операционистки(выполняют операции, имеют очередь)
     * • клиент(сумма денег, положить/снять, время обслуживания)
     * • основной поток случайно генерит клиенты
     * • клиент становится к операционистке с меньшей очереди
     * • обслуживание: a) обслужить б) уснуть на время обслуживания
     * • если в кассе денег нет, выдавать нельзя
     * • выводить логи
     */
    public static void main(String[] args) {
        //Заполняем счет банка
        Bank.setCash(1000);
        //count operators
        int count = 3;

        //Создаем операторов
        final List<Operator> operators = new ArrayList<Operator>();
        for (int i = 0; i < count; i++) {
            operators.add(new Operator());
        }
        //Запускаем их потоки
        for (Operator operator : operators) {
            operator.start();
        }

        //Поток создающий клиентов
        new Thread() {
            @Override
            public void run() {
                int i = 1;
                Random random = new Random();
                while (true) {
                    //Выбираем оператора для добавления в очередь ему клиента
                    operators.get(queueSelection(operators)).addClient(new Client(i));
                    i++;
                    //Засыпаем до создания следующего клиента
                    try {
                        Thread.sleep(random.nextInt(1500));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }

    public static int queueSelection(List<Operator> operators) {
        //кол-во клиентов в очереди всех операторов
        int[] counts = new int[operators.size()];
        for (int i = 0; i < operators.size(); i++) {
            counts[i] = operators.get(i).getCountQueue();
        }
        //нахождения минимального
        int min = counts[0];
        for (int count : counts) {
            if (count < min) {
                min = count;
            }
        }
        //если у операторов одинаковое кол-во в очереде, то выбирается рандомный оператор
        List<Integer> index = new ArrayList<Integer>();
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] == min) {
                index.add(i);
            }
        }
        return index.get(new Random().nextInt(index.size()));
    }
}
