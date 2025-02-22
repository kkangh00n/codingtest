package set;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class BOJ_7662 {

    public static void main(String[] args) throws IOException {
        List<String> answers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringTokenizer st;
        for (int i = 0; i < T; i++) {
            String answer = "EMPTY";

            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();

            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());

                String command = st.nextToken();
                int value = Integer.parseInt(st.nextToken());

                //삭제 연산
                if (command.equals("D")) {
                    if (map.isEmpty()) {
                        continue;
                    }

                    int temp;

                    //최댓갑 삭제
                    if (value == 1) {
                        temp = map.lastKey();
                    }
                    //최솟값 삭제
                    else {
                        temp = map.firstKey();
                    }

                    map.put(temp, map.get(temp)-1);
                    if (map.get(temp) == 0) {
                        map.remove(temp);
                    }
                }
                //삽입 연산
                else {
                    map.put(value, map.getOrDefault(value, 0) + 1);
                }
            }

            if (!map.isEmpty()) {
                Integer min = map.firstKey();
                Integer max = map.lastKey();
                answer = max + " " + min;
            }

            answers.add(answer);
        }

        for (String answer : answers) {
            System.out.println(answer);
        }
    }
}
