package views;

import java.awt.CardLayout;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
        views.add(view, name); // Use add(view, name) instead of put
    }

    public void switchToView(String name) {
        cardLayout.show(views, name);
    }

    public JPanel getMainPanel() {
        return views;
    }
}
