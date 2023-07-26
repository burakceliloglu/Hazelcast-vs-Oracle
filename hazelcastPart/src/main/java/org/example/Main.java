package org.example;

import com.hazelcast.collection.IList;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.Random;
import java.util.Scanner;


public class Main
{
    static int NUM_OF_ROWS;

    public static void main(String[] args)
    {
        Random randomNumberGenerator = new Random();

        HazelcastInstance hCastInstance = Hazelcast.newHazelcastInstance();
        IList<Integer> randomNumberList = hCastInstance.getList("randomNumberList");

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter how many rows will be inserted: ");
        NUM_OF_ROWS=scanner.nextInt();

        // INSERTING INTO HAZELCAST
        long startTimeHazelcastAdd = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_ROWS; i++){
            randomNumberList.add(randomNumberGenerator.nextInt());
        }
        long endTimeHazelcastAdd = System.currentTimeMillis();

        // GETTING FROM HAZELCAST
        long startTimeHazelcastGet = System.currentTimeMillis();
        for (int i = 0; i < NUM_OF_ROWS; i++){
            randomNumberList.get(i);
        }
        long endTimeHazelcastGet = System.currentTimeMillis();

        randomNumberList.destroy();
        hCastInstance.shutdown();

        System.out.println("The runtime for Hazelcast insert is: " + (endTimeHazelcastAdd - startTimeHazelcastAdd));
        System.out.println("The runtime for Hazelcast get is: " + (endTimeHazelcastGet - startTimeHazelcastGet));
    }
}