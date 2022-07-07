package interfaces;

import models.Categoria;

public interface ICategoriaDao {
	public void mostrar();
	public Categoria getOne(int id);
}
