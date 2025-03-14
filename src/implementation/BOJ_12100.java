package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 조건
 * 1. 상하좌우로 움직일 수 있다.
 * 2. 같은 숫자가 만나면 합쳐진다
 * ㄴ> ex) 2,2,2,2 를 왼쪽으로 움직이면 4,4 가 된다
 * 3. 같은 숫자가 3개가 한번에 부딪힐 때는 이동하려고 하는 쪽의 칸이 먼저 합쳐진다
 * ㄴ> ex) 2,2,2 를 왼쪽으로 움직이면 4,2 가 된다.
 * ㄴ> ex) 2,2,4 를 왼쪽으로 움직이면 4,4 가 된다.
 * ㄴ> 전에 삽입한 숫자가 가능한 합칠 수 있는 상태인지 기록이 필요하다.
 *
 * 풀이
 * DFS 갈 수 있는 모든 경우를 탐색한다 5번을 이동했을 때, 얻는 블록 중 최댓값을 정한다.
 * 지도를 90도씩 4번 회전하여 탐색한다.
 * 이동 시 주의 사항 이동하려는 방향에서부터 합친다 ㄴ> 왼쪽으로 이동한다면, 왼쪽에서부터 합친다. 스택 이용!
 */
public class BOJ_12100 {

    static Stack<Integer> stack = new Stack<>();

    static int max = Integer.MIN_VALUE;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];

        //게임 배열 초기화
        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] > max) {
                    max = map[i][j];
                }
            }
        }

        //탐색
        DFS(map, 0);

        System.out.println(max);
    }

    //탐색
    public static void DFS(int[][] map, int count) {
        //5번 움직인 경우 최댓값 추출
        if (count == 5) {
            for (int i = 0; i < map.length; i++) {
                max = Math.max(max, Arrays.stream(map[i]).max().getAsInt());
            }
            return;
        }

        for (int k = 0; k < 4; k++) {
            //기존 지도를 90도 회전 후
            rocate(map);

            //새로운 지도 생성
            int[][] copyMap = new int[map.length][map.length];
            for (int i = 0; i < map.length; i++) {
                for (int j = 0; j < map.length; j++) {
                    copyMap[i][j] = map[i][j];
                }
            }

            //새로운 지도를 왼쪽으로 이동
            move(copyMap);

            DFS(copyMap, count + 1);
        }
    }

    //지도 회전
    private static void rocate(int[][] map) {
        int[][] temp = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                temp[i][j] = map[N-1-j][i];
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = temp[i][j];
            }
        }
    }

    //이동
    private static void move(int[][] copyMap) {
        boolean availableCombine = true;

        //왼 -> 오
        for (int i = 0; i < copyMap.length; i++) {
            //위 -> 아래
            for (int j = 0; j < copyMap.length; j++) {
                availableCombine = combine(copyMap[j][i], availableCombine);
                copyMap[j][i] = 0;
            }

            //스택에 쌓은 것들 재배치
            int index = stack.size()-1;
            while (!stack.isEmpty()) {
                copyMap[index--][i] = stack.pop();
            }
        }
    }

    //타일 이동 시, 같은 숫자 합치는 과정
    private static boolean combine(int currentValue, boolean availableCombine) {
        //빈 칸일 경우 넘김
        if (currentValue == 0) {
            return availableCombine;
        }

        //이전에 숫자가 존재
        if (!stack.isEmpty()) {
            Integer previousValue = stack.peek();

            //이전 숫자가 현재 숫자와 동일하며, 이전 숫자가 합쳐져서 생긴 숫자가 아니라면
            if (previousValue == currentValue && availableCombine) {

                //pop 후 두배로 push
                stack.pop();

                currentValue = currentValue * 2;
                stack.push(currentValue);
                return false;
            }
        }

        stack.push(currentValue);
        return true;
    }
}
