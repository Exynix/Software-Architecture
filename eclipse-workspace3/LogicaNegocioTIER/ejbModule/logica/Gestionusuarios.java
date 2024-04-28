package logica;

import java.util.Hashtable;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.ejb.Stateless;
import modelo.DatosusuarioRemote;
import logic.Usuario;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;


@Stateless
public class Gestionusuarios implements GestionusuariosRemote {

    
	
	DatosusuarioRemote ejem = null;
	
    public Gestionusuarios() {
    	
        Hashtable<String,String>enviroment=new Hashtable<>();
		enviroment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		enviroment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		enviroment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");
		enviroment.put(Context.SECURITY_PRINCIPAL, "daniel");
		enviroment.put(Context.SECURITY_CREDENTIALS, "123");
     
        try {
            Context context = new InitialContext(enviroment);
            ejem = (DatosusuarioRemote) context.lookup("ejb:DataEAR/DatosTIER/Datosusuario!modelo.DatosusuarioRemote");
        	System.out.println("acaaaN");

        } catch (NamingException e) {
        	System.out.println("acaaa");
            e.printStackTrace();
        }
    }

    @Override
	public Usuario validarUsuario(String userName, String password) {
    	return ejem.validarUsuario(userName, password);
	}
    
    @Override
    public String pagar() {
    	
    	 final String BASE_URL = "http://127.0.0.1:8480/Taller2_Jakarta_EmuladorPasarelaPagos-1.0-SNAPSHOT/api/payments";
    	        // Create a new JAX-RS client
    	        Client client = ClientBuilder.newClient();

    	        // Target the specific URL
    	        WebTarget target = client.target(BASE_URL);

    	        // Create payment data
    	        PaymentData paymentData = new PaymentData("4242424242424242", "John Doe", "12", "2025", "123", 99.99);
    	        // Make the POST request
    	        Response response = target.request(MediaType.APPLICATION_JSON)
    	                                   .post(Entity.entity(paymentData, MediaType.APPLICATION_JSON));
    	        // Check the response and return result
    	        if (response.getStatus() == Response.Status.OK.getStatusCode()) {
    	            return response.readEntity(String.class);
    	        } else {
    	            return "Failed: " + response.getStatus();
    	        }
    }

	
    
    

}
