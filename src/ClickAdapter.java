import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class ClickAdapter extends MouseAdapter{

	@Override
	public void mouseClicked(MouseEvent e) {
		if (!Main.gameFinished) {
			// get button name
			String data[] = e.getComponent().getName().split(",");
			
			// get index of button
			int index = Integer.parseInt(data[0]) * 3 + Integer.parseInt(data[1]);
			
			// update button text
			JButton button = (JButton) Main.panel.getComponent(index);
			button.setText("Player");
			
			// update gameState
			Main.gamestate[Integer.parseInt(data[0])][Integer.parseInt(data[1])] = 1;
			
			if (Main.evaluate()[0] == 1) {
				Main.gameFinished = true;
				if (Main.evaluate()[1] == 1) {
					Main.game.setTitle("Player has won");
				}
				else {
					Main.game.setTitle("AI has won");
				}
				return;
			}
			
			// AI's turn
			Main.makeOptimalMin();
			
			if (Main.evaluate()[0] == 1) {
				Main.gameFinished = true;
				if (Main.evaluate()[1] == 1) {
					Main.game.setTitle("Player has won");
				}
				else {
					Main.game.setTitle("AI has won");
				}
				return;
			}	
		}	
	}
}
