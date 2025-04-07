package app.screen;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import thejavalistener.fwk.awt.splitpane.MySplitPane;

public class MyMultiSplitPane {
    private MySplitPane rootSplitPane;
    private List<MySplitPane> splitPanes = new ArrayList<>();

    public MyMultiSplitPane(int orientation, Component... panels) {
        if (panels == null || panels.length < 2) {
            throw new IllegalArgumentException("Se necesitan al menos 2 componentes.");
        }

        // Comenzar con el primer componente como raíz inicial
        Component currentComponent = panels[0];

        for (int i = 1; i < panels.length; i++) {
            // Crear un nuevo MySplitPane con el componente actual y el siguiente panel
            MySplitPane newSplitPane = new MySplitPane(orientation, currentComponent, panels[i]);
            splitPanes.add(newSplitPane);

            // Actualizar el componente actual como el nuevo MySplitPane
            currentComponent = newSplitPane.c(); // Usamos el método `c()` para obtener el JSplitPane interno
        }

        // El último MySplitPane construido será la raíz
        rootSplitPane = splitPanes.get(splitPanes.size() - 1);
    }

    public Component c()
    {
        return rootSplitPane.c();
    }
    
    public void setDividerLocation(int idxPanel, int locationPx) {
        if (idxPanel < 0 || idxPanel >= splitPanes.size()) {
            throw new IllegalArgumentException("Índice inválido: " + idxPanel + ". Total de divisores: " + splitPanes.size());
        }

        // Si es el primer divisor, simplemente asignamos la ubicación
        if (idxPanel == 0) {
            splitPanes.get(0).setDividerLocation(locationPx);
        } else {
            // Ubicación acumulativa: sumamos la posición de los divisores anteriores
            int cumulativeLocation = locationPx;
            for (int i = 0; i < idxPanel; i++) {
                cumulativeLocation += splitPanes.get(i).c().getDividerLocation();
            }
            splitPanes.get(idxPanel).setDividerLocation(cumulativeLocation);
        }
    }
    public void setDividerSize(int size) {
        for (MySplitPane splitPane : splitPanes) {
            splitPane.setDividerSize(size);
        }
    }

    public void setDividerColor(Color color) {
        for (MySplitPane splitPane : splitPanes) {
            splitPane.setDividerColor(color);
        }
    }
}