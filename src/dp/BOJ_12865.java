package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 냅색 알고리즘
 *
 * N : 물품의 수
 * K : 준서가 버틸 수 있는 무게
 * W : 물건의 무게
 * V : 물건의 가치
 *
 * 1. dp 배열
 * - i -> 무게 별
 * - dp[i] -> 가치
 *
 * 2. 물건을 입력받아 무게 별 정렬
 * 3. 각 무게 별 가질 수 있는 가치의 최댓값 저장
 */
public class BOJ_12865 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<ObjectThing> objects = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int W = Integer.parseInt(st.nextToken());
            int V = Integer.parseInt(st.nextToken());

            objects.add(new ObjectThing(W, V));
        }

        //물건을 무게 별 정렬
        Collections.sort(objects);

        //무게 별 최대 가치 배열 저장
        int[] dp = new int[K+1];

        //물건 별
        for (int i = 0; i < objects.size(); i++) {
            ObjectThing currentObject = objects.get(i);

            int currentWeight = currentObject.weight;
            int currentValue = currentObject.value;

            //무게 별 가질 수 있는 최대 가치 설정
            //max(현재 가질 수 있는 가치, 현재 물건을 뺀 가치에서 현재 가치를 더한 가치)
            for (int j = dp.length-1; j >=currentWeight ; j--) {
                dp[j] = Math.max(dp[j], dp[j-currentWeight] + currentValue);
            }
        }

        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}

class ObjectThing implements Comparable<ObjectThing>{
    int weight;
    int value;

    ObjectThing(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(ObjectThing o) {
        return this.weight - o.weight;
    }
}
