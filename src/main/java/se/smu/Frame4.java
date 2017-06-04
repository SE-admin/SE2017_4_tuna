package se.smu;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableModel;

import se.smu.Frame3.CheckBoxModelListener;

public class Frame4 extends JFrame {
		/**
	 * 
	 */
	private static final long serialVersionUID = -2491596652079118965L;
	static DefaultTableModel todotableModel;
	private JTextField todonameTextfield = new JTextField();
	private JTextField deadlineyearTextfield = new JTextField();
	private JTextField enddateyearTextfield = new JTextField();
	private JLabel todonameLabel = new JLabel("항목명", JLabel.CENTER);
	private JLabel enddateyearLabel = new JLabel("마감기한(년)", JLabel.CENTER);
	private JLabel enddatemonthLabel = new JLabel("마감기한(월)", JLabel.CENTER);
	private JLabel enddatedayLabel = new JLabel("마감기한(일)", JLabel.CENTER);
	private JLabel enddatehourLabel = new JLabel("마감기한(시)", JLabel.CENTER);
	private JLabel enddateminLabel = new JLabel("마감기한(분)", JLabel.CENTER);
	private JLabel deadlineyearLabel = new JLabel("실제마감일(년)", JLabel.CENTER);
	private JLabel deadlinemonthLabel = new JLabel("실제마감일(월)", JLabel.CENTER);
	private JLabel deadlnedayLabel = new JLabel("실제마감일(일)", JLabel.CENTER);
	private JLabel deadlinehourLabel = new JLabel("실제마감일(시)", JLabel.CENTER);
	private JLabel deadlineminLabel = new JLabel("실제마감일(분)", JLabel.CENTER);
	private JLabel doneLabel = new JLabel("완료여부", JLabel.CENTER);
	private JLabel importantLabel = new JLabel("중요여부", JLabel.CENTER);
	private JPanel enddatelabelPanel = new JPanel();
	private JPanel deadlineinputPanel = new JPanel();
	private JPanel deadlinelabelPanel = new JPanel();
	private JPanel enddateinputPanel = new JPanel();
	private DefaultComboBoxModel<String> FindModel;
	private JComboBox<String> endDateMonthList;
	private JComboBox<String> endDateDayList;
	private JComboBox<String> endDateHourList;
	private JComboBox<String> endDateMinList;
	private JComboBox<String> deadLineMonthList;
	private JComboBox<String> deadLineDayList;
	private JComboBox<String> deadLineHourList;
	private JComboBox<String> deadLineMinList;
	private JComboBox<String> endList;
	private JComboBox<String> importantList;
	private JPanel buttonPanel = new JPanel();
	private JButton jb1 = new JButton("ToDo등록");
	private JButton jb2 = new JButton("ToDo수정");
	private JButton jb3 = new JButton("ToDo삭제");
	static JTable todoTable;
	private JPanel subinputPanel = new JPanel();
	static int SelIndex;
	private JPanel oxPanel = new JPanel();
	private JScrollPane todotableScrollpane;
	static int openCheck = 0;
	CheckBoxModelListener checkboxListener = new CheckBoxModelListener();
		
