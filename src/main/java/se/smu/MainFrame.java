package se.smu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {

	private JPanel buttonPanel;
	private JButton subjectButton,todoButton,deleteButton;
	
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
		
		// 버튼 관리 패널 start
		buttonPanel = new JPanel(); 
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.setSize(10,30);
		subjectButton = new JButton("과목관리");
		subjectButton.addActionListener(new MyActionListener());
		todoButton = new JButton("총ToDo관리");
		todoButton.addActionListener(new MyActionListener());
		deleteButton = new JButton("삭제");
		deleteButton.addActionListener(new MyActionListener());
		buttonPanel.add(subjectButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(todoButton);
		buttonPanel.setSize(860,50);
		buttonPanel.setLocation(10,615);
		add(buttonPanel);
		// 버튼 관리 패널 end
	}
	
	class MyActionListener implements ActionListener { 
		public void actionPerformed(ActionEvent e) { 	
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("과목관리")){
				
			}
			else if(b.getText().equals("총ToDo관리")){
				
			}
			else if(b.getText().equals("삭제")){
				
			}
		}
	}
}

