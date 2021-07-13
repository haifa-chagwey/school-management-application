package P1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;	


public class StudentDBUtil {
	
	public static Connection ConnectToDB() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web_student_tracker","root","XXXXXX");
		return myConn;
	}

	private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws Exception {
		if (myRs != null) {
			myRs.close();
			}
		
		if (myStmt != null) {
			myStmt.close();
			}
		
		if (myConn != null) {
			myConn.close();
			}   
	
	}
	
	public static List<Student> getStudents() throws Exception {
//	public static void main (String[] args) throws Exception{

		Connection myConn = ConnectToDB();
		Statement myStmt = myConn.createStatement();
		String sql = "select * from student";
		ResultSet myRs = myStmt.executeQuery(sql);
		// create an empty list
		List<Student> students = new ArrayList<>();
		// add sample data
		while (myRs.next()) {
			int id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
			Student tempStudent = new Student(id,firstName,lastName , email );
			students.add(tempStudent);
			}
		close(myConn,myStmt,myRs);
		// return the list
		return students;
		
		}
	
	

	public static void addStudent(Student student) throws Exception {
//	public static void main (String[] args) throws Exception{

			Connection myConn = ConnectToDB();
 			String sql = "insert into student(first_name,last_name,email) values(?,?,?) ";
			PreparedStatement myStmt=myConn.prepareStatement(sql);
			myStmt.setString(1,student.getFirstName());
			myStmt.setString(2,student.getLastName());
			myStmt.setString(3,student.getEmail());
			myStmt.execute();
			close(myConn,myStmt,null);

	}
	
	public static Student getStudent(String studentId) throws Exception {
//	public static void main (String[] args) throws Exception{

			Connection myConn = ConnectToDB();
			String sql = "select * from student where id = ? ";
//			String sql = "select * from student where id = 5 ";
			PreparedStatement myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, studentId);
//			myStmt.setString(1, "5");
			ResultSet myRs = myStmt.executeQuery();
//			if(myRs.next()) {
			myRs.next();
			int id = myRs.getInt("id");
			String firstName = myRs.getString("first_name");
			String lastName = myRs.getString("last_name");
			String email = myRs.getString("email");
//			System.out.println(firstName + " " + lastName + " " + email );
			Student student = new Student(id,firstName,lastName , email );
			close(myConn,myStmt,myRs);
			return student;
//			}
//			else {
//				throw new Exception("Could not find student with id: " + studentId);
//			}
			
	}

	public static void updateStudent(String studentId, String newFirstName, String newLastName, String newEmail) throws Exception {
//		public static void main (String[] args) throws Exception{

		Connection myConn = ConnectToDB();
		String sql = "update student set first_name = ? , last_name = ?, email=? where id = ?; ";
		PreparedStatement myStmt=myConn.prepareStatement(sql);
		myStmt.setString(1,newFirstName);
		myStmt.setString(2,newLastName);
		myStmt.setString(3,newEmail);
		myStmt.setString(4,studentId);
//		myStmt.setString(1,"haifa");
//		myStmt.setString(2,"chagwey");
//		myStmt.setString(3,"haifa.chagwey@isticbc.org");
//		myStmt.setString(4,"15");
		myStmt.execute();
		close(myConn,myStmt,null);

	
	}

	public static void deleteStudent(String studentId) throws Exception {
		Connection myConn = ConnectToDB();
		String sql = "delete from student where id = ?; ";
		PreparedStatement myStmt=myConn.prepareStatement(sql);
		myStmt.setString(1,studentId);
		myStmt.execute();
		close(myConn,myStmt,null);

	}
	

}
