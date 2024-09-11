
package cinema;

import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int soldTickets = 0;
        int currentIncome = 0;
        int totalIncome = 0;
        int frontRows = 0;
        int backRows = 0;

        // Step 1: Input number of rows and seats
        System.out.print("Enter the number of rows: ");
        int rows = scanner.nextInt();
        System.out.print("Enter the number of seats in each row: ");
        int seats = scanner.nextInt();

        char[][] room = new char[rows][seats];

        // Initialize the seating room
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < seats; j++) {
                room[i][j] = 'S';  // 'S' means available
            }
        }

        // Calculate totalIncome based on seat configuration
        int totalSeats = seats * rows;
        if (totalSeats <= 60) {
            totalIncome = totalSeats * 10;
        } else {
            frontRows = rows / 2;
            backRows = rows - frontRows;
            totalIncome = (frontRows * seats * 10) + (backRows * seats * 8);
        }

        while (true) {
            // Display the menu
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    // Display seating chart
                    System.out.println("Cinema:");
                    System.out.print("  "); // Print empty space for alignment
                    for (int j = 1; j <= seats; j++) {
                        System.out.print(j + " ");
                    }
                    System.out.println();
                    for (int i = 0; i < rows; i++) {
                        System.out.print((i + 1) + " ");
                        for (int j = 0; j < seats; j++) {
                            System.out.print(room[i][j] + " ");
                        }
                        System.out.println();
                    }
                    break;

                case 2:
                    // Buying a ticket
                    boolean validInput = false;

                    while (!validInput) {
                        System.out.println("Enter a row number:");
                        int rowNumber = scanner.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        int seatNumber = scanner.nextInt();

                        // Check if the row and seat numbers are within valid bounds
                        if (rowNumber < 1 || rowNumber > rows || seatNumber < 1 || seatNumber > seats) {
                            System.out.println("Wrong input!");
                        } else if (room[rowNumber - 1][seatNumber - 1] == 'B') {
                            System.out.println("That ticket has already been purchased!");
                        } else {
                            // Valid seat and available, calculate ticket price
                            int ticketPrice = 0;
                            if (totalSeats <= 60) {
                                ticketPrice = 10;
                            } else {
                                if (rowNumber <= frontRows) {
                                    ticketPrice = 10;
                                } else {
                                    ticketPrice = 8;
                                }
                            }

                            System.out.println("Ticket price: $" + ticketPrice);
                            room[rowNumber - 1][seatNumber - 1] = 'B'; // Mark the seat as booked
                            soldTickets += 1;
                            currentIncome += ticketPrice;
                            validInput = true;
                        }
                    }
                    break;

                case 3:
                    // Show statistics
                    System.out.println("Number of purchased tickets: " + soldTickets);
                    double percentage = (double) soldTickets / totalSeats * 100;
                    System.out.printf("Percentage: %.2f%%\n", percentage);
                    System.out.println("Current income: $" + currentIncome);
                    System.out.println("Total income: $" + totalIncome);
                    break;

                case 0:
                    // Exit the program
                    return;

                default:
                    System.out.println("Invalid option. Please select a valid menu option.");
            }
        }
    }
}
