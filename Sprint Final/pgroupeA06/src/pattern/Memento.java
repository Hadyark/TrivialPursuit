package pattern;

public class Memento<T> {

	
	private T state;
	
	/**
	 * @param state
	 * The state to save
	 */
    public Memento(T state) {
        this.state = state;
    }
    /**
	 * @return the state saved
	 */
    public T getState() {
        return state;
    }

	@Override
	public String toString() {
		return "\nMemento [state=" + state + "]";
	}
    
}
