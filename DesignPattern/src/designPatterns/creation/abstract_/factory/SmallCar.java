package designPatterns.creation.abstract_.factory;

import designPatterns.creational.factory.CarType;

public class SmallCar extends Car {

	public SmallCar(Location location) {
		super(CarType.SMALL, location);
		construct();
	}
	

	@Override
	protected void construct() {
		System.out.println("Building Small car");

	}

	
}
