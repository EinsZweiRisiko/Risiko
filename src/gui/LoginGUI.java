package gui;

import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import ui.IO;
import de.root1.simon.exceptions.EstablishConnectionFailed;
import de.root1.simon.exceptions.LookupFailedException;

/**
 * shows a login screen
 * @author Hendrik
 *
 */
public class LoginGUI {
	
	Shell shell;
	AppClient app;
	
	/**
	 * create a new instance of a Login Window
	 * @param display the display which should be used for showing the shell
	 * @param app the calling AppClient
	 */
	public LoginGUI(Display display, final AppClient app) {
		
		this.app = app;
		
		shell = new Shell(display, SWT.MIN);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		shell.setText("EinsZweiRisiko |Login");
		
		Composite login = new Composite(shell, SWT.INHERIT_DEFAULT);
		
		Image bg = new Image(display, "assets/loginbg.png");
		
		shell.setBackgroundImage(bg);
		
		center(shell);
		
		GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns =2;
        gridLayout.horizontalSpacing = 10;
        gridLayout.verticalSpacing = 10;
        
       	login.setLayout(gridLayout);
       	
       	Label nameLabel = new Label(login, SWT.INHERIT_NONE);
       	nameLabel.setText("Name: ");
       	
       	final Text nameText = new Text(login, SWT.SINGLE);
       	
       	Label serverLabel = new Label (login, SWT.INHERIT_NONE);
       	serverLabel.setText("Server: ");
       	
       	final Text serverText = new Text(login, SWT.SINGLE);
       	serverText.setText("localhost");
       	
       	
        Button joinGame = new Button(login, SWT.PUSH);
		joinGame.setText("Spiel beitreten");
		
		joinGame.addMouseListener(new MouseListener() {
			@Override
			public void mouseDown(MouseEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void mouseDoubleClick(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void mouseUp(MouseEvent e) {
				try {
					String ip = serverText.getText();
					String name = nameText.getText();
					
					app.connect(ip, name);
				} catch (UnknownHostException e1) {
					IO.writeError("Unknown host: " + e1.getMessage());
				} catch (LookupFailedException e1) {
					IO.writeError("Lookup failed: " + e1.getMessage());
				} catch (EstablishConnectionFailed e1) {
					IO.writeError("Establish connection failed: " + e1.getMessage());
				}
				// Close the window after a successful connect
				shell.dispose();
			}
	      });
		
		Composite about = new Composite(shell, SWT.INHERIT_NONE);
		
		about.setLayout(gridLayout);
		
		Label aboutlabel = new Label(about, SWT.NONE);
		aboutlabel.setText("EinsZweiRisiko \n"+"Hochschule Bremen 2011 \n"+"Hendrik Druse, Jannes Meyer, Timur Teker");
		aboutlabel.pack();
		
		login.setBounds(0, 0, 250, 250);
		about.setBounds(0, 300, 250, 50);
		
		shell.pack();
		
		Point shellsize = shell.getSize();
		
		shell.setMinimumSize(shellsize);
		
		center(shell);
		
		shell.open();

		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
	/**
	 * centers a shell in the middle of the display
	 * @param shell
	 */
	private void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}
	
	@Override
	public void finalize() {
		shell.dispose();
	}
	
}
