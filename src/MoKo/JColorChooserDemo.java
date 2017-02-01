package MoKo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class JColorChooserDemo extends JFrame {
	JPanel panel;

	// Constructor to setup the UI components and event handlers
	public JColorChooserDemo() {
		Test.bgColor = Color.gray;
		// Panel's background color

		panel = new JPanel(new BorderLayout());

		JButton btnColor = new JButton("Chose Color");
		panel.add(btnColor);
		btnColor.setBounds(2, 0, 180, 40);
		panel.setLayout(null);

		// Declaration of button for saving the foreground test fields color
		// setting
		Test.testFieldForegroundColorButtonSet = new JButton(
				"Add color to foreground");
		Test.testFieldForegroundColorButtonSet.setBounds(2, 190, 180, 40);
		panel.add(Test.testFieldForegroundColorButtonSet);
		Test.testFieldForegroundColorButtonSet
				.addActionListener(new SetForegroundColorOfFields());

		// Declaration of button for saving the background test fields color
		// setting
		Test.testFieldBackgroundColorButtonSet = new JButton(
				"Add color to background");
		Test.testFieldBackgroundColorButtonSet.setBounds(2, 230, 180, 40);
		panel.add(Test.testFieldBackgroundColorButtonSet);
		Test.testFieldBackgroundColorButtonSet
				.addActionListener(new BackgroundColorFieldsSetter());

		btnColor.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				Color color = JColorChooser.showDialog(JColorChooserDemo.this,
						"Choose a color", Test.bgColor);
				if (color != null) { // new color selected
					Test.bgColor = color;
				}
				panel.setBackground(Test.bgColor); // change panel's background
				// color
				System.out.println(Test.bgColor);
			}
		});

		setAlwaysOnTop(true);
		setContentPane(panel);

		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setTitle("Color Set");
		setSize(190, 300);
		setResizable(false);
		// center the application window
		setVisible(true); // show it
	}

	// Class for adding a ForegroundColor in all testFi
	// elds and save the values of rgb in to the file TEST_FIELDS_PROPERTY.txt

	public class SetForegroundColorOfFields implements ActionListener {

		public void actionPerformed(ActionEvent arg0) {

			BufferedWriter outColor = null;
			try {
				outColor = new BufferedWriter(new FileWriter(
						"C://MoKo//TEST_FIELDS_PROPERTY.txt"));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			StringBuilder colorValue = new StringBuilder();
			String col = String.valueOf(Test.bgColor);
			char[] array = col.toCharArray();

			// Filter for values from JColorChooser.We takes only the literal
			// for RGB color.Example:(125,4,53)
			// and save these numbers into TEST_FIELDS_PROPERTY.txt file in
			// C:/MoKo folder.
			for (int i = 0; i < array.length; i++) {

				if (array[i] == ('1')
						|| (array[i] == ('2') || (array[i] == ('3') || (array[i] == ('4') || (array[i] == ('5') || (array[i] == ('6') || (array[i] == ('7') || (array[i] == ('8') || (array[i] == ('9') || (array[i] == ('0') || (array[i] == (',')))))))))))) {
					colorValue.append(array[i]);

				}

			}
			// Read "C://MoKo//TEST_FIELDS_PROPERTY.txt" file for saving the
			// parameters

			// Saving the rgb values..

			try {
				outColor.write(String.valueOf("<FOREGROUND_COLOR_FIELDS>["
						+ colorValue + "]</FOREGROUND_COLOR_FIELDS>"));

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			// System.out.println(colorValue);
			System.out.println(colorValue);
			try {

				outColor.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
