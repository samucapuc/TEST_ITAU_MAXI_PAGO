package br.com.samuel.service;

import java.util.ArrayList;
import java.util.List;

import br.com.samuel.algorithms.CombinationAlgorithmUtils;
import br.com.samuel.algorithms.DistanceAlgorithmsUtils;
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
		List<City[]> combinations = CombinationAlgorithmUtils.generateCombinations(dao.findListCity(), 2);
		for (City[] citiesDuple : combinations) {
			listDistance.add(new DistanceDTO(citiesDuple[0], citiesDuple[1], metrics,
					DistanceAlgorithmsUtils.distanceVicenty(citiesDuple[0].getLatitude(), citiesDuple[0].getLongitude(),
							citiesDuple[1].getLatitude(), citiesDuple[1].getLongitude(), metrics)));
		}
		return listDistance;
	}

}
