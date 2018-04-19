public class fib {
	public static int fib(int N) {
		int cur = 0;
		int next = 1;
		while(N > 0) {
			int temp = next;
			next = next + cur;
			cur = temp;
			N -= 1;
		}
		return cur;
	}

	public static int fib2(int n, int k, int f0, int f1) {
		if(k == n) return f0;
		else return fib2(n, k + 1, f1, f0 + f1);
	}

	public static void main(String[] args) {
		System.out.println(fib(0));
		System.out.println(fib(1));
		System.out.println(fib(2));
		System.out.println(fib(3));
		System.out.println(fib(4));
		System.out.println(fib2(0, 0, 0, 1));
		System.out.println(fib2(1, 0, 0, 1));
		System.out.println(fib2(2, 0, 0, 1));
		System.out.println(fib2(3, 0, 0, 1));
		System.out.println(fib2(4, 0, 0, 1));
	}
}