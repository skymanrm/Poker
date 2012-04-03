package action;

public abstract class Action {
	
	private final long time;
	
	public Action(long time){
		this.time = time;
	}
	
	public long getTime(){
		return time;
	}
}
