package interfaces;

import models.Carrito;
import models.ProductoCarrito;

public interface ICarritoDao {
	public void seleccionarProductos(ProductoCarrito pc);
	public void crear(String nombre);
	public Carrito getOneNombre(String nombre);
	public void mostrarProductos(String nombre);
}
