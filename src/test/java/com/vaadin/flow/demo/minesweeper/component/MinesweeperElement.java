package com.vaadin.flow.demo.minesweeper.component;

import org.openqa.selenium.By;

import com.vaadin.flow.component.html.testbench.DivElement;
import com.vaadin.testbench.TestBenchElement;
import com.vaadin.testbench.elementsbase.Element;

@Element("mine-sweeper")
public class MinesweeperElement extends TestBenchElement {

    public boolean isCellHidden(int row, int col) {
        TestBenchElement cell = getCell(row, col);
        return !cell.hasClassName("empty") && !cell.hasClassName("revealed");
    }

    public boolean isCellEmpty(int row, int col) {
        return getCell(row, col).hasClassName("empty");
    }

    public TestBenchElement getCell(int row, int col) {
        DivElement rowElement = $(DivElement.class).attribute("class", "row")
                .get(row);
        return (TestBenchElement) rowElement.findElements(By.className("cell"))
                .get(col);
    }
}
