package models;

import java.util.List;

public class Carrito {
	private int id;
	private String nombre;
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
	@Override
	public String toString() {
		return "Carrito [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
}
