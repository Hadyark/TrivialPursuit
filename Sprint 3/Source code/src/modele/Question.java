package modele;

import java.io.Serializable;

import enumeration.Category;

public class Question implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String author, statement, answer;
	private Category category;
	
	public Question(String author, Category categ, String statement, String answer ) {
		this.author = author;
		this.statement = statement;
		this.answer = answer;
		this.category = categ;
	}

	@Override
	public String toString() {
		return "Question [author=" + author + ", statement=" + statement + ", answer=" + answer + ", category="
				+ category + "]";
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	public boolean equalsQuestion (Object o){
		if(o instanceof Question){
			return ((Question)o).getStatement().equals(getStatement());
		}
		return false;
	}
	
}
