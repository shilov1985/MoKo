package MoKo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class Test extends ScanZsbFrameAndLogic {
	static JPanel[] testPanels;
	static Color bgColor;
	static StringBuilder arrayTestPoints, builderORD,
			arrayTestPointsForSetLayout;
	static JTextField[] testFields, fieldsForSetSize;
	static String[] array_ORD;
	static final String newLine = System.getProperty("line.separator");

	static JTextField testFieldsA, valueXSizeTestField, valueYSizeTestField,
			testFieldsTextSize;

	static JButton settingButton, testFieldForegroundColorButtonSet,
			testFieldBackgroundColorButtonSet;
	static int i = 0;
	// Variable for set the size of number in Test Fields;
	static int sizeNum = 9;
	static JFrame setLayoutFrame, frame;
	static OutputStream outputStream;
	static String[] paramPosition;
	static File[] listProgramsName;
	static File pathToDataBase;
	static boolean isFolderFound = false;
	static JButton resetButton;
	static String zsbScanValue;
	// Variables which consist the color for TestFields
	static ImageIcon firstImg, secondImg;
	static Color Color_FOREGROUND_CONVERTEDa, Color_BACKGROUND_CONVERTEDa;
	static String allColors, firstColor, secondColor;

	// static instantionTestFields;

	public static void Logic() {

		final ScanZsbFrameAndLogic zsbInstantion = new ScanZsbFrameAndLogic();
		zsbInstantion.RunScanSZbWindow();

		// Enter button function
		scanZSBFrame.getRootPane().getInputMap(
				JComponent.WHEN_IN_FOCUSED_WINDOW

		).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "clickButton");

		scanZSBFrame.getRootPane().getActionMap().put("clickButton",
				new AbstractAction() {

					private static final long serialVersionUID = 1L;

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// run in administrator mode///////////////
						if (ZSB_field.getText().equalsIgnoreCase("admin")) {

							// This part of code will be running if we want to
							// enter in administrator mode
							// this is an edit mode class.

							EditMode editModeInstantion = new EditMode();
							editModeInstantion.RunEditMode();

						} else {

							// get zsb number from ZSB_field
							// Split input to get only zsb number without
							// extention
							String[] zsbScanValueSplitted = zsbInstantion.ZSB_field
									.getText().split("\\.");

							// here we format the input for searching of .ORD or
							// .PRG program,it does not matter,
							// and if is needed we format that input with the
							// values substringned from
							// the MoKo.ini file between <FORMAT_ZSB_INPUT> and
							// </FORMAT_ZSB_INPUT>
							// add an extention of specified file.For example if
							// we search for .prg files we takes the extention
							// from MoKo.ini file and append it to the end of
							// the number from ZSB_field.
							zsbScanValue = zsbScanValueSplitted[0].substring(
									formatInputZSBStart, formatInputZSBEnd)
									+ extentionOfProgramFile;

							// here we format the input data from ZSB_field to
							// find the folder with specified name.
							String zsbScanValueFormatted = zsbInstantion.ZSB_field
									.getText().substring(formatInputStart,
											formatInputEnd).trim();

						
							File rootDir = new File(pathToZSB);
							// Check directory
							File[] listOfFiles = rootDir.listFiles();

							// Check if directory with specified name taken from
							// ZsbFrame -first scan is found.
							// if we have folder with that name ,search inside
							// this folder for ORD.file whit tile number

							for (File file : listOfFiles) {

								if (file.getName()
										.equals(zsbScanValueFormatted)
										&& file.isDirectory()) {

									pathToDataBase = new File(pathToZSB + "\\"
											+ file.getName());

									listProgramsName = pathToDataBase
											.listFiles();
								

									// if found folder we proceed far away
									// Is it means that,we found the searched
									// folder
									// example:303003 whith formatted name of
									// course.

									isFolderFound = true;

								}
							}

							if (isFolderFound) {
								isFolderFound = false;
								for (int x = 0; x < listProgramsName.length; x++) {

									// if files with properly name has founded
									// proceed far away
									//

									if (listProgramsName[x].getName().equals(
											zsbScanValue)
											&& zsbInstantion.ZSB_field
													.getText()
													.substring(
															formatInputStart,
															formatInputEnd)
													.trim()
													.equals(
															zsbScanValueFormatted)) {

										pathToORDfile = pathToDataBase + "//"
												+ listProgramsName[x].getName();
										scanZSBFrame.setVisible(false);

										FileReader readORD = null;
										File file_ORD = new File(pathToORDfile);
										try {
											readORD = new FileReader(file_ORD);
										} catch (FileNotFoundException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}

										BufferedReader readOrdBuf = new BufferedReader(
												readORD);
										String lengthORD;

										int lengthORD_Value = 0;
										try {
											while ((lengthORD = readOrdBuf
													.readLine()) != null) {
												lengthORD_Value++;

											}
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}
										array_ORD = new String[lengthORD_Value];
										String x1 = null;
										int c = 0;

										// Prisvoqvane ma ORD faila na masiv
										FileReader readORDForArray = null;
										try {
											readORDForArray = new FileReader(
													file_ORD);
										} catch (FileNotFoundException e3) {
											// TODO Auto-generated catch block
											e3.printStackTrace();
										}

										BufferedReader readBufForArray = new BufferedReader(
												readORDForArray);
										builderORD = new StringBuilder();
										try {
											while ((x1 = readBufForArray
													.readLine()) != null) {

												builderORD.append(x1 + "\n");
												array_ORD[c] = x1;

												c++;
											}
										} catch (IOException e2) {
											// TODO Auto-generated catch block
											e2.printStackTrace();
										}

										// ////////////////////////////RABOTA S
										// MASIVA NA ORD
										// FILE/////////////////////////

										String y = String.valueOf(builderORD);
										String[] spl = y.split("Vb:");

										for (int i = 0; i < spl.length; i++) {

										}

										String pathToPicture = null;

										// check for that what Xcode we have in
										// our scanned program and choose the
										// properly picture for her
										String x_code = null;
										for (int i = 0; i < xCodesPicturesNfo.length; i += 2) {

											for (int j = 0; j < spl.length; j++) {

												if (spl[j]
														.contains(xCodesPicturesNfo[i])) {
													x_code = xCodesPicturesNfo[i];
													
													pathToPicture = xCodesPicturesNfo[i + 1];

												}

											}

										}

										JFrame frame = new JFrame();

										frame = new JFrame(
												"MoKo.By M.Shilov and K.Ivanov for SEBN_BG");
										frame.setLayout(null);
										frame.setSize(H_Size, V_Size);
										frame.setResizable(false);
										frame.setLocationRelativeTo(null);
										frame
												.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);

										// adding the info label to frame
										JLabel infoLabel = new JLabel("X Code="
												+ x_code
												+ "  Path to founded file="
												+ pathToORDfile);
										infoLabel.setBounds(50, -30, 1000, 100);
										infoLabel.setForeground(Color.yellow);
										infoLabel.setVisible(true);

										// adding the picture were compare with
										// ZSB
										frame.setContentPane(new JLabel(
												new ImageIcon(pathToPicture)));
										frame.add(infoLabel);
										infoLabel.setFont(new Font("SansSerif",
												Font.BOLD, 20));

										frame.setVisible(true);
										// IMPORTANT............
										// IMPORTANT............
										// IMPORTANT............
										// IMPORTANT............
										// adding a button for restarting the
										// application
										resetButton = new JButton("RESET");

										frame.add(resetButton);
										resetButton
												.addActionListener(new RestartProgram());
										resetButton.setVisible(true);
										resetButton.setBounds(10, 50, 100, 400);

										// DECLARATE test point file for adding
										// in cycle in frame
										File testPointsFile = new File(
												"C://MoKo//PICTURES//POINTS_NUMBER_"
														+ x_code + ".txt");

										FileReader readtestPointsFile = null;
										try {
											readtestPointsFile = new FileReader(
													testPointsFile);
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										BufferedReader bufReadtestPointsFile = new BufferedReader(
												readtestPointsFile);

										int counterLengthTestPointsFile = 0;
										// Take's the length of POINTS_NUMBER
										// file
										// for initialization of
										// String[] arrayTestPoints;

										String tempLength;
										try {
											while ((tempLength = bufReadtestPointsFile
													.readLine()) != null) {

												counterLengthTestPointsFile++;
											}
										} catch (IOException e1) {
											// TODO Auto-generated catch block
											e1.printStackTrace();

										}

										FileReader readtestPointsFileA = null;
										try {
											readtestPointsFileA = new FileReader(
													testPointsFile);
										} catch (FileNotFoundException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

										BufferedReader bufReadtestPointsFileA = new BufferedReader(
												readtestPointsFileA);

										int countTestPoints = 0;
										

										arrayTestPoints = new StringBuilder();

										int countForSubstringPos = 0;

										int lengthTestFile = 0;
										String testFile = null;

										try {
											while ((testFile = bufReadtestPointsFileA
													.readLine()) != null) {
												lengthTestFile++;
												arrayTestPoints
														.append(testFile);
											}
										} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										boolean isPresentX_code = false;

										for (int i = 0; i < spl.length; i++) {

											int x1ValueNum = 0;
											int x2ValueNum = 0;

											if (spl[i].contains("X1:" + x_code)) {
												int subStartX1 = spl[i]
														.indexOf("T1:") + 3;
												int subEndX1 = spl[i]
														.indexOf("T2:");

												String x1Value = spl[i]
														.substring(subStartX1,
																subEndX1)
														.trim();
												x1ValueNum = Integer
														.valueOf(x1Value);

												isPresentX_code = true;
											}

											if (spl[i].contains("X2:" + x_code)) {
												int subStartX2 = spl[i]
														.indexOf("T2:") + 3;
												int subEndX2 = spl[i]
														.indexOf("X1:");

												String x2Value = spl[i]
														.substring(subStartX2,
																subEndX2)
														.trim();
												x2ValueNum = Integer
														.valueOf(x2Value);

												// Get the color of the
												// cable in the chunk
												// spl[i]
												isPresentX_code = true;
											}

											if (isPresentX_code) {

												if (x1ValueNum != 0) {
													// here proceed if imame
													// vruzka na SG_MOT1 v purvo
													// gnerzdo
													String addNumX_1 = null;

													if (x1ValueNum < 10) {
														addNumX_1 = "00";
													} else {
														if (x1ValueNum < 100) {
															addNumX_1 = "0";
														} else {
															addNumX_1 = "";
														}
													}

													int tempStartIntX_1 = arrayTestPoints
															.indexOf("<Point"
																	+ addNumX_1
																	+ x1ValueNum
																	+ ">") + 10;

													

													int tempEndIntX_1 = arrayTestPoints
															.indexOf("</Point"
																	+ addNumX_1
																	+ x1ValueNum
																	+ ">");
												

													String positionParam = arrayTestPoints
															.substring(
																	tempStartIntX_1,
																	tempEndIntX_1);
													
													String[] paramPosition = positionParam
															.split(",");
													JPanel panel = new JPanel();

													int subStartColor = spl[i]
															.indexOf("Vf:") + 3;
													int subEndColor = spl[i]
															.indexOf("T1:") - 1;
													allColors = spl[i]
															.substring(
																	subStartColor,
																	subEndColor);
													subStartColor = 0;
													subEndColor = 0;
													// Assign first color
													// value
													firstColor = allColors
															.substring(0, 2);

													firstImg = new ImageIcon(
															"C:\\MoKo\\CABELS_PICTURES\\"
																	+ firstColor
																	+ ".PNG");

													Image imgA = firstImg
															.getImage();
													Image newimg = imgA
															.getScaledInstance(
																	Integer
																			.valueOf(paramPosition[2]) + 2,
																	Integer
																			.valueOf(paramPosition[3]) + 2,
																	java.awt.Image.SCALE_AREA_AVERAGING);

													ImageIcon icon = new ImageIcon(
															newimg);

													JLabel lab = new JLabel(
															icon);

													lab
															.setAlignmentX(JLabel.CENTER);
													lab.setSize(25, 25);

													panel.setLayout(null);
													// //////////////////
													panel.add(lab);
													frame.add(panel, 1, 0);

													panel.setOpaque(false);
													panel
															.setBounds(
																	Integer
																			.valueOf(paramPosition[0]),
																	Integer
																			.valueOf(paramPosition[1]),
																	Integer
																			.valueOf(paramPosition[2]),
																	Integer
																			.valueOf(paramPosition[3]));

													// Add second color
													// /////////////////////////////////////////
													if ((allColors.length() > 2)
															&& (allColors
																	.length() <= 4)) {

														secondColor = allColors
																.substring(2, 4);

														secondImg = new ImageIcon(
																"C:\\MoKo\\CABELS_PICTURES\\"
																		+ secondColor
																		+ ".PNG");
														Image imgB = secondImg
																.getImage();
														Image newImg = imgB
																.getScaledInstance(
																		Integer
																				.valueOf(paramPosition[2]) / 2,
																		Integer
																				.valueOf(paramPosition[3]) / 2,
																		java.awt.Image.SCALE_AREA_AVERAGING);

														ImageIcon iconB = new ImageIcon(
																newImg);

														JLabel labB = new JLabel(
																iconB);

														labB
																.setAlignmentX(JLabel.CENTER);
														labB.setSize(15, 15);

														JPanel panelB = new JPanel();
														panelB.setLayout(null);

														// //////////////////
														panelB.add(labB);
														frame.add(panelB, 2, 0);

														panelB.setOpaque(false);
														panelB
																.setBounds(
																		Integer
																				.valueOf(paramPosition[0]) + 5,
																		Integer
																				.valueOf(paramPosition[1]) + 5,
																		Integer
																				.valueOf(paramPosition[2]),
																		Integer
																				.valueOf(paramPosition[3]));

														frame.setVisible(true);

														secondColor = null;

													}

												} else if (x2ValueNum != 0) {

													// here proceed if imame
													// vruzka na SG_MOT1 v purvo
													// gnerzdo
													String addNum = null;

													if (x2ValueNum < 10) {
														addNum = "00";
													} else {
														if (x2ValueNum < 100) {
															addNum = "0";
														} else {
															addNum = "";
														}
													}

													int tempStartInt = arrayTestPoints
															.indexOf("<Point"
																	+ addNum
																	+ x2ValueNum
																	+ ">") + 10;

													

													int tempEndInt = arrayTestPoints
															.indexOf("</Point"
																	+ addNum
																	+ x2ValueNum
																	+ ">");
												

													String positionParam = arrayTestPoints
															.substring(
																	tempStartInt,
																	tempEndInt);
													
													String[] paramPosition = positionParam
															.split(",");
													JPanel panel = new JPanel();

													int subStartColor = spl[i]
															.indexOf("Vf:") + 3;
													int subEndColor = spl[i]
															.indexOf("T1:") - 1;
													allColors = spl[i]
															.substring(
																	subStartColor,
																	subEndColor);
													subStartColor = 0;
													subEndColor = 0;
													// Assign first color
													// value
													firstColor = allColors
															.substring(0, 2);

													

													firstImg = new ImageIcon(
															"C:\\MoKo\\CABELS_PICTURES\\"
																	+ firstColor
																	+ ".PNG");

													Image imgA = firstImg
															.getImage();
													Image newimg = imgA
															.getScaledInstance(
																	Integer
																			.valueOf(paramPosition[2]) + 2,
																	Integer
																			.valueOf(paramPosition[3]) + 2,
																	java.awt.Image.SCALE_AREA_AVERAGING);

													ImageIcon icon = new ImageIcon(
															newimg);

													JLabel lab = new JLabel(
															icon);

													lab
															.setAlignmentX(JLabel.CENTER);
													lab
															.setSize(
																	Integer
																			.valueOf(paramPosition[2]),
																	Integer
																			.valueOf(paramPosition[3]));

													panel.setLayout(null);

													panel.add(lab);
													frame.add(panel, 1, 0);

													panel.setOpaque(false);
													panel
															.setBounds(
																	Integer
																			.valueOf(paramPosition[0]),
																	Integer
																			.valueOf(paramPosition[1]),
																	Integer
																			.valueOf(paramPosition[2]),
																	Integer
																			.valueOf(paramPosition[3]));

													// Add second color
													// /////////////////////////////////////////
													if ((allColors.length() > 2)
															&& (allColors
																	.length() <= 4)) {

														secondColor = allColors
																.substring(2, 4);

														secondImg = new ImageIcon(
																"C:\\MoKo\\CABELS_PICTURES\\"
																		+ secondColor
																		+ ".PNG");
														Image imgB = secondImg
																.getImage();
														Image newImg = imgB
																.getScaledInstance(
																		Integer
																				.valueOf(paramPosition[2]) / 2,
																		Integer
																				.valueOf(paramPosition[3]) / 2,
																		java.awt.Image.SCALE_AREA_AVERAGING);

														ImageIcon iconB = new ImageIcon(
																newImg);

														JLabel labB = new JLabel(
																iconB);

														labB
																.setAlignmentX(JLabel.CENTER);
														labB
																.setSize(
																		Integer
																				.valueOf(paramPosition[2]) / 2 + 2,
																		Integer
																				.valueOf(paramPosition[3]) / 2 + 2);

														JPanel panelB = new JPanel();
														panelB.setLayout(null);

														panelB.add(labB);
														frame.add(panelB, 2, 0);

														panelB.setOpaque(false);
														panelB
																.setBounds(
																		Integer
																				.valueOf(paramPosition[0]) + 5,
																		Integer
																				.valueOf(paramPosition[1]) + 5,
																		Integer
																				.valueOf(paramPosition[2]),
																		Integer
																				.valueOf(paramPosition[3]));

														frame.setVisible(true);

														secondColor = null;

													}

												}
												// By this counter compare the
												// number of test panel

												for (int j = 0; j < counterLengthTestPointsFile; j++) {

													countForSubstringPos++;

												}

											}
										}
										try {
											System.out.println("readORD losed");
											readORD.close();
											System.out.println("readOrdBuf losed");
											readOrdBuf.close();
											System.out.println("readORDForArray losed");
											readORDForArray.close();
											System.out.println("readBufForArray losed");
											readBufForArray.close();
											readtestPointsFile.close();
											bufReadtestPointsFile.close();											} catch (IOException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										isPresentX_code = false;
										isFolderFound = false;
										countTestPoints++;
									}

								}

							} else {

								// ////////////////////////////////////////if
								// folder whit the formatted name not found,
								// add label to scanZSBFrame.
								fileNotFoundLabel.setVisible(false);
								scanZSBFrame.add(folderNotFound);
								folderNotFound.setVisible(true);
								ZSB_field.setText("");

							}
						}

					}

				});

	}

	public static void main(String[] args) {

		Test aA = new Test();

		aA.Logic();
		// JColorChooserDemo a = new JColorChooserDemo();

	}

}
