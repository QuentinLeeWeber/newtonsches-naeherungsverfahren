import java.awt.KeyEventDispatcher;
import java.awt.event.KeyEvent;

public class MyKeyEventDispatcher implements KeyEventDispatcher 
{
	public boolean dispatchKeyEvent(KeyEvent e) {
		
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if(e.getKeyChar() == KeyEvent.VK_ENTER) {
				NWT.del();
			} else {
				NWT.push(e.getKeyChar());
			}
		} 
		return false;
	}
	
}