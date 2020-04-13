package pattern;

import java.util.ArrayList;

public class Caretaker<T> {

	private ArrayList<Memento<T>> mementos;

	public Caretaker(){
		mementos = new ArrayList<>();
	}
	
	/**
	 * Hold a memento in memory
	 * @param memento to save
	 */
	public void addMemento(Memento<T> memento) {
		mementos.add(memento);
	}
	
	/**
	 * @param index of the position of the memento to return
	 * @return the memento at the specified position in this list.
	 */
	public Memento<T> getMemento(int index) {
		return mementos.get(index);
	}
	
	/**
	 * Returns the previous memento and remove the actual state of the memory
	 * @return the previous memento
	 */
	public Memento<T> undo(){
		if(mementos.size()>1){
			mementos.remove(mementos.size()-1);
			return mementos.get(mementos.size()-1);
		}
		return mementos.get(0);
	}

	@Override
	public String toString() {
		return "Caretaker [mementos=" + mementos + "]";
	}

}
