
import java.util.List;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;

import jakarta.ejb.Stateless;
import jakarta.jms.ConnectionFactory;
import jakarta.jms.Destination;
import jakarta.jms.JMSContext;
import jakarta.jms.JMSProducer;
import prod.Productos;

/**
 * Session Bean implementation class SendCola
 */
@Stateless
public class SendCola  {
	String user = "daniel";
	String pass = "123";
	String CONNECTION_FACTORY = "jms/RemoteConnectionFactory";
	String DESTINATION = "jms/queue/CorreosCola";
	
	public void enviarMensaje(String destinatario, String asunto, List<Productos> productos) {

		try {
			Properties jmdiPrpiedades = new Properties();
			jmdiPrpiedades.put(Context.INITIAL_CONTEXT_FACTORY,
					"org.wildfly.naming.client.WildFlyInitialContextFactory");
			jmdiPrpiedades.put(Context.PROVIDER_URL, "http-remoting://localhost:8680");
			jmdiPrpiedades.put(Context.SECURITY_PRINCIPAL, user);
			jmdiPrpiedades.put(Context.SECURITY_CREDENTIALS, pass);
			jmdiPrpiedades.put("jboss.naming.client.ejb.context", "True");

			InitialContext ctx = new InitialContext(jmdiPrpiedades);

			ConnectionFactory cf = (ConnectionFactory) ctx.lookup(CONNECTION_FACTORY);
			Destination destination = (Destination) ctx.lookup(DESTINATION);
			JMSContext context = cf.createContext(user, pass);
			JMSProducer producer = context.createProducer();
			  String cuerpo = construirCuerpo(productos);
              String mensaje = "Para: " + destinatario + "\nAsunto: " + asunto + "\nCuerpo:\n" + cuerpo;
              System.out.print(mensaje);
            producer.send(destination, context.createTextMessage(mensaje));
			context.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	 private String construirCuerpo(List<Productos> productos) {
	        StringBuilder sb = new StringBuilder();
	        double total = 0;
	        sb.append("Detalles de los productos comprados:\n");
	        for (Productos producto : productos) {
	            sb.append("Nombre: ").append(producto.getNombre_prod()).append("\n");
	            sb.append("Precio: ").append(producto.getPrecio()).append("\n");
	            total += producto.getPrecio();
	            sb.append("\n");
	        }
	        sb.append("Total: ").append(total);
	        return sb.toString();
	    }

}