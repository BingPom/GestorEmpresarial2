package view;

import dao.EmpleadoDao_deprecated;
import io.IO;
import model.Departamento;
import model.Empleado;

public class MenuEmpleado {
	
	public static void menu() {
		EmpleadoDao_deprecated dao = new EmpleadoDao_deprecated();
		
		while (true) {
			IO.println("Empleados: " + EmpleadosView.OPCIONES);
			switch (Character.toUpperCase(IO.readChar())) {
			case 'C':
				buscarPorCodigo(dao);
				break;
			case 'N':
				buscarPorInicioDelNombre(dao);
				break;
			case 'M':
				mostrar(dao);
				break;
			case 'A':
				anadir(dao);
				break;
			case 'F':
				modificar(dao);
				break;
			case 'E':
				borrar(dao);
				break;
			case 'S':
				return;
			default:
			}
		}		
		
	}

	private static void borrar(EmpleadoDao_deprecated dao) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		boolean borrado = dao.delete(id);
		IO.println(borrado ? "Borrado" : "No se ha podido borrar");
	}

	private static void anadir(EmpleadoDao_deprecated dao) {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		IO.print("Salario ? ");
		Double salario = IO.readDoubleOrNull();
		Empleado e = Empleado.builder()
				.nombre(nombre)
				.salario(salario)
				.build();
		boolean anadido = dao.create(e);
		IO.println(anadido ? "Añadido" : "No se ha podido añadir");
	}

	private static void modificar(EmpleadoDao_deprecated dao) {
		IO.print("Código del empleado a modificar ? ");
		Integer id = IO.readInt();
		Empleado emp = dao.findById(id);
		if (emp == null) {
			IO.println("No se ha encontrado al empleado");
			return;
		}
		IO.printf("Nombre [%s] ? ", emp.getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			emp.setNombre(nombre);
		}
		IO.printf("Salario [%s] ? ", emp.getSalario());
		Double salario = IO.readDoubleOrNull();
		if (salario != null) {
			emp.setSalario(salario);
		}
		IO.printf("Departamento [%s] ? ", emp.getDepartamento().show());
		Integer departamento = IO.readIntOrNull();
		if (departamento != null) {
			emp.setDepartamento(Departamento.builder().id(departamento).build());
		}
		boolean anadido = dao.update(emp);
		IO.println(anadido ? "Modificado" : "No se ha podido modificar");		
	}

	private static void mostrar(EmpleadoDao_deprecated dao) {
		for (Empleado e : dao.findAll()) {
			IO.println(e.show());
		}
	}

	private static void buscarPorInicioDelNombre(EmpleadoDao_deprecated dao) {
		IO.print("El nombre empieza por ? ");
		String inicio = IO.readString();
		for (Empleado e : dao.findByName(inicio)) {
			IO.println(e.show());
		}
	}

	private static void buscarPorCodigo(EmpleadoDao_deprecated dao) {
		IO.print("Código ? ");
		Integer id = IO.readInt();
		Empleado e = dao.findById(id);
		if (e != null) {
			IO.println(e.show());
		}
	}

}

