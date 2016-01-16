package com.dunn.restful.data.rest.services;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;

import com.dunn.restful.data.dao.StaffDAO;
import com.dunn.restful.data.model.StaffInfo;
	
	@Path("/text/staff")
	public class StaffTextResource {
		@Context
		UriInfo uriInfo;
		@Context
		Request request;
		
		
		@GET
		@Produces(MediaType.TEXT_PLAIN)
		public String getStaffInfoBrowserText() {
			List<StaffInfo> staff = new ArrayList<StaffInfo>();
			staff.addAll(StaffDAO.instance.getStaffModel().values());
			return staff.toString();
		}

}
