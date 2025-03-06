package dijkstra;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ_1916 {

    static ArrayList<Bus>[] buses;
    static int[] cityCost;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int cityCount = sc.nextInt();
        int busCount = sc.nextInt();

        cityCost = new int[cityCount + 1];
        for (int i = 0; i < cityCost.length; i++) {
            cityCost[i] = Integer.MAX_VALUE;
        }
        visited = new boolean[cityCount+1];

        buses = new ArrayList[cityCount + 1];
        for (int i = 0; i < cityCount+1; i++) {
            buses[i] = new ArrayList<>();
        }

        for (int i = 0; i < busCount; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int cost = sc.nextInt();

            buses[from].add(new Bus(to, cost));
        }

        int from = sc.nextInt();
        int to = sc.nextInt();

        solution(from);

        System.out.println(cityCost[to] - cityCost[from]);
    }

    public static void solution(int from) {
        PriorityQueue<Bus> queue = new PriorityQueue<>();
        cityCost[from] = 0;
        queue.add(new Bus(from, 0));

        while(!queue.isEmpty()) {
            Bus bus = queue.poll();
            int city = bus.city;
            int cost = bus.cost;

            if (visited[city]) {
                continue;
            }

            visited[city] = true;

            ArrayList<Bus> nextBuses = buses[city];
            for (Bus nextBus : nextBuses) {
                int nextCity = nextBus.city;
                int nextCityCost = nextBus.cost + cost;

                cityCost[nextCity] = Math.min(cityCost[nextCity], nextCityCost);
                queue.add(new Bus(nextCity, cityCost[nextCity]));
            }
        }
    }
}

class Bus implements Comparable<Bus>{
    int city;
    int cost;

    Bus(int city, int cost){
        this.city = city;
        this.cost = cost;
    }


    @Override
    public int compareTo(Bus o) {
        return this.cost - o.cost;
    }
}
