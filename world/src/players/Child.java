package players;
import java.util.Scanner;
public class Child extends Parent implements ExtraBeta,Train {
	public String nick;
    public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}

	public Child(int nickValue) {
        super();
//       Scanner sc=new Scanner(System.in);
//       System.out.println("Enter Nick:");
        this.setNick("player "+nickValue);
        System.out.println("Intelligence "+this.getPvt());
        System.out.println("Agility "+this.getAgility());
        System.out.println("Strength "+this.getStrength());
        System.out.println("Physique "+this.getModel());
    }
    
    public Child(Child c) {
    	this.setAgility(c.getAgility());
    	this.setHp(c.getHp());
    	this.setModel(c.getModel());
    	this.setNick(c.getNick());
    	this.setStrength(c.getStrength());
    	this.setPvt(c.getPvt());
    	this.setMana(c.getMana());
    }
    
    public void morph() {
        System.out.println("Morph initialized");
        this.setPvt(this.getPvt() * 5);
        System.out.println("Intelligence"+this.getPvt());
        this.setAgility(this.getAgility() * 5);
        System.out.println("Agility"+this.getAgility());
        this.setStrength(this.getStrength() * 5);
        System.out.println("Strength"+this.getStrength());
        this.setModel(this.getModel() * 5);
        System.out.println("Physique"+this.getModel());
        System.out.println("Exiting Morph");
    }
    
    public void damageHP(int num) {
    	this.setHp(this.hp-num);
    	System.out.println(this.nick+" lost "+num+" hp...");
    	
    }
    public void damageMana(int num) {
    	num*=2;
    	this.setMana((this.getMana()-num));
    	System.out.println(this.nick+" lost "+num+" mana...");
    }
    public void showall() {
        System.out.println("Stats");
        System.out.println("Name "+this.getNick());
        System.out.println("Intelligence "+this.getPvt());
        System.out.println("Agility "+this.getAgility());
        System.out.println("Strength "+this.getStrength());
        System.out.println("Physique "+this.getModel());
        System.out.println("Health "+this.getHp());
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
		this.setAgility(this.getAgility()+10);
		
	}

}
