package org.vaadin.artur.minesweeper.component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.webcomponent.WebComponentDefinition;

public class MineSweeper extends Div {

    @Tag("mine-sweeper")
    public static class Exporter implements WebComponentExporter<MineSweeper> {

        @Override
        public void define(WebComponentDefinition<MineSweeper> definition) {
            definition.addProperty("rows", 10).onChange((component, value) -> {
                component.setRows(value);
            });
            definition.addProperty("cols", 10).onChange((component, value) -> {
                component.setCols(value);
            });
        }

    }

    private MineField mineField = null;
    private long seed = System.currentTimeMillis();
    private double mineDensity = 20.0 / 100.0;
    private int rows = 10, cols = 10;

    public MineSweeper() {
        getStyle().set("display", "inline-block");
        reinit();
    }

    public MineSweeper(long seed, double mineDensity, int rows, int cols) {
        this.seed = seed;
        this.mineDensity = mineDensity;
        this.rows = rows;
        this.cols = cols;
        getStyle().set("display", "inline-block");
        reinit();
    }

    public void setRows(int rows) {
        this.rows = rows;
        reinit();
    }

    public void setCols(int cols) {
        this.cols = cols;
        reinit();
    }

    private void reinit() {
        removeAll();

        mineField = new MineField(seed, mineDensity, rows, cols);
        mineField.addListeners(e -> {
            // On success
            showMessage("Congratulations!", "Play again");
        }, e -> {
            // On failure
            showMessage("BOOM!", "Try again");
        });
        addComponentAsFirst(mineField);
    }

    private void showMessage(String text, String link) {
        Div fail = new Div();
        fail.add(new Text(text), new Button(link, ee -> {
            reinit();
        }));
        add(fail);
    }

}