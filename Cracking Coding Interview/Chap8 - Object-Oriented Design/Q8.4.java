import java.util.*;

enum Type {
	Small, Large
}

class Lot {
	Type type;
	boolean available;
	Car currentCar;

	public Lot() {
		available = true;
	}

	public void moveInCar(Car car) {
		currentCar = car;
		available = false;
	}

	public Car moveOutCar() {
		available = true;
		Car temp = currentCar;
		currentCar = null;
		return temp;
	}

	public boolean isAvailable() {
		return available;
	}
}

class LargeLot extends Lot {
	public LargeLot() {
		super();
		type = Type.Large;
	}
}

class SmallLot extends Lot {
	public SmallLot() {
		super();
		type = Type.Small;
	}
}

class Car {
	Type type;
	String owner;

	public Car(Type type, String owner) {
		this.type = type;
		this.owner = owner;
	}
}

class ParkingLot {
	LinkedList<LargeLot> largeLotList;
	LinkedList<SmallLot> smallLotList;
	LinkedList<Car> waitList;

	public ParkingLot() {
		largeLotList = new LinkedList<LargeLot>();
		smallLotList = new LinkedList<SmallLot>();
		waitList = new LinkedList<Car>();
	}

	public LargeLot findAvaLargeLot() {
		ListIterator<LargeLot> iterator = largeLotList.listIterator();
		while(iterator.hasNext()) {
			LargeLot lot = iterator.next();
			if(lot.isAvailable())
				return lot;
		}
		return null;
	}

	public SmallLot findAvaSmallLot() {
		ListIterator<SmallLot> iterator = smallLotList.listIterator();
		while(iterator.hasNext()) {
			SmallLot lot = iterator.next();
			if(lot.isAvailable())
				return lot;
		}
		return null;
	}

	public void checkIn(Car car) {
		if(car.type == Type.Large) {
			LargeLot lot = findAvaLargeLot();
			if(lot == null) {
				waitList.add(car);
				return;
			}
			lot.moveInCar(car);
		}
		else {
			SmallLot lot = findAvaSmallLot();
			if(lot == null) {
				waitList.add(car);
				return;
			}
			lot.moveInCar(car);
		}
	}

	public void checkOut(Car car) {
		//...
	}
}

class Q8_4App {
	public static void main(String[] args) {

	}
}



