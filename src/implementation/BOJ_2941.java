package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_2941 {

    public static final String[] strings = new String[]{"c=", "c-", "dz=", "d-", "lj", "nj", "s=", "z="};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();

        int answer = 0;

        for (String s : strings) {
            //기존 문자열 갯수 - 크로아티아 알파벳을 삭제한 문자열 갯수 = 크로아티아 알파벳 갯수
            answer += (input.length()-input.replaceAll(s, "").length())/s.length();

            //기존 문자열 갯수 교체
            input = input.replaceAll(s, " ");
        }

        input = input.replaceAll(" ", "");
        answer += input.length();

        System.out.println(answer);
    }
}
