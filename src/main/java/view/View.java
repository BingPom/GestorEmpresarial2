package view;

import java.util.List;

import io.IO;

public class View {

	static final List<String> opciones = List.of(
			"1. Empleados", 
			"2. Departamentos", 
			"3. Proyectos", 
			"4. Salir");

	public static Integer getOption() {
		IO.println(opciones);
		return IO.readInt();
	}

}
