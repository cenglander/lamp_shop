package lamp_shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage() {
//    public void sendSimpleMessage(String to, String subject, String text) {
        
        SimpleMailMessage message = new SimpleMailMessage(); 
        message.setFrom("lampshop.exam@gmail.com");
        message.setTo("charlotte.englander@gmail.com"); 
        message.setSubject("Order confirmation"); 
        message.setText("Your order is being handled.");
//        message.setTo(to); 
//        message.setSubject(subject); 
//        message.setText(text);
        emailSender.send(message);
        
    }

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		// TODO Auto-generated method stub	
	}
}
