import java.util.*;

public class SparsePolynomial implements Polynomial {

    private Map<Integer, Integer> polynomial = new HashMap<>();

    private SparsePolynomial(Map<Integer, Integer> p) {
        this.polynomial = p;
    }

    /**
     * Constructor for creating DensePolynomials from canonical string representations of polynomials
     *
     * @param s canonical string representation of a polynomial
     */
    public SparsePolynomial(String s) {
        String[] expressions = s.split("\\s(?=[+-])");
        for (String i: expressions) {
            int sign = 1;
            if (i.charAt(0) == '-') {
                sign = -1;
            }
            int coefficient = sign * Integer.parseInt("0" + i.split("x")[0]);
            int exponent = Integer.parseInt(i.contains("\\^") ? "0" + i.split("\\^")[1]: (i.contains("x") ? "1": "0"));
            this.polynomial.put(exponent, coefficient);
        }
    }

    public Map<Integer, Integer> getPolynomial() {
        return polynomial;
    }

    /**
     * Returns the degree of the polynomial.
     *
     * @return the largest exponent with a non-zero coefficient.  If all terms have zero exponents, it returns 0.
     */
    @Override
    public int degree() {
        return Collections.max(getPolynomial().keySet());
    }

    /**
     * Returns the coefficient corresponding to the given exponent.  Returns 0 if there is no term with that exponent
     * in the polynomial.
     *
     * @param d the exponent whose coefficient is returned.
     * @return the coefficient of the term of whose exponent is d.
     */
    @Override
    public int getCoefficient(int d) throws IllegalArgumentException {
        return this.getPolynomial().getOrDefault(d, 0);
    }

    /**
     * @return true if the polynomial represents the zero constant
     */
    @Override
    public boolean isZero() {
        return this.getPolynomial().isEmpty();
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
    public Polynomial add(Polynomial q) {
        if (q == null) throw new NullPointerException("q cannot be null");
        Map<Integer, Integer> newMap = this.getPolynomial();
        if (q.getClass() == SparsePolynomial.class) {
            for (Integer i: ((SparsePolynomial) q).getPolynomial().keySet()) {
                if (this.getPolynomial().containsKey(i)) {
                    newMap.replace(i, this.getPolynomial().get(i) + ((SparsePolynomial) q).getPolynomial().get(i));
                } else {
                    newMap.put(i, ((SparsePolynomial) q).getPolynomial().get(i));
                }
            }
        } else if (q.getClass() == DensePolynomial.class) {
            for (int i = 0; i < ((DensePolynomial) q).getPolynomial().length; ++i) {
                if (((DensePolynomial) q).getPolynomial()[i] == 0) continue;
                if (this.getPolynomial().containsKey(i)) {
                    newMap.replace(i, this.getPolynomial().get(i) + ((DensePolynomial) q).getPolynomial()[i]);
                } else {
                    newMap.put(i, ((DensePolynomial) q).getPolynomial()[i]);
                }
            }
        }
        return new SparsePolynomial(newMap);
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
    public Polynomial multiply(Polynomial q) {
        if (q == null) throw new NullPointerException("q cannot be null");
        Map<Integer, Integer> newMap = new HashMap<>();
        if (q.getClass() == SparsePolynomial.class) {
            for (Integer i: this.getPolynomial().keySet()) {
                for (Integer j: ((SparsePolynomial) q).getPolynomial().keySet()) {
                    if (newMap.containsKey(i * j)) {
                        newMap.replace(i + j, newMap.get(i * j) + this.getPolynomial().get(i) * ((SparsePolynomial) q).getPolynomial().get(j));
                    } else {
                        newMap.put(i + j, this.getPolynomial().get(i) * ((SparsePolynomial) q).getPolynomial().get(j));
                    }
                }
            }
        } else if (q.getClass() == DensePolynomial.class) {
            for (Integer i: this.getPolynomial().keySet()) {
                for (int j = 0; j < ((DensePolynomial) q).getPolynomial().length; ++j) {
                    if (((DensePolynomial) q).getPolynomial()[j] == 0) continue;
                    if (newMap.containsKey(i * j)) {
                        newMap.replace(i + j, newMap.get(i * j) + this.getPolynomial().get(i) * ((DensePolynomial) q).getPolynomial()[j]);
                    } else {
                        newMap.put(i + j, this.getPolynomial().get(i) * ((DensePolynomial) q).getPolynomial()[j]);
                    }
                }
            }
        }
        return new SparsePolynomial(newMap);
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
    public Polynomial subtract(Polynomial q) {
        if (q == null) throw new NullPointerException("q cannot be null");
        Map<Integer, Integer> newMap = this.getPolynomial();
        if (q.getClass() == SparsePolynomial.class) {
            for (Integer i: ((SparsePolynomial) q).getPolynomial().keySet()) {
                if (this.getPolynomial().containsKey(i)) {
                    newMap.replace(i, this.getPolynomial().get(i) - ((SparsePolynomial) q).getPolynomial().get(i));
                } else {
                    newMap.put(i, -((SparsePolynomial) q).getPolynomial().get(i));
                }
            }
        } else if (q.getClass() == DensePolynomial.class) {
            for (int i = 0; i < ((DensePolynomial) q).getPolynomial().length; ++i) {
                if (((DensePolynomial) q).getPolynomial()[i] == 0) continue;
                if (this.getPolynomial().containsKey(i)) {
                    newMap.replace(i, this.getPolynomial().get(i) - ((DensePolynomial) q).getPolynomial()[i]);
                } else {
                    newMap.put(i, -((DensePolynomial) q).getPolynomial()[i]);
                }
            }
        }
        return new SparsePolynomial(newMap);
    }

    /**
     * Returns a polynomial by negating the current instance. The current instance is not modified.
     *
     * @return -this
     */
    @Override
    public Polynomial minus() {
        Map<Integer, Integer> newMap = new HashMap<>();
        for (Integer i: this.getPolynomial().keySet()) {
            newMap.put(i, -this.getPolynomial().get(i));
        }
        return new SparsePolynomial(newMap);
    }

    /**
     * Checks if the class invariant holds for the current instance.
     *
     * @return {@literal true} if the class invariant holds, and {@literal false} otherwise.
     */
    @Override
    public boolean wellFormed() {
        return this.getPolynomial().keySet().stream().filter(s -> s.getClass() != Integer.class).count() == 0 &&
                this.getPolynomial().values().stream().filter(s -> s.getClass() != Integer.class).count() == 0;
    }

    /**
     * Converts SparsePolynomial to a canonical mathematical string in decreasing order
     *
     * @return the string representation of the SparsePolynomial
     */
    public String toString() {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(this.getPolynomial());
        StringBuilder str = new StringBuilder();
        for (Integer i: sortedMap.descendingKeySet()) {
            if (i > 1) {
                str.append(sortedMap.get(i) == 1 ? "": sortedMap.get(i)).append("x^").append(i).append(" + ");
            } else if (i == 1) {
                str.append(sortedMap.get(i) == 1 ? "": sortedMap.get(i)).append("x").append(" + ");
            } else if (i == 0) {
                str.append(sortedMap.get(i)).append(" + ");
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
