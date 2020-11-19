import java.util.Arrays;

public class DensePolynomial implements Polynomial {

    private static final int MAX_SIZE = 100;
    int[] polynomial = new int[MAX_SIZE];

    public DensePolynomial() {}

    public DensePolynomial(int[] p) throws ArrayIndexOutOfBoundsException {
        if (p.length > MAX_SIZE) throw new ArrayIndexOutOfBoundsException();
        this.polynomial = p;
    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the largest exponent with a non-zero coefficient.  If all terms have zero exponents, it returns 0.
     */
    @Override
    public int degree() {
        int i = MAX_SIZE - 1;
        for (; i > 0; --i) {
            if (polynomial[i] != 0)  break;
        }
        return i;
    }

    /**
     * Returns the coefficient corresponding to the given exponent.  Returns 0 if there is no term with that exponent
     * in the polynomial.
     *
     * @param d the exponent whose coefficient is returned.
     * @return the coefficient of the term of whose exponent is d.
     */
    @Override
    public int getCoefficient(int d) throws IndexOutOfBoundsException {
        //if (d > MAX_SIZE - 1 || d < 0) throw new IndexOutOfBoundsException();
        return polynomial[d];
    }

    /**
     * @return true if the polynomial represents the zero constant
     */
    @Override
    public boolean isZero() {
        return (Arrays.stream(polynomial).sum() == 0);
    }

    /**
     * Returns a polynomial by adding the parameter to the current instance. Neither the current instance nor the
     * parameter are modified.
     *
     * @param q the non-null polynomial to add to <code>this</code>
     * @return <code>this + </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial add(Polynomial q) throws NullPointerException {
        int[] new_polynomial = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; ++i) {
            new_polynomial[i] = this.polynomial[i] + q.polynomial[i];
        }
        return new DensePolynomial(new_polynomial);
    }

    /**
     * Returns a polynomial by multiplying the parameter with the current instance.  Neither the current instance nor
     * the parameter are modified.
     *
     * @param q the polynomial to multiply with <code>this</code>
     * @return <code>this * </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial multiply(Polynomial q) throws NullPointerException {
        int[] new_polynomial = new int[MAX_SIZE*MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; ++i) {
            for (int j = 0; j < MAX_SIZE; ++j) {
                new_polynomial[i * j] += this.polynomial[i] * q.polynomial[j];
            }
        }
        try {
            return new DensePolynomial(new_polynomial);
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        } //TODO
    }

    /**
     * Returns a  polynomial by subtracting the parameter from the current instance. Neither the current instance nor
     * the parameter are modified.
     *
     * @param q the non-null polynomial to subtract from <code>this</code>
     * @return <code>this - </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial subtract(Polynomial q) throws NullPointerException {
        int[] new_polynomial = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; ++i) {
            new_polynomial[i] = this.polynomial[i] - q.polynomial[i];
        }
        return new DensePolynomial(new_polynomial);
    }

    /**
     * Returns a polynomial by negating the current instance. The current instance is not modified.
     *
     * @return -this
     */
    @Override
    public Polynomial minus() {
        int[] new_polynomial = new int[MAX_SIZE];
        for (int i = 0; i < MAX_SIZE; ++i) {
            new_polynomial[i] = -this.polynomial[i];
        }
        return new DensePolynomial(new_polynomial);
    }

    /**
     * Checks if the class invariant holds for the current instance.
     *
     * @return {@literal true} if the class invariant holds, and {@literal false} otherwise.
     */
    @Override
    public boolean wellFormed() {
        //TODO
        return false;
    }
}
