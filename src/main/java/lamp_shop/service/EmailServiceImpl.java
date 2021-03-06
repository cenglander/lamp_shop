package lamp_shop.service;

import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lamp_shop.model.OrderLine;

@Component
public class EmailServiceImpl implements EmailService {

	@Autowired
	private JavaMailSender emailSender;

	public void sendMessage(String to, int orderId, List<OrderLine> orderLines) {

		MimeMessage message = emailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		try {
			helper.setSubject("Order confirmation");
			helper.setFrom("lampshop.exam@gmail.com");
			helper.setTo(to);

			boolean html = true;

			String result = orderLines.toString().replace("]],", "<br>");
			result = result.replace("]", "");
			result = result.replace("[", "");

			helper.setText("<h2>Thank you for ordering from Lamp shop</h2>" + "<h3>Order number: " + orderId + "</h3>"
					+ "<p>" + result + "</p>", html);
			emailSender.send(message);

		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void sendMessageWithAttachment(String to, String subject, String text, String pathToAttachment) {
		// TODO Auto-generated method stub
	}

}
