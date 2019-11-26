package es.raulprieto.inventory.ui.dependency;

import es.raulprieto.inventory.R;
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
     * 2. ShortName has at least 3 characters
     * 3. ShortName doesn't contain any special character
     * 4. ShortName doesn't exists already
     * <p>
     * 2 & 3 checked with .matches("^[a-zA-Z0-9]{3,}$")
     *
     * @param dependency to validate
     * @return isValid
     */
    @Override
    public boolean validateDependency(Dependency dependency) {
        boolean isValid = true;

        if (dependency.getShortName().matches("^[a-zA-Z0-9]{3,}$")
                & !DependencyRepository.getInstance().exists(dependency.getShortName()))
            view.onSuccessValidate();
        else
            isValid = false;

        return isValid;
    }

    @Override
    public void add(Dependency dependency) {
        if (DependencyRepository.getInstance().add(dependency))
            view.onSuccess();
        else
            view.showError("Something went wrong, Debugging time!");
    }

    @Override
    public void edit(Dependency dependency) {
        if (DependencyRepository.getInstance().edit(dependency))
            view.onSuccess();
        else
            view.showError("Something went wrong, Debugging time!");
    }

}
