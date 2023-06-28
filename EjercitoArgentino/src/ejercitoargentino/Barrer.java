package ejercitoargentino;

public class Barrer extends Servicio {

	public Barrer(){
		super("BAR","Limpieza.");
	}

	@Override
	public void realizarAccion() {
		Consola.escribirLN("esta barriendo...");
	}
}
