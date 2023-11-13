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
			manager.getManager().merge(entity);
			manager.getTransaction().commit();
			manager.close();
			return true;
		} catch (Exception e) {
			// TODO
		} finally {
			if (manager.getTransaction().isActive())
				manager.getTransaction().rollback();
		}

		return false;
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

		TypedQuery<Departamento> query = manager.getManager().createNamedQuery("Departamento.findByName",
				Departamento.class);
		List<Departamento> list = query.getResultList();
		manager.close();

		return list;
	}

	@Override
	public Boolean update(Departamento entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean delete(Departamento entity) {
		logger.info("delete");

		HibernateManager manager = HibernateManager.getInstance();
		manager.open();

		try {
			manager.getTransaction().begin();
			// entity = manager.getManager().find(Departamento.class, entity.getId());
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
