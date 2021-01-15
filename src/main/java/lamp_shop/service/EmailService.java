package lamp_shop.service;

import java.util.List;

import lamp_shop.model.OrderLine;

public interface EmailService {

	void sendMessage(String to, int orderId, List<OrderLine> orderLines);
 
	void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment);
 
}
	