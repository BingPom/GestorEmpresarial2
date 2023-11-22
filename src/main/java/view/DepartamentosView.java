package view;

import java.util.List;

import io.IO;
import model.Departamento;
import model.Empleado;

public class DepartamentosView {

	static final List<String> OPCIONES = List.of(
			"1. Buscar por Id\n", 
			"2. Buscar por nombre\n", 
			"3. Listar todos\n",
			"4. Añadir un departamento\n", 
			"5. Actualizar los datos de un departamento\n", 
			"6. Borrar un departamento\n",
			"7. Salir\n");

	static public Integer getOption() {
		IO.println("Departamentos:");
		OPCIONES.forEach(o -> IO.print(o));
		return IO.readInt();
	}

	static public Departamento add() {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		Departamento d = Departamento.builder().nombre(nombre).build();
		return d;
	}

	static public Departamento modify(Departamento d) {
		if (d == null) {
			IO.println("No se ha encontrado el departamento");
			return null;
		}
		IO.printf("Nombre [%s] ? ", d.getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			d.setNombre(nombre);
		}
		IO.printf("Jefe [%s] ? ", d.getJefe() == null ? "sin jefe!!!" : d.getJefe().show());
		Integer jefe = IO.readIntOrNull();
		if (jefe != null) {
			d.setJefe(Empleado.builder().id(jefe).build());
		}
		return d;
	}

	static public String findByNameStart() {
		IO.print("El nombre empieza por ? ");
		return IO.readString();
	}

	static public int findById() {
		IO.print("Código ? ");
		return IO.readInt();
	}

	static public void show(Departamento d) {
		if (d == null) {
			return;
		}
		IO.println(d.show());
		IO.println("* Empleados del departamento :");
		for (Empleado e : d.getEmpleados()) {
			IO.println(e.show());
		}
	}

	static public void show(List<Departamento> list) {
		for (Departamento d : list) {
			IO.println(d.show());
		}
	}

	static public void result(String msg) {
		IO.println(msg);
	}

}
