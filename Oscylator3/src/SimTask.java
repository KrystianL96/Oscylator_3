import java.util.TimerTask;

public class SimTask extends TimerTask{
	//pola klasy dla obiektow
	private SimEngine engine;
	private SpringApplet aplet;
	private double t ;
	//konstruktor przypisywania do pol klasy
	public SimTask(double i ,SimEngine engine ,SpringApplet aplet){
		this.engine = engine;
		this.aplet = aplet;
		this.t = i;
	}
	//przeciazona metoda run()
	public void run() {
		engine.symuluj(t);
		aplet.repaint();
	}
}
