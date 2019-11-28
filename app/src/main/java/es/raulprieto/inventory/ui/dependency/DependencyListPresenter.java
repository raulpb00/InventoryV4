package es.raulprieto.inventory.ui.dependency;

import android.os.AsyncTask;

import java.util.List;

import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.data.db.repository.DependencyRepository;

public class DependencyListPresenter implements DependencyListContract.Presenter {
    private DependencyListContract.View view;

    DependencyListPresenter(DependencyListContract.View view) {
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
            if (DependencyRepository.getInstance().getAll().isEmpty())
                view.showImageNoData();
            view.onSuccessDeleted();
        }
    }

    @Override
    public void load() {
        new LoadDataTask().execute();

        // Same code, another way to execute an AsyncTask
        /*new AsyncTask<Void, Void, List<Dependency>>() {
            @Override
            protected List<Dependency> doInBackground(Void... voids) {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return (List<Dependency>) DependencyRepository.getInstance().getAll();
            }
        }.execute();*/
    }

    @Override
    public void undoDelete(Dependency dependency) {
        if(DependencyRepository.getInstance().add(dependency))
            view.onSuccessUndo();

        // 1. Check if there were no data
        if (view.isImageNoDataVisible())
            view.hideImageNoData();
    }

    /**
     * We are aware and we will not produce any memory leaks.
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Dependency>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            if (view.isImageNoDataVisible())
                view.hideImageNoData();
            view.showProgressBar();
        }

        @Override
        protected List<Dependency> doInBackground(Void... voids) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return (List<Dependency>) DependencyRepository.getInstance().getAll();
        }

        @Override
        protected void onPostExecute(List<Dependency> dependencies) {
            super.onPostExecute(dependencies);
            view.hideProgressBar();
            if (dependencies.isEmpty()) {
                view.clearOutList();
                view.showImageNoData();
            } else {
                if (view.isImageNoDataVisible())
                    view.hideImageNoData();
                view.onSuccess(dependencies);
            }
        }
    }
}
