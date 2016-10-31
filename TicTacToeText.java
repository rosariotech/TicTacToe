package semProjects;

import java.util.Random;
import java.util.Scanner;

public class TicTacToeText 
{
	static int moves = 0;
	static char[] board = new char[9];
	public static boolean oppblock()
	{
		boolean worked = false;
		for (int place = 0; place<9; place++)
		{
			if (board[place]=='o')
			{
			if (place>5)
			{
				if (empty(place-3)&&empty(place-6))
				{
					char trialarray[] = board.clone();
					trialarray [place-3] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place-3] = (char) (place+45);
					else
					{
						worked = true;
						board[place-6] = 'o';
						return worked;
					}
					trialarray[place-6] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place-6] = (char) (place+42);
					else
					{
						worked = true;
						board[place-3] = 'o';
						return worked;
					}
				}
				if (place-6==0)
					if (empty(place+1)&&empty(place+2))
							{
								char[] trialarray = board.clone();
								trialarray [place+2] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+2] = (char) (place+50);
								else
								{
									worked = true;
									board[place+1] = 'o';
									return worked;
								}
								trialarray[place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
								{
									worked = true;
									board[place+2] = 'o';
									return worked;
								}
							}
					else if (place-6==1)
						if (empty(place+2)&&empty(place))
						{
							char[] trialarray = board.clone();
							trialarray [place+2] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place+2] = (char) (place+50);
							else
							{
								worked = true;
								board[place] = 'o';
								return worked;
							}
							trialarray[place] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place] = (char) (place+48);
							else
							{
								worked = true;
								board[place+2] = 'o';
								return worked;
							}
						}
						else if (place-6==2)
							if (empty(place+1)&&empty(place))
							{
								char[] trialarray = board.clone();
								trialarray [place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
								{
									worked = true;
									board[place] = 'o';
									return worked;
								}
								trialarray[place] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place] = (char) (place+48);
								else
								{
									worked = true;
									board[place+1] = 'o';
									return worked;
								}
							}
			}
			else if (place>2)
			{
				if (empty(place-3)&&empty(place+3))
				{
					char trialarray[] = board.clone();
					trialarray [place+3] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place+3] = (char) (place+51);
					else
					{
						worked = true;
						board[place-3] = 'o';
						return worked;
					}
					trialarray[place-3] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place-3] = (char) (place+45);
					else
					{
						worked = true;
						board[place+3] = 'o';
						return worked;
					}
				}
				if (place-3==0)
					if (empty(place+1)&&empty(place+2))
							{
								char[] trialarray = board.clone();
								trialarray [place+2] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+2] = (char) (place+50);
								else
								{
									worked = true;
									board[place+1] = 'o';
									return worked;
								}
								trialarray[place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
								{
									worked = true;
									board[place+2] = 'o';
									return worked;
								}
							}
					else if (place-3==1)
						if (empty(place+2)&&empty(place))
						{
							char[] trialarray = board.clone();
							trialarray [place+2] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place+2] = (char) (place+50);
							else
							{
								worked = true;
								board[place] = 'o';
								return worked;
							}
							trialarray[place] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place] = (char) (place+48);
							else
							{
								worked = true;
								board[place+2] = 'o';
								return worked;
							}
						}
						else if (place-3==2)
							if (empty(place+1)&&empty(place))
							{
								char[] trialarray = board.clone();
								trialarray [place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
								{
									worked = true;
									board[place] = 'o';
									return worked;
								}
								trialarray[place] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place] = (char) (place+48);
								else
								{
									worked = true;
									board[place+1] = 'o';
									return worked;
								}
							}
			}
			else
			{
				if (empty(place+3)&&empty(place+6))
				{
					char trialarray[] = board.clone();
					trialarray [place+3] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place+3] = (char) (place+51);
					else
					{
						worked = true;
						board[place+6] = 'o';
						return worked;
					}
					trialarray[place+6] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place+6] = (char) (place+54);
					else
					{
						worked = true;
						board[place+3] = 'o';
						return worked;
					}
				}
				if (place==0)
					if (empty(place+1)&&empty(place+2))
							{
								char[] trialarray = board.clone();
								trialarray [place+2] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+2] = (char) (place+50);
								else
								{
									worked = true;
									board[place+1] = 'o';
									return worked;
								}
								trialarray[place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
								{
									worked = true;
									board[place+2] = 'o';
									return worked;
								}
							}
					else if (place==1)
						if (empty(place+2)&&empty(place))
						{
							char[] trialarray = board.clone();
							trialarray [place+2] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place+2] = (char) (place+50);
							else
							{
								worked = true;
								board[place] = 'o';
								return worked;
							}
							trialarray[place] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place] = (char) (place+48);
							else
							{
								worked = true;
								board[place+2] = 'o';
								return worked;
							}
						}
						else if (place==2)
							if (empty(place+1)&&empty(place))
							{
								char[] trialarray = board.clone();
								trialarray [place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
								{
									worked = true;
									board[place] = 'o';
									return worked;
								}
								trialarray[place] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place] = (char) (place+48);
								else
								{
									worked = true;
									board[place+1] = 'o';
									return worked;
								}
							}
			}
		}
		}
		return worked;
	}
	public static void main (String[] args)
	{	
		for (int i=0; i<9; i++)
			board[i] = (char) (i+49);
		while (toWin()=='n')
		{		
			printboard();
			input();
			playgame();
		}
		printboard();
		if (toWin()!='t')
			System.out.println("The winner is: " + toWin());
		else
			System.out.println("This game is a tie.");
	}
	public static char toWin()
	{
	   char winner = 'n';
	   for (int i=0; i<3; i++)
	   {
		   if ((board[3*i]==board[3*i+1]&&board[3*i+1]==board[3*i+2])&&(board[3*i]=='x'||board[3*i]=='o'))
			   winner = board[3*i];
		   else if ((board[i]==board[i+3]&&board[i+3]==board[i+6])&&(board[i]=='x'||board[i]=='o'))
			   winner = board[i];
		   else if ((board[0]==board[4]&&board[4]==board[8])&&(board[i]=='x'||board[i]=='o'))
			   winner = board[4];
		   else if ((board[2]==board[4]&&board[4]==board[6])&&(board[i]=='x'||board[i]=='o'))
			   winner = board[4];  
	   }
	   boolean full = true;
	   for (int i=0; i<8; i++)
		   if (empty(i))
			   full = false;
	   if (full == true)
		   winner = 't';
	   return winner;
	 }
	public static int twoinarow()
	{
	   int move = -1;
	   for (int i=0; i<3; i++)
	   {
		   if (board[3*i]==board[3*i+1]&&board[3*i]=='o'&&board[3*i+2]!='x')
			   move = 3*i+2;
		   else if (board[3*i+1]==board[3*i+2]&&board[3*i+1]=='o'&&board[3*i]!='x')
			   move = 3*i;
		   else if (board[3*i]==board[3*i+2]&&board[3*i]=='o'&&board[3*i+1]!='x')
			   move = 3*i+1;
		   else if (board[i]==board[i+3]&&board[i]=='o'&&board[i+6]!='x')
			   move = i+6;
		   else if (board[i+3]==board[i+6]&&board[i+3]=='o'&&board[i]!='x')
			   move = i;
		   else if (board[i]==board[i+6]&&board[i]=='o'&&board[i+3]!='x')
			   move = i+3;
		   else if (board[0]==board[4]&&board[0]=='o'&&board[8]!='x')
			   move = 9;
		   else if (board[0]==board[8]&&board[0]=='o'&&board[4]!='x')
			   move = 4;
		   else if (board[8]==board[4]&&board[8]=='o'&&board[0]!='x')
			   move = 0;
		   else if (board[2]==board[4]&&board[2]=='o'&&board[6]!='x')
			   move = 6;
		   else if (board[2]==board[6]&&board[2]=='o'&&board[4]!='x')
			   move = 4;
		   else if (board[4]==board[6]&&board[4]=='o'&&board[2]!='x')
			   move = 2;
	   }
	   return move;
	 }
	public static int block()
	{
	   int move = -1;
	   for (int i=0; i<3; i++)
	   {
		   if (board[3*i]==board[3*i+1]&&board[3*i]=='x'&&board[3*i+2]!='o')
			   move = 3*i+2;
		   else if (board[3*i+1]==board[3*i+2]&&board[3*i+1]=='x'&&board[3*i]!='o')
			   move = 3*i;
		   else if (board[3*i]==board[3*i+2]&&board[3*i]=='x'&&board[3*i+1]!='o')
			   move = 3*i+1;
		   else if (board[i]==board[i+3]&&board[i+3]=='x'&&board[i+6]!='o')
			   move = i+6;
		   else if (board[i+3]==board[i+6]&&board[i+3]=='x'&&board[i]!='o')
			   move = i;
		   else if (board[i]==board[i+6]&&board[i+6]=='x'&&board[i+3]!='o')
			   move = i+3;
		   else if (board[0]==board[4]&&board[0]=='x'&&board[8]!='o')
			   move = 8;
		   else if (board[0]==board[8]&&board[0]=='x'&&board[4]!='o')
			   move = 4;
		   else if (board[8]==board[4]&&board[8]=='x'&&board[0]!='o')
			   move = 0;
		   else if (board[2]==board[4]&&board[2]=='x'&&board[6]!='o')
			   move = 6;
		   else if (board[2]==board[6]&&board[2]=='x'&&board[4]!='o')
			   move = 4;
		   else if (board[4]==board[6]&&board[4]=='x'&&board[2]!='o')
			   move = 2;
	   }
	   return move;
	 }
	public static void playgame()
	{
		if (toWin()=='t')
			return;
		else if (twoinarow()!=-1)
		{
			board[twoinarow()] = 'o';
			return;
		}
		else if (block()!=-1)
		{
			board[block()] = 'o';
			return;
		}
		else if (fork()==true)
			return;
		else if (oppblock()==true)
			return;
		else if (opponentfork()==true)
			return;
		else
			remaining();
	}
	public static void remaining()
	{
		if (empty(4))
		{
			board[4] = 'o';
			return;
		}
		else if (board[0]=='x'&&empty(8))
		{
			board[8] = 'o';
			return;
		}
		else if (board[2]=='x'&&empty(6))
		{
			board[6] = 'o';
			return;
		}
		else if (board[8]=='x'&&empty(0))
		{
			board[0] = 'o';
			return;
		}
		else if (board[6]=='x'&&empty(2))
		{
			board[2] = 'o';
			return;
		}
		else if (empty(0))
		{
			board[0] = 'o';
			return;
		}
		else if (empty(2))
		{
			board[2] = 'o';
			return;
		}
		else if (empty(6))
		{
			board[6] = 'o';
			return;
		}
		else if (empty(8))
		{
			board[8] = 'o';
			return;
		}
		Random rand = new Random();
		boolean works = false;
		int input = 0;
		while (works==false)
		{
			input = rand.nextInt(9);
			if (input>9||input<1);
			else
				if (empty(input));
				else
					works=true;
		}
		board[input] = 'o';
	}
	public static boolean empty (int place)
	{
		boolean empty = true;
		if (board[place]=='x'||board[place]=='o')
			empty = false;
		return empty;
	}
	public static void input()
	{
		Scanner in = new Scanner(System.in);
		boolean works = false;
		int input = 0;
		while (works==false)
		{
			System.out.println("Please enter your move: ");
			input = in.nextInt()-1;
			if (input>8||input<0)
				System.out.println("Invalid option. Please enter a number between 1 to 9.");
			else
				if (board[input]=='x'||board[input]=='o')
					System.out.println("That spot is taken, please enter another spot.");
				else
					works=true;
		}
		board[input] = 'x';	
	}
	public static void printboard()
	{
		for (int row=0; row<3; row++)
		{
			for (int column=0; column<3; column++)
				System.out.print(board[3*row + column]);
			System.out.println();
		}
	}

	public static boolean opponentfork()
	{
		boolean success = true;
		int opponentwins= 0;
		char[] trialboard = board.clone();
		for (int i=0; i<8; i++)
		if (empty(i))
		{
			char[] currentboard = trialboard.clone();
			currentboard[i] = 'o';
			if (opponentforkcheck(currentboard)>opponentforkcheck(trialboard))
			{
				trialboard = currentboard;
				opponentwins = i;
			}
		}
		if (opponentforkcheck(trialboard)<2)
			success = false;
		else
			board[opponentwins] = 'o';
		return success;
	}
	public static int opponentforkcheck(char[]trialboard) 
	{
	   int waystowin = 0;
	   for (int i=0; i<3; i++)
	   {
		if (trialboard[3*i]==trialboard[3*i+1]&&trialboard[3*i]=='x'&&trialboard[3*i+2]!='o')
			   waystowin++;
		if (trialboard[3*i+1]==trialboard[3*i+2]&&trialboard[3*i+1]=='x'&&trialboard[3*i]!='o')
			   waystowin++;
		if (trialboard[3*i]==trialboard[3*i+2]&&trialboard[3*i]=='x'&&trialboard[3*i+1]!='o')
			   waystowin++;
		if (trialboard[i]==trialboard[i+3]&&trialboard[i]=='x'&&trialboard[i+6]!='o')
			   waystowin++;
		if (trialboard[i+3]==trialboard[i+6]&&trialboard[i+3]=='x'&&trialboard[i]!='o')
			   waystowin++;
		if (trialboard[i]==trialboard[i+6]&&trialboard[i]=='x'&&trialboard[i+3]!='o')
			   waystowin++;
		if (trialboard[0]==trialboard[4]&&trialboard[0]=='x'&&trialboard[8]!='o')
			   waystowin++;
		if (trialboard[0]==trialboard[8]&&trialboard[0]=='x'&&trialboard[4]!='o')
			   waystowin++;
		if (trialboard[8]==trialboard[4]&&trialboard[8]=='x'&&trialboard[0]!='o')
			   waystowin++;
		if (trialboard[2]==trialboard[4]&&trialboard[2]=='x'&&trialboard[6]!='o')
			   waystowin++;
		if (trialboard[2]==trialboard[6]&&trialboard[2]=='x'&&trialboard[4]!='o')
			   waystowin++;
		if (trialboard[4]==trialboard[6]&&trialboard[4]=='x'&&trialboard[2]!='o')
			   waystowin++;
	   }
	   return waystowin;
	 }
	public static boolean fork()
	{
		boolean success = true;
		int bestint = 0;
		char[] trialboard = board.clone();
		for (int i=0; i<8; i++)
		{
			char[] currentboard = trialboard.clone();
			currentboard[i] = 'o';
			if ((forkcheck(currentboard)>forkcheck(trialboard))&&empty(i))
			{
				trialboard = currentboard;
				bestint = i;
			}
			currentboard[i] = (char)(i+49);
		}
		if (forkcheck(trialboard)<2)
			success = false;
		else
			board[bestint] = 'o';
		return success;
	}

	public static int forkcheck(char[]trialboard)
	{
	   int waystowin = 0;
	   for (int i=0; i<3; i++)
	   {
		if (trialboard[3*i]==trialboard[3*i+1]&&trialboard[3*i]=='o'&&trialboard[3*i+2]!='x')
			   waystowin++; 
		if (trialboard[3*i+1]==trialboard[3*i+2]&&trialboard[3*i+1]=='o'&&trialboard[3*i]!='x')
			   waystowin++;
		if (trialboard[3*i]==trialboard[3*i+2]&&trialboard[3*i]=='o'&&trialboard[3*i+1]!='x')
			   waystowin++; 
		if (trialboard[i]==trialboard[i+3]&&trialboard[i]=='o'&&trialboard[i+6]!='x')
			   waystowin++; 
		if (trialboard[i+3]==trialboard[i+6]&&trialboard[i+3]=='o'&&trialboard[i]!='x')
			   waystowin++; 
		if (trialboard[i]==trialboard[i+6]&&trialboard[i]=='o'&&trialboard[i+3]!='x')
			   waystowin++; 
		if (trialboard[0]==trialboard[4]&&trialboard[0]=='o'&&trialboard[8]!='x')
			   waystowin++; 
		if (trialboard[0]==trialboard[8]&&trialboard[0]=='o'&&trialboard[4]!='x')
			   waystowin++; 
		if (trialboard[8]==trialboard[4]&&trialboard[8]=='o'&&trialboard[0]!='x')
			   waystowin++; 
		if (trialboard[2]==trialboard[4]&&trialboard[2]=='o'&&trialboard[6]!='x')
			   waystowin++; 
		if (trialboard[2]==trialboard[6]&&trialboard[2]=='o'&&trialboard[4]!='x')
			   waystowin++; 
		if (trialboard[4]==trialboard[6]&&trialboard[4]=='o'&&trialboard[2]!='x')
			   waystowin++; 
	   }
	   return waystowin;
	 }
}