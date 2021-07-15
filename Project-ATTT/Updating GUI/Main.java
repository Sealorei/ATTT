import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Desktop;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import java.awt.SystemColor;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import javax.swing.JProgressBar;

public class Main 
{

	JFrame frame;
	private JTextField textField;
	private JButton btnNewButton_1;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JLabel lblNewLabel;
	private JTextArea txtrSelecttxtFiles;
	private JTextArea txtrYourDropbox;
	private JTextArea txtrYouJustHave;
	private JTextArea txtrUploadingOnYour;
	File[] files;
	static String TextFromPlaintextFile;
    static String TextFromCiphertextFileDES;
    static String TextFromCiphertextFileAES;
    static String TextFromCiphertextFileRC4;
    static int count;
    static String PathOfImageWithKey;
    static String PathOfUserImage;
    static String ConcatKeysAfterEncryption = "";
    static String CipherFilePathToSave;
    static String CipherImagePathToSave;
    static String PlaintextFileRecoveredPathToSave;
    static Main f1 = new Main();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main window = new Main();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	// Giao diện chương trình
	
	private void initialize() 
	{
		
		frame = new JFrame("Secure File");
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 500, 450);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
//		textField = new JTextField();
//		textField.setEditable(false);
//		textField.setBounds(53, 142, 216, 33);
//		frame.getContentPane().add(textField);
//		textField.setColumns(10);
		
//		JButton btnNewButton = new JButton("CHỌN FILE");
//		btnNewButton.addMouseListener(new MouseAdapter() {
//			public void mouseClicked(MouseEvent arg0) {
//				JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
//                j.setDialogTitle("Select a File and an Image");
//                j.setMultiSelectionEnabled(true);
//                int r = j.showOpenDialog(null); 
//                if (r == JFileChooser.APPROVE_OPTION) 
//                { 
//                    files = j.getSelectedFiles();
//                		if((files[0].getAbsolutePath()).endsWith(".txt")||(files[1].getAbsolutePath()).endsWith(".txt"))
//                    	{
//                			String fileNames = "";
//                            for(File file: files)
//                            {
//                                fileNames += file.getName() + " ; ";
//                            }
//                            textField.setText(fileNames);
//                    	}
//                		else
//                		{
//                			WrongOpenFile wof = new WrongOpenFile();
//                			wof.setVisible(true);
//                		}
//                } 
//			}
//		});
//		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 18));
//		btnNewButton.setForeground(Color.WHITE);
//		btnNewButton.setBorder(null);
//		btnNewButton.setBackground(SystemColor.textHighlight);
//		btnNewButton.setBounds(302, 142, 135, 33);
//		frame.getContentPane().add(btnNewButton);
		
		JTextArea txtrSecureFileIs = new JTextArea();
		txtrSecureFileIs.setEditable(false);
//		txtrSecureFileIs.setFont(new Font("Calibri", Font.ITALIC, 14));
		txtrSecureFileIs.setFont(new Font("Calibri", Font.BOLD, 16));
		txtrSecureFileIs.setText("ỨNG DỤNG MÃ HÓA FILE");
		txtrSecureFileIs.setBackground(Color.LIGHT_GRAY);
		txtrSecureFileIs.setBounds(60, 30, 368, 16);
		frame.getContentPane().add(txtrSecureFileIs);
		
		btnNewButton_1 = new JButton("MÃ HÓA");
		btnNewButton_1.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File file1 = null;
				File file2 = null;
				
				JFileChooser fileChooser1 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            	fileChooser1.setDialogTitle("CHỌN PLAINTEXT FILE");
                int option = fileChooser1.showSaveDialog(frame);
                if(option == JFileChooser.APPROVE_OPTION)
                {
                   file1 = fileChooser1.getSelectedFile();
                   CipherFilePathToSave = file1.getAbsolutePath().split(".txt")[0] + "_encrypt.txt";
                }
                
