import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;


public class WaitingHall {
    public static WaitingHall waitingHall;

    private BlockingQueue queue;

    private WaitingHall(int capacity) {
        this.queue = new ArrayBlockingQueue(capacity, true);
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }

    public static WaitingHall getWaitingHall(int capacity) {
        if (waitingHall == null) {
            waitingHall = new WaitingHall(capacity);
        }
        return waitingHall;
    }

    public boolean addClient(Client client) {
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
