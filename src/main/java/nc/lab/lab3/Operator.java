package nc.lab.lab3;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operator extends Thread {
    private static final Logger log = Logger.getLogger(Operator.class);

    private List<Client> clients = Collections.synchronizedList(new ArrayList<Client>());

    private String name;
    private static int i = 1;

    public Operator() {
        name = "Operator №" + i;
        i++;
    }

    public int getCountQueue() {
        return clients.size();
    }

    public void addClient(Client client) {
        synchronized (clients) {
            clients.add(client);
            clients.notify();
            log.info("Клиент №" + client.getNumber() + " встал в очередь к " + name + ". Текущая очередь равна " + getCountQueue());
            System.out.println("Клиент №" + client.getNumber() + " встал в очередь к " + name + ". Текущая очередь равна " + getCountQueue());
        }
    }

    @Override
    public void run() {
        while (true) {

            //если клиентов нет, ждем пока появятся
            if (clients.size() == 0) {
                synchronized (clients) {
                    try {
                        log.info("У " + name + " нет клиентов, он ожидает ");
                        clients.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                //берем 1 клиента в очереди
                Client client = clients.remove(0);
                //если клиент хочет положить
                if (client.isOperation()) {
                    Bank.plusCash(client.getSum());
                    log.info("Клиент №" + client.getNumber() + " положил " + client.getSum() + " в кассу. Текущий баланс кассы: " + Bank.getCash());
                    System.out.println("Клиент №" + client.getNumber() + " положил " + client.getSum() + " в кассу. Текущий баланс кассы: " + Bank.getCash());
                }
                //если хочет снять
                else {
                    if (Bank.minusCash(client.getSum())) {
                        //если банк позволяет снять
                        log.info("Клиент №" + client.getNumber() + " взял " + client.getSum() + " из кассы. Текущий баланс кассы: " + Bank.getCash());
                        System.out.println("Клиент №" + client.getNumber() + " взял " + client.getSum() + " из кассы. Текущий баланс кассы: " + Bank.getCash());
                    } else {
                        //если у банка меньше денег
                        //отправляем клиента в конец очереди(?)
                        log.info("Клиент №" + client.getNumber() + " не смог взять деньги из кассы. Текущий баланс кассы: " + Bank.getCash());
                        System.out.println("Клиент №" + client.getNumber() + " не смог взять деньги из кассы. Текущий баланс кассы: " + Bank.getCash());
                        clients.add(client);
                    }
                }
                //Уснуть на время выполнении операции
                try {
                    Thread.sleep(client.getTime());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.info("Клиент №" + client.getNumber() + " обслужан " + name);
                System.out.println("Клиент №" + client.getNumber() + " обслужан " + name);
            }
        }
    }
}

