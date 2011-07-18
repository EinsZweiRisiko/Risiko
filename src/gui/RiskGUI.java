package gui;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Device;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import server.GameMethodsImpl.Phase;
import valueobjects.BonusCard;
import valueobjects.Player;
import valueobjects.PlayerCollection;
import valueobjects.Territory;

import commons.GameMethods;

/**
 * @author Hendrik
 */
public class RiskGUI {

	private GameMethods game;
	private Display display;
	private AppClient app;
	private Player myPlayer;
	private Shell shell;
	private Image map;
	private Image[] unitImage = new Image[6];
	private Image[] bonusImage = new Image[4];
	private Composite mainWindow;
	private int imgWidth;
	private int imgHeight;
	private final int defaultSizeX = 800;
	private final int defaultSizeY = 600;
	private final int maxSizeX = 1920;
	private final int maxSizeY = 1080;
	private HashMap<String, Territory> territories;
	private PlayerCollection players;
	private Button[] buttons = new Button[42];
	private Button[] playerButtons;
	private Text eventWindow;
	private Composite cardWindow;
	private String events = "";
	private Player currentPlayer;
	private Device dev;
	private Label[] bonusLabelStack;

	/**
	 * creates a new GUI and Game
	 * 
	 * @param display
	 *            the Display on which the shell is shown
	 */
	public RiskGUI(Display display, AppClient app, final GameMethods game) {
		this.game = game;
		this.app = app;
		this.display = display;

		this.myPlayer = app.getClient();

		territories = game.getTerritories();
		players = game.getPlayers();

		// Create a new Shell with Title
		shell = new Shell(display);
		shell.setText("EinsZweiRisiko");

		// Set size to default
		shell.setSize(defaultSizeX, defaultSizeY);

		// Create a new Composite make it default size adjust the Shell
		mainWindow = new Composite(shell, SWT.NONE);
		mainWindow.setSize(defaultSizeX, defaultSizeY);
		shell.pack();

		// Shell set minimum size to default size
		shell.setMinimumSize(shell.getBounds().width, shell.getBounds().height);

		// Composite set size to max size
		mainWindow.setSize(maxSizeX, maxSizeY);

		dev = shell.getDisplay();

		try{
			map = new Image(dev, "assets/riskClean.png");
		} catch (Exception e) {
			System.out.println("Cannot load image");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		imgWidth = map.getBounds().width;
		imgHeight = map.getBounds().height;
		
		mainWindow.setBackgroundImage(map);

		createButtons();

		createEventWindow();

		createCardWindow();


		// resize listener which auto centers the game
		shell.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event e) {
				centerImage(mainWindow);
				createButtons();
				createEventWindow();
				createCardWindow();
			}
		});

		// defines all Actions if Shell is closed, deactivated, deinconified or
		// iconified
		shell.addShellListener(new ShellListener() {
			@Override
			public void shellActivated(ShellEvent e) {
				centerImage(mainWindow);
				createButtons();
				createEventWindow();
				createCardWindow();
			}

			@Override
			public void shellClosed(ShellEvent e) {
				centerImage(mainWindow);
				createButtons();
				createEventWindow();
				createCardWindow();
			}

			@Override
			public void shellDeactivated(ShellEvent e) {
				centerImage(mainWindow);
				createButtons();
				createEventWindow();
				createCardWindow();
			}

			@Override
			public void shellDeiconified(ShellEvent e) {
				centerImage(mainWindow);
				createButtons();
				createEventWindow();
				createCardWindow();
			}

			@Override
			public void shellIconified(ShellEvent e) {
				centerImage(mainWindow);
				createButtons();
				createEventWindow();
				createCardWindow();
			}
		});

		center(shell);
		shell.open();
	}

	/**
	 * Starts the game loop
	 */
	public void start() {
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * add a new String to the Event Window
	 * 
	 * @param string
	 *            String which should be added
	 */
	private void eventWindowAppendText(String string) {
		events = string + "\n" + events;
		eventWindow.setText(events);
	}

	/**
	 * creates an eventWindow which contains all Messages which could be usefull
	 * for the User
	 */
	private void createEventWindow() {
		mainWindow.setBackgroundMode(SWT.INHERIT_DEFAULT);
		eventWindow = new Text(mainWindow, SWT.NONE | SWT.MULTI | SWT.WRAP
				| SWT.RESIZE | SWT.V_SCROLL);
		eventWindow.setBounds(0, 0, 250, 50);
		eventWindow.setText(events);
		eventWindow.setLocation(new Point(
				((imgWidth - shell.getClientArea().width) / 2
						+ shell.getClientArea().width - 250),
				((imgHeight - shell.getClientArea().height) / 2
						+ shell.getClientArea().height - 50)));
	}

	/**
	 * creates a Button on every Territory and adds a Tooltip and lable to it
	 */
	private void createButtons() {
		
		// load images for Button
		try {
			// load unit pictures
			// BLACK
			unitImage[0] = new Image(dev, "assets/unitsBLACK.png");
			// BLUE
			unitImage[1] = new Image(dev, "assets/unitsGREEN.png");
			// RED
			unitImage[2] = new Image(dev, "assets/unitsRED.png");
			// YELLOW
			unitImage[3] = new Image(dev, "assets/unitsYELLOW.png");
			// PINK
			unitImage[4] = new Image(dev, "assets/unitsPINK.png");
			// GREEN
			unitImage[5] = new Image(dev, "assets/unitsBLUE.png");

			// rescale unit icons for Buttons to 16x16px
			for (int i = 0; i < unitImage.length; i++) {
				unitImage[i] = new Image(dev, unitImage[i].getImageData().scaledTo(16,
						16));
			}
		} catch (Exception e) {
			System.out.println("Cannot load image");
			System.out.println(e.getMessage());
			System.exit(1);
		}


		// clear composite
		for (Control kid : mainWindow.getChildren()) {
			kid.dispose();
		}

		// size of the Buttons
		int buttonSizeW = 45;
		int buttonSizeH = 20;

		Territory territory;

		// NORD-AMERIKA

		// Alaska
		territory = territories.get("Alaska");
		buttons[0] = new Button(mainWindow, SWT.PUSH);
		buttons[0].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[0].setText(String.valueOf(territory.getUnits()));
		buttons[0].setBounds(615 - buttonSizeW / 2, 332 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[0].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Nordwest-Territorium
		territory = territories.get("Nordwest-Territorium");
		buttons[1] = new Button(mainWindow, SWT.PUSH);
		buttons[1].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[1].setText(String.valueOf(territory.getUnits()));
		buttons[1].setBounds(690 - buttonSizeW / 2, 342 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[1].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Alberta
		territory = territories.get("Alberta");
		buttons[2] = new Button(mainWindow, SWT.PUSH);
		buttons[2].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[2].setText(String.valueOf(territory.getUnits()));
		buttons[2].setBounds(682 - buttonSizeW / 2, 382 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[2].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Ontario
		territory = territories.get("Ontario");
		buttons[3] = new Button(mainWindow, SWT.PUSH);
		buttons[3].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[3].setText(String.valueOf(territory.getUnits()));
		buttons[3].setBounds(736 - buttonSizeW / 2, 400 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[3].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Quebec
		territory = territories.get("Quebec");
		buttons[4] = new Button(mainWindow, SWT.PUSH);
		buttons[4].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[4].setText(String.valueOf(territory.getUnits()));
		buttons[4].setBounds(790 - buttonSizeW / 2, 404 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[4].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Weststaaten
		territory = territories.get("Weststaaten");
		buttons[5] = new Button(mainWindow, SWT.PUSH);
		buttons[5].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[5].setText(String.valueOf(territory.getUnits()));
		buttons[5].setBounds(687 - buttonSizeW / 2, 445 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[5].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Oststaaten
		territory = territories.get("Oststaaten");
		buttons[6] = new Button(mainWindow, SWT.PUSH);
		buttons[6].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[6].setText(String.valueOf(territory.getUnits()));
		buttons[6].setBounds(750 - buttonSizeW / 2, 465 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[6].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Mittel-Amerika
		territory = territories.get("Mittelamerika");
		buttons[7] = new Button(mainWindow, SWT.PUSH);
		buttons[7].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[7].setText(String.valueOf(territory.getUnits()));
		buttons[7].setBounds(690 - buttonSizeW / 2, 512 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[7].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Grönland
		territory = territories.get("Grönland");
		buttons[8] = new Button(mainWindow, SWT.PUSH);
		buttons[8].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[8].setText(String.valueOf(territory.getUnits()));
		buttons[8].setBounds(843 - buttonSizeW / 2, 320 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[8].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// SÜDAMERIKA

		// Venezuela
		territory = territories.get("Venezuela");
		buttons[9] = new Button(mainWindow, SWT.PUSH);
		buttons[9].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[9].setText(String.valueOf(territory.getUnits()));
		buttons[9].setBounds(748 - buttonSizeW / 2, 571 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[9].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Peru
		territory = territories.get("Peru");
		buttons[10] = new Button(mainWindow, SWT.PUSH);
		buttons[10].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[10].setText(String.valueOf(territory.getUnits()));
		buttons[10].setBounds(757 - buttonSizeW / 2, 641 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[10].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Brasilien
		territory = territories.get("Brasilien");
		buttons[11] = new Button(mainWindow, SWT.PUSH);
		buttons[11].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[11].setText(String.valueOf(territory.getUnits()));
		buttons[11].setBounds(810 - buttonSizeW / 2, 623 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[11].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Argentinien
		territory = territories.get("Argentinien");
		buttons[12] = new Button(mainWindow, SWT.PUSH);
		buttons[12].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[12].setText(String.valueOf(territory.getUnits()));
		buttons[12].setBounds(765 - buttonSizeW / 2, 710 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[12].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// EUROPA

		// Island
		territory = territories.get("Island");
		buttons[13] = new Button(mainWindow, SWT.PUSH);
		buttons[13].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[13].setText(String.valueOf(territory.getUnits()));
		buttons[13].setBounds(910 - buttonSizeW / 2, 378 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[13].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Skandinavien
		territory = territories.get("Skandinavien");
		buttons[14] = new Button(mainWindow, SWT.PUSH);
		buttons[14].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[14].setText(String.valueOf(territory.getUnits()));
		buttons[14].setBounds(968 - buttonSizeW / 2, 383 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[14].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Großbritannien
		territory = territories.get("Großbritannien");
		buttons[15] = new Button(mainWindow, SWT.PUSH);
		buttons[15].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[15].setText(String.valueOf(territory.getUnits()));
		buttons[15].setBounds(892 - buttonSizeW / 2, 444 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[15].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// MittelEuropa
		territory = territories.get("Mitteleuropa");
		buttons[16] = new Button(mainWindow, SWT.PUSH);
		buttons[16].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[16].setText(String.valueOf(territory.getUnits()));
		buttons[16].setBounds(967 - buttonSizeW / 2, 454 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[16].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// SüdEuropa
		territory = territories.get("Südeuropa");
		buttons[17] = new Button(mainWindow, SWT.PUSH);
		buttons[17].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[17].setText(String.valueOf(territory.getUnits()));
		buttons[17].setBounds(976 - buttonSizeW / 2, 503 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[17].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// WestEuropa
		territory = territories.get("Westeuropa");
		buttons[18] = new Button(mainWindow, SWT.PUSH);
		buttons[18].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[18].setText(String.valueOf(territory.getUnits()));
		buttons[18].setBounds(915 - buttonSizeW / 2, 508 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[18].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Ukraine
		territory = territories.get("Ukraine");
		buttons[19] = new Button(mainWindow, SWT.PUSH);
		buttons[19].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[19].setText(String.valueOf(territory.getUnits()));
		buttons[19].setBounds(1042 - buttonSizeW / 2, 415 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[19].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// AFRIKA

		// Nordwestafrika
		territory = territories.get("Nordwestafrika");
		buttons[20] = new Button(mainWindow, SWT.PUSH);
		buttons[20].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[20].setText(String.valueOf(territory.getUnits()));
		buttons[20].setBounds(938 - buttonSizeW / 2, 606 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[20].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Ägypten
		territory = territories.get("Ägypten");
		buttons[21] = new Button(mainWindow, SWT.PUSH);
		buttons[21].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[21].setText(String.valueOf(territory.getUnits()));
		buttons[21].setBounds(998 - buttonSizeW / 2, 577 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[21].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Ostafrika
		territory = territories.get("Ostafrika");
		buttons[22] = new Button(mainWindow, SWT.PUSH);
		buttons[22].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[22].setText(String.valueOf(territory.getUnits()));
		buttons[22].setBounds(1037 - buttonSizeW / 2, 649 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[22].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Kongo
		territory = territories.get("Kongo");
		buttons[23] = new Button(mainWindow, SWT.PUSH);
		buttons[23].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[23].setText(String.valueOf(territory.getUnits()));
		buttons[23].setBounds(998 - buttonSizeW / 2, 678 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[23].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Südafrika
		territory = territories.get("Südafrika");
		buttons[24] = new Button(mainWindow, SWT.PUSH);
		buttons[24].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[24].setText(String.valueOf(territory.getUnits()));
		buttons[24].setBounds(999 - buttonSizeW / 2, 743 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[24].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Madagaskar
		territory = territories.get("Madagaskar");
		buttons[25] = new Button(mainWindow, SWT.PUSH);
		buttons[25].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[25].setText(String.valueOf(territory.getUnits()));
		buttons[25].setBounds(1075 - buttonSizeW / 2, 745 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[25].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// ASIEN

		// Ural
		territory = territories.get("Ural");
		buttons[26] = new Button(mainWindow, SWT.PUSH);
		buttons[26].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[26].setText(String.valueOf(territory.getUnits()));
		buttons[26].setBounds(1121 - buttonSizeW / 2, 404 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[26].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Sibirien
		territory = territories.get("Sibirien");
		buttons[27] = new Button(mainWindow, SWT.PUSH);
		buttons[27].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[27].setText(String.valueOf(territory.getUnits()));
		buttons[27].setBounds(1165 - buttonSizeW / 2, 365 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[27].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Jakutien
		territory = territories.get("Jakutien");
		buttons[28] = new Button(mainWindow, SWT.PUSH);
		buttons[28].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[28].setText(String.valueOf(territory.getUnits()));
		buttons[28].setBounds(1219 - buttonSizeW / 2, 344 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[28].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Kamtschatka
		territory = territories.get("Kamtschatka");
		buttons[29] = new Button(mainWindow, SWT.PUSH);
		buttons[29].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[29].setText(String.valueOf(territory.getUnits()));
		buttons[29].setBounds(1285 - buttonSizeW / 2, 346 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[29].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Irkutsk
		territory = territories.get("Irkutsk");
		buttons[30] = new Button(mainWindow, SWT.PUSH);
		buttons[30].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[30].setText(String.valueOf(territory.getUnits()));
		buttons[30].setBounds(1200 - buttonSizeW / 2, 416 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[30].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Mongolei
		territory = territories.get("Mongolei");
		buttons[31] = new Button(mainWindow, SWT.PUSH);
		buttons[31].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[31].setText(String.valueOf(territory.getUnits()));
		buttons[31].setBounds(1218 - buttonSizeW / 2, 460 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[31].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// China
		territory = territories.get("China");
		buttons[32] = new Button(mainWindow, SWT.PUSH);
		buttons[32].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[32].setText(String.valueOf(territory.getUnits()));
		buttons[32].setBounds(1195 - buttonSizeW / 2, 509 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[32].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Japan
		territory = territories.get("Japan");
		buttons[33] = new Button(mainWindow, SWT.PUSH);
		buttons[33].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[33].setText(String.valueOf(territory.getUnits()));
		buttons[33].setBounds(1299 - buttonSizeW / 2, 464 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[33].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Afghanistan
		territory = territories.get("Afghanistan");
		buttons[34] = new Button(mainWindow, SWT.PUSH);
		buttons[34].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[34].setText(String.valueOf(territory.getUnits()));
		buttons[34].setBounds(1105 - buttonSizeW / 2, 472 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[34].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Mittlerer Osten
		territory = territories.get("Mittlerer Osten");
		buttons[35] = new Button(mainWindow, SWT.PUSH);
		buttons[35].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[35].setText(String.valueOf(territory.getUnits()));
		buttons[35].setBounds(1054 - buttonSizeW / 2, 558 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[35].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Indien
		territory = territories.get("Indien");
		buttons[36] = new Button(mainWindow, SWT.PUSH);
		buttons[36].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[36].setText(String.valueOf(territory.getUnits()));
		buttons[36].setBounds(1153 - buttonSizeW / 2, 550 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[36].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Siam
		territory = territories.get("Siam");
		buttons[37] = new Button(mainWindow, SWT.PUSH);
		buttons[37].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[37].setText(String.valueOf(territory.getUnits()));
		buttons[37].setBounds(1214 - buttonSizeW / 2, 576 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[37].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// AUSTRALIEN

		// Indonesien
		territory = territories.get("Indonesien");
		buttons[38] = new Button(mainWindow, SWT.PUSH);
		buttons[38].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[38].setText(String.valueOf(territory.getUnits()));
		buttons[38].setBounds(1226 - buttonSizeW / 2, 664 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[38].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Neu-Guinea
		territory = territories.get("Neu-Guinea");
		buttons[39] = new Button(mainWindow, SWT.PUSH);
		buttons[39].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[39].setText(String.valueOf(territory.getUnits()));
		buttons[39].setBounds(1292 - buttonSizeW / 2, 638 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[39].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Westaustralien
		territory = territories.get("West-Australien");
		buttons[40] = new Button(mainWindow, SWT.PUSH);
		buttons[40].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[40].setText(String.valueOf(territory.getUnits()));
		buttons[40].setBounds(1258 - buttonSizeW / 2, 740 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[40].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		// Ostaustralien
		territory = territories.get("Ost-Australien");
		buttons[41] = new Button(mainWindow, SWT.PUSH);
		buttons[41].setImage(unitImage[territory.getOwner().getColor()]);
		buttons[41].setText(String.valueOf(territory.getUnits()));
		buttons[41].setBounds(1306 - buttonSizeW / 2, 712 - buttonSizeH / 2,
				buttonSizeW, buttonSizeH);
		buttons[41].setToolTipText(territory.getName() + " gehört "
				+ territory.getOwner().getName());

		//add to all buttons a Mouselistener
		for (int i = 0; i < buttons.length; i++) {
			buttons[i].addMouseListener(new MouseListener() {
				@Override
				public void mouseDoubleClick(MouseEvent e) {
					// TODO Auto-generated method stub

				}

				@Override
				public void mouseDown(MouseEvent e) {
					if(game.getActivePlayer().equals(myPlayer)){
						performAction(e);
					}
				}

				@Override
				public void mouseUp(MouseEvent e) {
					// TODO Auto-generated method stub

				}
			});
		}

		
		// Create some Buttons and Display the Player names Colors and current
		// Unitammount
		
		playerButtons = new Button[players.size()];
		int biggestButton = 0;
		
		for (int i = 0; i < players.size(); i++) {
			playerButtons[i] = new Button(mainWindow, SWT.PUSH | SWT.LEFT);
			playerButtons[i].setImage(unitImage[players.get(i).getColor()]);
			playerButtons[i].setText(players.get(i).getName() + "("
					+ players.get(i).getAllUnits() + ")");
			playerButtons[i].setAlignment(SWT.LEFT);
			playerButtons[i].pack();
			if (playerButtons[i].getBounds().width > biggestButton) {
				biggestButton = playerButtons[i].getBounds().width;
				playerButtons[i].setSize(biggestButton, 20);
			}
			playerButtons[i].setLocation(new Point(((imgWidth - shell
					.getClientArea().width) / 2 + 10), ((imgHeight - shell
					.getClientArea().height)
					/ 2
					+ shell.getClientArea().height
					- 10 - players.size() * 20 + (i * 20))));
		}

		// make all Buttons same size
		for (Button button : playerButtons) {
			if (button.getBounds().width > biggestButton) {
				biggestButton = button.getBounds().width;
				button.setSize(biggestButton, 20);
			} else if (button.getBounds().width < biggestButton) {
				button.setSize(biggestButton, 20);
			}
		}
	}

	/**
	 * updates all Buttons and their Values
	 */
	private void updateButtons() {
		for(Button button: buttons){
			
			//get the territory from the Server
			Territory territory = game.getTerritories().get(
					cutTooltip(button.getToolTipText()));
			
			//set the values and the owner, units and color
			button.setImage(unitImage[territory.getOwner().getColor()]);
			button.setText(String.valueOf(territory.getUnits()));
			button.setToolTipText(territory.getName() + " gehört "
					+ territory.getOwner().getName());
		}
	}
	
	/**
	 * opens a Dialog after MouseClick according to the Phase
	 * @param e calling Object
	 */
	private void performAction(MouseEvent e) {
		
		Phase phase = game.getCurrentPhase();
		
		Button clickedButton = (Button) e.widget;
		
		Territory territory = game.getTerritories().get(
				cutTooltip(clickedButton.getToolTipText()));
		
		if(phase.equals(Phase.PLACEMENT)){
			while(myPlayer.getSuppliesToAllocate() != 0){
				game.placeUnits(territory, 1);
				myPlayer.subtractSupplies(1);
			}
		}

		if(phase.equals(Phase.ATTACK)){
			//SOURCE TERRITORY
			//TARGET TERRITORY
			//AMOUNT
			ActionDialog ad = new ActionDialog(shell, SWT.NONE, phase, territory);
			ad.open();
		}
		
		if(phase.equals(Phase.MOVEMENT)){
			//SOURCE TERRITORY
			//TARGET TERRITORY
			//AMOUNT
			ActionDialog ad = new ActionDialog(shell, SWT.NONE, phase, territory);
			ad.open();
		}
	}

	private void createCardWindow() {
		cardWindow = new Composite(mainWindow,SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		cardWindow.setLayout(rowLayout);
		
		// load images for Bonus cards
		try {
	
			// load bonus pictures
			
			// Infantary
			bonusImage[0] = new Image(dev, "assets/bonusINF.png");
			
			// Calvary
			bonusImage[1] = new Image(dev, "assets/bonusCAL.png");
			
			// Artillery
			bonusImage[2] = new Image(dev, "assets/bonusART.png");
			
			// Joker alias Wildcard
			bonusImage[3] = new Image(dev, "assets/bonusJOK.png");
		} catch (Exception e) {
			System.out.println("Cannot load image");
			System.out.println(e.getMessage());
			System.exit(1);
		}
		
		ArrayList<BonusCard> bonuscards = myPlayer.getBonusCards();
		
		bonusLabelStack = new Label[bonuscards.size()];
		
		for(BonusCard bonusCard:bonuscards){
			Label label = new Label(cardWindow, SWT.NONE);
			int type = 0;
			
			if(bonusCard.getType().equals("Infantry")){
				type = 0;
			}
			if(bonusCard.getType().equals("Cavalry")){
				type = 1;
			}
			if(bonusCard.getType().equals("Artillery")){
				type = 2;
			}
			if(bonusCard.getType().equals("WildCard")){
				type = 3;
			}
			label.setSize(22, 32);
			label.setImage(bonusImage[type]);
			label.pack();
			cardWindow.pack();
		}
		
		cardWindow.setLocation(new Point(
				((imgWidth - shell.getClientArea().width) / 2
						+ shell.getClientArea().width - cardWindow.getBounds().width),
				((imgHeight - shell.getClientArea().height) / 2
						+ 5)));
		
		shell.update();
	}

	private void updateBonusCard() {
		cardWindow = new Composite(mainWindow,SWT.NONE);
		RowLayout rowLayout = new RowLayout();
		cardWindow.setLayout(rowLayout);
		
		ArrayList<BonusCard> bonuscards = myPlayer.getBonusCards();
		
		bonusLabelStack = new Label[bonuscards.size()];
		
		for(BonusCard bonusCard:bonuscards){
			Label label = new Label(cardWindow, SWT.NONE);
			
			int type = 0;
			
			if(bonusCard.getType().equals("Infantry")){
				type = 0;
			}
			if(bonusCard.getType().equals("Cavalry")){
				type = 1;
			}
			if(bonusCard.getType().equals("Artillery")){
				type = 2;
			}
			if(bonusCard.getType().equals("WildCard")){
				type = 3;
			}
			
			label.setSize(22, 32);
			label.setImage(bonusImage[type]);
			label.pack();
			cardWindow.pack();
		}
		
		cardWindow.setLocation(new Point(
				((imgWidth - shell.getClientArea().width) / 2
						+ shell.getClientArea().width - cardWindow.getBounds().width),
				((imgHeight - shell.getClientArea().height) / 2
						+  5)));
		
		shell.update();
		
	}

	/**
	 * Method to find out which territory the Button belongs to
	 * 
	 * @param toolTipText
	 *            of the calling Button
	 * @return the name of the Territory of the calling Button
	 */
	private String cutTooltip(String toolTipText) {
		String cutted = "";

		char[] toolTipChar = toolTipText.toCharArray();

		for (int i = 0; i < toolTipChar.length; i++) {
			if (toolTipChar[i] == ' ') {
				if (cutted.equals("Mittlerer")) {
					cutted = "Mittlerer Osten";
				}
				return cutted;
			}
			cutted += toolTipChar[i];
		}

		return cutted;
	}

	/**
	 * Centers a Shell in the middle of the Screen
	 * 
	 * @param shell
	 *            which should be centered
	 */
	private void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	/**
	 * Centers an image in the middle of the Shell
	 */
	private void centerImage(Composite window) {
		// center Composition
		int shellClientWidth = shell.getClientArea().width;
		int shellClientHeight = shell.getClientArea().height;

		Rectangle bds = window.getBounds();
		Point p = window.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		window.setLocation(-((imgWidth - shellClientWidth) / 2) + nLeft,
				-((imgHeight - shellClientHeight) / 2) + nTop);
	}

	@Override
	public void finalize() {
		shell.dispose();
	}

	/**
	 * Updates the current player after a NextPlayerAction was received.
	 */
	public void updateCurrentPlayer() {
		Player player = game.getActivePlayer();
		
		// Check whether the player equals my player
		if (player.equals(myPlayer)) {
			eventWindowAppendText("Du bist dran");
		} else {
			eventWindowAppendText(player.getName() + " ist dran.");
		}
	}

}
