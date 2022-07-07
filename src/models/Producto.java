package models;

public class Producto {
	private int id;
	private String nombre;
	private double precio;
	private Categoria categoria;
	
	public Producto( String nombre, double precio, Categoria categoria) {
		this.nombre = nombre;
		this.precio = precio;
		this.categoria = categoria;
	}
	public Producto( String nombre, double precio) {
		
		this.nombre = nombre;
		this.precio = precio;
		
	}
	public Producto() {
		this.categoria = new Categoria();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public String toString() {
		return "Producto [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", categoria=" + categoria + "]";
	}
	public String mostrarProductos() {
		return "Producto [ nombre=" + nombre + ", precio=" + precio + "]";
	}
	
	
	
	
}
