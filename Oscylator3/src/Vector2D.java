public class Vector2D {
	//deklaracja wspolrzednych
	public double x , y;
	//konstruktor domyslny
	public Vector2D () {
		this.x = 0;
		this.y = 0;
	}
	//konstruktor z parametrami
	public Vector2D (double x, double y) {
		this.x = x;
		this.y = y;
	}
	//metoda liczaca sume wektorow
	public Vector2D suma (Vector2D vec) {
		Vector2D s = new Vector2D(x + vec.x, y + vec.y);
		return s ;
	}
	//metoda liczaca roznice wektorow
	public Vector2D roznica (Vector2D vec) {
		Vector2D r = new Vector2D(x - vec.x, y - vec.y);
		return r ;
	}
	//metoda mnozaca wektor 1 przez 3
	public Vector2D mnozenie (double k) {
		Vector2D m = new Vector2D(x*k,y*k);
		return m ;
	}
	//metoda obliczajaca dlugosc wektorow
	public double dl () {
		return Math.sqrt(this.x*this.x+this.y*this.y);
	}
	//metoda obliczajaca znormalizowane wektory
	public Vector2D normy () {
		Vector2D n = new Vector2D(this.x/dl(), this.y/dl());
		return n;
	}
}
