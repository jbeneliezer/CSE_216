import java.util.*;

public class SparsePolynomial implements Polynomial {

    private Map<Integer, Integer> polynomial;
    private String expression;

    public SparsePolynomial(Map<Integer, Integer> p) {
        this.polynomial = p;
    }

    /**
     * Constructor for creating DensePolynomials from canonical string representations of polynomials
     *
     * @param s canonical string representation of a polynomial
     */
    public SparsePolynomial(String s) throws NumberFormatException {
        this.expression = s;
        if (expression.length() == 0 || !this.wellFormed()) throw new NumberFormatException("Expression must be canonical and cannot be empty.");
        String[] terms = expression.split("\\s(?=[+-]\\s)");
        polynomial = new HashMap<>();
        for (String i: terms) {
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
            int coefficient = sign * Integer.parseInt((i.split("x").length == 0) ? "1": i.split("x")[0]);
            int exponent = Integer.parseInt(i.contains("^") ? i.split("\\^")[1]: (i.contains("x") ? "1": "0"));
            this.polynomial.put(exponent, coefficient);
        }
    }

    public Map<Integer, Integer> getPolynomial() {
        return this.polynomial;
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
        return this.getPolynomial().values().stream().noneMatch(s -> s != 0);
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
                    if (newMap.containsKey(i + j)) {
                        newMap.replace(i + j, newMap.get(i + j) + this.getPolynomial().get(i) * ((SparsePolynomial) q).getPolynomial().get(j));
                    } else {
                        newMap.put(i + j, this.getPolynomial().get(i) * ((SparsePolynomial) q).getPolynomial().get(j));
                    }
                }
            }
        } else if (q.getClass() == DensePolynomial.class) {
            for (Integer i: this.getPolynomial().keySet()) {
                for (int j = 0; j < ((DensePolynomial) q).getPolynomial().length; ++j) {
                    if (((DensePolynomial) q).getPolynomial()[j] == 0) continue;
                    if (newMap.containsKey(i + j)) {
                        newMap.replace(i + j, newMap.get(i + j) + this.getPolynomial().get(i) * ((DensePolynomial) q).getPolynomial()[j]);
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
        String condensed = this.expression.replaceAll("[\\d.]", "");
        return !condensed.contains(".");
    }

    /**
     * Converts SparsePolynomial to a canonical mathematical string in decreasing order
     *
     * @return the string representation of the SparsePolynomial
     */
    public String toString() {
        TreeMap<Integer, Integer> sortedMap = new TreeMap<>(this.getPolynomial());
        StringBuilder str = new StringBuilder();
        String sign;
        if (this.isZero()) {
            return "0";
        } else {
            int time = 0;
            for (Integer i : sortedMap.descendingKeySet()) {
                if (i == 1) {
                    sign = (sortedMap.get(i) > 0) ? "+ ": "- ";
                    str.append((time == 0)? "": sign).append(sortedMap.get(i) == 1 ? "": sortedMap.get(i)).append("x ");
                    ++time;
                } else if (i == 0) {
                    sign = (sortedMap.get(i) > 0) ? "+ ": "- ";
                    str.append((time == 0) ? "": sign).append(sortedMap.get(i)).append(" ");
                } else {
                    sign = (this.getPolynomial().get(i) > 0) ? "+ ": "- ";
                    str.append((time == 0)? "": sign).append(sortedMap.get(i) == 1 ? "": Math.abs(sortedMap.get(i))).append("x^").append(i).append(" ");
                    ++time;
                }
            }
        }
        str.deleteCharAt(str.length() - 1);
        return str.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof SparsePolynomial) {
            return this.getPolynomial().equals(((SparsePolynomial) obj).getPolynomial());
        } else {
            return false;
        }
    }
}
