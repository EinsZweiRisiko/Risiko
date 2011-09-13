package gui.risk;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.DisposeEvent;
import org.eclipse.swt.events.DisposeListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.GC;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

public class AwesomeCircleButton extends Canvas {

	/**
	 * Number
	 */
	Integer number;
	String numberStr;

	/**
	 * Text color
	 */
	Color white;

	/**
	 * Background color
	 */
	Color baseColor;

	Color currentColor;
	Color highlightColor;
	Color downColor;
	Font font;

	public AwesomeCircleButton(Composite parent, int style) {
		super(parent, style | SWT.DOUBLE_BUFFERED);
		Display display = parent.getDisplay();
		white = display.getSystemColor(SWT.COLOR_WHITE);
		font = new Font(display, "Helvetica", 14, SWT.BOLD);

		addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent e) {
				AwesomeCircleButton.this.paintControl(e);
			}
		});
		addDisposeListener(new DisposeListener() {
			public void widgetDisposed(DisposeEvent e) {
				AwesomeCircleButton.this.widgetDisposed(e);
			}
		});
		addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				currentColor = downColor;
				redraw();
			}
		});
		addListener(SWT.MouseEnter, new Listener() {
			public void handleEvent(Event e) {
				currentColor = highlightColor;
				redraw();
			}
		});
		Listener resetColor = new Listener() {
			public void handleEvent(Event event) {
				currentColor = baseColor;
				redraw();
			}
		};
		addListener(SWT.MouseUp, resetColor);
		addListener(SWT.MouseExit, resetColor);
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
		numberStr = this.number.toString();
		redraw();
	}

	public Color getColor() {
		return baseColor;
	}

	public void setColor(Color color) {
		baseColor = color;
		currentColor = color;
		// Calculate mouseover and mousedown colors
		float[] hsb = color.getRGB().getHSB();
		highlightColor = new Color(null, new RGB(hsb[0],
				(float) (hsb[1] - 0.3), hsb[2]));
		downColor = new Color(null, new RGB(hsb[0], hsb[1], (float) (hsb[2] - 0.05)));
		// Redraw
		redraw();
	}

	/**
	 * Draw the widget
	 * 
	 * @param e
	 */
	public void paintControl(PaintEvent e) {
		GC gc = e.gc;
		gc.setAntialias(SWT.ON);

		Rectangle area = getClientArea();
		int d = Math.min(area.width, area.height);
		if (currentColor != null) {
			gc.setBackground(currentColor);
			gc.fillOval(0, 0, d, d);
		}
		if (number != null) {
			gc.setForeground(white);
			gc.setFont(font);
			Point textSize = gc.textExtent(numberStr);
			gc.drawString(numberStr, (d - textSize.x) / 2, (d - textSize.y) / 2);
		}
	}

	public void widgetDisposed(DisposeEvent e) {
		if (currentColor != null) {
			currentColor.dispose();
			baseColor.dispose();
			highlightColor.dispose();
			downColor.dispose();
		}
		if (font != null) {
			font.dispose();
		}
	}

	@Override
	public Point computeSize(int wHint, int hHint, boolean changed) {
		//SWT.DEFAULT
		return new Point(40, 40);
	}

}