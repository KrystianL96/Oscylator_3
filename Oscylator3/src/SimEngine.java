
public class SimEngine {
	//parametry symulacji
	private double m , k , c , l , v , g  ;
	private int  x , y, xz , yz ;
	Vector2D wektor;
	//konstruktor parametrow
	public SimEngine (double m, double k , double c, double l , int x, int y , double v, double g, int xz, int yz) {
		this.m = m;
		this.k = k;
		this.c = c;
		this.l = l;
		this.x = x;
		this.y = y;
		this.v = v;
		this.g = g;
		this.xz = xz;
		this.yz = yz;
		this.wektor = new Vector2D(this.y,this.v);
	}
	//akcesory
	public double getm() {
		return this.m;
	}
	public void setm(double m) {
		this.m = m;
	}
	public double getk() {
		return this.k;
	}
	public void setk(double k) {
		this.k = k;
		}
	public double getc() {
		return this.c;
	}
	public void setc(double c) {
		this.c = c;
		}
	public double getl() {
		return this.l;
	}
	public void setl(double l) {
		this.l = l;
		}
	public int getx() {
		return this.x;
	}
	public void setx(int x) {
		this.x = x;
		}
	public int gety() {
		return this.y;
	}
	public void sety(int y) {
		this.y = y;
		}
	public double getv() {
		return this.v;
	}
	public void setv(double v) {
		this.v = v;
		}
	public double getg() {
		return this.g;
	}
	
	public void setg(double g) {
		this.g = g;
		}
	public double getxz() {
		return this.xz;
	}
	public void setxz(int xz) {
		this.xz = xz;
		}
	public double getyz() {
		return this.yz;
	}
	public void setyz(int yz) {
		this.yz = yz;
		}
	
	//metoda symulujaca
	public void symuluj(double t) {
		//metoda Runge-Kutta II rzêdu
		Vector2D k1 = new Vector2D();
		Vector2D k2 = new Vector2D();
		Vector2D pom = new Vector2D();
		double s1 = -(this.k/this.m);
		double s2 = -(this.c/this.m);
		//obliczenie wsp. k1
		k1.x = wektor.y;
		k1.y = wektor.x * s1 + wektor.y*s2 + this.g;
		k1 = k1.mnozenie(t);
		//obliczenie wsp. k2
		pom = wektor;
		pom.y += this.g;
		k2.x = pom.y;
		k2.y = pom.x * s1 + pom.y * s2 + this.g;
		k2 = k2.mnozenie(t);
		// nowe po³o¿enie
		k1 = k1.suma(k2);
		k1 = k1.mnozenie(0.5);
		wektor = wektor.suma(k1);
	}
	//metoda resetujaca
		public void reset() {
			this.v = 0;
		}
	
}
