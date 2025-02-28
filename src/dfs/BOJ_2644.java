package dfs;

import java.util.ArrayList;
import java.util.Scanner;

public class BOJ_2644 {

    static int question2;

    static ArrayList<ArrayList<Integer>> peoples = new ArrayList<>();
    static boolean[] visited;

    static int answer = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int peopleCount = sc.nextInt();

        for (int i = 0; i <= peopleCount; i++) {
            peoples.add(new ArrayList<>());
        }
        visited = new boolean[peopleCount+1];

        int question1 = sc.nextInt();
        question2 = sc.nextInt();

        int relationCount = sc.nextInt();

        for (int i = 0; i < relationCount; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();

            peoples.get(from).add(to);
            peoples.get(to).add(from);
        }

        visited[question1] = true;
        DFS(question1, 0);

        System.out.println(answer==0?-1:answer);
    }

    public static void DFS(int input, int level) {
        if (input == question2) {
            answer = level;
        }
        else {
            for (Integer people : peoples.get(input)) {
                if (!visited[people]) {
                    visited[people] = true;
                    DFS(people, level+1);
                }
            }
        }
    }
}
