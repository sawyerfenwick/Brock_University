import java.io.*;
import java.net.*;
import java.util.*;

/**
 * @author Sawyer Fenwick  | 6005011
 * @author Daniel Figueroa | 6276463
 * @version 1.0
 *
 * COSC 4P14-A1-Project Part I
 */
public class BattleshipClient {

    public static final String RED = "\033[0;31m";
    public static final String RESET = "\033[0m";

    /**
     * This class defines the rules for the game Battleship
     */
    public static class Battleship {

        //2D Arrays represent game boards
        String[][] playerBoard = new String[10][10];
        String[][] enemyBoard = new String[10][10];
        //1D Arrays represent individual ships
        String[] carrier = new String[5];
        String[] battleship = new String[4];
        String[] cruiser = new String[3];
        String[] submarine = new String[3];
        String[] destroyer = new String[2];
        //boolean flags to check for sunk ships, game over and board set
        boolean carrierSunk, battleshipSunk, cruiserSunk, submarineSunk, destroyerSunk, gameOver, boardSet = false;
        boolean carrierFlag, battleshipFlag, cruiserFlag, submarineFlag, destroyerFlag = false;

        Scanner input = new Scanner(System.in);

        /**
         * Clear all arrays, show player the empty board, let player place pieces
         */
        public void setUpBoard(){
            Arrays.fill(carrier, null);
            Arrays.fill(battleship, null);
            Arrays.fill(cruiser, null);
            Arrays.fill(submarine, null);
            Arrays.fill(destroyer, null);
            showEmptyBoard();
            placePieces();
        }//setUpBoard

        /**
         * Start game
         */
        public void play(){
            if(!boardSet){
                setUpBoard();
            }
            connectToServer();
        }//play

        /**
         * Checks whether game is over by checking if all ships have been sunk
         *
         * @return true if all players ships have been sunk
         */
        public boolean checkGameOver(){
            if(carrierSunk && battleshipSunk && cruiserSunk && submarineSunk && destroyerSunk){
                System.out.println("GAME OVER. YOU LOSE.");
                gameOver = true;
                return true;
            }
            return false;
        }//checkGameOver

        /**
         * Checks if the [i][j] combination is the final attack of a ship
         *
         * @param i row index
         * @param j column index
         */
        public void checkBoats(int i, int j){
            String si = String.valueOf(i);
            String sj = String.valueOf(j);
            if(!checkCarrier(si,sj)){
                if(!checkBattleship(si,sj)){
                    if(!checkCruiser(si,sj)){
                        if(!checkSubmarine(si,sj)){
                            checkDestroyer(si,sj);
                        }
                    }
                }
            }
        }//checkBoats

        /**
         * Checks if the Carrier ship has been sunk
         *
         * @param si row index (A-J)
         * @param sj number index (0-9)
         * @return true if all elements of Carrier array contain "h" - hit
         */
        public boolean checkCarrier(String si, String sj){
            for(int x = 0; x < 5; x++){
                if(String.valueOf(carrier[x].charAt(0)).equals(si) && String.valueOf(carrier[x].charAt(2)).equals(sj)){
                    carrier[x] = "h";
                    System.out.println("OPPONENT HIT your CARRIER");
                    if(carrier[0].equals("h") && carrier[1].equals("h") && carrier[2].equals("h") && carrier[3].equals("h")
                            && carrier[4].equals("h")){
                        System.out.println("OPPONENT sunk your CARRIER");
                        carrierSunk = true;
                    }
                    return true;
                }
            }
            return false;
        }//checkCarrier

