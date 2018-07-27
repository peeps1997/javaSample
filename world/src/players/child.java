package players;
import java.util.Scanner;
public class child extends parent implements extraBeta,train {
	public String nick;
    public child() {
        super();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Nick:");
        this.nick=sc.next();
        System.out.println("Intelligence "+this.getPvt());
        System.out.println("Agility "+this.agility);
        System.out.println("Strength "+this.strength);
        System.out.println("Physique "+this.model);
    }
    
    public child(child c) {
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
    	this.setMana((this.getMana()-(num*2)));
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
