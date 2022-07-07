package models;

import java.util.List;

public class Usuario extends Persona{
	private int id;
	private String contrasenia;
	public Usuario(String nombre, String apellido,  String contrasenia) {
		super(nombre, apellido);
		this.contrasenia = contrasenia;
	}
	
	public Usuario(String nombre, String apellido) {
		super(nombre, apellido);
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContrasenia() {
		return contrasenia;
	}
	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
	
	
}
