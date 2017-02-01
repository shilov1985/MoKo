package MoKo;

//This class consist a JFileChooserfor for searching the layout file and adding him in to 
//the frame only for changing the position and color.For saving the setting we use another
//class called SavingLayout
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EditLayoutFiles extends EditMode implements ActionListener {

	static ImageIcon img;

	public void actionPerformed(ActionEvent arg0) {

		// Declaring File chooser for set up the Layouts

		JFileChooser fc = new JFileChooser();
		int ret = fc.showOpenDialog(null);

		if (ret == JFileChooser.APPROVE_OPTION) {

			File file = fc.getSelectedFile();
			filenameLay = file.getAbsolutePath();

			JLabel infoLabelLayout = new JLabel("Open Layout=" + filenameLay);

			infoLabelLayout.setForeground(Color.ORANGE);
			infoLabelLayout.setVisible(true);
			infoLabelLayout.setFont(new Font("SansSerif", Font.BOLD, 20));
			frame.add(infoLabelLayout);
			infoLabelLayout.setBounds(150, 25, 1000, 100);

			File testPointsFile = new File(filenameLay);

			FileReader readtestPointsFile = null;
			try {
				readtestPointsFile = new FileReader(testPointsFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			BufferedReader bufReadtestPointsFile = new BufferedReader(
					readtestPointsFile);

			int counterLengthTestPointsFile = 0;
			// Take's the length of POINTS_NUMBER file
			// for initialization of
			// String[] arrayTestPoints;

			String tempLength = null;
			try {
				while ((tempLength = bufReadtestPointsFile.readLine()) != null) {

					counterLengthTestPointsFile++;
				}

				bufReadtestPointsFile.close();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}

			FileReader readtestPointsFileA = null;
			try {
				readtestPointsFileA = new FileReader(testPointsFile);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BufferedReader bufReadtestPointsFileA = new BufferedReader(
					readtestPointsFileA);

			int countTestPoints = 0;
			String temp;

			arrayTestPoints = new StringBuilder();

			int countForSubstringPos = 0;

			// In this cycle is the basic program logic
			// of this program.

			testPanels = new JPanel[counterLengthTestPointsFile];

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

					int tempStartInt = arrayTestPoints.indexOf("<Point"
							+ addNum + countForSubstringPos + ">") + 10;

					int tempEndInt = arrayTestPoints.indexOf("</Point" + addNum
							+ countForSubstringPos + ">");

					String positionParam = arrayTestPoints.substring(
							tempStartInt, tempEndInt);

					paramPosition = positionParam.split(",");

					testPanels[i] = new JPanel();

					final JPanel panelTestPoints = testPanels[i];

					// Adding the test panel in to frame
					img = new ImageIcon("C:\\MoKo\\CABELS_PICTURES\\BL.png");
					Image imgBEdit = img.getImage();
					Image newImg = imgBEdit.getScaledInstance(Integer
							.valueOf(paramPosition[2]) + 2, Integer
							.valueOf(paramPosition[3]) + 2,
							java.awt.Image.SCALE_AREA_AVERAGING);
					ImageIcon iconEdit = new ImageIcon(newImg);

					JLabel labEdit = new JLabel(iconEdit);
					labEdit.setSize(Integer.valueOf(paramPosition[2]) + 2,
							Integer.valueOf(paramPosition[3]) + 2);

					panelTestPoints.setLayout(null);
					panelTestPoints.add(labEdit);

					frame.add(panelTestPoints);

					panelTestPoints.setOpaque(false);

					panelTestPoints.setBounds(
							Integer.valueOf(paramPosition[0]), Integer
									.valueOf(paramPosition[1]), Integer
									.valueOf(paramPosition[2]), Integer
									.valueOf(paramPosition[3]));
					panelTestPoints.setVisible(true);

					frame.setVisible(true);

					panelTestPoints.addMouseMotionListener(new MouseAdapter() {
						public void mouseDragged(MouseEvent E) {
							int X = E.getX() + panelTestPoints.getX() - 5;
							int Y = E.getY() + panelTestPoints.getY() - 5;
							panelTestPoints.setBounds(X, Y, Integer
									.valueOf(paramPosition[2]), Integer
									.valueOf(paramPosition[3]));
						}
					});

					i++;
					countTestPoints++;

				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();

			}

			try {
				readtestPointsFileA.close();
				readtestPointsFile.close();
				bufReadtestPointsFileA.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			valueXSizeTestField.setText(paramPosition[2]);
			valueYSizeTestField.setText(paramPosition[3]);

		}
		System.out.println(filenameLay);
		i = 0;

	}

}
