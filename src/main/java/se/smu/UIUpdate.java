package se.smu;

import java.awt.Component;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

class Todo_Index_Timeleft {
	long timeleft;
	int ith;
	int jth;
	public Todo_Index_Timeleft(long tl, int i, int j) {
		timeleft = tl;
		ith = i;
		jth = j;
	}
}

class compareByTime implements Comparator<Todo_Index_Timeleft> {
	
	public int compare(Todo_Index_Timeleft o1, Todo_Index_Timeleft o2) {
		 
		return o1.timeleft < o2.timeleft ? -1 : o1.timeleft > o2.timeleft ? 1:0;
	}

}

public class UIUpdate {

	public static void UpdateNearAlarmTable(){
    	 Object data[] = new Object[3];
  	  	if (MainFrame.nearalarmModel.getRowCount() > 0) {
  	  		   for (int i = MainFrame.nearalarmModel.getRowCount() - 1; i > -1; i--) {
  	  		    MainFrame.nearalarmModel.removeRow(i);
  	  		   }
  	  	}
  	  	
  	  	// 106번 줄 부터 161번줄 까지, 마감 시간과 제일가까운 3가지 todo 항목들을 자동으로 불러오는 기능을 구현함.
  	  	// -- todo 자동 설정 기능 시작 --
  	  	ArrayList<Todo_Index_Timeleft> listOfTodoByIndex = new ArrayList<Todo_Index_Timeleft>(); 	
  	  	
  	  	for(int i=0;i<GlobalVal.aGrade.size();i++)
  	  	{
  	  		for(int j=0;j<GlobalVal.aGrade.get(i).arToDo.size();j++){
  	  				String reqDateStr = GlobalVal.aGrade.get(i).arToDo.get(j).getdeadLine();
  	  				Date curDate = new Date(); 
  	  				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
  	  				Date reqDate = null;
  					try {
  						reqDate = dateFormat.parse(reqDateStr);
  					} catch (ParseException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					} 
  	  				long reqDateTime = reqDate.getTime();
  	  				try {
  						curDate = dateFormat.parse(dateFormat.format(curDate));
  					} catch (ParseException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					} 
  	  				long curDateTime = curDate.getTime();
  	  				long minute = (reqDateTime - curDateTime) / 1000;
  	  				Todo_Index_Timeleft temp = new Todo_Index_Timeleft(minute, i, j);
  	  				listOfTodoByIndex.add(temp);
  	  		}  		
  	  	}
  	  	
  	  	listOfTodoByIndex.sort(new compareByTime());
  	  	
  	  	int k = 0;
  	  	int closestTodoCnt = 0;
  	  	while (k < listOfTodoByIndex.size() && listOfTodoByIndex.size() > 0 && closestTodoCnt < 3) {
  	  		int x = listOfTodoByIndex.get(k).ith;
  	  		int y = listOfTodoByIndex.get(k).jth;
  	  		long time = listOfTodoByIndex.get(k).timeleft;
  	  		
  	  		data[0] = GlobalVal.aGrade.get(x).arToDo.get(y).getclassname();
  			data[1] = GlobalVal.aGrade.get(x).arToDo.get(y).gettodoName();
  			data[2] = time;
  			
  			if (time > 0) {
  				long sec = listOfTodoByIndex.get(k).timeleft;
	  			int day = (int)TimeUnit.SECONDS.toDays(sec);
	  			String timestr = null;
	  			if (day==0){
	  				timestr = String.format("%02d", TimeUnit.SECONDS.toHours(sec) - (day *24)) + "시간 ";
	  			}
	  			else{
	  				timestr = String.format("%02d", day) + "일 " + String.format("%02d", TimeUnit.SECONDS.toHours(sec) - (day *24)) + "시간 ";
	  			}
	  			data[2] = timestr;
  				MainFrame.nearalarmModel.insertRow(MainFrame.nearalarmModel.getRowCount(), data);		
  				closestTodoCnt++;
  			}
  			k++;
  		}  
  	  	//-- todo 자동설정 기능 끝 --
     }
     
