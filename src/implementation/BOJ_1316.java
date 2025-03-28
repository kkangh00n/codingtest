package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class BOJ_1316 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int answer = 0;
        Set<Character> set;

        for (int i=0; i<N; i++) {
            boolean isSequence = true;
            set = new HashSet<>();
            char[] input = br.readLine().toCharArray();
            set.add(input[0]);

            for (int j=1; j<input.length; j++) {
                //알파벳이 이전이랑 같다면, continue
                if (input[j] == input[j-1]) {
                    continue;
                }

                //알파벳이 이미 들어있다면, break
                if (set.contains(input[j])) {
                    isSequence = false;
                    break;
                }

                set.add(input[j]);
            }

            if (isSequence) {
                answer++;
            }
        }

        System.out.println(answer);
    }
}
