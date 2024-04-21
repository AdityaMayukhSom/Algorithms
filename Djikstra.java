import java.util.PriorityQueue;
import java.util.ArrayList;

class Pair {
    public int node;
    public double prob;

    public Pair(int node, double prob) {
        this.node = node;
        this.prob = prob;
    }
}

class Solution {
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        int e = edges.length;

        ArrayList<ArrayList<Pair>> adj = new ArrayList<>();
        double[] prob = new double[n];

        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<Pair>());
            prob[i] = 0;
        }

        for (int i = 0; i < e; i++) {
            adj.get(edges[i][0]).add(new Pair(edges[i][1], succProb[i]));
            adj.get(edges[i][1]).add(new Pair(edges[i][0], succProb[i]));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>((x, y) -> Double.compare(y.prob, x.prob));

        prob[start] = 1;
        pq.add(new Pair(start, 1));

        while (!pq.isEmpty()) {
            Pair el = pq.remove();
            int currNode = el.node;
            double currProb = el.prob;
            for (Pair nbrPair : adj.get(currNode)) {
                int nbr = nbrPair.node;
                double currToNbrProb = nbrPair.prob;
                if (currProb * currToNbrProb > prob[nbr]) {
                    prob[nbr] = currProb * currToNbrProb;
                    pq.add(new Pair(nbr, prob[nbr]));
                }
            }
        }

        return prob[end];
    }
}