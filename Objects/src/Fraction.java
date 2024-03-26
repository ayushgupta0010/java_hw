public class Fraction {
    int n, d;
    
    public Fraction(int n, int d) {
        this.n = n;
        this.d = d;
        reduce(this);
    }

    public int getNum() {
        return this.n;
    }

    public int getDenom() {
        return this.d;
    }

    public void setNum(int n) {
        this.n = n;
        reduce(this);
    }

    public void setDenom(int d) {
        this.d = d;
        reduce(this);
    }

    public Fraction add(Fraction fr) {
        int n = this.n * fr.d + this.d * fr.n ;
        int d = this.d * fr.d;
        return new Fraction(n, d);
    }

    public boolean equals(Fraction fr) {
        reduce(fr);
        return this.n == fr.n && this.d == fr.d;
    }

    public String toString() {
        return this.n + "/" + this.d;
    }

    private int getGcd(int a, int b) {
        return b == 0 ? a : getGcd(b, a % b);
    }

    private void reduce(Fraction fr) {
        int gcd = getGcd(fr.n, fr.d);
        fr.n /= gcd;
        fr.d /= gcd;
    }
}
