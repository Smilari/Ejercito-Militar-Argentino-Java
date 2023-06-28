package ejercitoargentino;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

public abstract class Servicio implements Serializable{
    private final String codigoServicio;
    private final String descripcion;
	private LocalDate fechaRealizado;
	private LocalTime horaRealizado;

    public Servicio(String codigoServicio, String descripcion) {
        this.codigoServicio = codigoServicio;
        this.descripcion = descripcion;
		this.fechaRealizado = LocalDate.now();
		this.horaRealizado = LocalTime.now();
    }

	public void mostrarInformacion(){
		Consola.escribirLN("- Servicio: " + descripcion + "\t Realizado el: " + fechaRealizado + "\t A las: " + horaRealizado);
	}

	public String getCodigoServicio(){
		return codigoServicio;
	}

	public String getDescripcion(){
		return descripcion;
	}
	
	public abstract void realizarAccion();
}
