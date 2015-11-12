package utilitaries;

public class Message {
	private String errorMessage;
	private String successMessage;
	
	public Message(String successMessage, String errorMessage){
		this.errorMessage = errorMessage;
		this.successMessage = successMessage;
	}
	
	public String getErrorMessage(){
		return this.errorMessage;
	}
	
	public String getSuccessMessage(){
		return this.successMessage;
	}
}
