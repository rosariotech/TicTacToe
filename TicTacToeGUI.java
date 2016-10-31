package semProjects;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

/**
 * @author 16ypande
 * @date Friday October 25
 * @purpose - A variation of tick-tack-toe where the aim is to win three games in a row, column or diagonal.
 */

public class TicTacToeGUI extends JFrame
{
	static char[] bigboardtosend = new char[9]; //this is a board which has all characters in lower case so it can be used with existing algorithms
	static int[][] bestmoves; //this is a board that stores the places to which the minimax algorithm should try to send the game. one of its rows has the move and the other shows how good the move is.
	static int lastmove = 0; //this shows the last move played by the algorithm. it is useful for determining which board the game will go to
	static boolean playingcomp = true; // this is used to toggle between playing the computer and playing another user
	static long startingtime = System.currentTimeMillis(); //this is the starting time to be used by the timer
	static int gamesplayedoverall = 0; // this counts how many small games have been played. it is used in determining if the overall game is a tie
	static int compwins = 0; //this counts the number of small games the computer/player 2 has won. at the end of the game, if no player has 3 in a row, the winner is determined by who has more points
	static int userwins = 0;//this counts the number of small games player 1 has won. at the end of the game, if no player has 3 in a row, the winner is determined by who has more points
	static char[] bigboard = new char[9]; //this is the character rendition of the big board which consists of 9 smaller games.
	static int curgame = 0; //this shows which game is currently being played. this board is also denoted by a 'p' in the bigboard and bigboardtosend arrays. however, it has been converted to integer form for easier access.
	private static int[] moves = new int[9]; //this shows the list of moves played in the current small game. it is useful for determining where the next game will be.
    static char board[] = new char[9]; //this is a character rendition of the smaller board which is currently being played.
    private static JButton smallbuttons[] = new JButton[9]; //this is an array of 9 buttons for the smaller boards.
    private static JButton bigbuttons[] = new JButton[9]; //this is an array of buttons for the bigger board.
	private JButton quitButton,ruleButton,chooseCompetitorButton,timerButton; //these are various buttons, all of which are shown on the top of the game. their names are self explanatory.
    private JPanel wholePanel, boardPanel, titlePanel,biggestPanel; //these are the panels of which the window consists. wholePanel shows the state of the 9 small games; boardPanel shows the state of the current small game; titlePanel has different buttons and a title and is on the top of the screen; biggestPanel is the whole window and consists of the other three panels 
    private JLabel title; //this is simply the banner which reads "welcome to yash's ultimate tic-tac-toe"
    private static int turns = 0; //this shows how many moves have been made in the current small game. it is useful for determining who will end the game (in conjunction with letter) as well as if the game is a tie.
    static String letter = "O"; //this shows which user is currently playing. it is useful for determining which letter the clicked tile should display as well as determining (in conjunction with turns) who will end the game.
    /*
     * This method simply creates a window which houses a TicTacToeMain object.
     */
    public static void main(String[] args) 
    {
        JFrame frame = new TicTacToeGUI();
        frame.setTitle("Tic Tac Toe");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    /*
     * This is the main method of the program, and it does four things.
     * First, it creates two new boards, bigboard and board, filling them with different characters.
     * Second, it sends the bigboard to the bigboardAI to get the best moves possible for the big baord. 
     * Third, it individually calls upon functions that will create objects which will be displayed to the user.
     * Finally, it sets the size and orientation of the screen.
     */
   public TicTacToeGUI()
   {
	 for (int i=0; i<9; i++)
	 {
		 bigboard[i] = 'N';
		 board[i] = (char) (i+48);
	 }
	 choosecompetitionbutton();
	 createTimer();
     createQuitButton();
     createRuleButton();
   	 createTitlePanel();
   	 createBoardPanel();
     createWholePanel();
     createBiggestPanel();
     Toolkit kit = Toolkit.getDefaultToolkit();
     Dimension screenSize = kit.getScreenSize();
     int screenHeight = screenSize.height;
     int screenWidth = screenSize.width;
     setSize(screenWidth, screenHeight);
     setLocation(screenWidth / 2, screenHeight / 2);    
    }
   /*
    * This function creates the timer button on the top of the screen. It does so by subtracting the current system time to the time when the game started.
    * This time is then updated every 1000ms using a timer.
    */
   private void createTimer()
   {
	   timerButton = new JButton("Timer: 0");
       timerButton.setFont(new Font(Font.SERIF, 0, 30));
       class TimerChanger implements ActionListener
       {
           public void actionPerformed(ActionEvent ae) 
           {
        	   long curtime = (System.currentTimeMillis()-startingtime)/1000;
        	   long minutes = curtime/60;
        	   curtime-=minutes*60;
        	   timerButton.setText("Timer: " + minutes + ":" + String.format("%02d", curtime));
           }
       }
       ActionListener timerchanger = new TimerChanger();
	   new Timer(1000, timerchanger).start();
   }
   /*
    * This function creates a quit button that instantly exits the game.
    */
    private void createQuitButton()
    {
    	quitButton = new JButton("Quit");
        quitButton.setFont(new Font(Font.SERIF, 0, 30));
    
        class QuitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent ae) 
            {
                System.exit(0);
            }
        }
 
