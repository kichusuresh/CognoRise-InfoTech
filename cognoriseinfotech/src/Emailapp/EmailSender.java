package Emailapp;
import java.util.Properties;
public class EmailSender {

	public static void main(String[] args) {
	
		// Set up SMTP server properties
        String host = "smtp.example.com";
        String port = "587";
        String username = "your_email@example.com";
        String password = "your_password";

        // Set SMTP server properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", port);

        // Create a Session object
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Create MimeMessage object
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("your_email@example.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("recipient@example.com"));
            message.setSubject("Testing Email");
            message.setText("This is a test email sent using JavaMail API.");

            // Send the email
            Transport.send(message);

            System.out.println("Email sent successfully!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
	}

}
