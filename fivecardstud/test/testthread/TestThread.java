package testthread;

public class TestThread extends Thread{
	
	private int i ;
	public void run()
	{
		for(;i<100;i++)
		{
			System.out.println(getName() + " " + i);
		}
	}
	
	public static void main(String[] args) throws Exception
	{
		for(int i = 0; i<100;i++)
		{
			System.out.println(Thread.currentThread().getName() + " " + i);
			if(i == 20)
			{
				new TestThread().start();
				TestThread tt = new TestThread();
				tt.setDaemon(true);
				tt.start();
			}
		}
	}
	
}