        ActionListener quitListener = new QuitListener();
        quitButton.addActionListener(quitListener);
    }
    /*
     * This function toggles a boolean that switches between your opponent being the computer and your opponent being another player.
     */
    private void choosecompetitionbutton()
    {
        chooseCompetitorButton = new JButton("Play Against Player");
        chooseCompetitorButton.setFont(new Font(Font.SERIF, 0, 30));
        class ChoiceListener implements ActionListener
        {
            public void actionPerformed(ActionEvent ae) 
            {
                if (playingcomp==true)
                {
                	playingcomp=false;
            		chooseCompetitorButton.setText("Play Against Computer");
                }
                else
                {
                	playingcomp=true;
            		chooseCompetitorButton.setText("Play Against Player");
                }
            }
        }
 
        ActionListener choicelistener = new ChoiceListener();
        chooseCompetitorButton.addActionListener(choicelistener);
    }
    /*
     * Creates a button that shows a popup window with the rules when clicked.
     */
    private void createRuleButton()
    {
    	ruleButton = new JButton("Rules");
        ruleButton.setFont(new Font(Font.SERIF, 0, 30));

        class QuitListener implements ActionListener
        {
            public void actionPerformed(ActionEvent ae) 
            {
                JOptionPane.showMessageDialog(null, "Welcome to Tic-Tac-Toe. This is a variation on the standard game in which there are two baord - a small board and a big board.\n\nWhen you win a game on a small board, you earn a square on the big board.\n\nThe next square on the big board is decided by the place on which the game on the small board ended.\n\nIf this place is not empty, then the place corresponding to the move before that will be used.\n\nIf the game is a tie, and the place corresponding to the last move is empty, the game will be considered a win for the player. (The square will be\nconsidered as being won by the player, although only one point will be given.\n\nIf a place on the big board has not been played, it is denoted by an N.\n\nIf there is a tie on a small board, it shows up as a T on the big board. If either X or O win a small board, their letter shows up on the big board.\n\nThe aim of the game is earning a win on the big board. You do this by getting 3 squares in a row; horizontally, vertically, or diagonally.\n\nIf there is a tie on the big board, the winner of the game is the player with the most points. Wins count as 2 points, ties count as 1.\n\nIf the game is a tie, and both players have the same number of points, the game is a tie.\n\nTo start the game, click on the place on the big board that you want to start with. Good Luck!");
            }
        }
 
        ActionListener quitListener = new QuitListener();
        ruleButton.addActionListener(quitListener);
    }
    /*
     * Creates the top half of the board in a flow layout with the quit button, timer button, rule button, choose competitor button, and game name (title).
     */
    private void createTitlePanel()
    {
        titlePanel = new JPanel();
        titlePanel.setLayout(new FlowLayout());
        title = new JLabel("Welcome to Tic Tac Toe!");
        title.setFont(new Font(Font.SERIF, 0, 30));
        titlePanel.add(timerButton);
        titlePanel.add(ruleButton);
        titlePanel.add(title);
        titlePanel.add(chooseCompetitorButton);
        titlePanel.add(quitButton);
    }
    /*
     * This function resets the board to its original state if the user chooses that he/she wants to play a new game.
     * It does this by resetting all variables, recreating the buttons and repainting the board. It also resets the timer to 0.
     */
    public void bigreset()
    {
    	letter = "O";
    	startingtime = System.currentTimeMillis();
		gamesplayedoverall = 0;
		userwins = 0;
		compwins = 0;
    	for (int i=0; i<9; i++)
    		bigboard[i] = 'N';
    	class ChoiceListener implements ActionListener
        {
             public void actionPerformed(ActionEvent ae) 
             {
                JButton pressedButton = (JButton)ae.getSource();
             	bigboard[Integer.parseInt(pressedButton.getText())-1] = 'p';
             	curgame = Integer.parseInt(pressedButton.getText())-1;
             	reset();
             }
         }
         ActionListener choicelistener = new ChoiceListener();
         remove(biggestPanel);
         biggestPanel.removeAll();
         wholePanel.removeAll();
         for (int i=0; i<9; i++)
         {
             bigbuttons[i] = new JButton(Integer.toString(i+1));
             bigbuttons[i].setFont(new Font(Font.SERIF, 0, 72));
             bigbuttons[i].addActionListener(choicelistener);
             wholePanel.add(bigbuttons[i]);
         }
         wholePanel.revalidate();
         wholePanel.repaint();
         createBiggestPanel();
      	 biggestPanel.revalidate();
       	 biggestPanel.repaint();
       	 add(biggestPanel);
     }
    /*
     * This function resets the main board and all temporary variables after a small game is finished.
     */
    private void reset()
    {
    	for (int i=0; i<9; i++)
    	{
    		board[i] = (char)(i+49);
    		moves[i] = 0;
    	}
		turns = 0;
    	remove(biggestPanel);
        wholePanel.removeAll();
        for (int i=0; i<9; i++)
        {
        	bigbuttons[i] = new JButton(Character.toString(bigboard[i]));
            bigbuttons[i].setFont(new Font(Font.SERIF, 0, 72));
            bigbuttons[i].setEnabled(false);
        	if (bigboard[i]=='p')
        		wholePanel.add(boardPanel);
        	else
        		wholePanel.add(bigbuttons[i]);
        }
        wholePanel.revalidate();
        wholePanel.repaint();
        createBiggestPanel();
        biggestPanel.revalidate();
        biggestPanel.repaint();
    	add(biggestPanel);
        for (int i=0; i<9; i++)
        {
        	smallbuttons[i].setText(Integer.toString(i+1));
            smallbuttons[i].setEnabled(true);
        }
        preparebigboard();
        bestmoves = TicTacToeBigBoardAI.compplay(bigboardtosend);
    }
    /*
     * This function creates a small board with 9 buttons. It is added onto the bigger board by createWholePanel().
     */
    private void createBoardPanel()
    {
        boardPanel = new JPanel(new GridLayout(3,3));
        class ButtonListener implements ActionListener
        {
            public void actionPerformed(ActionEvent ae) 
            {
                if (letter == "O")
                {
            	JButton pressedButton = (JButton)ae.getSource();
                letter = "X";
                board[Integer.parseInt(pressedButton.getText())-1] = 'x';
                moves[turns] = (Integer.parseInt(pressedButton.getText())-1);
                pressedButton.setText(letter);
                pressedButton.setEnabled(false);
                turns++;
                showResults();
                }
                else if (playingcomp==false && letter == "X")
                {
                JButton pressedButton = (JButton)ae.getSource();
                letter = "O";
                board[Integer.parseInt(pressedButton.getText())-1] = 'o';
                moves[turns] = (Integer.parseInt(pressedButton.getText())-1);
                pressedButton.setText(letter);
                pressedButton.setEnabled(false);
                turns++;
                showResults();
                }
                if (playingcomp==true && letter == "X")
                {
                letter = "O";
                turns++;
                int toplay = findBestMove();
                smallbuttons[toplay].setText(letter);
                smallbuttons[toplay].setEnabled(false);
                board[toplay] = 'o';
                moves[turns-1] = toplay;
                showResults();
                }
            }
 
        }
 
        ActionListener buttonListener = new ButtonListener();
        for (int i=0; i<9; i++)
        {
            smallbuttons[i] = new JButton(Integer.toString(i+1));
            smallbuttons[i].setFont(new Font(Font.SERIF, 0, 24));
            smallbuttons[i].addActionListener(buttonListener);
            boardPanel.add(smallbuttons[i]);
        }
    }
    /*
     * This function creates the whole panel which consists of the top panel (titlePanel) and the main panel (wholePanel).
     */
	private void createBiggestPanel()
	{
		biggestPanel = new JPanel();
        biggestPanel.setLayout(new BorderLayout());
		biggestPanel.add(titlePanel, BorderLayout.NORTH);
		biggestPanel.add(wholePanel, BorderLayout.CENTER);
		add(biggestPanel);
	}
	/*
	 * This function creates the main panel (wholePanel). In the beginning, it shows 9 panels and asks the user which one they would like to start with. It is then updated to have 1 current board and 8 boards that display the game state.
	 */
    private void createWholePanel()
    {
        JOptionPane.showMessageDialog(null, "Welcome to Yash's Ultimate Tic-Tac-Toe. If you know the rules of the game, pick a square to start with. Otherwise, click the rules button to get started.");
    	wholePanel = new JPanel(new GridLayout(3,3));
        class ChoiceListener implements ActionListener
        {
            public void actionPerformed(ActionEvent ae) 
            {
                JButton pressedButton = (JButton)ae.getSource();
            	bigboard[Integer.parseInt(pressedButton.getText())-1] = 'p';
            	curgame = Integer.parseInt(pressedButton.getText())-1;
            	reset();
            }
        }
        ActionListener choicelistener = new ChoiceListener();
        for (int i=0; i<9; i++)
        {
            bigbuttons[i] = new JButton(Integer.toString(i+1));
            bigbuttons[i].setFont(new Font(Font.SERIF, 0, 72));
            bigbuttons[i].addActionListener(choicelistener);
            wholePanel.add(bigbuttons[i]);
        }
    }
    /*
     * This function converts the current time of the game into a string that is displayed when the game ends.
     */
    private String timer()
    {
    	String timer = null;
   	  	long curtime = (System.currentTimeMillis()-startingtime)/1000;
   	  	long minutes = curtime/60;
   	  	curtime-=minutes*60;
   	  	if (minutes==0)
   	  		timer = "\nThe game lasted " + curtime + " seconds.";
   	  	else if (minutes==1)
   	  		if (curtime==0)
   	  			timer = "\nThe game lasted " + minutes + " minute.";
   	  		else
   	  			timer = "\nThe game lasted " + minutes + " minute and " + curtime + " seconds.";
   	  	else
   	  		if (curtime==0)
   	  			timer = "\nThe game lasted " + minutes + " minutes.";
   	  		else
   	  			timer = "\nThe game lasted " + minutes + " minutes and " + curtime + " seconds.";
   	  	return timer;
    }
    /*
     * This function checks if the game has been won.
     */
	public static boolean toWin()
	{
		boolean win = false;
		for (int i=0; i<3; i++)
		{
		   if ((board[3*i]==board[3*i+1]&&board[3*i+1]==board[3*i+2])&&(!isempty(3*i, board)))
			   win=true;
		   else if ((board[i]==board[i+3]&&board[i+3]==board[i+6])&&(!isempty(i,board)))
			   win=true;
		   else if ((board[0]==board[4]&&board[4]==board[8])&&(!isempty(0,board)))
			   win=true;
		   else if ((board[2]==board[4]&&board[4]==board[6])&&(!isempty(2,board)))
			   win=true;
		}
		return win;
	}
	/*
	 * This game displays the results of the game. It shows whether or not a small game has been won and whether or not a big game has been won.
	 */
    public void showResults()
    {
    		boolean win = false;
    		String timer = timer();
    		win = toWin();
            if (win==true)
            {
            	win = false;
            	if (letter.equals("O"))
            	{
            		if (playingcomp==true)
            			JOptionPane.showMessageDialog(null, "The computer wins! Better luck next time!");
            		else if (playingcomp==false)
                        JOptionPane.showMessageDialog(null, "Player 2 wins! Congratulations!");
                    compwins+=2;
                    gamesplayedoverall++;
                    bigboard[curgame] = 'O';
            	}
            	else if (letter.equals("X"))
            	{
               		if (playingcomp==true)
            			JOptionPane.showMessageDialog(null, "You win! Congratulations!");
            		else if (playingcomp==false)
                        JOptionPane.showMessageDialog(null, "Player 1 wins! Congratulations!");                    
               		userwins+=2;
                    gamesplayedoverall++;
                    bigboard[curgame] = 'X';
            	}
                for (int i=turns-1; i>=0; i--)
                {
                	if (bigboard[moves[i]]!='X'&&bigboard[moves[i]]!='O'&&bigboard[moves[i]]!='T'&&bigboard[moves[i]]!='p')
                	{
                        curgame = moves[i];
                		bigboard[moves[i]] = 'p';
                		break;
                	}
                }
                preparebigboard();
                if (bigWinCheck(bigboardtosend)=='o')
                {
              	   if (playingcomp==true)
              		   if (JOptionPane.showConfirmDialog(null,"The computer wins the whole game! Better luck next time!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
              			   bigreset();
              		   else
              			   System.exit(0);
              	   if (playingcomp==false)
              		   if (JOptionPane.showConfirmDialog(null,"Player 2 wins the whole game! Congratulations!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
              			   bigreset();
              		   else
              			   System.exit(0);
                }
                else if (bigWinCheck(bigboardtosend)=='x')
                {
               	   if (playingcomp==true)
               		   if (JOptionPane.showConfirmDialog(null,"You win whole game! Congratulations!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
               			   bigreset();
               		   else
               			   System.exit(0);
               	   if (playingcomp==false)
               		   if (JOptionPane.showConfirmDialog(null,"Player 1 wins the whole game! Congratulations!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
               			   bigreset();
               		   else
               			   System.exit(0);
                 }
                else
                	reset();
            }
            
            else if (turns==9 && win==false)
            {
              	if (bigboard[moves[turns-1]]!='X'&&bigboard[moves[turns-1]]!='O'&&bigboard[moves[turns-1]]!='T'&&bigboard[moves[turns-1]]!='p')
            	{
              		char lastplayer = letter.charAt(0);
                    bigboard[curgame] = lastplayer;
                    if (lastplayer == 'O')
                    {
                       	if (playingcomp==true)
                            JOptionPane.showMessageDialog(null, "The game is a tie. The computer gets the square for moving to an empty board.");
                    	if (playingcomp==false)
                            JOptionPane.showMessageDialog(null, "The game is a tie. Player 2 gets the square for moving to an empty board.");
                    	compwins++;
                    }
                    else if (lastplayer == 'X')
                    {
                       	if (playingcomp==true)
                            JOptionPane.showMessageDialog(null, "The game is a tie. You get the square for moving to an empty board.");
                    	if (playingcomp==false)
                            JOptionPane.showMessageDialog(null, "The game is a tie. Player 1 gets the square for moving to an empty board.");
                    	userwins++;
                    }
                    curgame = moves[turns-1];
            		bigboard[moves[turns-1]] = 'p';
            	}      
              	else
              		for (int i=turns-2; i>=0; i--)
              		{
              			if (bigboard[moves[i]]!='X'&&bigboard[moves[i]]!='O'&&bigboard[moves[i]]!='T'&&bigboard[moves[i]]!='p')
              			{
              				bigboard[curgame] = 'T';
              				curgame = moves[i];
              				bigboard[moves[i]] = 'p';
                            JOptionPane.showMessageDialog(null, "The game is a tie.");
              				break;
              			}          
              		}
            	gamesplayedoverall++;
                reset();
            }
            if (gamesplayedoverall==9)
            {
          	   if (compwins>userwins)
               {
               	   if (playingcomp==true)
               		   if (JOptionPane.showConfirmDialog(null,"The computer wins the whole game by having more wins overall! Better luck next time!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
               			   bigreset();
               		   else
               			   System.exit(0);
               	   if (playingcomp==false)
               		   if (JOptionPane.showConfirmDialog(null,"Player 1 wins whole game by having more wins overall! Congratulations!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
               			   bigreset();
               		   else
               			   System.exit(0);
                 }
            	else if (userwins>compwins)
                {
                	   if (playingcomp==true)
                		   if (JOptionPane.showConfirmDialog(null,"You win whole game by having more wins overall! Congratulations!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
                			   bigreset();
                		   else
                			   System.exit(0);
                	   if (playingcomp==false)
                		   if (JOptionPane.showConfirmDialog(null,"Player 1 wins whole game by having more wins overall! Congratulations!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
                			   bigreset();
                		   else
                			   System.exit(0);
                  }
            	else
         		   if (JOptionPane.showConfirmDialog(null,"The game is a tie! Better luck next time!" + timer + "\nWould you like to play a new game?" ,"Game Over", JOptionPane.YES_NO_OPTION)==0)
        			   bigreset();
        		   else
        			   System.exit(0);
            }
    }
    /*
     * This function is part 1 of the minimax algorithm. It finds the best move and plays it.
     * It does this by calling the recursive max algorithm which gives back a number and finds the move which gives the highest number.
     */
	private static int findBestMove() {
		int[] positions = getposition(board);
		int bestmove = -1;
        int previous = Integer.MIN_VALUE;
        for(int i=0; i<positions.length; i++)
        {
        	char[] child = board.clone();
        	child[positions[i]] = 'o';
        	lastmove = positions[i];
            int current = max(child);
            if(current > previous)
            {
                bestmove = positions[i];
                previous = current;
            }
        }
        return bestmove;
    }
    /*
     * This function is part 2 of the minimax algorithm. It returns the move with the highest chance of the computer winning for the current board.
     * It does this by playing an empty move and using the min algorithm to calculate the opponent's move.
     */
    public static int max(char[] Board){
        if (minimaxWinner(Board)=='o')
            return position(true);
        if (minimaxWinner(Board)=='t')
        	return position(false);
        if(minimaxWinner(Board)=='x')
            return -3;
        int best = Integer.MAX_VALUE;
        int[] positions = getposition(Board);
        for(int i=0; i<positions.length; i++)
        {
        	char[] child = Board.clone();
        	child[positions[i]] = 'x';
        	lastmove = positions[i];
            int current = min(child);
            if(current < best)
        		best = current;
        }
        return best;
    }
    
    /*
     * This function is part 3 of the minimax algorithm. It returns the move with the lowest chances of the computer winning for the current board.
     * It does this by playing an empty move and using the max algorithm to calculate the computer's move.
     */
    public static int min(char[] Board){
        if (minimaxWinner(Board)=='o')
            return position(true);
        if (minimaxWinner(Board)=='t')
        	return position(false);
        if(minimaxWinner(Board)=='x')
            return -3;
        int best = Integer.MIN_VALUE;
		int[] positions = getposition(Board);
        for(int i=0; i<positions.length; i++)
        {
        	char[] child = Board.clone();
        	child[positions[i]] = 'o';
        	lastmove = positions[i];
            int current = max(child);
            if(current > best)
            	best = current;
        }
        return best;
    }
/*
 * This function is used by the minimax algorithm. It looks at a given board and returns all the empty positions. 
 * This increases the efficiency of the minimax algorithm.
 */
    public static int[] getposition (char[] Board)
	{
    	int arraylength = 0;
    	for (int i=0; i<9; i++)
    		if (isempty(i, Board))
    			arraylength++;
    	int[] array = new int[arraylength];
    	int place = 0;
    	for (int i=0; i<9; i++)
    		if (isempty(i, Board))
    		{
    			array[place] = i;
    			place++;
    		}
    	return array;
	}
    /*
     * This function checks which returns the winner of a current board (x/o/tie/neither).
     */
	public static char minimaxWinner(char[] Board)
	{
	   char winner = 'n';
	   boolean tie = true;
	   for (int i=0; i<9; i++)
		   if (isempty(i, Board))
			   tie = false;
	   for (int i=0; i<3; i++)
	   {
		   if ((Board[3*i]==Board[3*i+1]&&Board[3*i+1]==Board[3*i+2])&&(Board[3*i]=='x'||Board[3*i]=='o'))
			   winner = Board[3*i];
		   else if ((Board[i]==Board[i+3]&&Board[i+3]==Board[i+6])&&(Board[i]=='x'||Board[i]=='o'))
			   winner = Board[i];
		   else if ((Board[0]==Board[4]&&Board[4]==Board[8])&&(Board[i]=='x'||Board[i]=='o'))
			   winner = Board[4];
		   else if ((Board[2]==Board[4]&&Board[4]==Board[6])&&(Board[i]=='x'||Board[i]=='o'))
			   winner = Board[4];  
	   }
	   if (winner == 'n' && tie == true)
		   return 't';
	   return winner;
	 }
	/*
	 * This function checks if a certain spot on a certain board is empty. It gives back this information as a boolean. it is used by the getposition() and minimaxWinner() algorithms.
	 */
	public static boolean isempty (int place, char[] Board)
	{
		if (Board[place]=='x'||Board[place]=='o')
			return false;
		return true;
	}
	/*
	 * This function is a part of the minimax algorithm. It gives points based on whether or not the game was won or tied and whether or not the next board is in a favorable place.
	 */
	public static int position(boolean win)
	{
		if (win || (turns%2==1))
		{
			for (int i=0; i<9; i++)
				if (bestmoves[0][i] == lastmove)
				{
					if (win)
						return bestmoves[1][i];
					return bestmoves[1][i] - 1;
				}
			if (win)
				return 1;
			return 0;
		}
		for (int i=0; i<9; i++)
			if (bestmoves[0][i]==lastmove)
				return -2;
		return 0;
	}
	/*
	 * This function checks whether or not there is a three in a row on the big board.
	 */
	public static char bigWinCheck(char[] bigboard)
	{
	   char winner = 'n';
	   for (int i=0; i<3; i++)
	   {
		   if ((bigboard[3*i]==bigboard[3*i+1]&&bigboard[3*i+1]==bigboard[3*i+2])&&(bigboard[3*i]=='x'||bigboard[3*i]=='o'))
			   winner = bigboard[3*i];
		   else if ((bigboard[i]==bigboard[i+3]&&bigboard[i+3]==bigboard[i+6])&&(bigboard[i]=='x'||bigboard[i]=='o'))
			   winner = bigboard[i];
		   else if ((bigboard[0]==bigboard[4]&&bigboard[4]==bigboard[8])&&(bigboard[i]=='x'||bigboard[i]=='o'))
			   winner = bigboard[4];
		   else if ((bigboard[2]==bigboard[4]&&bigboard[4]==bigboard[6])&&(bigboard[i]=='x'||bigboard[i]=='o'))
			   winner = bigboard[4];  
	   }
	   return winner;
	 }
	/*
	 * This function converts the bigboard characters to lowercase for use by the bigboard win algorithm and the TicTacToeBigBoardAI.
	 */
	public static void preparebigboard()
	{
        for (int i=0; i<9; i++)
        {
        	if (bigboard[i]=='X')
        		bigboardtosend[i]='x';
        	else if (bigboard[i]=='O')
        		bigboardtosend[i]='o';
        	else
        		bigboardtosend[i] = bigboard[i];
        }
	}
   }
