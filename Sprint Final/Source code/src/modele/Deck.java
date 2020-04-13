package modele;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import enumeration.Category;
import pattern.Caretaker;
import pattern.Memento;

public class Deck implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<Question> questions;
	private static Deck instanceDeck;
	private static Caretaker<List<Question>> caretaker;

	/**
	 * It allows to create a deck
	 */
	public Deck() {
		this.questions = new ArrayList<Question>();
	}
	/** 
	 * @return an instance of the deck
	 */
	public static Deck getInstanceDeck() {
		if(instanceDeck == null){
			instanceDeck = new Deck();
			caretaker = new Caretaker<>();
		}
		return instanceDeck;
	}
	/**
	 * add a question if the deck don't contain it
	 * @param q
	 * The question that needs to be added
	 * @return The resultat
	 */
	public boolean addQuestion(Question q) {
		for (Question question : questions) {
			if(q.equalsQuestion(question)) {	
				return false;
			}
		}
		this.questions.add(q);
		saveStateAndAdd();
		return true;
	}
	/**
	 * @return The list of the questions
	 */
	public List<Question> getQuestions() {
		return questions;
	}
	/**
	 * @param questions
	 * The new list of the question
	 */
	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}
	/**
	 * @return a string of the deck
	 */
	@Override
	public String toString() {
		return "Deck [questions=" + questions + "]";
	}
	/**
	 * @param category
	 * The category of the question to be drawed
	 * @return the question
	 */
	public Question toDraw(Category category) {
		int index=0;
		boolean test=false;
		Question tmp;
		Question question;
		do {
			question=this.questions.get(index);

			//Move the card drawn at the end of the deck
			if(question.getCategory()==category){
				tmp=question;
				this.questions.remove(index);
				this.questions.add(tmp);
				test=true;
			}
			index++;
		} while (test==false);
		return question;
	}

	//Memento
	public void undo(){
		restore(caretaker.undo());
	}
	/**Methode Memento
	 * restore the state from the memento
	 * @param memento who contains the state to restore
	 */
	public void restore(Memento<List<Question>> memento) {
		setState(memento.getState());
	}
	
	/**Methode Memento
	 * set the state 
	 * @param state
	 * The new state
	 */
	public void setState(List<Question> state) {
		this.questions = state;
	}
	
	/**Methode Memento
	 * save the state
	 * @return a memento who contains the saved state
	 */
	public Memento<List<Question>> save() {
		List<Question> list = new ArrayList<>();
		list.addAll(this.questions);
		return new Memento<>(list);
	}
	
	/**Methode Memento
	 * add the state into a memento
	 */
	public void saveStateAndAdd() {
		caretaker.addMemento(save());
	}

}
