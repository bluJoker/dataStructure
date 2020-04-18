package PriorityQueue;

import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.Transaction;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class TopM {
    public static void main(String[] args) throws Exception{
        int M = Integer.parseInt(args[0]);

        MaxPQ<Transaction> maxpq = new MaxPQ<>(M+1);
//        MinPQ<Transaction> minpq = new MinPQ<>(M+1);

        String path = "G:" + File.separator + "ZAIZEN_JAVA" + File.separator + "data"
                + File.separator + "algs4_jar" + File.separator + "algs4-data"
                + File.separator + "tinyBatch.txt";
        InputStream input = new FileInputStream(path);

        //将标准输入流重定向到tinyT.txt文件
        //API: static void setIn(InputStream in)
        //     //Reassigns the "standard" input stream.
        System.setIn(input);

        while (StdIn.hasNextLine()){
            maxpq.insert(new Transaction(StdIn.readLine()));
            if (maxpq.size() > M){
                maxpq.delMax();
            }
        }

        Stack<Transaction> stack = new Stack<>();
        while (!maxpq.isEmpty()){
            stack.push(maxpq.delMax());
        }

        for (Transaction t:
             stack) {
            StdOut.println(t);
        }
    }
}


/**
 * 1. 若使用MaxPQ，则输出为:
 * Turing      1/11/2002    66.10
 * Turing      6/17/1990   644.08
 * Dijkstra    9/10/2000   708.95
 * Dijkstra   11/18/1995   837.42
 * Hoare       8/12/2003  1025.70
 *
 * 2. 若使用MinPQ，则输出为:
 * Thompson    2/27/2000  4747.08
 * vonNeumann  2/12/1994  4732.35
 * vonNeumann  1/11/1999  4409.74
 * Hoare       8/18/1992  4381.21
 * vonNeumann  3/26/2002  4121.85
 *
 * PS：大小比较要考虑大小写
 * Q: MaxPQ第一次删除6元素有点问题导致结果不对？
 * */