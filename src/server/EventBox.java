package server;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class EventBox {

	public EventBox(Shell shell, String message, String playername) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_INFORMATION | SWT.OK);

		messageBox.setText(playername);
		messageBox.setMessage(message);
		messageBox.open();
	}

}