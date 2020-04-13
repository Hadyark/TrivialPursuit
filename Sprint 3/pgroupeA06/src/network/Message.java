package network;

import java.io.Serializable;

public class Message implements Serializable{

	private Action action;
	private Object object;


	public Message(Action action, Object object) {
		this.action = action;
		this.object = object;
	}
	public Action getAction() {
		return action;
	}
	public void setAction(Action action) {
		this.action = action;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	@Override
	public String toString() {
		return "Message [action=" + action + ", object=" + object + "]";
	}



}
