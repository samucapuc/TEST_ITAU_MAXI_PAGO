package br.com.samuel.dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import br.com.samuel.entity.City;
import br.com.samuel.enums.TypeMetrics;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "distances") 
public class DistanceDTO {

	@XmlElement(name = "citySource") 
	private City citySource;
	@XmlElement(name = "cityTarget") 
	private City cityTarget;
	@XmlElement(name = "metrics")
	private TypeMetrics metrics;
	@XmlElement(name = "distance")
	private Double distance;
	
	public DistanceDTO() {}
	
	public DistanceDTO(City citySource, City cityTarget, TypeMetrics metrics, Double distance) {
		super();
		this.citySource = citySource;
		this.cityTarget = cityTarget;
		this.metrics = metrics;
		this.distance = (distance !=null ? (((BigDecimal)new BigDecimal(distance).setScale(3, RoundingMode.HALF_UP)).doubleValue()) : null);
	}
	
	public City getCitySource() {
		return citySource;
	}
	public void setCitySource(City citySource) {
		this.citySource = citySource;
	}
	public City getCityTarget() {
		return cityTarget;
	}
	public void setCityTarget(City cityTarget) {
		this.cityTarget = cityTarget;
	}
	public TypeMetrics getMetrics() {
		return metrics;
	}
	public void setMetrics(TypeMetrics metrics) {
		this.metrics = metrics;
	}

	public Double getDistance() {
		return distance;
	}
	public void setDistance(Double distance) {
		this.distance = distance;
	}
	
}
