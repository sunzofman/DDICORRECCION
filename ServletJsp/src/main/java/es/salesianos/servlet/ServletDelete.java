package servlet;

import java.io.IOException;



import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import service.*;

public class ServletBorrar extends HttpServlet {
	private Service servicio = new Service();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String language = req.getParameter("language");
		service.DeleteLanguage(language);
		redirect(resp);
	}

	private void redirect(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("index.jsp");
	}
}
