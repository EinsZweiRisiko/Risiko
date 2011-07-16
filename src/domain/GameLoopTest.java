package domain;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import net.miginfocom.swing.MigLayout;

public class GameLoopTest {

	private JFrame frame;
	private JButton btnGameLoop;
	private Object key = new Object();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GameLoopTest window = new GameLoopTest();
		window.frame.setVisible(true);

		// Game Loop starten
		window.loop();
	}

	/**
	 * Create the application.
	 */
	public GameLoopTest() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new MigLayout("", "[30][30][30][30][30][][30][30][30]", "[40][40][40][40][40]"));
		
		btnGameLoop = new JButton("Game Loop");
		btnGameLoop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent ae) {
				System.out.println("Aktion " + btnGameLoop.getActionCommand() + " (z.B. Server-Aufruf) ausgef�hrt!");
				synchronized (key) {
					key.notify();					
				}
			}
		});
		frame.getContentPane().add(btnGameLoop, "cell 4 2 2 1");
	}

	/**
	 * The game loop
	 */
	public void loop() {
		int phase = 0;
		do {
			// Server nach n�chster Phase fragen
			phase++;
			
			// GUI aktualisieren
			btnGameLoop.setText("Game Loop in Phase " + phase);
			
			// Auf Ausf�hrung der GUI-Aktion warten (z.B. Angriff)
			try {
				System.out.println("GUI ist aktualisiert. Game Loop gestoppt, bis Aktion durchgef�hrt wurde.");
				synchronized (key) {
					key.wait();					
				}
			} catch (InterruptedException e) {
				// Nichts zu tun
			}
			System.out.println("Loop geht weiter...");
		} while (phase < 5);
	}
}

