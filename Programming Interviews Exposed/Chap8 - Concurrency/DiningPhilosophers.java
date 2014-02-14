public class DiningPhilosophers
{
	// Each “fork” is just an Object we synchronize on 
	private Object[] forks;
	private Philosopher[] philosophers;
	// Prepare the forks and philosophers
	private DiningPhilosophers( int num ){
		forks = new Object[ num ];
		philosophers = new Philosopher[ num ];

		for( int i = 0; i < num; ++i )
		{
			forks[i] = new Object();


			int fork1 = i;
			int fork2 = ( i + 1 ) % num;
			if( i == 0 )
			{
				philosophers[0] = new Philosopher( 0, fork2, fork1 );
			}
			else
			{
				philosophers[i] = new Philosopher( i, fork1, fork2 );
			}
		}
	}

	// Start the eating process
	public void startEating() throws InterruptedException
	{
		for( int i = 0; i < philosophers.length; ++i )
		{
			philosophers[i].start();
		}
		// Suspend the main thread until the first philosopher
		// stops eating, which will never happen -- this keeps
		// the simulation running indefinitely
		philosophers[0].join();
	}

	// Entry point for simulation
	public static void main( String[] args )
	{
		try
		{
			DiningPhilosophers d = new DiningPhilosophers( 5 );
			d.startEating();
		}
		catch( InterruptedException e )
		{ }
	}

	// Each philosopher runs in its own thread.
	private class Philosopher extends Thread
	{
		private int id;
		private int fork1;
		private int fork2;
		Philosopher( int id, int fork1, int fork2 )
		{
			this.id = id;
			this.fork1 = fork1;
			this.fork2 = fork2;
		}
		public void run()
		{
			status( "Ready to eat using forks " + fork1 + " and " + fork2 );
			pause(); // pause to let others get ready
			while( true )
			{
				status( "Picking up fork " + fork1 );
				synchronized( forks[ fork1 ] )
				{
					status( "Picking up fork " + fork2 );
					synchronized( forks[ fork2 ] )
					{
						status( "Eating" );
					}
				}
			}
		}

		private void pause()
		{
			try
			{
				sleep( 200 );
			}
			catch( InterruptedException e )
			{
				// do nothing
			}
		}

		private void status( String msg )
		{
			System.out.println( "Philosopher " + id + ": " + msg );
		}
	}
}