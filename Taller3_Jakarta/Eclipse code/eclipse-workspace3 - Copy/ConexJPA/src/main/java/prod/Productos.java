package prod;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity implementation class for Entity: Productos
 *
 */
@Entity
public class Productos implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nombre_prod;
	private Double precio;
	private Integer cantidad_en_stock;

	public Productos() {
		super();
	}
	public Productos(Integer id, String nombre_prod, Double precio) {
		super();
		this.id = id;
		this.nombre_prod = nombre_prod;
		this.precio = precio;
	}
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getNombre_prod() {
		return this.nombre_prod;
	}

	public void setNombre_prod(String nombre_prod) {
		this.nombre_prod = nombre_prod;
	}
	public Double getPrecio() {
		return this.precio;
	}

	public void setPrecio(Double precio) {
		this.precio = precio;
	}
	public Integer getCantidad_en_stock() {
		return this.cantidad_en_stock;
	}

	public void setCantidad_en_stock(Integer cantidad_en_stock) {
		this.cantidad_en_stock = cantidad_en_stock;
	}

}
