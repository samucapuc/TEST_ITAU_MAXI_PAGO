package br.com.samuel.service;

import java.util.ArrayList;
import java.util.List;

import br.com.samuel.dto.DistanceDTO;
import br.com.samuel.entity.City;
import br.com.samuel.enums.TypeMetrics;
import br.com.samuel.persistence.CityDao;

public class CityService {

	public void insertCities(List<City> cities) {
		cities.stream().forEach(c -> insertCity(c));
	}

	public void insertCity(City city) {
		CityDao dao = new CityDao();
		dao.insertCity(city);
	}

	public void deleteAllCities() {
		CityDao dao = new CityDao();
		dao.deleteAllCities();
	}

	public List<DistanceDTO> getDistance(TypeMetrics metrics) {
		CityDao dao = new CityDao();
		List<DistanceDTO> listDistance = new ArrayList<DistanceDTO>();
		List<City[]> combinations = generateCombinations(dao.findListCity(), 2);
		for (City[] citiesDuple : combinations) {
			listDistance.add(new DistanceDTO(citiesDuple[0], citiesDuple[1], metrics,
					distance(citiesDuple[0].getLatitude(), citiesDuple[0].getLongitude(), citiesDuple[1].getLatitude(),
							citiesDuple[1].getLongitude(), metrics)));
		}
		return listDistance;
	}

	public List<City[]> generateCombinations(List<City> cities, int r) {
		List<City[]> combinations = new ArrayList<>();
		combine(cities, combinations, new City[r], 0, cities.size() - 1, 0);
		return combinations;
	}

	private void combine(List<City> myData, List<City[]> combinations, City data[], int start, int end, int index) {
		if (index == data.length) {
			City[] combination = data.clone();
			combinations.add(combination);
		} else {
			int max = Math.min(end, end + 1 - data.length + index);
			for (int i = start; i <= max; i++) {
				data[index] = myData.get(i);
				combine(myData, combinations, data, i + 1, end, index + 1);
			}
		}
	}

	private double distance(double lat1, double lon1, double lat2, double lon2, TypeMetrics unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2))
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (TypeMetrics.KM.equals(unit)) {
			dist = dist * 1.609344;
		}
		return (dist);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts decimal degrees to radians : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	/* :: This function converts radians to decimal degrees : */
	/* ::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::: */
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}
