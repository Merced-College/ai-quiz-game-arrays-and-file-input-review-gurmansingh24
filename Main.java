// Gurman Dhaliwal
// 5/27/26
// Ai quiz game
// Program Description: This program reads AI quiz questions from a CSV file,
// stores the questions and answer choices in arrays, 
// displays the quiz to the user,checks each answer,
//  and gives a final score with feedback.

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {
    // Stores the total number of quiz questions expected from the CSV file.
    public static final int NUMBER_OF_QUESTIONS = 10;
    // Stores the number of answer choices for each question.
    public static final int NUMBER_OF_CHOICES = 4;

    public static void main(String[] args) {
        // This array stores each quiz question from the CSV file.
        String[] questions = new String[NUMBER_OF_QUESTIONS];
        // This two-dimensional array stores the four answer choices for each question.
        String[][] answers = new String[NUMBER_OF_QUESTIONS][NUMBER_OF_CHOICES];
        // This array stores the correct answer index for each question.
        int[] correctAnswers = new int[NUMBER_OF_QUESTIONS];

        // This method reads the quiz data from the CSV file into the arrays.
        
        readQuizFile(questions, answers, correctAnswers);

        // Scanner is used to get the user's answers from the keyboard.
        Scanner input = new Scanner(System.in);
        // Score keeps track of how many questions the user answers correctly.
        int score = 0;

        // These lines introduce the quiz and explain how to answer.
        System.out.println("Welcome to the AI Quiz Game!");
        System.out.println("Choose the correct answer by entering 1, 2, 3, or 4.\n");

        // This loop goes through each question in the quiz.
        for (int i = 0; i < questions.length; i++) {
            
            // Displays the current question number and question text.
            System.out.println("Question " + (i + 1) + ": " + questions[i]);

            // This loop displays each answer choice for the current quiz question.
            for (int j = 0; j < answers[i].length; j++) {
                System.out.println((j + 1) + ". " + answers[i][j]);
            }

            // Prompts the user to enter their answer.
            System.out.print("Your answer: ");
            
            // The user enters 1-4, but arrays start at 0, so we subtract 1.
            int userAnswer = input.nextInt() - 1;

            // This checks whether the user's answer matches the correct answer.
            if (userAnswer == correctAnswers[i]) {
                // If the answer is correct, the score increases by 1.
                System.out.println("Correct!\n");
                score++;
            } else {
                // If the answer is wrong, the program displays the correct answer.
                System.out.println("Incorrect.");
                System.out.println("The correct answer was: " + answers[i][correctAnswers[i]] + "\n");
            }
        }

        // Displays a message after all quiz questions are finished.
        
        System.out.println("Quiz complete!");
        // Displays the user's final score.
        System.out.println("Your final score is: " + score + " out of " + questions.length);

        // Enhancement: This section calculates the percentage score and gives feedback.
        double percentage = ((double) score / questions.length) * 100;
        System.out.printf("Your percentage score is: %.2f%%\n", percentage);

        if (percentage >= 90) {
            System.out.println("Excellent job! You really know this material.");
        } else if (percentage >= 70) {
            System.out.println("Good job! You understand most of the quiz.");
        } else if (percentage >= 50) {
            System.out.println("You passed, but you may want to review more.");
        } else {
            System.out.println("Keep studying and try again to improve your score.");
        }

        // Closes the Scanner to prevent resource leaks.
        
        input.close();
    }

    public static void readQuizFile(String[] questions, String[][] answers, int[] correctAnswers) {
        try {
            // Creates a File object for the CSV file that stores the quiz data.
            File file = new File("ai_quiz_questions.csv");
            // Scanner reads the contents of the quiz file.
            
            Scanner fileReader = new Scanner(file);

            // Skips the first line because it contains column headers.
            fileReader.nextLine();

            // Index keeps track of which question is being loaded into the arrays.
            int index = 0;

            while (fileReader.hasNextLine() && index < questions.length) {
                String line = fileReader.nextLine();
                String[] data = line.split(",");

                questions[index] = data[0];

                for (int i = 0; i < NUMBER_OF_CHOICES; i++) {
                    answers[index][i] = data[i + 1];
                }

                correctAnswers[index] = 0;
                index++;
            }

            fileReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("The quiz file could not be found.");
        }
    }
}