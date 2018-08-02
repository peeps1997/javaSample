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
        this.setHp(100);
        this.setMana(200);
        System.out.println("Initialized: hp+"+this.getHp()+" , mana+"+this.getMana());
    }
    public int getMana(){
        return this.mana;
    }
    public void setMana(int mana){
        this.mana=mana;
    }
}