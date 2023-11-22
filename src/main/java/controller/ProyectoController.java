package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import model.Proyecto;
import repository.proyecto.ImpProyecto;
import view.ProyectoView;

public class ProyectoController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final ImpProyecto repo;

	public ProyectoController() {
		repo = new ImpProyecto();
	}

	public void menu() {
		while (true) {
			int opt = ProyectoView.getOption();
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
				return;
			}
		}
	}

	private void delete() {
		Integer id = ProyectoView.findById();
		logger.info("Eliminando Proyecto con id: " + id);
		Optional<Proyecto> entity = repo.findById(id);
		if (entity.isPresent())
			// primero se pone a null el departamento de los empleados
			entity.get().removeAllEmpleados();
		// luego se borra de la DB
		ProyectoView.result(repo.delete(entity.get()) ? "Borrado" : "No se ha podido borrar");
	}

	private void update() {
		boolean updated = false;
		Integer id = ProyectoView.findById();
		logger.info("Actualizando Proyecto con id: " + id);
		Optional<Proyecto> entity = repo.findById(id);
		Proyecto p = null;
		if (entity.isPresent()) {
			p = ProyectoView.modify(entity.get());
			// actualiza la DB
			updated = repo.update(p);
		}
		ProyectoView.result(updated ? "Modificado" : "No se ha podido modificar");
	}

	private void create() {
		logger.info("Creando Proyecto");
		Proyecto entity = ProyectoView.add();
		ProyectoView.result(repo.create(entity) ? "Añadido" : "No se ha podido añadir");
	}

	private void getAll() {
		logger.info("Obteniendo Proyectos");
		List<Proyecto> list = repo.findAll();
		ProyectoView.show(list);
	}

	private void getByStartsName() {
		String inicio = ProyectoView.findByName();
		logger.info("Obteniendo Empleados que empiezan por " + inicio);
		List<Proyecto> list = repo.findByName(inicio + "%");
		ProyectoView.show(list);
	}

	private void getById() {
		Integer id = ProyectoView.findById();
		logger.info("Obteniendo Proyecto con id: " + id);
		Optional<Proyecto> entity = repo.findById(id);

		if (entity.isPresent()) {
			ProyectoView.show(entity.get());
		}
	}

}
