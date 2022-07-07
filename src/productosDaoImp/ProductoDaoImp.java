package productosDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import interfaces.IProductoDao;
import models.Categoria;
import models.Producto;

public class ProductoDaoImp implements IProductoDao{
	private Statement stmt;
	private String sql;
	public ProductoDaoImp(Statement stmt) {
		this.stmt = stmt;
	}
	@Override
	public List<Producto> findAll() {
		List<Producto> lista = new ArrayList<Producto>();
		sql = "SELECT productos.*, categorias.id_categoria as idCategoria, categorias.nombre as nombreCategoria\r\n" + 
				"FROM productos\r\n" + 
				"INNER JOIN categorias ON \r\n" + 
				"productos.id_categoria = categorias.id_categoria";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Producto p = new Producto();
				p.setId(rs.getInt("id_producto"));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getDouble("precio"));
				// Categoria
				Categoria c = new Categoria();
				c.setId(rs.getInt("id_categoria"));
				c.setNombre(rs.getString("nombre"));
				// Guardo categoria en producto
				p.setCategoria(c);
				lista.add(p);
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener los registros de producto " + e);
		}
		return lista;
	}

	@Override
	public Producto getOne(int id) {
		// TODO Auto-generated method stub
		Producto p = null;
		sql = "SELECT productos.*, categorias.id_categoria as idCategoria, categorias.nombre as nombreCategoria\r\n" + 
				"FROM productos\r\n" + 
				"INNER JOIN categorias ON \r\n" + 
				"productos.id_categoria = categorias.id_categoria\r\n" + 
				"WHERE productos.id_producto=" + id;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				p = new Producto();
				p.setId(rs.getInt("id_producto"));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getDouble("precio"));
				// Categoria
				Categoria c = new Categoria();
				c.setId(rs.getInt("idCategoria"));
				c.setNombre(rs.getString("nombreCategoria"));
				// Guardo categoria en producto
				p.setCategoria(c);
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtene el producto " + e);
		}
		return p;
	}
	
	public void mostrar() {
		sql = "SELECT productos.*, categorias.id_categoria as idCategoria, categorias.nombre as nombreCategoria\r\n" + 
				"FROM productos\r\n" + 
				"INNER JOIN categorias ON \r\n" + 
				"productos.id_categoria = categorias.id_categoria";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Producto p = new Producto();
				p.setId(rs.getInt("id_producto"));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getDouble("precio"));
				// Categoria
				Categoria c = new Categoria();
				c.setId(rs.getInt("idCategoria"));
				c.setNombre(rs.getString("nombreCategoria"));
				// Guardo categoria en producto
				p.setCategoria(c);
				System.out.println(p.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener los registros de producto " + e);
		}
	}
	@Override
	public void save(Producto producto) {
		sql = "INSERT INTO productos VALUES (null, '" + producto.getNombre() + "'," + producto.getPrecio() + "," + producto.getCategoria().getId() + ")";
		try {
			stmt.executeUpdate(sql);
			System.out.println("Alta exitoso");
		} catch (SQLException e) {
			System.out.println("Error al guardar "+ e);
		}
		// TODO Auto-generated method stub
		
	}
	@Override
	public void update(Producto producto) {
		// TODO Auto-generated method stub
		sql = "UPDATE productos SET nombre='" + producto.getNombre() + "', precio=" + producto.getPrecio() + ", id_categoria=" + producto.getCategoria().getId() + " where id_producto=" + producto.getId();
		try {
			stmt.executeUpdate(sql);
			System.out.println("Modificación exitosa");
		} catch (SQLException e) {
			System.out.println("Error al modificar "+ e);
		}
	}
	@Override
	public void delete(int id) {
		sql = "delete from productos where id_producto= " + id;
		try {
			stmt.executeUpdate(sql);
			System.out.println("Eliminado exitosamente");
		} catch (SQLException e) {
			System.out.println("Error al eliminar "+ e);
		}
		// TODO Auto-generated method stub
		
	}
	
	public Producto getOneNombre(String nombre) {
		// TODO Auto-generated method stub
		Producto p = null;
		sql = "SELECT productos.*, categorias.id_categoria as idCategoria, categorias.nombre as nombreCategoria\r\n" + 
				"FROM productos\r\n" + 
				"INNER JOIN categorias ON \r\n" + 
				"productos.id_categoria = categorias.id_categoria\r\n" + 
				"WHERE productos.nombre='" + nombre + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				p = new Producto();
				p.setId(rs.getInt("id_producto"));
				p.setNombre(rs.getString("nombre"));
				p.setPrecio(rs.getDouble("precio"));
				// Categoria
				Categoria c = new Categoria();
				c.setId(rs.getInt("idCategoria"));
				c.setNombre(rs.getString("nombreCategoria"));
				// Guardo categoria en producto
				p.setCategoria(c);
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener el producto " + e);
		}
		return p;
	}
	
	
	
}
