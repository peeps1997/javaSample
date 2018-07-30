package players;

class Human {
    public int hp;
    public int getHp() { 
		return hp;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}
	private int mana;
    Human(){
        System.out.println("Initializing human");
        this.hp=100;
        this.mana=100;
        System.out.println("Initialized: hp+100, mana+100");
    }
    public int getMana(){
        return this.mana;
    }
    public void setMana(int inc){
        this.mana=this.mana+inc;
    }
}