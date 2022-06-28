package pc;
import java.util.LinkedList;

public class procon {
	public static void main(String[] args)throws InterruptedException {
		final PC pc = new PC();
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				try {
					pc.producer();
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				try {
					pc.consumer();
					
				}
				catch(InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		});
		t1.start();
		t2.start();
		t1.join();
		t2.join();
	}
	public static class PC{
		LinkedList<Integer>list = new LinkedList <Integer>();
		int capacity = 2;
		public void producer() throws InterruptedException{
			int value = 0;
			while(true) {
				synchronized(this) {
					while(list.size()==capacity)
						wait();
					System.out.println("producer produced="+ value);
					list.add(value++);
					notify();
					Thread.sleep(1000);
				}
			}
			
		}
		public void consumer() throws InterruptedException {
			while(true) {
				synchronized(this) {
					while(list.size()==0)
						wait();
					int val = list.removeFirst();
					System.out.println("consumer consumed="+val);
					notify();
					Thread.sleep(1000);
				}
			}
		}
		
	}

}
