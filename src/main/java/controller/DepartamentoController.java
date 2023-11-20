package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import model.Departamento;
import model.Empleado;
import repository.departamento.ImpDepartamento;
import repository.empleado.ImpEmpleado;
import view.DepartamentosView;

public class DepartamentoController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final ImpDepartamento repo;
	private final ImpEmpleado emple;

	public DepartamentoController() {
		repo = new ImpDepartamento();
		emple = new ImpEmpleado();
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
				return;
			}
		}
	}

	private void getByStartsName() {
		String inicio = DepartamentosView.findByNameStart();
		logger.info("Obteniendo Departamentos que empiezan por " + inicio);
		List<Departamento> list = repo.findByName(inicio + "%");
		DepartamentosView.show(list);
	}

	private void getById() {
		Integer id = DepartamentosView.findById();
		logger.info("Obteniendo Departamento con id: " + id);
		Optional<Departamento> entity = repo.findById(id);
		if (entity.isPresent()) {
			DepartamentosView.show(entity.get());
		}
	}

	private void getAll() {
		logger.info("Obteniendo Departamentos");
		List<Departamento> list = repo.findAll();
		DepartamentosView.show(list);
	}

	private void create() {
		logger.info("Creando Departamento");
		Departamento entity = DepartamentosView.add();
		DepartamentosView.result(repo.create(entity) ? "Añadido" : "No se ha podido añadir");
	}

	private void update() {
		boolean updated = false;
		Integer id = DepartamentosView.findById();
		logger.info("Actualizando Departamento con id: " + id);
		Optional<Departamento> entity = repo.findById(id);
		Departamento d = null;
		if (entity.isPresent()) {
			d = DepartamentosView.modify(entity.get());

			// para que funcione necesitamos obtener el jefe de la base de datos
			if (d.getJefe() != null && emple.findById(d.getJefe().getId()).isPresent()) {
				Empleado jefe = emple.findById(d.getJefe().getId()).get();
				// primero se inserta en el departamento
				d.addEmpleado(jefe);
				// luego se pone como jefe
				d.setJefe(jefe);
			}

			// actualiza la DB
			updated = repo.update(d);
		}
		DepartamentosView.result(updated ? "Modificado" : "No se ha podido modificar");
	}

	private void delete() {
		Integer id = DepartamentosView.findById();
		logger.info("Eliminando Departamento con id: " + id);
		Optional<Departamento> entity = repo.findById(id);
		if (entity.isPresent())
			// primero se pone a null el departamento de los empleados
			entity.get().removeAllEmpleados();
		// luego se borra de la DB
		DepartamentosView.result(repo.delete(entity.get()) ? "Borrado" : "No se ha podido borrar");
	}
}
