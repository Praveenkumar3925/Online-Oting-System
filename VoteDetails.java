package com.mysql.VotingSystem;

import java.util.Scanner;
import java.sql.SQLException;

public class VoteDetails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Enter poll ID: ");
        int pollId = scanner.nextInt();
        System.out.print("Enter voter ID: ");
        int voterId = scanner.nextInt();
        scanner.nextLine(); // consume newline
        System.out.print("Enter option chosen: ");
        String optionChosen = scanner.nextLine();

        UserAuthentication auth = new UserAuthentication();
        try {
            auth.insertVote(pollId, voterId, optionChosen);
            System.out.println("Vote details inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
