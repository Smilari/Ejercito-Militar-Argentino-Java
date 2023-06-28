package ejercitoargentino;

import java.io.IOException;

public class Main {


	public static void main(String[] args) throws IOException {
		Control c = new Control();
        try {
            c.ejecutar();
        } catch (NullPointerException e) {
            Consola.escribir(e.getMessage());
        }
    }
}
	