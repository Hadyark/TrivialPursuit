package network;

import java.io.Serializable;

public class Message implements Serializable{

	private Action action;
	private Object object;

	/**
	 * It allows to create a deck
	 * @param action
	 * The action possible by the server and the client
	 * @param object
	 * The content of the message
	 */
	public Message(Action action, Object object) {
		this.action = action;
		this.object = object;
	}
	/**
	 * @return the action in the message
	 */
	public Action getAction() {
		return action;
	}
	/**
	 * @param action
	 * The new action of the message
	 */
	public void setAction(Action action) {
		this.action = action;
	}
	/**
	 * @return The object (content) of the message
	 */
	public Object getObject() {
		return object;
	}
	/**
	 * @param object
	 * The new object of the message
	 */
	public void setObject(Object object) {
		this.object = object;
	}
	/**
	 * @return a string of the message
	 */
	@Override
	public String toString() {
		return "Message [action=" + action + ", object=" + object + "]";
	}



}
