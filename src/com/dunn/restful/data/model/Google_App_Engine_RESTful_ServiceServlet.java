package com.dunn.restful.data.model;

import java.io.IOException;

import javax.servlet.http.*;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;

@SuppressWarnings("serial")
public class Google_App_Engine_RESTful_ServiceServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
				
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}
}
