import java.math.BigDecimal;

public class Newton {
	
	double x = 0;
	double oldx = 0;
	int loops = 0;
	
	public Newton(float StartX){
		x = StartX;
	}
	
	void rechne(int id) {
		System.out.println();
		System.out.println();
		System.out.println("Newton ID: " + (id + 1) + "  Start bei: " + x);
		System.out.println();
		while (true) {
			
			oldx = x;
			
			x = x - (f(oldx) / f1(oldx));
			
			System.out.println(x);
			
			loops++;
			
			if(Math.round(x * Math.pow(10, NWT.genau - 1)) == Math.round(oldx * Math.pow(10, NWT.genau - 1))) {
				System.out.println("Annäherungswert ist bei Rund: " + new BigDecimal(x).toPlainString());
				NWT.ergebnisse.add((float) x);
				break;
			}
			
			if(loops == 10000) {
				System.out.println("Zeit zu ende");
				NWT.ergebnisse.add((float) x);
				break;
			}
		}
	}
	
	public double f(double x) {
		float back = 0;
		for(int i = 0; i < 10;i++) {
			back += NWT.a[i] * Math.pow(x, NWT.b[i]);
		}
		return back;
	}
	
	public double f1(double x){
		float back = 0;
		for(int i = 0; i < 10;i++) {
			back += NWT.a1[i] * Math.pow(x, NWT.b1[i]);
		}
		return back;
	}
}