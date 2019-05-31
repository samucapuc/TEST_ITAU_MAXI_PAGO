package br.com.samuel.enums;

public enum TypeMetrics {

	MI("Miles"), KM("kilometers");

	private String label;

	TypeMetrics(String label) {
		this.setLabel(label);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
