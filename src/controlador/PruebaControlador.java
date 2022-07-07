package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

import carritoDaoImp.CarritoDaoImp;
import categoriasDaoImp.CategoriaDaoImp;
import compraDaoImp.CompraDaoImp;
import conexion.Conexion;
import interfaces.ICarritoDao;
import interfaces.ICategoriaDao;
import interfaces.IUsuarioDao;
import interfaces.ICompraDao;
import interfaces.IProductoDao;
import models.Carrito;
import models.Categoria;
import models.Usuario;
import models.Producto;
import models.ProductoCarrito;
import productosDaoImp.ProductoDaoImp;
import usuarioDaoImp.UsuarioDaoImp;

public class PruebaControlador {
	public static int opcion;
	public static int opcionC;
	public static Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub	
		Connection conn = null;
		Statement stmt = null;
		try {
			// REGISTRAR EL DRIVER
			Class.forName(Conexion.JDBC_DRIVER);
			
			//ABRIR CONEXION
			System.out.println("Conectando");
			conn = DriverManager.getConnection(Conexion.DB_URL, Conexion.USER, Conexion.PASS);
			
			// EJECUTAR CONSULTA SQL
			System.out.println("Creando statement...");
			stmt = conn.createStatement();
			String sql;
			
			//PROBAR CONSULTAS
			System.out.println("****Menú****");
			// servicios
			IProductoDao productoServicio = new ProductoDaoImp(stmt);
			ICategoriaDao categoriaServicio = new CategoriaDaoImp(stmt);
			ICarritoDao carritoServicio = new CarritoDaoImp(stmt);
			ICompraDao compraServicio = new CompraDaoImp(stmt);
			IUsuarioDao clienteServicio = new UsuarioDaoImp(stmt);
			
			String respuesta;
			Carrito carr = null;

			do {
				menuPrincipal(); // menuPrincipal
				switch (opcion) {
					
				case 1:
					menuAdministracion();
					switch(opcion) {
					case 1:
						Usuario cliente = Registrarse();
						clienteServicio.crear(cliente);
						break;
					case 2:
						System.out.print("Ingrese su nombre: ");
						String nombreCliente = sc.next();
						System.out.print("Ingrese su contrasenia: ");
						String contraseniaCliente = sc.next();
						Usuario cl = clienteServicio.getOne(contraseniaCliente);
						if(cl != null && cl.getContrasenia() == contraseniaCliente && cl.getNombre() == nombreCliente) {
							System.out.println("Bienvenido/a "+  cl.getNombre());
						}
						else {
							System.out.println("Usuario no registrado");
						}
						break;
					case 3:
						// alta de producto
						System.out.println("Ingrese el id de una categoria: ");
						int categoria = sc.nextInt();
						Categoria cate = categoriaServicio.getOne(categoria);
						if(cate != null) {
							System.out.print("Ingrese el nombre: ");
							String nombre = sc.next();
							System.out.print("Ingrese el precio: ");
							double precio = sc.nextDouble();
							Producto pro = new Producto (nombre, precio, cate);
							productoServicio.save(pro);
							System.out.println(pro.toString());
						}else {
							System.out.println("Debe ingresar una categoria valida");
						}
						break;
					case 4:
						//modificacion de producto
						System.out.print("Ingrese el id del pruducto a modificar: ");
						int idProd = sc.nextInt();
						Producto p1 = productoServicio.getOne(idProd);
						if(p1 != null) {
							System.out.print("Ingrese el nombre: ");
							String nombre = sc.next();
							System.out.print("Ingrese el precio: ");
							double precio = sc.nextDouble();
							p1.setNombre(nombre);
							p1.setPrecio(precio);
							//modificar categoria
							System.out.print("Ingrese el id de una categoria: ");
							int c1 = sc.nextInt();
							Categoria c = categoriaServicio.getOne(c1);
							if(c != null) {
								p1.setCategoria(c);
							}else {
								System.out.println("No se encontro categoria");
							}
							productoServicio.update(p1);
							System.out.println(p1.toString());
							
						}else {
							System.out.println("no existe");
						}
						break;
					case 5:
						//Ver todos los usuarios que realizaron una compra
						compraServicio.usuariosConCompra();
						break;
					case 6:
						//-	Ver listado de productos seleccionados por el usuario
						//compraServicio.usuariosConProductos();
						System.out.print("Ingrese el nombre del usuario: ");
						String nombre = sc.next();
						compraServicio.usuariosConProductos(nombre);
						break;
					default:
						break;
							
					}
					break;
				case 2:
					menuCliente();
					switch(opcion) {
					case 1:
						Usuario cliente = Registrarse();
						clienteServicio.crear(cliente);
						break;
					case 2:
						System.out.print("Ingrese su nombre: ");
						String nombreCliente = sc.next();
						System.out.print("Ingrese su contrasenia: ");
						String contraseniaCliente = sc.next();
						Usuario cl = clienteServicio.getOne(contraseniaCliente);
						if(cl != null && cl.getContrasenia() == contraseniaCliente && cl.getNombre() == nombreCliente ) {
							System.out.println("Bienvenido/a "+  cl.getNombre());
						}
						else {
							System.out.println("Usuario no registrado");
						}
						break;
					case 3:
						System.out.print("Ingrese un nombre para su carrito: ");
						String nombre = sc.next();
						carritoServicio.crear(nombre);
						break;
					case 4:
						// seleccionar productos
						System.out.print("Ingrese el nombre de su carrito: ");
						String nombreCarrito = sc.next();
						carr = carritoServicio.getOneNombre(nombreCarrito);
						System.out.print("Ingrese el nombre del producto a agregar: ");
						String nombreP = sc.next();
						Producto prod = productoServicio.getOneNombre(nombreP);
						if(prod != null && carr != null) {
							
							ProductoCarrito pro = new ProductoCarrito (prod, carr);
							carritoServicio.seleccionarProductos(pro);
							System.out.println(pro.toString());
						}else {
							System.out.println("Debe ingresar un nombre valido");
						}
						break;
					case 5:
						//System.out.print("Ingrese el nombre de su carrito: ");
						//nombreC = sc.next();
						carritoServicio.mostrarProductos(carr.getNombre());
						
						break;
					case 6:
						System.out.print("Desea autorizar dicha compra en su carrito (si/(no): ");
						String autorizar = sc.next();
						int idCarrito = 0;
						Usuario c = null;
						if(autorizar.toUpperCase().equals("SI")) {
							System.out.print("Ingrese su contrasenia para confirmar: ");
							String contrasenia = sc.next();
							//busco al cliente
							c = clienteServicio.getOne(contrasenia);
							//id carrito
							idCarrito = carr.getId();
							// buscar carrito, la compra del cliente
							//autorizar compra
							compraServicio.autorizarCompra(1, idCarrito, c.getId());
						}else {
							compraServicio.autorizarCompra(0, idCarrito, c.getId());
							break;
						}
						break;
					
					 default: 
						break;
			         
					}
					break;
							
				default:
					break;
					
				}
				sc.nextLine();
				System.out.println("Desea continuar si o no: ");
				respuesta = sc.nextLine();
				
			} while(respuesta.toUpperCase().equals("SI"));
			
			
			//
			
			stmt.close();
			conn.close();

		} catch(Exception e) {
			System.out.println("Algun error al conectar... " + e);
			
		}
		System.out.println("Termino...");
	}
	
	public static void iniciarSesion() {
		
		
	}
	
	public static Usuario Registrarse() {
		System.out.print("Ingrese su nombre: ");
		String nombreCliente = sc.next();
		System.out.print("Ingrese su apellido: ");
		String apellidoCliente = sc.next();
		System.out.print("Ingrese su contrasenia: ");
		String contraseniaCliente = sc.next();
		Usuario cliente  = new Usuario(nombreCliente, apellidoCliente, contraseniaCliente);
		return cliente;
	}
	
	public static void menuPrincipal() {
		System.out.println("Menú principal");
		System.out.println("1- Administracion");
		System.out.println("2- Cliente");
		System.out.print("Ingrese una opción del menu principal: ");
		opcion = sc.nextInt();
	}
	
	public static void menuAdministracion() {
		System.out.println("Administración");
		System.out.println("1- Registrarse");
		System.out.println("2- Iniciar sesion");
		System.out.println("3- Alta de producto");
		System.out.println("4- Modificar producto");
		System.out.println("5- Ver todos los usuarios que realizaron una compra");
		System.out.println("6- Ver listado de productos seleccionados por el usuario");
		System.out.print("Ingrese una opción del menu principal: ");
		opcion = sc.nextInt();
	}

	/*public static void menuPrincipal() {
		System.out.println("Menú principal");
		System.out.println("1- Categorias");
		System.out.println("2- Productos");
		System.out.println("3- Cliente");
		System.out.print("Ingrese una opción del menu principal: ");
		opcion = sc.nextInt();
	}*/
	
	public static void menuCategorias() {
		System.out.println("Categorias");
		System.out.println("1- Mostrar todos");
		System.out.println("2- Seleccionar categoria por id");
		System.out.println("3- Alta");
		System.out.println("4- Modificar");
		System.out.println("5- Eliminar");
		System.out.println("6- Seleccionar categoria por nombre");
		System.out.print("Ingrese una opción: ");
		opcion = sc.nextInt();
		
		
	}
	
	public static void menuProductos() {
		System.out.println("Productos");
		System.out.println("1- Mostrar todos");
		System.out.println("2- Seleccionar producto por id");
		System.out.println("3- Alta");
		System.out.println("4- Modificar");
		System.out.println("5- Eliminar");
		System.out.println("6- Seleccionar producto por nombre");
		System.out.print("Ingrese una opción: ");
		opcion = sc.nextInt();
	}
	
	public static void menuCliente() {
		System.out.println("Cliente");
		System.out.println("1- Registrarse");
		System.out.println("2- Iniciar sesion");
		System.out.println("3- Crear carrito");
		System.out.println("4- Seleccionar productos");
		System.out.println("5- Ver listado de productos seleccionados");
		System.out.println("6- Autorizar la compra de los productos seleccionados");
		System.out.print("Ingrese una opción: ");
		opcion = sc.nextInt();
	}
}
