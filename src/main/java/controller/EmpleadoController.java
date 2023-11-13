package controller;

import java.util.List;
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
			Character opt = EmpleadosView.getOption();
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
		String inicio = EmpleadosView.buscarPorInicioDelNombre();
		logger.info("Obteniendo Empleados que empiezan por " + inicio);
		List<Empleado> list = repo.findByName(inicio + "%");
		EmpleadosView.mostrar(list);		
	}

	private void getById() {
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Obteniendo Empleado con id: " + id);
		Empleado entity = repo.findById(id).stream().collect(Collectors.toList()).get(0);
		if (entity != null) {
			EmpleadosView.mostrar(entity);
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
		Empleado entity = repo.findById(id).stream().collect(Collectors.toList()).get(0);
		Empleado d = null;
		if (entity != null) {
			d = EmpleadosView.modificar(entity);
			updated = repo.update(d);
		}
		EmpleadosView.result(updated ? "Modificado" : "No se ha podido modificar");
	}

	private void delete() {
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Eliminando Empleado con id: " + id);
		Empleado entity = repo.findById(id).stream().collect(Collectors.toList()).get(0);
		if (entity != null)
			EmpleadosView.result(repo.delete(entity) ? "Borrado" : "No se ha podido borrar");
	}
}
