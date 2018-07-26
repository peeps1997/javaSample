package players;
import java.util.Scanner;
public class child extends parent implements extraBeta,train {
	public String nick;
    public child() {
        super();
        Scanner sc=new Scanner(System.in);
        System.out.println("Enter Nick:");
        this.nick=sc.next();
        System.out.println(this.getPvt());
        System.out.println(this.agility);
        System.out.println(this.strength);
        System.out.println(this.model);
    }
    
    public void morph() {
        System.out.println("Morph initialized");
        this.setPvt(this.getPvt() * 5);
        System.out.println(this.getPvt());
        this.agility = this.agility * 5;
        System.out.println(this.agility);
        this.strength = this.strength * 5;
        System.out.println(this.strength);
        this.model = this.model * 5;
        System.out.println(this.model);
        System.out.println("Exiting Morph");
    }

    public void showall() {
        System.out.println("Display initialized");
        System.out.println(this.nick);
        System.out.println(this.getPvt());
        System.out.println(this.agility);
        System.out.println(this.strength);
        System.out.println(this.model);
        System.out.println(this.hp);
        System.out.println(this.getMana());
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
