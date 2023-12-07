import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/DatabaseDeleteHTNL")
public class DatabaseDeleteHTNL extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DatabaseDeleteHTNL() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String key = request.getParameter("key");
		search(key, response);
	}

	void search(String key, HttpServletResponse response) throws IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String title = "Database Result";
		String docType = "<!doctype html public \"-//w3c//dtd html 4.0 " + //
				"transitional//en\">\n"; //
		out.println(docType + //
				"<html>\n" + //
				"<head><title>" + title + "</title></head>\n" + //
				"<body bgcolor=\"#f0f0f0\">\n" + //
				"<h1 align=\"center\">" + title + "</h1>\n");

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			DBConnectionHTNL.getDBConnection();
			connection = DBConnectionHTNL.connection;

			String deleteSQL = "DELETE FROM htnlTable WHERE NUID=?";
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, key);
		//}
		preparedStatement.executeUpdate();

		out.println("Deleted Student Number " + key + "<br>");
		out.println("<a href=/htnl/DatabaseDeleteHTNL.html>Delete Database</a> <br>");
		out.println("</body></html>");
		preparedStatement.close();
		connection.close();
	} catch (SQLException se) {
		se.printStackTrace();
	} catch (Exception e) {
		e.printStackTrace();
	} finally {
		try {
			if (preparedStatement != null)
				preparedStatement.close();
		} catch (SQLException se2) {
		}
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException se) {
			se.printStackTrace();
		}
	}
}

protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	doGet(request, response);
}

}
