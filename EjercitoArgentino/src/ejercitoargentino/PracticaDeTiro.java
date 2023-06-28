package ejercitoargentino;

public class PracticaDeTiro extends Servicio {

	public PracticaDeTiro(){
		super("PDT","Practica con rifle de asalto");
	}

	@Override
	public void realizarAccion() {
		Consola.escribirLN("esta practicando...");
	}
}
