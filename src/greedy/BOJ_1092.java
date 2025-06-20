package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 그리디
 * 1. 입력
 * 2. 크레인과 박스 내림차순 정렬
 * 3. 1분에 모든 크레인이 동작하여 가져갈 수 있는 박스 처리
 * 4. 모든 박스를 처리할 수 없다면 -1을 출력한다.
 */
public class BOJ_1092 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        Integer[] cranes = new Integer[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cranes[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        List<Integer> boxes = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        //크레인, 박스 배열 정렬
        Arrays.sort(cranes, Collections.reverseOrder());
        boxes.sort(Collections.reverseOrder());

        //무게가 가장 높은 박스를 옮길 수 없다면, -1 출력
        if (cranes[0] < boxes.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;

        //박스를 모두 옮길 때까지 제거
        while(!boxes.isEmpty()) {
            time++;

            //크레인을 통해서 옮길 수 있는 박스를 제거
            for (int craneIndex = 0; craneIndex < N; craneIndex++) {
                for (int boxIndex = 0; boxIndex < boxes.size(); boxIndex++) {

                    int currentCrane = cranes[craneIndex];
                    int currentBox = boxes.get(boxIndex);

                    if (currentCrane >= currentBox) {
                        boxes.remove(boxIndex);
                        break;
                    }
                }
            }
        }

        System.out.println(time);
    }
}
