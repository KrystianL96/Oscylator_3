import javax.swing.*;
import java.util.Timer;
import java.awt.*;
import java.awt.event.*;

public class SpringApplet extends JApplet implements MouseListener, MouseMotionListener, ActionListener {

	private static final long serialVersionUID = 1L;
	//pola do przechowywania obiektow
	private int xz , yz ;
	private SimEngine engine;
	private SimTask task;
	private Timer time;
	private boolean	mousedragging;
	//pola do przechowywania interfejsu graficznego
	private JPanel j;
	private Button sym;
	private TextField tm , tk , tc , tg , tl;
	private Label lm , lk , lc , lg , ll;
	//przeciazona metoda init()
	public void init(){
		//przypisanie wartoœci 0 dla stanu myszy
		mousedragging = false;
		//dodanie nas³uchiwaczy
		addMouseListener(this);
		addMouseMotionListener(this);
		//inicjalizacja pol obiektami
		tm = new TextField (10);
		tk = new TextField (10);
		tc = new TextField (10);
		tg = new TextField (10);
		tl = new TextField (10);
		lm = new Label ("Masa");
		lk = new Label ("Wspó³czynnik spê¿ystoœci");
		lc = new Label ("Wspó³czynnik t³umienia");
		lg = new Label ("Przyspieszenie grawitacyjne");
		ll = new Label ("D³ugoœæ spoczynkowa linki");
		j = new JPanel ();
		sym = new Button ("Symulacja");
		//nasluchiwacz przycisku
		sym.addActionListener(this);
		//dodanie komponetów do appletu
		j.add(lm);
		j.add(tm);
		j.add(lk);
		j.add(tk);
		j.add(lc);
		j.add(tc);
		j.add(lg);
		j.add(tg);
		j.add(ll);
		j.add(tl);
		j.add(sym);
		add(j);
		
		//utworzenie obiektow i uruchomienie timera
		engine = new SimEngine(1,0.7,0.8,3,0,0,0.2,9.81,0,0);
		task = new SimTask(0.1,engine,this);
		time = new Timer();
		time.scheduleAtFixedRate(task,50,50); 
		}     
	
	//przeciazona metoda paint()
	public void paint (Graphics g){
		//wyczyszczenie appletu
		g.clearRect(0, 0, getWidth(), getHeight());
		//narysowanie lini sprezyny
		g.setColor(Color.BLUE); 
		g.drawLine(getWidth()/2, 0, getWidth()/2, getHeight()/2-50+(int)engine.wektor.x +xz);
		//narysowanie owalu dla masy
		g.setColor(Color.BLACK);
		g.drawOval(getWidth()/2-50, getHeight()/2-50+(int)engine.wektor.x +xz, 100, 100);

	}


	@Override
	public void actionPerformed(ActionEvent ev) {
		Object but = ev.getSource();
				if ( but == sym) {
					//wy³¹czenie timera i reset symulacji
					time.cancel();
					time.purge();
					engine.reset();
					//przypisanie wartosci parametrow
					engine.setx(0);
					engine.sety(0);		
					engine.setm( Double.parseDouble(tm.getText()));
					engine.setk( Double.parseDouble(tk.getText()));
					engine.setc( Double.parseDouble(tc.getText()));
					engine.setg( Double.parseDouble(tg.getText()));
					engine.setl( Double.parseDouble(tl.getText()));
					task = new SimTask(0.1,engine,this);
					time = new Timer();
					time.scheduleAtFixedRate(task,50,50); 
					repaint();
				}
		
	}

	@Override
	public void mouseDragged(MouseEvent w) {
		//czy nastepuje przeciaganie
		if (mousedragging) {
			//odczytanie polozenia kursora
			xz = w.getY();
			yz = w.getX();
			//ustawienie masy w miejsu kursora
			xz = (xz - getHeight()/2 - (int)engine.wektor.x);
			yz =(yz - getHeight()/2);
			//wywolanie repaint
			this.repaint();
		}
		// metoda consume
		w.consume();
	}
		

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent w) {
		int x,y;
		//odczytanie po³o¿enia kursora
		x = w.getY();
		y = w.getX();
		//czy kursor znajduje siê wewn¹trz elipsy masy
		if ( y >= getWidth()/2-50 && y <= getWidth()/2+50 && x >= getHeight()/2-50+(int)engine.wektor.x +xz &&
			x <= getHeight()/2+50+(int)engine.wektor.x +xz) {
		//wy³¹czenie timera i reset symulacji
				time.cancel();
				time.purge();
				engine.reset();
		// wartosc 1 dla stanu myszy
				mousedragging = true;
		}
		// metoda consume
		w.consume();
	}

	@Override
	public void mouseReleased(MouseEvent w) {
		//czy wystêpuje przeciaganie
		if (mousedragging) {
			//wlaczenie timera, 0 dla stanu myszy
			task = new SimTask(0.1,engine,this);
			time = new Timer();
			time.scheduleAtFixedRate(task,50,50); 
			mousedragging = false;
		}
		//metoda consume
		w.consume();
	}
}
