package es.raulprieto.inventory.ui.base;

/**
 * Base interface for all the project views
 *
 * @param <T>
 */
public interface BaseListView<T> {
    void setManagePresenter(T ManagePresenter);

    void showError(String error);

    void onSuccess();

    void showProgressBar();

    void hideProgressBar();

    void showImageNoData();

    void hideImageNoData();

    boolean isImageNoDataVisible();

    void clearOutList();

    void onSuccessDeleted();

    void onSuccessUndo();
}
