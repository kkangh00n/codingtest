package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 리스트로 구현
 * <p>
 * push -> 그냥 집어 넣음 pop -> size() 확인, 비어있으면 -1 출력 안비어있으면 size()-1 index 제거 및 출력 size -> size() empty
 * -> isEmpty() top -> size()-1 index 출력
 */
public class BOJ_10828 {

    private static final String PUSH = "push";
    private static final String POP = "pop";
    private static final String SIZE = "size";
    private static final String EMPTY = "empty";
    private static final String TOP = "top";


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        List<Integer> stack = new ArrayList<>();

        StringTokenizer st;
        StringBuilder answer = new StringBuilder();
        while (N-- > 0) {
            st = new StringTokenizer(br.readLine());

            String input = st.nextToken();

            if (input.equals(PUSH)) {
                stack.add(Integer.parseInt(st.nextToken()));
            } else if (input.equals(POP)) {
                if (!stack.isEmpty()) {
                    answer.append(stack.remove(stack.size() - 1)).append("\n");
                } else {
                    answer.append(-1).append("\n");
                }

            } else if (input.equals(SIZE)) {
                answer.append(stack.size()).append("\n");
            } else if (input.equals(EMPTY)) {
                answer.append(stack.isEmpty() ? 1 : 0).append("\n");
            } else if (input.equals(TOP)) {
                if (!stack.isEmpty()) {
                    answer.append(stack.get(stack.size() - 1)).append("\n");
                } else {
                    answer.append(-1).append("\n");
                }
            }
        }

        System.out.println(answer);
    }
}
