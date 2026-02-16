import java.util.HashMap;
import java.util.Map;

public class UserService {

    private Map<String,User> userMap=new HashMap<>();
    private User currentUser= null;

    public boolean registerUser(String username, String password, String fullName , String contact){
        if(userMap.containsKey(username)){
            System.out.println("User name already exist!!");
            return false;
        }
        User user=new User(username,password,fullName,contact);
        userMap.put(username,user);
        System.out.println("Registration successfull!!");
        return true;
    }


    public boolean loginUser(String username, String password){
        if(!userMap.containsKey(username)){
            System.out.println("User not found!!");
            return  false;
        }
        User user= userMap.get(username);
        if(!user.getPassword().equals(password)){
            System.out.println("Password is incorect");
            return false;
        }

        currentUser=user;
        System.out.println("Welcome: "+currentUser.getFullName() +"!!");
        return true;

    }

       public void logOutUser(){
        if(currentUser!=null){
            System.out.println("Logged out"+ currentUser.getFullName());

        }
        currentUser=null;
        }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean isLoggenIN(){
        return  currentUser!=null;
    }
}
