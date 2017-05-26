package se.smu;
import java.io.Serializable;
import java.util.ArrayList;

class Grade implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1997496594856334326L;
	private String classname;
	private String professor;
	private int day;
	private String starttime;
	private String endtime;
	private String year;
	private String semester;
	ArrayList<ToDo> arToDo = new ArrayList<ToDo>();
	
	public String getclassname(){
		return classname;
	}
	public void setclassname(String classname){
		this.classname = classname;
	}
	public String getprofessor(){
		return professor;
	}
	public void setprofessor(String professor){
		this.professor = professor;
	}
	public int getday(){
		return day;
	}
	public void setday(int day){
		this.day = day;
	}
	public String getstarttime(){
		return starttime;
	}
	public void setstarttime(String starttime){
		this.starttime = starttime;
	}
	public String getendtime(){
		return endtime;
	}
	public void setendtime(String endtime){
		this.endtime = endtime;
	}
	public String getyear(){
		return year;
	}
	public void setyear(String year){
		this.year = year;
	}
	public String getsemester(){
		return semester;
	}
	public void setsemester(String semester){
		this.semester = semester;
	}
	
	public Grade(String classname, String professor, int day, String starttime, String endtime, String year, String semester) {
		this.classname = classname; this.professor = professor; this.day = day; this.starttime = starttime; this.endtime = endtime; this.year = year; this.semester = semester;
	}
	
	public void addToDo(String todoName, String deadLine, String endDate, int done, int important){
		ToDo t1 = new ToDo(todoName,deadLine,endDate,done,important);
		arToDo.add(t1);
	}
}

class ToDo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1828696325771832008L;
	private String todoName;
	private String deadLine;
	private String endDate;
	private int done;
	private int important;
	private String classname;
	private int alarm;
	
	public ToDo(String todoName, String deadLine, String endDate, int done, int important) {
		this.todoName = todoName;this.deadLine = deadLine;this.endDate = endDate;this.done = done;this.important = important;
	}
	
	public ToDo(String todoName, String deadLine, String endDate, int done, int important, String classname) {
		this.todoName = todoName;this.deadLine = deadLine;this.endDate = endDate;this.done = done;this.important = important;this.classname = classname;
	}
	
	public String gettodoName(){
		return todoName;
	}
	
	public void settodoName(String todoName){
		this.todoName = todoName;
	}
	
	public String getdeadLine(){
		return deadLine;
	}
	
	public void setdeadLine(String deadLine){
		this.deadLine = deadLine;
	}
	
	public String getendDate(){
		return endDate;
	}
	
	public void setendDate(String endDate){
		this.endDate = endDate;
	}
	
	public String getclassname(){
		return classname;
	}
	
	public void setclassname(String classname){
		this.classname = classname;
	}
	
	public int getdone(){
		return done;
	}
	
	public void setdone(int done){
		this.done = done;
	}
	
	public int getimportant(){
		return important;
	}
	
	public void setimportant(int important){
		this.important = important;
	}
	
	public int getalarm(){
		return alarm;
	}
	
	public void SetAlarm(){
		alarm = 1;
	}
	
	public void UnSetAlarm(){
		alarm = 0;
	}
}

public class GlobalVal {
	static ArrayList<Grade> aGrade = new ArrayList<Grade>();
	static ArrayList<ToDo> aToDo = new ArrayList<ToDo>();
}
