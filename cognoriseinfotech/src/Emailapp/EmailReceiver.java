package Emailapp;
import java.net.Authenticator;
import java.util.Properties;
public class EmailReceiver {
	public static void main(String[] args) {
		// Set up IMAP server properties
        String host = "imap.example.com";
        String port = "993";
        String username = "your_email@example.com";
        String password = "your_password";

        // Set IMAP server properties
        Properties props = new Properties();
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.imap.host", host);
        props.setProperty("mail.imap.port", port);

        // Create a Session object
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            // Connect to the email server
            Store store = session.getStore("imaps");
            store.connect(host, username, password);

            // Open the inbox folder
            Folder inbox = store.getFolder("INBOX");
            inbox.open(Folder.READ_ONLY);

            // Retrieve and process emails
            Message[] messages = inbox.getMessages();
            for (Message message : messages) {
                System.out.println("From: " + InternetAddress.toString(message.getFrom()));
                System.out.println("Subject: " + message.getSubject());
                System.out.println("Content: " + message.getContent());
            }

            // Close the folder and store
            inbox.close(false);
            store.close();
        } catch (MessagingException | IOException e)
        {
            e.printStackTrace();
        }
    }


	}


