package players;

public class Parent extends Human {
    int agility;
    public int model;
    private int intelligence;
    protected int strength;

    public int getAgility() {
		return agility;
	}

	public void setAgility(int agility) {
		this.agility = agility;
	}

	public int getModel() {
		return model;
	}

	public void setModel(int model) {
		this.model = model;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public void setIntelligence(int intelligence) {
		this.intelligence = intelligence;
	}

	public int getStrength() {
		return strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	
    Parent() {
        super();
        System.out.println("Initializing accessories");
        this.setAgility(10);
        this.setPvt(10);
        this.setStrength(10);
        this.setModel(10);
        System.out.println("Initialized, Agility +10, Intelligence +10, Strength +10, Physical Model +10");
    }

    public int getPvt() {
        return intelligence;
    }

    void setPvt(int intelligence) {
        this.intelligence = intelligence;
    }

}