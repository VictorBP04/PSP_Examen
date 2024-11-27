package EjerciciosNormales.Ejercicio5;

public class HiloCajera extends Thread {

	
	Bean b;
	
	
	
	public HiloCajera(Bean b) {
		this.b = b;
	}
	public void run() {
		
	
		while(b.numClientesAtendidos<3) {
			System.out.println("Soy cajera que pase el siguiente ");
			synchronized (b) {
				b.notify();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
				
			
		}
		
	}
	
}
