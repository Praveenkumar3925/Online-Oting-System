package com.mysql.VotingSystem;

import java.sql.*;

public class UserAuthentication {
    private Connection connect() throws SQLException {
        return DatabaseConnection.getConnection();
    }

    public void insertVoter(String username, String password, String name, String email) throws SQLException {
        String query = "INSERT INTO voters (username, password, name, email) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, name);
            pstmt.setString(4, email);
            pstmt.executeUpdate();
        }
    }

    public void insertPoll(String title, String description, String startDate, String endDate) throws SQLException {
        String query = "INSERT INTO polls (title, description, start_date, end_date) VALUES (?, ?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, title);
            pstmt.setString(2, description);
            pstmt.setDate(3, Date.valueOf(startDate));
            pstmt.setDate(4, Date.valueOf(endDate));
            pstmt.executeUpdate();
        }
    }

    public void insertVote(int pollId, int voterId, String optionChosen) throws SQLException {
        String query = "INSERT INTO votes (poll_id, voter_id, option_chosen) VALUES (?, ?, ?)";
        try (Connection conn = connect(); PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, pollId);
            pstmt.setInt(2, voterId);
            pstmt.setString(3, optionChosen);
            pstmt.executeUpdate();
        }
    }

    public ResultSet loginUser(String username, String password) throws SQLException {
        String query = "SELECT * FROM voters WHERE username = ? AND password = ?";
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setString(1, username);
        pstmt.setString(2, password);
        return pstmt.executeQuery();
    }

    public ResultSet getVoteDetails(int voterId) throws SQLException {
        String query = "SELECT v.poll_id, p.title, p.description, p.start_date, p.end_date, v.option_chosen, v.vote_time " +
                       "FROM votes v " +
                       "JOIN polls p ON v.poll_id = p.poll_id " +
                       "WHERE v.voter_id = ?";
        Connection conn = connect();
        PreparedStatement pstmt = conn.prepareStatement(query);
        pstmt.setInt(1, voterId);
        return pstmt.executeQuery();
    }
}
