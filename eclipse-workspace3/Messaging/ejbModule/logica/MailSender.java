package logica;

import java.util.Properties;

import jakarta.ejb.Stateless;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;
@Stateless
public class MailSender {


    public void sendMail(String destinatario, String asunto, String cuerpo) {
        final String emailFrom = "danigutierritos2817@gmail.com";
        final String password = "uqyb ciga jcce opvo";
        Properties props = System.getProperties();
 	   props.put("mail.smtp.host", "smtp.gmail.com"); 
 	   props.put("mail.smtp.user", emailFrom);
 	   props.put("mail.smtp.clave", password);    
 	   props.put("mail.smtp.auth", "true");   
 	   props.put("mail.smtp.starttls.enable", "true"); 
 	   props.put("mail.smtp.port", "587");

 	   Session session = Session.getDefaultInstance(props);
 	   MimeMessage message = new MimeMessage(session);
        try {
        	 
            message.setFrom(new InternetAddress(emailFrom));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(destinatario));
            message.setSubject(asunto);
            message.setText(cuerpo);

            Transport transport = session.getTransport("smtp");
            transport.connect("smtp.gmail.com", emailFrom, password);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

            System.out.println("¡El correo electrónico se envió correctamente a " + destinatario + "!");
        } catch (MessagingException e) {
            System.err.println("Error al enviar el correo electrónico: " + e.getMessage());
            e.printStackTrace();
            // Puedes relanzar la excepción o manejarla de otra manera según tus necesidades
            throw new RuntimeException("Error al enviar el correo electrónico", e);
        }
    }
}

