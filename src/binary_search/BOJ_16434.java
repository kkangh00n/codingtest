package binary_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 1. 용사는 능력치를 갖고있다. - 최대 체력 - 현재 체력 - 현재 공격력 2. 몬스터 방에 입장할 경우, 다음 행동이 이어진다. - 용사는 공격력만큼 몬스터의 생명을
 * 깎는다. 이 때 몬스터의 체력이 0 이하기 되면 용사는 다음 방으로 이동한다. - 몬스터의 공격력만큼 용사의 생명을 깎는다. 이 때 용사의 체력이 0 이하가 되면 용사는
 * 사망한다. 3. 포션 방에 입장할 경우, 현재 체력이 일정량 회복되고, 공격력도 일정량 증가한다. - 현재 체력은 최대 체력을 넘길 수 없다.
 * <p>
 * 입력 - 방의 갯수 N, 용사의 초기 공격력 - 방의 정보 (t,a,h) - t가 1인 경우, 공격력 a이며 생명력이 h인 몬스터 있음. - t가 2인 경우 용사의 공격력을
 * a만큼 증가시키고 현재 체력을 h만큼 증가시키는 포션이 있음.
 * <p>
 * 출력 N번째 방에 있는 용을 쓰러뜨리기 위한
 * <p>
 * 풀이 이분탐색(?) 최소 체력 1 최대 체력
 */
public class BOJ_16434 {

    static long userAttack;
    static List<Room> rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long roomCount = Integer.parseInt(st.nextToken());
        userAttack = Integer.parseInt(st.nextToken());

        rooms = new ArrayList<>();


        for (int i = 0; i < roomCount; i++) {
            st = new StringTokenizer(br.readLine());

            long roomType = Integer.parseInt(st.nextToken());
            long attack = Integer.parseInt(st.nextToken());
            long health = Integer.parseInt(st.nextToken());

            rooms.add(new Room(roomType, attack, health));
        }

        //이분 탐색
        long left = 1;
        long right = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;

            //중간 값으로 현재 맵을 클리어 할 수 있다면,
            if (clear(mid)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        System.out.println(left);
    }

    public static boolean clear(long maxHealth) {
        //현재 용사의 공격력과 체력
        long currentAttack = userAttack;
        long currentHealth = maxHealth;

        //방을 탐색한다.
        for (Room room : rooms) {

            //포션 방일 경우,
            if (room.roomType == 2) {

                long upgradeAttack = room.attack;
                long upgradeHealth = room.health;

                //유저의 공격력과 체력을 증가
                currentAttack = currentAttack + upgradeAttack;
                currentHealth = Math.min(currentHealth + upgradeHealth, maxHealth);

                continue;
            }

            //몬스터 방일 경우
            long monsterAttack = room.attack;
            long monsterHealth = room.health;

            //용사가 때리는 횟수
            long heroHitCount = (monsterHealth + currentAttack - 1)/currentAttack;
            //용사가 받는 데미지
            long takenDamage = (heroHitCount-1)*monsterAttack;

            currentHealth -= takenDamage;

            if (currentHealth <=0) {
                return false;
            }
        }

        return true;
    }
}

class Room {

    long roomType;
    long attack;
    long health;

    public Room(long roomType, long attack, long health) {
        this.roomType = roomType;
        this.attack = attack;
        this.health = health;
    }
}
