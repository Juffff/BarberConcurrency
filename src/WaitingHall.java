import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by Juff on 22.10.16.
 */
public class WaitingHall {
    public static WaitingHall waitingHall;

    private BlockingQueue queue;

    private WaitingHall(int capacity) {
        this.queue = new ArrayBlockingQueue(capacity, true);
    }

    public static WaitingHall getWaitingHall(int capacity) {
        if (waitingHall == null) {
            waitingHall = new WaitingHall(capacity);
        }
        return waitingHall;
    }

    public boolean addClient(Client client) {
        try {
            return queue.add(client);
        } catch (IllegalStateException e) {
            return false;
        }
    }

    public Object callNextClient(){
        return queue.poll();
    }


}
