package es.raulprieto.inventory.ui.dashboard.dependency;

import java.util.List;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.data.db.repository.DependencyRepository;

public class DependencyListPresenter implements DependencyListContract.Presenter {
    private DependencyListContract.View view;

    public interface DependencyListPresenterListener {
        void onSuccessLoadList(List<Dependency> dependencyList);
    }

    public DependencyListPresenter(DependencyListContract.View view) {
        this.view = view;
    }

    /**
     * Deleting doesn't affect the filters
     * Deleting doesn't affect the order
     *
     * @param dependency to be deleted
     */
    @Override
    public void delete(Dependency dependency) {
        // 1. Realize operation at Repository and check the result
        if (DependencyRepository.getInstance().delete(dependency)) {
            // 1.1. Check if there is no data
            if (DependencyRepository.getInstance().getCount() == 0)
                view.showImageNoData();
            view.onSuccessDeleted();
        }
    }

    @Override
    public void load() {
        List<Dependency> dependencyList;

        if (view.isImageNoDataVisible())
            view.hideImageNoData();
        view.showProgressBar();

        dependencyList = DependencyRepository.getInstance().getList();

        view.hideProgressBar();
        if (DependencyRepository.getInstance().getCount() == 0) {
            view.clearOutList();
            view.showImageNoData();
        } else {
            if (view.isImageNoDataVisible())
                view.hideImageNoData();
            view.onSuccess(dependencyList);
        }
    }

    @Override
    public void undoDelete(Dependency dependency) {
        if (DependencyRepository.getInstance().insert(dependency))
            view.onSuccessUndo();

        // 1. Check if there were no data
        if (DependencyRepository.getInstance().getCount() > 0)
            view.hideImageNoData();
    }

}
