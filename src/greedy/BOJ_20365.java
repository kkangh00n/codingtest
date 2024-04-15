package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_20365 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] problems = br.readLine().split("");

        int red = 0;
        int blue = 0;

        if (problems[0].equals("R")) {
            red++;
        }
        else if (problems[0].equals("B")) {
            blue++;
        }

        for (int i = 1; i < N; i++) {
            //이전에 색깔과 같지 않다면
            if(!problems[i].equals(problems[i-1])) {
                if(problems[i].equals("R")) {
                    red++;
                }
                if(problems[i].equals("B")) {
                    blue++;
                }
            }
        }

        int answer = Math.min(red, blue) + 1;

        System.out.println(answer);
    }
}
