package view;

import java.util.List;

import io.IO;
import model.Proyecto;

public class ProyectoView {

	static final List<String> OPCIONES = List.of( 
			"1. Buscar por Id", 
			"2. Buscar por nombre", 
			"3. Listar todos",
			"4. Añadir un proyecto", 
			"5. Actualizar los datos de un proyecto", 
			"6. Borrar un proyecto",
			"7. Salir");
	
	static public Integer getOption() {
		IO.println("Empleados: " + OPCIONES);
		return IO.readInt();
	}
	
	static public Proyecto anadir() {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		Proyecto p = Proyecto.builder()
				.nombre(nombre)
				.build();
		return p;
	}
	
}
