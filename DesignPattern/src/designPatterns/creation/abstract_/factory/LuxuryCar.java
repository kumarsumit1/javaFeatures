package designPatterns.creation.abstract_.factory;

import designPatterns.creational.factory.CarType;

public class LuxuryCar extends Car
{
	public LuxuryCar(Location location)
	{
		super(CarType.LUXURY, location);
		construct();
	}

	@Override
	protected void construct() {
		System.out.println("Building luxury car");
		//add accessories
	}
}

