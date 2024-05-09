package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_2121 {

    static List<Point> points;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new ArrayList<>();

        StringTokenizer st;

        st = new StringTokenizer(br.readLine());

        //만들고 싶은 직사각형
        int X = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken());

        //좌표 셋팅
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            Point p = new Point(x, y);
            points.add(p);
        }
        //y축 기준으로 좌표 정렬
        Collections.sort(points);

        int answer = 0;

        //하나의 좌표를 왼쪽 아래로 두고
        for (int i = 0; i < N; i++) {
            Point cp = points.get(i);
            Point point1 = new Point(cp.x + X, cp.y);
            Point point2 = new Point(cp.x, cp.y + Y);
            Point point3 = new Point(cp.x + X, cp.y + Y);

            if(find(point1) && find(point2) && find(point3)) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    public static boolean find(Point p) {
        int start = 0;
        int end = N - 1;

        while(start <= end) {
            int middle = (start+end)/2;

            //중간 point get
            Point mP = points.get(middle);

            if(p.y == mP.y && p.x == mP.x) {
                return true;
            }
            //중간 point와 비교하여, 크다면
            else if(p.y>mP.y || (p.y == mP.y && p.x>=mP.x)) {
                start = middle + 1;
            }
            else {
                end = middle - 1;
            }
        }
        return false;
    }
}

class Point implements Comparable<Point> {

    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int compareTo(Point o) {
        if(y == o.y){
            return x-o.x;
        }
        return y - o.y;
    }
}