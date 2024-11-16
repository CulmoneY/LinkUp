package views;

import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JPanel;
import interface_adapter.ViewManagerModel;

/**
 * The View Manager for the program. It listens for property change events
 * in the ViewManagerModel and updates which View should be visible.
 */
public class ViewManager implements PropertyChangeListener {
    private final CardLayout cardLayout;
    private final JPanel views;
    private final ViewManagerModel viewManagerModel;

    private final Map<String, Object> viewMap = new HashMap<>();

    public ViewManager(JPanel views, CardLayout cardLayout, ViewManagerModel viewManagerModel) {
        this.views = views;
        this.cardLayout = cardLayout;
        this.viewManagerModel = viewManagerModel;
        this.viewManagerModel.addPropertyChangeListener(this);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if ("activeViews".equals(evt.getPropertyName())) {
            final String viewModelName = (String) evt.getNewValue();
            cardLayout.show(views, viewManagerModel.getViewName());
        }
    }

    public void addView(String name, JPanel view) {
        views.add(view, name);
        viewMap.put(name, view);
    }

    public void switchToView(String name) {
        cardLayout.show(views, name);
    }

    public JPanel getMainPanel() {
        return views;
    }

    public String getUsername() {
        return this.viewManagerModel.getUser().getName();
    }

    public Object getView(String name) {
        return viewMap.get(name);
    }
}
