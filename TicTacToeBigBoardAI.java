package semProjects;

public class TicTacToeBigBoardAI 
{
	static char[] board = new char[9]; //this character array mirrors the current state of the big board in the main class.
	static int[][] bestmoves = new int [2][9]; //this integer array has two columns - one two hold the place on the board and the other to hold its value
	static int place = 0; //this integer holds information about where on the bestmoves board to place the next integer
	static int points = 0; //this integer holds information about how many points a place should be given
	/*
	 * This is the main method which resets the bestmoves array, considers the board being played as taken, 
	 * and runs other methods to determine moves in order from best (most chances of winning) to worst (least chances of winning).
	 * It decreases the points variable to record a decrease in the quality of move. Throughout the class, there are two ways in which a method
	 * returns a value; it either sends the value back to the function that called it, or it uses the fillboard method to return that value
	 * as a spot on the bestmoves array. Although the latter is technically not "returning" a value, it has the same function, is more efficient
	 * and is easier to program.
	 */
	public static int[][] compplay(char[] board)
	{
		place = 0;
		TicTacToeBigBoardAI.board = board;
		for (int i=0; i<9; i++)
		{
			bestmoves[0][i] = -1;
			bestmoves[1][i] = -1;
		}
	   for (int i=0; i<9; i++)
	   {
		   if (board[i]=='p')
	        		board[i]='o';
	   }
		points = 5;
		if (place<=8)
			twoinarow();
		if (place<=8)
			block();
		points = 4;
		if (place<=8)
			fork();
		if (place<=8)
			oppblock();
		points = 3;
		if (place<=8)
			opponentfork();
		points = 2;
		if (place<=8)
			remaining();
		return bestmoves;
	}
	/*
	 * This function determines if there are two squares in a row for the computer. If so, it plays the third to win the game.
	 */
	public static void twoinarow()
	{
	   for (int i=0; i<3; i++)
	   {
		   if (board[3*i]==board[3*i+1]&&board[3*i]=='o'&&board[3*i+2]!='x')
			   fillplace(3*i+2);
		   else if (board[3*i+1]==board[3*i+2]&&board[3*i+1]=='o'&&board[3*i]!='x')
			   fillplace(3*i);
		   else if (board[3*i]==board[3*i+2]&&board[3*i]=='o'&&board[3*i+1]!='x')
			   fillplace(3*i+1);
		   else if (board[i]==board[i+3]&&board[i]=='o'&&board[i+6]!='x')
			   fillplace(i+6);
		   else if (board[i+3]==board[i+6]&&board[i+3]=='o'&&board[i]!='x')
			   fillplace(i);
		   else if (board[i]==board[i+6]&&board[i]=='o'&&board[i+3]!='x')
			   fillplace(i+3);
		   else if (board[0]==board[4]&&board[0]=='o'&&board[8]!='x')
			   fillplace(8);
		   else if (board[0]==board[8]&&board[0]=='o'&&board[4]!='x')
			   fillplace(4);
		   else if (board[8]==board[4]&&board[8]=='o'&&board[0]!='x')
			   fillplace(0);
		   else if (board[2]==board[4]&&board[2]=='o'&&board[6]!='x')
			   fillplace(6);
		   else if (board[2]==board[6]&&board[2]=='o'&&board[4]!='x')
			   fillplace(4);
		   else if (board[4]==board[6]&&board[4]=='o'&&board[2]!='x')
			   fillplace(2);
	   }
	 }
	/*
	 * This function determines if there are two squares in a row for the opponent. If so, it blocks the opponent's win by playing the third.
	 */
	public static void block()
	{
	   for (int i=0; i<3; i++)
	   {
		   if (board[3*i]==board[3*i+1]&&board[3*i]=='x'&&board[3*i+2]!='o')
			   fillplace(3*i+2);
		   else if (board[3*i+1]==board[3*i+2]&&board[3*i+1]=='x'&&board[3*i]!='o')
			   fillplace(3*i);
		   else if (board[3*i]==board[3*i+2]&&board[3*i]=='x'&&board[3*i+1]!='o')
			   fillplace(3*i+1);
		   else if (board[i]==board[i+3]&&board[i+3]=='x'&&board[i+6]!='o')
			   fillplace(i+6);
		   else if (board[i+3]==board[i+6]&&board[i+3]=='x'&&board[i]!='o')
			   fillplace(i);
		   else if (board[i]==board[i+6]&&board[i+6]=='x'&&board[i+3]!='o')
			   fillplace(i+3);
		   else if (board[0]==board[4]&&board[0]=='x'&&board[8]!='o')
			   fillplace(8);
		   else if (board[0]==board[8]&&board[0]=='x'&&board[4]!='o')
			   fillplace(4);
		   else if (board[8]==board[4]&&board[8]=='x'&&board[0]!='o')
			   fillplace(0);
		   else if (board[2]==board[4]&&board[2]=='x'&&board[6]!='o')
			   fillplace(6);
		   else if (board[2]==board[6]&&board[2]=='x'&&board[4]!='o')
			   fillplace(4);
		   else if (board[4]==board[6]&&board[4]=='x'&&board[2]!='o')
			   fillplace(2);
	   }
	 }
	/*
	 * This method checks if there are any ways of the computer getting multiple opportunities to win. It does this by playing in each empty spot 
	 * on the board, then checking how many wins are possible with that spot played. It returns the spot with the most possible wins (generally 2).
	 */
	public static void fork()
	{
		char[] currentboard = board.clone();
		for (int i=0; i<9; i++)
		if (empty(i))
		{
			currentboard[i] = 'o';
			if (forkcheck(currentboard)>2)
				fillplace(i);
			currentboard[i] = (char)(i+49);
		}
	}
	/*
	 * This method is used in conjunction with the fork method and simply adds up possible computer wins for a given board and returns that number.
	 */
	public static int forkcheck(char[]trialboard)
	{
	   int wins = 0;
	   for (int i=0; i<3; i++)
	   {
		if (trialboard[3*i]==trialboard[3*i+1]&&trialboard[3*i]=='o'&&trialboard[3*i+2]!='x')
			   wins++;
		if (trialboard[3*i+1]==trialboard[3*i+2]&&trialboard[3*i+1]=='o'&&trialboard[3*i]!='x')
			   wins++;
		if (trialboard[3*i]==trialboard[3*i+2]&&trialboard[3*i]=='o'&&trialboard[3*i+1]!='x')
			   wins++;
		if (trialboard[i]==trialboard[i+3]&&trialboard[i]=='o'&&trialboard[i+6]!='x')
			   wins++;
		if (trialboard[i+3]==trialboard[i+6]&&trialboard[i+3]=='o'&&trialboard[i]!='x')
			   wins++;
		if (trialboard[i]==trialboard[i+6]&&trialboard[i]=='o'&&trialboard[i+3]!='x')
			   wins++;
		if (trialboard[0]==trialboard[4]&&trialboard[0]=='o'&&trialboard[8]!='x')
			   wins++;
		if (trialboard[0]==trialboard[8]&&trialboard[0]=='o'&&trialboard[4]!='x')
			   wins++;
		if (trialboard[8]==trialboard[4]&&trialboard[8]=='o'&&trialboard[0]!='x')
			   wins++;
		if (trialboard[2]==trialboard[4]&&trialboard[2]=='o'&&trialboard[6]!='x')
			   wins++;
		if (trialboard[2]==trialboard[6]&&trialboard[2]=='o'&&trialboard[4]!='x')
			   wins++;
		if (trialboard[4]==trialboard[6]&&trialboard[4]=='o'&&trialboard[2]!='x')
			   wins++;
	   }
	   return wins;
	 }
	/*
	 * This method is run to see if there are any possible ways of forcing the user to play a move. This is used so that the user cannot exploit
	 * the opportunity to create a situation in which he has multiple possible ways of winning. If this occurs, the computer will only be able to 
	 * block one of the ways, and so the user can play another way and win. To do so, it tries to find a line (vertical/horizontal/diagonal) which
	 * only has a move by the computer. It then checks the two remaining spots, and sees what will happen if it plays one of these spots. If it
	 * sees that the user will win, it checks the other spot. If it sees the user will not win, it plays that spot.
	 */
	public static void oppblock()
	{
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
						fillplace(place-6);
					trialarray[place-6] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place-6] = (char) (place+42);
					else
						fillplace(place-3);
				}
				if (place-6==0)
					if (empty(place+1)&&empty(place+2))
							{
								char[] trialarray = board.clone();
								trialarray [place+2] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+2] = (char) (place+50);
								else
									fillplace(place+1);
								trialarray[place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
									fillplace(place+2);
							}
					else if (place-6==1)
						if (empty(place+2)&&empty(place))
						{
							char[] trialarray = board.clone();
							trialarray [place+2] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place+2] = (char) (place+50);
							else
								fillplace(place);
							trialarray[place] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place] = (char) (place+48);
							else
								fillplace(place+2);
						}
						else if (place-6==2)
							if (empty(place+1)&&empty(place))
							{
								char[] trialarray = board.clone();
								trialarray [place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
									fillplace(place);
								trialarray[place] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place] = (char) (place+48);
								else
									fillplace(place+1);
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
						fillplace(place-3);
					trialarray[place-3] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place-3] = (char) (place+45);
					else
						fillplace(place+3);
				}
				if (place-3==0)
					if (empty(place+1)&&empty(place+2))
							{
								char[] trialarray = board.clone();
								trialarray [place+2] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+2] = (char) (place+50);
								else
									fillplace(place+1);
								trialarray[place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
									fillplace(place+2);
							}
					else if (place-3==1)
						if (empty(place+2)&&empty(place))
						{
							char[] trialarray = board.clone();
							trialarray [place+2] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place+2] = (char) (place+50);
							else
								fillplace(place);
							trialarray[place] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place] = (char) (place+48);
							else
								fillplace(place+2);
						}
						else if (place-3==2)
							if (empty(place+1)&&empty(place))
							{
								char[] trialarray = board.clone();
								trialarray [place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
									fillplace(place);
								trialarray[place] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place] = (char) (place+48);
								else
									fillplace(place+1);
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
						fillplace(place+6);
					trialarray[place+6] = 'x';
					if (opponentforkcheck(trialarray)>1)
						trialarray[place+6] = (char) (place+54);
					else
						fillplace(place+3);
				}
				if (place==0)
					if (empty(place+1)&&empty(place+2))
							{
								char[] trialarray = board.clone();
								trialarray [place+2] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+2] = (char) (place+50);
								else
									fillplace(place+1);
								trialarray[place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
									fillplace(place+2);
							}
					else if (place==1)
						if (empty(place+2)&&empty(place))
						{
							char[] trialarray = board.clone();
							trialarray [place+2] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place+2] = (char) (place+50);
							else
								fillplace(place);
							trialarray[place] = 'x';
							if (opponentforkcheck(trialarray)>1)
								trialarray[place] = (char) (place+48);
							else
								fillplace(place+2);
						}
						else if (place==2)
							if (empty(place+1)&&empty(place))
							{
								char[] trialarray = board.clone();
								trialarray [place+1] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place+1] = (char) (place+49);
								else
									fillplace(place);

								trialarray[place] = 'x';
								if (opponentforkcheck(trialarray)>1)
									trialarray[place] = (char) (place+48);
								else
									fillplace(place+1);
							}
			}
		}
		}
	}
	/*
	 * This method checks if there are any ways of the user getting multiple opportunities to win. It does this by playing as the user in each 
	 * empty spot on the board, then checking how many wins are possible with that spot played. It returns the spot with the most possible wins 
	 * (generally 2). By playing this spot, the computer takes away the opportunity for the user to gain the upper hand.
	 */
	public static void opponentfork()
	{
		char[] currentboard = board.clone();
		for (int i=0; i<9; i++)
		if (empty(i))
		{
			currentboard[i] = 'o';
			if (opponentforkcheck(currentboard)>2)
				fillplace(i);
			currentboard[i] = (char)(i+49);
		}
	}
	/*
	 * This method is used in conjunction with the opponentfork method and simply adds up possible user wins for a given board and returns that 
	 * number.
	 */
	public static int opponentforkcheck(char[]trialboard) 
	{
	   int wins = 0;
	   for (int i=0; i<3; i++)
	   {
		if (trialboard[3*i]==trialboard[3*i+1]&&trialboard[3*i]=='x'&&trialboard[3*i+2]!='o')
			   wins++;
		if (trialboard[3*i+1]==trialboard[3*i+2]&&trialboard[3*i+1]=='x'&&trialboard[3*i]!='o')
			   wins++;
		if (trialboard[3*i]==trialboard[3*i+2]&&trialboard[3*i]=='x'&&trialboard[3*i+1]!='o')
			   wins++;
		if (trialboard[i]==trialboard[i+3]&&trialboard[i]=='x'&&trialboard[i+6]!='o')
			   wins++;
		if (trialboard[i+3]==trialboard[i+6]&&trialboard[i+3]=='x'&&trialboard[i]!='o')
			   wins++;
		if (trialboard[i]==trialboard[i+6]&&trialboard[i]=='x'&&trialboard[i+3]!='o')
			   wins++;
		if (trialboard[0]==trialboard[4]&&trialboard[0]=='x'&&trialboard[8]!='o')
			   wins++;
		if (trialboard[0]==trialboard[8]&&trialboard[0]=='x'&&trialboard[4]!='o')
			   wins++;
		if (trialboard[8]==trialboard[4]&&trialboard[8]=='x'&&trialboard[0]!='o')
			   wins++;
		if (trialboard[2]==trialboard[4]&&trialboard[2]=='x'&&trialboard[6]!='o')
			   wins++;
		if (trialboard[2]==trialboard[6]&&trialboard[2]=='x'&&trialboard[4]!='o')
			   wins++;
		if (trialboard[4]==trialboard[6]&&trialboard[4]=='x'&&trialboard[2]!='o')
			   wins++;
	   }
	   return wins;
	 }
	/*
	 * This method is used to return the remaining squares in order from best to worst. First it returns the center, then any corners that are
	 * opposite to what the user has played, then any corners, then any remaining squares.
	 */
	public static void remaining()
	{
		if (empty(4))
			fillplace(4);
		if (board[0]=='x'&&empty(8))
			fillplace(8);
		if (board[2]=='x'&&empty(6))
			fillplace(6);
		if (board[8]=='x'&&empty(0))
			fillplace(0);
		if (board[6]=='x'&&empty(2))
			fillplace(2);
		if (empty(0))
			fillplace(0);
		if (empty(2))
			fillplace(2);
		if (empty(6))
			fillplace(6);
		if (empty(8))
			fillplace(8);
		if (empty(1))
			fillplace(1);
		if (empty(3))
			fillplace(3);
		if (empty(5))
			fillplace(5);
		if (empty(7))
			fillplace(7);
	}
	/*
	 * This method simply returns true if a place is empty, and false if the place is full. It is used to prevent the computer from trying to play
	 * a place that has already been taken, and is used by almost all the methods.
	 */
	public static boolean empty (int place)
	{
		if (board[place]=='x'||board[place]=='o'||board[place]=='T')
			return false;
		return true;
	}
	/*
	 * This method fills the next empty spot on the bestmoves array with a given value and the points that value deserves. The value is sent by
	 * an individual method while the points are set by the main method.
	 */
	public static void fillplace (int value)
	{
		boolean contained = false;
		for (int i=0; i<9; i++)
			if (bestmoves[0][i]==value)
				contained = true;
		if (place<=8 && !contained)
		{
			bestmoves[0][place] = value;
			bestmoves[1][place] = points;
			place++;
		}
	}
}