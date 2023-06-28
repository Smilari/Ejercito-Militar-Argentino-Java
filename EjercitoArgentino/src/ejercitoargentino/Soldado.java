package ejercitoargentino;

import java.io.IOException;
import java.util.ArrayList;

public class Soldado extends Militar{

    private ArrayList<Servicio> serviciosRealizados;
    
	public Soldado(String codigo, String nombreCompleto, Cuerpo cuerpo, Compania compania, Cuartel cuartel){
		super(codigo, nombreCompleto, cuerpo, compania, cuartel);
		this.serviciosRealizados = new ArrayList<>();
	}
	

	public ArrayList<Servicio> getServiciosRealizados() {
		return serviciosRealizados;
	}

	public void realizarServicio(Servicio servicio) {
		this.serviciosRealizados.add(servicio);
		Consola.escribir(this.getNombre() + " ");
		servicio.realizarAccion();
    }

	@Override
    public void mostrarInformacion() {
		// Método para mostrar información del soldado
        super.mostrarInformacion();
		Consola.escribirLN("Servicios realizados:");
        for (Servicio servicio : serviciosRealizados) {
            servicio.mostrarInformacion();
        }

    }

	@Override
	public Boolean proceder(SistemaGestion sistema) throws IOException {
		Boolean seguir = false;
		char opc;
		do{
			this.mostrarMenu();
			opc = Consola.leerCaracter();
			switch(opc){
				case '1':
					this.consultarDatos(sistema);
				break;

				case '2':
					seguir = true;
				break;

				case '3':
					seguir = false;
				break;
			}
		} while(opc != '2' && opc != '3');

		return seguir;
	}


	@Override
	public void mostrarMenu() {
		Consola.escribirLN(" -- MENU DE OPCIONES PARA SOLDADO -- ");
		Consola.escribirLN("[1] Pedir datos de militar\n"
		+ "[2] Salir de este usuario\n"
		+ "[3] Salir del sistema");
	}
}
