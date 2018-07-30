package players;
import java.util.Scanner;
public class Child extends Parent implements ExtraBeta,Train {
	public String nick;
    public Child(int nickValue) {
        super();
//       Scanner sc=new Scanner(System.in);
//       System.out.println("Enter Nick:");
        this.nick="player"+nickValue;
        System.out.println("Intelligence "+this.getPvt());
        System.out.println("Agility "+this.agility);
        System.out.println("Strength "+this.strength);
        System.out.println("Physique "+this.model);
    }
    
    public Child(Child c) {
    	this.agility=c.agility;
    	this.hp=c.hp;
    	this.model=c.model;
    	this.nick=c.nick;
    	this.strength=c.strength;
    	this.setPvt(c.getPvt());
    	this.setMana(c.getMana());
    }
    
    public void morph() {
        System.out.println("Morph initialized");
        this.setPvt(this.getPvt() * 5);
        System.out.println("Intelligence"+this.getPvt());
        this.agility = this.agility * 5;
        System.out.println("Agility"+this.agility);
        this.strength = this.strength * 5;
        System.out.println("Strength"+this.strength);
        this.model = this.model * 5;
        System.out.println("Physique"+this.model);
        System.out.println("Exiting Morph");
    }
    
    public void damageHP(int num) {
    	this.setHp(this.hp-num*num);
    	
    }
    public void damageMana(int num) {
    	num*=2;
    	this.setMana((this.getMana()-num));
    }
    public void showall() {
        System.out.println("Stats");
        System.out.println("Name "+this.nick);
        System.out.println("Intelligence "+this.getPvt());
        System.out.println("Agility "+this.agility);
        System.out.println("Strength "+this.strength);
        System.out.println("Physique "+this.model);
        System.out.println("Health "+this.hp);
        System.out.println("Mana "+this.getMana());
        System.out.println("Exiting Display");
    }
    
    public void trainStrength() {
    	this.strength+=10;
    	
    }

	
	public void trainIntelligence() {
		this.setPvt(this.getPvt()+10);
		
	}

	
	public void trainAgility() {
		this.agility+=10;
		
	}

}