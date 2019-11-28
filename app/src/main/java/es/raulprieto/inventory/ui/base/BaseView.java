package es.raulprieto.inventory.ui.base;

/**
 * Base interface for all the project views
 *
 * @param <T>
 */
public interface BaseView<T> {
    void setManagePresenter(T dependencyManagePresenter);

    void showError(String error);

    void onSuccess();
}
