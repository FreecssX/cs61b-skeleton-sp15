public class CalculatorUI {
	public static void main(String[] args) {
		int a;
		int b;
		String operator;
		Calculator c = new Calculator();  
		while (true) {
			System.out.print("> ");
			String s = StdIn.readLine();
			if (s.equals("quit")) {
				break;
			}
			String[] S = s.split(" ");
			a = Integer.parseInt(S[0]);
			b = Integer.parseInt(S[2]);
			operator = S[1];
			if (operator.equals("+")) {
				System.out.println(c.add(a, b));
			} else {
				System.out.println(c.multiply(a, b));
			}
		}
	}
}