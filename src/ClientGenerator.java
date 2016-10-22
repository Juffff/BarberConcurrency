import java.util.Date;
import java.util.Random;

public class ClientGenerator {
private WaitingHall waitingHall;
public ClientGenerator(WaitingHall waitingHall){
    this.waitingHall = waitingHall;
}
    public void generate() {

            waitingHall.addClient(generateRandomClient());
            try {
                int sleepInterval = 1000 * new Random().nextInt(5); //TODO: refactor
                //System.out.println("Interval = "+sleepInterval);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public Client generateRandomClient() {
        return new Client("Client_" + new Date().getTime());
    }


}