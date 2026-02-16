import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BookingService {
        private List<Train>trainList= new ArrayList<>();
        private List<Ticket> ticketList= new ArrayList<>();

        public BookingService(){
            trainList.add(new Train(101,"rajdhani","kolhapur","solhapur",100));
            trainList.add(new Train(102,"Mumbai Express","kolhapur","Mumbai",10));
            trainList.add(new Train(103,"Goa Express","kolhapur","Goa",170));
            trainList.add(new Train(104,"new one","kolhapur","Delhi",1000));
            trainList.add(new Train(105,"Tejas","kolhapur","Beed",90));
            trainList.add(new Train(106,"vande Bharat Express","kolhapur","Nagpur",91));


        }

        public List <Train> searchTrain(String source,String destination){
            List<Train> res= new ArrayList<>();
            for(Train train:trainList){
                if(train.getSource().equalsIgnoreCase(source)&& train.getDestination().equalsIgnoreCase(destination)){
                        res.add(train);
                }
            }

            return res;
        }


        public  Ticket bookTicket(User user,int trainId, int seatCount){
            for(Train train:trainList) {
                if (train.getTrainId() == trainId) {
                    if (train.bookSeats(seatCount)) {
                        Ticket tickit = new Ticket(user, train, seatCount);
                        ticketList.add(tickit);
                        return tickit;
                    } else {
                        System.out.println("not enough seats available");
                        return null;
                    }
                }
            }
                System.out.println("Train ID not Found!!");
                return null;

        }


        public List<Ticket> getTicketByUser(User user){
            List <Ticket> res= new ArrayList<>();
            for(Ticket ticket:ticketList){
                if(ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                        res.add((ticket));
                }
            }
            return res;
        }


        public boolean cancelTicket(int ticketId,User user){
            Iterator<Ticket> iterator=ticketList.listIterator();
            while (iterator.hasNext()){
                Ticket ticket=iterator.next();
                if(ticket.getTiketId()==ticketId && ticket.getUser().getUsername().equalsIgnoreCase(user.getUsername())){
                    Train train=ticket.getTrain();
                    train.cancelSeats(ticket.getSeatBooked());
                    iterator.remove();
                    System.out.println("Ticet: "+ticketId+" cancelled Successfully!!");
                    return true;
                }
            }
            System.out.println("ticket not found or does not belong to current  user ");
        return false;
        }


        public  void ListAllTrains(){
            System.out.println("List of all trains");
            for(Train train:trainList){
                System.out.println(train);
            }
        }
}
