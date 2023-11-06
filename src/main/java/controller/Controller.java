package controller;

import java.util.List;
import java.util.logging.Logger;

import view.View;

public class Controller {
	private final Logger logger = Logger.getLogger(Controller.class.getName());

	public Controller() {
		
		DepartamentosController departamento = new DepartamentosController();
		EmpleadosController empleado = new EmpleadosController();
		
		List<String> menu = List.of("Seleccione una opción", "1. Empleado",
								"2. Departamento", "3. Proyecto", "4. Salir");
		
		while (true) {
			Character opt = View.getOption();
			logger.info(menu.toString());
			switch (opt) {
				case 1:
					empleado.menu();
					break;
				case 2:
					departamento.menu();
					break;
				case 3:
					
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
