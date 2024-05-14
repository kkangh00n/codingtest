package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_2512 {

    static int N;
    static int M;
    static int[] budgets;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        budgets = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            budgets[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(budgets);
        //예산 최댓값
        M = Integer.parseInt(br.readLine());

        int answer = binarySearch();

        System.out.println(answer);
    }

    public static int binarySearch() {
        int start = 0;
        int end = budgets[N - 1];

        while (start <= end) {
            int middle = (start + end) / 2;

            int sum = calculate(middle);

            //예산이 초과된다면
            if(sum>M) {
                end = middle - 1;
            }
            else {
                start = middle + 1;
            }
        }
        return start - 1;
    }

    public static int calculate(int budgetMax) {

        int result = 0;
        for (int budget : budgets) {
            if(budgetMax > budget) {
                result += budget;
            }
            else {
                result += budgetMax;
            }
        }
        return result;
    }
}
