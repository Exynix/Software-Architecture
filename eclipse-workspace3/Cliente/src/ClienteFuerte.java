import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Scanner;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import logica.GestionproductosRemote;
import logica.GestionusuariosRemote;
import prod.Carrito;
import prod.DetallesPedido;
import prod.DireccionEnvio;
import prod.Pedido;
import prod.Productos;
import logic.Usuario;

public class ClienteFuerte {

	public static void main(String[] args) {
	    String mensa = null;
	    Carrito carrito = new Carrito();
	    SendCola sendCola = new SendCola();
	    
	    Hashtable<String, String> enviroment = new Hashtable<>();
	    enviroment.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
	    enviroment.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
	    enviroment.put(Context.PROVIDER_URL, "http-remoting://localhost:8380");
	    InitialContext context;

	    try {
	         
	    	List<Productos> productos2 = new ArrayList<>();
	        productos2.add(new Productos(1,"zanahoria",20.0));
	        productos2.add(new Productos(1,"ponque",30.0));

	        // Parámetros para enviar el mensaje
	        String destinatario = "dani.guti2817@gmail.com";
	        String asunto = "Compra realizada correctamente!";

	        // Envío del mensaje
	        sendCola.enviarMensaje(destinatario, asunto, productos2);
	    	
	        context = new InitialContext(enviroment);
	        GestionusuariosRemote ejem = null;
	        ejem = (GestionusuariosRemote) context
	                .lookup("ejb:LogicaEAR/LogicaNegocioTIER/Gestionusuarios!logica.GestionusuariosRemote");
	        mensa = ejem.pagar();
	        System.out.print(mensa);
	        Usuario usuario = null;
	        if (ejem != null) {
	            int opcion;
	            Scanner scanner = new Scanner(System.in);

	            // Lógica de inicio de sesión
	            do {
	                System.out.print("Ingrese su nombre de usuario: ");
	                String username = scanner.nextLine();

	                System.out.print("Ingrese su contraseña: ");
	                String password = scanner.nextLine();

	                usuario = ejem.validarUsuario(username, password);
	                if (usuario != null) {
	                    break; // Salir del bucle si las credenciales son válidas
	                } else {
	                    System.out.println("Inicio de sesión incorrecto. Inténtelo nuevamente.");
	                }
	            } while (true); // Repetir el bucle hasta que las credenciales sean válidas

	            // Menú del usuario solo si la sesión se ha iniciado correctamente
	            do {
	                System.out.println("----- Menú de Usuario -----");
	                System.out.println("1. Agregar producto al carrito");
	                System.out.println("2. Ver carrito");
	                System.out.println("3. Realizar pedido");
	                System.out.println("4. Salir");
	                System.out.print("Seleccione una opción: ");
	                opcion = scanner.nextInt();
	                scanner.nextLine();
	                
	                GestionproductosRemote prodRemote = null;
	                
	                prodRemote = (GestionproductosRemote) context.lookup("ejb:LogicaEAR/LogicaNegocioTIER/Gestionproductos!logica.GestionproductosRemote");
	                List<Productos> productos = prodRemote.obtenerProductos();

	                switch (opcion) {
	                    case 1:
	                    	 System.out.println("----- Listado de Productos -----");
	                    	    for (Productos producto : productos) {
	                    	        System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre_prod());
	                    	        System.out.println("Nombre: " + producto.getNombre_prod());
	                    	        System.out.println("Precio: " + producto.getPrecio());
	                    	        System.out.println("Cantidad en Stock: " + producto.getCantidad_en_stock());
	                    	        System.out.println("---------------------------------");
	                    	    }

	                    	    System.out.print("Seleccione el ID del producto que desea agregar al carrito: ");
	                    	    int productId = scanner.nextInt();
	                    	    scanner.nextLine(); // Consumir el salto de línea

	                    	    // Verificar si el producto ya está en el carrito
	                    	    boolean productoYaEnCarrito = false;
	                    	    for (Productos producto : prodRemote.obtenerProductosEnCarrito(usuario)) {
	                    	        if (producto.getId() == productId) {
	                    	            productoYaEnCarrito = true;
	                    	            break;
	                    	        }
	                    	    }

	                    	    if (productoYaEnCarrito) {
	                    	        System.out.println("El producto ya está en el carrito. No se puede agregar más del mismo producto.");
	                    	    } else {
	                    	        // Producto no está en el carrito, se puede agregar
	                    	        Productos productoSeleccionado = null;
	                    	        for (Productos producto : productos) {
	                    	            if (producto.getId() == productId) {
	                    	                productoSeleccionado = producto;
	                    	                break;
	                    	            }
	                    	        }

	                    	        if (productoSeleccionado != null) {
	                    	            // Agregar el producto al carrito
	                    	            carrito.setUsuario(usuario);
	                    	            carrito.setProducto(productoSeleccionado);
	                    	            prodRemote.anadirProductoCarrito(carrito);
	                    	            System.out.println("Producto agregado al carrito.");
	                    	        } else {
	                    	            System.out.println("El ID del producto ingresado no es válido.");
	                    	        }
	                    	    }
	                    	    break;
	                    case 2:
	                    	
	                        List<Productos> productosEnCarrito = prodRemote.obtenerProductosEnCarrito(usuario);
	                        if (productosEnCarrito.isEmpty()) {
	                            System.out.println("El carrito está vacío.");
	                        } else {
	                            System.out.println("----- Productos en el Carrito -----");
	                            for (Productos producto : productosEnCarrito) {
	                                System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre_prod());
	                                System.out.println("Precio: " + producto.getPrecio());
	                                // Otros detalles del producto...
	                                System.out.println("----------------------------------");
	                            }
	                        }
	                        break;
	                    case 3:
	                    	
	                    	List<DireccionEnvio> direccionesUsu = prodRemote.obtenerDirecciones(usuario);
	                        // Si el usuario tiene direcciones de envío existentes, mostrarlas y permitirle elegir una
	                        if (!direccionesUsu.isEmpty()) {
	                            System.out.println("Direcciones de envío existentes:");
	                            for (int i = 0; i < direccionesUsu.size(); i++) {
	                                DireccionEnvio dir = direccionesUsu.get(i);
	                                System.out.println((i + 1) + ". " + dir.getDireccion() + ", " + dir.getCiudad());
	                            }
	                            System.out.print("Seleccione una dirección existente (1-" + direccionesUsu.size() + ") o ingrese 0 para agregar una nueva: ");
	                            int opcionDireccion = scanner.nextInt();
	                            scanner.nextLine(); // Consumir el salto de línea

	                            if (opcionDireccion > 0 && opcionDireccion <= direccionesUsu.size()) {
	                                // Usar la dirección existente seleccionada por el usuario
	                                DireccionEnvio direccionSeleccionada = direccionesUsu.get(opcionDireccion - 1);
	                                // Realizar el pedido usando la dirección seleccionada
	                                realizarPedido(usuario, direccionSeleccionada,context);

	                            } else {
	                                // Agregar una nueva dirección de envío
	                                System.out.print("Ingrese la dirección de envío: ");
	                                String direccionEnvio = scanner.nextLine();

	                                System.out.print("Ingrese la ciudad de envío: ");
	                                String ciudadEnvio = scanner.nextLine();

	                                DireccionEnvio direccion = new DireccionEnvio();
	                                direccion.setUsuario(usuario);
	                                direccion.setDireccion(direccionEnvio);
	                                direccion.setCiudad(ciudadEnvio);                     
	                                prodRemote.anadirDirEnvio(direccion);

	                                // Realizar el pedido usando la nueva dirección de envío
	                                realizarPedido(usuario, direccion,context );
	                            }
	                        } else {
	                            // Si el usuario no tiene direcciones de envío existentes, agregar una nueva directamente
	                            System.out.println("No tiene direcciones de envío existentes.");
	                            System.out.print("Ingrese la dirección de envío: ");
	                            String direccionEnvio = scanner.nextLine();

	                            System.out.print("Ingrese la ciudad de envío: ");
	                            String ciudadEnvio = scanner.nextLine();

	                            DireccionEnvio direccion = new DireccionEnvio();
	                            direccion.setUsuario(usuario);
	                            direccion.setDireccion(direccionEnvio);
	                            direccion.setCiudad(ciudadEnvio);                     
	                            prodRemote.anadirDirEnvio(direccion);

	                            // Realizar el pedido usando la nueva dirección de envío
	                            realizarPedido(usuario, direccion,context);
	                        }

	                        break;
	                    case 4:
	                        System.out.println("Saliendo del programa...");
	                        break;
	                    default:
	                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
	                }
	            } while (opcion != 4);
	        } 
	    } catch (NamingException e) {
	        e.printStackTrace();
	    }
	}

	private static void realizarPedido(Usuario usuario, DireccionEnvio direccion, InitialContext context) throws NamingException {

        GestionproductosRemote prodRemote = (GestionproductosRemote) context.lookup("ejb:LogicaEAR/LogicaNegocioTIER/Gestionproductos!logica.GestionproductosRemote");
        List<Productos> productosEnCarrito = prodRemote.obtenerProductosEnCarrito(usuario);
        
        
        Pedido pedido = new Pedido();
        pedido.setUsuario(usuario);
        pedido.setDireccionEnvio(direccion);
        pedido.setFecha(null);
        pedido.setHora(null);
        pedido.setEstado_pedido("pendiente");
        prodRemote.guardarPedido(pedido);
        
        for (Productos producto : productosEnCarrito) {
            // Crear un nuevo detalle de pedido para cada producto en el carrito
            DetallesPedido detallesPedido = new DetallesPedido();
            

            // Solicitar al usuario la cantidad deseada de este producto
            //System.out.print("Ingrese la cantidad deseada de '" + producto.getNombre_prod() + "': ");
            //Scanner scanner = new Scanner(System.in);
			//int cantidadDeseada = scanner.nextInt();
            //scanner.nextLine(); // Consumir el salto de línea
            detallesPedido.setCantidad(2);
            detallesPedido.setPrecio_unitario(producto.getPrecio()); // Precio unitario del producto
            // Restar la cantidad comprada del stock disponible del producto
            System.out.println("eee" + producto.getCantidad_en_stock());

            producto.setCantidad_en_stock(producto.getCantidad_en_stock() );
            // Guardar el detalle del pedido en la base de datos
            //prodRemote.guardarProducto(producto);
            detallesPedido.setPedido(pedido);
            detallesPedido.setProducto(producto);
           System.out.println("llego");
           
            prodRemote.guardarDetallesPedido(detallesPedido);
            
        }
       
        
	}

}
