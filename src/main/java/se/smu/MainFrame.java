package se.smu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

public class MainFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7714016838917102658L;
	private JPanel buttonPanel;
	private JButton subjectButton,todoButton,deleteButton,doneshowButton;
	
	private JLabel timeLabel = new JLabel("현재 시간");
	
	static JTable timeTable;
	JScrollPane timetableScrollpane;
	static JComboBox<String> yearsemeList;
	static DefaultTableModel timetableModel;
	private JPanel timetablepanel = new JPanel();
	HashMap<String , Color> map = new HashMap<String , Color>();
	
	static int Selectedindex = 0;
	static int doneShow = 0;
	
	private Frame3 frame3;
	private Frame2 frame2;
	private Frame4 frame4;
	
	DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
	static JTable setalarmTable = new JTable();
	static JTable nearalarmTable = new JTable();
	static DefaultTableModel nearalarmModel;
	static DefaultTableModel setalarmModel;
	private JLabel setalarmLabel = new JLabel("설정된 알림", JLabel.CENTER);
	private JLabel nearalarmLabel = new JLabel("근접한 알림", JLabel.CENTER);
	private JPanel nearalarmPanel = new JPanel();
	private JPanel setalarmPanel = new JPanel();
	private JPanel alarmPanel = new JPanel();
	private JScrollPane setalarmtableScrollpane;
	private JScrollPane nearalarmtableScrollpane;
	
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
		doneshowButton = new JButton("완료항목 보이기");
		doneshowButton.addActionListener(new MyActionListener());
		buttonPanel.add(subjectButton);
		buttonPanel.add(deleteButton);
		buttonPanel.add(todoButton);
		buttonPanel.add(doneshowButton);
		buttonPanel.setSize(860,50);
		buttonPanel.setLocation(10,615);
		add(buttonPanel);
		// 버튼 관리 패널 end

		//현재시간 표기 start
		timeLabel.setSize(200,20);
		timeLabel.setLocation(1015,650);
		add(timeLabel);
		//현재시간 표기  end
		
		// TimeTable start
		timetablepanel.setLayout(new BorderLayout());
					
		yearsemeList = new JComboBox<String>();
		((JLabel)yearsemeList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		InitComboBox();
		yearsemeList.setSelectedIndex(0);
		yearsemeList.addActionListener(new ComboBoxListener());
					
		String[] tcolName = {"", "월","화","수","목","금","토","일"};
		timetableModel = new DefaultTableModel(null, tcolName){
			/**
			 * 
			 */
			private static final long serialVersionUID = 8263946920283084937L;

			public boolean isCellEditable(int i, int c){
				 return false;
			 }
		};

		timeTable = new JTable(timetableModel);
		setTableCellRenderer(timeTable, new TimeTableColorRender());
		JTableHeader header = timeTable.getTableHeader();
		header.setReorderingAllowed(false);
		timeTable.setRowHeight(50);
		timeTable.setRowSelectionAllowed(false);
		timeTable.setCellSelectionEnabled(true);
		timeTable.addMouseListener(mouseListener);
		timeTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		timetableScrollpane = new JScrollPane(timeTable);

		timetablepanel.add(yearsemeList, BorderLayout.PAGE_START);
		timetablepanel.add(timetableScrollpane, BorderLayout.CENTER);
		timetablepanel.setLocation(10,10);
		timetablepanel.setSize(860,600);
		add(timetablepanel);
		//timetable end
		



		//알림 start
		nearalarmPanel.setLayout(new BorderLayout());
		nearalarmPanel.add(nearalarmLabel, BorderLayout.PAGE_START);
		
		String[] alarmcolumnString = {"과목명","항목명","남은시간"};
		
		setalarmModel = new DefaultTableModel(null, alarmcolumnString){
			/**
			 * 
			 */
			private static final long serialVersionUID = 6325334502965261051L;

			public boolean isCellEditable(int i, int c){
				 return false;
			 }
		};
		
		setalarmTable = new JTable(setalarmModel);
		setalarmTable.setRowSelectionAllowed(false);
		for(int i = 0;i< setalarmTable.getColumnCount();i++){
			setalarmTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
		
		nearalarmModel = new DefaultTableModel(null, alarmcolumnString){
			/**
			 * 
			 */
			private static final long serialVersionUID = -1362482264460594947L;

			public boolean isCellEditable(int i, int c){
				 return false;
			 }
		};
		setTableCellRenderer(setalarmTable, new ColorRender());
		
		nearalarmTable = new JTable(nearalarmModel);
		nearalarmTable.setRowSelectionAllowed(false);
		for(int i = 0;i< nearalarmTable.getColumnCount();i++){
			nearalarmTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
		}
		
		
		setalarmPanel.setLayout(new BorderLayout());
		setalarmPanel.add(setalarmLabel, BorderLayout.PAGE_START);
		setalarmtableScrollpane = new JScrollPane(setalarmTable);
		nearalarmtableScrollpane = new JScrollPane(nearalarmTable);
		setalarmPanel.add(setalarmtableScrollpane, BorderLayout.CENTER);
		nearalarmPanel.add(nearalarmtableScrollpane, BorderLayout.CENTER);
		
		alarmPanel.setLayout(new GridLayout(2,1));
		alarmPanel.add(setalarmPanel);
		alarmPanel.add(nearalarmPanel);
		alarmPanel.setSize(300, 600);
		alarmPanel.setLocation(880,10);
		//알림 end
		
		
		
		
		//현재시간 표기 start
		
		timeLabel.setSize(200,20);
		timeLabel.setLocation(1015,650);
		//현재시간 표기  end
		
		
		
		//화면 추가
		add(timeLabel);
		add(buttonPanel);
		add(alarmPanel);
		add(timetablepanel);
		//화면추가
		
		
		
		
		//초기 화면 업데이트
		UIUpdate.UpdateTimeTable(yearsemeList.getSelectedItem().toString().split("년도 ")[0], yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0]);
		UIUpdate.UpdateSetAlarmTable();
		//초기 화면 업데이트

		//시간관리 쓰레드
		class TimeThread extends Thread{
            public void run(){
                while(true){
                	UIUpdate.UpdateNearAlarmTable();
                	UIUpdate.UpdateAlarmTableForThread();
                	
                    Date dt = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
                    timeLabel.setText("현재시간 : " + sdf.format(dt).toString());
                    try{
                        Thread.sleep(1000);
                    }catch(InterruptedException ue){
                        System.out.println(ue.getMessage());
                    }
                }
            }
        }
        TimeThread thread=new TimeThread();
        thread.start();
        //시간관리 쓰레드
        
        //초기 화면 업데이트
      	UIUpdate.UpdateTimeTable(yearsemeList.getSelectedItem().toString().split("년도 ")[0], yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0]);
        //초기 화면 업데이트
        
	}


	
	class ExitListener4 implements WindowListener{
		 
		 public void windowClosing(WindowEvent e){
			 Frame4.openCheck = 0;
		 }
		 public void windowOpened(WindowEvent e){}
		 public void windowActivated(WindowEvent e){}
		 public void windowDeactivated(WindowEvent e){}
		 public void windowClosed(WindowEvent e){}
		 public void windowIconified(WindowEvent e){}
		 public void windowDeiconified(WindowEvent e){}
	}

	MouseListener mouseListener = new MouseAdapter() { 
        public void mouseClicked(MouseEvent mouseEvent) {  
            
        	if (SwingUtilities.isLeftMouseButton(mouseEvent))
      	  	{
        		if (mouseEvent.getClickCount() == 2) {
        			if (Frame4.openCheck == 0){
        				int row = timeTable.getSelectedRow();
        				int col = timeTable.getSelectedColumn();
        				Selectedindex = -1;
        				for(int i = 0;i < GlobalVal.aGrade.size(); i++)
          	            {
        					if(row > -1&&col > -1)
        					{
        						if(timetableModel.getValueAt(row, col).toString().split(" " + "\\(" + GlobalVal.aGrade.get(i).getprofessor() + "\\)")[0].equals(GlobalVal.aGrade.get(i).getclassname())&&GlobalVal.aGrade.get(i).getyear().equals(yearsemeList.getSelectedItem().toString().split("년도 ")[0])&&GlobalVal.aGrade.get(i).getsemester().equals(yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0])){
        							Selectedindex = i;
        							break;
        						}
        						else{
            						Selectedindex = -1;
            					}
        					}
          	            }
	        			if (Selectedindex >= 0){
        					frame4 = new Frame4(Selectedindex);
        					frame4.setVisible(true);
        					frame4.addWindowListener(new ExitListener4());
        					Frame4.openCheck = 1;
        				}
        			}
        		}
      	  	} 
        } 
     };
	
     class ExitListener2 implements WindowListener{
		 
		 public void windowClosing(WindowEvent e){
			 Frame2.openCheck = 0;
		 }
		 public void windowOpened(WindowEvent e){}
		 public void windowActivated(WindowEvent e){}
		 public void windowDeactivated(WindowEvent e){}
		 public void windowClosed(WindowEvent e){}
		 public void windowIconified(WindowEvent e){}
		 public void windowDeiconified(WindowEvent e){}
	}
     
     class ExitListener3 implements WindowListener{
		 
		 public void windowClosing(WindowEvent e){
			 Frame3.openCheck = 0;
		 }
		 public void windowOpened(WindowEvent e){}
		 public void windowActivated(WindowEvent e){}
		 public void windowDeactivated(WindowEvent e){}
		 public void windowClosed(WindowEvent e){}
		 public void windowIconified(WindowEvent e){}
		 public void windowDeiconified(WindowEvent e){}
	}
     
     class MyActionListener implements ActionListener { 
 		public void actionPerformed(ActionEvent e) { 	
 			JButton b = (JButton)e.getSource();
 			if(b.getText().equals("과목관리")){
 				if (Frame2.openCheck == 0){
					frame2 = new Frame2();
					frame2.setVisible(true);
					frame2.addWindowListener(new ExitListener2());
					Frame2.openCheck = 1;
				}
 			}
 			else if(b.getText().equals("총ToDo관리")){
 				if (Frame3.openCheck == 0){
					frame3 = new Frame3();
					//frame3.getContentPane().setBackground(Color.ORANGE);
					frame3.setVisible(true);
					frame3.addWindowListener(new ExitListener3());
					Frame3.openCheck = 1;
				}
 			}
 			else if(b.getText().equals("삭제")){
 				int row = timeTable.getSelectedRow();
				int col = timeTable.getSelectedColumn();
				Selectedindex = -1;
				for(int i = 0;i < GlobalVal.aGrade.size(); i++)
  	            {
					if(row > -1&&col > -1)
					{
	  	            	if(timetableModel.getValueAt(row, col).toString().split(" " + "\\(" + GlobalVal.aGrade.get(i).getprofessor() + "\\)")[0].equals(GlobalVal.aGrade.get(i).getclassname())&&GlobalVal.aGrade.get(i).getyear().equals(yearsemeList.getSelectedItem().toString().split("년도 ")[0])&&GlobalVal.aGrade.get(i).getsemester().equals(yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0])){
	  	            		Selectedindex = i;
	  	            		break;
	  	            	}
	  	            	else{
	  	            		Selectedindex = -1;
	  	            	}
					}
  	            }
				if (Selectedindex>=0){
					if ((Frame3.openCheck == 1)&&(GlobalVal.aGrade.get(Selectedindex).arToDo.size()>0)){
						frame3.dispose();
						Frame3.openCheck = 0;
					}
					if (Frame4.openCheck == 1){
						frame4.dispose();
						Frame4.openCheck = 0;
					}
					GlobalVal.aGrade.remove(Selectedindex);
					UIUpdate.UpdateTimeTable(yearsemeList.getSelectedItem().toString().split("년도 ")[0], yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0]);
					InitComboBox();
				}
 			}
 			else if(b.getText().equals("완료항목 숨기기")){
 				b.setText("완료항목 보이기");
 				doneShow = 0;
 				if(frame4.openCheck == 1){
 					UIUpdate.UpdateTodoTable();
 				}
 				if(frame3.openCheck == 1){
 					UIUpdate.initJTable();
 					UIUpdate.UpdateAllTodoTable();
 				}
 				UIUpdate.UpdateSetAlarmTable();
 			}
 			else if(b.getText().equals("완료항목 보이기")){
 				b.setText("완료항목 숨기기");
 				doneShow = 1;
 				if(frame4.openCheck == 1){
 					UIUpdate.UpdateTodoTable();
 				}
 				if(frame3.openCheck == 1){
 					UIUpdate.initJTable();
 					UIUpdate.UpdateAllTodoTable();
 				}
 				UIUpdate.UpdateSetAlarmTable();
 			}
 		}
 	}
     
     class ComboBoxListener implements ActionListener{
 		@Override
 		public void actionPerformed(ActionEvent e) {
 			@SuppressWarnings("unchecked")
 			JComboBox<String> cb = (JComboBox<String>)e.getSource();
 			if(cb == yearsemeList){
 					if (yearsemeList.getItemCount() > 0){
 						UIUpdate.UpdateTimeTable(yearsemeList.getSelectedItem().toString().split("년도 ")[0], yearsemeList.getSelectedItem().toString().split("년도 ")[1].split("학기")[0]);
 					}
 					else{
 						java.util.Calendar cal = java.util.Calendar.getInstance();
 						UIUpdate.UpdateTimeTable(Integer.toString(cal.get(Calendar.YEAR)),"1");
 					}
 			}
 		}
 	}
     
	private void setTableCellRenderer(JTable table, TableCellRenderer renderer) {
	    TableColumnModel columnModel = table.getColumnModel();
	    int columnCount = columnModel.getColumnCount();
	
	    for (int i = 0; i < columnCount; i++) {
	        TableColumn column = columnModel.getColumn(i);
	        column.setCellRenderer(renderer);
	    }
	}
	
	public class TimeTableColorRender extends DefaultTableCellRenderer {
  	    /**
		 * 
		 */
		private static final long serialVersionUID = 507623465616618727L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, 
  	          boolean focused, int row, int column) {
  	    		Color color;
  	            setEnabled(table == null || table.isEnabled());
  	
  	            setForeground(Color.black);
  	            setBackground(Color.white);
  	            
  	            int r = 128;
  	            int g = 128;
  	            int b = 128;
  	            for(int i = 0;i < GlobalVal.aGrade.size(); i++)
  	            {
  	            	if(column!=0){
  	            		if(table.getValueAt(row, column).toString().split(" " + "\\(" + GlobalVal.aGrade.get(i).getprofessor() + "\\)")[0].equals(GlobalVal.aGrade.get(i).getclassname())){
  	  	            		String temp = GlobalVal.aGrade.get(i).getclassname();
  	  	            		if(map.containsKey(temp.split("_")[0])){
  	  	            			color = map.get(temp.split("_")[0]);
  	  	            		}
  	  	            		else{
  	  	            			r = r + (int)(Math.random()*90); 
  	  	            			if (r > 256){
  	  	            				r = r - 128;
  	  	            			}
  	  	            			g = g + (int)(Math.random()*90); 
  	  	            			if (g > 256){
  	  	            				g = g - 128;
  	  	            			}
  	  	            			b = b + (int)(Math.random()*90); 
  	  	            			if (b > 256){
  	  	            				b = b - 128;
  	  	            			}
  	  	 	            		color = new Color(r,g,b); 
  	  	            			map.put(temp.split("_")[0], color);
  	  	            		}
  	  	            		setBackground(color);
  	  	            	}	
  	            	}
  	            	
  	            }
  				
  				setHorizontalAlignment(SwingConstants.CENTER);
  				
  	            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
  	
  	            return this;
  	    }
  	}

  	public class ColorRender extends DefaultTableCellRenderer {
 	    /**
		 * 
		 */
		private static final long serialVersionUID = 7890368871292959782L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, 
 	          boolean focused, int row, int column) {
 	
 	            setEnabled(table == null || table.isEnabled());
 	
 	            setForeground(Color.black);
 	            setBackground(Color.white);
 	            
 				if(table.getValueAt(row, 2).equals("시간초과")){
 	            	setBackground(Color.LIGHT_GRAY);
 	            }
 				
 				setHorizontalAlignment(SwingConstants.CENTER);
 				
 	            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
 	
 	            return this;
 	    }
  	}
  	
	public static void InitComboBox() {
		DefaultComboBoxModel<String> FindModel = (DefaultComboBoxModel<String>)yearsemeList.getModel();
 		FindModel.removeAllElements();
 		ArrayList<String> arrList = new ArrayList<String>();
 		for(int i = 0;i < GlobalVal.aGrade.size();i++){
 			arrList.add(GlobalVal.aGrade.get(i).getyear() + "년도 " + GlobalVal.aGrade.get(i).getsemester() + "학기");
 		}
 		HashSet<String> hs = new HashSet<String>();
 		hs.addAll(arrList);
 		arrList.clear();
 		arrList.addAll(hs);
 		if (arrList.size()>0){
 			for(int i = 0;i < arrList.size();i++){
 	 			FindModel.addElement(arrList.get(i));
 	 		}
 		}
 		else{
 			java.util.Calendar cal = java.util.Calendar.getInstance();
			FindModel.addElement(Integer.toString(cal.get(Calendar.YEAR)) + "년도 " + "1" + "학기");
 		}
	}
}


