package model;

import java.io.Serializable;

public class Classroom implements Serializable{
	private String roomNumber;
	private BuildingName buildingName;
	

	public Classroom(String roomNumber, BuildingName buildingName) {
		super();
		this.buildingName = buildingName;
		this.roomNumber = roomNumber;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public BuildingName getRoomBuilding() {
		return buildingName;
	}

	public void setRoomBuilding(BuildingName buildingName) {
		this.buildingName = buildingName;
	}

	@Override
	public String toString() {
		return "Classroom [roomNumber=" + roomNumber + ", roomBuilding=" + buildingName + "]";
	}

}
