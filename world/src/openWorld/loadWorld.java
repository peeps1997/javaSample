package openWorld;
import players.child;
import java.util.concurrent.*;
import players.parent;
import java.lang.*;
public class loadWorld extends worldAttributes{
	loadWorld(){
		super();
	}
	
	public static void main(String args[]) {
		int i;
		loadWorld newWorld=new loadWorld();
		//ThreadGroup t1=new ThreadGroup("PlayerBunch");
		Thread t = null;
		
		for(i=0;i<10;i++)
			{t=new Thread( new playerThread(new child(i)));
			t.start();
			System.out.println("------------>"+i);
			}
		
		
	}
}