                JFileChooser fileChooser2 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
                fileChooser2.setDialogTitle("CHỌN ẢNH GIẤU KEY");
                int option2 = fileChooser2.showSaveDialog(frame);
                if(option2 == JFileChooser.APPROVE_OPTION)
                {
                   file2 = fileChooser2.getSelectedFile();
                   CipherImagePathToSave = file2.getAbsolutePath().split(".png")[0] + "_key.png";
                }
            	
//            	for(int i=0; i<2; i++)
//            	{
//            		if((files[i].getAbsolutePath()).endsWith(".txt"))
//                	{
                		BufferedReader in = null;
        				try 
        				{
        					in = new BufferedReader(new FileReader(file1.getAbsolutePath()));
        				} 
        				catch (FileNotFoundException e1) 
        				{
        					e1.printStackTrace();
        				} 
                        try 
                        {
		                  	  String st;
		                  	  TextFromPlaintextFile = "";
		                  	  while ((st = in.readLine()) != null) {
		                  		TextFromPlaintextFile = TextFromPlaintextFile + st + "\n";
		                  	  }
        				} 
                        catch (IOException e1) 
                        {
        					e1.printStackTrace();
        				}
//                	}
//                	else
//                	{
                		PathOfUserImage = file2.getAbsolutePath();
//                	}
//            	}
            	try 
            	{
					f1.RunningTheCompleteEncryptFunction();
				} 
            	catch (Exception e1) 
            	{
					e1.printStackTrace();
				}
            	EncryptNoti ec = new EncryptNoti();
            	ec.setVisible(true);
			}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton_1.setForeground(Color.WHITE);
		btnNewButton_1.setBorder(null);
		btnNewButton_1.setBackground(SystemColor.textHighlight);
		btnNewButton_1.setBounds(53, 150, 216, 38);
		frame.getContentPane().add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("MỞ DROPBOX");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try { Desktop.getDesktop().browse(new URI("https://www.dropbox.com/h")); } 
		        catch (IOException | URISyntaxException e1) { e1.printStackTrace(); }
			}
		});
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton_2.setForeground(Color.WHITE);
		btnNewButton_2.setBorder(null);
		btnNewButton_2.setBackground(SystemColor.textHighlight);
		btnNewButton_2.setBounds(53, 220, 216, 38);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("GIẢI MÃ");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				File file3 = null;
				File file4 = null;
				
				JFileChooser fileChooser3 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            	fileChooser3.setDialogTitle("CHỌN FILE MÃ HÓA");
                int option3 = fileChooser3.showSaveDialog(frame);
                if(option3 == JFileChooser.APPROVE_OPTION)
                {
                   file3 = fileChooser3.getSelectedFile();
                   PlaintextFileRecoveredPathToSave = file3.getAbsolutePath().split("_encrypt")[0] + "_decrypt.txt";
                }
                
				JFileChooser fileChooser4 = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
            	fileChooser4.setDialogTitle("CHỌN ẢNH GIẤU KHÓA");
                int option4 = fileChooser4.showSaveDialog(frame);
                if(option4 == JFileChooser.APPROVE_OPTION)
                {
                   file4 = fileChooser4.getSelectedFile();
//                   PlaintextFileRecoveredPathToSave = file.getAbsolutePath()+".txt";
                }
            	
//            	for(int i=0; i<2; i++)
//            	{
//            		if((files[i].getAbsolutePath()).endsWith(".txt"))
//                	{
        		BufferedReader xyz = null;
				try 
				{
					xyz = new BufferedReader(new FileReader(file3.getAbsolutePath()));
				} 
				catch (FileNotFoundException e1) 
				{
					e1.printStackTrace();
				} 
                try 
                {
					TextFromCiphertextFileDES = xyz.readLine();
					TextFromCiphertextFileAES = xyz.readLine();
					TextFromCiphertextFileRC4 = xyz.readLine();
				} 
                catch (IOException e1) 
                {
					e1.printStackTrace();
				}
//                	}
//                	else
//                	{
        		PathOfImageWithKey = file4.getAbsolutePath();
//                	}
//            	}
            	try 
                { f1.RunningTheCompleteDecryptFunction(); } 
                catch (Exception e1) 
                { e1.printStackTrace(); }
            	DecryptNoti dc =new DecryptNoti();
            	dc.setVisible(true);
			}
		});
		btnNewButton_3.setFont(new Font("Calibri", Font.BOLD, 18));
		btnNewButton_3.setForeground(Color.WHITE);
		btnNewButton_3.setBorder(null);
		btnNewButton_3.setBackground(SystemColor.textHighlight);
		btnNewButton_3.setBounds(53, 290, 216, 38);
		frame.getContentPane().add(btnNewButton_3);
		
		lblNewLabel = new JLabel("");
		Image img = new ImageIcon(this.getClass().getResource("/File-icon.png")).getImage();
		lblNewLabel.setIcon(new ImageIcon(img));
		lblNewLabel.setBounds(309, 170, 128, 135);
		frame.getContentPane().add(lblNewLabel);
		
