import java.util.Scanner;
import java.lang.Math;

public class TicTacToe{

	private static String[][] board = {{" ","|"," ","|"," "},{"-","-","-","-","-"},{" ","|"," ","|"," "},{"-","-","-","-","-"},{" ","|"," ","|"," "}};
	private static Scanner sc = new Scanner(System.in);
	private static int[] used = new int[9];
	private static int counter = 0;
	private static int[][] wincond = {{1,2,3},{4,5,6},{7,8,9},{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7}};

	public static void main(String[] args){
		printboard();
		for(int i = 0; i < 5; i++){
		playermove();
		
        String win = checkwin();
            if(win.equals("")){               
            }
            else if(win.equals("tie")){
                System.out.print("This is a  Tie!!");
                return;
            }
            else if(win.equals("O") || win.equals("X")){
                System.out.print(win + " wins!!");
                return;
            }
      

        cpumove();
        printboard();
            
           if(win.equals("")){
            }
            else if(win.equals("tie")){
                System.out.print("This is a  Tie!!");
                return;
            }
            else{
                System.out.print(win + " wins!!");
                return;
            }
        
        }
	}
	

	private static void playermove(){
		int x = 0;
		int move = 0;
		System.out.print("Enter your move(1-9):");
		do{
		if(x == 1)
			System.out.print("Please enter an empty spot:");
		else if(x == 2)
			System.out.print("Please enter a number between 1-9:");
		move = sc.nextInt();
		x = 1;

		if(move < 1 || move > 9)
			x = 2;
		else
			x = 1;

		}while(checkused(move) || x == 2);
		used[counter] = move;
		counter++;
		playmove(move, "O");
	}

	private static void cpumove(){
          
        int bestscore = -10000;
        int bestmove = 1;
        
        for(int i = 1; i<10; i++){
            if(checkused(i))
                continue;
            playmove(i, "X");
            int score = minimax(false);
            playmove(i, " ");
            if(score > bestscore){
                bestscore = score;
                bestmove = i;
            }
            
            
        }
        
        used[counter] = bestmove;
        counter++;
		playmove(bestmove, "X");
	

	}

	private static void printboard(){
		
		for(String[] a: board){
			for(String b: a){
			System.out.print(b);
			}
		System.out.println();
		}

	}

	private static int[] movecalc(int move){
		int i = move/3;
		int j = move%3;
		
		if(j == 0){
			--i;
			j = 3;
		}

		j -= 1;
		i += i;
		j += j;
	    
        int[] value = {i, j};
        
        return value;
	}

	private  static boolean checkused(int move){
	
		boolean bool = false;
		for(int a: used){
			if(a == move)
				bool = true;
		}
		
		return bool;

	}

	private static String checkwin (){
    
        int[] playermoves = new int[5];
        int[] cpumoves = new int[4];
        int pcount = 0,ccount = 0, tcount = 0;
       for(int i = 1; i < 10; i++){
           int[] pos = movecalc(i);
           if(board[pos[0]][pos[1]].equals("O")){
               playermoves[pcount++] = i;
               tcount++;
               
           }
           else if(board[pos[0]][pos[1]].equals("X")){
               cpumoves[ccount++] = i;
               tcount++;
           }
       }
           int count = 0;
            String winner = "";
        String[] values = {"O", "X"};
           for(String symb: values){
           for(int[] i: wincond){
               count = 0;
               for(int j: i){
                   int[] pos = movecalc(j);
                   if(board[pos[0]][pos[1]].equals(symb)){
                       count++;
                   }
               }
               if(count == 3)
                   winner = symb;
           }
       }
           
           if(tcount == 9 && winner.equals("")){
               winner = "tie";
           }
           
        return winner;//returns O,X,tie OR ""
       }
         
    private static void playmove(int move, String symbol){
        
        int[] pos = movecalc(move);
        board[pos[0]][pos[1]] = symbol;
        
    }
    
    private static int minimax(boolean ismaximizing){
        int bestscore;
        int score;
        String win = checkwin();

        if(win.equals("O")){
            return -1; 
        }
        else if(win.equals("X")){
            return 1;
        }
        else if(win.equals("tie")){
            return 0;
        }
            
        
                
        if(ismaximizing){
            bestscore = -1000;
            for(int i = 1; i < 10; i++){
                if(checkused(i))
                    continue;
                playmove(i, "X");
                score = minimax(false);
                 playmove(i, " ");
                if(score > bestscore){
                     bestscore = score;
                }
                   
            }
            return bestscore;
        }
        else{
            bestscore = 1000;
            for(int i = 1; i < 10; i++){
                if(checkused(i))
                    continue;
                playmove(i, "O");
                score = minimax( true);
                playmove(i, " ");
                if(score < bestscore){
                    bestscore = score;
                }
            }
            return bestscore;
        }
    }
}
                   
    
   




