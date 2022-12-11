package kurs2_task2.Game;

import kurs2_task2.Pieces.*;
import kurs2_task2.Players.Player;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Game {
    Board board;
    Player playerWhitePieces;
    Player playerBlackPieces;
    private HashMap<Piece, Cell> pieceCell = new HashMap<>();
    private HashMap<Cell, Piece> cellPiece = new HashMap<>();
    private String[] ABC = { "A", "B", "C", "D", "E", "F", "G", "H"};
    public void buildGame() {
        this.board = new Board();
        board.buildBoard();
        createPlayers();
        givePieces();
        arrangePieces(playerWhitePieces.getPieces(), playerBlackPieces.getPieces());
        board.printBoard(cellPiece);  //black - red
                            //white -yellow
        startGame();


    }
    public void startGame() {
        for (int i = 0; i < 50; i++) {
            movePiece(playerWhitePieces, playerBlackPieces);
            board.printBoard(cellPiece);
            movePiece(playerBlackPieces, playerWhitePieces);
            board.printBoard(cellPiece);
        }

    }
    public void movePiece(Player player, Player playerSecond){
        Piece currentPiece;
        ArrayList<String> cellsToMove;
        Cell currentCell;
        String cellCoordinates;
        while (true){
            currentPiece = takeRandomElementOf(player.getPieces());
            
            cellsToMove = currentPiece.canMoveTo(pieceCell.get(currentPiece), cellPiece);
            if (cellsToMove.isEmpty()) continue;
            
            cellCoordinates = takeRandomElementOf(cellsToMove);
            currentCell = board.getCell(cellCoordinates);
            
            if (cellPiece.containsKey(currentCell) && playerSecond.getPieces().contains(cellPiece.get(currentCell))){
                playerSecond.getPieces().remove(cellPiece.get(currentCell));
            }

            cellPiece.put(board.getCell(cellCoordinates), cellPiece.remove(pieceCell.get(currentPiece)));
            pieceCell.put(currentPiece, board.getCell(cellCoordinates));
            break;
        }

    }

    public <T> T takeRandomElementOf( ArrayList<T> list) {
        SecureRandom random = new SecureRandom();
        return list.get(random.nextInt(list.size()));
    }

    public void createPlayers(){
        this.playerWhitePieces = new Player();
        this.playerBlackPieces = new Player();
    }

    public void givePieces(){
        ArrayList<Piece> whitePieces = new ArrayList<>();
        ArrayList<Piece> blackPieces = new ArrayList<>();
        createPieces(whitePieces, blackPieces);
        playerWhitePieces.setPieces(whitePieces);
        playerBlackPieces.setPieces(blackPieces);
    }

    public void arrangePieces(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces){

        int temp = 0;
        for (int i = 7; i < 9; i++) {
            for (int j = 0; j < 8; j++) {
                pieceCell.put(blackPieces.get(temp),  board.getBoard().get(i + ABC[j]));
                cellPiece.put(board.getBoard().get(i + ABC[j]), blackPieces.get(temp));
                temp++;
            }
        }
        temp = 0;
        for (int i = 2; i > 0; i--) {
            for (int j = 0; j < 8; j++) {
                pieceCell.put(whitePieces.get(temp),  board.getBoard().get(i + ABC[j]));
                cellPiece.put(board.getBoard().get(i + ABC[j]), whitePieces.get(temp));
                temp++;
            }
        }
    }

    private void createPieces(ArrayList<Piece> whitePieces, ArrayList<Piece> blackPieces){
        for (int i = 0; i < 8; i++) {
            whitePieces.add(new WhitePawn());
            blackPieces.add(new BlackPawn());
        }
        whitePieces.addAll(Arrays.asList(
                new WhiteRook(), new WhiteKnight(), new WhiteBishop(),
                new WhiteQueen(), new WhiteKing(), new WhiteBishop(),
                new WhiteKnight(), new WhiteRook()));

        blackPieces.addAll(Arrays.asList(
                new BlackRook(), new BlackKnight(), new BlackBishop(),
                new BlackQueen(), new BlackKing(), new BlackBishop(),
                new BlackKnight(), new BlackRook()));
    }
}