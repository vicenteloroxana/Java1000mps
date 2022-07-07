package interfaces;

import models.Compra;

public interface ICompraDao {
	public void autorizarCompra(int autorizado, int id_carr, int id_cliente);
	public void usuariosConCompra();
	public void usuariosConProductos(String nombre);
	
}
