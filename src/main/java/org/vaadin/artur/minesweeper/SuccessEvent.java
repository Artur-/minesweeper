package org.vaadin.artur.minesweeper;

import com.vaadin.flow.component.ComponentEvent;

import org.vaadin.artur.minesweeper.component.MineField;

public class SuccessEvent extends ComponentEvent<MineField> {
    public SuccessEvent(MineField component) {
        super(component, true);
    }
}