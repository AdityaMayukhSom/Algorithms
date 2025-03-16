import java.io.Serializable;

public class Pair implements Serializable {
    public int first;
    public int second;

    public Pair(final int first, final int second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object ob) {
        if (ob == null) {
            return false;
        }

        if (ob.getClass() != this.getClass()) {
            return false;
        }

        Pair p = (Pair) ob;

        return p.first == this.first && p.second == this.second;
    }

    @Override
    public String toString() {
        return String.format(
            "Pair (first: %d, second: %d)", 
            this.first, 
            this.second
        );
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + this.first;
        hash = 97 * hash + this.second;
        return hash;
    }

}
