package server.gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import server.GameMethodsImpl;
import server.GameMethodsImpl.Phase;

/**
 * 
 * @author Hendrik
 *
 */
public class ServerMonitor {

	private Text console;
	private Composite labelComp;
	private Shell shell;
	private Display display;
	private Table table;
	private TableItem item;

	/**
	 * creates a gui for a server monitor
	 * @param serverMethods
	 * @param display
	 */
	public ServerMonitor(GameMethodsImpl serverMethods, Display display){
		this.display = display;

		shell = new Shell(display, SWT.DIALOG_TRIM | SWT.MIN);
		shell.setSize(800, 480);
		shell.setText("ZwoEinsRisiko ServerMonitor");
		shell.setLayout(new GridLayout());
		center(shell);

		Composite tableComp = new Composite(shell, SWT.NONE);
		tableComp.setLayout(new GridLayout());
		tableComp.setSize(800,480);		

		//TABLE
		table = new Table (tableComp, SWT.MULTI | SWT.BORDER | SWT.FULL_SELECTION);
		table.setLocation(0, 0);
		table.setLinesVisible (true);
		table.setHeaderVisible (true);
		GridData data = new GridData(SWT.FILL, SWT.FILL, true, true);
		data.heightHint = 45;
		table.setBounds(0, 0, 790, 45);
		table.setLayoutData(data);
		String[] titles = {"Phase", "Current Player", "source Territory" , "target Territory", "Supply To Allocate", "total units", "recieve a Bonuscard", "next Player"};
		for (int i=0; i<titles.length; i++) {
			TableColumn column = new TableColumn (table, SWT.NONE);
			column.setText (titles [i]);
		}	
		int count = 1;
		for (int i=0; i<count; i++) {
			item = new TableItem (table, SWT.NONE);
			item.setText (0, "-----------");
			item.setText (1, "-----------");
			item.setText (2, "-----------");
			item.setText (3, "-----------");
			item.setText (4, "-----------");
			item.setText (5, "-----------");
			item.setText (6, "-----------");
			item.setText (7, "-----------");
		}
		for (int i=0; i<titles.length; i++) {
			table.getColumn (i).pack ();
		}	

		labelComp = new Composite(shell, SWT.NONE);

		labelComp.setSize(800,480);	


		console = new Text(labelComp, SWT.NONE | SWT.MULTI | SWT.WRAP
				| SWT.RESIZE | SWT.V_SCROLL | SWT.BORDER);
		console.setBounds(5, 0, 775, 355);
		console.setBackground(display.getSystemColor(SWT.COLOR_BLACK));
		console.setForeground(display.getSystemColor(SWT.COLOR_WHITE));

		labelComp.pack();
	}

	public void start() {
		shell.open();
	
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
		System.exit(0);
	}

	/**
	 * centers the window
	 * @param shell
	 */
	private void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

	/**
	 * appends text to the console
	 * @param string
	 */
	public void updateConsole(String string) {
		console.append("> " + string  + "\n");
		labelComp.pack();
	}

	/**
	 * updates the shown current phase
	 * @param currentPhase
	 */
	public void updatePhase(Phase currentPhase) {
		item.setText(0, currentPhase.name());
	}
	
	/**
	 * updates the shown current player
	 * @param currentPlayer
	 */
	public void updateCurrentPlayer(String currentPlayer) {
		item.setText(1, currentPlayer);
	}

	/**
	 * updates the shown source territory
	 * @param source
	 * @param target
	 */
	public void updateSourceTarget(String source, String target) {
		item.setText(2, source);
		item.setText(3, target);
	}

	/**
	 * updates the shown supply to allocate
	 * @param supply
	 */
	public void updateSupply(int supply) {
		item.setText(4, Integer.toString(supply));
	}
	
	/**
	 * updates the shown total amount of units of the current player
	 * @param totalUnits
	 */
	public void updateTotalUnitAmmount(String totalUnits){
		item.setText(5, totalUnits);
	}

	/**
	 * updates whether the current player gets a bonuscard 
	 * @param recieveBonuscard
	 */
	public void updateRecieveBonus(String recieveBonuscard) {
		item.setText(6, recieveBonuscard);
	}

	/**
	 * updates the shown next player
	 * @param nextPlayer
	 */
	public void updateNextPlayer(String nextPlayer) {
		item.setText(7, nextPlayer);
	}
}
