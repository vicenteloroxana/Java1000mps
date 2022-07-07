package usuarioDaoImp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import interfaces.IUsuarioDao;
import models.Categoria;
import models.Usuario;
import models.Producto;

public class UsuarioDaoImp implements IUsuarioDao{
	private Statement stmt;
	private String sql;
	public UsuarioDaoImp(Statement stmt) {
		this.stmt = stmt;
	}
	@Override
	public void crear(Usuario c) {
		//sql = "INSERT INTO productos VALUES (null, '" + producto.getNombre() + "'," + producto.getPrecio() + "," + producto.getCategoria().getId() + ")";
		sql = "INSERT INTO usuarios VALUES (null, '" + c.getNombre() + "'," + c.getApellido() + "," + c.getContrasenia() + ")";
		try {
			stmt.executeUpdate(sql);
			System.out.println("Alta exitoso");
		} catch (SQLException e) {
			System.out.println("Error al guardar "+ e);
		}
		// TODO Auto-generated method stub
		
	}
	
	public Usuario getOne(String contrasenia) {
		// TODO Auto-generated method stub
		Usuario c = null;
		sql = "SELECT *\r\n" + 
				"FROM usuarios\r\n" + 
				"WHERE usuarios.contrasenia='" + contrasenia + "'";
		try {
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next()) {
				c.setId(rs.getInt("id_cliente"));
				c.setNombre(rs.getString("nombre"));
				c.setApellido(rs.getString("apellido"));
				c.setContrasenia(rs.getString("contrasenia"));
				
				
			}
			rs.close();
		} catch (SQLException e) {
			System.out.println("No se pudo obtener el cliente " + e);
		}
		return c;
	}
}
