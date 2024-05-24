package modelo;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import logic.Usuario;
import prod.Carrito;
import prod.DetallesPedido;
import prod.DireccionEnvio;
import prod.Pedido;
import prod.Productos;
@Stateless

public class Datosproducto implements DatosproductoRemote {

	@PersistenceContext
	private EntityManager entityManager;

	public Datosproducto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public List<Productos> obtenerProductos() {
		Query query = entityManager.createQuery("SELECT p FROM Productos p");
		@SuppressWarnings("unchecked")
		List<Productos> productos = query.getResultList();
		return productos;
	}
	@Override
	public void anadirProductoCarrito(Carrito carrito) {
		entityManager.persist(carrito);
	}

	@Override
	public List<Productos> obtenerProductosEnCarrito(Usuario usuario) {
	    Query query = entityManager.createQuery("SELECT c.producto FROM Carrito c WHERE c.usuario = :usuario");
	    query.setParameter("usuario", usuario);
	    @SuppressWarnings("unchecked")
	    List<Productos> productosEnCarrito = query.getResultList();
	    return productosEnCarrito;
	}

	@Override
	public void anadirDirEnvio(DireccionEnvio direccion) {
		entityManager.persist(direccion);
	}

	@Override
	public List<DireccionEnvio> obtenerDirecciones(Usuario user) {
		Query query = entityManager.createQuery("SELECT d FROM DireccionEnvio d WHERE d.usuario = :usuario");
        query.setParameter("usuario", user);
        @SuppressWarnings("unchecked")
        List<DireccionEnvio> direcciones = query.getResultList();
        return direcciones;
	}
	
	@Override
	public void guardarPedido(Pedido pedido) {
		entityManager.persist(pedido);
		entityManager.flush();

	}
	
	@Override
	public void guardarDetallesPedido(DetallesPedido detallesPedido) {
		System.out.println("lleasdasdsa ");
		entityManager.persist(detallesPedido);
		entityManager.flush();
	}
	
	@Override
	public void guardarProducto(Productos producto) {
		entityManager.merge(producto);
		entityManager.flush();

	}

}