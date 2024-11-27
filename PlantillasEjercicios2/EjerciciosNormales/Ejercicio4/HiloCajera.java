package EjerciciosNormales.Ejercicio4;

public class HiloCajera extends Thread {

	
	Bean b;
	
	
	
	public HiloCajera(Bean b) {
		this.b = b;
	}
	public void run() {
		
	synchronized (b) {
		while(b.numClientesAtendidos<3) {
			System.out.println("Cajera");
			if(!b.cajeraReponiendo) {
			   b.notify();
			   System.out.println("Atiendo a un cliente"); 
			   b.cajeraReponiendo=true;
			   try {
				b.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}

				
			}
				
			
		}
		
	}
	
}
