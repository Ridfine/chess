package kurs2_task2.Game;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class Piece{
    private String icon;
    private boolean colorPiece;
    private boolean canBeKilled;

    public abstract ArrayList<String> canMoveTo(Cell cell, HashMap<Cell, Piece> cellPiece);
    public Piece(String icon, boolean colorPiece, boolean canBeKilled) {
        this.icon = icon;
        this.colorPiece = colorPiece;
        this.canBeKilled = canBeKilled;
    }

    public boolean getCanBeKilled(){
        return canBeKilled;
    }

    public String getIcon(){
        return this.icon;
    }

    public Boolean getColor(){
        return this.colorPiece;
    }


}
