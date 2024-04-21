import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class BipartiteGraph {
    static enum Colour {
        TRANSPARENT, WHITE, BLACK;

        static Colour invert(Colour color) {
            switch (color) {
                case WHITE:
                    return BLACK;
                case BLACK:
                    return WHITE;
                default:
                    return TRANSPARENT;
            }
        }
    }

    static class Container {
        int node;
        Colour colour;

        Container(int node, Colour colour) {
            this.node = node;
            this.colour = colour;
        }
    }

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        Colour[] vis = new Colour[n];
        Arrays.fill(vis, Colour.TRANSPARENT);

        for (int node = 0; node < n; node++) {
            if (vis[node] != Colour.TRANSPARENT) {
                continue;
            }
            Queue<Container> queue = new LinkedList<>();
            queue.add(new Container(node, Colour.WHITE));
            vis[node] = Colour.WHITE;

            while (!queue.isEmpty()) {
                Container ctr = queue.remove();
                for (int nbr : graph[ctr.node]) {
                    if (vis[nbr] == ctr.colour) {
                        return false;
                    } else if (vis[nbr] == Colour.invert(ctr.colour)) {
                        // do nothing as the neighbour is already properly coloured
                    } else if (vis[nbr] == Colour.TRANSPARENT) {
                        vis[nbr] = Colour.invert(ctr.colour);
                        queue.add(new Container(nbr, Colour.invert(ctr.colour)));
                    }
                }
            }
        }
        return true;
    }
}
