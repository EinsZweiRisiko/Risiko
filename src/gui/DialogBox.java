package gui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

public class DialogBox {

	/**
	 * Shows a dialog box with a message and an OK button.
	 * 
	 * @param parent
	 *            Shell
	 * @param iconType
	 *            The icon which is shown in the message box.
	 *            Possible values include:
	 *            SWT.ICON_INFORMATION,
	 *            SWT.ICON_WARNING
	 * @param title
	 * @param message
	 */
	public DialogBox(Shell parent, int iconType, String title, String message) {
		MessageBox dialog = new MessageBox(parent, iconType | SWT.OK);
		dialog.setText(title);
		dialog.setMessage(message);
		dialog.open();
	}
}