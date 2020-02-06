package com.company;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * This is a seat booking system in a theatre using collections framework.
 * Created By Pramod Kondur
 */

public class Main {

    public static void main(String[] args) {
        Theatre theatre = new Theatre("Olympian", 8, 12);

        List<Theatre.Seat> viewSeats = new ArrayList<>(theatre.getSeats());
        Collections.sort(viewSeats);
        printList(viewSeats);

        int userInput;
        Scanner scanner = new Scanner(System.in);

        do {
            System.out.println("Enter option \n 1. Book ticket \n 2. Cancel \n 3. Exit");
            userInput = scanner.nextInt();
            scanner.nextLine();
            if (userInput == 1) {
                System.out.println("ENTER seat number from above list");
                String seat = scanner.nextLine();
                if (theatre.reserveSeat(seat)) {
                    System.out.println("Please pay for " + seat);
                }
//                else {
//                    System.out.println("Seat already reserved");
//                }
            } else if (userInput == 2) {
                System.out.println("ENTER seat");
                String seat = scanner.nextLine();
                theatre.cancelSeat(seat);
            }
        } while (userInput != 3);
        scanner.close();

        System.out.println("Performing already defined actions for testing");

        if (theatre.reserveSeat("D12")) {
            System.out.println("Please pay for D12");
        } else {
            System.out.println("Seat already reserved");
        }

        if (theatre.reserveSeat("D12")) {
            System.out.println("Please pay for D12");
        } else {
            System.out.println("Seat already reserved");
        }

        if (theatre.reserveSeat("B13")) {
            System.out.println("Please pay for B13");
        } else {
            System.out.println("Seat already reserved");
        }

        List<Theatre.Seat> priceSeats = new ArrayList<>(theatre.getSeats());
        priceSeats.add(theatre.new Seat("B00", 13.00));
        priceSeats.add(theatre.new Seat("A00", 13.00));
        Collections.sort(priceSeats, Theatre.PRICE_ORDER);

        printList(priceSeats);
    }


    public static void printList(List<Theatre.Seat> list) {
        System.out.println("Seats sorted in price order");
        for (Theatre.Seat seat : list) {
            System.out.print(" " + seat.getSeatNumber() + " $" + seat.getPrice());
        }
        System.out.println();
        System.out.println("======================================================================");
    }
}
