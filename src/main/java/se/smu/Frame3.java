package se.smu;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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

public class Frame3 extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4327526838752658086L;
	static int openCheck = 0;
	static JTable todoTable;
	static DefaultTableModel todotableModel;
	private JCheckBox importantCheckbox = new JCheckBox();
	private JCheckBox doneCheckbox = new JCheckBox();
	private JPanel messagePanel = new JPanel();
	private JLabel yellowLabel = new JLabel("중요여부는 YELLOW", JLabel.CENTER);
	private JScrollPane todotableScrollpane;
	
	Frame3(){
		
		//초기화면 설정
		setTitle("총 ToDO관리");
		setResizable(false);
		setSize(650, 500);
		setVisible(true);
		setLayout(null);
		//초기화면 설정
	
		//테이블 설정
		String[] tablecolumnnameString = {"과목명","항목명","마감 기한","실제 마감일","완료 여부","중요 여부"};
		todotableModel = new DefaultTableModel(null, tablecolumnnameString){
			/**
			 * 
			 */
			private static final long serialVersionUID = 6994338893990077266L;

			public boolean isCellEditable(int i, int c){
				if (c == 5||c == 4){
					return true;
				}
				else{
					return false;
				}
				 
			 }
		};
		
		todoTable = new JTable(todotableModel);
		setTableCellRenderer(todoTable, new ColorRender());
		todoTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		todoTable.getColumn("중요 여부").setCellRenderer(checkboxrender);
		importantCheckbox.setHorizontalAlignment(JLabel.CENTER);
		todoTable.getColumn("중요 여부").setCellEditor(new DefaultCellEditor(importantCheckbox));
		todoTable.getModel().addTableModelListener(new CheckBoxModelListener());
		
		todoTable.getColumn("완료 여부").setCellRenderer(checkboxrender);
		doneCheckbox.setHorizontalAlignment(JLabel.CENTER);
		todoTable.getColumn("완료 여부").setCellEditor(new DefaultCellEditor(doneCheckbox));
		todoTable.getModel().addTableModelListener(new CheckBoxModelListener());
		
		JTableHeader todotableHeader = todoTable.getTableHeader();
		todotableHeader.setReorderingAllowed(false);
		
		todotableScrollpane = new JScrollPane(todoTable);
		todotableScrollpane.setSize(625,300);
		todotableScrollpane.setLocation(10,10);
		add(todotableScrollpane);
		//테이블 설정
		
		//안내 메시지 설정	
		messagePanel.setLayout(new GridLayout(3,1));
	
		yellowLabel.setOpaque(true);
		yellowLabel.setBackground(Color.YELLOW);

		messagePanel.add(yellowLabel);
		
		messagePanel.setSize(625,100);
		messagePanel.setLocation(10, 320);
		add(messagePanel);
		//안내 메시지 설정
		

	}
	
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
	    				GlobalVal.aToDo.get(aGnum).setimportant(1);
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
	    				System.out.println("aGnum :" + aGnum + ",  aTnum : " + aTnum + ",  value : " + GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).getimportant());
	    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setimportant(0);
	    				
	    				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
	    					if(GlobalVal.aToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))&&GlobalVal.aToDo.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
	    						aGnum = i;
	    						break;
	    					}
	    				}
	    				GlobalVal.aToDo.get(aGnum).setimportant(0);
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
	    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setdone(1);
	    				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
	    					if(GlobalVal.aToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))&&GlobalVal.aToDo.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
	    						aGnum = i;
	    						break;
	    					}
	    				}
	    				
	    				Date dt = new Date();
	    				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
	    				
	    				GlobalVal.aToDo.get(aGnum).setdone(1);
	    				GlobalVal.aToDo.get(aGnum).setendDate(sdf.format(dt).toString());
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
	    				System.out.println("aGnum :" + aGnum + ",  aTnum : " + aTnum + ",  value : " + GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).getdone());
	    				GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).setdone(0);
	    				
	    				for(int i = 0;i < GlobalVal.aToDo.size(); i++){
	    					if(GlobalVal.aToDo.get(i).gettodoName().equals(todotableModel.getValueAt(row, 1))&&GlobalVal.aToDo.get(i).getclassname().equals(todotableModel.getValueAt(row, 0))){
	    						aGnum = i;
	    						break;
	    					}
	    				}
	    				GlobalVal.aToDo.get(aGnum).setdone(0);
	    				GlobalVal.aToDo.get(aGnum).setendDate("");
	                }
	            }
	        }
	    }
	
	private void setTableCellRenderer(JTable todoTable, TableCellRenderer renderer) {
        TableColumnModel columnModel = todoTable.getColumnModel();
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
		private static final long serialVersionUID = -7323951366045427498L;

		public Component getTableCellRendererComponent(JTable todoTable, Object value, boolean selected, 
	          boolean focused, int row, int column) {
	
	            setEnabled(todoTable == null || todoTable.isEnabled());
	
	            setForeground(Color.black);
	            setBackground(Color.white);
	            
	            setHorizontalAlignment(SwingConstants.CENTER);
	            
		        int aGnum = 0;
		        @SuppressWarnings("unused")
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
				if(todoTable.getValueAt(row, 5).equals(true)){
	            	setBackground(Color.yellow);
	            }

	
	            super.getTableCellRendererComponent(todoTable, value, selected, focused, row, column);
	
	            return this;
	    }
	}

	DefaultTableCellRenderer checkboxrender = new DefaultTableCellRenderer()
	 {
	  /**
		 * 
		 */
		private static final long serialVersionUID = -8783386308838726207L;

	public Component getTableCellRendererComponent
	   (JTable todoTable, Object value, boolean isSelected, boolean hasFocus, int row, int column)
	  {
	   JCheckBox box= new JCheckBox();
	   box.setSelected(((Boolean)value).booleanValue());  
	   box.setHorizontalAlignment(JLabel.CENTER);
	   return box;
	  }
	 };
}
