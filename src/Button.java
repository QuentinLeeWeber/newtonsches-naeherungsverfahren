import java.awt.Color;
import java.awt.Graphics;

public class Button {
	
	String title = "";
	int x, y, sizeX, sizeY;
	boolean negative = false;
	
	public Button(int startx, int starty, String startTitle) {
		x = startx;
		y = starty;
		title = startTitle;
		sizeX = 140;
		sizeY = 30;
	}
	
	public void render(Graphics g) {
		if (NWT.gui.mouseX >= x && NWT.gui.mouseX <= x + sizeX && NWT.gui.mouseY >= y && NWT.gui.mouseY <= y + sizeY) {
			negative = true;
		} else {
			negative = false;
		}
		g.setColor(new Color(70, 70, 70));
		g.fillRect(x + 3, y + 3, sizeX, sizeY);
		if(negative) {
			g.setColor(new Color(2, 14, 102));
		} else {
			g.setColor(new Color(255, 255, 255));
		}
		g.fillRect(x, y, sizeX, sizeY);
		g.setColor(new Color(0, 0, 0));
		if(negative) {
			g.drawRect(x + 1, y + 1, sizeX, sizeY);
		} else {
			g.drawRect(x, y, sizeX, sizeY);
		}
		if(negative) {
			g.setColor(new Color(255, 255, 255));
		}
		g.setFont(g.getFont().deriveFont(29F));
		if(negative) {
			g.drawString(title, x + 2 + 1, y + sizeY - 3 + 1);
		} else {
			g.drawString(title, x + 2, y + sizeY - 3);
		}
		
	}
	
	public void click(int startx, int starty) {
		if (startx >= x && startx <= x + sizeX && starty >= y && starty <= y + sizeY) {
			doing();
		}
	}
	
	void doing() {
		
	}
}