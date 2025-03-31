package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1158 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int index = 0;

        List<Integer> list = new LinkedList<>();
        List<Integer> answer = new ArrayList<>();
        for (int i=1; i<=N; i++) {
            list.add(i);
        }

        while(!list.isEmpty()) {
            index = ((index+K-1)%list.size());
            answer.add(list.remove(index));
        }

        StringBuilder output = new StringBuilder("<");
        for (int i = 0; i < N; i++) {
            output.append(answer.get(i));
            if (i<N-1) {
                output.append(", ");
            }
        }
        output.append(">");
        System.out.println(output);
    }
}