        /**
         * Checks if the Battleship has been sunk
         *
         * @param si row index (A-J)
         * @param sj number index (0-9)
         * @return true if all elements of the Battleship array contain "h" - hit
         */
        public boolean checkBattleship(String si, String sj){
            for(int x = 0; x < 4; x++){
                if(String.valueOf(battleship[x].charAt(0)).equals(si) && String.valueOf(battleship[x].charAt(2)).equals(sj)){
                    battleship[x] = "h";
                    System.out.println("OPPONENT HIT your BATTLESHIP");
                    if(battleship[0].equals("h") && battleship[1].equals("h") && battleship[2].equals("h") &&
                            battleship[3].equals("h")) {
                        System.out.println("OPPONENT sunk your BATTLESHIP");
                        battleshipSunk = true;
                    }
                    return true;
                }
            }
            return false;
        }//checkBattleship

        /**
         * Checks if the Cruiser has been sunk
         *
         * @param si row index (A-J)
         * @param sj column index (0-9)
         * @return true if all elements of the Cruiser array contain "h" - hit
         */
        public boolean checkCruiser(String si, String sj){
            for(int x = 0; x < 3; x++){
                if(String.valueOf(cruiser[x].charAt(0)).equals(si) && String.valueOf(cruiser[x].charAt(2)).equals(sj)){
                    cruiser[x] = "h";
                    System.out.println("OPPONENT HIT your CRUISER");
                    if(cruiser[0].equals("h") && cruiser[1].equals("h") && cruiser[2].equals("h")){
                        System.out.println("OPPONENT sunk your CRUISER");
                        cruiserSunk = true;
                    }
                    return true;
                }
            }
            return false;
        }//checkCruiser

        /**
         * Checks if the Submarine has been sunk
         *
         * @param si row index (A-J)
         * @param sj column index (0-9)
         * @return true if all elements of the Submarine array contain "h" - hit
         */
        public boolean checkSubmarine(String si, String sj){
            for(int x = 0; x < 3; x++){
                if(String.valueOf(submarine[x].charAt(0)).equals(si) && String.valueOf(submarine[x].charAt(2)).equals(sj)){
                    submarine[x] = "h";
                    System.out.println("OPPONENT HIT your SUBMARINE");
                    if(submarine[0].equals("h") && submarine[1].equals("h") && submarine[2].equals("h")){
                        System.out.println("OPPONENT sunk your SUBMARINE");
                        submarineSunk = true;
                    }
                    return true;
                }
            }
            return false;
        }//checkSubmarine

        /**
         * Checks if the Destroyer has been sunk
         *
         * @param si row index (A-J)
         * @param sj column index (0-9)
         */
        public void checkDestroyer(String si, String sj){
            for(int x = 0; x < 2; x++){
                if(String.valueOf(destroyer[x].charAt(0)).equals(si) && String.valueOf(destroyer[x].charAt(2)).equals(sj)){
                    destroyer[x] = "h";
                    System.out.println("OPPONENT HIT your DESTROYER");
                    if(destroyer[0].equals("h") && destroyer[1].equals("h")){
                        System.out.println("OPPONENT sunk your DESTROYER");
                        destroyerSunk = true;
                    }
                }
            }
        }//checkDestroyer

