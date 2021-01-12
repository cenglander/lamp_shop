package lamp_shop.service;

public interface EmailService {

	void sendSimpleMessage();
//	void sendSimpleMessage(String to, String subject, String text);
 
	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
 
}
	