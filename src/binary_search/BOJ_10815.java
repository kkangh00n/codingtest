package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_10815 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //숫자 카드 갯수
        int N = Integer.parseInt(st.nextToken());
        //숫자 카드 배열
        int[] numbers = new int[N];

        //갖고있는 숫자 카드 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int number = Integer.parseInt(st.nextToken());
            numbers[i] = number;
        }
        //숫자 카드 정렬
        Arrays.sort(numbers);

        //질문 갯수
        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int[] questions = new int[M];

        //확인할 숫자
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int q = Integer.parseInt(st.nextToken());
            questions[i] = q;
        }

        StringBuilder answer = new StringBuilder();
        for (int question : questions) {
            int result = find(numbers, question);
            answer.append(result + " ");
        }

        System.out.println(answer);
    }

    public static int find(int[] numbers, int question) {
        int start = 0;
        int end = numbers.length - 1;

        while (start <= end) {
            int middle = (start + end) / 2;

            int m = numbers[middle];
            if(question > m) {
                start = middle + 1;
            }
            else if(question < m){
                end = middle - 1;
            }
            else {
                return 1;
            }
        }
        return 0;
    }
}
