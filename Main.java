import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.println("\nWelcome " + name + "! 🎉");
        System.out.println("Let's start the quiz...");

        System.out.println("================================");
        System.out.println("      ONLINE QUIZ APPLICATION");
        System.out.println("================================");

        System.out.println("Welcome to the Quiz!");

        int score = 0;
        int correctAnswers = 0;
        int wrongAnswers = 0;

        // Question 1
        System.out.println("\nQuestion 1:");
        System.out.println("Which language is used for Android development?");

        System.out.println("1. Java");
        System.out.println("2. Python");
        System.out.println("3. C++");
        System.out.println("4. HTML");

        System.out.print("Enter your answer: ");
        int answer = scanner.nextInt();
        while (answer < 1 || answer > 4) {
            System.out.print("Invalid choice! Enter a number between 1 and 4: ");
            answer = scanner.nextInt();
        }

        if (answer == 1) {
            System.out.println("Correct answer! 🎉");
            score++;
            correctAnswers++;
        } else {
            System.out.println("Wrong answer!");
            wrongAnswers++;
        }
        // Question 2
        System.out.println("\nQuestion 2:");
        System.out.println("Which keyword is used to create a class in Java?");

        System.out.println("1. function");
        System.out.println("2. class");
        System.out.println("3. create");
        System.out.println("4. new");

        System.out.print("Enter your answer: ");
        answer = scanner.nextInt();
        while (answer < 1 || answer > 4) {
            System.out.print("Invalid choice! Enter a number between 1 and 4: ");
            answer = scanner.nextInt();
        }

        if (answer == 2) {
            System.out.println("Correct answer! 🎉");
            score++;
            correctAnswers++;
        } else {
            System.out.println("Wrong answer!");
            wrongAnswers++;
        }
        // Question 3
        System.out.println("\nQuestion 3:");
        System.out.println("Which method is the starting point of a Java program?");

        System.out.println("1. start()");
        System.out.println("2. run()");
        System.out.println("3. main()");
        System.out.println("4. begin()");

        System.out.print("Enter your answer: ");
        answer = scanner.nextInt();
        while (answer < 1 || answer > 4) {
            System.out.print("Invalid choice! Enter a number between 1 and 4: ");
            answer = scanner.nextInt();
        }

        if (answer == 3) {
            System.out.println("Correct answer! 🎉");
            score++;
            correctAnswers++;
        } else {
            System.out.println("Wrong answer!");
            wrongAnswers++;
        }
        // Question 4
        System.out.println("\nQuestion 4:");
        System.out.println("Which of these is used to store multiple values in Java?");

        System.out.println("1. Array");
        System.out.println("2. int");
        System.out.println("3. char");
        System.out.println("4. boolean");

        System.out.print("Enter your answer: ");
        answer = scanner.nextInt();
        while (answer < 1 || answer > 4) {
            System.out.print("Invalid choice! Enter a number between 1 and 4: ");
            answer = scanner.nextInt();
        }

        if (answer == 1) {
            System.out.println("Correct answer! 🎉");
            score++;
            correctAnswers++;
        } else {
            System.out.println("Wrong answer!");
            wrongAnswers++;
        }
        // Question 5
        System.out.println("\nQuestion 5:");
        System.out.println("Which symbol is used to end a statement in Java?");

        System.out.println("1. :");
        System.out.println("2. .");
        System.out.println("3. ;");
        System.out.println("4. ,");

        System.out.print("Enter your answer: ");
        answer = scanner.nextInt();
        while (answer < 1 || answer > 4) {
            System.out.print("Invalid choice! Enter a number between 1 and 4: ");
            answer = scanner.nextInt();
        }

        if (answer == 3) {
            System.out.println("Correct answer! 🎉");
            score++;
            correctAnswers++;
        } else {
            System.out.println("Wrong answer!");
            wrongAnswers++;
        }

        // Final Score
        System.out.println("\n================================");
        System.out.println("           QUIZ RESULT");
        System.out.println("================================");
        System.out.println("Congratulations " + name + "! 🎉");

        System.out.println("Your Score: " + score + " / 5");
        System.out.println("Correct Answers: " + correctAnswers);
        System.out.println("Wrong Answers: " + wrongAnswers);
        System.out.println("Total Questions: 5");

        double percentage = (score / 5.0) * 100;

        System.out.printf("Percentage: %.2f%%%n", percentage);

        if (score >= 3) {
            System.out.println("Result: PASSED 🎉");
        } else {
            System.out.println("Result: FAILED");
        }
        if (score == 5) {
            System.out.println("Performance: Excellent! 🏆");
        } else if (score >= 4) {
            System.out.println("Performance: Very Good! 🌟");
        } else if (score >= 3) {
            System.out.println("Performance: Good! 👍");
        } else {
            System.out.println("Performance: Needs Improvement. 💪");
        }

        System.out.println("================================");

        scanner.close();
    }
}