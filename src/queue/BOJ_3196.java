package queue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_3196 {

    static int[][] moved = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        //뱀의 몸통 순서 기억
        Queue<Point> queue = new LinkedList<>();
        //방향 전환 시간 기억
        Map<Integer, String> map = new HashMap<>();

        //보드 경로
        int N = sc.nextInt();
        //사과 갯수
        int appleCount = sc.nextInt();

        //지도
        int[][] arr = new int[N][N];

        //사과 위치 저장
        for (int i = 0; i < appleCount; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            arr[x-1][y-1] = 2;
        }

        //전환 시간 및 방향 저장
        int movedSequence = sc.nextInt();
        for (int i = 0; i < movedSequence; i++) {
            map.put(sc.nextInt(), sc.next());
        }

        int time = 0;
        //오른쪽에서부터 시작
        int m = 1;

        //머리 시작 위치 저장
        arr[0][0] = 1;
        queue.add(new Point(0, 0));

        int headX = 0;
        int headY = 0;

        while (true) {
            //1. 시간을 더한다
            time++;

            //2. 머리를 이동한다.
            int[] move = moved[m];

            //3. 머리를 이동시킬 때, 게임이 끝나는지 본다
            // - 지도와 부딪히는지
            // - 자기 몸과 부딪히는지
            Point next = new Point(headX + move[0], headY + move[1]);

            if (next.x >= N || next.y >= N || next.x < 0 || next.y < 0) {
                break;
            }
            if (arr[next.x][next.y] == 1) {
                break;
            }

            //4. 이동할 위치에 사과가 존재하는지 본다.
            boolean existsApple = arr[next.x][next.y] == 2;

            //5. 머리를 이동한다.
            arr[next.x][next.y] = 1;
            queue.add(next);
            headX = next.x;
            headY = next.y;

            //6. 사과 유무에 따라 꼬리를 제거한다.
            // - 사과가 존재하면 꼬리가 제거되지 않는다.
            // - 사과가 존재하지 않으면 꼬리가 제거된다.
            if (!existsApple) {
                Point poll = queue.poll();
                arr[poll.x][poll.y] = 0;
            }

            //7. 다음 방향을 설정한다.
            if (map.containsKey(time)) {
                String direction = map.get(time);

                m = (direction.equals("D") ? m+1 : m+3) % 4;
            }
        }

        System.out.println(time);
    }
}

class Point {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
