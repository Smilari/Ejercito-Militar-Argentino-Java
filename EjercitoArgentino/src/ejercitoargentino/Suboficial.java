package ejercitoargentino;

import java.io.IOException;

public class Suboficial extends Militar {
	
	public Suboficial(String codigo, String nombreCompleto, Cuerpo cuerpo, Compania compania, Cuartel cuartel){
		super(codigo, nombreCompleto, cuerpo, compania, cuartel);
	}

	@Override
	public Boolean proceder(SistemaGestion sistema) throws IOException {
		Boolean seguir = false;
		char opc, opc2;
		do{
			this.mostrarMenu();
			opc = Consola.leerCaracter();
			switch(opc){
				case '1':
					if(sistema.existenSoldados()){
						Soldado s = sistema.elegirSoldadoDisponible();
						do{
							this.mostrarSubMenuServicios();
							opc2 = Consola.leerCaracter();
							switch(opc2){
								case '1':
									s.realizarServicio(new Barrer());
								break;
							
								case '2':
									s.realizarServicio(new Correr());
								break;
							
								case '3':
									s.realizarServicio(new PracticaDeTiro());
								break;
							}
						} while(opc2 != '4');
					} else{
						Consola.escribirLN("No hay soldados disponibles.");
					}
				break;

				case '2':
					this.consultarDatos(sistema);
				break;

				case '3':
					seguir = true;
				break;
					
				case '4':
					seguir = false;
				break;
			}
			sistema.serializar("ejercito.txt");
			Consola.escribirLN("\n");
		} while(opc != '3' && opc != '4');
		
		return seguir;
	}

	@Override
	public void mostrarMenu() {
		Consola.escribirLN(" -- MENU DE OPCIONES PARA SUBOFICIAL -- ");
		Consola.escribirLN(
		  "[1] Asignar servicios\n"
		+ "[2] Pedir datos de militar\n"
		+ "[3] Salir de este usuario\n"
		+ "[4] Salir del sistema");
	}

	public void mostrarSubMenuServicios(){
		Consola.escribirLN(" -- SUBMENU DE OPCIONES PARA SERVICIOS -- ");
		Consola.escribirLN(
		  "[1] Asignar Barrer\n"
		+ "[2] Asignar Correr\n"
		+ "[3] Asignar Practica de tiro\n"
		+ "[4] Salir de este submenu");
	}
}	
