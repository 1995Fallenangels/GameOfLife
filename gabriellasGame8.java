/**
 * Write a description of class gabriellasgame7 here.
 *
 * @author Gabriella Bitju
 * @version 23/06/2023
 * This game is Gabriella's Game of Life (based on the game Conway's Game of Life)
 * This game is played on a 2D grid, where every square is a cell. It can either be dead or alive, depending on the cells surrounding it. The goal of the game is to make pa
 */
import java.util.Scanner;//keyboard scanner
public class gabriellasGame8
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
     * Constructor for objects of class gabriellasgame3
     */
    public gabriellasGame8()
        {
            // initialise instance variables
            for (int x=0;x<gridSize;x++)//creating my 2D array. This is creating a 21x21 array of integers
            {
                for (int y=0;y<gridSize;y++) board[x][y]=0;
            }
            newBoard = board;
            System.out.println("Welcome to Gabriella's Game of Life.");
            gameOption();
            startGame();
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
                    System.out.println("Each cell with one or no neighbors dies, because of underpopulation.");
                    System.out.println("Each cell with four or more neighbors dies, as if by overpopulation.");
                    System.out.println("Each cell with two or three neighbors survives.");
                    System.out.println("Each cell with three neighbors becomes populated.");
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
            System.out.println("How many generations do you want to run?");
            for(int generations = kb.nextInt(); generations > 0; generations--){
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
            for (int x=0;x<gridSize-1;x++)//this checks every colomn
            {
                for (int y=0;y<gridSize-1;y++)//this checks every row
                {
                    
                }
            }
            board=newBoard;
        }
    
        public static void clearScreen()
        {
            System.out.print("\u000C");
            System.out.flush();
        }// This is a method that clears the screen
    
        public void printGrid()// This code below is responsible for printing out the grid and the formatting of the grid.
        {
            clearScreen();//this clears the screen before printing out the new grid, this way the grid will stay in the same position ,making it clear of what is changing on the grid.
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
                        if (board[y][x]==0)
                        System.out.print("□ ");//This is printing out the cell on the grid. It represents an empty/dead cell.
                        else System.out.print("■ ");//this is printing out a cell on the grid if it was occupied/alive
                        System.out.print(" ");
                    }
                    System.out.println();// This makes the code print out on the next line and not on the same line
                }
            System.out.println("");
        }
}
