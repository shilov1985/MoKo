package MoKo;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

import javax.swing.AbstractAction;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;

public class ScanZsbFrameAndLogic {
	static JFrame scanZSBFrame;
	static JTextField ZSB_field;
	static boolean isZSB_exist = false;
	static String pathZSB_DIR;
	static String pathToZSB;
	static String pathToORDfile;
	static String Color_BACKGROUND, Color_FOREGROUND, formatInputLiteral;
	static Color Color_BACKGROUND_CONVERTED, Color_FOREGROUND_CONVERTED,
			Color_FOREGROUND_CONVERTEDa, Color_BACKGROUND_CONVERTEDa;
	static StringBuilder iniStrBuild;
	static boolean isAdminPass, isTestPass;
	static JLabel fileNotFound;
	static String ScanZsbFrameAndLogic, extentionOfProgramFile,formatInputZSBLiteral;
	static int formatInputEnd, formatInputStart,formatInputZSBStart,formatInputZSBEnd,H_Size,V_Size;
	static JLabel folderNotFound, fileNotFoundLabel;
	static String[] xCodesPicturesNfo;
	

	public void RunScanSZbWindow() {

		scanZSBFrame = new JFrame("Please scan ZSB number!");

		scanZSBFrame.setContentPane(new JLabel(new ImageIcon(
				"C:\\MoKo\\SZB_Frame.png")));
		scanZSBFrame.setContentPane(new JLabel(new ImageIcon(
				"C:\\MoKo\\Icons\\scanZSBFramePic.png")));
		scanZSBFrame.setVisible(true);

		scanZSBFrame.setBounds(200, 200, 400, 200);

		scanZSBFrame.setLocationRelativeTo(null);

		scanZSBFrame.setDefaultCloseOperation(scanZSBFrame.EXIT_ON_CLOSE);

		scanZSBFrame.setAlwaysOnTop(true);
		
		scanZSBFrame.setResizable(false);

		JLabel scanZSBFrameLabel = new JLabel("SCAN ZSB");
		scanZSBFrame.add(scanZSBFrameLabel);
		scanZSBFrameLabel.setFont(new Font("SansSerif", Font.BOLD, 30));
		scanZSBFrameLabel.setBounds(110, 10, 400, 50);

		ZSB_field = new JTextField();

		ZSB_field.setVisible(true);
		ZSB_field.setEditable(true);
		scanZSBFrame.add(ZSB_field);
		ZSB_field.setBounds(10, 80, 365, 70);
		ZSB_field.setFont(new Font("SansSerif", Font.BOLD, 30));

		ZSB_field.requestFocus();

		FileReader readIni = null;
		File iniMoKoFile = new File("C://MoKo//MoKo.ini");
		try {
			readIni = new FileReader(iniMoKoFile);
		} catch (FileNotFoundException e) {
			ZSB_field.setText("ini file not found in C:/MoKo/MoKo.ini");
			ZSB_field.setFont(new Font("SansSerif", Font.BOLD, 15));
		}

		BufferedReader readIniBuffered = new BufferedReader(readIni);
		String tmpIni;
		iniStrBuild = new StringBuilder();
		try {
			while ((tmpIni = readIniBuffered.readLine()) != null) {
				iniStrBuild.append(tmpIni);
			}
		} catch (IOException e) {
			ZSB_field.setText("no lines found in C:/MoKo/MoKo.ini");
			ZSB_field.setFont(new Font("SansSerif", Font.BOLD, 15));
		}
		int tmpStartStart = iniStrBuild.indexOf("<PATH_ORD_DIR>") + 14;
		int tmpStartEnd = iniStrBuild.indexOf("</PATH_ORD_DIR>");

		try {
			pathToZSB = iniStrBuild.substring(tmpStartStart, tmpStartEnd);
		} catch (Exception e) {
			ZSB_field.setText("no lines found in C:/MoKo/MoKo.ini");
			ZSB_field.setFont(new Font("SansSerif", Font.BOLD, 15));
		}

		int formatStart = iniStrBuild.indexOf("<FORMAT_FOLDER_INPUT>") + 21;
		int formatEnd = iniStrBuild.indexOf("</FORMAT_FOLDER_INPUT>");

		try {
			formatInputLiteral = iniStrBuild.substring(formatStart, formatEnd);
		} catch (Exception e) {
			ZSB_field.setText("no lines found in C:/MoKo/MoKo.ini");
			ZSB_field.setFont(new Font("SansSerif", Font.BOLD, 15));
		}
		String[] splittedFormat = formatInputLiteral.split(",");
		formatInputStart = Integer.valueOf(splittedFormat[0]);
		formatInputEnd = Integer.valueOf(splittedFormat[1]);

		

		int extentionStart = iniStrBuild.indexOf("<FILE_EXTENTION>") + 16;
		int extentionEnd = iniStrBuild.indexOf("</FILE_EXTENTION>");

		try {
			extentionOfProgramFile = iniStrBuild.substring(extentionStart,
					extentionEnd);
		} catch (Exception e) {
			ZSB_field.setText("no lines found in C:/MoKo/MoKo.ini");
			ZSB_field.setFont(new Font("SansSerif", Font.BOLD, 15));
		}

		fileNotFoundLabel = new JLabel(extentionOfProgramFile
				+ " file not found.Please check the PROGRAM FOLDER!");

		fileNotFoundLabel.setForeground(Color.red);
		fileNotFoundLabel.setFont(new Font("SansSerif", Font.BOLD, 12));
		fileNotFoundLabel.setBounds(0, 60, 500, 20);

		ZSB_field.setText("");
		fileNotFoundLabel.setVisible(false);

		folderNotFound = new JLabel("Not found folder with "
				+ extentionOfProgramFile + "files in " + pathToZSB);

		folderNotFound.setForeground(Color.red);
		folderNotFound.setFont(new Font("SansSerif", Font.BOLD, 12));
		folderNotFound.setBounds(0, 60, 500, 20);

		ZSB_field.setText("");

		folderNotFound.setVisible(false);

		// substring the info for Xcodes were we are searching for.
		int xCodeStart = iniStrBuild.indexOf("<PICTURE>") + 9;
		int xCodeEnd = iniStrBuild.indexOf("</PICTURE>");

		xCodesPicturesNfo = iniStrBuild.substring(xCodeStart, xCodeEnd).split(
				"<>");
		
		
	//Get the formatting values for scan of .ord files in founded filder
		int formatStartZSBRead = iniStrBuild.indexOf("<FORMAT_ZSB_INPUT>") + 18;
		int formatEndZSBRead = iniStrBuild.indexOf("</FORMAT_ZSB_INPUT>");

		try {
			formatInputZSBLiteral = iniStrBuild.substring(formatStartZSBRead, formatEndZSBRead);
			
		} catch (Exception e) {
			ZSB_field.setText("no lines found in C:/MoKo/MoKo.ini");
			ZSB_field.setFont(new Font("SansSerif", Font.BOLD, 15));
		}
		String tempZSBLiteral =formatInputZSBLiteral;
		String[] formatInputZSBLiteral = tempZSBLiteral.split(",");
		formatInputZSBStart = Integer.valueOf(formatInputZSBLiteral[0]);
		formatInputZSBEnd = Integer.valueOf(formatInputZSBLiteral[1]);
	
	//get the frame size of program
		int sizeStartFrame = iniStrBuild.indexOf("<MAIN_FRAME_SIZE>") + 17;
		int sizeEndFrame = iniStrBuild.indexOf("</MAIN_FRAME_SIZE>");
		String sizeFrame = iniStrBuild.substring(sizeStartFrame,sizeEndFrame);
		
		String[] sizeFrameArray = sizeFrame.split(",");
		H_Size = Integer.valueOf(sizeFrameArray[0]);
		 V_Size = Integer.valueOf(sizeFrameArray[1]);
		 
		 
		 //Close all flows
	     
		 try {
			readIniBuffered.close();
			readIni.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
