package ejercitoargentino;

import java.io.IOException;

public class Oficial extends Militar{
	public Oficial(String codigo, String nombreCompleto, Cuerpo cuerpo, Compania compania, Cuartel cuartel){
		super(codigo, nombreCompleto, cuerpo, compania, cuartel);
	}

	@Override
	public Boolean proceder(SistemaGestion sistema) throws IOException {
		Boolean seguir = false;
		char opc = 0, opc2 = 0;
		Militar m;
			do{
				this.mostrarMenu();
				opc = Consola.leerCaracter();
				
				switch(opc){
					case '1':
						do{
							this.mostrarSubMenuRegistrar();
							opc2 = Consola.leerCaracter();
							switch(opc2){
									case '1':
										sistema.pedirDatos();
									break;
									case '2':
										sistema.pedirDatosCuerpoMilitar();
									break;
								
									case '3':
										sistema.pedirDatosCompania();
									break;
									
									case '4':
										sistema.pedirDatosCuartel();
									break;
							}
						} while(opc2 != '5');
					break;
					
					case '2':
						String codigo = Consola.leer("Ingrese el codigo del militar a asignar: ");
						m = sistema.buscarMilitar(codigo);
						do{
							if(m != null){
								this.mostrarSubMenuAsignar();
								opc2 = Consola.leerCaracter();
								switch(opc2){
									case '1':
										if(!sistema.isCuerposEmpty()){
											m.setCuerpo(sistema.elegirCuerpoDisponible());
										} else{
											Consola.escribirLN("No existen cuerpos.");
										}
									break;
									case '2':
										if(!sistema.isCompaniasEmpty()){
											m.setCompania(sistema.elegirCompaniaDisponible());
										} else{
											Consola.escribirLN("No existen compañias.");
										}
									break;
									
									case '3':
										if(!sistema.isCuartelesEmpty()){
											m.setCuartel(sistema.elegirCuartelDisponible());
										} else{
											Consola.escribirLN("No existen cuarteles.");
										}
									break;
									
									case '4':
										m.mostrarInformacion();
									break;
								}
							} else{
								Consola.escribirLN("No existe un militar con ese codigo.");
							}
						} while(opc2 != '5' && m != null);
					break;
					
					case '3':
						this.consultarDatos(sistema);
					break;
					
					case '4':
						seguir = true;
					break;
					
					case '5':
						seguir = false;
					break;
				}
				sistema.serializar("ejercito.txt");
				Consola.escribirLN("\n");
			} while(opc != '4' && opc != '5');

		return seguir;
	}

	@Override
	public void mostrarMenu(){
		Consola.escribirLN(" -- MENU DE OPCIONES PARA OFICIAL -- ");
		Consola.escribirLN(
		  "[1] Registrar (Militar | Cuerpo Militar | Compañia | Cuartel)\n"
		+ "[2] Asignar (Cuerpo Militar | Compañia | Cuartel)\n"
		+ "[3] Pedir datos de militar\n"
		+ "[4] Salir de este usuario\n"
		+ "[5] Salir del sistema");
	}

	private void mostrarSubMenuRegistrar(){
		Consola.escribirLN(" -- SUBMENU DE OPCIONES PARA REGISTRAR -- ");
		Consola.escribirLN(
		  "[1] Registrar nuevo militar\n"
		+ "[2] Registrar nuevo cuerpo militar\n"
		+ "[3] Registrar nueva compañia\n"
		+ "[4] Registrar nuevo cuartel\n"
		+ "[5] Salir de este submenu");
	}

	private void mostrarSubMenuAsignar(){
		Consola.escribirLN(" -- SUBMENU DE OPCIONES PARA ASIGNAR -- ");
		Consola.escribirLN(
		  "[1] Asignar cuerpo militar\n"
		+ "[2] Asignar compañia\n" 
		+ "[3] Asignar cuartel\n"
		+ "[4] Mostrar informacion del militar\n"
		+ "[5] Salir de este submenu");
	}

}

