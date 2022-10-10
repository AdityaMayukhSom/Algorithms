public class Lecture16 {
    public static void main(String[] args) {
        int[] h = { 0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1 };
        int l = 0;
        int r = h.length - 1;
        int lmax = 0, rmax = 0;
        int water = 0;
        while (l <= r) {
            if (rmax <= lmax) {
                water = water + Math.max(0, rmax - h[r]);
                rmax = Math.max(rmax, h[r]);
                r--;
            } else {
                water = water + Math.max(0, lmax - h[l]);
                lmax = Math.max(lmax, h[l]);
                l++;
            }
        }
        System.out.println("Answer Water Is " + water);
    }
}
