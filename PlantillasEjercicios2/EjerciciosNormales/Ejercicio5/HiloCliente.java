package EjerciciosNormales.Ejercicio5;

public class HiloCliente extends Thread{
	
	Bean b;
	
	
	
	public HiloCliente(Bean b) {
		super();
		this.b = b;

	}



	



	public void run() {
	
		//Entrar de 1 en 1
		//Hacer el gasto entre 1 y 100
			int total=(int)(Math.random()*100)+1;
			System.out.println("Entra cliente");
			synchronized (b) {

			try {
				System.out.println("Soy el cliente "+Thread.currentThread().getId()+" me bloqueo");
				b.wait();
				System.out.println("Soy el cliente "+Thread.currentThread().getId()+" voy a pagar");
				b.total+=total;
				System.out.println("Soy el cliente "+Thread.currentThread().getId()+" me voy");
				b.numClientesAtendidos++;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				
				
			}
		
		//Pagar
		
	}

}
