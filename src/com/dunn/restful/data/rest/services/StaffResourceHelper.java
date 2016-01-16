/**
 * 
 */
package com.dunn.restful.data.rest.services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import com.dunn.restful.data.dao.StaffDAO;
import com.dunn.restful.data.model.StaffInfo;

/**
 * @author joshuadunn
 *
 */
public class StaffResourceHelper {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;

	public StaffResourceHelper(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}

	// Application integration
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public StaffInfo getStaffInfo() {
		StaffInfo StaffInfo = StaffDAO.getStaffModel().get(id);
		if (StaffInfo == null)
			throw new RuntimeException("Get: Staff with " + id + " not found");
		return StaffInfo;
	}

	// For the browser
	@GET
	@Produces(MediaType.TEXT_XML)
	public StaffInfo getStaffInfoHTML() {
		StaffInfo StaffInfo = StaffDAO.getStaffModel().get(id);
		if (StaffInfo == null)
			throw new RuntimeException("Get: Staff with " + id + " not found");
		return StaffInfo;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putStaff(JAXBElement<StaffInfo> StaffInfo) {
		StaffInfo StaffInfoMember = StaffInfo.getValue();
		return putAndGetResponse(StaffInfoMember);
	}

	@DELETE
	public void deleteStaffInfo() {
		StaffInfo StaffInfo = StaffDAO.getStaffModel().remove(id);
		if (StaffInfo == null)
			throw new RuntimeException("Delete: Staff with " + id + " not found");
	}

	private Response putAndGetResponse(StaffInfo StaffInfo) {
		Response res;
		if (StaffDAO.getStaffModel().containsKey(
				StaffInfo.getStaffID())) {
			res = Response.noContent().build();
		} else {
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		StaffDAO.getStaffModel()
				.put(StaffInfo.getStaffID(), StaffInfo);
		return res;
	}
}
