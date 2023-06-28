package ejercitoargentino;

import java.io.IOException;

public class Control {

	public void ejecutar() throws IOException {

		SistemaGestion sistemaGestion = new SistemaGestion();
		boolean seguir;
		try {
			sistemaGestion = sistemaGestion.deSerializar("ejercito.txt");
			seguir = Consola.leerBoolean("SISTEMA DE GESTION EJERCITO ARGENTINO\nDesea ingresar?");
		} catch (Exception e) {
			String codigo = Consola.leer("Primer arranque del sistema.\n" + "Oficial, ingrese su codigo de clave unico: ");
			if (codigo.equals("")) {
				throw new NullPointerException("[ERROR] El codigo no puede ser nulo.");
			}
			String nombreCompleto = Consola.leer("Ingrese su nombre completo: ");
			Militar m = new Oficial(codigo, nombreCompleto, null, null, null);
			sistemaGestion.agregarMilitar(m);

			try {
				sistemaGestion.serializar("ejercito.txt");
				Consola.escribirLN("El arranque ha sido exitoso. Ahora se debe reiniciar el sistema...");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			seguir = false;
		}

		while (seguir) {
			String codigo = Consola.leer("Ingrese su codigo de militar: ");

			Militar m = sistemaGestion.buscarMilitar(codigo);

			if (m == null) {
				Consola.escribirLN("[ERROR] No existe ningun militar con ese codigo.");
			} else {
				seguir = m.proceder(sistemaGestion); 
			}
		}
	}
}