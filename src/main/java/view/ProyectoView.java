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
		IO.println("Proyectos: " + OPCIONES);
		OPCIONES.forEach(o -> IO.println(o));
		return IO.readInt();
	}

	static public Proyecto anadir() {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		Proyecto p = Proyecto.builder().nombre(nombre).build();
		return p;
	}

	static public Proyecto modificar(Proyecto p) {
		IO.printf("Nombre [%s] ? ", p.getNombre());
		String nombre = IO.readString();
		if (!nombre.isBlank()) {
			p.setNombre(nombre);
		}

		return p;
	}

	static public int buscarPorCodigo() {
		IO.print("Código ? ");
		return IO.readInt();
	}

	public static String buscarPorInicioDelNombre() {
		IO.print("El nombre empieza por ? ");
		return IO.readString();
	}

	static public void show(List<Proyecto> list) {
		for (Proyecto p : list) {
			IO.println(p.show());
		}
	}

	static public void show(Proyecto p) {
		if (p == null) {
			return;
		}
		IO.println(p.show());
	}

	static public void result(String msg) {
		IO.println(msg);
	}

}
