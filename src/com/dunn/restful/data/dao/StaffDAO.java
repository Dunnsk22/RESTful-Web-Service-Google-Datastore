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
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

/**
 * @author joshuadunn
 *
 */
	public enum StaffDAO {
		instance;

		private static Map<String, StaffInfo> addStaffData;
		static DatastoreService dataStoreService = DatastoreServiceFactory.getDatastoreService();


		/** 
		 * 
		 * @return addStaffData - Returns a map of data from the GAE Datastore
		 *
		 */		public Map<String, StaffInfo> queryGoogleDatastore(){
			addStaffData = new LinkedHashMap<String, StaffInfo>();
			Query query = new Query("StaffDetails");
			PreparedQuery googleQuery = dataStoreService.prepare(query);
			
			if (googleQuery.countEntities(FetchOptions.Builder.withDefaults()) < 1) {
				
				addNewDatastoreEntry();
			}
			
			for (Entity entity : googleQuery.asIterable()) {
				
				String idKey = entity.getKey().toString();
				String id = idKey.substring(idKey.indexOf("(") + 1,idKey.indexOf(")"));
				String forename = entity.getProperty("Forename").toString();
				String surname = entity.getProperty("Surname").toString();
				String email = entity.getProperty("Email").toString();
				String address = entity.getProperty("Address").toString();
				String phone_num = entity.getProperty("Phone_Num").toString();
				addStaffData.put(id, new StaffInfo(id, forename, surname, address, email, phone_num));
			}
			
			return addStaffData;
		}


		/** 
		 * 
		 * Adds Two New Entities to the GAE Datastore for testing locally
		 *
		 */
		public static void addNewDatastoreEntry() {
			
			System.out.println("IN the datastoreEntry Method");
			Entity staffMember = new Entity("StaffDetails");
			staffMember.setProperty("Forename", "Johnathon");
			staffMember.setProperty("Surname", "Robson");
			staffMember.setProperty("Email", "robby_john@me.com");
			staffMember.setProperty("Phone_Num", "01887 223112");
			staffMember.setProperty("Address", "22 Hataway Close, Biddulph, Stoke on Trent");

			Entity staffMemberTwo = new Entity("StaffDetails");
			staffMemberTwo.setProperty("Forename", "Kai");
			staffMemberTwo.setProperty("Surname", "Salt");
			staffMemberTwo.setProperty("Email", "salty_pig@me.com");
			staffMemberTwo.setProperty("Phone_Num", "01877 223112");
			staffMemberTwo.setProperty("Address", "2211 Runaway Drive, Congleton, Stoke on Trent");

			// Add the data to the GAE Datastore
			dataStoreService.put(staffMember);
			dataStoreService.put(staffMemberTwo);
			
		}
	}