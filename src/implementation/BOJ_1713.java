package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ_1713 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        List<Student> list = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        int time = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < time; i++) {
            int current = Integer.parseInt(st.nextToken());

            boolean isHubo = false;

            //현재 후보인지 확인
            for (Student student:list) {
                //현재 후보라면
                if (student.number == current) {
                    isHubo = true;

                    //추천수와 시간 최신화
                    student.count++;
                    break;
                }
            }

            Collections.sort(list);

            //현재 후보가 아니라면
            if (!isHubo){
                //후보의 수가 가득 찼다면
                if (list.size()>=N) {
                    list.remove(0);
                }
                //후보 등록
                list.add(new Student(current, i, 1));
            }
        }

        int[] answer = new int[list.size()];

        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i).number;
        }

        Arrays.sort(answer);

        for (int i = 0; i < answer.length; i++) {
            System.out.print(answer[i] + " ");
        }
    }
}

class Student implements Comparable<Student>{
    int number;
    int time;
    int count;

    public Student(int number, int time, int count) {
        this.number = number;
        this.time = time;
        this.count = count;
    }

    @Override
    public int compareTo(Student o) {
        if (this.count == o.count){
            return this.time - o.time;
        }
        return this.count - o.count;
    }
}
