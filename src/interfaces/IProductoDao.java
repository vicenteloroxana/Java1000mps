package interfaces;

import java.util.List;

import models.Producto;

public interface IProductoDao {
	public List<Producto> findAll();
	public Producto getOne(int id);
	public Producto getOneNombre(String nombre);
	public void mostrar();
	public void save(Producto producto);
	public void update(Producto producto);
	public void delete(int id);
}
