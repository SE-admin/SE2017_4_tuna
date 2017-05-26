package se.smu;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	MainFrame(){
		//초기화면 설정
		setTitle("과목/ToDo 관리");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);
		setSize(1200, 700);
		setVisible(true);
		//초기화면 설정
	}
	
	public static void main(String[] args) {
		new MainFrame();
	}

}
