package se.smu;

import java.awt.Color;
import java.awt.Component;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultCellEditor;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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

public class Frame4 extends JFrame {
		/**
	 * 
	 */
	private static final long serialVersionUID = -2491596652079118965L;
		static DefaultTableModel todotableModel;
		static int SelIndex;
		static int openCheck = 0;
		private JScrollPane todotableScrollpane;
		static JTable todoTable;
		
	Frame4(){
		//초기화면 설정
		setTitle("과목별 ToDO관리");
		setLayout(null);
		setResizable(false);
		setSize(650, 550);
		setVisible(true);
		//초기화면 설정
	
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
		todoTable.getModel().addTableModelListener(new CheckBoxModelListener());
		
		todoTable.getColumn("완료 여부").setCellRenderer(checkboxrender);
		doneCheckbox.setHorizontalAlignment(JLabel.CENTER);
		todoTable.getColumn("완료 여부").setCellEditor(new DefaultCellEditor(doneCheckbox));
		todoTable.getModel().addTableModelListener(new CheckBoxModelListener());
		
		todotableScrollpane = new JScrollPane(todoTable);
		todotableScrollpane.setSize(625,260);
		todotableScrollpane.setLocation(10,10);
		add(todotableScrollpane);
		//ToDO테이블 설정
		
		
		//초기화면 설정
		UIUpdate.UpdateTodoTable();
		//초기화면 설정
		
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

}

