package app.screen;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class VerticalFlowLayout implements LayoutManager {

    @Override
    public void addLayoutComponent(String name, Component comp) {
        // No es necesario en este caso
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        // No es necesario en este caso
    }

    @Override
    public Dimension preferredLayoutSize(Container parent) {
        int width = 0;
        int height = 0;

        for (Component comp : parent.getComponents()) {
            Dimension compPrefSize = comp.getPreferredSize();
            width = Math.max(width, compPrefSize.width);
            height += compPrefSize.height;
        }

        return new Dimension(width, height);
    }

    @Override
    public Dimension minimumLayoutSize(Container parent) {
        return preferredLayoutSize(parent); // Igual al tamaño preferido
    }

    @Override
    public void layoutContainer(Container parent) {
        int y = 0;

        for (Component comp : parent.getComponents()) {
            Dimension compPrefSize = comp.getPreferredSize();
            comp.setBounds(0, y, parent.getWidth(), compPrefSize.height);
            y += compPrefSize.height;
        }
    }
}