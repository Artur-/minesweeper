package org.vaadin.artur.minesweeper;

import com.vaadin.flow.component.ComponentEvent;

import org.vaadin.artur.minesweeper.component.MineField;

public class FailEvent extends ComponentEvent<MineField> {
    public FailEvent(MineField component) {
        super(component, true);
    }
}