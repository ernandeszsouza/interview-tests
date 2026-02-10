package com.ernandes;

public class Transaction {

    final long timestamp; // epoch millis

    final long amount;    // positive amount in cents


    Transaction(long timestamp, long amount) {

        this.timestamp = timestamp;

        this.amount = amount;

    }

}