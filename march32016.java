//March 3, 2016

public static void main(String[] args){
	for (int i = 0; i < 5; i++) new Printer(i).start();
}


class Printer extends Thread{
	int i;
	Printer(int newi)(i = newi;)
	public void run(){
		for(int j = 0; j <300; j++) printLine(i);
	}

	public void printLine(int tabs){
		for(int i = 0; i < tabs; i++){
			System.out.print("\t");
		}
		System.out.println(tabs);
	}
}