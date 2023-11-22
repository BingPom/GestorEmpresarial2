package view;

import java.util.List;

import io.IO;
import model.Empleado;

public class EmpleadosView {

	static final List<String> OPCIONES = List.of("1. Buscar por Id\n",
			"2. Buscar por nombre\n",
			"3. Listar todos\n",
			"4. Añadir un empleado\n",
			"5. Actualizar los datos de un empleado\n",
			"6. Borrar un empleado\n",
			"7. Añadir un empleado a un departamento\n",
			"8. Sacar un empleado a un departamento\n",
			"9. Añadir un empleado a un proyecto\n",
			"10. Sacar a un empleado de un proyecto\n",
			"11. Salir\n");

	static public Integer getOption() {
		IO.println("Empleados:");
		OPCIONES.forEach(o -> IO.print(o));
		return IO.readInt();
	}

	static public Empleado add() {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		IO.print("Salario ? ");
		Double salario = IO.readDoubleOrNull();
		Empleado e = Empleado.builder().nombre(nombre).salario(salario).build();
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
