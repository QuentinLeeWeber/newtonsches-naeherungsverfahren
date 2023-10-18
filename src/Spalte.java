import java.awt.Color;
import java.awt.Graphics;

public class Spalte {
	int x, y, sizeX, sizeY, number;
	boolean pressed;
	char type;
	String content = "0";

	Color col = new Color(255, 255, 255);

	public Spalte(int startX, int startY, int startNumber, char startType) {
		x = startX;
		y = startY;
		sizeX = 80;
		sizeY = 30;
		if(startType == 'b') {
			sizeX = (int) (sizeX * 0.7);
			sizeY = (int) (sizeY * 0.7);
		}
		type = startType;
		number = startNumber;
	}

	public void render(Graphics g) {
		g.setColor(new Color(70, 70, 70));
		g.fillRect(x + 3, y + 3, sizeX, sizeY);
		g.setColor(col);
		g.fillRect(x, y, sizeX, sizeY);
		g.setColor(new Color(0, 0, 0));
		g.drawRect(x, y, sizeX, sizeY);
		if(type == 'a') {
			g.setFont(g.getFont().deriveFont(29F));
		} else {
			g.setFont(g.getFont().deriveFont(21F));
		}
		g.drawString(new String(content), x + 2, y + sizeY - 3);
		if(type == 'a') {
			g.drawString(new String("* X"), x + 2 + sizeX, y + sizeY - 3);
			g.drawString(new String("+"), x - 21, y + sizeY - 3);
		}
		if (type == 'a') {
			try {
				NWT.a[number] = Float.parseFloat(content);
			} catch (Exception e) {
				g.setColor(new Color(255, 0, 0));
				g.setFont(g.getFont().deriveFont(30F));
				g.drawString(new String("keine Zahl"), 300, 400);
			}
		} else if (type == 'b') {
			try {
				NWT.b[number] = Float.parseFloat(content);
			} catch (Exception e) {
				g.setColor(new Color(255, 0, 0));
				g.setFont(g.getFont().deriveFont(30F));
				g.drawString(new String("keine Zahl"), 300, 400);
			}
		}
	}

	public void click(int startx, int starty) {
		if (startx >= x && startx <= x + sizeX && starty >= y && starty <= y + sizeY) {
			col = new Color(240, 240, 240);
			pressed = true;
		} else {
			col = new Color(255, 255, 255);
			pressed = false;
		}
	}

	public void push(char c) {
		if (pressed == true) {
			// System.out.println(c);
			try {
				content = new String(content + c);
			} catch (Exception e) {
			}
		}
	}

	public void del() {
		if (pressed) {
			if (content.length() == 0) {
				content = "0";
			} else {
				content = content.substring(0, content.length() - 1);
			}
		}
	}
}