package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Decorations;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import server.missions.Mission;

/**
 * 
 * @author Hendrik
 *
 */
public class MissionDialog {

	private Shell shell;
	private Display display;

	/**
	 * shows a windows showing a players mission
	 * @param myMission
	 * @param display
	 */
	public MissionDialog(Mission myMission, Display display) {
		this.display = display;
		shell = new Shell(display);
		shell.setText("Mission");
		shell.setSize(200, 80);
		center(shell);
		shell.setLayout(new RowLayout());
		Label missionText = new Label(shell, SWT.NONE | SWT.MULTI | SWT.WRAP
				| SWT.RESIZE);
		missionText.setText(myMission.getDescription());
		missionText.pack();
		shell.pack();
	}

	/**
	 * opens the window
	 */
	public void open() {
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
	}

	/**
	 * center the dialog shell in the middle of the Screen
	 * @param shell
	 */
	private void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
}
