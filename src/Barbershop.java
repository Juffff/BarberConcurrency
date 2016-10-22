/**
 * Created by Juff on 22.10.16.
 * <p>
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
    public static Object monitor = new Object();

    public static void main(String[] args) throws InterruptedException {

        new Barbershop().go();

    }

    private void go() throws InterruptedException {
        int n = 5; //TODO: n to Input
        waitingHall = WaitingHall.getWaitingHall(n);
        saloon = Saloon.getSaloon();

      /*  Thread barberThread = new Thread(new Barber(waitingHall, saloon));*/
       /* Thread clientGeneratorThread = new Thread(new ClientGenerator(waitingHall));
        clientGeneratorThread.start();*/




        Thread barberThread = new Thread() {
            Barber barber = new Barber(waitingHall, saloon);
            boolean sleeping = false;

            @Override
            public void run() {

                    synchronized (monitor) {
                        if (waitingHall.isEmpty() /*&& !saloon.isBuisy()*/) {
                            try {
                                System.out.println("Barber go sleeping!");
                                sleeping = true;
                                monitor.wait();

                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        } else {
                         /*   if (sleeping) {
                                System.out.println("Barber got up!");
                                sleeping = false;
                            }*/
                            barber.trim();
                        }

                    }


                }

        };

        barberThread.start();

        Thread clientGeneratorThread = new Thread(){
            ClientGenerator clientGenerator = new ClientGenerator(waitingHall);
            @Override
            public void run() {
                synchronized (monitor) {
                    while (!isInterrupted()) {
                        System.out.println(waitingHall.isEmpty());
                        clientGenerator.generate();
                        saloon.setBusy(true);
                        System.out.println(barberThread.getState());
                        monitor.notify();
                        System.out.println(barberThread.getState());
                    }
                    }


            }
        };
        clientGeneratorThread.start();

    }


}

