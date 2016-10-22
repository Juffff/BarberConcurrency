import java.util.Random;


public class Barber {
    private WaitingHall waitingHall;
    private Saloon saloon;

    public Barber(WaitingHall waitingHall , Saloon saloon) {
        this.waitingHall = waitingHall;
        this.saloon = saloon;
    }


    public void trim() {
        Client client = (Client) waitingHall.callNextClient();
            saloon.setBusy(true);
            System.out.println("Saloon is busy with a "+ client + "!");
            int sleepInterval = 1000 * new Random().nextInt(5); //TODO: refactor
            try {
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(client + " trimmed");
            saloon.setBusy(false);
          System.out.println("Saloon is free!");


    }


}
