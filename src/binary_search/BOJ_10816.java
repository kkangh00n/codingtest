package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10816 {

    public static void main(String[] args) throws IOException {
        StringBuilder answer = new StringBuilder();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] cards = new int[N];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards);

        st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());

        int[] keys = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < M; i++) {
            keys[i] = Integer.parseInt(st.nextToken());

            answer.append(upperBound(cards, keys[i]) - lowerBound(cards, keys[i]));
        }

        System.out.println(answer);
    }

    public static int upperBound(int[] card, int key) {
        int start = 0;
        int end = card.length;

        while (start<=end) {
            int mid = (start+end)/2;

            if (key < card[mid]) end = mid;
            else start = mid + 1;
        }

        return start;
    }

    public static int lowerBound(int[] card, int key) {
        int start = 0;
        int end = card.length;

        while (start<end) {
            int mid = (start+end)/2;

            if (key <= card[mid]) end = mid;
            else start = mid + 1;
        }

        return start;
    }
}
