import java.util.Random;

public class CarMain {
    public static void main(String[] args) {
        Car cars[] = new Car[5];
        cars[0] = new Car("Ferrari", "F8", 2022, 25, 10000, 40, 32);
        cars[1] = new Car("Ford", "Pinto", 1972, 17.5, 132480, 12, 8);
        cars[2] = new Car("Lamborghini", "Urus", 2023, 30, 112166, 50, 22);
        cars[3] = new Car("McLaren", "GT", 2021, 35, 23278, 45, 42);
        cars[4] = new Car("Porsche", "718", 2024, 45, 52365, 50, 50);

        for(Car car : cars) {
            System.out.println("*".repeat(10).concat(" ") + car.make.concat(" ") + "*".repeat(10));
            System.out.println("Car info before driving: " + car);

            int miles = new Random().nextInt(100);
            System.out.printf("Driving for %d miles\n", miles);
            car.drive(miles);

            System.out.printf("\nFuel remaining: %.2f\n\n", car.getFuelRemaining());

            int fuel = new Random().nextInt(10); 
            System.out.printf("Filling %d gallon fuel\n", fuel);
            car.fillTank(fuel);

            System.out.printf("\nFuel remaining: %.2f\n\n", car.getFuelRemaining());

            System.out.println("Car info after driving: " + car + '\n');
        }
    }    
}
