package negocio;

import java.util.List;

import entidades.Coche;
import persistencia.CocheDaoMySql;
import persistenciaInterfaces.CocheDao;

public class GestorCoches {

	public boolean alta(Coche c){
		CocheDao cocheDao = new CocheDaoMySql();
		boolean alta = cocheDao.alta(c);
		return alta;
	}

	public boolean baja(String matricula){
		CocheDao cocheDao = new CocheDaoMySql();
		boolean baja = cocheDao.baja(matricula);
		return baja;
	}
	
	public boolean modificar(Coche c){
		CocheDao cocheDao = new CocheDaoMySql();
		boolean modificar = cocheDao.modificar(c);
		return modificar;
	}
	
	public Coche obtener(int id){
		CocheDao cocheDao = new CocheDaoMySql();
		Coche coche = cocheDao.obtener(id);
		return coche;
	}
	
	public List<Coche> listar(){
		CocheDao cocheDao = new CocheDaoMySql();
		List<Coche> listaCoches = cocheDao.listar();
		return listaCoches;
	}
	
	
}
