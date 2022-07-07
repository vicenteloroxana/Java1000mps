package models;

import java.util.List;

public class ProductoCarrito {
	//private List<Producto> productos;
	//private List<Carrito> carritos;
	private Producto producto;
	private Carrito carrito;
	
	public ProductoCarrito() {};
	public ProductoCarrito(Producto producto, Carrito carrito) {
		
		this.producto = producto;
		this.carrito = carrito;
	}
	public Producto getProducto() {
		return producto;
	}
	public void setProducto(Producto producto) {
		this.producto = producto;
	}
	public Carrito getCarrito() {
		return carrito;
	}
	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	@Override
	public String toString() {
		return "ProductoCarrito [producto=" + producto + ", carrito=" + carrito + "]";
	}
	
	
	
}