//		txtrSelecttxtFiles = new JTextArea();
//		txtrSelecttxtFiles.setEditable(false);
//		txtrSelecttxtFiles.setFont(new Font("Calibri", Font.BOLD, 14));
//		txtrSelecttxtFiles.setText("( Định dạng file : \".txt\" )");
//		txtrSelecttxtFiles.setBackground(Color.LIGHT_GRAY);
//		txtrSelecttxtFiles.setBounds(53, 182, 216, 22);
//		frame.getContentPane().add(txtrSelecttxtFiles);
		
		txtrYourDropbox = new JTextArea();
		txtrYourDropbox.setEditable(false);
		txtrYourDropbox.setText("( Định dạng file được yêu cầu : \".txt\" )");
		txtrYourDropbox.setFont(new Font("Calibri", Font.ITALIC, 14));
		txtrYourDropbox.setBackground(Color.LIGHT_GRAY);
		txtrYourDropbox.setBounds(60, 61, 368, 16);
		frame.getContentPane().add(txtrYourDropbox);
		
		txtrYouJustHave = new JTextArea();
		txtrYouJustHave.setEditable(false);
		txtrYouJustHave.setText("Để mã hóa -> Chọn plaintext file -> Chọn ảnh giấu key. ");
		txtrYouJustHave.setFont(new Font("Calibri", Font.ITALIC, 14));
		txtrYouJustHave.setBackground(Color.LIGHT_GRAY);
		txtrYouJustHave.setBounds(60, 78, 368, 16);
		frame.getContentPane().add(txtrYouJustHave);
		
		txtrUploadingOnYour = new JTextArea();
		txtrUploadingOnYour.setEditable(false);
		txtrUploadingOnYour.setText("Để mã hóa -> Chọn ciphertext file -> Chọn ảnh giấu key tương ứng. ");
		txtrUploadingOnYour.setFont(new Font("Calibri", Font.ITALIC, 14));
		txtrUploadingOnYour.setBackground(Color.LIGHT_GRAY);
		txtrUploadingOnYour.setBounds(60, 96, 368, 16);
		frame.getContentPane().add(txtrUploadingOnYour);
	}
	
	
	// Thuật toán DES
	
	private static class DES 
    { 
        // Initial Permutation Table 
        int[] IP = { 58, 50, 42, 34, 26, 18, 10, 2, 60, 52, 44, 36, 28, 20, 12, 4, 62, 54, 46, 38, 30, 22, 14, 6, 64, 56, 
                     48, 40, 32, 24, 16, 8, 57, 49, 41, 33, 25, 17, 9, 1, 59, 51, 43, 35, 27, 19, 11, 3, 61, 53, 45, 
                     37, 29, 21, 13, 5, 63, 55, 47, 39, 31, 23, 15, 7 }; 
        // Inverse Initial Permutation Table 
        int[] IP1 = { 40, 8, 48, 16, 56, 24, 64, 32, 39, 7, 47, 15, 55, 23, 63, 31, 38, 6, 46, 14, 54, 22, 62, 30, 37, 
                      5, 45, 13, 53, 21, 61, 29, 36, 4, 44, 12, 52, 20, 60, 28, 35, 3, 43, 11, 51, 19, 59, 27, 34, 
                      2, 42, 10, 50, 18, 58, 26, 33, 1, 41, 9, 49, 17, 57, 25 }; 
        // first key-hePermutation Table 
        int[] PC1 = { 57, 49, 41, 33, 25, 17, 9, 1, 58, 50, 42, 34, 26, 18, 10, 2, 59, 51, 43, 35, 27, 19, 11, 3, 60, 
                      52, 44, 36, 63, 55, 47, 39, 31, 23, 15, 7, 62, 54, 46, 38, 30, 22, 14, 6, 61, 
                      53, 45, 37, 29, 21, 13, 5, 28, 20, 12, 4 }; 
        // second key-Permutation Table 
        int[] PC2 = { 14, 17, 11, 24, 1, 5, 3, 28, 15, 6, 21, 10, 23, 19, 12, 4, 26, 8, 16, 7, 27, 20, 13, 2, 41, 52, 
                      31, 37, 47, 55, 30, 40, 51, 45, 33, 48, 44, 49, 39, 56, 34, 53, 46, 42, 50, 36, 29, 32 }; 
        // Expansion D-box Table 
        int[] EP = { 32, 1, 2, 3, 4, 5, 4, 5, 6, 7, 8, 9, 8, 9, 10, 11, 12, 13, 12, 13, 14, 15, 16, 17, 16, 17, 18, 
                     19, 20, 21, 20, 21, 22, 23, 24, 25, 24, 25, 26, 27, 28, 29, 28, 29, 30, 31, 32, 1 }; 
        // Straight Permutation Table 
        int[] P = { 16, 7, 20, 21, 29, 12, 28, 17, 1, 15, 23, 26, 5, 18, 31, 10, 2, 8, 24, 14, 32, 27, 3, 9, 19, 13, 
                    30, 6, 22, 11, 4, 25 }; 
        // S-box Table 
        int[][][] sbox = { 
            { { 14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7 }, 
              { 0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8 }, 
              { 4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0 }, 
              { 15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13 } }, 
            { { 15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10 }, 
              { 3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5 }, 
              { 0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15 }, 
              { 13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9 } }, 
            { { 10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8 }, 
              { 13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1 }, 
              { 13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7 }, 
              { 1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12 } }, 
            { { 7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15 }, 
              { 13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9 }, 
              { 10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4 }, 
              { 3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14 } }, 
            { { 2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9 }, 
              { 14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6 }, 
              { 4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14 }, 
              { 11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3 } }, 
            { { 12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11 }, 
              { 10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8 }, 
              { 9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6 }, 
              { 4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13 } }, 
            { { 4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1 }, 
              { 13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6 }, 
              { 1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2 }, 
              { 6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12 } }, 
            { { 13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7 }, 
              { 1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2 }, 
              { 7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8 }, 
              { 2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11 } } 
        }; 
        
        // Array to store the number of rotations that are to be done on each round
        int[] shiftBits = { 1, 1, 2, 2, 2, 2, 2, 2, 1, 2, 2, 2, 2, 2, 2, 1 }; 

        public static String ASCIItoHEX(String ascii)
        {
            String hex = "";
            for (int i = 0; i < ascii.length(); i++) 
            {
                char ch = ascii.charAt(i);
                int in = (int)ch;
                String part = Integer.toHexString(in);
                hex += part;
            }
            return hex;
        }
        
        public static String hexToASCII(String hex) 
        { 
            String ascii = ""; 
            for (int i = 0; i < hex.length(); i += 2) 
            { 
                String part = hex.substring(i, i + 2); 
                char ch = (char)Integer.parseInt(part, 16);
                ascii = ascii + ch; 
            } 
            return ascii; 
        }
        
        String hextoBin(String input) 
        { 
            int n = input.length() * 4; 
            input = Long.toBinaryString(Long.parseUnsignedLong(input, 16)); 
            while (input.length() < n) 
                input = "0" + input; 
            return input; 
        } 
   
        String binToHex(String input) 
        { 
            int n = (int)input.length() / 4; 
            input = Long.toHexString(Long.parseUnsignedLong(input, 2)); 
            while (input.length() < n) 
                input = "0" + input; 
            return input; 
        } 
        
        //Add zeroes at end of hex string to convert length into multiple of 16 for block-size
        public static String AddZero(String HexadecimalValue)
        {
        for(int i=1;i<=16;i++)
        {
            if(HexadecimalValue.length()%16!=0)
            {
                HexadecimalValue = HexadecimalValue+"0";
                count = i;
            }
        }
        return HexadecimalValue;
        }
        
        public static String RemoveZero(String LetsRemoveZeroes)
        {
        	for(int i=1; i<=count; i++)
        	{        		    		
        			LetsRemoveZeroes = LetsRemoveZeroes.substring(0, LetsRemoveZeroes.length()-1);	
        	}
        	return LetsRemoveZeroes;
        }
 
        // per-mutate input hexadecimal according to specified sequence 
        String permutation(int[] sequence, String input) 
        { 
            String output = ""; 
            input = hextoBin(input); 
            for (int i = 0; i < sequence.length; i++) 
                output += input.charAt(sequence[i] - 1); 
            output = binToHex(output); 
            return output; 
        } 
        
        // Simple xor function on two int arrays
        String xor(String a, String b) 
        { 
            long t_a = Long.parseUnsignedLong(a, 16); 
            long t_b = Long.parseUnsignedLong(b, 16); 
            t_a = t_a ^ t_b; 
            a = Long.toHexString(t_a); 
            while (a.length() < b.length()) 
                a = "0" + a; 
            return a; 
        } 
  
        String leftCircularShift(String input, int numBits) 
        { 
            int n = input.length() * 4; 
            int perm[] = new int[n]; 
            for (int i = 0; i < n - 1; i++) 
                perm[i] = (i + 2); 
            perm[n - 1] = 1; 
            while (numBits-- > 0) 
                input = permutation(perm, input); 
            return input; 
        } 
  
        // preparing 16 keys for 16 rounds 
        String[] getKeys(String key) 
        { 
            String keys[] = new String[16];  
            key = permutation(PC1, key); 
            for (int i = 0; i < 16; i++) 
            { 
                key = leftCircularShift(key.substring(0, 7), shiftBits[i]) + leftCircularShift(key.substring(7, 14), shiftBits[i]); 
                keys[i] = permutation(PC2, key); 
            } 
            return keys; 
        } 
  
        String sBox(String input) 
        { 
            String output = ""; 
            input = hextoBin(input); 
            for (int i = 0; i < 48; i += 6) 
            { 
                String temp = input.substring(i, i + 6); 
                int num = i / 6; 
                int row = Integer.parseInt( 
                    temp.charAt(0) + "" + temp.charAt(5), 2); 
                int col = Integer.parseInt( 
                    temp.substring(1, 5), 2); 
                output += Integer.toHexString( 
                    sbox[num][row][col]); 
            } 
            return output; 
        } 
  
        String round(String input, String key, int num) 
        { 
            String left = input.substring(0, 8); 
            String temp = input.substring(8, 16); 
            String right = temp; 
            temp = permutation(EP, temp); 
            temp = xor(temp, key); 
            temp = sBox(temp); 
            temp = permutation(P, temp); 
            left = xor(left, temp); 
            return right + left; 
        } 
  
        String encrypt(String plainText, String key) 
        { 
            int i; 
            String keys[] = getKeys(key); 
            plainText = permutation(IP, plainText); 
            for (i = 0; i < 16; i++) 
            { 
                plainText = round(plainText, keys[i], i); 
            } 
            plainText = plainText.substring(8, 16) + plainText.substring(0, 8); 
            plainText = permutation(IP1, plainText); 
            return plainText; 
        } 
  
        String decrypt(String plainText, String key) 
        { 
            int i; 
            String keys[] = getKeys(key); 
            plainText = permutation(IP, plainText); 
            for (i = 15; i > -1; i--) 
            { 
                plainText = round(plainText, keys[i], 15 - i); 
            } 
            plainText = plainText.substring(8, 16) + plainText.substring(0, 8); 
            plainText = permutation(IP1, plainText); 
            return plainText; 
        } 
        
        //Creating random 16 digit hexadecimal key
        static String getAlphaNumericString(int n) 
        { 
            String AlphaNumericString = "ABCDEF" + "0123456789"; 
            StringBuilder sb = new StringBuilder(n); 
            for (int i = 0; i < n; i++) 
            { 
                int index = (int)(AlphaNumericString.length() * Math.random()); 
                sb.append(AlphaNumericString.charAt(index)); 
            } 
            return sb.toString(); 
        }
    } 	
	
	// Chuyển thông điệp từ định dạng string về bit
    public static int[] bit_Msg(String msg)
    {
    	int j=0;
    	int[] b_msg=new int[msg.length()*8];
    	for(int i=0;i<msg.length();i++)
    	{
    		int x=msg.charAt(i);
    		String x_s=Integer.toBinaryString(x);
    		while(x_s.length()!=8)
    		{
    			x_s='0'+x_s;
    		}
    		for(int i1=0;i1<8;i1++) 
    		{
    			b_msg[j] = Integer.parseInt(String.valueOf(x_s.charAt(i1)));
    			j++;
    		}
    	}
    	return b_msg;
    }
    // Đọc ảnh 
    public static BufferedImage Read_Image_File(String COVERIMAGEFILE)
    {
    	BufferedImage The_Image = null;
    	File p = new File (COVERIMAGEFILE);
    	try
    	{
    		The_Image = ImageIO.read(p);
    	}
    	catch (IOException e)
    	{
    		e.printStackTrace();
    		System.exit(1);
    	}
    	return The_Image;
    }
    
    // Giấu khóa vào ảnh
    
    public static void Hide_The_Message (int[] bits, BufferedImage The_Image) throws Exception
    {
    	File f = new File (CipherImagePathToSave);
    	BufferedImage sten_img=null;
    	int bit_l=bits.length/8;
    	int[] bl_msg=new int[8];
    	String bl_s=Integer.toBinaryString(bit_l);
    	while(bl_s.length()!=8)
    	{
    		bl_s='0'+bl_s;
    	}
    	for(int i1=0;i1<8;i1++) 
    	{
    		bl_msg[i1] = Integer.parseInt(String.valueOf(bl_s.charAt(i1)));
    	}
    	int j=0;
    	int b=0;
    	int Current_Bit_Entry=8;

    	for (int x = 0; x < The_Image.getWidth(); x++)
    	{
    		for ( int y = 0; y < The_Image.getHeight(); y++)
    		{
    			if(x==0&&y<8)
    			{
    				
    				int currentPixel = The_Image.getRGB(x, y);	
    				int ori=currentPixel;
    				int red = currentPixel>>16;
    				red = red & 255;
    				int green = currentPixel>>8;
    				green = green & 255;
    				int blue = currentPixel;
    				blue = blue & 255;
    				String x_s=Integer.toBinaryString(blue);
    				String sten_s=x_s.substring(0, x_s.length()-1);
    				sten_s=sten_s+Integer.toString(bl_msg[b]);
    				int temp=Integer.parseInt(sten_s,2);
    				int s_pixel=Integer.parseInt(sten_s, 2);
    				int a=255; 
    				int rgb = (a<<24) | (red<<16) | (green<<8) | s_pixel;
    				The_Image.setRGB(x, y, rgb);
    				ImageIO.write(The_Image, "png", f);
    				b++;
    				
    				
    			}
    			else if (Current_Bit_Entry < bits.length+8 )
    			{
    				int currentPixel = The_Image.getRGB(x, y);	
    				int ori=currentPixel;
    				int red = currentPixel>>16;
    				red = red & 255;
    				int green = currentPixel>>8;
    				green = green & 255;
    				int blue = currentPixel;
    				blue = blue & 255;
    				String x_s=Integer.toBinaryString(blue);
    				String sten_s=x_s.substring(0, x_s.length()-1);
    				sten_s=sten_s+Integer.toString(bits[j]);
    				j++;
    				int temp=Integer.parseInt(sten_s,2);
    				int s_pixel=Integer.parseInt(sten_s, 2);
    				int a=255;
    				int rgb = (a<<24) | (red<<16) | (green<<8) | s_pixel;
    				The_Image.setRGB(x, y, rgb);
    				ImageIO.write(The_Image, "png", f);
    				Current_Bit_Entry++;	
    			}
    		}
    	}
    }

	static String DECODEDMESSAGEFILE;
	public static String b_msg="";
	public static int len = 0;
	
	// Đọc ảnh
	public static BufferedImage Read_Image_File_1(String COVERIMAGEFILE)
	{
		BufferedImage The_Image = null;
		File p = new File (COVERIMAGEFILE);
		try
		{
			The_Image = ImageIO.read(p);
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		return The_Image;
	}
	 
	// Lấy khóa từ ảnh
	
	public static void Decode_The_Message (BufferedImage Y_Image) throws Exception
	{
		int j=0;
		int Current_Bit_Entry=0;
		String bx_msg="";
		for (int x = 0; x < Y_Image.getWidth(); x++)
		{
			for ( int y = 0; y < Y_Image.getHeight(); y++)
			{
				if(x==0&&y<8)
				{
					int currentPixel = Y_Image.getRGB(x, y);	
					int red = currentPixel>>16;
					red = red & 255;
					int green = currentPixel>>8;
	         		green = green & 255;
	         		int blue = currentPixel;
	         		blue = blue & 255;
	         		String x_s=Integer.toBinaryString(blue);
	         		bx_msg+=x_s.charAt(x_s.length()-1);
	         		len=Integer.parseInt(bx_msg,2);
				}
				else if(Current_Bit_Entry<len*8)
				{
					int currentPixel = Y_Image.getRGB(x, y);	
					int red = currentPixel>>16;
					red = red & 255;
					int green = currentPixel>>8;
    				green = green & 255;
    				int blue = currentPixel;
    				blue = blue & 255;
    				String x_s=Integer.toBinaryString(blue);
    				b_msg+=x_s.charAt(x_s.length()-1);
    				Current_Bit_Entry++;	
				}
			}
		}
	}
	
	
	// Thuật toán AES
	
	static Cipher cipher1;
	public static String encrypt(String plainText, SecretKey secretKey)throws Exception //function to encrypt the plaintext
	{
		byte[] plainTextByte = plainText.getBytes();
		cipher1.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher1.doFinal(plainTextByte);
		Base64.Encoder encoder = Base64.getEncoder();
		String encryptedText = encoder.encodeToString(encryptedByte);
		return encryptedText;
	}
	
	public static String decrypt(String encryptedText, SecretKey secretKey)throws Exception //function to decrypt the ciphertext
	{
		Base64.Decoder decoder = Base64.getDecoder();
		byte[] encryptedTextByte = decoder.decode(encryptedText);
		cipher1.init(Cipher.DECRYPT_MODE, secretKey);
		byte[] decryptedByte = cipher1.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);
		return decryptedText;
	}
	
	// Mã hóa file
	
    void RunningTheCompleteEncryptFunction() throws Exception
    {
    	try 
    	{ 
	        BufferedWriter out = new BufferedWriter(new FileWriter(CipherFilePathToSave,true));
	    }
	    catch(IOException e) 
    	{
	    	System.out.println("error occured");
	    	e.printStackTrace();
	    }
    	String Inside_File_To_Be_Encrypted = TextFromPlaintextFile;
    	System.out.println("\nPlaintext to be encrypted : \n" + TextFromPlaintextFile + "\n\n");
        int leng= Inside_File_To_Be_Encrypted.length();
		if(leng%3==1)
		{
			leng=leng+2;
			Inside_File_To_Be_Encrypted += "  ";
		}
		else if(leng%3==2)
		{
			leng=leng+1;
			Inside_File_To_Be_Encrypted += " ";
		}
		else
		{
			leng=leng;
		}
		
		// Chia plaintext thành 3 phần bằng nhau
		
		int numPart=3;
		String[] newStr = new String [numPart];
		int tempo=0;
		int len_size=leng/numPart;
		for(int i=0;i<leng;i=i+len_size)
		{
			String part= Inside_File_To_Be_Encrypted.substring(i, i+len_size);  
           newStr[tempo] = part;
           tempo++;
		}
		String plaintext1,plaintext2,plaintext3;
		for(int i=0;i<newStr.length;i++)
		{
			if(i==0)
			{
				plaintext1=newStr[i];
			}
			if(i==1)
			{
				plaintext2=newStr[i];
			}
			if(i==2)
			{
				plaintext3=newStr[i];
			}
		}
		
		
		System.out.println("Pha mã hóa : \n");
		for(int x=0;x<newStr.length;x++)
		{
			// Mã hóa sử dụng DES
			
			if(x==0)
				{	
				long startTime = Instant.now().toEpochMilli();
				plaintext1=newStr[x];
				DES cipher = new DES();
				// Tạo ngẫu nhiên khóa 16 ký tự hexa
				String key = cipher.getAlphaNumericString(16); 
				String plaintext = plaintext1;
				// Đưa các ký tự ASCII của plaintext về dạng hexa
				String hexvalue = cipher.ASCIItoHEX(plaintext);
				// Padding 0 vào sau plaintext sao cho độ dài của plaintext dạng hexa là bội của 16
				String NewHexValue = cipher.AddZero(hexvalue); 
				String[] BlocksOfPlaintext = new String[100000000];
				String[] EncryptedText = new String[100000000];
				// Chia plaintext thành các block 16 ký tự hexa
				for(int i=0;i<NewHexValue.length();i=i+16)
				{
					BlocksOfPlaintext[i] = NewHexValue.substring(i, i+16);
					String text = BlocksOfPlaintext[i];
					// Mã hóa từng block
					EncryptedText[i] = cipher.encrypt(text, key);
				}
				// Tổng hợp các cipher block để tạo thành 1 ciphertext 
				String CombinedCipherText="";
				
				for(int i=0;i<NewHexValue.length();i=i+16)
				{
					CombinedCipherText = CombinedCipherText+EncryptedText[i];
				}
				
				try 
				{ 
					BufferedWriter out = new BufferedWriter(new FileWriter(CipherFilePathToSave,true));
					out.write(CombinedCipherText);
					out.write("\n");
					out.close();
				}
				catch(IOException e) 
				{
					System.out.println("error occured");
					e.printStackTrace();
				} 
				
//				Instant end = Instant.now();
				ConcatKeysAfterEncryption = ConcatKeysAfterEncryption + key + "\n";
				System.out.println("DES : \nKey : "+ key +"\nEncrypted text : "+CombinedCipherText+"\n");
//				Duration interval = Duration.between(start, end);
				long endTime = Instant.now().toEpochMilli();
				long timeElapsed = endTime - startTime;
//				System.out.println("Execution time DES Encrypt (milliseconds): " + timeElapsed);
//				System.out.println("Execution time DES in seconds: " + interval.getSeconds());
			}
			
			// Mã hóa sử dụng AES
			
			if(x==1)
			{	
//				Instant start = Instant.now();
				long startTime = Instant.now().toEpochMilli();
				
				
				plaintext2=newStr[x];
				// Sử dụng thư viện KeyGenerator để sinh khóa 128 bit cho AES
				KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
				keyGenerator.init(128);
				SecretKey secretKey = keyGenerator.generateKey();
				byte[] aesKey = secretKey.getEncoded();
				String aesKeyBase64 = Base64.getEncoder().encodeToString(aesKey);
				cipher1 = Cipher.getInstance("AES");
				String plainText,encryptedText,decryptedText;
				plainText = plaintext2;
				// Mã hóa plaintext sử dụng AES
				encryptedText = encrypt(plainText, secretKey);
				
				
				try 
				{ 
					BufferedWriter out = new BufferedWriter(new FileWriter(CipherFilePathToSave,true));
					out.write(encryptedText);
					out.write("\n");
					out.close();
		        }
				finally 
				{}
				ConcatKeysAfterEncryption = ConcatKeysAfterEncryption + aesKeyBase64 + "\n";
//				Instant end = Instant.now();
				System.out.println("AES : \nKey : "+aesKeyBase64 + "\nEncrypted text : "+encryptedText+"\n");
				long endTime = Instant.now().toEpochMilli();
				long timeElapsed = endTime - startTime;
//				System.out.println("Execution time AES Encrypt (milliseconds): " + timeElapsed);
//						+ "\nEncrypted text : "+encryptedText+"\n");
//				Duration interval = Duration.between(start, end);
//				System.out.println("Execution time AES in seconds: " + interval.getSeconds());
			}
			
			//Mã hóa sử dụng RC4
			
			if(x==2)
			{	
//				Instant start = Instant.now();
				long startTime = Instant.now().toEpochMilli();
				
				
				plaintext3=newStr[x];
			    byte[] plainBytes = plaintext3.getBytes(StandardCharsets.UTF_8);
			    // Sử dụng thư viện KeyGenerator sinh khóa cho RC4
			    KeyGenerator rc4KeyGenerator = KeyGenerator.getInstance("RC4");
			    SecretKey key = rc4KeyGenerator.generateKey();
			    byte[] rc4Key = key.getEncoded();
			    String rc4KeyBase64 = Base64.getEncoder().encodeToString(rc4Key);
			    // Mã hóa plaintext sử dụng RC4 
			    Cipher cipherEnc = Cipher.getInstance("RC4");
			    cipherEnc.init(Cipher.ENCRYPT_MODE, key);
			    byte[] cipherBytes = cipherEnc.doFinal(plainBytes);
			    Base64.Encoder encoder = Base64.getEncoder();
			    String encryptedrc4Text = encoder.encodeToString(cipherBytes);
			    
			    
			    try 
			    { 
				    BufferedWriter out = new BufferedWriter(new FileWriter(CipherFilePathToSave,true));
				    out.write(encryptedrc4Text);
				    out.close();
				}
			    finally 
			    {}
			    ConcatKeysAfterEncryption = ConcatKeysAfterEncryption + rc4KeyBase64;
			    System.out.println("RC4 : \nKey : "+rc4KeyBase64 + "\nEncrypted text : "+encryptedrc4Text+"\n");
//			    Instant end = Instant.now();
//			    Duration interval = Duration.between(start, end);
//				System.out.println("Execution time RC4 in seconds: " + interval.getSeconds());
			    long endTime = Instant.now().toEpochMilli();
				long timeElapsed = endTime - startTime;
//				System.out.println("Execution time RC4 Encrypt (milliseconds): " + timeElapsed);
			}
		}  
		
		String Inside_Message_File = ConcatKeysAfterEncryption;
		int[] bits=bit_Msg(Inside_Message_File);
		BufferedImage The_Image = Read_Image_File(PathOfUserImage);
		Hide_The_Message(bits, The_Image);
		BufferedReader in = new BufferedReader(new FileReader(CipherFilePathToSave)); 
		String mystring = in.readLine(); 
    }
    
    // Giải mã file
    
    void RunningTheCompleteDecryptFunction() throws Exception
    {
    	System.out.println("\nPha giải mã : \n");
    	String InputForDESDecryption = TextFromCiphertextFileDES;
    	String InputForAESDecryption = TextFromCiphertextFileAES;
    	String InputForRC4Decryption = TextFromCiphertextFileRC4;
    	
    	BufferedImage Y_Image=Read_Image_File_1(PathOfImageWithKey);
    	Decode_The_Message(Y_Image);
    	
    	String msg="";
    	for(int x=0;x<len*8;x=x+8)
    	{
    		String sub=b_msg.substring(x,x+8);	
    		int m=Integer.parseInt(sub,2);
    		char ch=(char) m;
    		msg+=ch;
    	}  
    	DECODEDMESSAGEFILE = msg;
    	BufferedReader bfr = new BufferedReader(new StringReader(DECODEDMESSAGEFILE));
    	String KeyForDESDecryption=bfr.readLine();
    	String KeyForAESDecryption=bfr.readLine();
    	String KeyForRC4Decryption=bfr.readLine();
	
    	//DES starts here
    	
    	long startTime = Instant.now().toEpochMilli();
    	
    	
    	DES cipher = new DES();
    	String[] BlocksOfCipherText = new String[100000000];
    	String[] DecryptedText = new String[100000000];
    	for(int k=0;k<InputForDESDecryption.length();k=k+16)
    	{
    		BlocksOfCipherText[k] = InputForDESDecryption.substring(k, k+16);
    		String text = BlocksOfCipherText[k];
    		// Sử dụng DES giải mã từng block của ciphertext
    		DecryptedText[k] = cipher.decrypt(text, KeyForDESDecryption); 
    	}
    	
    	// Tổng hợp lại các block đã được giải mã thành 1 plaintext
    	String CombinedDecryptedText="";
    	for(int b=0;b<InputForDESDecryption.length();b=b+16)
    	{
    		CombinedDecryptedText = CombinedDecryptedText+DecryptedText[b];
    	}
    	// Loại bỏ các padding 0 sử dụng trong phần mã hóa
    	CombinedDecryptedText = cipher.RemoveZero(CombinedDecryptedText);
    	
    	
    	
    	String OriginalPlaintextRecovered = cipher.hexToASCII(CombinedDecryptedText);
    	System.out.println("DES : \nKey : "+KeyForDESDecryption+"\nDecrypted text : "+OriginalPlaintextRecovered+"\n");
    	long endTime = Instant.now().toEpochMilli();
    	 
        long timeElapsed = endTime - startTime;
 
//        System.out.println("Execution time in DES Decrypt (milliseconds) : " + timeElapsed);
//    	+"\nDecrypted text : "+OriginalPlaintextRecovered+"\n");
		
    	//AES starts here
        long startTime1 = Instant.now().toEpochMilli();
        
        
    	byte[] decodedKey = Base64.getDecoder().decode(KeyForAESDecryption);
    	SecretKey secretKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
    	cipher1 = Cipher.getInstance("AES");	
    	// Giải mã ciphertext sử dụng AES
    	String decryptedText = decrypt(InputForAESDecryption,secretKey);
    	
    	
    	System.out.println("AES : \nKey : "+KeyForAESDecryption+"\nDecrypted text : "+decryptedText+"\n");
    	long endTime1 = Instant.now().toEpochMilli();
   	 
        long timeElapsed1 = endTime1 - startTime1;
 
//        System.out.println("Execution time in AES Decrypt (milliseconds) : " + timeElapsed1);
//    	+"\nDecrypted text : "+decryptedText+"\n");

    	//RC4 starts here
        long startTime2 = Instant.now().toEpochMilli();
        
        
    	byte[] decodedrc4Key = Base64.getDecoder().decode(KeyForRC4Decryption);
    	SecretKey Key = new SecretKeySpec(decodedrc4Key, 0, decodedrc4Key.length, "RC4");        
    	Base64.Decoder decoder = Base64.getDecoder();
    	byte[] encryptedrc4TextByte = decoder.decode(InputForRC4Decryption);
    	Cipher cipherDec = Cipher.getInstance("RC4");
    	// Giải mã ciphertext sử dụng RC4
    	cipherDec.init(Cipher.DECRYPT_MODE,Key);
    	byte[] decryptBytes = cipherDec.doFinal(encryptedrc4TextByte);
    	
    	
    	
    	System.out.println("RC4 : \nKey : "+KeyForRC4Decryption +"\nDecrypted text : "+new String(decryptBytes)+"\n");
//    	+"\nDecrypted text : "+new String(decryptBytes)+"\n");
    	long endTime2 = Instant.now().toEpochMilli();
      	 
        long timeElapsed2 = endTime2 - startTime2;
 
//        System.out.println("Execution time in RC4 Decrypt (milliseconds) : " + timeElapsed2);
        
    	try 
    	{ 
		 	BufferedWriter bfr1 = new BufferedWriter(new FileWriter(PlaintextFileRecoveredPathToSave,true));
		    bfr1.write(OriginalPlaintextRecovered);
		    bfr1.write(decryptedText);
		    bfr1.write(new String(decryptBytes));
		    bfr1.close(); 
    	}
    	catch(IOException e) 
    	{
			System.out.println("error occured");
		    e.printStackTrace();
    	} 
    	
    	try 
    	{ 
		 	BufferedReader bfr2 = new BufferedReader(new FileReader(PlaintextFileRecoveredPathToSave));
		 	String PlaintextRecoveredafterDecryption = bfr2.readLine();
		 	System.out.println("Plaintext Recovered after Decryption : \n"+PlaintextRecoveredafterDecryption);
    	}
    	catch(IOException e) 
    	{
			System.out.println("error occured");
		    e.printStackTrace();
    	}
    }
}