        /**
         * Defines the movements of the First Player (Attack first, get Attacked second)
         *
         * @param socket Client socket connected to server
         * @throws IOException if error with BufferedReader
         */
        public void moveFirst(Socket socket) throws IOException {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            //waiting for p2
            String start = in.readLine();

            //p2 has connected
            if(start.equals("start")){
                System.out.println("Connected to Player Two");
            }

            //start playing
            while(!gameOver){
                //attack
                showEnemyBoard();
                String move = attack();
                while(move.equals(" ")){
                    move = attack();
                }
                int i = Character.toLowerCase(move.charAt(0)) - 'a';
                int j = move.charAt(1) - '0';
                //pass this move to p2
                out.println(move);
                //get result of attack
                String result = in.readLine();
                String sunk = in.readLine();    //sunk ship?
                String game = in.readLine();    //game over?

                if(!sunk.equals(" ")){
                    System.out.println("OPPONENT says: You sunk my " + sunk + "!");
                }
                if(game.equals("game over")){
                    System.out.println("HIT");
                    enemyBoard[i][j] = "x";
                    gameOver = true;
                    showEnemyBoard();
                    System.out.println("GAME OVER. YOU WIN!");
                }
                else if(result.equals("h")){
                    System.out.println("HIT");
                    enemyBoard[i][j] = "x";
                    showEnemyBoard();
                }
                else if(result.equals("m")){
                    System.out.println("MISS");
                    enemyBoard[i][j] = "o";
                    showEnemyBoard();
                }
                if(!gameOver){
                    System.out.println("Waiting for OPPONENT to Attack...");
                    //get attacked
                    String attack = in.readLine();
                    System.out.println("OPPONENT attacked " + Character.toUpperCase(attack.charAt(0))+attack.charAt(1));
                    //tell hit or miss
                    i = Character.toLowerCase(attack.charAt(0)) - 'a';
                    j = attack.charAt(1) - '0';

                    if(playerBoard[i][j] == null){
                        //miss
                        System.out.println("OPPONENT MISSED");
                        out.println("m");   //miss
                        out.println(" ");
                        out.println(" ");
                    }
                    else {
                        if(playerBoard[i][j].equals("o")){
                            playerBoard[i][j] = "x";
                            out.println("h");   //hit
                            checkBoats(i,j);
                            if(carrierSunk && !carrierFlag){
                                carrierFlag = true;
                                out.println("CARRIER");
                            }
                            else if(battleshipSunk && !battleshipFlag){
                                battleshipFlag = true;
                                out.println("BATTLESHIP");
                            }
                            else if(cruiserSunk && !cruiserFlag){
                                cruiserFlag = true;
                                out.println("CRUISER");
                            }
                            else if(submarineSunk && !submarineFlag){
                                submarineFlag = true;
                                out.println("SUBMARINE");
                            }
                            else if(destroyerSunk && !destroyerFlag){
                                destroyerFlag = true;
                                out.println("DESTROYER");
                            }
                            else{
                                out.println(" ");   //nothing sunk
                            }
                            if(checkGameOver()){
                                out.println("game over");   //tell opponent they won
                            }
                            else{
                                out.println(" ");           //game not over
                            }
                            showPlayerBoard();
                        }
                    }
                }
            }
        }//moveFirst

