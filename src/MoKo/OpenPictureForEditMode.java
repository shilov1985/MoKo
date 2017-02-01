package MoKo;

//This class consist JFileChooser for searching the background picture and adding at 
//frame for setting up the layout.

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;

public class OpenPictureForEditMode extends EditMode implements ActionListener {

	public void actionPerformed(ActionEvent arg0) {

		// Declaring File chooser for set up the Layouts
		String filenamePicture = null;
		JFileChooser fc = new JFileChooser();
		int ret = fc.showOpenDialog(null);

		if (ret == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			filenamePicture = file.getAbsolutePath();

			frame.setContentPane(new JLabel(new ImageIcon(filenamePicture)));

			frame.add(getLayButton);
			frame.add(getPictureButton);
			frame.add(testFieldSetHeader);

			frame.add(valueXSizeTestFieldLabel);
			frame.add(valueXSizeTestField);
			frame.add(valueYSizeTestFieldLabel);
			frame.add(valueYSizeTestField);

			JButton okButton = new JButton("SAVE");
			okButton.setBounds(10, 700, 90, 30);
			okButton.addActionListener(new SavingLayout());
			frame.add(okButton);

			JLabel infoLabelLayoutPic = new JLabel("Open picture="
					+ filenamePicture);
			infoLabelLayoutPic.setBounds(150, -20, 1000, 100);
			infoLabelLayoutPic.setForeground(Color.orange);
			infoLabelLayoutPic.setVisible(true);
			infoLabelLayoutPic.setFont(new Font("SansSerif", Font.BOLD, 20));
			frame.add(infoLabelLayoutPic);
			frame.setVisible(true);
		}

	}

}