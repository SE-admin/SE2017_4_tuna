package se.smu;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileFunction {
	 @SuppressWarnings("unchecked")
	public static ArrayList<Grade> loading(ArrayList<Grade> gradeList) throws ClassNotFoundException, IOException {
	        FileInputStream fis = new FileInputStream("save.txt");
	        ObjectInputStream ois = new ObjectInputStream(fis);
	        
	        gradeList = (ArrayList<Grade>)ois.readObject();
	        
	        fis.close();
	        ois.close();
	        return gradeList;
	    }
	
	public static void save(ArrayList<Grade> gradeList) throws IOException {
     
     FileOutputStream fos = new FileOutputStream("save.txt");
     ObjectOutputStream oos = new ObjectOutputStream(fos);

     oos.writeObject(gradeList);
     
     fos.close();
     oos.close();

 }

}
