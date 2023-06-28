package ejercitoargentino;

import java.io.Serializable;

public class Cuartel implements Serializable{
    private String codigoCuartel;
    private String nombre;
    private String ubicacion;
	
    public Cuartel(String codigoCuartel, String nombre, String ubicacion) {
        this.codigoCuartel = codigoCuartel;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
    }

	public void mostrarInformacion(){
		Consola.escribirLN("Codigo de cuartel: " + codigoCuartel + "\t Nombre: " + nombre + "\t Ubicacion: " + ubicacion);
	}
	public String getCodigo(){
		return codigoCuartel;
	}

	public String getNombre(){
		return nombre;
	}
}
