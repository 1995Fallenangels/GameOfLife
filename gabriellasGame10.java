/**
 * Write a description of class gabriellasgame10 here.
 *
 * @author Gabriella Bitju
 * @date 17/07/2023
 * @version 10
 * What does your code do?
 * My code is a game based on the game Conway's Game of Life.
 * The name of this game is Gabriella's Game of Life.
 * This game is zero player game which means the game evolves based on it's initial state and doesn't need human input.
 * It is played on a 2D grid, where every square is a cell.
 * The cell can either be dead or alive, depending on the cells surrounding it and the rules of the game.
 * Depending on it's initial state, the cells will form patterns on the grid.
 * The rules of the game are:
 * - For every cell that is alive/populated:
 * if it has no neighbours it dies by underpopulation
 * if it has four or more neighbours it dies by overpopulation
 * if it has 2 or 3 neighbours it will stay alive
 * - For every cell that is dead/empty:
 * if it has 3 neighbours it becomes alive.
 * The game will ask if you want to choose for the alive cells to be randomly generated or for you to manually choose which cells are alive.
 */
import java.util.Scanner;//keyboard scanner
public class gabriellasGame10
{
    // instance variables - replace the example below with your own
    int gridSize=21;
    int board[][]=new int[gridSize][gridSize];// defining my 2D array.
    int newBoard[][] = new int[gridSize][gridSize];
    int userPickx=0;
    int userPicky=0;
    boolean stillEditing =true;
    int runningGenerations = 0;
    int secondsToSleep = 5;
    int askingUserCoordinatesStage= 0;
    /**
     * Constructor for objects of class gabriellasgame10
     */
    public gabriellasGame10()
        {
            // initialise instance variables
            for (int x=0;x<gridSize;x++)//creating my 2D array. This is creating a 21x21 array of integers
            {
                for (int y=0;y<gridSize;y++) board[x][y]=0;
            }
            newBoard = board;
            System.out.println("Welcome to Gabriella's Game of Life.");
            gameOption();
        }
    //below are methods, I have put components of my game into methods to make it easier for me to call them, to make my code more organized, and to prevent errors.
    //This method applies the rules of Conways Game of life, it scans the 8 cells around each cell and turns it on and off based on the previoue 
    public void gameOption()
        {
            int option=100000;
            Scanner kb= new Scanner(System.in);//this is 
            while (option!=0)// 
            {   
                System.out.println("Enter 0 to start game");
                System.out.println("Enter 1 to see the game rules");
                System.out.println("Enter 2 to see the game credits");
                option= kb.nextInt();
                if (option==1)
                {
                    System.out.println("Here are the game rules:");
                    System.out.println("Each cell with one or no neighbors dies, because of underpopulation.");
                    System.out.println("Each cell with four or more neighbors dies, as if by overpopulation.");
                    System.out.println("Each cell with two or three neighbors survives.");
                    System.out.println("Each dead cell with three neighbors becomes populated.");
                }
                else if (option==2)
                {
                    System.out.println("Thank you Conway for originally creating this game");
                    System.out.println("This game was coded by Gabriella Bella Rose");
                }
                else if (option==0){}
                else
                {
                    System.out.println("Please enter 0, 1, or 2.");
                }
            }
            startGame();
        }
    public void startGame()
        {
            Scanner kb= new Scanner(System.in);
            while (stillEditing)// this is a loop which is responsible for user input, then changing the grid, then repeating until the user is done editing the grid.
            {
                printGrid();//This prints the grid, the code for this is below
                askingUserCoordinates();//This is the method which asks for user input for co-ordinates.
                printGrid();//This prints out a new grid with the user input chaning the cells.
            }
            for(int generations=GameEngine.askForInt("How many generations do you want to run?",0, 1000); generations > 0; generations--){
                allRules();
                try{Thread.sleep(1000/2);}
                catch(InterruptedException ie){System.out.println(ie);};
                printGrid();
                System.out.println(generations);
            }
        }
    public void askingUserCoordinates()
        {
            Scanner kb= new Scanner(System.in);
            int userPickx=GameEngine.askForInt("enter the x co-ordinate of the cell you want to change",0, 21);
            int userPicky=GameEngine.askForInt("enter the y co-ordinate of the cell you want to change",0, 21);
            board[userPickx][userPicky]=1;//user input changes the cell chosen to be alive/on
            System.out.println("If you want to stop enter 's' or press any key to continue");
            if (kb.nextLine().equals("s"))//this is an if statement that makes the 'stilEditing' loop stop if the user inputs the letter s, this way the game/generations can begin and the rules can be applied to the grid.
                {
                    stillEditing= false;
                }
        }
        
