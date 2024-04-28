package modelo;

import java.util.List;

import jakarta.ejb.Remote;
import logic.Usuario;
import prod.Carrito;
import prod.DetallesPedido;
import prod.DireccionEnvio;
import prod.Pedido;
import prod.Productos;

@Remote
public interface DatosproductoRemote {
	
	List<Productos> obtenerProductos();
	void anadirProductoCarrito(Carrito carrito) ;
	List<Productos> obtenerProductosEnCarrito(Usuario usuario);
	void anadirDirEnvio(DireccionEnvio direccion);
	List<DireccionEnvio> obtenerDirecciones(Usuario user);
	void guardarPedido(Pedido pedido);
	void guardarDetallesPedido(DetallesPedido detallesPedido);
	void guardarProducto(Productos producto);

}
