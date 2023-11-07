package repository.empleado;

import java.util.List;
import java.util.Optional;

import dao.HibernateManager;
import model.Empleado;

public class ImpEmpleado implements EmpleadoRepository {

	@Override
	public Boolean create(Empleado entity) {
		HibernateManager hbManager = HibernateManager.getInstance();
		hbManager.open();
		
		return false;
	}

	@Override
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Empleado> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Boolean update(Empleado entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean delete(Empleado entity) {
		// TODO Auto-generated method stub
		return false;
	}

}
