package dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class BOJ_1068 {

    //노드 갯수
    static int N;

    //트리
    static ArrayList<Integer>[] tree;

    //삭제할 노드
    static int deleteNode;

    static int leafCounts;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        //트리 초기화
        tree = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tree[i] = new ArrayList<>();
        }

        StringTokenizer st = new StringTokenizer(br.readLine());

        //트리
        int root = -1;
        for (int i = 0; i < N; i++) {
            int parent = Integer.parseInt(st.nextToken());

            if (parent == -1) {
                root = i;
                continue;
            }

            tree[parent].add(i);
        }

        deleteNode = Integer.parseInt(br.readLine());

        leafCounts = 0;

        if (deleteNode != root) {
            DFS(root);
        }

        System.out.println(leafCounts);

    }

    public static void DFS(int current) {

        //현재 노드의 자식 노드들을 조회
        ArrayList<Integer> childs = tree[current];

        int childCount=0;

        //자식 노드 탐색
        for (Integer child : childs) {

            //만약 자식 노드가 삭제할 노드라면, 스킵
            if (child == deleteNode) {
                continue;
            }

            childCount++;
            DFS(child);
        }

        if (childCount==0) {
            leafCounts++;
        }
    }

}