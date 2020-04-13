package pattern;

import java.util.ArrayList;

public class Caretaker<T> {
	
	private ArrayList<Memento<T>> mementos;
	
public Caretaker(){
		mementos = new ArrayList<>();
	}
	public void addMemento(Memento<T> memento) {
		mementos.add(memento);
	}

	public Memento<T> getMemento(int index) {
		return mementos.get(index);
	}
	
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
