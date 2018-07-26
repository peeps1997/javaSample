package players;

class parent extends human {
    int agility;
    public int model;
    private int intelligence;
    protected int strength;

    parent() {
        super();
        System.out.println("Initializing accessories");
        this.agility = 10;
        this.setPvt(10);
        this.strength = 10;
        this.model = 10;
        System.out.println("Initialized, Agility +10, Intelligence +10, Strength +10, Physical Model +10");
    }

    public int getPvt() {
        return intelligence;
    }

    void setPvt(int intelligence) {
        this.intelligence = intelligence;
    }

}