package modele;

import java.io.Serializable;

import enumeration.Category;

public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String author, statement, answer;
	private Category category;
	
	/**
	 * It allows to create an account
	 * @param author
	 * author of the question
	 * @param categ
	 * category of the question
	 * @param statement
	 * statement of the question
	 * @param answer
	 * answer of the question
	 */
	public Question(String author, Category categ, String statement, String answer ) {
		this.author = author;
		this.statement = statement;
		this.answer = answer;
		this.category = categ;
	}

	/**
	 * @return a string of the question
	 */
	@Override
	public String toString() {
		return "Question [author=" + author + ", statement=" + statement + ", answer=" + answer + ", category="
				+ category + "]";
	}
	/**
	 * @return the author of the question
	 */
	public String getAuthor() {
		return author;
	}
	/**
	 * @param author
	 * The new author of the question
	 */
	public void setAuthor(String author) {
		this.author = author;
	}
	/**
	 * @return The statement of the question
	 */
	public String getStatement() {
		return statement;
	}
	/**
	 * @param statement
	 * The new statement of the question
	 */
	public void setStatement(String statement) {
		this.statement = statement;
	}
	/**
	 * @return The answer of the question
	 */
	public String getAnswer() {
		return answer;
	}
	/**
	 * @param answer
	 * The new answer of the question
	 */
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	/**
	 * @return The category of the question
	 */
	public Category getCategory() {
		return category;
	}
	/**
	 * @param category
	 * The new category of the question
	 */
	public void setCategory(Category category) {
		this.category = category;
	}
	/**
	 * 
	 * @param o
	 * The object to be compared
	 * @return the resultat
	 */
	public boolean equalsQuestion (Object o){
		if(o instanceof Question){
			return ((Question)o).getStatement().equals(getStatement());
		}
		return false;
	}
	
}
