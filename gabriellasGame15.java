/**
 * Write a description of class gabriellasGame15 here.
 *
 * @author Gabriella Bitju
 * @date 18/08/2023
 * @version 16
 * My code is a game based on the game Conway's Game of Life.
 * The name of this game is Gabriella's Game of Life.
 * This game is a zero-player game, which means it evolves based on its initial state and doesn't need human input.
 * It is played on a 2D grid, where every square is a cell.
 * The cell can either be dead or alive, depending on the cells surrounding it and the rules of the game (the cells interactions with other cells).
 * Depending on its initial state and the interactions, the cells will form patterns on the grid.
 * The game will ask you to manually choose which cells you want to be alive. Then ask how many generations you want it to run.
 * Then you will watch the cells evolve after every generation.
 * The rules of the game are:
 * - For every cell that is alive/populated:
 * if it has no neighbors it dies of underpopulation
 * if it has four or more neighbors it dies of overpopulation
 * if it has 2 or 3 neighbors it will stay alive
 * - For every cell that is dead/empty:
 * if it has 3 neighbors it becomes alive.
 * the cells will evolve for how many generations the user wants it to run.
 * the game will then ask the user if they want to add more cells or run more generations or end the game.
 * if they choose to add more cells then the game will ask the user for the coordinates of the cell they want to be alive.
 * Then they run the generations and repeat until the user chooses to end the game.
 * This game doesn't really have an aim, it just creates patterns.
 */
