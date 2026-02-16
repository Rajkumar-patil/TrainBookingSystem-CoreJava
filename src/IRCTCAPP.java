import java.util.List;
import java.util.Scanner;

public class IRCTCAPP {
    private final Scanner scanner= new Scanner(System.in);

    private final UserService userService= new UserService();

    private final BookingService bookingService= new BookingService();

    public static void main(String[] args) {
        new IRCTCAPP().start();
    }

    public void start(){
        while (true){
            System.out.println("!!!!!!!!---------Welcome To IRCTC APP--------!!!!!");
            if(!userService.isLoggenIN()){
                System.out.println("1. Register");
                System.out.println("2. Login");
                System.out.println("3. Exit");
                System.out.println("Enter choice: ");
                int choice=scanner.nextInt();

                switch (choice){
                    case 1:
                        register();
                        break;
                    case 2:
                        login();
                        break;
                    case 3:
                        exitApp();
                        break;
                    default:
                        System.out.println("Invalid choice!!");
                }
            }else {
                showUserMenu();
            }
        }
    }

    public void register(){
        System.out.println("Enter user name: ");
        String username=scanner.next();
        System.out.println("Enter Password: ");
        String password=scanner.next();
        System.out.println("Enter full Name: ");
        scanner.nextLine();
        String fullName=scanner.nextLine();
        System.out.println("Enter contact: ");
        String contact=scanner.next();

        userService.registerUser(username,password,fullName,contact);
    }

    public void login(){
        System.out.println("Enter user name: ");
        String username=scanner.next();
        System.out.println("Enter Password: ");
        String password=scanner.next();

        userService.loginUser(username,password);
    }

    private void showUserMenu(){
        while (userService.isLoggenIN()){
            System.out.println("\n---------- User Menu----------");
            System.out.println("1.Search Train: ");
            System.out.println("2.Book Tickit: ");
            System.out.println("3.view My Ticket: ");
            System.out.println("4.cancel Tickets: ");
            System.out.println("5.view All Trains: ");
            System.out.println("6.Logout: ");
            System.out.println("Enter Choice");

            int choice=scanner.nextInt();
            switch (choice){
                case 1:
                    searchTrain();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    viewMyTicket();
                    break;
                case 4:
                    cancelTicket();
                    break;
                case 5:
                    bookingService.ListAllTrains();
                    break;
                case 6:
                    userService.logOutUser();
                    break;
                default:
                    System.out.println("Invalid choice!!");
            }





        }
    }

    private  void searchTrain(){
        System.out.println("Enter  Source station: ");
        String source=scanner.next();

        System.out.println("Enter  destination station: ");
        String destination=scanner.next();

        List<Train> trains=bookingService.searchTrain(source,destination);
        if(trains.isEmpty()){
            System.out.println("No Trians Found Between"+source+" TO "+destination);
            return;
        }
        System.out.println("Trains Found: ");
        for(Train train:trains){
            System.out.println(train);
        }

        System.out.println("DO you want to book ticket ? (yes/no)");
        String choice=scanner.next();

        if(choice.equalsIgnoreCase("yes")){
            System.out.println("Enter train id to book");
            int trainId=scanner.nextInt();
            System.out.println("Enter number of seats to book: ");
            int seats=scanner.nextInt();

            Ticket ticket=bookingService.bookTicket(userService.getCurrentUser(),trainId,seats);
            if(ticket!=null){
                System.out.println("Booking Successfull!!");
                System.out.println(ticket);
            }
        }
        else {
            System.out.println("Reterning to user menu...");
        }

    }


    private void bookTicket(){
        System.out.println("Enter  Source station: ");
        String source=scanner.next();

        System.out.println("Enter  destination station: ");
        String destination=scanner.next();


        List<Train> trains=bookingService.searchTrain(source,destination);

        if(trains.isEmpty()){
            System.out.println("No Trians available Between"+source+" TO "+destination);
            return;
        }
        System.out.println("Available Trains: ");
        for(Train train:trains){
            System.out.println(train);
        }
        System.out.println("Enter train id to book");
        int trainId=scanner.nextInt();
        System.out.println("Enter number of seats to book: ");
        int seats=scanner.nextInt();

        Ticket ticket=bookingService.bookTicket(userService.getCurrentUser(),trainId,seats);
        if(ticket!=null){
            System.out.println("Booking Successfull!!");
            System.out.println(ticket);
        }
    }

private void viewMyTicket(){
        List<Ticket> ticketByUser=bookingService.getTicketByUser(userService.getCurrentUser());
        if(ticketByUser.isEmpty()){
            System.out.println("NO Tickit Booked yet!!");
        }else{
            System.out.println("Your Tickets: ");
            for (Ticket ticket:ticketByUser){
                System.out.println(ticket);
            }
        }
    }


    private void cancelTicket(){
        System.out.println("Enter Ticket ID to cancle: ");
        int ticketID=scanner.nextInt();
        bookingService.cancelTicket(ticketID,userService.getCurrentUser());


    }

    private void exitApp(){
        System.out.println("Than you for using IRCTC APP");
        System.exit(0);
    }
}


