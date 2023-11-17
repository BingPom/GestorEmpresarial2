package repository.proyecto;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.HibernateManager;
import jakarta.persistence.TypedQuery;
import model.Empleado;
import model.Proyecto;
import repository.empleado.ImpEmpleado;

public class ImpProyecto implements ProyectoRepository {
	private final Logger logger = Logger.getLogger(ImpEmpleado.class.getName());

	@Override
	public Boolean create(Proyecto entity) {
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
	public List<Proyecto> findAll() {
		logger.info("findAll");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		TypedQuery<Proyecto> query = manager.getManager().createNamedQuery("Proyecto.findAll", Proyecto.class);
		List<Proyecto> list = query.getResultList();
		manager.close();

		return list;
	}

	@Override
	public Optional<Proyecto> findById(Integer id) {
		logger.info("findById)");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();
		Optional<Proyecto> proyecto = Optional.ofNullable(manager.getManager().find(Proyecto.class, id));
		manager.close();

		return proyecto;
	}

	@Override
	public Boolean update(Proyecto entity) {
		logger.info("create");
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
	public Boolean delete(Proyecto entity) {
		logger.info("delete");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			manager.getTransaction().begin();
			// entity = manager.getManager().find(Proyecto.class, entity.getId());
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

}
