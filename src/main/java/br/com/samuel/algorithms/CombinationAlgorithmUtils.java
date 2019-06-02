package br.com.samuel.algorithms;

import java.util.ArrayList;
import java.util.List;

import br.com.samuel.entity.City;

public class CombinationAlgorithmUtils {
	public static List<City[]> generateCombinations(List<City> cities, int r) {
		List<City[]> combinations = new ArrayList<>();
		combine(cities, combinations, new City[r], 0, cities.size() - 1, 0);
		return combinations;
	}

	private static void combine(List<City> myData, List<City[]> combinations, City data[], int start, int end,
			int index) {
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
}
