package sliding_window;

import java.io.IOException;
import java.util.Scanner;

public class BOJ_15961 {

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        //접시 갯수
        int N = sc.nextInt();
        //초밥 가짓 수
        int d = sc.nextInt();
        //연속해서 먹는 접시의 수
        int k = sc.nextInt();
        //쿠폰을 통해 먹을 수 있는 초밥 번호
        int c = sc.nextInt();

        //회전 초밥 목록
        int[] sushi = new int[N];
        //먹은 초밥 목록 저장
        int[] eated = new int[d + 1];

        for (int i = 0; i < N; i++) {
            sushi[i] = sc.nextInt();
        }

        //index 0부터 시작해서 먹은 초밥 초기화
        int count = 0;
        for (int i = 0; i < k; i++) {
            if (eated[sushi[i]] == 0) {
                count++;
            }
            eated[sushi[i]]++;
        }

        int max = count;
        if (eated[c] == 0) {
            max++;
        }

        //index 1부터 n-1번까지 이동
        for (int i = 1; i < N; i++) {

            int end = (i + k - 1) % N;

            //이전에 먹은 초밥 제거
            if (--eated[sushi[i - 1]] == 0) {
                count--;
            }

            //새로 먹은 초밥 추가
            if (eated[sushi[end]]++ == 0) {
                count++;
            }

            max = Math.max(max, eated[c] == 0 ? count + 1 : count);
        }

        System.out.println(max);
    }
}
