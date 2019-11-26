package es.raulprieto.inventory.ui.dependency;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.data.db.repository.DependencyRepository;

class DependencyManagePresenter implements DependencyManageContract.Presenter {
    private DependencyManageContract.View view;


    public DependencyManagePresenter(DependencyManageContract.View view
    ) {
        this.view = view;
    }

    /**
     * Validates RN2,RN3, RN4.
     *
     * @param dependency to validate
     */
    @Override
    public void validateDependency(Dependency dependency) {
//view.showError();
        view.onSuccessValidate();
        // TODO validado modelo de negocio?
    }

    @Override
    public void add(Dependency dependency) {
        DependencyRepository.getInstance().add(dependency);
        view.onSuccess();
    }

    @Override
    public void edit(Dependency dependency) {
        DependencyRepository.getInstance().edit(dependency);
        view.onSuccess();
    }
}
