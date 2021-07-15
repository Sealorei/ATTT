import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Window.Type;

public class EncryptNoti extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EncryptNoti dialog = new EncryptNoti();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	
	// Thông báo sự kiện mã hóa 
	public EncryptNoti() {
		setResizable(false);
		setType(Type.POPUP);
		getContentPane().setBackground(SystemColor.controlHighlight);
		setBounds(100, 100, 300, 255);
		getContentPane().setLayout(null);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - getWidth()) / 2;
		final int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		
		JTextArea txtrFileEncryptedSuccessfully = new JTextArea();
		txtrFileEncryptedSuccessfully.setBackground(SystemColor.controlHighlight);
		txtrFileEncryptedSuccessfully.setEditable(false);
		txtrFileEncryptedSuccessfully.setFont(new Font("Calibri", Font.BOLD, 18));
		txtrFileEncryptedSuccessfully.setText("Mã hóa file thành công !");
		txtrFileEncryptedSuccessfully.setBounds(37, 32, 216, 21);
		getContentPane().add(txtrFileEncryptedSuccessfully);
		
		JTextArea txtrCiphertextFileSaved = new JTextArea();
		txtrCiphertextFileSaved.setBackground(SystemColor.controlHighlight);
		txtrCiphertextFileSaved.setEditable(false);
		txtrCiphertextFileSaved.setText("Ciphertext file được lưu tại : ");
		txtrCiphertextFileSaved.setFont(new Font("Calibri", Font.BOLD, 16));
		txtrCiphertextFileSaved.setBounds(37, 75, 216, 21);
		getContentPane().add(txtrCiphertextFileSaved);
		
		JTextArea txtrCipherImageSaved = new JTextArea();
		txtrCipherImageSaved.setBackground(SystemColor.controlHighlight);
		txtrCipherImageSaved.setEditable(false);
		txtrCipherImageSaved.setText("Cipher image được lưu tại : ");
		txtrCipherImageSaved.setFont(new Font("Calibri", Font.BOLD, 16));
		txtrCipherImageSaved.setBounds(37, 143, 216, 21);
		getContentPane().add(txtrCipherImageSaved);
		
		JTextArea txtrCiphertextFileSaved_1 = new JTextArea();
		Main w = new Main();
		txtrCiphertextFileSaved_1.setText(w.CipherFilePathToSave);
		txtrCiphertextFileSaved_1.setFont(new Font("Calibri", Font.ITALIC, 16));
		txtrCiphertextFileSaved_1.setEditable(false);
		txtrCiphertextFileSaved_1.setBackground(SystemColor.controlHighlight);
		txtrCiphertextFileSaved_1.setBounds(37, 102, 216, 21);
		getContentPane().add(txtrCiphertextFileSaved_1);
		
		JTextArea txtrCiphertextFileSaved_2 = new JTextArea();
		txtrCiphertextFileSaved_2.setText(w.CipherImagePathToSave);
		txtrCiphertextFileSaved_2.setFont(new Font("Calibri", Font.ITALIC, 16));
		txtrCiphertextFileSaved_2.setEditable(false);
		txtrCiphertextFileSaved_2.setBackground(SystemColor.controlHighlight);
		txtrCiphertextFileSaved_2.setBounds(37, 170, 216, 21);
		getContentPane().add(txtrCiphertextFileSaved_2);

	}

}
