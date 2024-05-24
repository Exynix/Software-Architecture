package logica;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import jakarta.ejb.Stateless;
import logic.Usuario;
import modelo.DatosproductoRemote;
import prod.Carrito;
import prod.DetallesPedido;
import prod.DireccionEnvio;
import prod.Pedido;
import prod.Productos;

@Stateless
public class Gestionproductos implements GestionproductosRemote {
	
	
	
	DatosproductoRemote ejem = null;
	
    public Gestionproductos() {
    	
        Hashtable<String,String>enviroment=new Hashtable<>();
		enviroment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
		enviroment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
		enviroment.put(Context.PROVIDER_URL, "http-remoting://localhost:8480");
		enviroment.put(Context.SECURITY_PRINCIPAL, "daniel");
		enviroment.put(Context.SECURITY_CREDENTIALS, "123");
     
        try {
            Context context = new InitialContext(enviroment);
            ejem = (DatosproductoRemote) context.lookup("ejb:DataEAR/DatosTIER/Datosproducto!modelo.DatosproductoRemote");

        } catch (NamingException e) {
            e.printStackTrace();
        }
    }

	@Override
	public List<Productos> obtenerProductos() {
		return ejem.obtenerProductos();
	}

	@Override
	public void anadirProductoCarrito(Carrito carrito) {
		ejem.anadirProductoCarrito(carrito);
	}

	@Override
	public List<Productos> obtenerProductosEnCarrito(Usuario usuario) {
		return ejem.obtenerProductosEnCarrito(usuario);
	}

	@Override
	public void anadirDirEnvio(DireccionEnvio direccion) {
		ejem.anadirDirEnvio(direccion);
	}

	@Override
	public List<DireccionEnvio> obtenerDirecciones(Usuario user) {
		return ejem.obtenerDirecciones(user);
	}

	@Override
	public void guardarPedido(Pedido pedido) {
		ejem.guardarPedido(pedido);
	}

	@Override
	public void guardarDetallesPedido(DetallesPedido detallesPedido) {
		System.out.println("eewerewrewr ");
		ejem.guardarDetallesPedido(detallesPedido);
	}

	@Override
	public void guardarProducto(Productos producto) {
		// TODO Auto-generated method stub
		System.out.println("sadsadasd ");

		ejem.guardarProducto(producto);
	}

}
