package ch25_gui;

import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUIMain1 extends JFrame {
	
	private final String ADMIN_USERNAME = "admin";
	private final String ADMIN_PASSWORD = "1234";
	
	private CardLayout mainCardLayout;

	private JPanel mainCardPane;
	private JPanel loginPanel;
	private JPanel homePanel;
	private JTextField usernameTextField;
	private JPasswordField PasswordTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUIMain1 frame = new GUIMain1();
		frame.setVisible(true);
	}

	/**
	 * Create the frame.
	 */
	public GUIMain1() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 534, 359);
		mainCardPane = new JPanel();
		mainCardLayout = new CardLayout();
		mainCardPane.setLayout(mainCardLayout);// null이면 absolute로 설정 됨. 여기선 card layout 했음
		setContentPane(mainCardPane);// JFrame에 패널을 하나 설정해야 함. -> card layout 패널로 설정했음
		
		loginPanel = new JPanel();
		loginPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		loginPanel.setLayout(null);//null이면 absolute로 설정 됨.
		mainCardPane.add(loginPanel, "loginPanel");
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(159, 11, 162, 71);
		loginPanel.add(usernameTextField);
		usernameTextField.setColumns(10);
		
		PasswordTextField = new JPasswordField();
		PasswordTextField.setColumns(10);
		PasswordTextField.setBounds(159, 92, 162, 71);
		loginPanel.add(PasswordTextField);
		
		JButton signinBtn = new JButton("Login");
		signinBtn.addMouseListener(new MouseAdapter() {// 원래 구현해야 하는 인터페이스는 메소드가 너무 많아서, 원하는 메소드만 구현하기 좋도록, 대신 추상 클래스를 구현하도록 한다 -> 어뎁터 패턴
			@Override
			public void mouseClicked(MouseEvent e) {
				String username = usernameTextField.getText();
				String password = PasswordTextField.getText();
				
				if(!username.equals(ADMIN_USERNAME) || !password.equals(ADMIN_PASSWORD)) {
					JOptionPane.showMessageDialog(loginPanel, "사용자 정보가 일치하지 않습니다.", "로그인 실패", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				JOptionPane.showMessageDialog(loginPanel, "환영합니다.", "로그인 성공", JOptionPane.PLAIN_MESSAGE);
				mainCardLayout.show(mainCardPane, "homePanel");
			}
		});
		signinBtn.setBounds(159, 189, 162, 45);
		loginPanel.add(signinBtn);
		
		homePanel = new JPanel();
		homePanel.setLayout(null);
		mainCardPane.add(homePanel, "homePanel");
	}
}
