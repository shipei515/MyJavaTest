package thread;

public class ThreadTest {

	public static void main(String[] args) {
		MyTread1 thread = new MyTread1();
		System.out.println("main start:" + System.currentTimeMillis());
		thread.start();
		System.out.println("main end:" + System.currentTimeMillis());
	}

}
