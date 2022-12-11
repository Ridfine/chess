package kurs2_task2.Players;

import kurs2_task2.Game.Piece;

import java.util.ArrayList;

public class Player {
    ArrayList<Piece> Pieces = new ArrayList<>();

    public void setPieces(ArrayList<Piece> Pieces){
        this.Pieces.addAll(0, Pieces);
    }
    public ArrayList<Piece> getPieces(){
        return Pieces;
    }

}
