package com.mysql.VotingSystem;

import java.util.Scanner;
import java.sql.SQLException;

public class VoterDetails {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); 

        System.out.print("Enter voter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter voter password: ");
        String password = scanner.nextLine();
        System.out.print("Enter voter name: ");
        String name = scanner.nextLine();
        System.out.print("Enter voter email: ");
        String email = scanner.nextLine();

        UserAuthentication auth = new UserAuthentication();
        try {
            auth.insertVoter(username, password, name, email);
            System.out.println("Voter details inserted successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
