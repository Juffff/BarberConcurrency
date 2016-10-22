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

    public boolean isEmpty() {
        if (queue.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    public static WaitingHall getWaitingHall(int capacity) {
        if (waitingHall == null) {
            waitingHall = new WaitingHall(capacity);
        }
        return waitingHall;
    }

    public boolean addClient(Client client) {
        System.out.println("New Client came: " + client);

        if (queue.remainingCapacity() > 0) {
            System.out.println(client + " added to waiting hall!");
        } else {
            System.out.println(client + " gone!");
            return false;
        }
        try {

                return queue.add(client);


        } catch (IllegalStateException e) {
            return false;
        }
    }


    public Object callNextClient() {
        return queue.poll();
    }


}
