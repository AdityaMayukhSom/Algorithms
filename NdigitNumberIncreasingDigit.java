import java.util.ArrayList;

public class NdigitNumberIncreasingDigit {
    public static void main(String[] args) {
        ArrayList<Integer> solution = increasingNumbers(2);
        for (int i = 0; i < solution.size(); i++) {
            System.out.println(solution.get(i));
        }
    }

    static ArrayList<Integer> increasingNumbers(int N) {
        ArrayList<Integer> answer = new ArrayList<Integer>();
        if (N == 1) {
            for (int i = 0; i <= 9; i++) {
                answer.add(i);
            }
            return answer;
        } else {
            dfs(1, 0, N, answer);
        }

        return answer;
    }

    public static void dfs(int start, int num, int N, ArrayList<Integer> answer) {
        if (N == 0) {
            answer.add(num);
            return;
        }
        for (int i = start; i <= 9; i++) {
            int curr = num * 10 + i;
            dfs(i + 1, curr, N - 1, answer);
        }
    }

}