package kurs2_task2.Pieces;

import kurs2_task2.Game.Cell;
import kurs2_task2.Game.Piece;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class BlackPawn extends Piece {
    public BlackPawn (){
        super("\u001B[31m♙\u001B[0m", false, true);
    }
    public ArrayList<String> canMoveTo(Cell cell, HashMap<Cell, Piece> cellPiece){
        ArrayList<String> moveCells = new ArrayList<String>();
        EnumSet<Cell.nameLinks> setLinks = EnumSet.of(Cell.nameLinks.toDownRight, Cell.nameLinks.toDownLeft);
        for  (Cell.nameLinks link : setLinks){
            if (cell.getLink(link) !=null                                        // существет ли ячейка?
                    && cellPiece.containsKey(cell.getLink(link))                 // есть ли в этой ячейке фигура?
                    && cellPiece.get(cell.getLink(link)).getColor()              // эта фигура белая?
                    && cellPiece.get(cell.getLink(link)).getCanBeKilled()){      // не король?

                moveCells.add(cell.getLink(link).getCoordinates());
            }
        }

        if (cell.getLink(Cell.nameLinks.toDown) != null
                && !cellPiece.containsKey(cell.getLink(Cell.nameLinks.toDown))){
            moveCells.add(cell.getLink(Cell.nameLinks.toDown).getCoordinates());

            if (cell.getCoordinates().charAt(0) == '7'
                    && !cellPiece.containsKey(cell.getLink(Cell.nameLinks.toDown).getLink(Cell.nameLinks.toDown))){
                moveCells.add(cell.getLink(Cell.nameLinks.toDown).getLink(Cell.nameLinks.toDown).getCoordinates());
            }
        }
        return moveCells;
    }
}
