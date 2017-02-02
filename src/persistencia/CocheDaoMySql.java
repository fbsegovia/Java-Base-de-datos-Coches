package persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entidades.Coche;
import persistenciaInterfaces.CocheDao;

public class CocheDaoMySql implements CocheDao {

	private Connection conexion;
	
	/**
	 * Método que comprueba si se ha podido establecer una conexión con los driver de MySql.
	 */
	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Driver cargado");
		} catch (ClassNotFoundException e) {
			System.out.println("Driver no cargado");
			e.printStackTrace();
		}
	}
	
	/**
	 * Método que conecta con la base de datos de MySql. 
	 * @return Devuelve verdadero o falso en funcion de si se ha podido o no conectar.
	 */
	public boolean abrirConexion(){
		String url = "jdbc:mysql://localhost:3306/prueba";
		String usuario = "root";
		String password = "telefonica";
		try {
			conexion = DriverManager.getConnection(url,usuario,password);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Método que cierra la conexion con la base de datos de MySql. 
	 * @return Devuelve verdadero o falso en funcion de si no se ha podido o no conectar.
	 */
	public boolean cerrarConexion(){
		try {
			conexion.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	/**
	 * Método que incorpora los datos de un coche a la tabla coches en la base de datos prueba. 
	 * @return Devuelve como true o false si se han podido introducir los datos correctamente en la tabla.
	 */

	@Override
	public boolean alta(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean alta = true;
		String query = "insert into coches (MATRICULA,MARCA,MODELO,KILOMETROS) values (?,?,?,?)";
		try {
				PreparedStatement ps = conexion.prepareStatement(query);
				ps.setString(1,c.getMatricula());
				ps.setString(2, c.getMarca());
				ps.setString(3, c.getModelo());
				ps.setInt(4, c.getKilometros());
				int numeroFilasAfectadas = ps.executeUpdate();
				if(numeroFilasAfectadas == 0){
					alta = false;
				}else{
					alta = true;
				}
			} catch (SQLException e) {
				System.out.println("alta -> Error al insertar: "+c);
				alta=false;
				e.printStackTrace();
			}finally{
				cerrarConexion();
			}
		return alta;
	}
	
	/**
	 * Método que elimina los datos de un coche de la tabla coches en la base de datos prueba a traves de una "MATRICULA". 
	 * @return Devuelve como true o false si se han podido eliminar los datos correctamente en la tabla.
	 */

	@Override
	public boolean baja(String matricula) {
		if(!abrirConexion()){
			return false;
		}
		boolean borrado = true;
		String query = "delete from coches where matricula = ?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1,matricula);
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0){
				borrado = false;
			}else{
				borrado = true;
			}
		} catch (SQLException e) {
			System.out.println("baja -> No se ha podido dar de baja el coche con la matricula: "+matricula);
			e.printStackTrace();
			borrado= false;
		}finally {
			cerrarConexion();
		}
		return borrado;
	}
	
	/**
	 * Método que modifica los datos de un coche de la tabla coches en la base de datos prueba a traves de un "ID". 
	 * @return Devuelve como true o false si se han podido modificar los datos correctamente en la tabla.
	 */

	@Override
	public boolean modificar(Coche c) {
		if(!abrirConexion()){
			return false;
		}
		boolean modificado = true;
		String query = "update coches set MATRICULA=?, MARCA=?, MODELO=?, KILOMETROS=? where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setString(1, c.getMatricula());
			ps.setString(2, c.getMarca());
			ps.setString(3, c.getModelo());
			ps.setInt(4, c.getKilometros());
			ps.setInt(5, c.getId());
			int numeroFilasAfectadas = ps.executeUpdate();
			if(numeroFilasAfectadas == 0){
				modificado = false;
			}else{
				modificado = true;
			}
		} catch (SQLException e) {
			System.out.println("modificar -> error al modificar el coche: "+c);
			e.printStackTrace();
			modificado= false;
		}finally {
			cerrarConexion();
		}
		return modificado;
	}
	
	/**
	 * Método que muestra los datos de un coche de la tabla coches en la base de datos prueba a traves de un "ID". 
	 * @return Devuelve los datos delcoche incluidos en la tabla Coches.
	 */

	@Override
	public Coche obtener(int id) {
		if(!abrirConexion()){
			return null;
		}
		Coche coche = null;
		String query = "select ID,MATRICULA,MARCA,MODELO,KILOMETROS from coches where id=?";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.setInt(1, id);
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setKilometros(rs.getInt(5));
			}
		} catch (SQLException e) {
			System.out.println("obtener -> error al obtener el coche con el id "+id);
			e.printStackTrace();
			coche = null;
		}finally {
			cerrarConexion();
		}
		return coche;
	}
	
	/**
	 * Método que muestra los datos de todos los coches de la tabla coches en la base de datos prueba. 
	 * @return Devuelve los datos de los coches incluidos en la tabla Coches.
	 */

	@Override
	public List<Coche> listar() {
		if(!abrirConexion()){
			return null;
		}
		List<Coche> listaCoches = new ArrayList<>();
		String query = "select ID,MATRICULA,MARCA,MODELO,KILOMETROS from coches";
		try {
			PreparedStatement ps = conexion.prepareStatement(query);
			ps.executeQuery();
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				Coche coche = new Coche();
				coche.setId(rs.getInt(1));
				coche.setMatricula(rs.getString(2));
				coche.setMarca(rs.getString(3));
				coche.setModelo(rs.getString(4));
				coche.setKilometros(rs.getInt(5));
				listaCoches.add(coche);
			}
		} catch (SQLException e) {
			System.out.println("listar -> error al obtener los coches ");
			e.printStackTrace();
		}finally {
			cerrarConexion();
		}
		return listaCoches;
	}
	
	
}
