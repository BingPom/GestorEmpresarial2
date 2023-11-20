package controller;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import model.Departamento;
import model.Empleado;
import model.Proyecto;
import repository.departamento.ImpDepartamento;
import repository.empleado.ImpEmpleado;
import repository.proyecto.ImpProyecto;
import view.EmpleadosView;

public class EmpleadoController {
	private final Logger logger = Logger.getLogger(this.getClass().getName());

	private final ImpEmpleado repo;
	private final ImpDepartamento dpt;
	private final ImpProyecto prj;

	public EmpleadoController() {
		repo = new ImpEmpleado();
		dpt = new ImpDepartamento();
		prj = new ImpProyecto();
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
				addEmpleadoToDepartamento();
				break;
			case 8:
				removeEmpleadoOfDepartamento();
				break;
			case 9:
				addEmpleadoToProyecto();
				break;
			case 10:
				removeEmpleadoOfProyecto();
				break;
			case 11:
				return;
			default:
				return;
			}
		}
	}

	private void getByStartsName() {
		String inicio = EmpleadosView.findByName();
		logger.info("Obteniendo Empleados que empiezan por " + inicio);
		List<Empleado> list = repo.findByName(inicio + "%");
		EmpleadosView.show(list);
	}

	private void getById() {
		Integer id = EmpleadosView.findById();
		logger.info("Obteniendo Empleado con id: " + id);
		Optional<Empleado> entity = repo.findById(id);

		if (entity.isPresent()) {
			EmpleadosView.show(entity.get());
		}
	}

	private void getAll() {
		logger.info("Obteniendo Empleados");
		List<Empleado> list = repo.findAll();
		EmpleadosView.show(list);
	}

	private void create() {
		logger.info("Creando Empleado");
		Empleado entity = EmpleadosView.add();
		EmpleadosView.result(repo.create(entity) ? "Añadido" : "No se ha podido añadir");
	}

	private void update() {
		boolean updated = false;
		Integer id = EmpleadosView.findById();
		logger.info("Actualizando Empleado con id: " + id);
		Optional<Empleado> entity = repo.findById(id);
		Empleado e = null;
		if (entity.isPresent()) {
			e = EmpleadosView.modify(entity.get());

			// se obtiene el departamento de la base de datos
			if (e.getDepartamento() != null && dpt.findById(e.getDepartamento().getId()).isPresent())
				e.setDepartamento(dpt.findById(e.getDepartamento().getId()).get());

			updated = repo.update(e);
		}
		EmpleadosView.result(updated ? "Modificado" : "No se ha podido modificar");
	}

	private void delete() {
		Integer id = EmpleadosView.findById();
		logger.info("Eliminando Empleado con id: " + id);
		Optional<Empleado> entity = repo.findById(id);
		if (entity.isPresent())
			EmpleadosView.result(repo.delete(entity.get()) ? "Borrado" : "No se ha podido borrar");
		else
			EmpleadosView.result("No existe");
	}

	private void addEmpleadoToDepartamento() {
		Integer idDepartamento = EmpleadosView.findById(); // Get Departamento Id
		logger.info("Seleccione qué empleado añadir al departamento con id: " + idDepartamento);
		Integer idEmpleado = EmpleadosView.findById(); // Get Empleado Id
		Optional<Departamento> d = dpt.findById(idDepartamento);
		Optional<Empleado> e = repo.findById(idEmpleado);
		if (d.isPresent() && e.isPresent()) {
			EmpleadosView.result(dpt.addEmpleado(d.get(), e.get()) ? "Empleado añadido correctamente"
					: "Error a la hora de añadir el empleado");
		} else {
			EmpleadosView.result("Error a la hora de añadir el empleado");
		}
	}

	private void removeEmpleadoOfDepartamento() {
		Integer idDepartamento = EmpleadosView.findById(); // Get Departamento Id
		logger.info("Seleccione qué empleado sacacr del departamento con id: " + idDepartamento);
		Integer idEmpleado = EmpleadosView.findById(); // Get Empleado Id
		Optional<Departamento> d = dpt.findById(idDepartamento);
		Optional<Empleado> e = repo.findById(idEmpleado);
		if (d.isPresent() && e.isPresent()) {
			EmpleadosView.result(d.get().removeEmpleado(e.get()) ? "Empleado eliminado correctamente"
					: "Error a la hora de sacar el empleado");
		} else {
			EmpleadosView.result("Error a la hora de sacar el empleado");
		}
	}

	private void addEmpleadoToProyecto() {
		Integer idProyecto = EmpleadosView.findById(); // Get Proyecto Id
		logger.info("Seleccione qué empleado añadir al proyecto con id: " + idProyecto);
		Integer idEmpleado = EmpleadosView.findById(); // Get Empleado Id
		Optional<Proyecto> p = prj.findById(idProyecto);
		Optional<Empleado> e = repo.findById(idEmpleado);
		if (p.isPresent() && e.isPresent()) {
			EmpleadosView.result(p.get().addEmpleado(e.get()) ? "Empleado añadido correctamente"
					: "Error a la hora de añadir el empleado");
		} else {
			EmpleadosView.result("Error a la hora de añadir el empleado");
		}
	}

	private void removeEmpleadoOfProyecto() {
		Integer idProyecto = EmpleadosView.findById(); // Get Proyecto Id
		logger.info("Seleccione qué empleado sacar del proyecto con id: " + idProyecto);
		Integer idEmpleado = EmpleadosView.findById(); // Get Empleado Id
		Optional<Proyecto> p = prj.findById(idProyecto);
		Optional<Empleado> e = repo.findById(idEmpleado);
		if (p.isPresent() && e.isPresent()) {
			EmpleadosView.result(p.get().removeEmpleado(e.get()) ? "Empleado eliminado correctamente"
					: "Error a la hora de sacar el empleado");
		} else {
			EmpleadosView.result("Error a la hora de sacar el empleado");
		}
	}

}