     public static void UpdateAlarmTableForThread(){
    	 Object data[] = new Object[3];
    	 for(int i=0;i<MainFrame.setalarmModel.getRowCount();i++)
  	  	 {
    		 	int aGnum = 0, aTnum = 0;
				for(int j = 0;j < GlobalVal.aGrade.size(); j++){
					if(GlobalVal.aGrade.get(j).getclassname().equals(MainFrame.setalarmModel.getValueAt(i, 0))){
						aGnum = j;
						break;
					}
				}
				for(int j = 0;j < GlobalVal.aGrade.get(aGnum).arToDo.size(); j++){
					if(GlobalVal.aGrade.get(aGnum).arToDo.get(j).gettodoName().equals(MainFrame.setalarmModel.getValueAt(i, 1))){
						aTnum = j;
						break;
					}
				}
  	  			if(GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).getalarm() == 1){
  	  				data[0] = GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).getclassname();
  	  				data[1] = GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).gettodoName();
  	  				
  	  				String reqDateStr = GlobalVal.aGrade.get(aGnum).arToDo.get(aTnum).getdeadLine();
  	  				Date curDate = new Date(); 
  	  				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
  	  				Date reqDate = null;
  					try {
  						reqDate = dateFormat.parse(reqDateStr);
  					} catch (ParseException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					} 
  	  				long reqDateTime = reqDate.getTime();
  	  				try {
  						curDate = dateFormat.parse(dateFormat.format(curDate));
  					} catch (ParseException e) {
  						// TODO Auto-generated catch block
  						e.printStackTrace();
  					} 
  	  				long curDateTime = curDate.getTime();
  	  				long sec = (reqDateTime - curDateTime) / 1000;
  	  				int day = (int)TimeUnit.SECONDS.toDays(sec);
  	  				String timestr = null;
  	  				if (day==0){
  	  					timestr = String.format("%02d", TimeUnit.SECONDS.toHours(sec) - (day *24)) + "시간 ";
  	  				}
  	  				else{
  	  					timestr = String.format("%02d", day) + "일 " + String.format("%02d", TimeUnit.SECONDS.toHours(sec) - (day *24)) + "시간 ";
  	  				}
  	  				if (sec < 0){
  	  					data[2] = "시간초과";
  	  				}
  	  				else{
  	  					data[2] = timestr;
  	  				}
  	  				MainFrame.setalarmModel.setValueAt(data[2], i, 2);
  	  				MainFrame.setalarmModel.fireTableDataChanged();
  	  			}
  	  	}
     }
     
     public static void UpdateSetAlarmTable(){
     	Object data[] = new Object[3];
 	  	if (MainFrame.setalarmModel.getRowCount() > 0) {
 	  		   for (int i = MainFrame.setalarmModel.getRowCount() - 1; i > -1; i--) {
 	  		    MainFrame.setalarmModel.removeRow(i);
 	  		   }
 	  	}
 	  	
 	  	for(int i=0;i<GlobalVal.aGrade.size();i++)
 	  	{
 	  		for(int j=0;j<GlobalVal.aGrade.get(i).arToDo.size();j++){
 	  			if(GlobalVal.aGrade.get(i).arToDo.get(j).getalarm() == 1){
 	  				data[0] = GlobalVal.aGrade.get(i).arToDo.get(j).getclassname();
 	  				data[1] = GlobalVal.aGrade.get(i).arToDo.get(j).gettodoName();
 	  				String reqDateStr = GlobalVal.aGrade.get(i).arToDo.get(j).getdeadLine();
 	  				Date curDate = new Date(); 
 	  				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
 	  				Date reqDate = null;
 					try {
 						reqDate = dateFormat.parse(reqDateStr);
 					} catch (ParseException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} 
 	  				long reqDateTime = reqDate.getTime();
 	  				try {
 						curDate = dateFormat.parse(dateFormat.format(curDate));
 					} catch (ParseException e) {
 						// TODO Auto-generated catch block
 						e.printStackTrace();
 					} 
 	  				long curDateTime = curDate.getTime();
 	  				long sec = (reqDateTime - curDateTime) / 1000;
 	  				int day = (int)TimeUnit.SECONDS.toDays(sec);
 	  				String timestr = null;
 	  				if (day==0){
 	  					timestr = String.format("%02d", TimeUnit.SECONDS.toHours(sec) - (day *24)) + "시간 ";
 	  				}
 	  				else{
 	  					timestr = String.format("%02d", day) + "일 " + String.format("%02d", TimeUnit.SECONDS.toHours(sec) - (day *24)) + "시간 ";
 	  				}
 	  				if (sec < 0){
 	  					data[2] = "시간초과";
 	  				}
 	  				else{
 	  					data[2] = timestr;
 	  				}
 	  				MainFrame.setalarmModel.insertRow(MainFrame.setalarmModel.getRowCount(), data);
 	  			}
 	  		}
 	  	}
 	  	
      }

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
	  	  			if(MainFrame.doneShow == 1){
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
		 	  			
		 	  			Frame3.todotableModel.insertRow(Frame3.todotableModel.getRowCount(), data);
	  	  			}
	  	  			else if(MainFrame.doneShow == 0){
		  	  			data[0] = GlobalVal.aToDo.get(i).getclassname();
		 	  			data[1] = GlobalVal.aToDo.get(i).gettodoName();
		 	  			data[2] = GlobalVal.aToDo.get(i).getdeadLine();
		 	  			data[3] = GlobalVal.aToDo.get(i).getendDate();
		 	  			if (GlobalVal.aToDo.get(i).getdone() == 1){
		 	  				continue;
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
		 	  			
		 	  			Frame3.todotableModel.insertRow(Frame3.todotableModel.getRowCount(), data);
		  	  		}
	  	  		}
	  	  		try {
	  	 			FileFunction.save(GlobalVal.aGrade);
	  	 		} catch (IOException e) {
	  	 		// TODO Auto-generated catch block
	  	 		e.printStackTrace();
	  	 	}
    	}
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
	
	public static void UpdateTodoTable(){
  		Object data[] = new Object[6];
  		if(GlobalVal.aGrade.size()>0)
  		{
	  		if (Frame4.todotableModel.getRowCount() > 0) {
	  		    for (int i = Frame4.todotableModel.getRowCount() - 1; i > -1; i--) {
	  		    	Frame4.todotableModel.removeRow(i);
	  		    }
	  		}
	  		
	  		if(MainFrame.doneShow == 1){
		  		for(int i=0;i<GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.size();i++)
		  		{
		  			data[0] = GlobalVal.aGrade.get(MainFrame.Selectedindex).getclassname();
		  			data[1] = GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).gettodoName();
	 	  			data[2] = GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getdeadLine();
	 	  			data[3] = GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getendDate();
	 	  			if(GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getdone() == 1){
	 	  				data[4] = true;
	 	  			}
	 	  			else{
	 	  				data[4] = false;
	 	  			}
	 	  			if(GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getimportant() == 1){
	 	  				data[5] = true;
	 	  			}
	 	  			else{
	 	  				data[5] = false;
	 	  			}
	 	  			
	 	  			Frame4.todotableModel.insertRow(Frame4.todotableModel.getRowCount(), data);
		  		}
	  		}
	  		else if(MainFrame.doneShow == 0){
	  			for(int i=0;i<GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.size();i++)
		  		{
		  			data[0] = GlobalVal.aGrade.get(MainFrame.Selectedindex).getclassname();
		  			data[1] = GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).gettodoName();
	 	  			data[2] = GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getdeadLine();
	 	  			data[3] = GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getendDate();
	 	  			if(GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getdone() == 1){
	 	  				continue;
	 	  			}
	 	  			else{
	 	  				data[4] = false;
	 	  			}
	 	  			if(GlobalVal.aGrade.get(MainFrame.Selectedindex).arToDo.get(i).getimportant() == 1){
	 	  				data[5] = true;
	 	  			}
	 	  			else{
	 	  				data[5] = false;
	 	  			}
	 	  			
	 	  			Frame4.todotableModel.insertRow(Frame4.todotableModel.getRowCount(), data);
		  		}
	  		}
	  		try {
	  			FileFunction.save(GlobalVal.aGrade);
	 		} catch (IOException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
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
