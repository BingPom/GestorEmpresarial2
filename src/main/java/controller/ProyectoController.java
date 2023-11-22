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
		// TODO Auto-generated method stub

	}

	private void update() {
		// TODO Auto-generated method stub

	}

	private void create() {
		// TODO Auto-generated method stub

	}

	private void getAll() {
		logger.info("Obteniendo Proyectos");
		List<Proyecto> list = repo.findAll();
		ProyectoView.show(list);
	}

	private void getByStartsName() {
		String inicio = ProyectoView.buscarPorInicioDelNombre();
		logger.info("Obteniendo Empleados que empiezan por " + inicio);
		List<Proyecto> list = repo.findByName(inicio + "%");
		ProyectoView.show(list);
	}

	private void getById() {
		Integer id = ProyectoView.buscarPorCodigo();
		logger.info("Obteniendo Proyecto con id: " + id);
		Optional<Proyecto> entity = repo.findById(id);

		if (entity.isPresent()) {
			ProyectoView.show(entity.get());
		}
	}

}
