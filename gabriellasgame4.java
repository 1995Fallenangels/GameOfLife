/**
 * Write a description of class gabriellasgame3 here.
 *
 * @author Gabriella
 * @version 31/05/2023
 * This game is played on a 2D grid, where every square is a cell. It can either be dead or alive,
 * depending on the cells surrounding it. The goal of the game is to make pa
 */
import java.util.Scanner;//keyboard scanner
import java.io.PrintStream;
public class gabriellasgame4
{
    // instance variables - replace the example below with your own
    int gridSize=21;
    int board[][]=new int[gridSize][gridSize];// defining my 2D array.
    int userPickx=0;
    int userPicky=0;
    boolean stillEditing =true;
    /**
     * Constructor for objects of class gabriellasgame3
     */
    public gabriellasgame4()
        {
            // initialise instance variables
            for (int x=0;x<gridSize;x++)//creating my 2D array. This is creating a 21x21 array of integers
            {
             for (int y=0;y<gridSize;y++)
                 board[x][y]=0;
            }
            Scanner kb= new Scanner(System.in);
            while (stillEditing)//
            {
                printGrid();//This prints the grid, the code for this is below
                askingUserCoordinates();
                printGrid();//This prints out a new grid
                System.out.println("If you want to stop enter 'stop'");
                
                if (kb.nextLine()=="stop")
                    {
                        stillEditing= false;
                        break;
                    }
            }
        }
    public void ruleOne()//This method applies one of the GameofLife rules
        {
            for (int x=0;x<gridSize-1;x++)//this checks every colomn
            {
                for (int y=0;y<gridSize-1;y++)//this checks every row
                {
                    int count = 0;
                    if (x>0 && y>0 && board[y-1][x-1]==1) count++;
                    if (x>0 && y>0 && board[y-1][x]==1) count++;
                    if (x<gridSize && y>0 && board[y-1][x+1]==1) count++;
                    if (x>0 && board[y][x-1]==1) count++;
                    if (x<gridSize && board[y][x+1]==1) count++;
                    if (x>0 && y<gridSize && board[y+1][x-1]==1)count++;
                    if (y<gridSize && board[y+1][x]==1) count++; //something wrong with this
                    if (x<gridSize && y<gridSize && board[y+1][x+1]==1) count++;
                    if(count < 2 && board[y][x]==1){
                        board[y][x] = 0;
                    }
                }
            } 
        }
    public static void clearScreen() {
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
                    System.out.print(" ");
                } else {
                    System.out.print("  ");
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
                    System.out.print("□  ");//This is printing out the cell on the grid. It represents an empty/dead cell.
                    else System.out.print("■  ");//this is printing out a cell on the grid if it was occupied/alive
                    System.out.print(" ");
                }
                System.out.println();// This makes the code print out on the next line and not on the same line    
            }
        System.out.println("");
    }
    public void askingUserCoordinates()
        {
            Scanner kb= new Scanner(System.in);
            System.out.println("enter the x co-ordinate of the cell you want to change");//asking for user input, which x co-ordinate needs to be changed
            userPickx=kb.nextInt();//the user input, user types answer on keyboard
            System.out.println("enter the y co-ordinate of the cell you want to change");//asking for user input, which y co-ordinate needs to be changed
            userPicky=kb.nextInt();//the user input, user types answer on keyboard
            board[userPickx][userPicky]=1;//user input changes the cell chosen to be alive/on
        }
}
