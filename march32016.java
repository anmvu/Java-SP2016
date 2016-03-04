//March 3, 2016

public static void main(String[] args){
	for (int i = 0; i < 5; i++) new Printer(i).start();
}

class Supplier extends Thread{
	Buffer theBuff;
	Supplier(Buffer newBuff)(theBuff = newBuff;)

	public void run(){
		while(true){
			
		}
	}
}

class Buffer{
	final int MAX = 100;
	int count;
	Buffer() {count = 0;}

	void addToBuffer(){
		if (count >= MAX){
			try{
				Thread.currentThread().sleep(100);
			} catch(InterruptedException ex){}

		}
		int newCount = count+1;
		count = newCount;
	}

	void takeFromBuffer(){
		if (count <= 0 ){
			try{
				Thread.currentThread().sleep(100);
			} catch(InterruptedException ex){}

		}
		int newCount = count-1;
		count = newCount;
		if (count < 0 || count > MAX{
			Thread.currentThread().
		}
	}
}


class Printer extends Thread{
	int i;
	// Object sem;
	// Printer(int newi, Object newSem)(i = newi; sem = newSem;)
	//.. I guess we don't use the built in semphores

	public void run(){
		for(int j = 0; j <300; j++) printLine(i);
	}

	//if static - then synchronized needs to happen on class object not on the instance
	public static synchronized void printLine(int tabs){
		// try{
		// 	sem.wait();
		// }catch(InterruptedException ex){}

		for(int i = 0; i < tabs; i++){
			System.out.print("\t");
		}
		// System.out.println(tabs);
		// sem.notify();
	}

	//every object in Java has a built-in semaphore
}