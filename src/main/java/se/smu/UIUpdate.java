package se.smu;

import java.awt.Component;
import java.io.IOException;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

public class UIUpdate {
	public static void UpdateTimeTable(String Year, String Seme){
		Object data[] = new Object[8];
		int row = 0;
		if (MainFrame.timetableModel.getRowCount() > 0) {
	  		   for (int i = MainFrame.timetableModel.getRowCount() - 1; i > -1; i--) {
	  		   	MainFrame.timetableModel.removeRow(i);
	  		   }
	  	}
		
	  	for(int i=0;i<23;i++)
	  	{
	  		data[0] = i;
	  		data[1] = "";
	  		data[2] = "";
	  		data[3] = "";
	  		data[4] = "";
	  		data[5] = "";
	  		data[6] = "";
	  		data[7] = "";
	  		MainFrame.timetableModel.insertRow(i, data);
	  	}
		for(int i=0;i<GlobalVal.aGrade.size();i++){
			if(GlobalVal.aGrade.get(i).getday() == 0){
				if(GlobalVal.aGrade.get(i).getyear().equals(Year)&&GlobalVal.aGrade.get(i).getsemester().equals(Seme)){
					data[0] = GlobalVal.aGrade.get(i).getclassname() + " (" + GlobalVal.aGrade.get(i).getprofessor() + ") ";
					for(int j = Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime());j<Integer.parseInt(GlobalVal.aGrade.get(i).getendtime());j++){
						row = j;
						MainFrame.timetableModel.setValueAt(data[0], row, 1);
					}
				}		
			}
			else if(GlobalVal.aGrade.get(i).getday() == 1){
				if(GlobalVal.aGrade.get(i).getyear().equals(Year)&&GlobalVal.aGrade.get(i).getsemester().equals(Seme)){
					data[0] = GlobalVal.aGrade.get(i).getclassname() + " (" + GlobalVal.aGrade.get(i).getprofessor() + ") ";
					for(int j = Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime());j<Integer.parseInt(GlobalVal.aGrade.get(i).getendtime());j++){
						row = j;
						MainFrame.timetableModel.setValueAt(data[0], row, 2);
					}
				}	
			}
			else if(GlobalVal.aGrade.get(i).getday() == 2){
				if(GlobalVal.aGrade.get(i).getyear().equals(Year)&&GlobalVal.aGrade.get(i).getsemester().equals(Seme)){
					data[0] = GlobalVal.aGrade.get(i).getclassname() + " (" + GlobalVal.aGrade.get(i).getprofessor() + ") ";
					for(int j = Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime());j<Integer.parseInt(GlobalVal.aGrade.get(i).getendtime());j++){
						row = j;
						MainFrame.timetableModel.setValueAt(data[0], row, 3);
					}
				}	
			}
			else if(GlobalVal.aGrade.get(i).getday() == 3){
				if(GlobalVal.aGrade.get(i).getyear().equals(Year)&&GlobalVal.aGrade.get(i).getsemester().equals(Seme)){
					data[0] = GlobalVal.aGrade.get(i).getclassname() + " (" + GlobalVal.aGrade.get(i).getprofessor() + ") ";
					for(int j = Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime());j<Integer.parseInt(GlobalVal.aGrade.get(i).getendtime());j++){
						row = j;
						MainFrame.timetableModel.setValueAt(data[0], row, 4);
					}
				}	
			}
			else if(GlobalVal.aGrade.get(i).getday() == 4){
				if(GlobalVal.aGrade.get(i).getyear().equals(Year)&&GlobalVal.aGrade.get(i).getsemester().equals(Seme)){
					data[0] = GlobalVal.aGrade.get(i).getclassname() + " (" + GlobalVal.aGrade.get(i).getprofessor() + ") ";
					for(int j = Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime());j<Integer.parseInt(GlobalVal.aGrade.get(i).getendtime());j++){
						row = j;
						MainFrame.timetableModel.setValueAt(data[0], row, 5);
					}
				}	
			}
			else if(GlobalVal.aGrade.get(i).getday() == 5){
				if(GlobalVal.aGrade.get(i).getyear().equals(Year)&&GlobalVal.aGrade.get(i).getsemester().equals(Seme)){
					data[0] = GlobalVal.aGrade.get(i).getclassname() + " (" + GlobalVal.aGrade.get(i).getprofessor() + ") ";
					for(int j = Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime());j<Integer.parseInt(GlobalVal.aGrade.get(i).getendtime());j++){
						row = j;
						MainFrame.timetableModel.setValueAt(data[0], row, 6);
					}
				}	
			}
			else if(GlobalVal.aGrade.get(i).getday() == 6){
				if(GlobalVal.aGrade.get(i).getyear().equals(Year)&&GlobalVal.aGrade.get(i).getsemester().equals(Seme)){
					data[0] = GlobalVal.aGrade.get(i).getclassname() + " (" + GlobalVal.aGrade.get(i).getprofessor() + ") ";
					for(int j = Integer.parseInt(GlobalVal.aGrade.get(i).getstarttime());j<Integer.parseInt(GlobalVal.aGrade.get(i).getendtime());j++){
						row = j;
						MainFrame.timetableModel.setValueAt(data[0], row, 7);
					}
				}	
			}
		}
		MainFrame.timetableModel.fireTableDataChanged();
		resizeColumnWidth(MainFrame.timeTable);
		try {
			FileFunction.save(GlobalVal.aGrade);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public static void UpdateAllTodoTable(){
     	Object data[] = new Object[6];
    		if(GlobalVal.aGrade.size()>0)
    		{
	  	  		if (Frame3.todotableModel.getRowCount() > 0) {
	  	  		    for (int i = Frame3.todotableModel.getRowCount() - 1; i > -1; i--) {
	  	  		    	Frame3.todotableModel.removeRow(i);
	  	  		    }
	  	  		}
	  	  		for(int i=0;i<GlobalVal.aToDo.size();i++)
	  	  		{
	  	  			data[0] = GlobalVal.aToDo.get(i).getclassname();
	 	  			data[1] = GlobalVal.aToDo.get(i).gettodoName();
	 	  			data[2] = GlobalVal.aToDo.get(i).getdeadLine();
	 	  			data[3] = GlobalVal.aToDo.get(i).getendDate();
	 	  			if (GlobalVal.aToDo.get(i).getdone() == 1){
	 	  				data[4] = true;
	 	  			}
	 	  			else{
	 	  				data[4] = false;
	 	  			}
	 	  			if (GlobalVal.aToDo.get(i).getimportant() == 1){
	 	  				data[5] = true;
	 	  			}
	 	  			else{
	 	  				data[5] = false;
	 	  			}
	 	  			
	 	  			Frame3.todotableModel.insertRow(i, data);
	  	  		}
	  	  		try {
	  	 			FileFunction.save(GlobalVal.aGrade);
	  	 		} catch (IOException e) {
	  	 		// TODO Auto-generated catch block
	  	 		e.printStackTrace();
	  	 	}
    	}
    	resizeColumnWidth(Frame3.todoTable);
    }
	
	public static void initJTable(){
    	if(GlobalVal.aGrade.size()>0)
   		{
   			int asize = GlobalVal.aToDo.size();
   			for(int i = 0;i<asize;i++){
   				GlobalVal.aToDo.remove(0);
   			}
   			for(int i = 0;i<GlobalVal.aGrade.size();i++){
   				for(int j = 0; j<GlobalVal.aGrade.get(i).arToDo.size(); j++)
   				{
   					GlobalVal.aToDo.add(GlobalVal.aGrade.get(i).arToDo.get(j));
   				}
   			}
   		}
    }
	
	
	 public static void resizeColumnWidth(JTable table) {
	 	    final TableColumnModel columnModel = table.getColumnModel();
	 	    for (int column = 0; column < table.getColumnCount(); column++) {
	 	        int width = 50;
	 	        for (int row = 0; row < table.getRowCount(); row++) {
	 	            TableCellRenderer renderer = table.getCellRenderer(row, column);
	 	            Component comp = table.prepareRenderer(renderer, row, column);
	 	            width = Math.max(comp.getPreferredSize().width +1 , width);
	 	        }
	 	        columnModel.getColumn(column).setPreferredWidth(width);
	 	    }
	 	}
}
