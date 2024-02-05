//challenge: no extra UI, only in java console. Is it possible? IDK

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean gameOver = false;
        int score = 0;

        // Initialize the game board
        int width = 10;
        int height = 10;
        char[][] board = new char[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = ' ';
            }
        }

        // Initialize the snake
        int snakeLength = 1;
        int snakeX = width / 2;
        int snakeY = height / 2;
        board[snakeY][snakeX] = 'S';

        // Generate the first apple
        int appleX = (int) (Math.random() * width);
        int appleY = (int) (Math.random() * height);
        board[appleY][appleX] = '@';

        // Game loop
        while (!gameOver) {
            // Print the game board with walls
            for (int i = 0; i < height + 2; i++) {
                for (int j = 0; j < width + 2; j++) {
                    if (i == 0 || i == height + 1 || j == 0 || j == width + 1) {
                        System.out.print("#");
                    } else {
                        System.out.print(board[i - 1][j - 1]);
                    }
                }
                System.out.println();
            }

            // Get user input
            System.out.println("The current score is: " + score);
            System.out.print("Enter direction (w/a/s/d): ");
            char direction = scanner.next().charAt(0);
            
            // Update snake position
            switch (direction) {
                case 'w':
                    snakeY--;
                    break;
                case 'a':
                    snakeX--;
                    break;
                case 's':
                    snakeY++;
                    break;
                case 'd':
                    snakeX++;
                    break;
            }

            // Check for collision with walls
            if (snakeX < 0 || snakeX >= width || snakeY < 0 || snakeY >= height) {
                gameOver = true;
                break;
            }

            // Check for collision with apple
            if (snakeX == appleX && snakeY == appleY) {
                // Increase score and snake length
                score++;
                snakeLength++;

                // Generate a new apple
                appleX = (int) (Math.random() * width);
                appleY = (int) (Math.random() * height);
            }

            // Update the game board
            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (i == snakeY && j == snakeX) {
                        board[i][j] = 'S';
                    } else if (i == appleY && j == appleX) {
                        board[i][j] = '@';
                    } else {
                        board[i][j] = ' ';
                    }
                }
            }

            // Clear the console
            System.out.print("\033[H\033[2J");
            System.out.flush();
        }

        // Game over
        System.out.println("Game over! You hit the wall!");
        System.out.println("You ate: " + score + " apples!");
    }
}
