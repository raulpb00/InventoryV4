package es.raulprieto.inventory.ui.dependency;

import java.util.List;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.ui.base.BaseView;

public interface DependencyListContract {
    interface View extends BaseView<Presenter> {
        void showProgressBar();

        void hideProgressBar();

        void showImageNoData();

        void hideImageNoData();

        boolean isImageNoDataVisible();

        void clearOutList();

        void onSuccess(List<Dependency> dependencyList);
    }

    interface Presenter {
        boolean delete(Dependency dependency);

        void load();
    }
}
