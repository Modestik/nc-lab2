package nc.lab.lab3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Operator extends Thread {
    private List<Client> clients = Collections.synchronizedList(new ArrayList<Client>());

    public void addClient(Client client) {
        synchronized (clients) {
            clients.add(client);
            clients.notify();
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (clients) {
                //если клиентов нет, ждем пока появятся
                if (clients.size() == 0) {
                    try {
                        clients.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    //берем 1 клиента в очереди
                    Client client = clients.remove(0);
                    //если клиент хочет положить
                    if (client.isOperation()){
                        Bank.plusCash(client.getSum());
                    }
                    //если хочет снять
                    else {

                        if (Bank.minusCash(client.getSum()))
                        {
                            //если банк позволяет снять
                        }
                        else{
                            //если у банка меньше денег
                            //отправляем клиента в конец очереди(?)
                            clients.add(client);
                        }
                    }
                }
            }
        }
    }
}
