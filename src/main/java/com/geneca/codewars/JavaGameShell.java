package com.geneca.codewars;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.geneca.codewars.commands.MyAbstractGameCommand;
import com.geneca.codewars.data.GameCommandObject;
import com.geneca.codewars.data.GameStatusObject;

/**
 * Servlet to receive request to "take a turn" in the game
 */
@WebServlet(name = "JavaGameShell", urlPatterns = { "/javaGameShell" })
public class JavaGameShell extends HttpServlet {
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// prepare response
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		// get input
		String gameStatusJsonIn = request.getParameter("jsonInput");
		System.out.println("Input = " + gameStatusJsonIn);

		// call command to "take a turn"
		String gameCommandJsonResult = takeTurn(gameStatusJsonIn);
		System.out.println("Response = " + gameCommandJsonResult);

		// write response
		out.print(gameCommandJsonResult);

		// close up everything,
		out.flush();
		out.close();
	}

	/**
	 * Call MyGameCommand to take a turn, specifying the input converted from
	 * JSON to GameStatusObject
	 * 
	 * @param jsonIn
	 * @return
	 */
	public String takeTurn(String jsonIn) {
		GameStatusObject gameStatus = JsonConverter.convertFromJson(jsonIn);

		try {
			Class<?> cmdclass = Class
					.forName("com.geneca.codewars.commands.MyGameCommand");
			MyAbstractGameCommand cmd = (MyAbstractGameCommand) cmdclass
					.newInstance();
			GameCommandObject gameCommand = cmd.takeTurn(gameStatus);
			return JsonConverter.convertToJson(gameCommand);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} // or similar

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException {
		try {
			doPost(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) throws Exception {
		Server server = new Server(Integer.valueOf(System.getenv("PORT")));
		ServletContextHandler context = new ServletContextHandler(
				ServletContextHandler.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);
		context.addServlet(new ServletHolder(new JavaGameShell()), "/*");
		server.start();
		server.join();
	}

}
