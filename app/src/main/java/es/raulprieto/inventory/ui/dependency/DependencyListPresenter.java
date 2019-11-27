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

    @Override
    public boolean delete(Dependency dependency) {
        return DependencyRepository.getInstance().delete(dependency);
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

    /**
     * We are aware and we will not produce any memory leaks.
     */
    private class LoadDataTask extends AsyncTask<Void, Void, List<Dependency>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
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
            if (dependencies.isEmpty()){
                view.showImageNoData();
                view.clearOutList();
            }
            else {
                if (view.isImageNoDataVisible())
                    view.hideImageNoData();
                view.onSuccess(dependencies);
            }
        }
    }
}
