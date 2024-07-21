package com.mysql.VotingSystem;

import java.util.Scanner;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        UserAuthentication auth = new UserAuthentication();
        try {
            ResultSet voterDetails = auth.loginUser(username, password);
            if (voterDetails != null && voterDetails.next()) {
                int voterId = voterDetails.getInt("voter_id");
                String voterName = voterDetails.getString("name");
                String voterEmail = voterDetails.getString("email");

                System.out.println("Login successful!");
                System.out.println("Name: " + voterName);
                System.out.println("Email: " + voterEmail);
                System.out.println("Voter ID: " + voterId);
                System.out.println();

                ResultSet voteDetails = auth.getVoteDetails(voterId);
                while (voteDetails.next()) {
                    System.out.println("Poll ID: " + voteDetails.getInt("poll_id"));
                    System.out.println("Title: " + voteDetails.getString("title"));
                    System.out.println("Description: " + voteDetails.getString("description"));
                    System.out.println("Start Date: " + voteDetails.getDate("start_date"));
                    System.out.println("End Date: " + voteDetails.getDate("end_date"));
                    System.out.println("Option Chosen: " + voteDetails.getString("option_chosen"));
                    System.out.println("Vote Time: " + voteDetails.getTimestamp("vote_time"));
                    System.out.println();
                }
            } else {
                System.out.println("Invalid credentials!");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
