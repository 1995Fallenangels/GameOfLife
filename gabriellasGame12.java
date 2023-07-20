/**
 * Write a description of class gabriellasGame12 here.
 *
 * @author Gabriella Bitju
 * @date 20/07/2023
 * @version 12
 * My code is a game based on the game Conway's Game of Life.
 * The name of this game is Gabriella's Game of Life.
 * This game is zero player game which means the game evolves based on it's initial state and doesn't need human input.
 * It is played on a 2D grid, where every square is a cell.
 * The cell can either be dead or alive, depending on the cells surrounding it and the rules of the game.
 * Depending on it's initial state, the cells will form patterns on the grid.
 * The game will ask you to manually choose which cells you want to be alive. Then will ask how many generations you want it run.
 * Then you will watch the cells evolve after every generation.
 * The rules of the game are:
 * - For every cell that is alive/populated:
 * if it has no neighbours it dies by underpopulation
 * if it has four or more neighbours it dies by overpopulation
 * if it has 2 or 3 neighbours it will stay alive
 * - For every cell that is dead/empty:
 * if it has 3 neighbours it becomes alive.
 *
 */
import java.util.Scanner;//keyboard scanner
public class gabriellasGame12
{
    // instance variables - replace the example below with your own
    int gridSize=21;//I made gridSize into an integer in case I had time to change it so the user could change the grid size.
    int board[][]=new int[gridSize][gridSize];// defining my 2D array.
    int newBoard[][] = new int[gridSize][gridSize];
    int usersXCoordinate=0;//this is an integer for the users x coordinates
    int usersYCoordinate=0;//this is an integer for the users y coordinates
    boolean stillEditing =true;//I have set stillEditing (which is a while loop I made for when the user in still editing the grid). When the user inputs "s" the stillEditing loop will be set to false and stop the loop.
    boolean continuingGame= true;
    int askingUserCoordinatesStage= 0;
    int numberOfCellsAlive = 0;
    Scanner kb= new Scanner(System.in);
    /**
     * Constructor for objects of class gabriellasGame12
     */
    public gabriellasGame12()
        {
            // initialise instance variables
            for (int x=0;x<gridSize;x++)//creating my 2D array. This is creating a 21x21 array of integers
            {
                for (int y=0;y<gridSize;y++) board[x][y]=0;
            }
            newBoard = board;
            System.out.println("Welcome to Gabriella's Game of Life.");//
            starterMenu();
        }
    //below are methods, I have put components of my game into methods to make it easier for me to call them, to make my code more organized, and to prevent errors.
    public void starterMenu()
        {
            String option = "";
            System.out.println("Enter 0 to start game");
            System.out.println("Enter 1 to see how to play");
            System.out.println("Enter 2 to see the game rules");
            System.out.println("Enter 3 to see the game credits");
            while (!option.equals("0"))//This is a while loop for my starter menu. While the user input is not 0(to start game), then the starter menu will keep reappearing.
            {   option= kb.nextLine();
                if (option.equals("1"))
                {
                    System.out.println("How to play:");
                    System.out.println("1. Choose which cells you want to be alive by entering it's x and y coordinates. Enter 's' when you are done editing the grid");
                    System.out.println("2. Choose how many generations you want to run");
                    System.out.println("3. Watch as the cells evolve through each generation");
                }
                else if (option.equals("2"))
                {
                    System.out.println("Here are the game rules:");
                    System.out.println("Each cell with one or no neighbors dies, because of underpopulation.");
                    System.out.println("Each cell with four or more neighbors dies, as if by overpopulation.");
                    System.out.println("Each cell with two or three neighbors survives.");
                    System.out.println("Each dead cell with three neighbors becomes populated.");
                }
                else if (option.equals("3"))
                {
                    System.out.println("Thank you Conway for originally creating this game");
                    System.out.println("This game was coded by Gabriella Bella Rose");
                }
                else if (!option.equals("0")){}{
                    System.out.println("Please enter 0, 1, 2, or 3.");
                }//
            }
            startGame();//this will start the same
        }
    // this method will start the game
        public void startGame()
        {
            while (stillEditing)// this is a loop which is responsible for user input, then updating the grid, then repeating until the user is done editing the grid.
            //updating the grid as the user is still asking for coordinates is good for one of my relevant implications, usability. It relates to visibility of system status.
            {
                printGrid();//This prints the grid, the code for this is below
                askingUserCoordinates();//This is the method which asks for user input for co-ordinates.
                printGrid();//This prints out a new grid with the user input chaning the cells.
            }
            for(int generations=askForInt("How many generations do you want to run? (1-1000)",0, 1000); generations > 0; generations--){
                applyingGameRules();
                try{Thread.sleep(2000/2);}
                catch(InterruptedException ie){System.out.println(ie);};// this is time delay
                printGrid();
                //System.out.println(generations + "more generations to go through");
            }
            endGameOption();
        }
        public void addingMoreCells()
        {
            while (continuingGame){
                stillEditing= true;
                startGame();
            }
        }
    //This method asks the user for the coordinates of the cell they want to change.
        public void askingUserCoordinates()
        {
            //this asks for the x coordinate of the cell they want to change
            int usersXCoordinate=askForInt("enter the x co-ordinate of the cell you want to change",0, 21);
            //this asks for the y coordinate of the cell they want to change
            int usersYCoordinate=askForInt("enter the y co-ordinate of the cell you want to change",0, 21);
            board[usersXCoordinate][usersYCoordinate]=1;//user input changes the cell chosen to be alive/on
            System.out.println("If you want to stop enter 's' or press any key to continue");
            if (kb.nextLine().equals("s"))//this is an if statement that makes the 'stilEditing' loop stop if the user inputs the letter s, the game will proceed to ask how many generations it wants to run.
                {
                    stillEditing= false;
                }
        }
    //This method (applyingGameRules) applies the rules of Conways Game of life, it scans the neighbours of each cell and turns the cell on or off based on the the previous state and based on the rules.  
    public void applyingGameRules()
        {
            for (int x=0;x<21;x++)//this checks every colomn
            {
                for (int y=0;y<21;y++)//this checks every row
                {
                    int numberOfCellsAlive = 0;
                    //the following if statements checks each cell. I have made it so it checks every corner and side before it checks everything in the middle because if I didn't make it check the sides and corners it would go out of bounds.
                    //if a cell is alive, it will add one to the "numberOfCellsAlive" and the rules will
                    if(x == 0 && y == 0)//this checks if the 3 top left corner cells are alive
                    {
                        if(board[x+1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y+1] == 1)
                            numberOfCellsAlive++;
                    }   else if(x == 20 && y == 0)//this checks if the top right corner cells are alive
                    {
                        if(board[x-1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y+1] == 1)
                            numberOfCellsAlive++;
                    }   else if(x == 0 && y == 20)//this checks if the bottom left corner cells are alive
                    {
                        if(board[x][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y] == 1)
                            numberOfCellsAlive++;
                    }   else if(x == 20 && y == 20)//this checks if the bottom right corner cells are alive
                    {
                        if(board[x][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y] == 1)
                            numberOfCellsAlive++;
                    }   else if(y == 0)//this checks if the top row cells (x axis) are alive
                    {
                        if(board[x-1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y+1] == 1)
                            numberOfCellsAlive++;
                    }   else if(y == 20)//this checks if the bottom row cells (x axis) are alive
                    {
                        if(board[x-1][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y] == 1)
                            numberOfCellsAlive++;
                    }   else if(x == 0)//this checks if the left side row cells (y axis) are alive
                    {
                        if(board[x][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y-1] == 1)
                            numberOfCellsAlive++;
                    }   else if(x == 20)//this checks if the right side row cells (y axis) are alive
                    {
                        if(board[x-1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y+1] == 1)
                            numberOfCellsAlive++;
                    } else// this checks for every cell in the middle if they're alive
                    {
                        if(board[x-1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x-1][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x][y+1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y-1] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y] == 1)
                            numberOfCellsAlive++;
                        if(board[x+1][y+1] == 1)
                            numberOfCellsAlive++;
                    }
                    if (board[x][y] == 1) //if the cell is alive it will apply the rules
                    {  switch(numberOfCellsAlive)
                        {
                        case 0: //if there are no neighbours, it dies
                            board[x][y] = 0;
                            break;
                        case 1: //if there is one neighbour it dies
                            board[x][y] = 0;
                            break;
                        case 2: //if there are 2 neighbours it becomes alive
                            board[x][y] = 1;
                            break;
                        case 3: //if there are 3 neighbours it becomes alive
                            board[x][y] = 1;
                            break;
                        default: //if it has more than 4 neighbours it dies
                            board[x][y] = 0;
                            break;
                        }
                    }  else if (board[x][y] == 0)// if the cell is dead it will apply the rule "Each dead cell with three neighbors becomes populated."
                    {  switch(numberOfCellsAlive)
                        {
                        case 0: //if there are no neighbours, it dies
                            board[x][y] = 0;
                            break;
                        case 1: //if there is one neighbour it dies
                            board[x][y] = 0;
                            break;
                        case 2: //if there are 2 neighbours it dies
                            board[x][y] = 0;
                            break;
                        case 3: //if there are 3 neighbours it becomes alive
                            board[x][y] = 1;
                            break;
                        default: //if it has more than 4 neighbours it dies
                            board[x][y] = 0;
                            break;
                        }
                    }
                }
            }
            board=newBoard;
        }
    public String askForString(String message) {
        System.out.println(message);
        String next = kb.nextLine();
        return next;
    }
    public int askForInt(String question, int min, int max){
        String input = askForString(question);
        int answer;
        try{
            answer =Integer.parseInt(input);
        } catch(NumberFormatException nfe){
            System.out.println("invalid input! please enter numbers only");
            return askForInt(question, min, max);

        }
        if( min<=answer && answer<=max){
            return answer;
        } else {return askForInt(question, min, max);}
    }//this method clears the screen
        public static void clearScreen()
        {
            System.out.print("\u000C");
        }// This is a method that clears the screen
    //This method prints out the grid.
        public void printGrid()// This code below is responsible for printing out the grid and the formatting of the grid.
        {
            clearScreen();//this clears the screen before printing out the new grid, this way the grid will stay in the same position ,making it clear of what is changing on the grid.
            System.out.print("   ");//This creates a little gap before I print out the x co-ordinates above the grid so the x is in line with the y co-ordinates.
            for (int x=0;x<gridSize;x++)
                {  
                    System.out.print(" ");
                    System.out.print(x);//This is printing the numbers at the top of the grid so the player knows the x co-ordinates for the cell they want to manipulate.
                    if (x>=10)
                    {
                        System.out.print("");
                    } else {
                        System.out.print(" ");
                    }// This creates a space between the numbers on the x co-ordinates. This is just for spacing/formatting and making the grid look nice and even.
                }
                System.out.println();//This makes the next line of code print out on the next line and not beside it. Without this the next line would be printed out on the wrong line.
            for (int y=0;y<gridSize;y++)
                {
                    System.out.print(" ");//this prints out a space before the y coordinates
                    System.out.print(y);//This prints out the y co-ordinates on the side.
                    System.out.print(" ");// this prints out a space after the y co-ordinates.
                    if (y<10){ System.out.print(" ");}
                    for (int x=0;x<gridSize;x++)
                    {  
                        if (board[x][y]==1)
                        System.out.print("■ ");//this is printing out a cell on the grid if it was occupied/alive
                        else System.out.print("□ ");//This is printing out the cell on the grid. It represents an empty/dead cell.
                        System.out.print(" ");
                    }
                    System.out.println();// This makes the code print out on the next line and not on the same line
                }
            System.out.println("");
        }
        public void endGameOption()
        {
            String option = "";
            System.out.println("Enter 1 to add more cells, 2 to run more generations, or 3 to quit playing.");
            while (!option.equals("3"))//This is a while loop for my starter menu. While the user input is not 0(to start game), then the starter menu will keep reappearing.
            {   option= kb.nextLine();
                if (option.equals("1"))
                {
                    addingMoreCells();
                }
                else if (option.equals("2"))
                {
                    startGame();//If I call the startGame() method, it'll immediately ask for how many generations they want to run (doesn't ask to add more cells because stillEditing was already set to false).
                }
                else if (option.equals("3")){}
                else if (!option.equals("3")){
                    System.out.println("Please enter 1, 2, or 3.");
                }//
            }
            clearScreen();
            System.out.println("Thank you for playing");
            }
        }
