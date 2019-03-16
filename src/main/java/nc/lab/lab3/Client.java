package nc.lab.lab3;

import lombok.Data;
import lombok.Getter;

import java.util.Random;

@Data
public class Client {

    //номер клиента
    private int number;
    //сумма денег
    private int sum;
    //true - положить
    //false - снять
    private boolean operation;
    //время обслуживания
    private int time;

    public Client(int number) {
        this.number = number;
        Random random = new Random();
        sum = random.nextInt(100);
        operation = random.nextInt(2) == 1;
        //от 1 до 3 сек
        time = random.nextInt(2000) + 1000;
    }

}
