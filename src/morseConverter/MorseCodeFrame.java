package morseConverter;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.EventQueue;

public class MorseCodeFrame extends JFrame implements ActionListener
{
	JButton btnEncode;
	JButton btnDecode;
	JButton btnClear;
	
	JTextArea txtAreaInput;
	JTextArea txtAreaOutput;
	
	MorseCodeTree morseCoder;
	public MorseCodeFrame()
	{
		morseCoder = new MorseCodeTree();
		this.init();
	}
	
	public void init()
	{
		setBounds(100, 100, 450, 320);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Morse Code Translator");
		getContentPane().setLayout(null);
		
		JLabel lblPrompt = new JLabel("Enter text to encode or code to decode:");
		lblPrompt.setBounds(10, 11, 424, 14);
		getContentPane().add(lblPrompt);
		
		txtAreaInput = new JTextArea();
		txtAreaInput.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtAreaInput.setBounds(10, 36, 424, 100);
		txtAreaInput.setLineWrap(true);
		txtAreaInput.setWrapStyleWord(true);
		getContentPane().add(txtAreaInput);
		
		txtAreaOutput = new JTextArea();
		txtAreaOutput.setFont(new Font("Courier New", Font.PLAIN, 13));
		txtAreaOutput.setBounds(10, 181, 424, 100);
		txtAreaOutput.setEditable(false);
		txtAreaOutput.setLineWrap(true);
		txtAreaOutput.setWrapStyleWord(true);
		getContentPane().add(txtAreaOutput);
		
		btnEncode = new JButton("To Morse Code");
		btnEncode.setBounds(10, 147, 132, 23);
		getContentPane().add(btnEncode);
		btnEncode.addActionListener(this);
		
		btnDecode = new JButton("From Morse Code");
		btnDecode.setBounds(156, 147, 132, 23);
		getContentPane().add(btnDecode);
		btnDecode.addActionListener(this);
		
		btnClear = new JButton("Clear");
		btnClear.setBounds(302, 147, 132, 23);
		getContentPane().add(btnClear);
		btnClear.addActionListener(this);
	}
	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == btnClear)
		{
			txtAreaInput.setText("");
			txtAreaOutput.setText("");
		}
		else if (e.getSource() == btnEncode)
		{
			String encoded = morseCoder.encode(txtAreaInput.getText());
			if (encoded.isEmpty())
				encoded = "Enter some letters to encode.";
			txtAreaOutput.setText(encoded);
		}
		else if (e.getSource() == btnDecode)
		{
			String decoded = morseCoder.decode(txtAreaInput.getText());
			if (decoded.isEmpty())
				decoded = "Invalid morse code.";
			txtAreaOutput.setText(decoded);
		}
		
	}
	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					MorseCodeFrame frame = new MorseCodeFrame();
					frame.setVisible(true);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
}
