package openWorld;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import players.*;
public class PlayerThread implements Runnable{
	Child tc;
	public final static List<Child> PlayerList=new ArrayList<Child>();
	public final static DamageMeta DmgMeta= new DamageMeta();
	public static final int MAX_PLAYERS=6;
	int chance=0;
	int exit_code=0;
	int oppIndex=0;
	static int flag;
	PlayerThread(Child c){
		tc=c;
	}
	@Override
	public void run() {
		synchronized(this) {
		try {
		DmgMeta.author="";
		 DmgMeta.free=true;
		//dmgMeta.free=true;
		//dmgMeta.isDmg=false;
		 PlayerList.add(tc);
		flag= PlayerList.size();
		if( PlayerList.size()==MAX_PLAYERS) {this.notifyAll();}
		System.out.println(tc.getNick()+ " registered? "+ PlayerList.contains(tc)+" size "+ PlayerList.size());
		System.out.println(tc.getNick()+" deployed"); 
		//Thread.currentThread().sleep(2000);
		
		
		while(this.checkStatus(tc)) {
			if( PlayerList.size()==1 && flag==MAX_PLAYERS) {
				System.out.println(tc.getNick()+" has won");
				break;
			}
			System.out.println(tc.getNick()+"("+tc.getHp()+") is roaming");
			if( DmgMeta.free==true) {
				//System.out.println("dmgMeta.free 1= "+ dmgMeta.free);
				 DmgMeta.free=false;
				this.setDuelDamage(tc);
				
			}
			
			if(tc.getNick()== DmgMeta.rec)
				{this.deliverDuelDamage(tc);
				this.notify();}
			else {this.wait(500);}
			
			
			 DmgMeta.free=true;
			
			//tc.damageHP(1);
			
		}
	
		}catch(Exception e) {
			System.out.println(tc.getNick()+" is facing "+e+" some issues");
		}
	}}
	boolean checkStatus(Child c) {
		if(c.getHp()<=0) {
			System.out.println(c.getNick()+ " has no more health left...");
			 PlayerList.remove(c);
			
			return false;
		}
		else if(c.getMana()<=0) {
			System.out.println(c.getNick()+ " has no more mana left...");
			 PlayerList.remove(c);
			return false;
		}
		
		else return true;
	}
	void deathTrap(Child c) {
		this.kill(c);
		System.out.println(c.getNick()+" fell into a trap!!"+"Stats: "+c.getHp());
	}
	
	
	
	void manaBurn(Child c) {
		c.setMana(0);
		System.out.println(c.getNick()+" fell into a magical trap!!"+"Stats: "+c.getMana());
	}
	
	void kill(Child c) {
		c.setHp(0);
	}
	
//	void monster(Child c) {
//		System.out.println("A Monster appeared!!");
//		this.chance=(int) ((Math.random() * ((20 - 1) + 1)) + 1);
//		if(this.chance!=0) {
//			if(this.chance%2==0) {
//				System.out.println(c.getNick() +" fought and sustained many injuries");
//				c.damageHP(this.chance);
//				c.damageMana(this.chance);
//				c.showall();
//				//wait();
//				System.out.println(c.getNick()+" is resting...");
//				
//			}
//			else {
//				System.out.println(c.getNick()+ " has fled the scene");
//				//notifyAll();
//			}
//			
//		}
//		
//	}
	List<Child> randomizeList(List<Child> playerList){
		Collections.shuffle(playerList);
		return playerList;
	}
	String getPlayerHP(){
		if(tc.getHp()>0) {
			return tc.getNick();
		}
		else {
			return null;
		}
	}
	void setDamageMeta(int dmg, String author, String rec, boolean free, boolean isDmg) {
		 DmgMeta.dmg=dmg;
		 DmgMeta.author=author;
		 DmgMeta.rec=rec;
		 DmgMeta.free=free;
		 DmgMeta.isDmg=isDmg;
		
		
	}
	
	void viewSetDmg() {
		System.out.println("dmgMeta.dmg="+ DmgMeta.dmg);
		System.out.println("dmgMeta.author="+ DmgMeta.author);
		System.out.println("dmgMeta.rec="+ DmgMeta.rec);
		System.out.println("dmgMeta.free="+ DmgMeta.free);
		System.out.println("dmgMeta.isDmg="+ DmgMeta.isDmg);
	}
	void setDuelDamage(Child c) {
		//System.out.println("dmgMeta.free 2= "+ dmgMeta.free);
		while(flag!= MAX_PLAYERS &&  DmgMeta.author==c.getNick())
			try {
				//Thread.currentThread().sleep(2000);
				
					this.wait();
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		//System.out.println("dmgMeta.free 3= "+ dmgMeta.free);
		String opponent;
		this.chance=(int) ((Math.random() * (( PlayerList.size() - 1 ) + 1)));
		//System.out.println("dmgMeta.free 4= "+ dmgMeta.free+" Chance "+this.chance);
			//if(this.chance%2==0) {
			//System.out.println( playerList.get(1).getNick());
		//	
		if( PlayerList.get(chance).getNick()!=c.getNick()) {
			System.out.println(c.getNick() +" fought and sustained many injuries");
			opponent= PlayerList.get(chance).getNick();
			System.out.println(tc.getNick()+"'s Opponent "+ opponent);
			setDamageMeta(this.chance+10, c.getNick(),opponent,false, true);
			//viewSetDmg();
			this.notify();
			
		}
//			}
//			else {
//				System.out.println(c.getNick()+ " has fled the scene");
//				
//			}
			
		//}
		
	}
	
	void deliverDuelDamage(Child c) {
		if(c.getNick()== DmgMeta.rec) {
			c.damageHP( DmgMeta.dmg);
			System.out.println( DmgMeta.author+ " attacked "+c.getNick()+"("+c.getHp()+")"+" for "+  DmgMeta.dmg + " HP");
			
			 DmgMeta.free=true;
			notify();
		}
		else {
			System.out.println("missed him - "+c.getNick()+"("+c.getHp()+")");
			
		}
	}
	
	int end(Child c) {
		System.out.println(c.getNick()+ " has successfully exited the world");
		return 1;
	}
	
}
