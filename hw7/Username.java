import java.util.Random;
public class Username {

    // Potentially useless note: (int) '0' == 48, (int) 'a' == 97

    // Instance Variables (remember, they should be private!)
    // YOUR CODE HERE
	private String Username;
    public Username() {
        Random rand = new Random(); 
        int value1 = rand.nextInt(26) + 97; 
        int value2 = rand.nextInt(26) + 97;
        int value3 = rand.nextInt(26) + 97;
        boolean Cap1 = rand.nextInt(2) == 1;
        boolean Cap2 = rand.nextInt(2) == 1;
        boolean Cap3 = rand.nextInt(2) == 1;
        int num = rand.nextInt(2) + 2;
       
        char a = (char) value1;
        if(Cap1) {
        	a = Character.toUpperCase((a));
        }
        char b = (char) value2;
        if(Cap2) {
        	b = Character.toUpperCase(b);
        }
        char c = (char) value3;
        if(Cap3) {
        	c = Character.toUpperCase(c);
        }
        String result = Character.toString(a) + Character.toString(b);
        if(num == 3) {
        	result = result + Character.toString(c);
        }
        Username = result;
    }

    public Username(String reqName) {
        if(reqName == null) {
        	throw new NullPointerException("Requested username is null!");
        }
        if(reqName.length() != 2 || reqName.length() != 3) {
        	throw new IllegalArgumentException("wrong length");
        } else {
        	for(int i = 0; i < reqName.length(); i += 1) {
        		char s = reqName.charAt(i);
        		int k = (int) s;
        		if((k <= 48 + 9 && k >= 48) || (k >= 97 && k <= 97 + 25)
        		    || (k >= ((int) 'A') && k <= ((int) 'A' + 25))) {
        			
        		} else {
        			throw new IllegalArgumentException("wrong characters");
        		}
        	}
        	Username = reqName;
        }
    }
    private boolean charEquals(char a, char b) {
    	return a == b || Math.abs(((int) a - (int) b))== Math.abs(((int) 'a' - (int) 'b'));
    }
    @Override
    public boolean equals(Object o) {
        if(o instanceof Username) {
        	if(((String) o).length() == Username.length() ) {
        		for(int i = 0; i < Username.length(); i += 1) {
        			if(!(charEquals(Username.charAt(i), ((String) o).charAt(i)))) {
        				break;
        			}
        			return true;
        		}
        	}
        	else return false;
        }
        return false;
    }

    @Override
    public int hashCode() { 
        String s = "";
        int result = 0;
        for(int i = 0; i < Username.length(); i += 1) {
        	s = s + Character.toString(Character.toLowerCase(Username.charAt(i)));
        }
        for(int i = 0; i < s.length(); i += 1) {
        	char k = Character.toLowerCase(Username.charAt(i));
        	int num = (int) k;
        	if(num <=57) num = num - 48;
        	else num = num - 97;
        	result = result * 36 + num;
        }
        return result;
    }
    public String getName() {
    	return Username;
    }

    public static void main(String[] args) {
        // You can put some simple testing here.
    }
}