	Frame4(){
		//초기화면 설정
		setTitle("과목별 ToDO관리");
		setLayout(null);
		setResizable(false);
		setSize(650, 550);
		setVisible(true);
		//초기화면 설정
	
		//ToDo관리 버튼 설정
		jb1.addActionListener(new MyActionListener());
		jb2.addActionListener(new MyActionListener());
		jb3.addActionListener(new MyActionListener());
		buttonPanel.setLayout(new GridLayout(1,3));
		buttonPanel.add(jb1);
		buttonPanel.add(jb2);
		buttonPanel.add(jb3);
		buttonPanel.setSize(625,50);
		buttonPanel.setLocation(10,470);
		add(buttonPanel);
		//ToDo관리 버튼 설정
		
		//ToDo 입력 칸 설정
		enddatelabelPanel.setLayout(new GridLayout(1,5));
				
		deadlineinputPanel.setLayout(new GridLayout(1,5));
				
		deadlinelabelPanel.setLayout(new GridLayout(1,5));
				
		enddateinputPanel.setLayout(new GridLayout(1,5));

				
		String[] oxString = { "X", "O"};
		endList = new JComboBox<String>(oxString);
		endList.setSelectedIndex(0);
		importantList = new JComboBox<String>(oxString);
		importantList.setSelectedIndex(0);
			
		deadLineMonthList = new JComboBox<String>();
		((JLabel)deadLineMonthList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)deadLineMonthList.getModel();
		FindModel.removeAllElements();
		for(int i = 1;i <= 12;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		deadLineMonthList.setSelectedIndex(0);
				
		deadLineDayList = new JComboBox<String>();
		((JLabel)deadLineDayList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)deadLineDayList.getModel();
		FindModel.removeAllElements();
		for(int i = 1;i <= 31;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		deadLineDayList.setSelectedIndex(0);
				
		deadLineHourList = new JComboBox<String>();
		((JLabel)deadLineHourList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)deadLineHourList.getModel();
		FindModel.removeAllElements();
		for(int i = 0;i <= 23;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		deadLineHourList.setSelectedIndex(0);
				
		deadLineMinList = new JComboBox<String>();
		((JLabel)deadLineMinList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)deadLineMinList.getModel();
		FindModel.removeAllElements();
		for(int i = 0;i <= 59;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		deadLineMinList.setSelectedIndex(0);
				
		endDateMonthList = new JComboBox<String>();
		((JLabel)endDateMonthList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)endDateMonthList.getModel();
		FindModel.removeAllElements();
		for(int i = 1;i <= 12;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		endDateMonthList.setSelectedIndex(0);
				
		endDateDayList = new JComboBox<String>();
		((JLabel)endDateDayList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)endDateDayList.getModel();
		FindModel.removeAllElements();
		for(int i = 1;i <= 31;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		endDateDayList.setSelectedIndex(0);
				
		endDateHourList = new JComboBox<String>();
		((JLabel)endDateHourList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)endDateHourList.getModel();
		FindModel.removeAllElements();
		for(int i = 0;i <= 23;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		endDateHourList.setSelectedIndex(0);
				
		endDateMinList = new JComboBox<String>();
		((JLabel)endDateMinList.getRenderer()).setHorizontalAlignment(JLabel.CENTER);
		FindModel = (DefaultComboBoxModel<String>)endDateMinList.getModel();
		FindModel.removeAllElements();
		for(int i = 0;i <= 59;i++){
			FindModel.addElement(String.format("%02d", i));
		}
		endDateMinList.setSelectedIndex(0);
				

		subinputPanel.setLayout(new GridLayout(10,1));
		subinputPanel.add(todonameLabel);
		subinputPanel.add(todonameTextfield);
		subinputPanel.add(enddatelabelPanel);
		subinputPanel.add(deadlineinputPanel);
		subinputPanel.add(deadlinelabelPanel);
		subinputPanel.add(enddateinputPanel);
		subinputPanel.setSize(625,250);
		subinputPanel.setLocation(10,270);
				
		enddatelabelPanel.add(enddateyearLabel);
		enddatelabelPanel.add(enddatemonthLabel);
		enddatelabelPanel.add(enddatedayLabel);
		enddatelabelPanel.add(enddatehourLabel);
		enddatelabelPanel.add(enddateminLabel);
		deadlineinputPanel.add(deadlineyearTextfield);
		deadlineinputPanel.add(deadLineMonthList);
		deadlineinputPanel.add(deadLineDayList);
		deadlineinputPanel.add(deadLineHourList);
		deadlineinputPanel.add(deadLineMinList);
				
		deadlinelabelPanel.add(deadlineyearLabel);
		deadlinelabelPanel.add(deadlinemonthLabel);
		deadlinelabelPanel.add(deadlnedayLabel);
		deadlinelabelPanel.add(deadlinehourLabel);
		deadlinelabelPanel.add(deadlineminLabel);
		enddateinputPanel.add(enddateyearTextfield);
		enddateinputPanel.add(endDateMonthList);
		enddateinputPanel.add(endDateDayList);
		enddateinputPanel.add(endDateHourList);
		enddateinputPanel.add(endDateMinList);
				
		oxPanel.setLayout(new GridLayout(1,4));
		oxPanel.add(doneLabel);
		oxPanel.add(endList);
		oxPanel.add(importantLabel);
		oxPanel.add(importantList);
				
		oxPanel.setSize(625,30);
		oxPanel.setLocation(10,430);

		add(oxPanel);
		add(subinputPanel);
		//ToDo 입력칸 설정
		
		//ToDO 테이블 설정
		String[] colName = {"과목명","항목명","마감 기한","실제 마감일","완료 여부","중요 여부"};
		
		todotableModel = new DefaultTableModel(null, colName){
			/**
			 * 
			 */
			private static final long serialVersionUID = -8711605036881641918L;

			public boolean isCellEditable(int i, int c){
				if (c == 5||c == 4){
					return true;
				}
				else{
					return false;
				}
				 
			 }
		};
		JCheckBox importantCheckbox = new JCheckBox();
		JCheckBox doneCheckbox = new JCheckBox();
		
		todoTable = new JTable(todotableModel);
		setTableCellRenderer(todoTable, new ColorRender());
		todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JTableHeader header = todoTable.getTableHeader();
		header.setReorderingAllowed(false);
		
		todoTable.getColumn("중요 여부").setCellRenderer(checkboxrender);
		importantCheckbox.setHorizontalAlignment(JLabel.CENTER);
		todoTable.getColumn("중요 여부").setCellEditor(new DefaultCellEditor(importantCheckbox));
		
		todoTable.getColumn("완료 여부").setCellRenderer(checkboxrender);
		doneCheckbox.setHorizontalAlignment(JLabel.CENTER);
		todoTable.getColumn("완료 여부").setCellEditor(new DefaultCellEditor(doneCheckbox));
		
		todoTable.getModel().addTableModelListener(checkboxListener);
		
		todotableScrollpane = new JScrollPane(todoTable);
		todotableScrollpane.setSize(625,260);
		todotableScrollpane.setLocation(10,10);
		add(todotableScrollpane);
		//ToDO테이블 설정
		
		//초기화면 설정
		UIUpdate.UpdateTodoTable();
		//초기화면 설정
	}
	
	public boolean checkDate(String szDate, String szFormat) {
        
        boolean bResult = true;
        SimpleDateFormat oDateFormat = new SimpleDateFormat();
        @SuppressWarnings("unused")
		Date oDate = new Date();
 
        oDateFormat.applyPattern(szFormat);
        oDateFormat.setLenient(false);
         
        try {
            oDate = oDateFormat.parse(szDate);
        } catch (ParseException e) {
            bResult = false;
        }
         
        return bResult;
         
    }
	
	DefaultTableCellRenderer checkboxrender = new DefaultTableCellRenderer()
	 {
	  /**
		 * 
		 */
		private static final long serialVersionUID = 8367758138016776435L;

	public Component getTableCellRendererComponent  // 셀렌더러
	   (JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	  {
	   JCheckBox box= new JCheckBox();
	   box.setSelected(((Boolean)value).booleanValue());  
	   box.setHorizontalAlignment(JLabel.CENTER);
	   return box;
	  }
	 };
	 
	public class CheckBoxModelListener implements TableModelListener {

        public void tableChanged(TableModelEvent e) {
            int row = e.getFirstRow();
            int column = e.getColumn();
            int aGnum = 0, aTnum = 0;
            if (column == 5) {
                TableModel model = (TableModel) e.getSource();
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (checked) { //체크시
    				for(int i = 0;i < GlobalVal.aGrade.size(); i++){
    					if(GlobalVal.aGrade.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				for(int i = 0;i < GlobalVal.aGrade.get(aGnum).arToDo.size(); i++){
    					if(GlobalVal.aGrade.get(aGnum).arToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))){
    						aTnum = i;
    						break;
    					}
    				}
    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setimportant(1);
    				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
    					if(GlobalVal.aToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))&&GlobalVal.aToDo.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				if (Frame3.openCheck == 1){
    					GlobalVal.aToDo.get(aGnum).setimportant(1);
    					UIUpdate.UpdateAllTodoTable();
    				}
    				try {
						FileFunction.save(GlobalVal.aGrade);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                } else { // 체크해제시
                	for(int i = 0;i < GlobalVal.aGrade.size(); i++){
    					if(GlobalVal.aGrade.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				for(int i = 0;i < GlobalVal.aGrade.get(aGnum).arToDo.size(); i++){
    					if(GlobalVal.aGrade.get(aGnum).arToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))){
    						aTnum = i;
    						break;
    					}
    				}
    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setimportant(0);
    				
    				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
    					if(GlobalVal.aToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))&&GlobalVal.aToDo.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				if (Frame3.openCheck == 1){
    					GlobalVal.aToDo.get(aGnum).setimportant(0);
    					UIUpdate.UpdateAllTodoTable();
    				}
    				try {
						FileFunction.save(GlobalVal.aGrade);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
            else if (column == 4){
            	TableModel model = (TableModel) e.getSource();
                Boolean checked = (Boolean) model.getValueAt(row, column);
                if (checked) { //체크시
    				for(int i = 0;i < GlobalVal.aGrade.size(); i++){
    					if(GlobalVal.aGrade.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				for(int i = 0;i < GlobalVal.aGrade.get(aGnum).arToDo.size(); i++){
    					if(GlobalVal.aGrade.get(aGnum).arToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))){
    						aTnum = i;
    						break;
    					}
    				}
    				Date dt = new Date();
    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
    				
    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setdone(1);
    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setendDate(sdf.format(dt).toString());
    				UIUpdate.UpdateTodoTable();
    				
    				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
    					if(GlobalVal.aToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))&&GlobalVal.aToDo.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				if (Frame3.openCheck == 1){
    					GlobalVal.aToDo.get(aGnum).setdone(1);
    					GlobalVal.aToDo.get(aGnum).setendDate(sdf.format(dt).toString());
    					UIUpdate.UpdateAllTodoTable();
    				}
    				try {
						FileFunction.save(GlobalVal.aGrade);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                } else { // 체크해제시
                	for(int i = 0;i < GlobalVal.aGrade.size(); i++){
    					if(GlobalVal.aGrade.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				for(int i = 0;i < GlobalVal.aGrade.get(aGnum).arToDo.size(); i++){
    					if(GlobalVal.aGrade.get(aGnum).arToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))){
    						aTnum = i;
    						break;
    					}
    				}
    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setdone(0);
    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setendDate("");
    				UIUpdate.UpdateTodoTable();
    				
    				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
    					if(GlobalVal.aToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))&&GlobalVal.aToDo.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
    						aGnum = i;
    						break;
    					}
    				}
    				if (Frame3.openCheck == 1){
    					GlobalVal.aToDo.get(aGnum).setdone(0);
    					GlobalVal.aToDo.get(aGnum).setendDate("");
    					UIUpdate.UpdateAllTodoTable();
    				}
    				try {
						FileFunction.save(GlobalVal.aGrade);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
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
	
	public class ColorRender extends DefaultTableCellRenderer {
	    /**
		 * 
		 */
		private static final long serialVersionUID = 6328088993668885150L;

		public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, 
	          boolean focused, int row, int column) {
	
	            setEnabled(table == null || table.isEnabled());
	
	            setForeground(Color.black);
	            setBackground(Color.white);
	            setHorizontalAlignment(SwingConstants.CENTER);
	            
		        int aGnum = 0;
		        int aTnum = 0;
		        for(int i = 0;i < GlobalVal.aGrade.size(); i++){
					if(GlobalVal.aGrade.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
						aGnum = i;
						break;
					}
				}
				for(int i = 0;i < GlobalVal.aGrade.get(aGnum).arToDo.size(); i++){
					if(GlobalVal.aGrade.get(aGnum).arToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))){
						aTnum = i;
						break;
					}
				}
				if (GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).getalarm() == 1&&table.getValueAt(row, 5).equals(true)){
					setBackground(Color.cyan);
				}
				else if(table.getValueAt(row, 5).equals(true)){
	            	setBackground(Color.yellow);
	            }
				else if (GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).getalarm() == 1){
					setBackground(Color.pink);
				}
	
	            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
	
	            return this;
	    }
	}
	
	class MyActionListener implements ActionListener { 
		public void actionPerformed(ActionEvent e) { 	
			JButton b = (JButton)e.getSource();
			if(b.getText().equals("ToDo등록")){
				int check = 0;
				String classname = GlobalVal.aGrade.get(SelIndex).getclassname();
				String ToDoName = todonameTextfield.getText();
				String endDate = "";
				
				String deadLineYear = deadlineyearTextfield.getText();
				String deadLineMonth = (String) deadLineMonthList.getSelectedItem();
				String deadLineDay = (String) deadLineDayList.getSelectedItem();
				String deadLineHour = (String) deadLineHourList.getSelectedItem();
				String deadLineMin = (String) deadLineMinList.getSelectedItem();
				String deadLine = deadLineYear + "-" + deadLineMonth + "-" + deadLineDay + " " + deadLineHour + ":" + deadLineMin;
				if (!checkDate(deadLine,"yyyy-MM-dd HH:mm")){
					GlobalVal.ErrorName = "날짜오류";
					JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
					check = 1;
				}
				int done = endList.getSelectedIndex();			
				if (done == 1)
				{
					String endDateYear = enddateyearTextfield.getText();
					String endDateMonth = (String) endDateMonthList.getSelectedItem();
					String endDateDay = (String) endDateDayList.getSelectedItem();
					String endDateHour = (String) endDateHourList.getSelectedItem();
					String endDateMin = (String) endDateMinList.getSelectedItem();
					endDate = endDateYear + "-" + endDateMonth + "-" + endDateDay + " " + endDateHour + ":" + endDateMin;
					if (!checkDate(endDate,"yyyy-MM-dd HH:mm")){
						GlobalVal.ErrorName = "날짜오류";
						JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
						check = 1;
					}
				}
				int important = importantList.getSelectedIndex();
				ToDo t1 = new ToDo(ToDoName, deadLine, endDate, done, important, classname);
				for(int i=0;i<GlobalVal.aGrade.get(SelIndex).arToDo.size();i++)
				{
					if(GlobalVal.aGrade.get(SelIndex).arToDo.get(i).gettodoName().equals(ToDoName)){
						GlobalVal.ErrorName = "중복";
					JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
						check = 1;
						break;
					}
				}
				
				if (check == 0){
					GlobalVal.aGrade.get(SelIndex).arToDo.add(t1);
					todonameTextfield.setText("");
					deadlineyearTextfield.setText("");
					deadLineMonthList.setSelectedIndex(0);
					deadLineDayList.setSelectedIndex(0);
					deadLineHourList.setSelectedIndex(0);
					deadLineMinList.setSelectedIndex(0);
					enddateyearTextfield.setText("");
					endDateMonthList.setSelectedIndex(0);
					endDateDayList.setSelectedIndex(0);
					endDateHourList.setSelectedIndex(0);
					endDateMinList.setSelectedIndex(0);
					endList.setSelectedIndex(0);
					importantList.setSelectedIndex(0);
					UIUpdate.UpdateTodoTable();
				}
				if (Frame3.openCheck == 1&&check == 0){
					GlobalVal.aToDo.add(t1);
					UIUpdate.UpdateAllTodoTable();
				}
				UIUpdate.UpdateSetAlarmTable();
			}
			else if(b.getText().equals("ToDo수정")){
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
				int check = 0;
				int index = todoTable.getSelectedRow();
				String toDoName = todonameTextfield.getText();
				String deadLineYear = deadlineyearTextfield.getText();
				String deadLineMonth = (String) deadLineMonthList.getSelectedItem();
				String deadLineDay = (String) deadLineDayList.getSelectedItem();
				String deadLineHour = (String) deadLineHourList.getSelectedItem();
				String deadLineMin = (String) deadLineMinList.getSelectedItem();
				String deadLine = deadLineYear + "-" + deadLineMonth + "-" + deadLineDay + " " + deadLineHour + ":" + deadLineMin;

				
				String endDateYear = enddateyearTextfield.getText();
				String endDateMonth = (String) endDateMonthList.getSelectedItem();
				String endDateDay = (String) endDateDayList.getSelectedItem();
				String endDateHour = (String) endDateHourList.getSelectedItem();
				String endDateMin = (String) endDateMinList.getSelectedItem();
				String endDate = endDateYear + "-" + endDateMonth + "-" + endDateDay + " " + endDateHour + ":" + endDateMin;

				
				int done = endList.getSelectedIndex();		
				int important = importantList.getSelectedIndex();		
				Date dt = new Date();
				
				for(int i = 0; i < GlobalVal.aGrade.get(SelIndex).arToDo.size();i++){
					if (GlobalVal.aGrade.get(SelIndex).arToDo.get(i).gettodoName().equals(toDoName)){
						GlobalVal.ErrorName = "중복";
						JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
						check = 1;
						break;
					}
				}
				
				if (!checkDate(deadLine,"yyyy-MM-dd HH:mm")&&!deadlineyearTextfield.getText().equals("")&&!deadlineyearTextfield.getText().equals(null)){
					GlobalVal.ErrorName = "날짜오류";
					JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
					check = 1;
				}
				
				if (!checkDate(endDate,"yyyy-MM-dd HH:mm")&&!enddateyearTextfield.getText().equals("")&&!enddateyearTextfield.getText().equals(null)){
					GlobalVal.ErrorName = "날짜오류";
					JOptionPane.showMessageDialog(null,  GlobalVal.ErrorName + "입니다.\n 다시입력해주세요", GlobalVal.ErrorName, JOptionPane.ERROR_MESSAGE);
					check = 1;
				}
				
				
				if (index >= 0&&check == 0)
				{
					if ((!GlobalVal.aGrade.get(SelIndex).arToDo.get(index).gettodoName().equals(toDoName))&&(!toDoName.equals(""))){
						GlobalVal.aGrade.get(SelIndex).arToDo.get(index).settodoName(toDoName);
					}
					
					if ((!GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getdeadLine().equals(deadLine))&&(!deadLine.equals(""))&&!deadlineyearTextfield.getText().equals("")&&!deadlineyearTextfield.getText().equals(null)){
						GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setdeadLine(deadLine);
					}
					
					if ((!GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getendDate().equals(endDate))&&!enddateyearTextfield.getText().equals("")&&!enddateyearTextfield.getText().equals(null)){
						if(done == 1){
								GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setendDate(endDate);
						}
					}
					else if ((!GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getendDate().equals(endDate))&&enddateyearTextfield.getText().equals("")&&enddateyearTextfield.getText().equals(null)){
						if(done == 1){
							GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setendDate(sdf.format(dt).toString());
						}
					}
					
					if(done == 1){
						if ((GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getdone() != done)){
							GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setdone(1);
						}
					}
					else if(done == 0){
						if ((GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getdone() != done)){
							GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setendDate("");
							GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setdone(0);
						}
					}
					
					if (important == 1){
						if ((GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getimportant() != important)){
							GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setimportant(1);
						}
					}
					else if (important == 0){
						if ((GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getimportant() != done)){
							GlobalVal.aGrade.get(SelIndex).arToDo.get(index).setimportant(0);
						}
					}
					
					todonameTextfield.setText("");
					deadlineyearTextfield.setText("");
					deadLineMonthList.setSelectedIndex(0);
					deadLineDayList.setSelectedIndex(0);
					deadLineHourList.setSelectedIndex(0);
					deadLineMinList.setSelectedIndex(0);
					enddateyearTextfield.setText("");
					endDateMonthList.setSelectedIndex(0);
					endDateDayList.setSelectedIndex(0);
					endDateHourList.setSelectedIndex(0);
					endDateMinList.setSelectedIndex(0);
					endList.setSelectedIndex(0);
					importantList.setSelectedIndex(0);
					
					UIUpdate.UpdateTodoTable();
					UIUpdate.UpdateSetAlarmTable();
				}
			}
			else if(b.getText().equals("ToDo삭제")){
				int index = todoTable.getSelectedRow();
				int rowNum = 0;
				
				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
					if(GlobalVal.aGrade.get(SelIndex).arToDo.get(index).getclassname().equals(GlobalVal.aToDo.get(i).getclassname())){
						if(GlobalVal.aGrade.get(SelIndex).arToDo.get(index).gettodoName().equals(GlobalVal.aToDo.get(i).gettodoName())){
							rowNum = i;
							break;
						}
					}
				}

				if (index >= 0)
				{
					GlobalVal.aGrade.get(SelIndex).arToDo.remove(index);
					UIUpdate.UpdateTodoTable();
					
					if (Frame3.openCheck == 1){
						GlobalVal.aToDo.remove(rowNum);
						UIUpdate.UpdateAllTodoTable();
					}
				}
				UIUpdate.UpdateSetAlarmTable();
			}
			
		}
	}

}

