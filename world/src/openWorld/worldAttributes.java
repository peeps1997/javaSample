package openWorld;

class worldAttributes {
	private int npc;
	private int environment;
	private int sandbox;
	public int getNpc() {
		return npc;
	}
	void setNpc(int npc) {
		this.npc = npc;
	}
	public int getEnvironment() {
		return environment;
	}
	void setEnvironment(int environment) {
		this.environment = environment;
	}
	public int getSandbox() {
		return sandbox;
	}
	void setSandbox(int sandbox) {
		this.sandbox = sandbox;
	}
	worldAttributes(){
		System.out.println("Loading world Attributes");
		this.setNpc(10);
		this.setEnvironment(1);
		this.setSandbox(1);
		System.out.println("Loaded world attributes: npc+10, environment+1, sandbox+1");
	}
}
