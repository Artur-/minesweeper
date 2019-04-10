package org.vaadin.artur.minesweeper.component;

public class Cell {

    private String text;
    private boolean marked;
    private boolean revealed;
    private boolean mine;

    public Cell() {
        // No-arg constructor for the model
    }

    public String getText() {
        return text;
    }

    public boolean isMarked() {
        return marked;
    }

    public void setMarked(boolean marked) {
        this.marked = marked;
    }

    public boolean isRevealed() {
        return revealed;
    }

    public void setRevealed(boolean revealed) {
        this.revealed = revealed;
    }

    public boolean isMine() {
        return mine;
    }

    public void setMine(boolean mine) {
        this.mine = mine;
    }

    public void setText(String text) {
        this.text = text;
    }
}