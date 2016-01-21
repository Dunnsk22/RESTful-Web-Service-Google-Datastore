/**
 * 
 */
package com.dunn.restful.data.utilities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.dunn.restful.data.dao.StaffDAO;
import com.dunn.restful.data.model.StaffInfo;

/**
 * @author joshuadunn
 *
 */
public class StaffUtils {
		private static Map<String, StaffInfo> staffMapData;

		public static Map<String, StaffInfo> getSampleStaff() {
			staffMapData = StaffDAO.instance.queryGoogleDatastore();
			return (staffMapData);
		}

		public static List<StaffInfo> findAllStaff() {
			Map<String, StaffInfo> staffInfoMap = getSampleStaff();
			ArrayList<StaffInfo> staffList = new ArrayList<StaffInfo>();
			for (StaffInfo info : staffInfoMap.values()) {
				staffList.add(info);
			}
			return staffList;
		}

		public static List<StaffInfo> findAllStaffByName(String name) {

			Map<String, StaffInfo> staffInfoMap = getSampleStaff();
			ArrayList<StaffInfo> staffList = new ArrayList<StaffInfo>();
			for (StaffInfo c : staffInfoMap.values()) {
				staffList.add(c);
			}
			return staffList;
		}

		public static StaffInfo getStaff(String id) {
			if (id == null) {
				id = "unknown";
			}
			return (staffMapData.get(id.toLowerCase()));
		}

		public static List<StaffInfo> getStaffMemberByName(String firstname, String lastname) {
			Map<String, StaffInfo> staffInfoMap = getSampleStaff();
			ArrayList<StaffInfo> staffList = new ArrayList<StaffInfo>();
			for (StaffInfo info : staffInfoMap.values()) {
				if (info.getForename().equalsIgnoreCase(firstname)
						&& (info.getSurname().equalsIgnoreCase(lastname))) {
					staffList.add(info);
				}
			}
			return staffList;
		}

		public static StaffInfo getNamedCustomerByFirstOrSecondName(
				String firstName, String lastName) {
			Collection<StaffInfo> staff = getSampleCustomers().values();
			for (StaffInfo staffObject : staff) {
				if ((staffObject.getForename().equalsIgnoreCase(firstName))
						&& (staffObject.getSurname().equalsIgnoreCase(lastName))) {
					return staffObject;
				}
			}
			return (null);
		}

		public static Map<String, StaffInfo> getSampleCustomers() {
			return staffMapData;
		}
	}