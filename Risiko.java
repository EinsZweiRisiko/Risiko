/*
Changes:

- 0.1
	- fight Funktion
	- W�rfel funktion
- 0.2
	- GUI
	

TODO:
- Statistik einf�gen
- angabe der gesamt anzahl der angreifenden

*/

import java.util.ArrayList;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Risiko extends JFrame {
	ArrayList <Integer> ang = new ArrayList<Integer>();
	ArrayList <Integer> def = new ArrayList<Integer>(); 
	JLabel angLabel = new JLabel("Angriff");
	JLabel defLabel = new JLabel("Defensive");
	//Txtfelder f�r die Anzahl der Angreifenden Soldaten
	JTextField textField1 = new JTextField("", 2);
	JTextField textField2 = new JTextField("", 2);
	
	TextArea textArea = new TextArea(30 ,25);
	JButton button = new JButton(" >> Fight <<");
	
	//Angriff RadioButtons
	JRadioButton angRadio1 = new JRadioButton("Angriff:1");
	JRadioButton angRadio2 = new JRadioButton("Angriff:2");
	JRadioButton angRadio3 = new JRadioButton("Angriff:3");
	
	//Defensive RadioButtons
	JRadioButton defRadio1 = new JRadioButton("Defensive:1");
	JRadioButton defRadio2 = new JRadioButton("Defensive:2");
	
	//RadioButton Group
	ButtonGroup angGroup = new ButtonGroup();
	ButtonGroup defGroup =  new ButtonGroup();

	//Anzhal angreifender Soldaten
	int angAnzahl;
	int defAnzahl;
	
	//Angriffz�ge
	int angriffZug;
	int maxAngriffZug;
	
	// die angreifenden W�rfel mit ihren wertigen absteigen sortiert
	int angEins = 0;
	int angZwei = 0;
	int defEins = 0;
	int defZwei = 0;
		
	// Gesamten Einheiten auf der Quell bzw. Ziell�ndern
	int angGesamt = 10;
	int defGesamt = 10;

	public static ArrayList getDice(int anzahl) {	// anzahl der angreifenden varriert
		ArrayList <Integer> dice = new ArrayList<Integer>();
		for (int i = 0; i <= anzahl - 1; i++) {
			dice.add(i, (int)((Math.random()) * 6 + 1));
		}
		return dice;
	}
	
	//
	public int getGroessteZahl(ArrayList <Integer> w�rfel) {
		int maximum = w�rfel.get(0);
		boolean in = false;
		int i = 1;
		while(i < w�rfel.size()){
			if (w�rfel.get(i) > maximum) {
				maximum = w�rfel.get(i); 
				w�rfel.remove(i);
				in = true;
			}
			i++;
		}
		if(in == false) { w�rfel.remove(0); }
		return maximum;
	}
	
	public int readInt(String Msg) {
		System.out.println(Msg);
		String line = null;
		int val = 0;
		try {
			BufferedReader is = new BufferedReader(
			new InputStreamReader(System.in));
			line = is.readLine();
			val = Integer.parseInt(line);
		} catch (NumberFormatException ex) {
			System.err.println("Not a valid number: " + line);
		} catch (IOException e) {
			System.err.println("Unexpected IO ERROR: " + e);
		}
		//System.out.println("I read this number: " + val);
		return val;
	}
	
	public void fight() {
		/*Benutzereingabe muss �berpr�ft werden mit Einheiten Anzahl (angGesamt und defGesamt) auf dem Angriff bzw Zielland
		*  angAnzahl darf nur 2 oder 3 sein wenn eine Einheit auf dem Quellland bleibt
		*  bei Defense land muss min. eine Einheite vorhanden sein heisst die Zahl darf nur 1 oder 2 sein.
		*/  
		//Gesamt angreifende Zahl Eingabe
		
		angGroup.getSelection();
		//hier werden die w�rfel Anzahl festgelegt je nach anzahl der Einheiten auf dem Land
		ang = getDice(angAnzahl);
		def = getDice(defAnzahl); 
	
		
		/* generiert die gr��ten Zahlen im Integer f�r den Angriff es gilt:
			f�r den Angriff:
			angAnzahl = 2 dann nur eine Zahl zum k�mpfen
			angAnzahl = 3 dann zwei Zahlen zum k�mpfen
		
			f�r die Defensive:
			defAnzahl = 1 dann eine Zahl zum verteidigen
			defAnzahl = 2 dann zwei Zahlen zum verteidigen
		*/
		
		// Kampfgeschehen ausrechnen der Verluste
		if(angAnzahl == 1) {
			angEins = getGroessteZahl(ang);
			angriffZug++;
			textArea.append("------ RUNDE ("+angriffZug+") -------- \r\n");
			// 1 gegen 1
			if(defAnzahl == 1) {
				defEins = getGroessteZahl(def);
				
				if(angEins > defEins) {
					defGesamt--;
					System.out.println("Angriff:" +angEins +" schl�gt Defensive: "+ defEins);
					textArea.append("Angriff:" +angEins +" schl�gt Defensive: "+ defEins+"\r\n");
				} else {
					angGesamt--;
					System.out.println("Defensive:" +defEins+" schl�gt Angriff: "+angEins);
					textArea.append("Defensive:" +defEins+" schl�gt Angriff: "+angEins+"\r\n");
				}
			}
			// 1 gegen 2
			if(defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);
				
				if(angEins > defEins) {
					defGesamt--;
					System.out.println("Angriff: "+angEins+" schl�gt Defensive: "+defEins);
					textArea.append("Angriff: "+angEins+" schl�gt Defensive: "+defEins+"\r\n");
				}else {
					angGesamt--;
					System.out.println("Defensive: "+defEins+" schl�gt Angriff: "+angEins);
					textArea.append("Defensive: "+defEins+" schl�gt Angriff: "+angEins+"\r\n");
				}
			}
		}
		
		if(angAnzahl == 2 ) { // mit Abfrage wegen einem Soldat auf dem Quellland
			angEins = getGroessteZahl(ang);
			angZwei = getGroessteZahl(ang);
			angriffZug++;
			textArea.append("------ RUNDE ("+angriffZug+") -------- \r\n");
			// 2 gegen 1
			if(defAnzahl == 1) {
				defEins = getGroessteZahl(def);
				
				if(angEins > defEins) {
					defGesamt--;
					System.out.println("Angriff: "+angEins+" schl�gt Defensive: "+defEins);
					textArea.append("Angriff: "+angEins+" schl�gt Defensive: "+defEins+"\r\n");
				}else {
					angGesamt--;
					System.out.println("Defensive: "+defEins+" schl�gt Angriff: "+angEins);
					textArea.append("Angriff: "+angEins+" schl�gt Defensive: "+defEins+"\r\n");
				}
			}
			// 2 gegen 2
			if(defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);
				
				// ersten W�rfel treten gegeneinander an
				if(angEins > defEins) {
					defGesamt--;
					System.out.println("Angriff: " +angEins+" schl�gt Defensive: "+defEins);
					textArea.append("Angriff: " +angEins+" schl�gt Defensive: "+defEins+"\r\n");
				} else {
					angGesamt--;
					System.out.println("Defensive: "+defEins+" schl�gt Angriff: "+angEins);
					textArea.append("Defensive: "+defEins+" schl�gt Angriff: "+angEins +"\r\n");
				}
				
				// zweiten W�rfel treten gegen einander an
				if(angZwei > defZwei) {
					defGesamt--;
					System.out.println("Angriff: "+angZwei+" schl�gt Defensive: "+defZwei);
					textArea.append("Angriff: "+angZwei+" schl�gt Defensive: "+defZwei+"\r\n");
				}else {
					angGesamt--;
					System.out.println("Defensive:" +defZwei+" schl�gt Angriff: "+angZwei);
					textArea.append("Defensive:" +defZwei+" schl�gt Angriff: "+angZwei+"\r\n");
				}
			}
		} else {
			//System.out.println("Sei m�ssen min. 1 Soldaten auf dem Angreifenden Land lassen. Geben sie eine neue Anzahl an!");
			//TODO : neu eingabe der anzahl der Soldaten FUNKTION
		}
		
		if(angAnzahl == 3) {
			angEins = getGroessteZahl(ang);
			angZwei = getGroessteZahl(ang);	
			angriffZug++;
			textArea.append("------ RUNDE ("+angriffZug+") -------- \r\n");
			// 2 gegen 1
			if(defAnzahl == 1) {
				defEins = getGroessteZahl(def);
				
				if(angEins > defEins) {
					defGesamt--;
					System.out.println("Angriff: "+angEins+" schl�gt Defensive: "+defEins);
					textArea.append("Angriff: "+angEins+" schl�gt Defensive: "+defEins+"\r\n");
				}else {
					angGesamt--;
					System.out.println("Defensive: "+defEins+" schl�gt Angriff: "+angEins);
					textArea.append("Defensive: "+defEins+" schl�gt Angriff: "+angEins+"\r\n");
				}
			}
			
			// 2 gegen 2
			if(defAnzahl == 2) {
				defEins = getGroessteZahl(def);
				defZwei = getGroessteZahl(def);
				// ersten W�rfel treten gegeneinander an
				if(angEins > defEins) {
					defGesamt--;
					System.out.println("Angriff: "+angEins+" schl�gt Defensive: "+defEins);
					textArea.append("Angriff: "+angEins+" schl�gt Defensive: "+defEins+"\r\n");
				} else {
					angGesamt--;
					System.out.println("Defensive: "+defEins+" schl�gt Angriff: "+angEins);
					textArea.append("Defensive: "+defEins+" schl�gt Angriff: "+angEins+"\r\n");
				}
				
				// zweiten W�rfel treten gegen einander an
				if(angZwei > defZwei) {
					defGesamt--;
					System.out.println("Angriff: "+angZwei+" schl�gt Defensive: "+defZwei);
					textArea.append("Angriff: "+angZwei+" schl�gt Defensive: "+defZwei+"\r\n");
				}else {
					angGesamt--;
					System.out.println("Angriff: "+angZwei+" schl�gt Defensive: "+defZwei);
					textArea.append("Angriff: "+angZwei+" schl�gt Defensive: "+defZwei+"\r\n");
				}
			}
		}
	}
	
	//erstellt das Frame und behandelt die Listener
	public Risiko() {
		//constructor
		super("Risiko Fight");
		getContentPane().setLayout(new FlowLayout());
		//Add action listener to button
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Execute when button is pressed
				fight();
			}
		});     
		
		//RadioButton Actionlistener
		angRadio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				angAnzahl = 1;
			}
		});  
		angRadio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				angAnzahl = 2;
			}
		}); 
		angRadio3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				angAnzahl = 3;
			}
		}); 
		
		defRadio1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defAnzahl = 1;
			}
		}); 
		defRadio2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				defAnzahl = 2;
			}
		}); 
		
		//Button Angriff Group zugeh�rigkeit
		angGroup.add(angRadio1);
		angGroup.add(angRadio2);
		angGroup.add(angRadio3);
		
		//Button Defense Group zugeh�rigkeit
		defGroup.add(defRadio1);
		defGroup.add(defRadio2);

		//textArea
		textArea.setEditable(false);
		add(new JScrollPane(textArea));
		textArea.setCaretPosition(0);

		//hinzuf�gen der GUI Elementen
		getContentPane().add(textField1);
		getContentPane().add(textField2);
		getContentPane().add(angRadio1);
		getContentPane().add(angRadio2);
		getContentPane().add(angRadio3);
		
		getContentPane().add(defRadio1);
		getContentPane().add(defRadio2);
		
		//Textarea und Button
		getContentPane().add(textArea);
		getContentPane().add(button);
		
		// Frame eigene Funktionen
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(300, 700);
		setVisible(true);
	}
	
	public static void main (String[] args) {
		Risiko r = new Risiko();
	}
}