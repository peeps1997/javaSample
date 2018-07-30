package openWorld;
import players.*;
public class playerThread implements Runnable{
	child tc;
	int chance=0,exit_code=0;
	playerThread(child c){
		tc=c;
	}
	@Override
	public void run() {
		try {
		System.out.println(tc.nick+" deployed");
		//Thread.currentThread().sleep(2000);
		
		
		while(this.checkStatus(tc)) {
			System.out.println(tc.nick+" is roaming");
			this.fight(tc);
			
		}
		
		}catch(Exception e) {
			System.out.println(tc.nick+" is facing some issues");
		}
	}
	boolean checkStatus(child c) {
		if(c.getHp()<=0) {
			System.out.println(c.nick+ " has no more health left...");
			return false;
		}else if(c.getMana()<=0) {
			System.out.println(c.nick+ " has no more mana left...");
			return false;
		}
		
		else return true;
	}
	void deathTrap(child c) {
		this.kill(c);
		System.out.println(c.nick+" fell into a trap!!"+"Stats: "+c.getHp());
	}
	
	void manaBurn(child c) {
		c.setMana(0);
		System.out.println(c.nick+" fell into a magical trap!!"+"Stats: "+c.getMana());
	}
	
	void kill(child c) {
		c.hp=0;
	}
	
	void fight(child c) {
		System.out.println("A Monster appeared!!");
		this.chance=(int) ((Math.random() * ((20 - 1) + 1)) + 1);
		if(this.chance!=0) {
			if(this.chance%2==0) {
				System.out.println(c.nick +" fought and sustained many injuries");
				c.damageHP(this.chance);
				c.damageMana(this.chance);
				c.showall();
				
			}
			else {
				System.out.println(c.nick+ " has fled the scene");
			}
			
		}
		
	}
	int end(child c) {
		System.out.println(c.nick+ " has successfully exited the world");
		return 1;
	}
	
}
