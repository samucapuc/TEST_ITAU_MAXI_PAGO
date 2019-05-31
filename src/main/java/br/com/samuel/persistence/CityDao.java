package br.com.samuel.persistence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import br.com.samuel.entity.City;

public class CityDao extends GenericDao<City> {
	public City findCityById(Long id) {
		return super.find(new StringBuilder("SELECT C.ID, C.NAME, C.LATITUDE, C.LONGITUDE FROM CITY C WHERE C.ID=?"), new Object[] {id});
	}
	public List<City> findListCity() {
		return super.findList(new StringBuilder("SELECT C.ID, C.NAME, C.LATITUDE, C.LONGITUDE FROM CITY C"), null);
	}
	
	public List<City> findListCityByName(String name) {
		return super.findList(new StringBuilder("SELECT C.ID, C.NAME, C.LATITUDE, C.LONGITUDE FROM CITY C WHERE C.NAME=?"), new Object[] {name});
	}
	
	public void insertCity(City city) {
		super.insert(new StringBuilder("INSERT INTO CITY(ID,NAME,LATITUDE,LONGITUDE) VALUES(?,?,?,?)"), new Object[] {city.getId(),city.getName(),city.getLatitude(),city.getLongitude()});
	}
	
	public void updateCity(City city){
		super.update(new StringBuilder("UPDATE CITY SET NAME=?,LATITUDE=?,LONGITUDE=? WHERE ID=?"), new Object[] {city.getName(),city.getLatitude(),city.getLongitude(),city.getId()});
	}
	
	public void deleteCity(Long id) {
		super.delete(new StringBuilder("DELETE FROM CITY WHERE ID=?"), new Object[] {id});
	}
	
	public void deleteAllCities() {
		super.executeSql(new StringBuilder("DELETE FROM CITY"));		
	}

	@Override
	protected City afterFind(ResultSet rs,City city) throws SQLException {
			city.setId(rs.getLong("ID"));
			city.setName(rs.getString("NAME"));
			city.setLatitude(rs.getDouble("LATITUDE"));
			city.setLongitude(rs.getDouble("LONGITUDE"));
		return city;
	}

	@Override
	protected String getNameEntity() {
		return "City";
	}
	@Override
	protected City getInstance() {
		return new City();
	}

}
