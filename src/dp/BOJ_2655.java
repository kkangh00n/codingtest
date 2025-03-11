package dp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ_2655 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        List<Block> blocks = new ArrayList<>();
        int[] dyHeight = new int[N];

        ArrayList<Block>[] tops = new ArrayList[N];
        for (int i = 0; i < N; i++) {
            tops[i] = new ArrayList<>();
        }

        for (int i = 0; i < N; i++) {
            int extent = sc.nextInt();
            int height = sc.nextInt();
            int weight = sc.nextInt();

            blocks.add(new Block(i+1, extent, height, weight));
        }

        //넓이 오름차순 정렬
        Collections.sort(blocks);

        dyHeight[0] = blocks.get(0).height;
        tops[0].add(blocks.get(0));

        for (int i = 1; i < N; i++) {
            //현재 블록
            Block currentBlock = blocks.get(i);
            dyHeight[i] = currentBlock.height;
            tops[i].add(currentBlock);
            for (int j = 0; j < i; j++) {
                //이전 블록
                Block previousBlock = blocks.get(j);

                //이전 블록이 현재 블록보다 작다면, 밑에 쌓을 수 있음.
                //현재 블록에 쌓을 수 있는 값 갱신
                if (previousBlock.isSmallBlockThan(currentBlock)) {
                    if (dyHeight[i] < dyHeight[j] + currentBlock.height) {
                        dyHeight[i] = dyHeight[j] + currentBlock.height;
                        tops[i] = new ArrayList<>(tops[j]);
                        tops[i].add(currentBlock);
                    }
                }
            }
        }

        int maxIndex = 0;
        int maxValue = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            if (dyHeight[i] > maxValue) {
                maxIndex = i;
                maxValue = dyHeight[i];
            }
        }

        ArrayList<Block> topBlocks = tops[maxIndex];

        System.out.println(topBlocks.size());
        for (Block topBlock : topBlocks) {
            System.out.println(topBlock.id);
        }
    }
}

class Block implements Comparable<Block> {
    //식별자
    int id;
    //넓이
    int extent;
    //높이
    int height;
    //무게
    int weight;

    Block(int id, int extent, int height, int weight) {
        this.id = id;
        this.extent = extent;
        this.height = height;
        this.weight = weight;
    }

    public boolean isSmallBlockThan(Block block) {
        return this.weight < block.weight;
    }

    @Override
    public int compareTo(Block o) {
        return this.extent - o.extent;
    }
}
