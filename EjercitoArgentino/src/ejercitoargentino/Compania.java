package ejercitoargentino;

import java.io.Serializable;
import java.util.ArrayList;

public class Compania implements Serializable{
    private int numeroCompania;
    private String actividadPrincipal;
    private ArrayList<Militar> militares;

    public Compania(int numeroCompania, String actividadPrincipal) {
        this.numeroCompania = numeroCompania;
        this.actividadPrincipal = actividadPrincipal;
        this.militares = new ArrayList<Militar>();
    }

	public void mostrarInformacion(){
		Consola.escribirLN("Numero de compañia: " + numeroCompania + "\t Actividad principal: " + actividadPrincipal);
		Consola.escribirLN("Militares de esta compañia:");
		for(Militar m : militares){
			Consola.escribir("- ");
			m.mostrarInformacionReducida();
		}
	}

	public int getNumeroCompania(){
		return numeroCompania;
	}

	public String getActividadPrincipal(){
		return actividadPrincipal;
	}

    public void agregarMilitar(Militar militar) {
        militares.add(militar);
    }

    public void eliminarMilitar(Militar militar) {
        militares.remove(militar);
    }
}
