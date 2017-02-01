package MoKo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class EditMode extends Test {
	static JFrame frame;
	static JButton getLayButton, getPictureButton, fieldsColorSetup;
	static String filenameLay = null;
	static JLabel testFieldSetHeader, valueXSizeTestFieldLabel,
			valueYSizeTestFieldLabel;

	public static void RunEditMode() {

		scanZSBFrame.setVisible(false);

		frame = new JFrame();

		frame = new JFrame("MoKo");
		frame.setLayout(null);
		frame.setSize(H_Size, V_Size);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setVisible(true);

		// adding the getLayButton and getPictureButton to frame
		getLayButton = new JButton("Select Layout");
		frame.add(getLayButton);
		getLayButton.setBounds(2, 170, 120, 50);
		getLayButton.addActionListener(new EditLayoutFiles());

		getPictureButton = new JButton("Select Picture");
		frame.add(getPictureButton);
		getPictureButton.setBounds(2, 230, 120, 50);
		getPictureButton.addActionListener(new OpenPictureForEditMode());

		testFieldSetHeader = new JLabel("Set size of the fields");
		testFieldSetHeader.setVisible(true);

		testFieldSetHeader.setBounds(2, 5, 150, 20);

		valueXSizeTestFieldLabel = new JLabel("X size");
		valueXSizeTestFieldLabel.setBounds(28, 22, 50, 20);

		valueXSizeTestField = new JTextField();
		valueXSizeTestField.setVisible(true);

		valueXSizeTestField.setBounds(26, 40, 50, 20);

		valueYSizeTestFieldLabel = new JLabel("Y size");
		valueYSizeTestFieldLabel.setVisible(true);

		valueYSizeTestFieldLabel.setBounds(28, 63, 50, 20);
		// administrator
		valueYSizeTestField = new JTextField();
		valueYSizeTestField.setVisible(true);

		valueYSizeTestField.setBounds(26, 80, 50, 20);

		// /////////////////////////////////////////////////////////////////////////////////////////////////////////////

	}

}