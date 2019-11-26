package es.raulprieto.inventory.ui.dependency;

import java.util.ArrayList;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.data.db.repository.DependencyRepository;

public class DependencyListPresenter implements DependencyListContract.Presenter {
    DependencyListContract.View view;

    public DependencyListPresenter(DependencyListContract.View view) {
        this.view = view;
    }

    @Override
    public void delete(Dependency dependency) {

    }

    @Override
    public void load() {
//        (ArrayList<Dependency>) DependencyRepository.getInstance().getAll();
    }
}
