package org.vaadin.artur.minesweeper.component;

import java.util.List;

import com.vaadin.flow.templatemodel.TemplateModel;

public interface MinesweeperModel extends TemplateModel {
    void setCells(List<List<Cell>> cells);

    List<List<Cell>> getCells();
}
