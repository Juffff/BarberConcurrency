import java.util.Random;


public class Barber {
    private WaitingHall waitingHall;
    private Saloon saloon;
    private Client client;

    public Barber(WaitingHall waitingHall , Saloon saloon) {
        this.waitingHall = waitingHall;
        this.saloon = saloon;
    }


    public void trim() {
            client = (Client) waitingHall.callNextClient();
            System.out.println(client + " trimmed");
            int sleepInterval = 1000 * new Random().nextInt(2); //TODO: refactor
            try {
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    }


}
