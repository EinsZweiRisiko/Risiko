package gui;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import valueobjects.Territory;

public class AttackDialog {

	private Image[] attackDiceImage = new Image[6];
	private Image[] defendDiceImage = new Image[6];
	private Display dev;

	public AttackDialog(Shell parent, String title, String message, List<Integer> attackDices, List<Integer> defendDices, Territory source, Territory target) {

		dev = parent.getDisplay();
		final Shell shell = new Shell(parent, SWT.DIALOG_TRIM | SWT.APPLICATION_MODAL);
		shell.setSize(200, 80);
		shell.setText("Angriffsergebnis");
		center(shell);

		loadDices();

		GridLayout gridLayout = new GridLayout();
		gridLayout.numColumns = 1;
		gridLayout.makeColumnsEqualWidth = true;
		gridLayout.horizontalSpacing = 4;
		
		shell.setLayout(gridLayout);

		RowLayout verticalRowLayout = new RowLayout(SWT.VERTICAL);
		RowLayout horizontalRowLayout = new RowLayout (SWT.HORIZONTAL);

		Composite mainComp = new Composite (shell,SWT.CENTER);

		mainComp.setLayout(gridLayout);

		Composite vsComp = new Composite(mainComp, SWT.CENTER);
		
		vsComp.setLayout(horizontalRowLayout);
		
		Color red = dev.getSystemColor(SWT.COLOR_RED);
		Color blue = dev.getSystemColor(SWT.COLOR_BLUE);
		
		Font font = new Font(dev, "Arial", 12, SWT.BOLD);
		
		
		Label attackerL = new Label(vsComp, SWT.NONE);
		attackerL.setFont(font);
		attackerL.setForeground(red);
		attackerL.setText(source.getName() +  "(" + source.getUnitCount() + ")");
		attackerL.pack();
		
		Label vs = new Label (vsComp, SWT.NONE);
		vs.setFont(font);
		vs.setText("  vs.  ");
		vs.pack();
		
		Label defenderL = new Label(vsComp, SWT.NONE);
		defenderL.setFont(font);
		defenderL.setForeground(blue);
		defenderL.setText(target.getName() +  "(" + target.getUnitCount() + ")");
		defenderL.pack();
		
		Composite diceComp = new Composite(mainComp, SWT.CENTER);

		diceComp.setLayout(horizontalRowLayout);

		Composite attackDiceComp = new Composite(diceComp, SWT.NONE);
		Composite defendDiceComp = new Composite(diceComp, SWT.NONE);

		attackDiceComp.setLayout(verticalRowLayout);
		defendDiceComp.setLayout(verticalRowLayout);

		for(Integer dice:attackDices){
			System.out.println("attackDiceSize " + attackDices.size());
			if(dice == 1){
				Label aDice = new Label(attackDiceComp, SWT.NONE);
				aDice.setImage(attackDiceImage[0]);
				aDice.pack();
			}
			if(dice == 2){
				Label aDice = new Label(attackDiceComp, SWT.NONE);
				aDice.setImage(attackDiceImage[1]);
				aDice.pack();
			}
			if(dice == 3){
				Label aDice = new Label(attackDiceComp, SWT.NONE);
				aDice.setImage(attackDiceImage[2]);
				aDice.pack();
			}
			if(dice == 4){
				Label aDice = new Label(attackDiceComp, SWT.NONE);
				aDice.setImage(attackDiceImage[3]);
				aDice.pack();
			}
			if(dice == 5){
				Label aDice = new Label(attackDiceComp, SWT.NONE);
				aDice.setImage(attackDiceImage[4]);
				aDice.pack();
			}
			if(dice == 6){
				Label aDice = new Label(attackDiceComp, SWT.NONE);
				aDice.setImage(attackDiceImage[5]);
				aDice.pack();
			}
		}

		for(Integer dice:defendDices){
			if(dice == 1){
				Label dDice = new Label(defendDiceComp, SWT.NONE);
				dDice.setImage(defendDiceImage[0]);
				dDice.pack();
			}
			if(dice == 2){
				Label dDice = new Label(defendDiceComp, SWT.NONE);
				dDice.setImage(defendDiceImage[1]);
				dDice.pack();
			}
			if(dice == 3){
				Label dDice = new Label(defendDiceComp, SWT.NONE);
				dDice.setImage(defendDiceImage[2]);
				dDice.pack();
			}
			if(dice == 4){
				Label dDice = new Label(defendDiceComp, SWT.NONE);
				dDice.setImage(defendDiceImage[3]);
				dDice.pack();
			}
			if(dice == 5){
				Label dDice = new Label(defendDiceComp, SWT.NONE);
				dDice.setImage(defendDiceImage[4]);
				dDice.pack();
			}
			if(dice == 6){
				Label dDice = new Label(defendDiceComp, SWT.NONE);
				dDice.setImage(defendDiceImage[5]);
				dDice.pack();
			}
		}
		
		attackDiceComp.pack();
		defendDiceComp.pack();
		
		
		Label label = new Label(mainComp, SWT.CENTER);
		label.setText(message);
		label.pack();

		Button ok = new Button(mainComp, SWT.PUSH | SWT.CENTER);
		ok.setText("Ok");
		ok.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
			}
		});
		
		shell.pack();
		
		vsComp.setLocation(((mainComp.getBounds().width/2) - (vsComp.getBounds().width/2)), vsComp.getBounds().y);
		diceComp.setLocation(((mainComp.getBounds().width/2)-(diceComp.getBounds().width/2)), diceComp.getBounds().y);
		label.setLocation(((mainComp.getBounds().width/2) - (label.getBounds().width/2)), label.getBounds().y);
		ok.setLocation(mainComp.getBounds().width/2 - ok.getBounds().width/2, ok.getBounds().y);
		
		shell.open();
		Display display = parent.getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) display.sleep();
		}
	}

	private void loadDices() {
		try {
			attackDiceImage[0] = new Image(dev, "assets/r1.png");
			attackDiceImage[1] = new Image(dev, "assets/r2.png");
			attackDiceImage[2] = new Image(dev, "assets/r3.png");
			attackDiceImage[3] = new Image(dev, "assets/r4.png");
			attackDiceImage[4] = new Image(dev, "assets/r5.png");
			attackDiceImage[5] = new Image(dev, "assets/r6.png");

			defendDiceImage[0] = new Image(dev, "assets/b1.png");
			defendDiceImage[1] = new Image(dev, "assets/b2.png");
			defendDiceImage[2] = new Image(dev, "assets/b3.png");
			defendDiceImage[3] = new Image(dev, "assets/b4.png");
			defendDiceImage[4] = new Image(dev, "assets/b5.png");
			defendDiceImage[5] = new Image(dev, "assets/b6.png");
		} catch (Exception e) {
			System.out.println("Cannot load image");
			System.out.println(e.getMessage());
			System.exit(1);
		}
	}

	private void center(Shell shell) {

		Rectangle bds = shell.getDisplay().getBounds();

		Point p = shell.getSize();

		int nLeft = (bds.width - p.x) / 2;
		int nTop = (bds.height - p.y) / 2;

		shell.setBounds(nLeft, nTop, p.x, p.y);
	}

}
