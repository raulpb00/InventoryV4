package es.raulprieto.inventory.ui.dependency;

import java.util.List;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.ui.base.BaseView;

public interface DependencyListContract {
    interface View extends BaseView<Presenter> {
        void showProgress();

        void hideProgress();

        void showNoData();

        void showData(List<Dependency> dependencyList);
    }

    interface Presenter {
        void delete(Dependency dependency);

        void load();
    }
}
