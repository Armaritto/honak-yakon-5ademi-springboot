package com.stgsporting.honakyakon5ademi.config;

public class DatabaseEnum {
    // Base ID for all tables
    public static final String baseId = "id";

    // USERS TABLE
    public static final String usersTable = "USERS";
    public static final String username = "username";
    public static final String password = "password";
    public static final String khedmaId = "khedmaId";

    // ADMINS TABLE
    public static final String adminsTable = "ADMINS";
    public static final String role = "role";


    // KHEDMA TABLE
    public static final String khedmaTable = "KHEDMAS";
    public static final String name = "name";

    // QUESTIONS TABLE
    public static final String questionsTable = "QUESTIONS";
    public static final String text = "text";
    public static final String type = "type";

    // QUIZZES TABLE
    public static final String quizTable = "QUIZZES";
    public static final String quizId = "quizId";
    public static final String date = "date";
    public static final String checkbox = "checkbox";

    // RESPONSES TABLE
    public static final String responsesTable = "RESPONSES";
    public static final String userId = "userId";

    //ANSWERS TABLE
    public static final String answersTable = "ANSWERS";
    public static final String responseId = "responseId";
    public static final String questionId = "questionId";
    public static final String answer = "answer";
}
