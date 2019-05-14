import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.time.*;

    public class Main {
        private int n;
        private Scanner sc = new Scanner(System.in);

        public void zadacha1() {
            int[][] a = {{0, 0, 0, 0, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {0, 1, 1, 1, 0}, {1, 0, 0, 0, 0}};
            int m = 5, n = 5;
            int col = 0, i, j, i1, j1, tmp, tmp1;
            int i_start = 0, i_end = 0, j_start = 0, j_end = 0;
            for (i = 0; i < m; i++)
                for (j = 0; j < n; j++) {
                    if (a[i][j] != 0) {
                        tmp = 0;
                        for (j1 = j; j1 < n && a[i][j1] != 0; j1++) {

                            tmp1 = 0;
                            for (i1 = i; i1 < m && a[i1][j1] != 0; i1++)
                                tmp1++;
                            if (tmp == 0)
                                tmp = tmp1;
                            else {
                                if (tmp > tmp1)
                                    tmp = tmp1;
                            }
                            if (col < tmp * (j1 - j + 1)) {
                                col = tmp * (j1 - j + 1);
                                i_start = i;
                                j_start = j;
                                i_end = i1;
                                j_end = j1 + 1;
                            }
                        }
                    }
                }
            System.out.println("Максимально можлива площа сараю = " + col);
            for (i = 0; i < n; i++) {
                for (j = 0; j < m; j++) {
                    if (i >= i_start && i < i_end && j >= j_start && j < j_end)
                        System.out.print("©");
                    else System.out.print(a[i][j]);
                }
                System.out.print("\n");
            }
        }

        public void zadacha3() {
            // Wagner and Fischer
            int[][] d = new int[50][50];
            String s = "ptslddf";
            String t = "tsgldds";

            char[] u = s.toCharArray();
            char[] v = t.toCharArray();

            for (int i = 0; i <= u.length; i++) {
                d[i][0] = i;
            }
            for (int j = 0; j <= v.length; j++) {
                d[0][j] = j;
            }

            for (int j = 0; j < u.length; j++) {
                for (int i = 0; i < v.length; i++) {
                    int tracker = u[j] == v[i] ? 0 : 1;
                    int temp = Math.min(d[i][j + 1] + 1, d[i + 1][j] + 1);
                    d[i + 1][j + 1] = Math.min(temp, d[i][j] + tracker);
                }
            }

            System.out.println("D(x, y) мінімальне число вставок, видалень і замін символу, яке необхідно для перетворення x в y = " + d[v.length][u.length]);

        }

        public void zadacha8() throws ParseException {
            int current_num = 0, max_num = 0;
            System.out.println("Введіть кількість відвідувачів");
            int kilk_vidviduva4iv = sc.nextInt();
            Date[] chas_vhodu = new Date[kilk_vidviduva4iv];
            Date[] chas_vyhodu = new Date[kilk_vidviduva4iv];
            SimpleDateFormat time_format = new SimpleDateFormat("HH:mm");
            for (int i = 0; i < kilk_vidviduva4iv; i++) {
                System.out.println("Введіть час входу відвідувача " + i);
                chas_vhodu[i] = time_format.parse(sc.next());
                System.out.println("Введіть час виходу відвідувача " + i);
                chas_vyhodu[i] = time_format.parse(sc.next());
            }
            Date temp_date = new Date();
            for (int i = 0; i < 24; i++) {
                for (int j = 0; j < 60; j++) {
                    temp_date = time_format.parse(i + ":" + j);
                    for (int k = 0; k < kilk_vidviduva4iv; k++) {
                        if (chas_vhodu[k].before(temp_date) && chas_vyhodu[k].after(temp_date))
                            current_num++;
                    }
                    if (max_num < current_num)
                        max_num = current_num;
                    current_num = 0;
                }
            }
            System.out.println("Найбільша кількість відвідувачів " + max_num);


        }

        public void zadacha6() {
            System.out.println("Введіть кількість каменів");
            n = sc.nextInt();
            int[] lot = new int[n];
            create_lot(lot);
            arrange_lot(lot);
            arrange_bags(lot);
        }

        public void zadacha9() {
            int[] a = {0, 1, 2, 3, 4, 5, 6};
            System.out.println("Введіть k");
            int k = sc.nextInt();
            moveRight(a, k);
            for (int el : a) {
                System.out.print(el + " ");
            }
        }

        public static void moveRight(int[] array, int positions) {
            int size = array.length;

            for (int i = 0; i < positions; i++) {
                int temp = array[size - 1];

                for (int j = size - 1; j > 0; j--) {
                    array[j] = array[j - 1];
                }

                array[0] = temp;
            }
        }


        public static void main(String[] args) throws ParseException {
          new Main().zadacha1(); // solved
          new Main().zadacha3(); // solved
          new Main().zadacha6(); // solved
          new Main().zadacha8(); // solved
          new Main().zadacha9(); // solved
        }

        void create_lot(int[] stones) //створення масивів всіх каменів
        {
            System.out.println(n + " каменів у купі. Введіть їх вагу");
            for (int i = 0; i < n; i++) {
                System.out.println("Камінь №" + (i + 1) + " його вага:");
                stones[i] = sc.nextInt();
            }
        }

        void arrange_lot(int[] stones) //сортуємо
        {
            int i, j, cur;
            for (i = 0; i < n - 1; i++)
                for (j = i + 1; j < n; j++)
                    if (stones[i] < stones[j]) {
                        cur = stones[i];
                        stones[i] = stones[j];
                        stones[j] = cur;
                    }
        }

        void arrange_bags(int[] stones) //створення двух куп, схожих по вазі
        {
            int sum1 = 0, sum2 = 0, k1 = 0, k2 = 0, i;
            int[] bag1 = new int[n - 1];
            int[] bag2 = new int[n - 1];
            for (i = 0; i < n; i++) {
                if (sum1 <= sum2) {
                    bag1[k1] = stones[i];
                    sum1 += stones[i];
                    k1++;
                } else {
                    bag2[k2] = stones[i];
                    sum2 += stones[i];
                    k2++;
                }
            }
            if (1.0 * sum1 / sum2 <= 2.0) {
                System.out.println("\nКупа 1: ");
                for (i = 0; i < k1; i++)
                    System.out.print(bag1[i] + " ");
                System.out.println("Вага 1: " + sum1 + "");

                System.out.println("\nКупа 2: ");
                for (i = 0; i < k2; i++)
                    System.out.print(bag2[i] + " ");
                System.out.println("Вага 2: " + sum2 + "");
            } else System.out.println("Вага має дуже велику різницю");
            ;
        }

    }

