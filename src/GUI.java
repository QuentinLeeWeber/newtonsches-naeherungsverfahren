import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.KeyboardFocusManager;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Arrays;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI extends JPanel implements Runnable {
	private Thread t;
	JFrame frame = new JFrame();

	int mouseX, mouseY;

	MyMouseMotionListener MML = new MyMouseMotionListener();
	MyMouseListener ML = new MyMouseListener();

	ArrayList<Spalte> spalten = new ArrayList<Spalte>();
	ArrayList<Button> buttons = new ArrayList<Button>();
	
	BufferedImage i;

	public GUI() {
		frame.setSize(1600 + 16, 500 + 39);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.add(this);
		frame.setTitle("Newton Anäherungsverfahren");
		try {
			i = ImageIO.read(new File("tex/icon.png"));
		} catch (IOException e) {}
		frame.setIconImage(i);
		buttons.add(new Button(900, 300, "Reset") {
			@Override
			void doing() {
				for (int i = 0; i < spalten.size(); i++) {
					spalten.get(i).content = "0";
				}
			}
		});
		buttons.add(new Button(900, 350, "Rechnen") {
			@Override
			void doing() {
				NWT.rechnen();
			}
		});
	}

	public void update() {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		g.setColor(new Color(230, 230, 230));
		g.fillRect(0, 0, 2000, 500);
		for (int i = 0; i < spalten.size(); i++) {
			spalten.get(i).render(g);
		}
		g.setColor(new Color(0, 0, 0));
		g.drawString(new String(Arrays.toString(NWT.a)), 1100, 370);
		g.drawString(new String(Arrays.toString(NWT.b)), 1100, 400);
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).render(g);
		}
		for(int i = 0; i < NWT.ergebnisse.size();i++) {
			g.setColor(new Color(0, 0, 0));
			g.setFont(g.getFont().deriveFont(29F));
			g.drawString(new String((i + 1) + ": (" + NWT.ergebnisse.get(i) + " / 0)"), 20, i * 30 + 150);
		}
	}

	@Override
	public void run() {
		try {
			while (true) {
				update();
				Thread.sleep(0);
			}
		} catch (InterruptedException e) {
		}
	}

	public void start() {
		this.addMouseMotionListener(MML);
		this.addMouseListener(ML);
		KeyboardFocusManager m = KeyboardFocusManager.getCurrentKeyboardFocusManager();
		MyKeyEventDispatcher dispatcher = new MyKeyEventDispatcher();
		m.addKeyEventDispatcher(dispatcher);
		if (t == null) {
			t = new Thread(this, "GUI");
			t.start();
		}
		for (int i = 0; i < 10; i++) {
			spalten.add(new Spalte(10 + i * 150, 50, i, 'a'));
			spalten.add(new Spalte(10 + 110 + i * 150, 23, i, 'b'));
		}
	}

	public void MouseDragged(int x, int y) {
		mouseX = x;
		mouseY = y;
	}

	public void click() {
		for (int i = 0; i < spalten.size(); i++) {
			spalten.get(i).click(mouseX, mouseY);
		}
		for (int i = 0; i < buttons.size(); i++) {
			buttons.get(i).click(mouseX, mouseY);
		}
	}

	public void push(char c) {
		for (int i = 0; i < spalten.size(); i++) {
			spalten.get(i).push(c);
		}
	}

	public void del() {
		for (int i = 0; i < spalten.size(); i++) {
			spalten.get(i).del();
		}
	}
}