    public void allRules()
        {
            for (int x=0;x<21;x++)//this checks every colomn
            {
                for (int y=0;y<21;y++)//this checks every row
                {
                    int count = 0;
                    //System.out.println(x+", "+y);
                    //the following if statements checks each cell. I have made it so it checks every corner and side before it checks everything in the middle because if I didn't make it check the sides and corners it would go out of bounds.
                    if(x == 0 && y == 0)//this checks the top left corner cells
                    {
                        if(board[x+1][y] == 1)
                            count++;
                        if(board[x+1][y+1] == 1)
                            count++;
                        if(board[x][y+1] == 1)
                            count++;
                    }   else if(x == 20 && y == 0)//this checks the top right corner cells
                    {
                        if(board[x-1][y] == 1)
                            count++;
                        if(board[x-1][y+1] == 1)
                            count++;
                        if(board[x][y+1] == 1)
                            count++;
                    }   else if(x == 0 && y == 20)//this checks the bottom left corner cells
                    {
                        if(board[x][y-1] == 1)
                            count++;
                        if(board[x+1][y-1] == 1)
                            count++;
                        if(board[x+1][y] == 1)
                            count++;
                    }   else if(x == 20 && y == 20)//this checks the bottom right corner cells
                    {
                        if(board[x][y-1] == 1)
                            count++;
                        if(board[x-1][y-1] == 1)
                            count++;
                        if(board[x-1][y] == 1)
                            count++;
                    }   else if(y == 0)//this checks the top row cells (x axis)
                    {
                        if(board[x-1][y] == 1)
                            count++;
                        if(board[x+1][y] == 1)
                            count++;
                        if(board[x+1][y+1] == 1)
                            count++;
                        if(board[x][y+1] == 1)
                            count++;
                        if(board[x+1][y+1] == 1)
                            count++;
                    }   else if(y == 20)//this checks the bottom row cells (x axis)
                    {
                        if(board[x-1][y-1] == 1)
                            count++;
                        if(board[x-1][y] == 1)
                            count++;
                        if(board[x][y-1] == 1)
                            count++;
                        if(board[x+1][y-1] == 1)
                            count++;
                        if(board[x+1][y] == 1)
                            count++;
                    }   else if(x == 0)//this checks the left side row cells (y axis)
                    {
                        if(board[x][y-1] == 1)
                            count++;
                        if(board[x][y+1] == 1)
                            count++;
                        if(board[x+1][y] == 1)
                            count++;
                        if(board[x+1][y+1] == 1)
                            count++;
                        if(board[x+1][y-1] == 1)
                            count++;
                    }   else if(x == 20)//this checks the right side row cells (y axis)
                    {
                        if(board[x-1][y] == 1)
                            count++;
                        if(board[x-1][y-1] == 1)
                            count++;
                        if(board[x][y-1] == 1)
                            count++;
                        if(board[x-1][y+1] == 1)
                            count++;
                        if(board[x][y+1] == 1)
                            count++;
                    } else// this checks every cell in the middle
                    {
                        if(board[x-1][y] == 1)
                            count++;
                        if(board[x-1][y-1] == 1)
                            count++;
                        if(board[x][y-1] == 1)
                            count++;
                        if(board[x-1][y+1] == 1)
                            count++;
                        if(board[x][y+1] == 1)
                            count++;
                        if(board[x+1][y-1] == 1)
                            count++;
                        if(board[x+1][y] == 1)
                            count++;
                        if(board[x+1][y+1] == 1)
                            count++;
                    }
                    if (board[x][y] == 1) //if the cell is alive it will apply the rules
                    {  switch(count)
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
                    {  switch(count)
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
    
        /**public static void clearScreen()
        {
            System.out.print("\u000C");
            System.out.flush();
        }// This is a method that clears the screen**/
    
        public void printGrid()// This code below is responsible for printing out the grid and the formatting of the grid.
        {
            //clearScreen();//this clears the screen before printing out the new grid, this way the grid will stay in the same position ,making it clear of what is changing on the grid.
            System.out.print("   ");//This creates a little gap before I print out the x co-ordinates above the grid so the x is in line with the y co-ordinates.
            for (int i=0;i<gridSize;i++)
                {  
                    System.out.print(" ");
                    System.out.print(i);//This is printing the numbers at the top of the grid so the player knows the x co-ordinates for the cell they want to manipulate.
                    if (i>=10)
                    {
                        System.out.print("");
                    } else {
                        System.out.print(" ");
                    }// This creates a space between the numbers on the x co-ordinates.
                }
                System.out.println();//This makes the next line of code print out on the next line and not beside it. Without this the next line would be printed out on the wrong line.
            for (int x=0;x<gridSize;x++)
                {
                    System.out.print(" ");
                    System.out.print(x);//This prints out the y co-ordinates
                    System.out.print(" ");// this prints out a space after the y co-ordinates.
                    if (x<10){ System.out.print(" ");}
                    for (int y=0;y<gridSize;y++)
                    {
                        if (board[y][x]==1)
                        System.out.print("■ ");//this is printing out a cell on the grid if it was occupied/alive
                        else System.out.print("□ ");//This is printing out the cell on the grid. It represents an empty/dead cell.
                        System.out.print(" ");
                    }
                    System.out.println();// This makes the code print out on the next line and not on the same line
                }
            System.out.println("");
        }
}