        /**
         * Defines the movements of the Second Player (get Attack first, Attack second)
         *
         * @param socket Client socket connected to server
         * @throws IOException if error with BufferedReader
         */
        public void moveSecond(Socket socket) throws IOException {
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while(!gameOver){
                System.out.println("Waiting for OPPONENT to Attack...");
                //get attacked
                String attack = in.readLine();  //hit or miss?
                System.out.println("OPPONENT attacked " + Character.toUpperCase(attack.charAt(0))+attack.charAt(1));
                //tell hit or miss
                int i = Character.toLowerCase(attack.charAt(0)) - 'a';
                int j = attack.charAt(1) - '0';

                if(playerBoard[i][j] == null){      //miss
                    System.out.println("OPPONENT MISSED");
                    out.println("m");
                    out.println(" ");
                    out.println(" ");
                }
                else{
                    if(playerBoard[i][j].equals("o")){
                        playerBoard[i][j] = "x";
                        out.println("h");   //hit
                        checkBoats(i,j);
                        if(carrierSunk && !carrierFlag){
                            carrierFlag = true;
                            out.println("CARRIER");
                        }
                        else if(battleshipSunk && !battleshipFlag){
                            battleshipFlag = true;
                            out.println("BATTLESHIP");
                        }
                        else if(cruiserSunk && !cruiserFlag){
                            cruiserFlag = true;
                            out.println("CRUISER");
                        }
                        else if(submarineSunk && !submarineFlag){
                            submarineFlag = true;
                            out.println("SUBMARINE");
                        }
                        else if(destroyerSunk && !destroyerFlag){
                            destroyerFlag = true;
                            out.println("DESTROYER");
                        }
                        else{
                            out.println(" ");   //nothing sunk
                        }
                        if(checkGameOver()){
                            out.println("game over");   //tell opponent they won
                        }
                        else{
                            out.println(" ");           //game not over
                        }
                        showPlayerBoard();
                    }
                }
                if(!gameOver){
                    //attack
                    showEnemyBoard();
                    String move = attack();
                    while(move.equals(" ")){
                        move = attack();
                    }
                    i = Character.toLowerCase(move.charAt(0)) - 'a';
                    j = move.charAt(1) - '0';
                    //pass this move to p2
                    out.println(move);
                    //get result of attack
                    String result = in.readLine();
                    String sunk = in.readLine();    //sunk ship?
                    String game = in.readLine();    //game over?
                    if(!sunk.equals(" ")){
                        System.out.println("OPPONENT says: You sunk my " + sunk + "!");
                    }
                    if(game.equals("game over")){
                        System.out.println("HIT");
                        enemyBoard[i][j] = "x";
                        showEnemyBoard();
                        System.out.println("GAME OVER. YOU WIN!");
                        gameOver = true;
                    }
                    else if(result.equals("h")){
                        System.out.println("HIT");
                        enemyBoard[i][j] = "x";
                        showEnemyBoard();
                    }
                    else if(result.equals("m")){
                        System.out.println("MISS");
                        enemyBoard[i][j] = "o";
                        showEnemyBoard();
                    }
                }
            }
        }//moveSecond

        /**
         * Reads attack from Player, determines if it is a valid move
         *
         * @return Player move, if it is appropriate form LETTERNUMBER
         * @throws IOException if error with BufferedReader
         */
        public String attack() throws IOException {
            BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

            System.out.print("Your Move: ");
            String move = stdIn.readLine();

            if(isCorrectMove(move)){
                int i = Character.toLowerCase(move.charAt(0)) - 'a';
                int j = move.charAt(1) - '0';
                //Check if already attacked here
                if(enemyBoard[i][j] != null){
                    System.out.println("You have already attacked here. Select another location.");
                    return " ";
                }
                else{
                    return move;
                }
            }
            else{
                System.out.println("Invalid Format. Attacks should be of the form: LETTERNUMBER");
                return " ";
            }
        }//attack

