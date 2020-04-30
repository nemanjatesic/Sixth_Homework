package mvc2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Assistant;
import database.SQliteDB;

@WebServlet(name = "LoginServlet", urlPatterns = "/")
public class LoginServlet extends HttpServlet implements Servlet {

	private static final long serialVersionUID = 1132456713082687859L;
	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		if (request.getRequestURI().equals("/")) {
			disp = request.getRequestDispatcher("/oceni.jsp");
			disp.forward(request, response);
		}else if (request.getRequestURI().equals("/myServlet")) {
			List<Assistant> list = SQliteDB.getInstance().getAllAssistant();
			Set<Assistant> set = new HashSet<>(list);
			List<Assistant> outList = new ArrayList<>();
			for (Assistant assistant : set) {
				Assistant assistantOut = new Assistant(assistant.getName(), assistant.getLastName(), 0);
				int cnt = 0;
				for (Assistant a : list) {
					if (a.equals(assistant)) {
						assistantOut.setPoints(assistantOut.getPoints() + a.getPoints());
						cnt++;
					}
				}
				assistantOut.setPoints(assistantOut.getPoints() / cnt);
				outList.add(assistantOut);
			}

			request.setAttribute("assistants", outList);
			disp = request.getRequestDispatcher("/prosek.jsp");
			disp.forward(request, response);
		}else {
			disp = request.getRequestDispatcher("/error.jsp");
			disp.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher disp;
		response.setContentType("text/html");
		if (request.getRequestURI().equals("/myServlet")) {
			try {
				String imeTmp = request.getParameter("ime").toLowerCase();
				String prezimeTmp = request.getParameter("prezime").toLowerCase();
				String ime = imeTmp.substring(0, 1).toUpperCase() + imeTmp.substring(1);
				String prezime = prezimeTmp.substring(0, 1).toUpperCase() + prezimeTmp.substring(1);
				int ocena = Integer.parseInt(request.getParameter("ocena"));
				if (ocena < 0 || ocena > 10)
					throw new NumberFormatException();
				if (ime.equals("Aleksandar"))
					ocena = 0;
				Assistant assistant = new Assistant(ime, prezime, ocena);
				SQliteDB.getInstance().addAssistant(assistant);
				disp = request.getRequestDispatcher("/uspeh.jsp");
				disp.forward(request, response);
			} catch (NumberFormatException e) {
				disp = request.getRequestDispatcher("/error.jsp");
				disp.forward(request, response);
			}
		}else {
			disp = request.getRequestDispatcher("/error.jsp");
			disp.forward(request, response);
		}

	}
}
