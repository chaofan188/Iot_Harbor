package com.cetc.iot.database.dao;

import java.util.List;
import com.cetc.iot.database.model.VeGeoLocation;

public interface VeGeoLocationDao {

	List<VeGeoLocation> query(VeGeoLocation veGeoLocation, int page,
			int size);

	String delete(VeGeoLocation veGeoLocation);

	String add(VeGeoLocation veGeoLocation);

	String update(VeGeoLocation veGeoLocation);



}
