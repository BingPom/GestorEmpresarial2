package controller;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import model.Departamento;
import repository.departamento.ImpDepartamento;
import view.DepartamentosView;

public class DepartamentoController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final ImpDepartamento repo;
	
	public DepartamentoController() {
		repo = new ImpDepartamento();
	}

	public void menu() {
		while (true) {
			int opt = DepartamentosView.getOption();
			switch (opt) {
			case 1:
				getById();
				break;
			case 2:
				getByStartsName();
				break;
			case 3:
				getAll();
				break;
			case 4:
				create();
				break;
			case 5:
				update();
				break;
			case 6:
				delete();
				break;
			case 7:
				return;
			default:
			}
		}
	}

	private void getByStartsName() {
		String inicio = DepartamentosView.buscarPorInicioDelNombre();
		logger.info("Obteniendo Departamentos que empiezan por " + inicio);
		List<Departamento> list = repo.findByName(inicio + "%");
		DepartamentosView.mostrar(list);
	}

	private void getById() {
		Integer id = DepartamentosView.buscarPorCodigo();
		logger.info("Obteniendo Departamento con id: " + id);
		Departamento entity = repo.findById(id).stream().collect(Collectors.toList()).get(0);
//		entity.ifPresent(DepartamentosView::mostrar);
//		if (entity != null) {
//			DepartamentosView.mostrar(entity, dao.getEmpleados(id));
//		}
	}

	private void getAll() {
		logger.info("Obteniendo Departamentos");
		List<Departamento> list = repo.findAll();
		DepartamentosView.mostrar(list);
	}

	private void create() {
		logger.info("Creando Departamento");
		Departamento entity = DepartamentosView.add();
		DepartamentosView.result(repo.create(entity) ? "Añadido" : "No se ha podido añadir");
	}

	private void update() {
		boolean updated = false;
		Integer id = DepartamentosView.buscarPorCodigo();
		logger.info("Actualizando Departamento con id: " + id);
		Departamento entity = repo.findById(id).stream().collect(Collectors.toList()).get(0);
		Departamento d = null;
		if (entity != null) {
			d = DepartamentosView.modificar(entity);
			updated = repo.update(d);
		}
		DepartamentosView.result(updated ? "Modificado" : "No se ha podido modificar");
	}

	private void delete() {
		Integer id = DepartamentosView.buscarPorCodigo();
		logger.info("Eliminando Departamento con id: " + id);
		Departamento entity = repo.findById(id).stream().collect(Collectors.toList()).get(0);
		if (entity != null)
			DepartamentosView.result(repo.delete(entity) ? "Borrado" : "No se ha podido borrar");
	}
}
