public class Ticket {
    private int tiketId;
    private User user;
    private Train train;

    private int seatBooked;

    private static  int counter=1001;

    public Ticket( User user, Train train, int seatBooked) {
        this.tiketId = counter++;
        this.user = user;
        this.train = train;
        this.seatBooked = seatBooked;
    }

    public int getTiketId() {
        return tiketId;
    }

    public void setTiketId(int tiketId) {
        this.tiketId = tiketId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Train getTrain() {
        return train;
    }

    public void setTrain(Train train) {
        this.train = train;
    }

    public int getSeatBooked() {
        return seatBooked;
    }

    public void setSeatBooked(int seatBooked) {
        this.seatBooked = seatBooked;
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Ticket.counter = counter;
    }

    @Override
    public String toString() {
        return "Ticket ID"+tiketId+"| Train: "+train.getName()+"|Route: "+train.getSource()+" ->"+train.getDestination()+"|seats: "+seatBooked+"|Booked By: "+user.getFullName();
    }
}
