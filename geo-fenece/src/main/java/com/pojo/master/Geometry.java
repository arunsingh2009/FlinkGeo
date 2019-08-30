package com.pojo.master;

import com.vividsolutions.jts.geom.Coordinate;

public class Geometry {

	private String[][][] coordinates;

	private String type;

	private String radius;
	
	private Coordinate[] vCoordinates;

	public String[][][] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String[][][] coordinates) {
		this.coordinates = coordinates;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRadius() {
		return radius;
	}

	public void setRadius(String radius) {
		this.radius = radius;
	}
	
	public  Coordinate[] getCoordinateList(){
		vCoordinates= new Coordinate[coordinates[0].length];
		for(int i=0;i<coordinates[0].length; i++){
			String[] latlog=coordinates[0][i];
			Double newLatitude = Double.parseDouble(latlog[0]);
			Double newLongitude = Double.parseDouble(latlog[1]);
			vCoordinates[i]= new Coordinate(newLatitude,newLongitude);
		}
		return vCoordinates;
		
	}

	@Override
	public String toString() {
		return "ClassPojo [coordinates = " + coordinates + ", type = " + type + ", radius = " + radius + "]";
	}
}
