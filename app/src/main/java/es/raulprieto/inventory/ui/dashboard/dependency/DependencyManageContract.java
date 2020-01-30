package es.raulprieto.inventory.ui.dashboard.dependency;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.ui.base.BaseView;

/**
 * Interface which corresponds to the contract established between DependencyManageFragment (view)
 * and DependencyManagePresenter (presenter).
 */
public interface DependencyManageContract {

    interface View extends BaseView<Presenter> {
        void onSuccessValidate();

        void setShortnameError(int errorStringId);
    }

    interface Presenter {
        void validateDependency(Dependency dependency);

        void add(Dependency dependency);

        void edit(Dependency dependency);
    }
}
