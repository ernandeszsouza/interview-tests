package com.ernandes;

import java.util.List;

public class Main {


    public static void main(String[] args) {

        var test1Result = test1();
        var test2Result = test2();
        var test3Result = test3();

        System.out.println("test 1 result " + test1Result);
        System.out.println("test 2 result " + test2Result);
        System.out.println("test 3 result " + test3Result);
    }


    public static boolean test1(){
        var t1 = new Transaction(1000, 500);
        var t2 = new Transaction(1500, 700);
        var t3 = new Transaction(1800, 400);
        var t4 = new Transaction(4000, 200);

        var transactions = List.of(t1, t2, t3, t4);

        var minTransactions = 3;
        var timeWindowMillis = 1000;
        var amountThreshold = 1500;

        return hasSuspiciousBurst(transactions, minTransactions, timeWindowMillis, amountThreshold);
    }

    public static boolean test2(){

        var t1 = new Transaction(100, 100);
        var t2 = new Transaction(200, 100);
        var t3 = new Transaction(300, 100);
        var t4 = new Transaction(500, 100);

        var transactions = List.of(t1, t2, t3, t4);

        var minTransactions = 3;
        var timeWindowMillis = 500;
        var amountThreshold = 500;

        return hasSuspiciousBurst(transactions, minTransactions, timeWindowMillis, amountThreshold);
    }

    public static boolean test3(){
        var t1 = new Transaction(1000, 10);
        var t2 = new Transaction(5000, 600);
        var t3 = new Transaction(5200, 600);
        var t4 = new Transaction(5400, 600);

        var transactions = List.of(t1, t2, t3, t4);

        var minTransactions = 3;
        var timeWindowMillis = 500;
        var amountThreshold = 1500;

        return hasSuspiciousBurst(transactions, minTransactions, timeWindowMillis, amountThreshold);
    }

    public static boolean hasSuspiciousBurst(List<Transaction> transactions,
                                             int minTransactions, long timeWindowMillis,
                                             long amountThreshold) {

        var totalAmount = transactions.getFirst().amount;
        var point2 = 1;

        for (int point1 = 0; point1 < transactions.size(); point1++) {

            while (shouldMovePoint2(transactions, timeWindowMillis, point1, point2, minTransactions, transactions.size())) {
                totalAmount = totalAmount + transactions.get(point2).amount;
                if (totalAmount >= amountThreshold) {
                    return true;
                }
                point2++;
            }

            totalAmount = totalAmount - transactions.get(point1).amount;
        }

        return false;
    }

    private static boolean shouldMovePoint2(List<Transaction> transactions, long timeWindowMillis,
                                            int point1, int point2, int minTransactions, int listSize) {
        if (point2 == listSize) {
            return false;
        }

        var timestamp = transactions.get(point1).timestamp + transactions.get(point2).timestamp;
        var numberOfTransaction = point2 - point1;

        return timestamp >= timeWindowMillis || numberOfTransaction >= minTransactions;
    }
}
















