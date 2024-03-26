import java.util.Random;

public class FractionMain {
    public static void main(String[] args) {
        Fraction fr1 = new Fraction(getRand(), getRand());
        Fraction fr2 = new Fraction(getRand(), getRand());

        System.out.println("\nFraction 1: " + fr1);
        System.out.println("Fraction 2: " + fr2 + "\n");

        System.out.println("Adding both fractions: " + fr1.add(fr2));

        System.out.println("\nAre the fractions equal? " + fr1.equals(fr2));

        int d = getRand();
        System.out.println("\nChanging the denominator of both fraction to " + d + "\n");
        fr1.setDenom(d);
        fr2.setDenom(d);

        System.out.println("Fraction 1: " + fr1);
        System.out.println("Fraction 2: " + fr2 + "\n");

        System.out.println("Adding both fractions: " + fr1.add(fr2));
        System.out.println("\nAre the fractions equal? " + fr1.equals(fr2));
    }    

    private static int getRand() {
        return new Random().nextInt(1000);
    }
}
