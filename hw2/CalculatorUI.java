public class CalculatorUI {
	public static void main(String[] args) {
		int a;
		int b;
		int n;
		String operator;
		Calculator c = new Calculator();  
		while (true) {
			System.out.print("> ");
			String s = StdIn.readLine();
			if (s.equals("quit")) {
				break;
			}
			else if (s.equals("dump")) {
				c.printAllHistory();
			}
			else if (s.equals("clear")) {
				c.clearHistory();
			}
			else if (s.equals("undo")) {
				c.undoEquation();
			}
			else if (s.equals("sum")) {
				System.out.println(c.cumulativeSum());
			}
			else if (s.equals("product")) {
				System.out.println(c.cumulativeProduct());
			}
			else {
				String[] S = s.split(" ");
				if (S[0].equals("history")) {
					n = Integer.parseInt(S[1]);
					c.printHistory(n);
				}
				else{
					a = Integer.parseInt(S[0]);
					b = Integer.parseInt(S[2]);
					operator = S[1];
					int result;
					String equation = S[0] + " " + operator + " " + S[2];
					if (operator.equals("+")) {
						result = c.add(a, b);
						System.out.println(result);
					} else {
						result = c.multiply(a, b);
						System.out.println(result);
					}
					c.saveEquation(equation, result);
				}
			}
		}
	}
}