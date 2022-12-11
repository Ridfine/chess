package kurs2_task2.Pieces;

import kurs2_task2.Game.Cell;
import kurs2_task2.Game.Piece;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.HashMap;

public class BlackQueen extends Piece {

    public BlackQueen (){
        super("\u001B[31m♕\u001B[0m",false, true);
    }
    public ArrayList<String> canMoveTo(Cell cell, HashMap<Cell, Piece> cellPiece){
        ArrayList<String> moveCells = new ArrayList<String>();
        EnumSet<Cell.nameLinks> links = EnumSet.range(Cell.nameLinks.toUPLeft, Cell.nameLinks.toLeft);
        for (Cell.nameLinks link: links) {
            if (cell.getLink(link) != null){
                goLink(moveCells,cell.getLink(link),cellPiece, link);
            }
        }
        return moveCells;
    }

    public void goLink(ArrayList<String> moveCells, Cell cell, HashMap<Cell, Piece> cellPiece, Cell.nameLinks link){
        if (cellPiece.containsKey(cell)){// есть ли в этой ячейке фигура?
            if(cellPiece.get(cell).getColor() && cellPiece.get(cell).getCanBeKilled()) {
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
