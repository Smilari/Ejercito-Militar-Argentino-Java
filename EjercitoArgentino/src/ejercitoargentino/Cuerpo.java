package ejercitoargentino;

import java.io.Serializable;

public class Cuerpo implements Serializable{
    private String codigoCuerpo;
	private String denominacion;	
	
	public Cuerpo(String denominacion, String codigoCuerpo) {
		this.codigoCuerpo = codigoCuerpo;
		this.denominacion = denominacion;
	}

	public void mostrarInformacion(){
		Consola.escribirLN("Codigo de cuerpo: " + codigoCuerpo + "\t Denominacion: " + denominacion);
	}

	public String getCodigo(){
		return codigoCuerpo;
	}

	public String getDenominacion(){
		return denominacion;
	}
}
