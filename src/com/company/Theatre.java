package com.company;

import java.util.*;

public class Theatre {
    private final String theatreName;
    private List<Seat> seats = new ArrayList<>();

    /**
     * This method sorts the seats based on price using comparator
     * */

    static final Comparator<Seat> PRICE_ORDER;
    static {
        PRICE_ORDER = new Comparator<Seat>() {
            @Override
            public int compare(Seat seat1, Seat seat2) {
                if (seat1.getPrice() < seat2.getPrice()) {
                    return -1;
                } else if (seat1.getPrice() > seat2.getPrice()) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
    }

    /**
     * This is a constructor and initializes the price of the seats using the param entered
     * @param theatreName - name of theatre
     * @param numRows - number of rows
     * @param seatsPerRow - number of seats per row
     *
     * */

    public Theatre(String theatreName, int numRows, int seatsPerRow) {
        this.theatreName = theatreName;

        int lastRow = 'A' + (numRows -1);
        for (char row = 'A'; row <= lastRow; row++) {
            for(int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                double price = 12.00;

                if((row < 'D') && (seatNum >=4 && seatNum <=9)) {
                    price = 14.00;
                } else if((row > 'F') || (seatNum < 4 || seatNum > 9)) {
                    price = 7.00;
                }

                Seat seat = new Seat(row + String.format("%02d", seatNum), price);
                seats.add(seat);
            }
        }
    }

    public String getTheatreName() {
        return theatreName;
    }

    /**
     * This method is used for seat reservation
     * @param seatNumber - seat to be reserved
     *
     * */

    public boolean reserveSeat(String seatNumber) {
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).reserve();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }
    }

    /**
     * This method is used for seat cancellation
     * @param seatNumber - seat to be cancelled
     *
     * */

    public boolean cancelSeat(String seatNumber){
        Seat requestedSeat = new Seat(seatNumber, 0);
        int foundSeat = Collections.binarySearch(seats, requestedSeat, null);
        if (foundSeat >= 0) {
            return seats.get(foundSeat).cancel();
        } else {
            System.out.println("There is no seat " + seatNumber);
            return false;
        }

    }

    public Collection<Seat> getSeats() {
        return seats;
    }

    /**
     * This is an inner class for seat
     * @author - Pramod Kondur
     *
     * */

    public class Seat implements Comparable<Seat> {
        private final String seatNumber;
        private double price;
        private boolean reserved = false;

        public Seat(String seatNumber, double price) {
            this.seatNumber = seatNumber;
            this.price = price;
        }

        @Override
        public int compareTo(Seat seat) {
            return this.seatNumber.compareToIgnoreCase(seat.getSeatNumber());
        }

        public boolean reserve() {
            if(!this.reserved) {
                this.reserved = true;
                System.out.println("Seat " + seatNumber + " reserved");
                return true;
            } else {
                System.out.println("Seat " + seatNumber + " already reserved");
                return false;
            }
        }

        public boolean cancel() {
            if(this.reserved) {
                this.reserved = false;
                System.out.println("Reservation of seat " + seatNumber + " cancelled");
                return true;
            } else {
                System.out.println("Seat to be cancelled " + seatNumber + " is not reserved");
                return false;
            }
        }

        public String getSeatNumber() {
            return seatNumber;
        }

        public double getPrice() {
            return price;
        }
    }
}
