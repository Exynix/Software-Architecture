package logica;

import java.util.List;

import jakarta.ejb.ActivationConfigProperty;
import jakarta.ejb.EJB;
import jakarta.ejb.MessageDriven;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import jakarta.mail.MessagingException;
import prod.Productos;

/**
 * Message-Driven Bean implementation class for: ConsumirCola
 */
@MessageDriven(
	    activationConfig = {
	        @ActivationConfigProperty(
	            propertyName = "destinationLookup",
	            propertyValue = "java:jboss/exported/jms/queue/CorreosCola"
	        )
	    }
	)
public class ConsumirCola implements MessageListener {

	@EJB
	private MailSender mailSender;


	public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                ObjectMessage objectMessage = (ObjectMessage) message;

                List<Productos> products = (List<Productos>) objectMessage.getObject();

                StringBuilder sb = new StringBuilder();
                sb.append("Tu lista de productos comprados son:\n\n");
                sb.append("Lista de productos:\n");
                double total = 0;
                for (Productos product : products) {
                    sb.append(product.getNombre_prod())
                      .append(" - ")
                      .append(product.getPrecio())
                      .append("\n");
                    total += product.getPrecio();
                }
                sb.append("\n\nValor total: ").append(total);
                String body = sb.toString();
                System.out.print(body);
                mailSender.sendMail("dani.guti2817@gmail.com", "Compra realizada correctamente!", body);
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }

}
