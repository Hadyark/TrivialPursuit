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

	public Deck() {
		this.questions = new ArrayList<Question>();
	}

	public static Deck getInstanceDeck() {
		if(instanceDeck == null){
			instanceDeck = new Deck();
			caretaker = new Caretaker<>();
		}
		return instanceDeck;
	}

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
	public List<Question> getQuestions() {
		return questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	@Override
	public String toString() {
		return "Deck [questions=" + questions + "]";
	}

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
	public void restore(Memento<List<Question>> memento) {
		setState(memento.getState());
	}

	public void setState(List<Question> state) {
		this.questions = state;
	}

	public Memento<List<Question>> save() {
		List<Question> list = new ArrayList<>();
		list.addAll(this.questions);
		return new Memento<>(list);
	}
	public void saveStateAndAdd() {
		caretaker.addMemento(save());
	}

}
