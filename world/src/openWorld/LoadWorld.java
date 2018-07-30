package openWorld;
import java.util.ArrayList;
import java.util.List;
import players.Child;
public class LoadWorld extends WorldAttribute{
	
	LoadWorld(){
		super();
	}
	
	public static void main(String args[]) {
		int i;
		List<String> alivePlayers=new ArrayList<String>();
		new LoadWorld();
		//ThreadGroup t1=new ThreadGroup("PlayerBunch");
		Thread t = null;
		PlayerThread p1;
		for(i=0;i<10;i++)
			{
			p1= new PlayerThread(new Child(i));
			t=new Thread(p1);
			t.start();
			alivePlayers.add(p1.getPlayerHP());
			//System.out.println("------------>"+i);
			}
		
		
	}
}

