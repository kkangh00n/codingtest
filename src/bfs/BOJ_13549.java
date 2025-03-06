package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_13549 {

    static int[] dis;
    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();

        dis = new int[100001];
        for (int i = 0; i < dis.length; i++) {
            dis[i] = Integer.MAX_VALUE;
        }

        BFS(N, K);

        System.out.println(answer);
    }

    public static void BFS(int N, int K) {

        //현재 위치, 다음 위치, 다음 위치까지 걸리는 시간
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        dis[N] = 0;

        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();

            if (currentIndex == K) {
                answer = dis[currentIndex];
                break;
            }

            int next1Index = currentIndex - 1;
            if (next1Index >= 0 && dis[next1Index] > dis[currentIndex] + 1) {
                dis[next1Index] = dis[currentIndex] + 1;
                queue.add(next1Index);
            }

            int next2Index = currentIndex + 1;
            if (next2Index <= 100000 && dis[next2Index] > dis[currentIndex] + 1) {
                dis[next2Index] = dis[currentIndex] + 1;
                queue.add(next2Index);
            }

            int next3Index = currentIndex * 2;
            if (next3Index <= 100000 && dis[next3Index] > dis[currentIndex]) {
                dis[next3Index] = dis[currentIndex];
                queue.add(next3Index);
            }
        }


    }
}
