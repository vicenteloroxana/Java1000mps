package carritoDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interfaces.ICarritoDao;
import models.Carrito;
import models.Categoria;
import models.Producto;
import models.ProductoCarrito;

public class CarritoDaoImp implements ICarritoDao{
	private Statement stmt;
	private String sql;
	public CarritoDaoImp(Statement stmt) {
		this.stmt = stmt;
	}
	public void seleccionarProductos(ProductoCarrito pc) {
		sql = "INSERT INTO carrito_producto VALUES (null, " + pc.getProducto().getId() + "," + pc.getCarrito().getId() + ")";
		try {
			stmt.executeUpdate(sql);
			System.out.println("Selección exitosa");
		} catch (SQLException e) {
			System.out.println("Error al seleccionar productos "+ e);
		}
		// TODO Auto-generated method stub
		
	}
	public void crear(String nombre) {
		sql = "INSERT INTO carritos VALUES (null, '" + nombre + "')";
		
		try {
			stmt.executeUpdate(sql);
			System.out.println("Alta exitoso");
		} catch (SQLException e) {
			System.out.println("Error al guardar "+ e);
		}
		// TODO Auto-generated method stub
		
	}
	public Carrito getOneNombre(String nombre) {
		// TODO Auto-generated method stub
		Carrito carrito = null;
		sql = "SELECT * FROM carritos\r\n" +  
				"WHERE carritos.nombre='" + nombre + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				carrito = new Carrito();
				carrito.setId(rs.getInt("id_carrito"));
				carrito.setNombre(rs.getString("nombre"));
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener el carrito " + e);
		}
		return carrito;
	}
	public void mostrarProductos(String nombre) {
		ProductoCarrito pCarrito = new ProductoCarrito();
		sql = "SELECT productos.id_producto, productos.nombre, productos.precio, carritos.nombre as nombreCarrito, carritos.id_carrito, categorias.nombre as nombreCateg, categorias.id_categoria\r\n" + 
				"FROM productos\r\n" + 
				"INNER JOIN categorias ON \r\n" + 
				"productos.id_categoria = categorias.id_categoria\r\n" +
				"INNER JOIN carrito_producto ON \r\n" + 
				"productos.id_producto = carrito_producto.id_producto\r\n" + 
				"JOIN carritos ON carritos.id_carrito = carrito_producto.id_carrito\r\n" +
				"WHERE carritos.nombre='" + nombre + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				Producto p = new Producto();
				p.setId(rs.getInt("id_producto"));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getDouble("precio"));
				// Carrito
				Carrito c = new Carrito();
				c.setId(rs.getInt("id_carrito"));
				c.setNombre(rs.getString("nombreCarrito"));
				// Categoria
				Categoria ca = new Categoria();
				ca.setId(rs.getInt("id_categoria"));
				ca.setNombre(rs.getString("nombreCateg"));
				// Guardo categoria en producto
				p.setCategoria(ca);
				// Guardo carrito en pCarrito
				pCarrito.setCarrito(c);
				pCarrito.setProducto(p);
				System.out.println(pCarrito.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener los productos seleccionados " + e);
		}
		
	}
}
