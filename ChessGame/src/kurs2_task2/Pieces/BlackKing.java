package kurs2_task2.Pieces;

import kurs2_task2.Game.Cell;
import kurs2_task2.Game.Piece;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class BlackKing extends Piece {
    public BlackKing (){
        super("\u001B[31m♔\u001B[0m", false, false);
    }
    public ArrayList<String> canMoveTo(Cell cell, HashMap<Cell, Piece> cellPiece){
        ArrayList<String> moveCells = new ArrayList<String>();
        EnumSet<Cell.nameLinks> links = EnumSet.range(Cell.nameLinks.toUPLeft, Cell.nameLinks.toLeft);
        for (Cell.nameLinks link: links) {
            if (cell.getLink(link) != null){
                if (cellPiece.containsKey(cell.getLink(link))){// есть ли в этой ячейке фигура?
                    if (cellPiece.get(cell.getLink(link)).getColor()){// эта фигура вражеская?
                        moveCells.add(cell.getLink(link).getCoordinates());
                    }
                }else {
                    moveCells.add(cell.getLink(link).getCoordinates());
                }
            }
        }




        return moveCells;
    }

}
