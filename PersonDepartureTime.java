import java.util.Arrays;

public class PersonDepartureTime {
    private static int[] getDeptTime(int[] time, int n, int m) {
        int[] dept = new int[m];

        // if there are more than or equal to counters than person,
        // then there is no case of a delay so everyone will get
        // their service in one second
        if (n >= m) {
            Arrays.fill(dept, 1);
            return dept;
        }

        // the first m people will not get any queing delay
        for (int i = 0; i < n; i++) {
            dept[i] = time[i] + 1;
        }

        // as people tend to go to the minimum crowded counter, so fill up will be
        // according to a round robin manner, hence departure of a person will depend
        // on the person standing before him, so it will be maximum of his arrival time
        // and person's dept time plus one for his service time
        for (int i = n; i < m; i++) {
            dept[i] = Math.max(dept[i - n], time[i]) + 1;
        }

        return dept;
    }

    public static void main(String[] args) {
        int[] time = new int[] { 0, 0, 0, 0 };
        int n = 2;
        int m = 4;

        System.out.println(Arrays.toString(getDeptTime(time, n, m)));
    }
}
