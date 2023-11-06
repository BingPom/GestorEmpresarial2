package view;

import java.time.LocalDate;
import java.util.List;

import io.IO;
import model.Departamento;
import model.Empleado;

public class EmpleadosView {
	
	static final List<String> OPCIONES = List.of( 
			"buscar por Código", 
			"buscar por Nombre", 
			"Mostrar", 
			"Añadir",
			"modiFicar",
			"Eliminar",
			"Salir");
	
	static public Character getOption() {
		IO.println("Empleados: " + OPCIONES);
		return Character.toUpperCase(IO.readChar());
	}

	static public Empleado anadir() {
		IO.print("Nombre ? ");
		String nombre = IO.readString();
		IO.print("Salario ? ");
		Double salario = IO.readDoubleOrNull();
		IO.print("Nacido (aaaa-mm-dd) ? ");
		LocalDate nacido = IO.readLocalDateOrNull();
		Empleado e = Empleado.builder()
				.nombre(nombre)
				.salario(salario)
				.build();
		return e;
	}

	static public Empleado modificar(Empleado e) {
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

	static public String buscarPorInicioDelNombre() {
		IO.print("El nombre empieza por ? ");
		return IO.readString();
	}

	static public int buscarPorCodigo() {		
		IO.print("Código ? ");
		return IO.readInt();
	}

	static public void mostrar(List<Empleado> list) {
		for (Empleado e : list) {
			IO.println(e.show());
		}
	}
	
	static public void mostrar(Empleado e) {
		if (e == null) {
			return;
		}
		IO.println(e.show());
	}
	
	static public void result(String msg) {
		IO.println(msg);
	}

}

