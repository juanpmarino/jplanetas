package com.jpmarino.jplanetas.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions;
import com.jpmarino.jplanetas.data.ClimaJob;

/**
* Servlet que espera llamado del job para generar la base con 10 años de registros climáticos.
* 
* @author Juan Pablo Marino
* @version 1.0
*/
public class ClimaServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(ClimaJob.class);

	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		if (req.getParameter("execute_job") != null) {
			logger.info("Encolando tarea para generar 10 años...");
			
			// Add the task to the default queue.
	        Queue queue = QueueFactory.getDefaultQueue();
	        queue.add(TaskOptions.Builder.withUrl("/job"));
			
			logger.info("Tarea encolada.");
			resp.setContentType("text/plain");
			resp.getWriter().println("Tarea encolada OK.");
		} else {
			resp.setContentType("text/plain");
			resp.getWriter().println("...");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
			logger.info("A punto de generar 10 años...");
			ClimaJob.generar10años();
			logger.info("10 años de registros climáticos generados.");
			resp.setContentType("text/plain");
			resp.getWriter().println("10 años generados OK.");
	}

}
