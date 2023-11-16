import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DatabaseInsertHTNL")
public class DatabaseInsertHTNL extends HttpServlet {
   private static final long serialVersionUID = 1L;

   public DatabaseInsertHTNL() {
      super();
   }

   protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      String firstName = request.getParameter("firstName");
      String lastName = request.getParameter("lastName");
      String gender = request.getParameter("gender");
      String phone = request.getParameter("phone");
      String email = request.getParameter("email");
      String major = request.getParameter("major");
      String graduation = request.getParameter("graduation");
      String hobbies = request.getParameter("hobbies");

      Connection connection = null;
      String insertSql = " INSERT INTO htnlTable (id, FIRST_NAME, LAST_NAME, GENDER, PHONE, EMAIL, MAJOR, GRADUATING_YEAR, HOBBIES) values (default, ?, ?, ?, ?, ?, ?, ?, ?)";

      try {
         DBConnectionHTNL.getDBConnection();
         connection = DBConnectionHTNL.connection;
         PreparedStatement preparedStmt = connection.prepareStatement(insertSql);
         preparedStmt.setString(1, firstName);
         preparedStmt.setString(2, lastName);
         preparedStmt.setString(3, gender);
         preparedStmt.setString(4, phone);
         preparedStmt.setString(5, email);
         preparedStmt.setString(6, major);
         preparedStmt.setString(7, graduation);
         preparedStmt.setString(8, hobbies);
         preparedStmt.execute();
         connection.close();
      } catch (Exception e) {
         e.printStackTrace();
      }

      // Set response content type
      response.setContentType("text/html");
      PrintWriter out = response.getWriter();
      String title = "Insert Data to DB table";
      String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + "transitional//en\">\n";
      out.println(docType + //
            "<html>\n" + //
            "<head><title>" + title + "</title></head>\n" + //
            "<body bgcolor=\"#f0f0f0\">\n" + //
            "<h2 align=\"center\">" + title + "</h2>\n" + //
            "<ul>\n" + //
            "  <li><b>First Name</b>: " + firstName + "\n" + //
            "  <li><b>Last Name</b>: " + lastName + "\n" + //
            "  <li><b>Gender</b>: " + gender + "\n" + //
            "  <li><b>Email</b>: " + phone + "\n" + //
            "  <li><b>Phone</b>: " + email + "\n" + //
            "  <li><b>Major</b>: " + major + "\n" + //
            "  <li><b>Graduation Year</b>: " + graduation + "\n" + //
            "  <li><b>Hobbies</b>: " + hobbies + "\n" + //

            "</ul>\n");

      out.println("<a href=/htnl/DatabaseSearchHTNL.html>Search Database</a> <br>");
      out.println("</body></html>");
   }

   protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request, response);
   }

}
