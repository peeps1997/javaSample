package openWorld;
import players.child;
public class loadWorld extends worldAttributes{
	loadWorld(){
		super();
		System.out.println("Spawning player");
		child newPlayer= new child();
		System.out.println("Player spawned");
		System.out.println("Player stats");
		newPlayer.showall();
	}
	
	public static void main(String args[]) {
		loadWorld newWorld=new loadWorld();
	}
}
