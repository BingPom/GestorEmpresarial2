package repository.empleado;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.HibernateManager;
import jakarta.persistence.TypedQuery;
import model.Departamento;
import model.Empleado;

public class ImpEmpleado implements EmpleadoRepository {
	private final Logger logger = Logger.getLogger(ImpEmpleado.class.getName());

	@Override
	public Boolean create(Empleado entity) {
		logger.info("create()");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();
		manager.getTransaction().begin();
		try {
			manager.getManager().merge(entity);
			manager.getTransaction().commit();
			manager.close();

			return true;
		} catch (Exception e) {
			// taca
		} finally {
			// si sigue activa es porque ha fallado algo, asi que rollback
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
		}

		return false;
	}

	@Override
	public List<Empleado> findAll() {
		logger.info("findAll");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		TypedQuery<Empleado> query = manager.getManager().createNamedQuery("Empleado.findAll", Empleado.class);
		List<Empleado> list = query.getResultList();
		manager.close();

		return list;
	}

	@Override
	public Optional<Empleado> findById(Integer id) {
		logger.info("findById)");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();
		Optional<Empleado> empleado = Optional.ofNullable(manager.getManager().find(Empleado.class, id));
		manager.close();

		return empleado;
	}

	public List<Empleado> findByName(String str) {
		logger.info("findByName");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		TypedQuery<Empleado> query = manager.getManager().createNamedQuery("Empleado.findAll", Empleado.class);
		List<Empleado> list = query.getResultList();
		manager.close();

		return list;
	}
	
	public List<Empleado> findByName(String str) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean update(Empleado entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean delete(Empleado entity) {
		logger.info("delete");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			manager.getTransaction().begin();
			// entity = manager.getManager().find(Empleado.class, entity.getId());
			manager.getManager().remove(entity);
			manager.getTransaction().commit();
			manager.close();

			return true;
		} catch (Exception e) {
			// taca
		} finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
		}
		return false;
	}

}
