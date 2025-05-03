package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * Y(이긴 게임)/X(게임 횟수) = Z(승률)
 *
 * 형택이가 게임을 최소 몇 번 더 해야 Z가 변하는지 구한다.
 *
 * 1. 단순히 X와 Y의 값을 증가 시켜본다?
 *      - 수의 범위가 큼
 * 2. 이분탐색
 */
public class BOJ_1072 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long X = Long.parseLong(st.nextToken());
        long Y = Long.parseLong(st.nextToken());

        //현재 승률
        int currentZ = (int) ((Y*100)/X);

        if (currentZ >=99) {
            System.out.println(-1);
            return;
        }

        int left = 0;
        int right = 1000000000;
        int result = -1;

        while(left<=right) {
            int mid = (left+right)/2;

            //mid판 만큼 게임을 더 했을 때 승률
            int Z = (int) ((Y+mid)*100/(X+mid));

            //게임을 더 했는데 승률이 같거나 작다면, 더 많이 게임을 해야한다.
            if (currentZ >= Z) {
                left = mid + 1;
                result = mid + 1;
            }
            //게임을 더 해서 승률이 높아졌다면, 게임 횟수를 줄여본다.
            else {
                right = mid - 1;
            }
        }

        System.out.println(result);

    }
}
