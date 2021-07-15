import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JTextArea;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;

public class DecryptNoti extends JDialog {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DecryptNoti dialog = new DecryptNoti();
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
	
	// Thống báo sự kiện giải mã
	
	public DecryptNoti() {
		getContentPane().setBackground(SystemColor.controlHighlight);
		setResizable(false);
		setBounds(100, 100, 314, 178);
		getContentPane().setLayout(null);
		final Toolkit toolkit = Toolkit.getDefaultToolkit();
		final Dimension screenSize = toolkit.getScreenSize();
		final int x = (screenSize.width - getWidth()) / 2;
		final int y = (screenSize.height - getHeight()) / 2;
		setLocation(x, y);
		
		JTextArea txtrFileDecryptedSuccessfully = new JTextArea();
		txtrFileDecryptedSuccessfully.setEditable(false);
		txtrFileDecryptedSuccessfully.setBackground(SystemColor.controlHighlight);
		txtrFileDecryptedSuccessfully.setFont(new Font("Calibri", Font.BOLD, 18));
		txtrFileDecryptedSuccessfully.setText("Giải mã file thành công !");
		txtrFileDecryptedSuccessfully.setBounds(35, 30, 217, 22);
		getContentPane().add(txtrFileDecryptedSuccessfully);
		
		JTextArea txtrRecoveredPlaintextFile = new JTextArea();
		txtrRecoveredPlaintextFile.setEditable(false);
		txtrRecoveredPlaintextFile.setBackground(SystemColor.controlHighlight);
		txtrRecoveredPlaintextFile.setFont(new Font("Calibri", Font.BOLD, 16));
		txtrRecoveredPlaintextFile.setText("Plaintext file được lưu tại :");
		txtrRecoveredPlaintextFile.setBounds(35, 75, 246, 16);
		getContentPane().add(txtrRecoveredPlaintextFile);
		
		JTextArea txtrRecoveredPlaintextFile_1 = new JTextArea();
		txtrRecoveredPlaintextFile_1.setEditable(false);
		Main q = new Main();
		txtrRecoveredPlaintextFile_1.setText(q.PlaintextFileRecoveredPathToSave);
		txtrRecoveredPlaintextFile_1.setFont(new Font("Calibri", Font.ITALIC, 16));
		txtrRecoveredPlaintextFile_1.setBackground(SystemColor.controlHighlight);
		txtrRecoveredPlaintextFile_1.setBounds(35, 102, 246, 16);
		getContentPane().add(txtrRecoveredPlaintextFile_1);

	}

}
