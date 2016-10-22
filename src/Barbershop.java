/**
 * Created by Juff on 22.10.16.

 * В парикмахерской есть зал для стрижки одного клиента и комната
 * ожидания n клиентов. Клиенты заходят в комнату ожидания по одному, если
 * в ней есть место, либо уходят стричься в другой салон, если все места
 * заняты. После того, как парикмахер заканчивает стрижку, клиент покидает
 * салон и к парикмахеру заходит следующий клиент(если таковой имеется).
 * Клиенты могут по одному заходить в комнату ожидания и также по одному
 * заходить в зал для стрижки (если он свободен), причем эти два события
 * являются взаимоисключающими. Если парикмахер видит, что в зале
 * ожидания никого нет, он пересаживается вздремнуть в этом зале. Новый
 * клиент, обнаружив дремлющего парикмахера, будит его, и тот стрижет
 * клиента; в противном случае пришедший клиент должен будет подождать.
 * Воспользуйтесь многопоточностью, чтобы скоординировать действия
 * парикмахера и клиентов.
 */
public class Barbershop {

    public static WaitingHall waitingHall;
    public static Saloon saloon;
    public static final Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Barbershop().go();

    }

    private void go() throws InterruptedException {
        int n = 1;
        waitingHall = WaitingHall.getWaitingHall(n);
        saloon = Saloon.getSaloon();

        Thread clientGenerator = new Thread(new Runnable() {
            ClientGenerator clientGenerator = new ClientGenerator(waitingHall);
            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    clientGenerator.generate();
                    if (!waitingHall.isEmpty()) {
                        synchronized (monitor) {
                            monitor.notifyAll();
                        }
                    }

                }
            }
        });
        clientGenerator.start();

        Thread barberThread = new Thread(new Runnable() {
            Barber barber = new Barber(waitingHall, saloon);

            @Override
            public void run() {
                while (!Thread.currentThread().isInterrupted()) {
                    if (!waitingHall.isEmpty() && !saloon.isBuisy()) {
                        barber.trim();
                    } else {

                        System.out.println("[BARBER]: Nobody to trim! I'm go sleeping!");
                        synchronized (monitor) {
                            try {
                                while (waitingHall.isEmpty()) {
                                    monitor.wait();
                                }
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                        System.out.println("Barber got up!");
                    }
                }
            }
        });

        barberThread.start();

    }
}

