import java.util.*;

public class DensePolynomial implements Polynomial {

    public static final int MAX_SIZE = 100;    //set to highest valid exponent
    private int[] polynomial = new int[MAX_SIZE];

    /**
     * Constructor for creating DensePolynomials from int arrays
     *
     * @param p int array to be set to this.polynomial
     */
    private DensePolynomial(int[] p) {
        this.polynomial = Arrays.copyOfRange(p, 0, MAX_SIZE);
    }


    /**
     * Constructor for creating DensePolynomials from canonical string representations of polynomials
     *
     * @param s canonical string representation of a polynomial
     * @throws ArrayIndexOutOfBoundsException for polynomials containing exponents greater than MAX_SIZE
     */
    public DensePolynomial(String s) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        if (s.length() == 0) return;
        String[] expressions = s.split("\\s(?=[+-]\\s)");
        for (String i: expressions) {
            int sign = 1;
            if (i.charAt(0) == '-') {
                sign = -1;
                i = i.substring(1);
            } else if (i.charAt(0) == '+') {
                i = i.substring(1);
            }
            if (i.charAt(0) == ' ') {
                i = i.substring(1);
            }
            int coefficient = sign * Integer.parseInt("0" + (i.split("x").length == 0 ? 1: i.split("x")[0]));
            int exponent = Integer.parseInt(i.contains("^") ? "0" + i.split("\\^")[1]: (i.contains("x") ? "1": "0"));
            this.polynomial[exponent] = coefficient;
        }
    }

    /**
     * getter for polynomial
     *
     * @return polynomial
     */
    public int[] getPolynomial() {
        return this.polynomial;
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
            if (this.getPolynomial()[i] != 0)  break;
        }
        return i;
    }

    /**
     * Returns the coefficient corresponding to the given exponent.  Returns 0 if there is no term with that exponent
     * in the polynomial.
     *
     * @param d the exponent whose coefficient is returned.
     * @return the coefficient of the term of whose exponent is d.
     * @throws IndexOutOfBoundsException for invalid values of d
     */
    @Override
    public int getCoefficient(int d) throws IndexOutOfBoundsException {
        return this.getPolynomial()[d];
    }

    /**
     * @return true if the polynomial represents the zero constant
     */
    @Override
    public boolean isZero() {
        return (Arrays.stream(this.getPolynomial()).filter(s -> s != 0).count()) == 0;
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
        if (q == null) throw new NullPointerException("q cannot be null");
        int[] new_polynomial = new int[MAX_SIZE];
        if (q.getClass() == DensePolynomial.class) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                new_polynomial[i] = this.getPolynomial()[i] + ((DensePolynomial) q).getPolynomial()[i];
            }
        } else if (q.getClass() == SparsePolynomial.class) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                if (((SparsePolynomial) q).getPolynomial().containsKey(i)) {
                    new_polynomial[i] = ((SparsePolynomial) q).getPolynomial().get(i) + this.getPolynomial()[i];
                } else {
                    new_polynomial[i] = this.getPolynomial()[i];
                }
            }
        }

        return new DensePolynomial(new_polynomial);
    }

    /**
     * Returns a polynomial by multiplying the parameter with the current instance.  Neither the current instance nor
     * the parameter are modified.  All expressions with exponents greater than MAX_SIZE are ignored
     *
     * constraints: this.polynomial.size * q.polynomial.size < MAX_SIZE
     *
     * @param q the polynomial to multiply with <code>this</code>
     * @return <code>this * </code>q
     * @throws NullPointerException if q is null
     */
    @Override
    public Polynomial multiply(Polynomial q) throws NullPointerException {
        if (q == null) throw new NullPointerException("q cannot be null");
        int[] new_polynomial = new int[2*MAX_SIZE];
        if (q.getClass() == DensePolynomial.class) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                for (int j = 0; j < MAX_SIZE; ++j) {
                    new_polynomial[i + j] += this.getPolynomial()[i] * ((DensePolynomial) q).getPolynomial()[j];
                }
            }
        } else if (q.getClass() == SparsePolynomial.class) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                for (int j = 0; j < MAX_SIZE; ++j) {
                    if (((SparsePolynomial) q).getPolynomial().containsKey(j)) {
                        new_polynomial[i + j] += ((SparsePolynomial) q).getPolynomial().get(j) * this.getPolynomial()[i];
                    }
                }

            }
        }
        return new DensePolynomial(new_polynomial);
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
        if (q == null) throw new NullPointerException("q cannot be null");
        int[] new_polynomial = new int[MAX_SIZE];
        if (q.getClass() == DensePolynomial.class) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                new_polynomial[i] = this.getPolynomial()[i] - ((DensePolynomial) q).getPolynomial()[i];
            }
        } else if (q.getClass() == SparsePolynomial.class) {
            for (int i = 0; i < MAX_SIZE; ++i) {
                if (((SparsePolynomial) q).getPolynomial().containsKey(i)) {
                    new_polynomial[i] = this.getPolynomial()[i] - ((SparsePolynomial) q).getPolynomial().get(i);
                } else {
                    new_polynomial[i] = this.getPolynomial()[i];
                }
            }
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
            new_polynomial[i] = -this.getPolynomial()[i];
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
        for (Object i: this.getPolynomial()) {
            if (i.getClass() != Integer.class) {    //array indices are always integers so only checking class of coefficients
                return false;
            }
        }
        return true;
    }

    /**
     * Converts DensePolynomial object to canonical mathematical representation
     *
     * @return the string representation of this DensePolynomial
     */
    public String toString() {
        StringBuilder str = new StringBuilder();
        if (this.isZero()) {
            return "0";
        } else {
            for (int i = MAX_SIZE - 1; i > 1; --i) {
                if (this.getPolynomial()[i] != 0) {
                    str.append(this.getPolynomial()[i] == 1 ? "": this.getPolynomial()[i]).append("x^").append(i).append(" + ");
                }
            }
            if (this.getPolynomial()[1] != 0) {
                str.append(this.getPolynomial()[1] == 1 ? "": this.getPolynomial()[1]).append("x + ");
            }
            if (this.getPolynomial()[0] != 0) {
                str.append(this.getPolynomial()[0]).append(" + ");
            }
        }
        if (str.length() == 0) {
            str.append("0");
        } else {
            str.delete(str.length() - 3, str.length() - 1);
        }
        return str.toString();
    } //TODO add support for negative numbers
}
