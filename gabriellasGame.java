
/**
 * Write a description of class gabriellasGame here.
 * 
 * @author Gabriella
 * @version 18/05/2023
 * This game is played on a 2D grid, where every square is a cell. It can either be dead or alive,
 * depending on the cells surrounding it. The goal of the game is to make patterns...
 */
import java.util.Scanner;
public class gabriellasGame
{
    // instance variables - replace the example below with your own
    int board[][]=new int[6][6];
    int userPickx=0;
    int userPicky=0;
    /**
     * Constructor for objects of class gabriellasGame
     */
    public gabriellasGame()
    {
        // initialise instance variables
        for (int x=0;x<6;x++)
            {
             for (int y=0;y<6;y++)
                 board[x][y]=0;
            }
        System.out.print("  ");
        for (int i=0;i<6;i++)
            {   
                System.out.print(i);
                System.out.print(" ");
            }
            System.out.println(); 
        for (int x=0;x<6;x++)
            {
                System.out.print(x);
                System.out.print(" ");
                for (int y=0;y<6;y++)
                {
                    if (board[y][x]==0)
                    System.out.print("□ ");
                    else System.out.print("■ ");
                }
                System.out.println();
            }
        System.out.println("");
        Scanner kb= new Scanner(System.in);
        userPickx=kb.nextInt();
        userPicky=kb.nextInt();
        board[userPickx][userPicky]=1;
}
}
