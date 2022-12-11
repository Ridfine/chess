package kurs2_task2.Pieces;

import kurs2_task2.Game.Cell;
import kurs2_task2.Game.Piece;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class WhiteRook extends Piece {
    public WhiteRook (){
        super("\u001B[33m♜\u001B[0m", true, true);
    }
    public ArrayList<String> canMoveTo(Cell cell, HashMap<Cell, Piece> cellPiece){
        ArrayList<String> moveCells = new ArrayList<String>();
        EnumSet<Cell.nameLinks> links = EnumSet.of(Cell.nameLinks.toUP, Cell.nameLinks.toRight, Cell.nameLinks.toDown, Cell.nameLinks.toLeft);
        for (Cell.nameLinks link: links) {
            if (cell.getLink(link) != null){
                goLink(moveCells,cell.getLink(link),cellPiece, link);
            }
        }
        return moveCells;
    }

    public void goLink(ArrayList<String> moveCells, Cell cell, HashMap<Cell, Piece> cellPiece, Cell.nameLinks link){
        if (cellPiece.containsKey(cell)){// есть ли в этой ячейке фигура?
            if(!cellPiece.get(cell).getColor() && cellPiece.get(cell).getCanBeKilled()) {
                moveCells.add(cell.getCoordinates());
            }
            return;
        }else {
            moveCells.add(cell.getCoordinates());
        }
        if (cell.getLink(link) != null){
            goLink(moveCells,cell.getLink(link),cellPiece, link);
        }

    }
}
