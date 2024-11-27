package EjerciciosNormales.Ejercicio5;

public final class PrincipalCarrefour {

	public static void main(String[] args) {
	//Crear un programa que simule el comportamiento de un supermercado.
	//Existirán 10 hilos cliente que al supermercado de un en uno,gastarán 
	// en la compra una cantidad
	// de dinero de manera aleatoria entre 1 y 100€ y 
	// tendrán que esperar a que les
    // cobre la cajera que atiende.
	// La cajera tendra que contar el dinero que lleva.
	// La cola será tipo carrefur cola única.
		
	Bean b = new Bean();
	HiloCliente hc1 = new HiloCliente(b);
	HiloCliente hc2 = new HiloCliente(b);
	HiloCliente hc3 = new HiloCliente(b);
		
	HiloCajera cajera = new HiloCajera(b);
	HiloCajera cajera2 = new HiloCajera(b);
	
	hc1.start();
	hc2.start();
	hc3.start();
	cajera.start();
	cajera2.start();
	
	try {
		hc1.join();
		hc2.join();
		hc3.join();
		cajera.join();
		cajera2.join();
	} catch (InterruptedException e) {
		e.printStackTrace();
	}
    
	System.out.println("El total de la caja:" +b.total);

	}

}
