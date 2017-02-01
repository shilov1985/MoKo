package MoKo;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class BackgroundColorFieldsSetter implements ActionListener {
	// Class for saving the background color of the all TestFields
	public void actionPerformed(ActionEvent arg0) {

		BufferedWriter outColorBackground = null;

		try {
			outColorBackground = new BufferedWriter(new FileWriter(
					"C://MoKo//TEST_FIELDS_PROPERTYA.txt"));
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
		// Saving the rgb values..
		try {
			outColorBackground.write(String
					.valueOf("<BACKGROUND_COLOR_FIELDS>[" + colorValue
							+ "]</BACKGROUND_COLOR_FIELDS>"));

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		System.out.println(colorValue);

		try {
			outColorBackground.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
