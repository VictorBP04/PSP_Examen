package EjerciciosNormales.Ejercicio10;

public class HiloConductor extends Thread{
	Bean b;
	Bean2 b2;


	
	public HiloConductor(Bean b, Bean2 b2) {
		super();
		this.b = b;
		this.b2 = b2;
	}



	public void run() {
		

		 System.out.println("Conductor esperando");

		
		synchronized (b2) {
			try {
				b2.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		synchronized (b) {
			System.out.println("Desbloqueo a todos");
			b.notifyAll();
		}
			
			
	}
}
