import java.util.Map;
import java.util.HashMap;
public class UsernameBank {

    // Instance variables (remember, they should be private!)
    private HashMap<Username, String> ub;
    private HashMap<String, Integer> badU;
    private HashMap<String, Integer> badE;
    public UsernameBank() {
        ub = new HashMap<Username, String>();
        badU = new HashMap<String, Integer>();
        badE = new HashMap<String, Integer>();
    }
    private boolean isValidEmail(String email) {
        int numOfat = 0;
        if(email == null) return false;
        for(int i = 0; i < email.length(); i += 1) {
            if(email.charAt(i) == '@' ) {
                numOfat += 1;
            }
        }
        if(numOfat < 1) return false;
        return true;
    }
    public void generateUsername(String username, String email) {
        try {
            Username un = new Username(username);
            String e = ub.get(un);
            if(e != null) {
                System.out.println("IllegalArgument");
                return;
            }
            if(isValidEmail(email)) {
                ub.put(un, email);
            } else {
                System.out.println("Invalid email address");
            }
        }
        catch(Exception e) {
            System.out.println(e);
        }
    }

    public String getEmail(String username) {
        if(username == null) {
            throw new NullPointerException();
        }
        try {
            Username un = new Username(username);
            String email = ub.get(un);
            return email;
        }
        catch(Exception e) {
            Integer num = badU.get(username);
            if(num == null) badU.put(username, 1);
            else badU.put(username, num + 1);
            return null;
        }
    }

    public String getUsername(String userEmail)  {
        if(userEmail == null) throw new NullPointerException();
        for(Username un: ub.keySet()) {
            String email = ub.get(un);
            if(email == null) continue;
            if(ub.get(ub).equals(userEmail)) return un.getName();
        } 
        Integer num = badE.get(userEmail);
        if(num == null) badE.put(userEmail, 1);
        else badE.put(userEmail, num + 1); 
        return null;
    }

    public Map<String, Integer> getBadEmails() {
        return badE;
    }

    public Map<String, Integer> getBadUsernames() {
        return badU;
    }

    public String suggestUsername() {
        for(int i = 0; i < 10; i += 1) {
            Username un = new Username();
            if(ub.get(un) == null) return un.getName();
        }
        return null;
    }

    // The answer is somewhere in between 3 and 1000.
    public static final int followUp() {
        // YOUR CODE HERE
        return 3; // the total number of userNames should be 36^n where 36^n + 36^(n-1) + ... + 36^2 <= 2^16
    }

    // Optional, suggested method. Use or delete as you prefer.
    /*private void recordBadUsername(String username) {
        // YOUR CODE HERE
    }

    // Optional, suggested method. Use or delete as you prefer.
    private void recordBadEmail(String email) {
        // YOUR CODE HERE
    }*/
}