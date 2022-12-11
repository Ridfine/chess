package kurs2_task2.Pieces;

import kurs2_task2.Game.Cell;
import kurs2_task2.Game.Piece;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class WhiteKnight extends Piece {
    public  WhiteKnight (){
        super("\u001B[33m♞\u001B[0m", true, true);
    }

    public ArrayList<String> canMoveTo(Cell cell, HashMap<Cell, Piece> cellPiece){
        ArrayList<String> moveList = new ArrayList<String>();
        Cell temp;
        EnumSet<Cell.nameLinks> linksFirst = EnumSet.of(Cell.nameLinks.toUP, Cell.nameLinks.toRight, Cell.nameLinks.toDown, Cell.nameLinks.toLeft);
        EnumSet<Cell.nameLinks> linksSecond;
        int i = 0;
        for (Cell.nameLinks link : linksFirst){
            if (cell.getLink(link) != null && cell.getLink(link).getLink(link) != null){
                if (i % 2 == 0){
                    linksSecond = EnumSet.of(Cell.nameLinks.toRight, Cell.nameLinks.toLeft);
                }else {
                    linksSecond = EnumSet.of(Cell.nameLinks.toUP, Cell.nameLinks.toDown);
                }

                checkOf(cellPiece, moveList, cell.getLink(link).getLink(link), linksSecond);
                i++;
            }
        }
        return moveList;
    }
    private void checkOf(HashMap<Cell, Piece> cellPiece, ArrayList<String> moveCells, Cell temp,  EnumSet<Cell.nameLinks> linksSecond) {
        for (Cell.nameLinks link : linksSecond){
            if (temp.getLink(link) != null){
                if (!cellPiece.containsKey(temp.getLink(link))){
                    moveCells.add(temp.getLink(link).getCoordinates());
                }else if (cellPiece.containsKey(temp.getLink(link)) && !cellPiece.get(temp.getLink(link)).getColor() && cellPiece.get(temp.getLink(link)).getCanBeKilled()){
                    moveCells.add(temp.getLink(link).getCoordinates());
                }
            }
        }
    }
}
