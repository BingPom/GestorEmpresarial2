package view;

import java.util.List;

import io.IO;
import model.Departamento;
import model.Empleado;

public class EmpleadosView {
	
	static final List<String> OPCIONES = List.of( 
			"1. Buscar por Id", 
			"2. Buscar por nombre", 
			"3. Listar todos",
			"4. Añadir un empleado", 
			"5. Actualizar los datos de un empleado", 
			"6. Borrar un empleado",
			"7. Añadir un empleado a un departamento",
			"8. Sacar un empleado a un departamento",
			"9. Añadir un empleado a un proyecto",
			"10. Sacar a un empleado de un proyecto",
			"11. Salir");
	
	static public Integer getOption() {
		IO.println("Empleados: " + OPCIONES);
		return IO.readInt();
	}

	static public Empleado add() {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		IO.print("Salario ? ");
		Double salario = IO.readDoubleOrNull();
		Empleado e = Empleado.builder()
				.nombre(nombre)
				.salario(salario)
				.build();
		return e;
	}

	static public Empleado modify(Empleado e) {
		IO.printf("Nombre [%s] ? ", e.getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			e.setNombre(nombre);
		}
		IO.printf("Salario [%s] ? ", e.getSalario());
		Double salario = IO.readDoubleOrNull();
		if (salario != null) {
			e.setSalario(salario);
		}

		Departamento d = e.getDepartamento();
		IO.printf("Departamento [%s] ? ", d == null ? "sin departamento!!!" : d.show());
		Integer departamento = IO.readIntOrNull();
		if (departamento != null) {
			e.setDepartamento(Departamento.builder().id(departamento).build());
		}
		return e;
	}

	static public String findByName() {
		IO.print("El nombre empieza por ? ");
		return IO.readString();
	}

	static public int findById() {		
		IO.print("Código ? ");
		return IO.readInt();
	}

	static public void show(List<Empleado> list) {
		for (Empleado e : list) {
			IO.println(e.show());
		}
	}
	
	static public void show(Empleado e) {
		if (e == null) {
			return;
		}
		IO.println(e.show());
	}
	
	static public void result(String msg) {
		IO.println(msg);
	}

}

