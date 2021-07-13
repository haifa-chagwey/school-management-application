package P1;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * Servlet implementation class control
 */
@WebServlet("/StudentControllerServlet")
public class StudentControllerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentControllerServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// local variable
		String theCommand = request.getParameter("command");
		try {
			if (theCommand== null) {
				theCommand = "list";
			}
//			System.out.println("theCommand = " + theCommand);
			
			switch (theCommand) {
			
			case "list":
				listStudents(request,response);
				break;
			case "load":
				loadStudent(request,response);
				break;
			case "delete":
				deleteStudent(request,response);
				break;
				
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
						
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String theCommand = request.getParameter("command");
			switch (theCommand) {
							
			case "add":
				addStudent(request, response);
				break;
			case "update":
				updateStudent(request,response);
				break;
			}
				
		}
		catch (Exception exc) {
			throw new ServletException(exc);
		}
		
	}



	private void listStudents(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		List<Student> students = StudentDBUtil.getStudents();
		request.setAttribute("student_list", students);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/studentList.jsp");		
		dispatcher.forward(request, response);
		
	}
	
	private void addStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Student student = new Student(firstName,lastName,email);
		StudentDBUtil.addStudent(student);
		response.sendRedirect("/web_student_tracker_IW/");
	}
	
	private void loadStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		Student student = StudentDBUtil.getStudent(studentId);
		request.setAttribute("studentInf", student);
		RequestDispatcher dispatcher = 
		request.getRequestDispatcher("/updateStudent.jsp");		
		dispatcher.forward(request, response);
	}

	private void updateStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		String newFirstName = request.getParameter("firstName");
		String newLastName = request.getParameter("lastName");
		String newEmail = request.getParameter("email");
		StudentDBUtil.updateStudent(studentId,newFirstName,newLastName,newEmail);
		response.sendRedirect("/web_student_tracker_IW/");

	}

	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String studentId = request.getParameter("studentId");
		StudentDBUtil.deleteStudent(studentId);
//		listStudents(request,response);
		response.sendRedirect("/web_student_tracker_IW/");



	}
}
