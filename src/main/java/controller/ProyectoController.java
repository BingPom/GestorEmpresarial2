package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import model.Empleado;
import model.Proyecto;
import repository.proyecto.ImpProyecto;
import view.EmpleadosView;
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
				break;
			case 8:
				break;
			case 9:
				break;
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
		// TODO Auto-generated method stub

	}

	private void getByStartsName() {
		String inicio = ProyectoView.buscarPorInicioDelNombre();
		logger.info("Obteniendo Empleados que empiezan por " + inicio);
		List<Proyecto> list = repo.findByName(inicio + "%");
		ProyectoView.mostrar(list);
	}

	private void getById() {
		Integer id = ProyectoView.buscarPorCodigo();
		logger.info("Obteniendo Proyecto con id: " + id);
		Optional<Proyecto> entity = repo.findById(id);

		if (entity.isPresent()) {
			ProyectoView.mostrar(entity.get());
		}
	}

}
