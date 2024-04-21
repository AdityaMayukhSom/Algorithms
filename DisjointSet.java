import java.util.Arrays;
import java.util.Collections;

class DisjointSet {
    // negative element signifies that the element is the parent of himself
    // the absolute value of the negative number is the rank of the component
    // whereas if the rank is positive, then the positive value is the parent
    // of the component
    private int[] rank;

    DisjointSet(final int n) {
        this.rank = new int[n];
        Arrays.fill(this.rank, -1);
    }

    private int findRoot(final int v) {
        if (rank[v] < 0) return v;
        int p = findRoot(rank[v]);
        rank[v] = p;
        return p;
    }

    public boolean sameComponent(final int v1, final int v2) {
        int p1 = findRoot(v1);
        int p2 = findRoot(v2);
        return p1 == p2;
    }

    public void merge(final int v1, final int v2) {
        int p1 = findRoot(v1);
        int p2 = findRoot(v2);

        if(p1 == p2){
            return;
        }

        if (rank[p2] < rank[p1]) {
            int temp = p1;
            p1 = p2;
            p2 = temp;
        }

        // p1 points to the larger component now

        rank[p1] += rank[p2];
        rank[p2] = p1;
        return;
    }

	private void printRanks(){
		System.out.println(Arrays.toString(this.rank));
	}
    
	public static void main(String[] args){
		DisjointSet ds = new DisjointSet(5);
		ds.merge(1, 2);
		ds.printRanks();
		// ds.merge(2, 4);
		// System.out.println(ds.findRoot(4));
		System.out.println(ds.findRoot(2));
	}
}

