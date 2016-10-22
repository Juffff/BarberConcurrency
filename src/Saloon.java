
public class Saloon {
    private static Saloon saloon;
    private boolean isBusy;

    private Saloon(){
        isBusy = false;
    }

    public static Saloon getSaloon(){
        if(saloon==null){
            saloon = new Saloon();
        }
        return saloon;
    }

    public boolean isBuisy() {
        return isBusy;
    }

    public void setBusy(boolean busy) {
        isBusy = busy;
    }
}
