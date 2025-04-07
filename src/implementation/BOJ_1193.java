package implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ_1193 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int num1 = 1;
        int num2 = 1;

        if (N == 1) {
            System.out.println(num1 + "/" + num2);
            return;
        }

        for (int i = 1; i < N; i++) {
            if (num1 == 1) {
                if (num2%2 != 0) {
                    num2++;
                } else {
                    num1++;
                    num2--;
                }
            }
            else if (num2 == 1) {
                if (num1%2 != 0) {
                    num1--;
                    num2++;
                }
                else {
                    num1++;
                }
            }
            else {
                if ((num1+num2)%2==0) {
                    num1--;
                    num2++;
                }
                else {
                    num1++;
                    num2--;
                }
            }
        }

        System.out.println(num1 + "/" + num2);



    }
}
