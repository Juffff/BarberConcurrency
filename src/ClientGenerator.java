
import java.util.Random;

public class ClientGenerator {

private WaitingHall waitingHall;
public ClientGenerator(WaitingHall waitingHall){
    this.waitingHall = waitingHall;
}
    public void generate() {

            waitingHall.addClient(generateRandomClient());


            try {
                int sleepInterval = 1000 * new Random().nextInt(2); //TODO: refactor
                Thread.sleep(sleepInterval);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

    }

    public Client generateRandomClient() {
        return new Client("Client_" + new Random().nextInt(10000) + new Random().nextInt(10000));
    }


}