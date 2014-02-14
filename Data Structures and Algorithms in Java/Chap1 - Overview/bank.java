import java.io.*;

// class bank {
	class BankAccount {
		private double balance;						// account balance
		public BankAccount(double openingBalance) { // constructor
			balance = openingBalance;
		}
		public void deposit(double amount) {		// makes deposit
			balance = balance + amount;
		}
		public void withdraw(double amount) {		// makes withdrawal
			balance = balance - amount;
		}
		public void display() {						// displays balance
			System.out.println("balance=" + balance);
		}
	}	// end class BankAccount

	// public static void main(String[] args) {
	// 	BankAccount ba1 = new BankAccount(100.00);	// create acct

	// 	System.out.print("Before transaction, ");
	// 	ba1.display();								// display balance

	// 	ba1.deposit(74.35);							// make deposit
	// 	ba1.withdraw(20.00);						// make withdrawal

	// 	System.out.print("After transaction, ");
	// 	ba1.display();								// display balance
	// }
	class BankApp {
		public static String getString() throws IOException {
			String s = "";
			try {
				InputStreamReader isr = new InputStreamReader(System.in);
				BufferedReader br = new BufferedReader(isr);
				s = br.readLine();
			}
			catch(Exception e) { }
			return s;
		}

		public static void main(String[] args) {
			System.out.print("Please Input: ");
			System.out.flush();
			String input = "";
			try {
				input = getString();
			}
			catch(Exception e) {}
			System.out.println("Your Input " + input);

			BankAccount ba1 = new BankAccount(100.00);	// create acct
			System.out.print("Before transactions, ");
			ba1.display();								// display balance
			ba1.deposit(74.35);							// make deposit
			ba1.withdraw(20.00);						// make withdrawal
			System.out.print("After transactions, ");
			ba1.display();								// display balance
		}  // end main()
	}  // end class BankApp
// }