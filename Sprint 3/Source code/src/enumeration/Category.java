package enumeration;

public enum Category {

	NETWORKS ("Networks"),
	OPERATING_SYSTEMS ("Operating systems"),
	CYBERSECURITY("Cybersecurity"),
	PROGRAMMING_LANGUAGES ("Programming languages"),
	INTERNET ("Internet"),
	SOCIAL_NETWORKS ("Social networks");

	private String categoryValue;

	private Category(String categoryValue){
		this.categoryValue = categoryValue;
	}

	public String toString(){
		return categoryValue;}

	public String getCategoryValue() {  
		return  this.categoryValue ;  
	} 
	
}
