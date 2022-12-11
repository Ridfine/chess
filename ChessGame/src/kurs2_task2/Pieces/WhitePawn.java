package kurs2_task2.Pieces;

import kurs2_task2.Game.Cell;
import kurs2_task2.Game.Piece;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class WhitePawn extends Piece {
    public WhitePawn (){
        super("\u001B[33m♟\u001B[0m", true, true);
    }

    public ArrayList<String> canMoveTo(Cell cell, HashMap<Cell, Piece> cellPiece){
        ArrayList<String> moveCells = new ArrayList<String>();
        EnumSet<Cell.nameLinks> setLinks = EnumSet.of(Cell.nameLinks.toUPLeft, Cell.nameLinks.toUPRight);
        for  (Cell.nameLinks link : setLinks){
            if (cell.getLink(link) !=null                                        // существет ли ячейка?
                    && cellPiece.containsKey(cell.getLink(link))                 // есть ли в этой ячейке фигура?
                    && !cellPiece.get(cell.getLink(link)).getColor()             // эта фигура черная?
                    && cellPiece.get(cell.getLink(link)).getCanBeKilled()){      // не король?

                moveCells.add(cell.getLink(link).getCoordinates());
            }
        }

        if (cell.getLink(Cell.nameLinks.toUP) != null
                && !cellPiece.containsKey(cell.getLink(Cell.nameLinks.toUP))){
            moveCells.add(cell.getLink(Cell.nameLinks.toUP).getCoordinates());

            if (cell.getCoordinates().charAt(0) == '2'                             // находится на изначальной позиции?
                    && !cellPiece.containsKey(cell.getLink(Cell.nameLinks.toUP).getLink(Cell.nameLinks.toUP))){
                moveCells.add(cell.getLink(Cell.nameLinks.toUP).getLink(Cell.nameLinks.toUP).getCoordinates());
            }
        }
        return moveCells;
    }
}
