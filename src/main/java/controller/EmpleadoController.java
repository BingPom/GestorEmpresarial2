package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import model.Empleado;
import repository.empleado.ImpEmpleado;
import view.EmpleadosView;

public class EmpleadoController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final ImpEmpleado repo;

	public EmpleadoController() {
		repo = new ImpEmpleado();
	}

	public void menu() {
		while (true) {
			int opt = EmpleadosView.getOption();
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

	private void getByStartsName() {
		String inicio = EmpleadosView.buscarPorInicioDelNombre();
		logger.info("Obteniendo Empleados que empiezan por " + inicio);
		List<Empleado> list = repo.findByName(inicio + "%");
		EmpleadosView.mostrar(list);
	}

	private void getById() {
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Obteniendo Empleado con id: " + id);
		Optional<Empleado> entity = repo.findById(id);

		if (entity.isPresent()) {
			EmpleadosView.mostrar(entity.get());
		}
	}

	private void getAll() {
		logger.info("Obteniendo Empleados");
		List<Empleado> list = repo.findAll();
		EmpleadosView.mostrar(list);
	}

	private void create() {
		logger.info("Creando Empleado");
		Empleado entity = EmpleadosView.anadir();
		EmpleadosView.result(repo.create(entity) ? "Añadido" : "No se ha podido añadir");
	}

	private void update() {
		boolean updated = false;
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Actualizando Empleado con id: " + id);
		Optional<Empleado> entity = repo.findById(id);
		Empleado e = null;
		if (entity.isPresent()) {
			e = EmpleadosView.modificar(entity.get());
			updated = repo.update(e);
		}
		EmpleadosView.result(updated ? "Modificado" : "No se ha podido modificar");
	}

	private void delete() {
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Eliminando Empleado con id: " + id);
		Optional<Empleado> entity = repo.findById(id);
		if (entity.isPresent())
			EmpleadosView.result(repo.delete(entity.get()) ? "Borrado" : "No se ha podido borrar");
		else
			EmpleadosView.result("No existe");
	}

}
