package interfaces;

import models.Usuario;

public interface IUsuarioDao {
	public void crear(Usuario c);
	public Usuario getOne(String contrasenia);
}
