/**
 * 
 */
package com.dunn.restful.data.dao;

import java.util.LinkedHashMap;
import java.util.Map;

import com.dunn.restful.data.model.StaffInfo;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * @author joshuadunn
 *
 */
	public enum StaffDAO {
		instance;

		private static Map<String, StaffInfo> addStaffData;

		//Queries the Datastore for Records
		public static Map<String, StaffInfo> queryGoogleDatastore(){
			DatastoreService dataStoreService = DatastoreServiceFactory.getDatastoreService();
			addStaffData = new LinkedHashMap<String, StaffInfo>();
			Query query = new Query("Staff").addSort("Forename", Query.SortDirection.ASCENDING);
			PreparedQuery prep = dataStoreService.prepare(query);
			for (Entity entity : prep.asIterable()) {
				String idKey = entity.getKey().toString();
				String id = idKey.substring(idKey.indexOf("(") + 1,idKey.indexOf(")"));
				String forename = entity.getProperty("Forename").toString();
				String surname = entity.getProperty("Surname").toString();
				String email = entity.getProperty("Email").toString();
				String address = entity.getProperty("Address").toString();
				String phone_num = entity.getProperty("Phone_Number").toString();
				addStaffData.put(id, new StaffInfo(id, forename, surname, address, email, phone_num));
			}
			return addStaffData;
		}


		//Returns the Staff Model
		public static Map<String, StaffInfo> getStaffModel() {
			addStaffData = queryGoogleDatastore( );
			return addStaffData;
		}

	}