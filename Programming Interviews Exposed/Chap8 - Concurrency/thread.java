public class thread
{
	public class Account
	{
		int userNumber;
		String userLastName;
		String userFirstName;
		double userBalance;

		public boolean deposit(double amount)
		{
			double newBalance;

			if(amount < 0.0)
				return false;
			else
			{
				synchronized(this)
				{
					newBalance = userBalance + amount;
					userBalance = newBalance;
				}
				return true;
			}
		}

		public synchronized boolean withdraw(double amount)
		{
			double newBalance;

			if(amount > userBalance)
				return false;
			else
			{
				newBalance = userBalance - amount;
				userBalance = newBalance;
				return true;
			}
		}
	}

	public static void main(String[] args)
	{
		System.out.println("Thread.");
	}
}