        /**
         * Displays an empty game board
         */
        public void showEmptyBoard(){
            System.out.println("    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
            System.out.println("---------------------------------------------");
            System.out.println("| A |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| B |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| C |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| D |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| E |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| F |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| G |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| H |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| I |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
            System.out.println("| J |   |   |   |   |   |   |   |   |   |   |");
            System.out.println("---------------------------------------------");
        }//showEmptyBoard

        /**
         * Displays the Players game board
         */
        public void showPlayerBoard(){
            System.out.println("                     YOU                     ");
            System.out.println("---------------------------------------------");
            System.out.println("    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
            System.out.println("---------------------------------------------");
            for(int i = 0; i < 10; i ++){
                char c = (char) (i + 65);
                System.out.print("| " + c + " |");
                for(int j = 0; j < 10; j++){
                    if(playerBoard[i][j] == null){
                        System.out.print("   |");
                    }
                    else{
                        if(playerBoard[i][j].equals("x")){
                            System.out.print(" " + RED + playerBoard[i][j] + RESET + " |");
                        }
                        else{
                            System.out.print(" " + playerBoard[i][j] + " |");
                        }
                    }
                }
                System.out.print("\n");
                System.out.println("---------------------------------------------");
            }
        }//showPlayerBoard

        /**
         * Displays the Opponents game board
         */
        public void showEnemyBoard(){
            System.out.println("                  OPPONENT                   ");
            System.out.println("---------------------------------------------");
            System.out.println("    | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
            System.out.println("---------------------------------------------");
            for(int i = 0; i < 10; i ++){
                char c = (char) (i + 65);
                System.out.print("| " + c + " |");
                for(int j = 0; j < 10; j++){
                    if(enemyBoard[i][j] == null){
                        System.out.print("   |");
                    }
                    else{
                        System.out.print(" " + enemyBoard[i][j] + " |");
                    }
                }
                System.out.print("\n");
                System.out.println("---------------------------------------------");
            }
        }//showEnemyBoard

        /**
         * Gets user menu input
         * Options are: Play, Set up Board, Print Instructions, or Quit
         */
        public void menu(){
            String choice;

            while(!gameOver){
                printMenu();
                choice = input.next();

                switch (choice) {
                    //Start Game
                    case "1" -> play();
                    case "2" -> setUpBoard();
                    //Print Instructions
                    case "3" -> printInstructions();
                    //Quit Game
                    case "0" -> {
                        System.out.println("Exiting Game...");
                        //disconnect from socket here
                        System.exit(0);
                    }
                    //Invalid Input
                    default -> System.out.println("Invalid Input.");
                }
            }
        }//menu

        /**
         * Places the Carrier ship on the Players game board
         */
        @SuppressWarnings("SuspiciousNameCombination")
        public void placeCarrier(){
            String placement;
            System.out.println("Place your CARRIER (5): ");
            placement = input.next();
            if(isCorrectFormat(placement)) {
                String[] placementArr = placement.split("-");
                int i, j, x, y, a = 0;
                i = Character.toLowerCase(placementArr[0].charAt(0)) - 'a';
                j = Character.toLowerCase(placementArr[1].charAt(0)) - 'a';
                x = placementArr[0].charAt(1) - '0';
                y = placementArr[1].charAt(1) - '0';
                if (i != j && x != y) {
                    System.out.println("Invalid Input");
                    placeCarrier();
                } else {
                    if (i == j) {
                        if (Math.abs(x - y) == 4) {
                            if(x < y){
                                if(spaceTakenH(i,x,y)){
                                    for(int z = x; z <= y; z++){
                                        carrier[a] = i + "-" + z;
                                        playerBoard[i][z] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeCarrier();
                                }
                            }
                            else if (spaceTakenH(i, y, x)) {
                                for (int z = y; z <= x; z++) {
                                    carrier[a] = i + "-" + z;
                                    playerBoard[i][z] = "o";
                                    a++;
                                }
                            } else {
                                System.out.println("Invalid Placement. A ship is in the way.");
                                placeCarrier();
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeCarrier();
                        }
                    }
                    if (x == y) {
                        if (Math.abs(i - j) == 4) {
                            if(i < j){
                                if(spaceTakenV(x,i,j)){
                                    for(int z = i; z <= j; z++){
                                        carrier[a] = z + "-" + x;
                                        playerBoard[z][x] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeCarrier();
                                }
                            }
                            else{
                                if(spaceTakenV(x, j, i)){
                                    for(int z = j; z <= i; z++){
                                        carrier[a] = z + "-" + x;
                                        playerBoard[z][x] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeCarrier();
                                }
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeCarrier();
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid Format. Input should be of the form: a1-b1");
                placeCarrier();
            }
        }//placeCarrier

        /**
         * Places Battleship on the Players game board
         */
        @SuppressWarnings("SuspiciousNameCombination")
        public void placeBattleship(){
            String placement;
            System.out.println("Place your BATTLESHIP (4): ");
            placement = input.next();
            if(isCorrectFormat(placement)) {
                String[] placementArr = placement.split("-");
                int i, j, x, y, a = 0;
                i = Character.toLowerCase(placementArr[0].charAt(0)) - 'a';
                j = Character.toLowerCase(placementArr[1].charAt(0)) - 'a';
                x = placementArr[0].charAt(1) - '0';
                y = placementArr[1].charAt(1) - '0';
                if (i != j && x != y) {
                    System.out.println("Invalid Input");
                    placeBattleship();
                } else {
                    if (i == j) {
                        if (Math.abs(x - y) == 3) {
                            if(x < y){
                                if(spaceTakenH(i,x,y)){
                                    for(int z = x; z <= y; z++){
                                        battleship[a] = i + "-" + z;
                                        playerBoard[i][z] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeBattleship();
                                }
                            }
                            else if (spaceTakenH(i, y, x)) {
                                for (int z = y; z <= x; z++) {
                                    battleship[a] = i + "-" + z;
                                    playerBoard[i][z] = "o";
                                    a++;
                                }
                            } else {
                                System.out.println("Invalid Placement. A ship is in the way.");
                                placeBattleship();
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeBattleship();
                        }
                    }
                    if (x == y) {
                        if (Math.abs(i - j) == 3) {
                            if(i < j){
                                if(spaceTakenV(x,i,j)){
                                    for(int z = i; z <= j; z++){
                                        battleship[a] = z + "-" + x;
                                        playerBoard[z][x] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeBattleship();
                                }
                            }
                            else{
                                if(spaceTakenV(x, j, i)){
                                    for(int z = j; z <= i; z++){
                                        battleship[a] = z + "-" + x;
                                        playerBoard[z][x] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeBattleship();
                                }
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeBattleship();
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid Format. Input should be of the form: a1-b1");
                placeBattleship();
            }
        }//placeBattleship

        /**
         * Places Cruiser or Submarine on the Players game board
         * @param ship name of ship to place: Cruiser or Submarine
         */
        @SuppressWarnings("SuspiciousNameCombination")
        public void placeCruiserSubmarine(String ship){
            String placement;
            System.out.println("Place your " + ship + " (3): ");
            placement = input.next();
            if(isCorrectFormat(placement)) {
                String[] placementArr = placement.split("-");
                int i, j, x, y, a = 0;
                i = Character.toLowerCase(placementArr[0].charAt(0)) - 'a';
                j = Character.toLowerCase(placementArr[1].charAt(0)) - 'a';
                x = placementArr[0].charAt(1) - '0';
                y = placementArr[1].charAt(1) - '0';
                if (i != j && x != y) {
                    System.out.println("Invalid Input");
                    placeCruiserSubmarine(ship);
                } else {
                    if (i == j) {
                        if (Math.abs(x - y) == 2) {
                            if(x < y){
                                if(spaceTakenH(i,x,y)){
                                    for(int z = x; z <= y; z++){
                                        if(ship.equals("SUBMARINE")){
                                            submarine[a] = i + "-" + z;
                                        }
                                        else{
                                            cruiser[a] = i + "-" + z;
                                        }
                                        playerBoard[i][z] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeCruiserSubmarine(ship);
                                }
                            }
                            else{
                                if(spaceTakenH(i,y,x)){
                                    for(int z = y; z <= x; z++){
                                        if(ship.equals("SUBMARINE")){
                                            submarine[a] = i + "-" + z;
                                        }
                                        else{
                                            cruiser[a] = i + "-" + z;
                                        }
                                        playerBoard[i][z] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeCruiserSubmarine(ship);
                                }
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeCruiserSubmarine(ship);
                        }
                    }
                    if (x == y) {
                        if (Math.abs(i - j) == 2) {
                            if(i < j){
                                if(spaceTakenV(x,i,j)){
                                    for(int z = i; z <= j; z++){
                                        if(ship.equals("SUBMARINE")){
                                            submarine[a] = z + "-" + x;
                                        }
                                        else{
                                            cruiser[a] = z + "-" + x;
                                        }
                                        playerBoard[z][x] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeCruiserSubmarine(ship);
                                }
                            }
                            else{
                                if(spaceTakenV(x,j,i)){
                                    for(int z = j; z <= i; z++){
                                        if(ship.equals("SUBMARINE")){
                                            submarine[a] = z + "-" + x;
                                        }
                                        else{
                                            cruiser[a] = z + "-" + x;
                                        }
                                        playerBoard[z][x] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeCruiserSubmarine(ship);
                                }
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeCruiserSubmarine(ship);
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid Format. Input should be of the form: a1-b1");
                placeCruiserSubmarine(ship);
            }
        }//placeCruiserSubmarine

        /**
         * Places Destroyer on Players game board
         */
        @SuppressWarnings("SuspiciousNameCombination")
        public void placeDestroyer(){
            String placement;
            System.out.println("Place your DESTROYER (2): ");
            placement = input.next();
            if(isCorrectFormat(placement)) {
                String[] placementArr = placement.split("-");
                int i, j, x, y, a = 0;
                i = Character.toLowerCase(placementArr[0].charAt(0)) - 'a';
                j = Character.toLowerCase(placementArr[1].charAt(0)) - 'a';
                x = placementArr[0].charAt(1) - '0';
                y = placementArr[1].charAt(1) - '0';
                if (i != j && x != y) {
                    System.out.println("Invalid Input");
                    placeDestroyer();
                } else {
                    if (i == j) {
                        if (Math.abs(x - y) == 1) {
                            if(x < y){
                                if(spaceTakenH(i,x,y)){
                                    for(int z = x; z <= y; z++){
                                        destroyer[a] = i + "-" + z;
                                        playerBoard[i][z] = "o";
                                        a++;
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeDestroyer();
                                }
                            }
                            else if (spaceTakenH(i, y, x)) {
                                for (int z = y; z <= x; z++) {
                                    destroyer[a] = i + "-" + z;
                                    playerBoard[i][z] = "o";
                                }
                            } else {
                                System.out.println("Invalid Placement. A ship is in the way.");
                                placeDestroyer();
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeDestroyer();
                        }
                    }
                    if (x == y) {
                        if (Math.abs(i - j) == 1) {
                            if(i < j){
                                if(spaceTakenV(x,i,j)){
                                    for(int z = i; z <= j; z++){
                                        playerBoard[z][x] = "o";
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeDestroyer();
                                }
                            }
                            else{
                                if(spaceTakenV(x, j, i)){
                                    for(int z = j; z <= i; z++){
                                        playerBoard[z][x] = "o";
                                    }
                                }
                                else{
                                    System.out.println("Invalid Placement. A ship is in the way.");
                                    placeDestroyer();
                                }
                            }
                        } else {
                            System.out.println("Invalid Placement");
                            placeDestroyer();
                        }
                    }
                }
            }
            else{
                System.out.println("Invalid Format. Input should be of the form: a1-b1");
                placeDestroyer();
            }
        }//placeDestroyer

        /**
         * Checks if the spaces x through y along a given row are taken
         *
         * @param i row number
         * @param x starting number
         * @param y ending number
         * @return true if space is available
         */
        public boolean spaceTakenH(int i, int x, int y){
            for(int z = x; z <= y; z++){
                if(Objects.equals(playerBoard[i][z], "o")){
                    return false;
                }
            }
            return true;
        }//spaceTakenH

        /**
         * Checks if the spaces i through j along a given column are taken
         *
         * @param x column number
         * @param i starting number
         * @param j ending number
         * @return true if space is available
         */
        public boolean spaceTakenV(int x, int i, int j){
            for(int z = i; z <= j; z++){
                if(Objects.equals(playerBoard[z][x], "o")){
                    return false;
                }
            }
            return true;
        }//spaceTakenV

        /**
         * Places all 5 pieces on the board, showing each piece after it has been placed
         */
        public void placePieces(){
            placeCarrier();
            showPlayerBoard();
            placeBattleship();
            showPlayerBoard();
            placeCruiserSubmarine("CRUISER");
            showPlayerBoard();
            placeCruiserSubmarine("SUBMARINE");
            showPlayerBoard();
            placeDestroyer();
            showPlayerBoard();
            boardSet = true;
        }//placePieces

        /**
         * Checks if input is of the correct form for placing ships
         *
         * @param s String input from Client (a1-b1): where they would like to place their ship
         * @return true if input is of form: LETTERNUMBER-LETTERNUMBER
         */
        public boolean isCorrectFormat(String s){
            return s.matches("\\p{Lower}\\d-\\p{Lower}\\d") || s.matches("\\p{Upper}\\d-\\p{Upper}\\d") ||
                    s.matches("\\p{Upper}\\d-\\p{Lower}\\d") || s.matches("\\p{Lower}\\d-\\p{Upper}\\d");
        }//isCorrectFormat

        /**
         * Checks if input is of the correct form for attacking ships
         *
         * @param s String input from Client (a1): where they would like to attack the opponent
         * @return true if input is of form: LETTERNUMBER
         */
        public boolean isCorrectMove(String s){
            return s.matches("\\p{Lower}\\d") || s.matches("\\p{Upper}\\d}");
        }//isCorrectMove

        /**
         * Prints the menu for the game
         */
        public void printMenu(){
            System.out.println("                  BATTLESHIP                 ");
            System.out.println("---------------------------------------------");
            System.out.println("1. Play\n2. Set Board\n3. Instructions\n0. Quit");
            System.out.println("---------------------------------------------");
        }//printMenu

        /**
         * Prints the games instructions
         */
        public void printInstructions(){
            System.out.println("                 Instructions                ");
            System.out.println("---------------------------------------------");
            System.out.println("                    SET UP                   ");
            System.out.println("---------------------------------------------");
            System.out.println("""
                    Each player has 5 ships to place on a 10x10  \s
                    grid. The ships are: CARRIER (size 5),\s
                    BATTLESHIP (size 4), CRUISER (size 3),\s
                    SUBMARINE (size 3), DESTROYER (size 2). When\s
                    prompted to place each ship you will enter the
                    starting point and ending point. If it is a\s
                    valid placement you may place your ship. Ships
                    may not overlap but can be laid next to each\s
                    other. Ships cannot be placed diagonally.\s
                    Ships must be placed in the following format:
                    LETTERNUMBER-LETTERNUMBER
                    The letter can be upper or lower case so long
                    as it is a letter from A-J. Numbers must also
                    range from 0-9.""");
            System.out.println("---------------------------------------------");
            System.out.println("                  GAME PLAY                  ");
            System.out.println("---------------------------------------------");
            System.out.println("""
                    Players take turns guessing the location of
                    the others ships by entering a location on\s
                    the grid. The first player to successfully\s
                    locate all ship pieces (HITs) wins.
                    Hits will be marked with an x.
                    Misses will be marked with an o.
                    First player to connect to the server starts.
                    Ex/YOU: b7 / CPU: MISS / YOU: a4 / CPU: HIT""");
            System.out.println("---------------------------------------------");
        }//printInstructions

        /**
         * Connects to the Server
         * If Client is first to connect to the Server, it waits for a second Client to begin game
         */
        public void connectToServer(){
            String host = "127.0.0.1";
            int port = 4000;

            try (Socket socket = new Socket(host, port)){
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                System.out.println("Connected to the Server");

                String clientID = in.readLine();

                if(clientID.equals("p1")){
                    System.out.println("Waiting for Player Two...");
                    moveFirst(socket);
                }
                else{
                    moveSecond(socket);
                }
            } catch (Exception e){
                System.out.println("Cannot Connect to Server. Please Try Again Later.");
            }
        }//connectToServer
    }//Battleship

    /**
     * Main
     * @param args program parameters
     */
    public static void main(String[] args){
        Battleship battleship = new Battleship();
        battleship.menu();
    }//main
}//BattleshipClient
