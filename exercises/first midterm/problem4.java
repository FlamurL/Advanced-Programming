import java.util.*;
import java.io.*;

import java.util.Scanner;

class MinMax<T extends Comparable<T>>{
    T min;
    T max;
    int tmpMinCnt = 1;
    int tmpMaxCnt = 1;
    int cnt = 0;
    public MinMax() {
        min = null;
        max = null;
    }

    void update(T element)
    {  if (min == null) {

        min = element;
        max = element;

    }

    else{
        int diffMin = element.compareTo(min);
        int diffMax = element.compareTo(max);
        boolean is = false;
        if (diffMin == 0){
            tmpMinCnt++;
            is = true;
        }
        if (diffMax == 0){
            tmpMaxCnt++;
            is = true;
        }
        if (!is){
            if (diffMin < 0) {
                if (max != min) cnt += tmpMinCnt;
                tmpMinCnt = 1;
                min = element;
            }
            else if (diffMax > 0){
                if (max != min) cnt += tmpMaxCnt;
                tmpMaxCnt = 1;
                max = element;
            }
            else cnt++;
        }
    }

    }
    @Override
    public String toString(){
        return String.format("%s %s %s\n", min, max, cnt);
    }
    T max(){
        return max;
    }
    T min(){
        return min;
    }

}

public class MinAndMax {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        MinMax<String> strings = new MinMax<String>();
        for(int i = 0; i < n; ++i) {
            String s = scanner.next();
            strings.update(s);
        }
        System.out.println(strings);
        MinMax<Integer> ints = new MinMax<Integer>();
        for(int i = 0; i < n; ++i) {
            int x = scanner.nextInt();
            ints.update(x);
        }
        System.out.println(ints);
    }
}