package es.raulprieto.inventory.data.db.repository;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import es.raulprieto.inventory.data.InventoryDatabase;
import es.raulprieto.inventory.data.dao.DependencyDao;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.ui.dashboard.dependency.DependencyListPresenter;

public class DependencyRepository {
    private static DependencyRepository instance;
    private ArrayList<Dependency> list;
    private DependencyDao dependencyDao;
    private DependencyListPresenter.DependencyListPresenterListener dependencyListPresenterListener;


    private DependencyRepository() {
        dependencyDao = InventoryDatabase.getDatabase().dependencyDao();
    }

    public static DependencyRepository getInstance() {
        if (instance == null)
            instance = new DependencyRepository();

        return instance;
    }

    private void initialize() {
        insert(new Dependency("1º Ciclo Formativo", "1CFGS", "1º Desarrollo de Aplicaciones Multiplataforma", "2020", "unsplash.it/32/32"));
        insert(new Dependency("2º Ciclo Formativo", "2CFGS", "2º Desarrollo de Aplicaciones Multiplataforma", "2020", "unsplash.it/32/32"));
        insert(new Dependency("3º Ciclo Formativo", "3CFGS", "3º Desarrollo de Aplicaciones Multiplataforma", "2018", "unsplash.it/32/32"));
        insert(new Dependency("4º Ciclo Formativo", "4CFGS", "4º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        insert(new Dependency("5º Ciclo Formativo", "5CFGS", "5º Desarrollo de Aplicaciones Multiplataforma", "2018", "unsplash.it/32/32"));
        insert(new Dependency("6º Ciclo Formativo", "6CFGS", "6º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        insert(new Dependency("7º Ciclo Formativo", "7CFGS", "7º Desarrollo de Aplicaciones Multiplataforma", "2018", "unsplash.it/32/32"));
        insert(new Dependency("8º Ciclo Formativo", "8CFGS", "8º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        insert(new Dependency("9º Ciclo Formativo", "9CFGS", "9º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        insert(new Dependency("10º Ciclo Formativo", "10CFGS", "10º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        insert(new Dependency("11º Ciclo Formativo", "11CFGS", "11º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        insert(new Dependency("12º Ciclo Formativo", "12CFGS", "12º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));
        insert(new Dependency("13º Ciclo Formativo", "13CFGS", "13º Desarrollo de Aplicaciones Multiplataforma", "2019", "unsplash.it/32/32"));

    }

    public void getList(DependencyListPresenter.DependencyListPresenterListener dependencyListPresenterListener) {
        if (dependencyListPresenterListener != null)
            this.dependencyListPresenterListener = dependencyListPresenterListener;
        new QueryAsyncTask(dependencyListPresenterListener).execute();
    }

    public List<Dependency> getList() {
        List<Dependency> list = null;

        try {
            list = InventoryDatabase.databaseWriteExecutor.submit(() -> dependencyDao.getAll()).get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            return list;
        }
    }

    public int getCount() {
        try {
            return InventoryDatabase.databaseWriteExecutor.submit(() -> dependencyDao.getCount()).get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * Search a Dependency by its shortname as it's an unique key for Dependency Model
     *
     * @param dependency to check its shortname
     * @return Searched dependency by its primary key
     */
    public boolean findByShortName(Dependency dependency) {
        dependencyDao.findByShortName(dependency.getShortName());
        return true;
    }

    /**
     * Google Solution
     *
     * @param dependency to insert
     * @return if insert was made
     */
    public boolean insert(final Dependency dependency) {
        new InsertAsyncTask().execute(dependency);
        return true;
    }

    public boolean update(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> dependencyDao.update(dependency));
        return true;
    }

    public boolean delete(Dependency dependency) {
        InventoryDatabase.databaseWriteExecutor.execute(() -> dependencyDao.delete(dependency));
        return true;
    }

    //region Soluciones antiguas

    /*
     * Google Solution
     *
     * @param dependency to insert
     * @return if insert was made
     * public boolean insert(final Dependency dependency) {
     * new InsertAsyncTask().execute(dependency);
     * return true;
     * }
     */

    //endregion


    private class QueryAsyncTask extends AsyncTask<Void, Void, List<Dependency>> {
        private DependencyListPresenter.DependencyListPresenterListener dependencyListPresenterListener;

        public QueryAsyncTask(DependencyListPresenter.DependencyListPresenterListener dependencyListPresenterListener) {
            this.dependencyListPresenterListener = dependencyListPresenterListener;
        }

        @Override
        protected List<Dependency> doInBackground(Void... voids) {
            return dependencyDao.getAll();
        }

        @Override
        protected void onPostExecute(List<Dependency> dependencyList) {
            super.onPostExecute(dependencyList);
            dependencyListPresenterListener.onSuccessLoadList(dependencyList);
        }
    }

    /**
     * Insert Async Task.
     */
    private class InsertAsyncTask extends AsyncTask<Dependency, Void, Long> {

        @Override
        protected Long doInBackground(Dependency... dependencies) {
            Long result = dependencyDao.insert(dependencies[0]);

            if (result == -1)
                dependencyDao.update(dependencies[0]);

            return null;
        }
    }
}
