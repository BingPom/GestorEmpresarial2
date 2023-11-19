package repository.empleado;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.HibernateManager;
import jakarta.persistence.TypedQuery;
import model.Empleado;
import model.Proyecto;

public class ImpEmpleado implements EmpleadoRepository {
	private final Logger logger = Logger.getLogger(ImpEmpleado.class.getName());

	@Override
	public Boolean create(Empleado entity) {
		logger.info("create");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();
		manager.getTransaction().begin();

		try {
			manager.getManager().persist(entity);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// si sigue activa es porque ha fallado algo, asi que rollback
			if (manager.getTransaction() != null && manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			System.err.println("Error al agregar entidad " + e.getMessage());
			return false;
		} finally {
			manager.close();
		}
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
		Empleado empleado = manager.getManager().find(Empleado.class, id);
		manager.close();

		return Optional.ofNullable(empleado);
	}

	public List<Empleado> findByName(String str) {
		logger.info("findByName");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		TypedQuery<Empleado> query = manager.getManager().createNamedQuery("Empleado.findByName", Empleado.class)
				.setParameter("nombre", str);
		List<Empleado> list = query.getResultList();
		manager.close();

		return list;
	}

	@Override
	public Boolean update(Empleado entity) {
		logger.info("update");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();
		manager.getTransaction().begin();

		try {
			manager.getManager().merge(entity);
			manager.getTransaction().commit();
			return true;
		} catch (Exception e) {
			// si sigue activa es porque ha fallado algo, asi que rollback
			if (manager.getTransaction() != null && manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			System.err.println("Error al actualizar la entidad " + e.getMessage());
			return false;
		} finally {
			manager.close();
		}
	}

	@Override
	public Boolean delete(Empleado entity) {
		logger.info("delete");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			manager.getTransaction().begin();
			entity = manager.getManager().find(Empleado.class, entity.getId());
			manager.getManager().remove(entity);
			manager.getTransaction().commit();

			return true;
		} catch (Exception e) {
			if (manager.getTransaction() != null && manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	public Boolean addProyecto(Empleado e, Proyecto p) {
		logger.info("add proyecto");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			e.addProyecto(p);
			manager.getTransaction().commit();
			return true;
		} catch (Exception f) {
			if (manager.getTransaction() != null && manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}
	}

	public Boolean removeProyecto(Empleado e, Proyecto p) {
		logger.info("remove proyecto");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			e.removeEmpleado(p);
			manager.getTransaction().commit();
			return true;
		} catch (Exception f) {
			if (manager.getTransaction() != null && manager.getTransaction().isActive())
				manager.getTransaction().rollback();
			return false;
		} finally {
			manager.close();
		}

	}
}
