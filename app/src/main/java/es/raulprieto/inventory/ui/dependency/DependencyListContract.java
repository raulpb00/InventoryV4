package es.raulprieto.inventory.ui.dependency;

import java.util.List;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.ui.base.BaseListView;

public interface DependencyListContract {
    interface View extends BaseListView<Presenter> {
        void onSuccess(List<Dependency> dependencyList);
    }

    interface Presenter {
        void delete(Dependency dependency);

        void load();

        void undoDelete(Dependency dependency, int position);
    }
}
