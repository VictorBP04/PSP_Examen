package EjerciciosNormales.Ejercicio10;

public class Principal {

	public static void main(String[] args) {
		
		Bean b=new Bean();
		Bean2 b2=new Bean2();
		HiloVisitante h1 = new HiloVisitante(b,b2);
		HiloVisitante h2 = new HiloVisitante(b,b2);
		HiloVisitante h3 = new HiloVisitante(b,b2);
		HiloVisitante h4 = new HiloVisitante(b,b2);
		HiloVisitante h5 = new HiloVisitante(b,b2);
		HiloVisitante h6 = new HiloVisitante(b,b2);
		HiloVisitante h7 = new HiloVisitante(b,b2);
		HiloVisitante h8 = new HiloVisitante(b,b2);
		HiloVisitante h9 = new HiloVisitante(b,b2);
		HiloVisitante h10 = new HiloVisitante(b,b2);
		
		HiloConductor hc = new HiloConductor(b,b2);
		
		h1.start();
		h2.start();
		h3.start();
		h4.start();
		h5.start();
		h6.start();
		h7.start();
		h8.start();
		h9.start();
		h10.start();
		hc.start();

	}

}
