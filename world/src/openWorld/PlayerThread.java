package openWorld;
import players.*;
public class PlayerThread implements Runnable{
	Child tc;
	int chance=0;
	int exit_code=0;
	PlayerThread(Child c){
		tc=c;
	}
	@Override
	public void run() {
		try {
		System.out.println(tc.getNick()+" deployed"); 
		//Thread.currentThread().sleep(2000);
		
		
		while(this.checkStatus(tc)) {
			System.out.println(tc.getNick()+" is roaming");
			this.monster(tc);
			
		}
		
		}catch(Exception e) {
			System.out.println(tc.getNick()+" is facing "+e+" some issues");
		}
	}
	boolean checkStatus(Child c) {
		if(c.getHp()<=0) {
			System.out.println(c.getNick()+ " has no more health left...");
			return false;
		}
		else if(c.getMana()<=0) {
			System.out.println(c.getNick()+ " has no more mana left...");
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
	
	void monster(Child c) {
		System.out.println("A Monster appeared!!");
		this.chance=(int) ((Math.random() * ((20 - 1) + 1)) + 1);
		if(this.chance!=0) {
			if(this.chance%2==0) {
				System.out.println(c.getNick() +" fought and sustained many injuries");
				c.damageHP(this.chance);
				c.damageMana(this.chance);
				c.showall();
				//wait();
				System.out.println(c.getNick()+" is resting...");
				
			}
			else {
				System.out.println(c.getNick()+ " has fled the scene");
				//notifyAll();
			}
			
		}
		
	}
	
	String getPlayerHP(){
		if(tc.getHp()>0) {
			return tc.getNick();
		}
		else {
			return null;
		}
	}
	void duel() {
		
		
	}
	
	int end(Child c) {
		System.out.println(c.getNick()+ " has successfully exited the world");
		return 1;
	}
	
}
