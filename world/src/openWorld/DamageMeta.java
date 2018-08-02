package openWorld;

import java.util.Comparator;

import players.Child;

public class DamageMeta implements Comparator<Child>{
	public boolean free=true;
	public boolean isDmg=false;
	public int dmg;
	public String author;
	public String rec;
	@Override
	public int compare(Child arg0, Child arg1) {
		
		return arg0.getMana()-arg1.getMana();
	}

}
