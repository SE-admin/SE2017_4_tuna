package se.smu;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class Frame2 extends JFrame{
	
		/**
	 * 
	 */
	private static final long serialVersionUID = -5137665578420611571L;
		private JTextField classnameTextfield = new JTextField();
		private JTextField professorTextfield = new JTextField(); 
		private JTextField yearTextfield = new JTextField(); 
		private JComboBox<String> dayList;
		private JComboBox<String> starttimeList;
		private JComboBox<String> endtimeList;
		private JComboBox<String> semeList;
		private JPanel inputPanel;
		private JPanel buttonPanel;
		private JLabel classnameLabel;
		private JLabel professorLabel;
		private JLabel dayLabel;
		private JLabel starttimeLabel;
		private JLabel endtimeLabel;
		private JLabel yearLabel;
		private JLabel semesterLabel;
		private JButton addButton = new JButton("등록");
		private JButton modifyButton = new JButton("수정");
		private DefaultComboBoxModel<String> FindModel;
		static int openCheck;
		
		
		Frame2(){
			
			//초기설정
			setTitle("과목관리");
			setLayout(null);
			setResizable(false);
			setSize(800, 110);
			setVisible(true);
			//초기설정
			
			//버튼
			buttonPanel = new JPanel();
			add(buttonPanel);
			buttonPanel.add(addButton);
			buttonPanel.add(modifyButton);
			buttonPanel.setLocation(720,0);
			buttonPanel.setSize(80,100);
			addButton.addActionListener(new MyActionListener());
			modifyButton.addActionListener(new MyActionListener());
			//버튼
			
			//입력 칸 패널화
			inputPanel = new JPanel();
			inputPanel.setLayout(new GridLayout(2,7));
			add(inputPanel);
			inputPanel.setLocation(10,10);
			inputPanel.setSize(700,50);
			//입력 칸 패널화
			
			//입력 라벨
			classnameLabel = new JLabel("과목 명", JLabel.CENTER);
			inputPanel.add(classnameLabel);
			professorLabel = new JLabel("담당교수", JLabel.CENTER);
			inputPanel.add(professorLabel);
			dayLabel = new JLabel("강의요일", JLabel.CENTER);
			inputPanel.add(dayLabel);
			starttimeLabel = new JLabel("강의 시작시간", JLabel.CENTER);
			inputPanel.add(starttimeLabel);
			endtimeLabel = new JLabel("강의 종료시간", JLabel.CENTER);
			inputPanel.add(endtimeLabel);
			yearLabel = new JLabel("수강년도", JLabel.CENTER);
			inputPanel.add(yearLabel);
			semesterLabel = new JLabel("수강학기", JLabel.CENTER);
			inputPanel.add(semesterLabel);
			//입력 라벨
			
			//요일 입력
			String[] dayString = { "월", "화", "수", "목", "금", "토", "일" };
			dayList = new JComboBox<String>(dayString);
			((JLabel)dayList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
			dayList.setSelectedIndex(0);
			//요일 입력
			
			//종료시간 입력
			endtimeList = new JComboBox<String>();
			((JLabel)endtimeList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
			FindModel = (DefaultComboBoxModel<String>)endtimeList.getModel();
			FindModel.removeAllElements();
			for(int i = 0;i <= 23;i++){
				FindModel.addElement(String.format("%02d", i));
			}
			endtimeList.setSelectedIndex(0);
			//종료시간 입력
			
			//시작시간 입력
			starttimeList = new JComboBox<String>();
			((JLabel)starttimeList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
			starttimeList.addActionListener(new ComboBoxListener());
			FindModel = (DefaultComboBoxModel<String>)starttimeList.getModel();
			FindModel.removeAllElements();
			for(int i = 0;i <= 23;i++){
				FindModel.addElement(String.format("%02d", i));
			}
			starttimeList.setSelectedIndex(0);
			//시작시간 입력
			
			//학기 입력
			String[] semeString = {"1", "2", "하계계절", "동계계절"};
			semeList = new JComboBox<String>(semeString);
			((JLabel)semeList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
			semeList.setSelectedIndex(0);
			//학기 입력
			
			inputPanel.add(classnameTextfield);
			inputPanel.add(professorTextfield);
			inputPanel.add(dayList);
			inputPanel.add(starttimeList);
			inputPanel.add(endtimeList);
			inputPanel.add(yearTextfield);
			inputPanel.add(semeList);
			
	 
		}
		
		class ComboBoxListener implements ActionListener{
			@Override
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unchecked")
				JComboBox<String> cb = (JComboBox<String>)e.getSource();
				if(cb == starttimeList){
					DefaultComboBoxModel<String> FindModel = (DefaultComboBoxModel<String>)endtimeList.getModel();
					FindModel.removeAllElements();
					for(int i = starttimeList.getSelectedIndex()+1;i <= 23;i++){
						FindModel.addElement(String.format("%02d", i));
					}
				}
			}
		}
		
		class MyActionListener implements ActionListener  { 
			public void actionPerformed(ActionEvent e) { 
	    
				JButton b = (JButton)e.getSource();
				if(b.getText().equals("등록")){
					String classname = classnameTextfield.getText();
					String professor = professorTextfield.getText();
					int day = dayList.getSelectedIndex();
					String starttime = (String) starttimeList.getSelectedItem();
					String endtime = (String) endtimeList.getSelectedItem();
					String year = yearTextfield.getText();
					String semester = (String) semeList.getSelectedItem();
					Grade g1 = new Grade(classname,professor,day,starttime,endtime,year,semester);
					int check = 0;
					for(int i=0;i<GlobalVal.aGrade.size();i++)
					{
						if(GlobalVal.aGrade.get(i).getclassname().equals(g1.getclassname())){
							GlobalVal.ErrorName = "중복";
							JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
							check = 1;
							break;
						}
					}
					
					for(int i = 0;i<GlobalVal.aGrade.size();i++){
						if(GlobalVal.aGrade.get(i).getyear().equals(g1.getyear())&&GlobalVal.aGrade.get(i).getsemester().equals(g1.getsemester())&&(GlobalVal.aGrade.get(i).getday() == g1.getday())){
							if( (Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime()) <= Integer.parseInt(starttime)) && (Integer.parseInt(GlobalVal.aGrade.get(i).getendtime()) > Integer.parseInt(starttime)) ){
							GlobalVal.ErrorName = "시간중복";
							JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
								check = 1;
								break;
							}
							else if( (Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime()) < Integer.parseInt(endtime)) && (Integer.parseInt(GlobalVal.aGrade.get(i).getendtime()) >= Integer.parseInt(endtime)) ){
							GlobalVal.ErrorName = "시간중복";
							JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
								check = 1;
								break;
							}
						}
					}
					
					if(classnameTextfield.getText().equals(null) || classnameTextfield.getText().equals("")){
						GlobalVal.ErrorName = "과목명 공백";
						JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
						check = 1;
					}
					
					
					if (check == 0){
						GlobalVal.aGrade.add(g1);
						UIUpdate.UpdateTimeTable(MainFrame.yearsemeList.getSelectedItem().toString().split("년도 ")[0], MainFrame.yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0]);
						MainFrame.InitComboBox();
						classnameTextfield.setText("");
						dayList.setSelectedIndex(0);
						starttimeList.setSelectedIndex(0);
						endtimeList.setSelectedIndex(0);
						semeList.setSelectedIndex(0);
						professorTextfield.setText("");
						yearTextfield.setText("");
					}
					
				}
				else if(b.getText().equals("수정")){
					String classname = classnameTextfield.getText();
					String professor = professorTextfield.getText();
					int day = dayList.getSelectedIndex();
					String starttime = (String) starttimeList.getSelectedItem();
					String endtime = (String) endtimeList.getSelectedItem();
					String year = yearTextfield.getText();
					String semester = (String) semeList.getSelectedItem();
					int index = -1;
					int row = MainFrame.timeTable.getSelectedRow();
    				int col = MainFrame.timeTable.getSelectedColumn();
    				int check = 0;
					for(int i = 0;i < GlobalVal.aGrade.size(); i++)
	  	            {
						if(row > -1&&col > -1)
						{
							if(MainFrame.timetableModel.getValueAt(row, col).toString().split(" " + "\\(" + GlobalVal.aGrade.get(i).getprofessor() + "\\)")[0].equals(GlobalVal.aGrade.get(i).getclassname())&&GlobalVal.aGrade.get(i).getyear().equals(MainFrame.yearsemeList.getSelectedItem().toString().split("년도 ")[0])&&GlobalVal.aGrade.get(i).getsemester().equals(MainFrame.yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0])){
								index = i;
								break;
							}
							else{
								index = -1;
        						GlobalVal.ErrorName = "과목선택이 안되어있습니다.";
								JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "\n선택후 다시 진행해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
							}
						}
	  	            }

					
					if (index >= 0)
					{
						if(year.equals("")){
							year = GlobalVal.aGrade.get(index).getyear();
						}
						
						for(int i=0;i<GlobalVal.aGrade.size();i++)
						{
							if(GlobalVal.aGrade.get(i).getclassname().equals(classname)){
								GlobalVal.ErrorName = "중복";
								JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
								check = 1;
								break;
							}
							
							if((!GlobalVal.aGrade.get(index).getclassname().equals(GlobalVal.aGrade.get(i).getclassname())))
							{
								if(GlobalVal.aGrade.get(i).getyear().equals(year)&&GlobalVal.aGrade.get(i).getsemester().equals(semester)&&GlobalVal.aGrade.get(i).getday() == day){
									if( (Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime()) <= Integer.parseInt(starttime)) && (Integer.parseInt(GlobalVal.aGrade.get(i).getendtime()) > Integer.parseInt(starttime)) ){
										GlobalVal.ErrorName = "시간중복";
										JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
										check = 1;
										break;
									}
									else if( (Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime()) < Integer.parseInt(endtime)) && (Integer.parseInt(GlobalVal.aGrade.get(i).getendtime()) >= Integer.parseInt(endtime)) ){
										GlobalVal.ErrorName = "시간중복";
										JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
										check = 1;
										break;
									}
								}
							}
						}
						
						if (check == 0){
							if ((!GlobalVal.aGrade.get(index).getclassname().equals(classname))&&(!classname.equals(""))){
								GlobalVal.aGrade.get(index).setclassname(classname);
							}
							if ((!GlobalVal.aGrade.get(index).getprofessor().equals(professor))&&(!professor.equals(""))){
								GlobalVal.aGrade.get(index).setprofessor(professor);
							}
							if (GlobalVal.aGrade.get(index).getday() != day){
								GlobalVal.aGrade.get(index).setday(day);
							}
							if ((!GlobalVal.aGrade.get(index).getstarttime().equals(starttime))&&(!starttime.equals(""))){
								GlobalVal.aGrade.get(index).setstarttime(starttime);
							}
							if ((!GlobalVal.aGrade.get(index).getendtime().equals(endtime))&&(!endtime.equals(""))){
								GlobalVal.aGrade.get(index).setendtime(endtime);
							}
							if ((!GlobalVal.aGrade.get(index).getyear().equals(year))&&(!year.equals(""))){
								GlobalVal.aGrade.get(index).setyear(year);
							}
							if ((!GlobalVal.aGrade.get(index).getsemester().equals(semester))&&(!semester.equals(""))){
								GlobalVal.aGrade.get(index).setsemester(semester);
							}
							
							for(int i = 0; i < GlobalVal.aGrade.get(index).arToDo.size(); i++){
								GlobalVal.aGrade.get(index).arToDo.get(i).setclassname(GlobalVal.aGrade.get(index).getclassname());
							}
							
							classnameTextfield.setText("");
							dayList.setSelectedIndex(0);
							starttimeList.setSelectedIndex(0);
							endtimeList.setSelectedIndex(0);
							semeList.setSelectedIndex(0);
							professorTextfield.setText("");
							yearTextfield.setText("");
						}
						UIUpdate.UpdateTimeTable(MainFrame.yearsemeList.getSelectedItem().toString().split("년도 ")[0], MainFrame.yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0]);
						UIUpdate.UpdateSetAlarmTable();
						if(Frame3.openCheck == 1){
							UIUpdate.initJTable();
							UIUpdate.UpdateAllTodoTable();
						}
						if(Frame4.openCheck == 1){
							UIUpdate.UpdateTodoTable();
						}
						MainFrame.InitComboBox();
					}
				}
			}
		}
}