package br.com.samuel.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "distancesBetweenCity") 
@XmlAccessorType (XmlAccessType.FIELD)
public class DistanceXML {
	private List<DistanceDTO> distances;

	public List<DistanceDTO> getDistances() {
		return distances;
	}

	public void setDistances(List<DistanceDTO> distances) {
		this.distances = distances;
	}
}
