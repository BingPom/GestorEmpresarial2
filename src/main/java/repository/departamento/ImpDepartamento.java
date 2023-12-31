package repository.departamento;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import dao.HibernateManager;
import jakarta.persistence.TypedQuery;
import model.Departamento;
import model.Empleado;

public class ImpDepartamento implements DepartamentoRepository {
	private final Logger logger = Logger.getLogger(ImpDepartamento.class.getName());

	@Override
	public Boolean create(Departamento entity) {
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
	public List<Departamento> findAll() {
		logger.info("findAll");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		TypedQuery<Departamento> query = manager.getManager().createNamedQuery("Departamento.findAll",
				Departamento.class);
		List<Departamento> list = query.getResultList();
		manager.close();

		return list;
	}

	@Override
	public Optional<Departamento> findById(Integer id) {
		logger.info("findById)");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();
		Optional<Departamento> departamento = Optional.ofNullable(manager.getManager().find(Departamento.class, id));
		manager.close();

		return departamento;
	}

	public List<Departamento> findByName(String str) {
		logger.info("findByName");
		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		TypedQuery<Departamento> query = manager.getManager()
				.createNamedQuery("Departamento.findByName", Departamento.class).setParameter("nombre", str);
		List<Departamento> list = query.getResultList();
		manager.close();

		return list;
	}

	@Override
	public Boolean update(Departamento entity) {
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
	public Boolean delete(Departamento entity) {
		logger.info("delete");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			manager.getTransaction().begin();
			entity = manager.getManager().find(Departamento.class, entity.getId());
			
			entity.removeAllEmpleados();
			entity.setJefe(null);
			
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

	public Boolean addEmpleado(Departamento d, Empleado e) {
		logger.info("add empleado");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			manager.getTransaction().begin();

			// recuperamos de la base de datos
			d = manager.getManager().find(Departamento.class, d.getId());
			e = manager.getManager().find(Empleado.class, e.getId());
			
			// ahora ya hacemos cambios
			if (!d.addEmpleado(e))
				return false;
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

	public Boolean removeEmpleado(Departamento d, Empleado e) {
		logger.info("remove empleado");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			manager.getTransaction().begin();
			
			// recuperamos de la base de datos
			d = manager.getManager().find(Departamento.class, d.getId());
			e = manager.getManager().find(Empleado.class, e.getId());
			
			if (!d.removeEmpleado(e)) 
				return false;
			
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
