package com.vaadin.flow.demo.minesweeper.component;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.chrome.ChromeDriver;

import com.vaadin.flow.component.notification.testbench.NotificationElement;
import com.vaadin.flow.demo.minesweeper.component.data.MineFieldData;
import com.vaadin.testbench.TestBenchTestCase;

public class MinesweeperIT extends TestBenchTestCase {

    private MinesweeperElement minesweeper;

    @Before
    public void setup() {
        setDriver(new ChromeDriver());
    }

    @After
    public void teardown() {
        if (getDriver() != null) {
            getDriver().quit();
        }
    }

    protected void open(String parameters) {
        String url = "http://localhost:8080/?" + parameters;
        getDriver().get(url);
        minesweeper = $(MinesweeperElement.class).first();
    }

    @Test
    public void clickOnEmptyRevealsCell() {
        open("seed=1");
        assertTrue(minesweeper.isCellHidden(0, 0));
        minesweeper.getCell(0, 0).click();
        assertTrue(minesweeper.isCellEmpty(0, 0));
    }

    @Test
    public void clickOnMineShowsNotification() {
        open("seed=1");
        minesweeper.getCell(9, 0).click();
        Assert.assertEquals("BOOM! Reload to try again",
                $(NotificationElement.class).first().getText());
    }

    @Test
    public void solveSeed1Minefield() {
        open("seed=1&rows=10&cols=10");
        MineFieldData mineFieldData = new MineFieldData(10, 10, 1, 20 / 100.0);
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                if (!mineFieldData.isMine(row, col)) {
                    assertTrue(row + "," + col + " is revealed or a mine",
                            minesweeper.isCellHidden(row, col)
                                    || minesweeper.isCellEmpty(row, col));
                    minesweeper.getCell(row, col).click();
                }
            }
        }
        Assert.assertEquals("Congratulations!",
                $(NotificationElement.class).first().getText());
    }
}
