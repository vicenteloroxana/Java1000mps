package categoriasDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interfaces.ICategoriaDao;
import models.Categoria;
import models.Producto;

public class CategoriaDaoImp implements ICategoriaDao{
	private Statement stmt;
	private String sql;
	public CategoriaDaoImp(Statement stmt) {
		this.stmt = stmt;
	}
	public Categoria getOne(int id) {
		// TODO Auto-generated method stub
		Categoria c = null;
		sql = "SELECT * FROM categorias\r\n" +  
				"WHERE categorias.id_categoria=" + id;
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				c = new Categoria();
				c.setId(rs.getInt("id_categoria"));
				c.setNombre(rs.getString("nombre"));
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener la categorias " + e);
		}
		return c;
	}
	public void mostrar() {
		sql = "SELECT * FROM categorias";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				Categoria c = new Categoria();
				c.setId(rs.getInt("id_categoria"));
				c.setNombre(rs.getString("nombre"));
				
				System.out.println(c.toString());
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener los registros de categorias " + e);
		}
}
}