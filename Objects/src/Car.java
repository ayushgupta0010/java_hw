public class Car {
    String make, model;
    int year;
    double MPG, milesDriven, fuelCapacity, fuelRemaining;

    public Car(String make, String model, int year, double MPG, double milesDriven, double fuelCapacity, double fuelRemaining) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.MPG = MPG;
        this.milesDriven = milesDriven;
        this.fuelCapacity = fuelCapacity;
        this.fuelRemaining = fuelRemaining;
    }

    public void fillTank(double g) {
        this.fuelRemaining = Math.min(fuelCapacity, fuelRemaining + g);
    }

    public void drive(double m) {
        this.milesDriven += m;
        this.fuelRemaining -= this.MPG / m;
    }

    public String toString() {
        return String.format("Car(make = '%s', model = '%s', year = %d, MPG = %.2f, milesDriven = %.2f, fuelCapacity = %.2f, fuelRemaining = %.2f)", this.make, this.model, this.year, this.MPG, this.milesDriven, this.fuelCapacity, this.fuelRemaining);
    }

    public double getFuelRemaining() {
        return this.fuelRemaining;
    }
}
