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

}


public class GlobalVal {
	static ArrayList<Grade> aGrade = new ArrayList<Grade>();
}
