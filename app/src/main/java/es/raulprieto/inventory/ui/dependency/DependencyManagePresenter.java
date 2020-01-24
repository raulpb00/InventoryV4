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
     * Validates RN2,RN3
     * 2. ShortName has at least 3 characters
     * 3. ShortName doesn't contain any special character
     * <p>
     * 3 checked with .matches("^[a-zA-Z0-9]*$")
     *
     * @param dependency to validate
     */
    @Override
    public void validateDependency(Dependency dependency) {
        if (dependency.getShortName().length() < 3)
            view.setShortnameError(R.string.errInvalidLengthShortName);
        else if (!dependency.getShortName().matches("^[a-zA-Z0-9]*$"))
            view.setShortnameError(R.string.errInvalidCharacterShortName);
        else
            view.onSuccessValidate();
    }

    @Override
    public void add(Dependency dependency) {
        if (DependencyRepository.getInstance().insert(dependency))
            view.onSuccess();
        else
            view.setShortnameError(R.string.errAlreadyExistsShortName);
    }

    @Override
    public void edit(Dependency dependency) {
        if (DependencyRepository.getInstance().update(dependency))
            view.onSuccess();
        else
            view.showError("Something went wrong, Debugging time!");
    }
}
