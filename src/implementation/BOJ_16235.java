package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * N*N 크기의 땅을 구매
 * M개의 나무를 심음
 * K년 반복
 *
 * 봄
 * - 나무가 자신의 나이만큼 각 칸에 있는 양분을 먹음 -> 나이 1 증가
 * - 어린 나무부터 양분을 먹는다.
 * - 양분이 부족해 먹을 수 없는 나무는 죽는다.
 * 여름
 * - 죽은 나무가 양분으로 변한다.
 * - 양분 값은 죽은 나무의 나이/2 만큼 추가된다
 * 가을
 * - 나무의 나이가 5의 배수라면 나무는 8개 방향으로 번식한다.
 * - 새로 생긴 나무는 나이가 1이다.
 * 겨울
 * - 각 땅의 양분을 추가한다 -> 양분의 배열 내 수만큼 추가된다.
 *
 * 입력
 * N, M, K
 * 땅 배열
 * 나무의 위치 & 나이
 *
 */
public class BOJ_16235 {

    static int[][] moved = new int[][]{{0, 1},{0, -1},{1, 0},{-1, 0},
                                        {-1, -1},{-1, 1},{1, -1},{1, 1}};

    static PriorityQueue<Tree> priorityQueue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        priorityQueue = new PriorityQueue<>();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        //땅 배열
        int[][] A = new int[N + 1][N + 1];

        //매년 추가될 양분
        int[][] sampleA = new int[N + 1][N + 1];

        //땅 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                int value = Integer.parseInt(st.nextToken());
                A[i][j] = 5;
                sampleA[i][j] = value;
            }
        }

        //나무 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int age = Integer.parseInt(st.nextToken());

            //
            priorityQueue.add(new Tree(x, y, age));
        }

        //K년 반복
        while(K-->0) {
            //봄에 죽지 않은 나무 및 올해 새로 생긴 나무
            List<Tree> aliveTrees = new ArrayList<>();
            List<Tree> deadTrees = new ArrayList<>();

            //봄
            while(!priorityQueue.isEmpty()) {
                Tree tree = priorityQueue.poll();

                //나무가 자신의 나이만큼 각 칸에 있는 양분을 먹음 -> 나이 1 증가
                if (A[tree.x][tree.y]>=tree.age) {
                    A[tree.x][tree.y] -= tree.age++;

                    aliveTrees.add(tree);
                }
                //양분이 부족해 먹을 수 없는 나무는 죽는다.
                else {
                    deadTrees.add(tree);
                }
            }

            //여름
            //죽은 나무들은 양분으로 변한다.
            for (int d = 0; d < deadTrees.size(); d++) {
                //양분 값은 죽은 나무의 나이/2 만큼 추가된다
                Tree deadTree = deadTrees.get(d);
                A[deadTree.x][deadTree.y] += deadTree.age/2;

            }

            //가을
            int total = aliveTrees.size();

            for (int i = 0; i < total; i++) {
                Tree newTree = aliveTrees.get(i);

                //나무의 나이가 5의 배수라면
                if (newTree.age%5==0) {
                    //나무는 8개 방향으로 번식한다.
                    for (int[] m : moved) {

                        int nextX = newTree.x + m[0];
                        int nextY = newTree.y + m[1];

                        //번식 시 지도의 범위를 벗어나면 continue
                        if (nextX<=0||nextX>N||nextY<=0||nextY>N) {
                            continue;
                        }

                        //새로 생긴 나무는 나이가 1이다.
                        aliveTrees.add(new Tree(nextX, nextY, 1));
                    }
                }
            }

            //겨울
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    //각 땅의 양분을 추가한다 -> 양분의 배열 내 수만큼 추가된다.
                    A[i][j] += sampleA[i][j];
                }
            }

            //올해 살아남은 나무를 우선순위 큐에 넣어준다.
            priorityQueue.addAll(aliveTrees);
        }

        System.out.println(priorityQueue.size());

    }
}

class Tree implements Comparable<Tree>{
    int x;
    int y;
    int age;

    Tree(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    @Override
    public int compareTo(Tree o) {
        return this.age - o.age;
    }

}
