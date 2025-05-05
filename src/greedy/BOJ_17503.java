package greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * N : 축제 기간
 * M : 선호도 합
 * K : 맥주 종류의 수
 *
 * 맥주 선호도, 도수 레벨
 *
 * 도수 레벨이 간 레벨보다 높으면 기절함
 * 선호도를 만족할 수 있는 간 레벨의 최소를 구한다.
 */
public class BOJ_17503 {

    static int totalDay, totalPreference;
    static List<Beer> beers;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>();

        //축제가 열리는 기간
        totalDay = Integer.parseInt(st.nextToken());

        //채워야 하는 선호도 합
        totalPreference = Integer.parseInt(st.nextToken());

        //맥주 종류의 수
        int beerCount = Integer.parseInt(st.nextToken());

        beers = new ArrayList<>();

        //맥주 정보 저장
        for (int i = 0; i < beerCount; i++) {
            st = new StringTokenizer(br.readLine());

            int preference = Integer.parseInt(st.nextToken());
            int alcohol = Integer.parseInt(st.nextToken());

            beers.add(new Beer(preference, alcohol));
        }

        //맥주 도수 오름차순 정렬
        Collections.sort(beers);

        int total = 0;
        int answer = -1;

        //맥주를 도수 적은 것부터 순회한다.
        for (int i = 0; i < beers.size(); i++) {
            Beer beer = beers.get(i);

            //맥주의 선호도를 누적시킨다.
            total += beer.preference;
            queue.add(beer.preference);

            //만약 맥주의 축제 일수를 넘어선다면, 도수가 작은 것을 제거한다.
            if (queue.size() > totalDay) {
                total -= queue.poll();
            }

            //맥주를 하루에 한잔 씩 먹을 떄, 선호도를 충족할 수 있다면
            if (queue.size() == totalDay && total >= totalPreference) {
                answer = beer.alcohol;
                break;
            }
        }

        System.out.println(answer);
    }
}

class Beer implements Comparable<Beer>{
    int preference;
    int alcohol;

    public Beer(int preference, int alcohol) {
        this.preference = preference;
        this.alcohol = alcohol;
    }

    @Override
    public int compareTo(Beer o) {
        if (this.alcohol == o.alcohol) {
            return this.preference - o.preference;
        }
        return this.alcohol - o.alcohol;
    }
}
