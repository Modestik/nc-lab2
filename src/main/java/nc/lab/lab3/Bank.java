package nc.lab.lab3;

public class Bank {

    private static int cash;

    public static synchronized void plusCash(int plus) {
        cash += plus;
    }

    public static synchronized boolean minusCash(int minus) {
        if (cash > minus) {
            cash -= minus;
            return true;
        } else {
            return false;
        }
    }

    public static synchronized void setCash(int setCash) {
        cash = setCash;
    }

    public static synchronized int getCash() {
        return cash;
    }
}
