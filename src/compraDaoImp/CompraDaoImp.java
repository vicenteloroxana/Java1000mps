package compraDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interfaces.ICompraDao;
import models.Carrito;
import models.Categoria;
import models.Usuario;
import models.Compra;
import models.Producto;
import models.ProductoCarrito;

public class CompraDaoImp implements ICompraDao {
	private Statement stmt;
	private String sql;
	public CompraDaoImp(Statement stmt) {
		this.stmt = stmt;
	}
	public void autorizarCompra(int autorizado, int id_carr, int id_cliente) {
		sql = "INSERT INTO productos VALUES (null, '" + autorizado + "'," + id_carr + "," + id_cliente + ")";
		
		try {
			stmt.executeUpdate(sql);
			System.out.println("Alta exitoso");
		} catch (SQLException e) {
			System.out.println("Error al guardar "+ e);
		}
	}
	
	public void usuariosConCompra() {
		sql = "SELECT usuarios.nombre, usuarios.apellido\r\n" + 
				"FROM usuarios\r\n" + 
				"INNER JOIN compras ON \r\n" + 
				"usuarios.id_cliente = compras.id_cliente\r\n" +
				"WHERE compras.autorizado=1";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				// cliente
				Usuario cliente = new Usuario(rs.getString("nombre"), rs.getString("apellido"));
				
				System.out.println(cliente.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener los usuarios que realizaron una compra" + e);
		}
		
	}
	public void usuariosConProductos(String nombre) {
		sql = "SELECT productos.nombre, productos.precio\r\n" + 
				"FROM usuarios\r\n" + 
				"INNER JOIN compras ON \r\n" + 
				"usuarios.id_cliente = compras.id_cliente\r\n" +
				"INNER JOIN carrito_producto ON \r\n" + 
				"compras.id_carrito = carrito_producto.id_carrito\r\n" + 
				"JOIN productos ON productos.id_producto = carrito_producto.id_producto\r\n" +
				"WHERE usuarios.nombre='" + nombre + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				
				Producto p = new Producto(rs.getString("nombre"), rs.getDouble("precio"));
				
				
				System.out.println(p.mostrarProductos());
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener los productos del usuario " + e);
		}
		
	}
}
