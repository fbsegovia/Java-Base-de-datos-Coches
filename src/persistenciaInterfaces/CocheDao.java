package persistenciaInterfaces;

import java.util.List;
import entidades.Coche;

public interface CocheDao {
	boolean alta(Coche c);
	boolean baja(String matricula);
	boolean modificar(Coche c);
	Coche obtener(int id);
	List<Coche> listar();
}
