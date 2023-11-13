package view;

import dao.BD_deprecated;
import io.IO;

public class Menu {
	
	public static void main(String[] args) {
		
		while (true) {
			IO.println(View.opciones);
			switch (Character.toUpperCase(IO.readChar())) {
			case 1:
				MenuEmpleado.menu();
				break;
			case 2:
				MenuDepartamento.menu();
				break;
			case 3:
//				MenuProyecto.menu();
				break;
			case 4:
				BD_deprecated.close();
				return;
			default:
			}
		}		
				
	}

}

