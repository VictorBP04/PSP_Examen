package EjerciciosNormales.Ejercicio4;

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
				if(b.cajeraReponiendo) {
				    b.cajeraReponiendo=false;
					b.notify();
				}
				b.total+=total;
				System.out.println("Parcial:"+total);
				b.numClientesAtendidos++;
				}
		
		//Pagar
		
	}

}
