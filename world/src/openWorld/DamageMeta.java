package openWorld;

import java.util.Comparator;

import players.Child;

public class DamageMeta implements Comparator<Child>{
	public boolean free=true;
	public boolean isFree() {
		return free;
	}
	public boolean isDmg() {
		return isDmg;
	}
	public int getDmg() {
		return dmg;
	}
	public String getAuthor() {
		return author;
	}
	public String getRec() {
		return rec;
	}
	public boolean isDmg=false;
	public int dmg;
	public String author;
	public String rec;
	@Override
	public int compare(Child arg0, Child arg1) {
		
		return arg0.getMana()-arg1.getMana();
	}

}
