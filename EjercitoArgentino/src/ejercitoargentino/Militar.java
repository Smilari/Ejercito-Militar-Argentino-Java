package ejercitoargentino;

import java.io.IOException;
import java.io.Serializable;

public abstract class Militar implements Serializable{
	private String codigo;
    private String nombreCompleto;
    private Cuartel cuartel;
    private Cuerpo cuerpo;
    private Compania compania;
	
	public Militar(String codigo, String nombreCompleto, Cuerpo cuerpo, Compania compania, Cuartel cuartel) {
		this.codigo = codigo;
		this.nombreCompleto = nombreCompleto;
		this.cuartel = cuartel;
		this.cuerpo = cuerpo;
		this.compania = compania;
	}
	

	public void setCompania(Compania compania) {
		if(this.compania != null){
			this.compania.eliminarMilitar(this);
		}	
        this.compania = compania;
		compania.agregarMilitar(this);
    }

	public void setCuartel(Cuartel cuartel) {
		this.cuartel = cuartel;
	}
	
	public void setCuerpo(Cuerpo cuerpo) {
		this.cuerpo = cuerpo;
	}

	public Cuartel getCuartel() {
		return this.cuartel;
	}

	public Cuerpo getCuerpo() {
		return this.cuerpo;
	}

	public Compania getCompania() {
		return this.compania;
	}

	public String getNombre(){
		return this.nombreCompleto;
	}

	public String getCodigo(){
		return this.codigo;
	}

	public abstract Boolean proceder(SistemaGestion sistema) throws IOException;
	
	public abstract void mostrarMenu();

	public void consultarDatos(SistemaGestion sistema){
		char opc = Consola.leerCaracter("[1] Datos de un militar particular\n" + 
										"[2] Datos de todos los militares enlistados\n");
		switch(opc){
			case '1':
				String codigo = Consola.leer("Ingrese el codigo del militar a consultar: ");
				sistema.mostrarMilitar(codigo);
			break;

			case '2':
				sistema.mostrarMilitares();
			break;
		}
	}

	public void mostrarInformacion(){
		Consola.escribirLN("- Código de soldado: " + codigo);
        Consola.escribirLN("- Nombre completo: " + nombreCompleto);
		Consola.escribirLN("- Graduación: " + getClass().getSimpleName());
		
		try{
			Consola.escribirLN("- Cuerpo: " + cuerpo.getDenominacion());
		} catch(NullPointerException  ex){
			Consola.escribirLN("- No dispone de un cuerpo militar asignado.");
		}

		try{
			Consola.escribirLN("- Compañía: " + compania.getNumeroCompania());
			Consola.escribirLN("- Actividad principal de la compañía: " + compania.getActividadPrincipal());
	
		} catch(NullPointerException  ex){
			Consola.escribirLN("- No dispone de una compañia asignada.");
		}

		try{
			Consola.escribirLN("- Cuartel asignado: " + cuartel.getNombre());

		} catch(NullPointerException  ex){
			Consola.escribirLN("- No dispone de un cuartel asignado.");
		}
	}

	public void mostrarInformacionReducida(){
		Consola.escribirLN("Nombre: " + nombreCompleto + "\tCodigo: " + codigo + "\tGraduación: " + getClass().getSimpleName());
	}
}
