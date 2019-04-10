package org.vaadin.artur.minesweeper.component;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.WebComponentExporter;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dependency.StyleSheet;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.webcomponent.WebComponentDefinition;
import com.vaadin.flow.dom.Style;

@StyleSheet("frontend://minesweeper.css")
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
        reinit();
    }

    public MineSweeper(long seed, double mineDensity, int rows, int cols) {
        this.seed = seed;
        this.mineDensity = mineDensity;
        this.rows = rows;
        this.cols = cols;
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
        getStyle().set("display", "inline-block");
        getStyle().set("position", "relative");
        removeAll();

        mineField = new MineField(seed, mineDensity, rows, cols);
        mineField.addListeners(e -> {
            // On success
            showMessage("Congratulations!", "Play again");
        }, e -> {
            // On failure
            Image fail = new Image(
                    "https://upload.wikimedia.org/wikipedia/commons/7/79/Operation_Upshot-Knothole_-_Badger_001.jpg",
                    "");
            Style s = fail.getStyle();
            s.set("position", "absolute");
            s.set("width", "100%");
            s.set("height", "100%");
            s.set("top", "0");
            s.set("opacity", "0.5");

            add(fail);

            Button b = new Button("Try again", ee -> {
                reinit();
            });
            b.getStyle().set("position", "absolute");
            add(b);
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