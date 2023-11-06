package controller;

import java.util.List;
import java.util.logging.Logger;

import dao.DepartamentoDao;
import model.Departamento;

import view.DepartamentosView;

public class DepartamentosController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final DepartamentoDao dao;

	public DepartamentosController() {
		dao = new DepartamentoDao();
	}
	
	public void menu() {
		while (true) {
			Character opt = DepartamentosView.getOption();
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
		String inicio = DepartamentosView.buscarPorInicioDelNombre();
		logger.info("Obteniendo Departamentos que empiezan por " + inicio);
		List<Departamento> list = dao.findByName(inicio + "%");
		DepartamentosView.mostrar(list);		
	}

	private void getById() {
		Integer id = DepartamentosView.buscarPorCodigo();
		logger.info("Obteniendo Departamento con id: " + id);
		Departamento entity = dao.findById(id);
		if (entity != null) {
			DepartamentosView.mostrar(entity, dao.getEmpleados(id));
		}
	}
	
	private void getAll() {
		logger.info("Obteniendo Departamentos");
		List<Departamento> list = dao.findAll();
		DepartamentosView.mostrar(list);
	}

	private void create() {
		logger.info("Creando Departamento");
		Departamento entity = DepartamentosView.anadir();
		boolean anadido  = dao.create(entity);
		DepartamentosView.result(anadido ? "Añadido" : "No se ha podido añadir");
	}

	private void update() {
		boolean actualizado = false;
		Integer id = DepartamentosView.buscarPorCodigo();
		logger.info("Actualizando Departamento con id: " + id);
		Departamento entity = dao.findById(id);
		Departamento d = null;
		if (entity != null) {
			d = DepartamentosView.modificar(entity);
			actualizado = dao.update(d);
		}
		DepartamentosView.result(actualizado ? "Modificado" : "No se ha podido modificar");
	}

	private void delete() {
		boolean borrado = false;
		Integer id = DepartamentosView.buscarPorCodigo();
		logger.info("Eliminando Departamento con id: " + id);
		Departamento entity = dao.findById(id);
		if (entity != null) {
			borrado = dao.delete(id);
		}
		DepartamentosView.result(borrado ? "Borrado" : "No se ha podido borrar");
	}
}
