package openWorld;
import players.child;
import players.parent;
public class loadWorld extends worldAttributes{
	loadWorld(){
		super();
		System.out.println("Spawning player");
		child newPlayer= new child();
		System.out.println("Player spawned");
		System.out.println("Player stats");
		((child) newPlayer).showall();
		playerThread p1=new playerThread(newPlayer);
		Thread t1=new Thread(p1);
		t1.start();
	}
	
	public static void main(String args[]) {
		loadWorld newWorld=new loadWorld();
		
		
	}
}

