package controller;

import java.util.List;
import java.util.logging.Logger;

import view.View;

public class Controller {
	private final Logger logger = Logger.getLogger(Controller.class.getName());

	public Controller() {
		
		DepartamentoController departamento = new DepartamentoController();
		EmpleadoController empleado = new EmpleadoController();
		ProyectoController proyecto = new ProyectoController();
		
		List<String> mainMenu = List.of("Seleccione una opción", "1. Empleado",
								"2. Departamento", "3. Proyecto", "4. Salir");
		
		while (true) {
			int opt = View.getOption();
			logger.info(mainMenu.toString());
			switch (opt) {
				case 1:
					empleado.menu();
					break;
				case 2:
					departamento.menu();
					break;
				case 3:
					proyecto.menu();
					break;
				case 4:
					return;
				default:
					logger.info("Opción no válida");
					break;
			}		
		}
	}
	
}
