package ejercitoargentino;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class SistemaGestion implements Serializable {

    private ArrayList<Militar> militares;
	private ArrayList<Cuerpo> cuerpos;
	private ArrayList<Cuartel> cuarteles;
	private ArrayList<Compania> companias;
	
	// CONSTRUCTOR
    public SistemaGestion() {
        this.militares = new ArrayList<>();
		this.cuerpos = new ArrayList<>();
		this.cuarteles = new ArrayList<>();
		this.companias = new ArrayList<>();
    }

	// UTILIDADES
	private int elegirOpcion(int max){
		int opc = Consola.leerEntero();
		while(opc < 1 || opc > max){
			opc = Consola.leerEntero("[ERROR] Ingrese un valor disponible: ");
		} 
		return (opc - 1);
	}


	// GESTION DE ARCHIVOS
    public void serializar(String arch) throws IOException {
		FileOutputStream file = new FileOutputStream(arch);
        ObjectOutputStream oi = new ObjectOutputStream(file);
        oi.writeObject(this);
        oi.close();
        file.close();
    }

	public SistemaGestion deSerializar(String arch) throws IOException, ClassNotFoundException {
		FileInputStream file = new FileInputStream(arch);
		ObjectInputStream oi = new ObjectInputStream(file);
		SistemaGestion sistema = (SistemaGestion) oi.readObject();
		oi.close();
		file.close();
		return sistema;
	}


	// MILITARES
    public Militar buscarMilitar(String cod) {
        int i = 0;
        Militar m;
		// Se mantiene en el while si el contador 'i' es menor a la cantidad de militares en el Array, y si el codigo ingresado (cod)
		// es distinto del codigo obtenido en la posicion 'i' en el array de militares.
        while (i < militares.size() && !cod.equals(militares.get(i).getCodigo())) { 
            i++;
        }
        m = i < militares.size() ? militares.get(i) : null;
		return m;
    }

    public void agregarMilitar(Militar m){
        if(!existeMilitar(m.getCodigo())){
			this.militares.add(m);
		} else{
			Consola.escribirLN("Ya existe un militar con el codigo " + m.getCodigo());
		}
    }

	public Boolean existeMilitar(String cod){
		return buscarMilitar(cod) != null;
	}
	
	public Boolean existeMilitar(Militar m){
		return militares.contains(m);
	}

	public void pedirDatos(){
			String codigo = Consola.leer("Ingrese su codigo de militar: ");
			while(existeMilitar(codigo)){
				codigo = Consola.leer("[ERROR] Codigo ya existente, ingrese otro: ");
			}
			String nombreCompleto = Consola.leer("Ingrese su nombre completo: ");
			Cuartel cuartel = null;
			Cuerpo cuerpo = null;
			Compania compania = null;
			
			if(!cuerpos.isEmpty()){
				cuerpo = elegirCuerpoDisponible();
			}
			if(!companias.isEmpty()){
				compania = elegirCompaniaDisponible();
			}
			if(!cuarteles.isEmpty()){
				cuartel = elegirCuartelDisponible();
			}
			
			Militar m = null;
			char opc = Consola.leerCaracter("De que rango es el militar a ingresar?\n[1] Soldado\t[2] Suboficial\t[3] Oficial: ");
			while(opc != '1' && opc != '2' && opc != '3'){
					opc = Consola.leerCaracter("[ERROR] Ingrese un numero valido: ");
			}
			switch(opc){
				case '1':
					m = new Soldado(codigo, nombreCompleto, cuerpo, compania, cuartel);
					break;
				case '2':
					m = new Suboficial(codigo, nombreCompleto, cuerpo, compania, cuartel);
					break;
				case '3':
					m = new Oficial(codigo, nombreCompleto, cuerpo, compania, cuartel);
					break;
			}
			if(compania != null){
				compania.agregarMilitar(m);
			}
			this.militares.add(m);
	}

	public void mostrarMilitares(){
		for(Militar m : militares){
			Consola.escribir((militares.indexOf(m) + 1) + " - ");
			m.mostrarInformacionReducida();
		}
	}

	public void mostrarMilitar(String codigo){
		if(existeMilitar(codigo)){
			buscarMilitar(codigo).mostrarInformacion();
		} else {
			Consola.escribirLN("[ERROR] No existe un militar con ese codigo.");
		}
	}

	public Soldado elegirSoldadoDisponible() {
		int i = 1;
		Consola.escribirLN("Opciones de soldados disponibles: ");
		for(Militar m : militares){
			if(esSoldado(m)){
				Consola.escribir("[" + i + "] ");
				m.mostrarInformacionReducida();
			}
			i++;
		}
		int opc = Consola.leerEntero();
		while(!(militares.get(opc - 1) instanceof Soldado)){
			opc = Consola.leerEntero("[ERROR] Ingrese un numero valido: ");
		}

		return ((Soldado) militares.get(opc - 1));
	}

	private Boolean esSoldado(Militar m){
		return m instanceof Soldado;
	}

	public Boolean existenSoldados(){
		Boolean vof = false;
		for(Militar m : militares){
			vof = esSoldado(m) ? true : vof;
		}
		return vof;
	}


	// CUERPOS MILITARES 
	public void agregarCuerpo(Cuerpo c){
		if(buscarCuerpo(c.getCodigo()) == null){
			this.cuerpos.add(c);
		} else{
			Consola.escribirLN("Ya existe un cuerpo militar con el codigo " + c.getCodigo());
		}
	}

	public Cuerpo buscarCuerpo(String codigo){
		Cuerpo cuerpoB;
		int i = 0;
		
		while(i < cuerpos.size() && !codigo.equals(cuerpos.get(i).getCodigo())){
			i++;
		}
		cuerpoB = i < cuerpos.size() ? cuerpos.get(i) : null;
		return cuerpoB;
	}
	
	public Boolean existeCuerpo(String cod){
		return buscarCuerpo(cod) != null;
	}

	public void pedirDatosCuerpoMilitar(){
		String codigo = Consola.leer("Ingrese el codigo del cuerpo: ");
		while(existeCuerpo(codigo)){
			codigo = Consola.leer("[ERROR] Codigo ya existente, ingrese otro: ");
		}
		String denominacion = Consola.leer("Ingrese su denominacion: ");

		agregarCuerpo(new Cuerpo(denominacion, codigo));
	}
	
	public Cuerpo elegirCuerpoDisponible() {
		Consola.escribirLN("Opciones de cuerpos militares disponibles: ");
		for(Cuerpo c : cuerpos){
			Consola.escribir("[" + (cuerpos.indexOf(c) + 1) + "] ");
			c.mostrarInformacion();
		}
		int opc = elegirOpcion(cuerpos.size());
		return cuerpos.get(opc);
	}

	public Boolean isCuerposEmpty(){
		return cuerpos.isEmpty();
	}

	
	// COMPAÑIAS
	public void agregarCompania(Compania c){
		if(buscarCompania(c.getNumeroCompania()) == null){
			this.companias.add(c);
		} else{
			Consola.escribirLN("Ya existe una compañia con el numero " + c.getNumeroCompania());
		}
	}
	
	private Compania buscarCompania(int id) {
		Compania companiaB;
		int i = 0;
		
		while(i < companias.size() && id != companias.get(i).getNumeroCompania()){
			i++;
		}
		companiaB = i < companias.size() ? companias.get(i) : null;
		return companiaB;		
	}

	private Boolean existeCompania(int id) {
		return buscarCompania(id) != null;
	}
	
	public void pedirDatosCompania() {
		int id = Consola.leerEntero("Ingrese el numero de compañia: ");
		while(existeCompania(id)){
			id = Consola.leerEntero("[ERROR] Numero ya existente, ingrese otro: ");
		}
		String actPrincipal = Consola.leer("Ingrese la actividad principal: ");
		
		agregarCompania(new Compania(id, actPrincipal));
	}

	public Compania elegirCompaniaDisponible(){
		Consola.escribirLN("Opciones de compañias disponibles: ");
		for(Compania c : companias){
			Consola.escribir("[" + (companias.indexOf(c) + 1) + "] ");
			c.mostrarInformacion();
		}
		int opc = elegirOpcion(companias.size()); 
		return companias.get(opc);
	}

	public Boolean isCompaniasEmpty(){
		return companias.isEmpty();
	}

	// CUARTELES
	public void agregarCuartel(Cuartel c){
		if(buscarCuartel(c.getCodigo()) == null){
			this.cuarteles.add(c);
		} else{
			Consola.escribirLN("Ya existe un cuerpo militar con el codigo " + c.getCodigo());
		}
	}
	
	private Cuartel buscarCuartel(String codigo) {
		Cuartel cuartelB;
		int i = 0;
		
		while(i < cuarteles.size() && !codigo.equals(cuarteles.get(i).getCodigo())){
			i++;
		}
		cuartelB = i < cuarteles.size() ? cuarteles.get(i) : null;
		return cuartelB;		
	}

	private Boolean existeCuartel(String cod) {
		return buscarCuartel(cod) != null;
	}
	
	public void pedirDatosCuartel() {
		String codigo = Consola.leer("Ingrese el codigo de cuartel: ");
		while(existeCuartel(codigo)){
			codigo = Consola.leer("[ERROR] Codigo ya existente, ingrese otro: ");
		}
		String nombre = Consola.leer("Ingrese nombre del cuartel: ");
		String ubicacion = Consola.leer("Ingrese la ubicacion del cuartel: ");
		
		agregarCuartel(new Cuartel(codigo, nombre, ubicacion));
	}

	public Cuartel elegirCuartelDisponible(){
		Consola.escribirLN("Opciones de cuarteles disponibles: ");
		for(Cuartel c : cuarteles){
			Consola.escribir("[" + (cuarteles.indexOf(c) + 1) + "] ");
			c.mostrarInformacion();
		}
		int opc = elegirOpcion(cuarteles.size());
		return cuarteles.get(opc);
	}

	public Boolean isCuartelesEmpty(){
		return cuarteles.isEmpty();
	}
}