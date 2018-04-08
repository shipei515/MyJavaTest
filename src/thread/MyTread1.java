package thread;

public class MyTread1 extends Thread{
	@Override
	public void run() {
		try {
			System.out.println("run threadName=" + this.currentThread().getName() + "begin");
			sleep(2000);
			System.out.println("run threadName=" + this.currentThread().getName() + "end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
