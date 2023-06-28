package ejercitoargentino;

public class Correr extends Servicio {

	public Correr(){
		super("COR","Ejercicio aerobico");
	}

	@Override
	public void realizarAccion() {
		Consola.escribirLN("esta corriendo...");
	}
}
