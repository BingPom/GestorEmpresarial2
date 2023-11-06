package controller;

import java.util.List;
import java.util.logging.Logger;

import dao.EmpleadoDao;
import model.Empleado;

import view.EmpleadosView;

public class EmpleadosController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final EmpleadoDao dao;

	public EmpleadosController() {
		dao = new EmpleadoDao();
	}
	
	public void menu() {
		while (true) {
			Character opt = EmpleadosView.getOption();
			switch (opt) {
			case 'C':
				getById();
				break;
			case 'N':
				getByStartsName();
				break;
			case 'M':
				getAll();
				break;
			case 'A':
				create();
				break;
			case 'F':
				update();
				break;
			case 'E':
				delete();
				break;
			case 'S':
				return;
			default:
			}
		}
	}

	private void getByStartsName() {
		String inicio = EmpleadosView.buscarPorInicioDelNombre();
		logger.info("Obteniendo Empleados que empiezan por " + inicio);
		List<Empleado> list = dao.findByName(inicio + "%");
		EmpleadosView.mostrar(list);		
	}

	private void getById() {
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Obteniendo Empleado con id: " + id);
		Empleado entity = dao.findById(id);
		if (entity != null) {
			EmpleadosView.mostrar(entity);
		}
	}
	
	private void getAll() {
		logger.info("Obteniendo Empleados");
		List<Empleado> list = dao.findAll();
		EmpleadosView.mostrar(list);
	}

	private void create() {
		logger.info("Creando Empleado");
		Empleado entity = EmpleadosView.anadir();
		boolean anadido = dao.create(entity);
		EmpleadosView.result(anadido ? "Añadido" : "No se ha podido añadir");
	}

	private void update() {
		boolean modificado = false;
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Actualizando Empleado con id: " + id);
		Empleado entity = dao.findById(id);
		Empleado d = null;
		if (entity != null) {
			d = EmpleadosView.modificar(entity);
			modificado = dao.update(d);
		}
		EmpleadosView.result(modificado ? "Modificado" : "No se ha podido modificar");
	}

	private void delete() {
		boolean borrado = false;
		Integer id = EmpleadosView.buscarPorCodigo();
		logger.info("Eliminando Empleado con id: " + id);
		Empleado entity = dao.findById(id);
		if (entity != null) {
			borrado = dao.delete(id);
		}
		EmpleadosView.result(borrado ? "Borrado" : "No se ha podido borrar");
	}
}
