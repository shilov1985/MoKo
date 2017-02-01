package MoKo;

//This class is saving the position of  all test fields(mask)
//the class editLayoutFiles only is changing the attributes of fields and after that
//the class this class save the changes.

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SavingLayout extends EditMode implements ActionListener {
	static boolean isAddedPanels;
	static JPanel[] testPanelsArr;
	JTextField d = new JTextField();

	BufferedWriter out = null;

	public void actionPerformed(ActionEvent e) {

		try {
			out = new BufferedWriter(new FileWriter(filenameLay));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String numCorrection = null;
		int counterForOutputStream = 1;
		for (int x = 0; x < testPanels.length; x++) {

			int xPos = testPanels[x].getX();
			int yPos = testPanels[x].getY();

			if (counterForOutputStream < 10) {
				numCorrection = "00";
			} else {
				if (counterForOutputStream < 100) {
					numCorrection = "0";
				} else {
					numCorrection = "";
				}
			}
			try {

				out.write("<Point" + numCorrection + ""
						+ counterForOutputStream + ">" + xPos + "," + yPos
						+ "," + valueXSizeTestField.getText().trim() + ","
						+ valueYSizeTestField.getText().trim() + "</Point"
						+ numCorrection + "" + counterForOutputStream + ">");
				out.write(newLine);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			counterForOutputStream++;

			testPanels[x].setVisible(false);

		}

		try {
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		arrayTestPointsForSetLayout = new StringBuilder();

		File testPointsFile = new File(filenameLay);

		FileReader readtestPointsFile = null;
		try {
			readtestPointsFile = new FileReader(testPointsFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		BufferedReader bufReadtestPointsFile = new BufferedReader(
				readtestPointsFile);

		int counterLengthTestPointsFile = 0;
		// Take's the length of POINTS_NUMBER file
		// for initialization of
		// String[] arrayTestPoints;

		String tempLength;
		try {
			while ((tempLength = bufReadtestPointsFile.readLine()) != null) {

				counterLengthTestPointsFile++;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}

		FileReader readtestPointsFileA = null;
		try {
			readtestPointsFileA = new FileReader(testPointsFile);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		BufferedReader bufReadtestPointsFileA = new BufferedReader(
				readtestPointsFileA);

		int countTestPoints = 0;
		String temp;

		arrayTestPoints = new StringBuilder();

		int countForSubstringPos = 0;
		int ix = 0;
		// In this cycle is the basic program logic
		// of this program.
		testPanelsArr = new JPanel[counterLengthTestPointsFile];

		try {
			while ((temp = bufReadtestPointsFileA.readLine()) != null) {
				arrayTestPoints.append(temp);
				countForSubstringPos++;

				String addNum = null;

				if (countForSubstringPos < 10) {
					addNum = "00";
				} else {
					if (countForSubstringPos < 100) {
						addNum = "0";
					} else {
						addNum = "";
					}
				}

				int tempStartInt = arrayTestPoints.indexOf("<Point" + addNum
						+ countForSubstringPos + ">") + 10;

				int tempEndInt = arrayTestPoints.indexOf("</Point" + addNum
						+ countForSubstringPos + ">");

				String positionParam = arrayTestPoints.substring(tempStartInt,
						tempEndInt);

				paramPosition = positionParam.split(",");

				isAddedPanels = true;

				testPanels[ix] = new JPanel();

				final JPanel a1 = testPanels[ix];

				// Adding the test panel in to frame
				ImageIcon imgSave = new ImageIcon(
						"C:\\MoKo\\CABELS_PICTURES\\BL.png");
				Image imgBEdit = imgSave.getImage();
				Image newImg = imgBEdit.getScaledInstance(Integer
						.valueOf(paramPosition[2]) + 2, Integer
						.valueOf(paramPosition[3]) + 2,
						java.awt.Image.SCALE_AREA_AVERAGING);
				ImageIcon iconEdit = new ImageIcon(newImg);

				JLabel labEdit = new JLabel(iconEdit);
				labEdit.setSize(Integer.valueOf(paramPosition[2]) + 2, Integer
						.valueOf(paramPosition[3]) + 2);

				a1.setLayout(null);
				a1.add(labEdit);

				frame.add(a1);

				a1.setOpaque(false);

				a1.setBounds(Integer.valueOf(paramPosition[0]), Integer
						.valueOf(paramPosition[1]), Integer
						.valueOf(valueXSizeTestField.getText().trim()), Integer
						.valueOf(valueYSizeTestField.getText().trim()));
				a1.setVisible(true);

				a1.addMouseMotionListener(new MouseAdapter() {
					public void mouseDragged(MouseEvent E) {
						int X = E.getX() + a1.getX() - 5;
						int Y = E.getY() + a1.getY() - 5;
						a1.setBounds(X, Y, Integer.valueOf(paramPosition[2]),
								Integer.valueOf(paramPosition[3]));
					}
				});

				ix++;
				countTestPoints++;

			}

		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();

		}
		try {
			readtestPointsFile.close();
			out.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

}
