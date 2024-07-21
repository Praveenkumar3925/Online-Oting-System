package com.mysql.VotingSystem;

import java.util.Scanner;
import java.sql.SQLException;

public class PollDetails {
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter poll title: ");
        String title = scanner.nextLine();
        System.out.print("Enter poll description: ");
        String description = scanner.nextLine();
        System.out.print("Enter poll start date (YYYY-MM-DD): ");
        String startDate = scanner.nextLine();
        System.out.print("Enter poll end date (YYYY-MM-DD): ");
        String endDate = scanner.nextLine();

        UserAuthentication auth = new UserAuthentication();
        try {
            auth.insertPoll(title, description, startDate, endDate);
            System.out.println("Poll details inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
