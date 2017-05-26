package se.smu;

import java.io.File;

import javax.swing.JFrame;

public class MainFrame extends JFrame {

	public static Boolean fileIsLive(String isLivefile) {
	     File f1 = new File(isLivefile); 
	     if ( f1.exists() ) {
	      return true;
	     } else {
	      return false;
	     }
	}
	
	public static void main(String[] args) throws Exception{
		if(fileIsLive("save.txt"))
		{
			GlobalVal.aGrade = FileFunction.loading(GlobalVal.aGrade);
		}
		new MainFrame();
	}
	
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
}
