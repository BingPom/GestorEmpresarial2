package repository.proyecto;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.HibernateManager;
import model.Empleado;
import model.Proyecto;
import repository.departamento.ImpDepartamento;

public class ImpProyecto implements ProyectoRepository {
	private final Logger logger = Logger.getLogger(ImpDepartamento.class.getName());
	
	@Override
	public Boolean create(Proyecto entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Proyecto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Proyecto> findById(Integer id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Boolean update(Proyecto entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean delete(Proyecto entity) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public Boolean addEmpleado(Proyecto p, Empleado e) {
		logger.info("add empleado");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			return p.addEmpleado(e);
		} catch (Exception f) {
			if (manager.getTransaction() != null && manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}
	
	public Boolean removeEmpleado(Proyecto p, Empleado e) {
		logger.info("remove empleado");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			return p.removeEmpleado(e);
		} catch (Exception f) {
			if (manager.getTransaction() != null && manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}

	}

}
