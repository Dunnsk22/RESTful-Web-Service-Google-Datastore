package com.dunn.restful.data.rest.services;

import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import com.dunn.restful.data.dao.StaffDAO;
import com.dunn.restful.data.model.StaffInfo;

	// Will map the resource to the URL xml/staff
	@Path("/xml/staff")
	public class StaffXmlResource {

		@Context
		UriInfo uriInfo;
		@Context
		Request request;

		// Return the list of Staff in the browser
		@GET
		@Produces(MediaType.TEXT_XML)
		public List<StaffInfo> getStaffInfoBrowser() {
			List<StaffInfo> staff = new ArrayList<StaffInfo>();
			staff.addAll(StaffDAO.instance.queryGoogleDatastore().values());
			return staff;
		}

		// returns the number of staff in the Rest Service
		@GET
		@Path("numOfStaff")
		@Produces(MediaType.TEXT_PLAIN)
		public String getCount() {
			int count = StaffDAO.instance.queryGoogleDatastore().size();
			return String.valueOf(count);
		}

		// Allows users to search for Staff Members by their Id Number
		@Path("{staff}")
		public StaffResourceHelper getStaffInfo(
				@PathParam("staff") String staff_id) {
			return new StaffResourceHelper(uriInfo, request, staff_id);
		}

	}
