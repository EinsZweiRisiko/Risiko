package server;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class ErrorBox {

	public ErrorBox(Shell shell, String message) {
		MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);

		messageBox.setText("Error");
		messageBox.setMessage(message);
		messageBox.open();
	}

}
