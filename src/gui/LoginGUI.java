package gui;

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

public class LoginGUI {
	
	Shell shell;
	
	public LoginGUI(Display display){
		shell = new Shell(display);
		shell.setText("EinsZweiRisiko -- Login");
		
		Composite login = new Composite(shell, SWT.INHERIT_NONE);
		login.setSize(200,100);
		
		Image bg = new Image(display, "assets/loginbg.png");
		
		login.setBackgroundImage(bg);
		
		center(shell);
		
		GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 2;
        gridLayout.horizontalSpacing = 10;
        gridLayout.verticalSpacing = 10;
       	login.setLayout(gridLayout);
       	
       	Label nameLabel = new Label(login, SWT.INHERIT_NONE);
       	nameLabel.setText("Name: ");
       	
       	Text nameText = new Text(login, SWT.SINGLE);
       	
       	Label serverLabel = new Label (login, SWT.INHERIT_NONE);
       	serverLabel.setText("Server: ");
       	
       	Text serverText = new Text(login, SWT.SINGLE);
       	
       	Button createGame = new Button(login, SWT.PUSH);
		createGame.setText("Spiel erstellen");
		createGame.addMouseListener(new MouseListener() {
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
				// TODO Auto-generated method stub
				
			}
	      });
       	
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
				// TODO Auto-generated method stub
				
			}
	      });
		login.pack();
		shell.pack();
		shell.open();

		while (!login.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}
	
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
	
	public static void main(String[] args) {
		Display display = new Display();
		LoginGUI logingui = new LoginGUI(display);		
		logingui.finalize();
		display.dispose();
	}
	
}