import java.util.Scanner; //keyboard scanner
public class gabriellasGame15 {
    // instance variables - replace the example below with your own
    int gridSize = 21; //I made gridSize into an integer in case I had time to change it so the user could change the grid size.
    boolean board[][] = new boolean[gridSize][gridSize]; // defining my 2D array.
    boolean newBoard[][] = new boolean[gridSize][gridSize];//making another 2D array
    int usersXCoordinate = 0; //this is an integer for the users x coordinates
    int usersYCoordinate = 0; //this is an integer for the users y coordinates
    boolean stillEditing = true; //I have set stillEditing to true (which I put in a while loop I made for when the user is still editing the grid).
    //When the user inputs "s" the stillEditing loop will be set to false and stop the loop.
    boolean continuingGame = true;//continuingGame is a boolean that I set to true/on. I put this boolean in a while loop for when the user wants to continue adding cells.
    int numberOfCellsAlive = 0;//this is an integer for how many neighbors around a cell are alive
    Scanner kb = new Scanner(System.in);//this is my keyboard scanner
    /**
     * Constructor for objects of class gabriellasGame15
     */
    public gabriellasGame15() {
        // initialise instance variables
        for (int x = 0; x < gridSize; x++) //creating my 2D array. This is creating a 21x21 array of integers
        {
            for (int y = 0; y < gridSize; y++) board[x][y] = false;//this has set my grid cells to dead.
        }
        System.out.println("Welcome to Gabriella's Game of Life (✿◠‿◠)"); //This is the opening to my game
        System.out.println("Gabriella's Game of Life is based on the game Conway's Game of Life. The game is played on a 2D grid, where every square is a cell. Each cell can either be dead or alive.");
        System.out.println("You will choose which cell is dead or alive. The cells will go through generations and change based on its interactions between each cell.");
        starterMenu();//This calls out the starter menu method which prints out and operates the starter menu before the user starts the game.
        startGame(); //this will start the same when the user enters 0. This method will do everything from asking the user coordinates to running the geneartions.
    }
    //below are methods, I have put components of my game into methods to make it easier for me to call them, to make my code more organized, and to prevent errors.
    public void starterMenu() {
        String option = "";
        //below prints out the options for the starter menu
        System.out.println("Starter menu:");
        System.out.println("Enter 0 to start game");
        System.out.println("Enter 1 to see how to play");
        System.out.println("Enter 2 to see game credits");
        while (!option.equals("0")) //This is a while loop for my starter menu. While the user input is not 0(to start the game), then the starter menu will keep reappearing after every input.
        {
            option = kb.nextLine();
            if (option.equals("1")) {
                System.out.println("How to play:");
                System.out.println("1. Choose which cells you want to be alive or dead by entering the x, y coordinates. Repeat until you have finished choosing which cells you want to be alive.");
                System.out.println("(to kill an alive cell, enter the coordinate of the alive cell)");
                System.out.println("2. Choose how many generations you want the cells to go through.");
                System.out.println("3. Watch as the cells evolve through each generation.");
                System.out.println("4. Repeat until you want to quit the game.");
                System.out.println("  ");
                System.out.println("(The cells become dead or alive depending on it's interactions with other cells. The game rules are the possible interactions.)");
                System.out.println("The game rules:");
                System.out.println("Each cell with one or no neighbors dies, because of underpopulation.");
                System.out.println("Each cell with four or more neighbors dies, as if by overpopulation.");
                System.out.println("Each cell with two or three neighbors survives.");
                System.out.println("Each dead cell with three neighbors becomes populated.");
            } else if (option.equals("2")) {
                System.out.println("Thank you Conway for originally creating this game");
                System.out.println("This game was coded by Gabriella Bella Rose");
            } else if (!option.equals("0")) {} {
                System.out.println("Please enter 0, 1, or 2.");//this will be printed out whenever the user inputs anything other than 0, 1, 2, 3, or 4 so the game won't break.
            } //
        }
    }
    //This method will start the game
    public void startGame() {
        while (stillEditing) // this is a loop that is responsible for user input, then updating the grid, then repeating until the user is done editing the grid.
        //updating the grid as the user is still asking for coordinates is good for one of my relevant implications, usability. It relates to the visibility of system status.
        {
            printGrid(); //This prints the grid, the code for this is below
            askingUserCoordinates(); //This is the method that asks for user input for coordinates.
            printGrid(); //This prints out a new grid with the user input changing the cells.
        }
        for (int generations = askForInt("How many generations do you want to run? (1-50)", 0, 50); generations > 0; generations--) {
            applyingGameRules();//This will go through the cells and apply the game rules to change the cells.
            try {
                Thread.sleep(2000 / 2);
            } catch (InterruptedException ie) {
                System.out.println(ie);
            }// this craetes a time delay between each time the generations print out.
            printGrid();//this prints out the new grid after every generation
            System.out.println(generations);//this prints out the number of generations
        }
        endGameMenu();//after it has gone through all the generations it will go to the end of game menu. If they choose 3, the game will end.
    }
    //This method asks the user for the coordinates of the cell they want to change.
    public void askingUserCoordinates() {
        //this asks for the x coordinate of the cell they want to change
        int usersXCoordinate = askForInt("enter the number of the x coordinate of the cell you want to change (e.g 10)", 0, 21);
        //this asks for the y coordinate of the cell they want to change
        int usersYCoordinate = askForInt("enter the number of y coordinate of the cell you want to change (eg.5)", 0, 21);
        board[usersXCoordinate][usersYCoordinate] = !board[usersXCoordinate][usersYCoordinate];//user input changes the cell chosen to be alive/on. This means if the users x y coordinate is dead before, now make it alive/not dead.
        // And if the users x y coordinate is alive already, then make it dead. This way the user can undo/kill the cell that they picked before.
        printGrid();
        System.out.println("To continue adding cells press 'enter', to stop adding cells enter 's'");
        String input = kb.nextLine();
        input = input.toLowerCase(); //This makes it so if the user inputs a capital 'S', it will turn it to a lowercase S and stop the editing.
        if (input.equals("s")) //this is an if statement that makes the 'stilEditing' loop stop if the user inputs the letter s, the game will proceed to ask how many generations it wants to run.
        {
            stillEditing = false;
        }
    }
    //This method prints out the grid.
    public void printGrid() // This code below is responsible for printing out the grid and formatting the grid.
    {
        clearScreen(); //this clears the screen before printing out the new grid, this way the grid will stay in the same position, making it clear what is changing on the grid.
        System.out.print("   "); //This creates a little gap before I print out the x coordinates above the grid so the x is in line with the y coordinates.
        for (int x = 0; x < gridSize; x++) {
            System.out.print(" ");
            System.out.print(x); //This is printing the numbers at the top of the grid so the player knows the x coordinates for the cell they want to manipulate.
            if (x >= 10) {
                System.out.print("");
            } else {
                System.out.print(" ");
            } // This creates a space between the numbers on the x coordinates. This is just for spacing/formatting and making the grid look nice and even.
        }
        System.out.println(); //This makes the next line of code print out on the next line and not beside it. Without this the next line would be printed out on the wrong line.
        for (int y = 0; y < gridSize; y++) {
            System.out.print(" "); //this prints out a space before the y coordinates
            System.out.print(y); //This prints out the y coordinates on the side.
            System.out.print(" "); // this prints out a space after the y coordinates.
            if (y < 10) {
                System.out.print(" ");
            }
            for (int x = 0; x < gridSize; x++) {
                if (board[x][y])
                    System.out.print("■ "); //this is printing out a cell on the grid if it was occupied/alive
                else System.out.print("□ "); //This is printing out the cell on the grid. It represents an empty/dead cell.
                System.out.print(" ");
            }
            System.out.println(); // This makes the code print out on the next line and not on the same line
        }
        System.out.println("");
    }
    //This method (applyingGameRules) applies the rules of Conway's Game of Life, it scans the neighbors of each cell and turns the cell on or off based on the previous state and based on the rules.  
    public void applyingGameRules() {
        for (int j = 0; j < gridSize; j++)//this checks every column of newBoard
        {
            for (int i = 0; i < gridSize; i++)//this checks every row of newBoard
            {
                newBoard[j][i] = board[j][i];//this goes through the cells of newBoard and changes it to be the same as the variables of (original) board
            }
        }
        for (int x = 0; x < gridSize; x++) //this checks every column of the board
        {
            for (int y = 0; y < gridSize; y++) //this checks every row of the board
            {
                int numberOfCellsAlive = 0;//This integer of numberOfCellsAlive is the integer that represents the number of neighbors a cell has.
                //The following if statements check each cell. I have made it so it checks every corner and side before it checks everything in the middle.
                //If I didn't make it check the sides and corners it would go out of bounds. If a cell is alive, it will add one to the "numberOfCellsAlive".
                //It's basically telling us how many neighbors are alive.
                if (x == 0 && y == 0) //this checks if the 3 top left corner cells are alive
                {
                    if (board[x + 1][y])
                        numberOfCellsAlive++;
                    if (board[x + 1][y + 1])
                        numberOfCellsAlive++;
                    if (board[x][y + 1])
                        numberOfCellsAlive++;
                } else if (x == 20 && y == 0) //this checks if the top right corner cells are alive
                {
                    if (board[x - 1][y])
                        numberOfCellsAlive++;
                    if (board[x - 1][y + 1] )
                        numberOfCellsAlive++;
                    if (board[x][y + 1])
                        numberOfCellsAlive++;
                } else if (x == 0 && y == 20) //this checks if the bottom left corner cells are alive
                {
                    if (board[x][y - 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y - 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y])
                        numberOfCellsAlive++;
                } else if (x == 20 && y == 20) //this checks if the bottom right corner cells are alive
                {
                    if (board[x][y - 1])
                        numberOfCellsAlive++;
                    if (board[x - 1][y - 1])
                        numberOfCellsAlive++;
                    if (board[x - 1][y])
                        numberOfCellsAlive++;
                } else if (y == 0) //this checks if the top row cells (x axis) are alive
                {
                    if (board[x - 1][y])
                        numberOfCellsAlive++;
                    if (board[x + 1][y])
                        numberOfCellsAlive++;
                    if (board[x + 1][y + 1])
                        numberOfCellsAlive++;
                    if (board[x][y + 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y + 1])
                        numberOfCellsAlive++;
                } else if (y == 20) //this checks if the bottom row cells (x axis) are alive
                {
                    if (board[x - 1][y - 1])
                        numberOfCellsAlive++;
                    if (board[x - 1][y])
                        numberOfCellsAlive++;
                    if (board[x][y - 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y - 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y])
                        numberOfCellsAlive++;
                } else if (x == 0) //this checks if the left side row cells (y axis) are alive
                {
                    if (board[x][y - 1])
                        numberOfCellsAlive++;
                    if (board[x][y + 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y])
                        numberOfCellsAlive++;
                    if (board[x + 1][y + 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y - 1])
                        numberOfCellsAlive++;
                } else if (x == 20) //this checks if the right side row cells (y axis) are alive
                {
                    if (board[x - 1][y])
                        numberOfCellsAlive++;
                    if (board[x - 1][y - 1])
                        numberOfCellsAlive++;
                    if (board[x][y - 1])
                        numberOfCellsAlive++;
                    if (board[x - 1][y + 1])
                        numberOfCellsAlive++;
                    if (board[x][y + 1])
                        numberOfCellsAlive++;
                } else // this checks for every cell in the middle if they're alive
                {
                    if (board[x - 1][y])
                        numberOfCellsAlive++;
                    if (board[x - 1][y - 1])
                        numberOfCellsAlive++;
                    if (board[x][y - 1])
                        numberOfCellsAlive++;
                    if (board[x - 1][y + 1])
                        numberOfCellsAlive++;
                    if (board[x][y + 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y - 1])
                        numberOfCellsAlive++;
                    if (board[x + 1][y])
                        numberOfCellsAlive++;
                    if (board[x + 1][y + 1])
                        numberOfCellsAlive++;
                }
                if (board[x][y]) //if the cell is alive it will apply the rules below
                {
                    if (numberOfCellsAlive < 2 || numberOfCellsAlive > 3)//if there are less than 2 neighbors or more than 3 neighbors the cell will die
                    {
                        newBoard[x][y] = false;//kills the cell
                    }
                } else if (!board[x][y]) // if the cell is dead it will apply the rule "Each dead cell with three neighbors becomes populated."
                {
                    if (numberOfCellsAlive == 3)//if the cell has 3 neighbors then the cell will become alive
                    {
                        newBoard[x][y] = true;//makes the cell alive.
                    }
                }
            }
        }
        for (int j = 0; j < gridSize; j++)//goes through each column
        {
            for (int i = 0; i < gridSize; i++)//goes through each row
            {
                board[j][i] = newBoard[j][i];
            }
        }
    }
    //this method is an option that allows the users to add more cells (continue playing the game) after the generations have run.
    public void addingMoreCells() {
        while (continuingGame) {
            stillEditing = true;
            //I have set stillEditing to true before running the startGame method because if I didn't the game would skip asking the x y coordinates to ask how many generations would like to be run.
            //This is because in my askingUserCoordinates method when the user enters "s" then stillEditing is set to false so it wouldn't ask the user to add more cells.
            startGame(); //this starts the game again but with the same grid. It will ask the user for more cell coordinates.
        }
    }
    // This method clears the screen
    public static void clearScreen() {
        System.out.print("\u000C");
    }
    //This method asks for a string.
    public String askForString(String message) {
        System.out.println(message);
        String next = kb.nextLine();
        return next;
    }
    //This method asks for an integer
    public int askForInt(String question, int min, int max) {
        String input = askForString(question);
        int answer;
        try {
            answer = Integer.parseInt(input);//this makes the string into a an integer
        } catch (NumberFormatException nfe) {
            System.out.println("invalid input! Please enter numbers only");//if they enter anything other than a number it will print out this message
            return askForInt(question, min, max);//then it will ask the question again
        }
        if (min <= answer && answer <= max) {
            return answer;
        } else {
            System.out.println("number too low or too high, please enter an appropriate number");//This message appears when the input number is less or more than the required number.
            return askForInt(question, min, max);
        }
    }
    //This method shows the options that appear when the game has gone through a number of generations. The user can either add more cells to the grid or the user can run more generations or quit the game.
    public void endGameMenu() {
        String option = "";
        System.out.println("Enter 1 to add more cells, 2 to run more generations, or 3 to quit playing."); //prints out the options
        while (!option.equals("3")) //This is a while loop for my starter menu. While the user input is not 0(to start the game), then the starter menu will keep reappearing.
        {
            option = kb.nextLine();
            if (option.equals("1")) //if the user enters 1 then it will call the addingMoreCells method
            {
                addingMoreCells();
            } else if (option.equals("2")) //if the user enters 2 then it will call the startGame method which asks them how many generations they want to run.
            {
                startGame(); //If I call the startGame() method, it'll immediately ask for how many generations they want to run (doesn't ask to add more cells because stillEditing was already set to false).
            } else if (option.equals("3")) {
                //if they choose 3 the following will happen
                clearScreen(); //clears the screen
                System.out.println("Thank you for playing Gabriella's Game of Life (✿◠‿◠) "); //this prints out a message when the game ends.
                System.exit(0);//this ends the game :D!
            } //if the user enters 3 then it will proceed to clearScreen()
            else if (!option.equals("3")) {
                System.out.println("Please enter 1, 2, or 3.");//this will print out when the user enters something other than 3.
            } //
        }
    }
}
