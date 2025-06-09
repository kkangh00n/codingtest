package two_pointer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_1940 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        //재료의 개수
        int N = Integer.parseInt(br.readLine());
        //갑옷을 만드는데 필요한 수
        int M = Integer.parseInt(br.readLine());

        int[] materials = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            materials[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(materials);

        int left = 0;
        int right = N-1;

        int answer = 0;

        while(left<right) {
            int leftMaterial = materials[left];
            int rightMaterial = materials[right];

            if (leftMaterial + rightMaterial == M) {
                answer++;
                left++;
                right--;
            } else if(leftMaterial + rightMaterial > M) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(answer);
    }
}
