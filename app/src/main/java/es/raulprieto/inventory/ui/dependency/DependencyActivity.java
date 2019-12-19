package es.raulprieto.inventory.ui.dependency;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.ui.base.BaseActivity;

public class DependencyActivity extends BaseActivity implements DependencyListFragment.OnManageDependencyListener {


    // Fragments controlled by the Activity
    private DependencyListFragment dependencyListFragment;
    private DependencyListPresenter dependencyListPresenter;

    private DependencyManageFragment dependencyManageFragment;
    private DependencyManagePresenter dependencyManagePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_content);

        showListFragment();
    }

    private void showListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        dependencyListFragment = (DependencyListFragment) fragmentManager.findFragmentByTag(DependencyListFragment.TAG);
        if (dependencyListFragment == null) {
            dependencyListFragment = (DependencyListFragment) DependencyListFragment.newInstance(null);
            fragmentManager.beginTransaction().add(R.id.frameContent, dependencyListFragment, DependencyListFragment.TAG).commit();
        }

        /* ************ Contract initialization ************* */
        dependencyListPresenter = new DependencyListPresenter(dependencyListFragment);
        dependencyListFragment.setManagePresenter(dependencyListPresenter);

    }

    private void showManageFragment(Dependency dependency) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        dependencyManageFragment = (DependencyManageFragment) fragmentManager.findFragmentByTag(DependencyManageFragment.TAG);
        Bundle bundle = null;

        if (dependency != null) {
            bundle = new Bundle();
            bundle.putSerializable(Dependency.TAG, dependency);
        }

        if (dependencyManageFragment == null)
            dependencyManageFragment = (DependencyManageFragment) DependencyManageFragment.newInstance(bundle);

        // After creating the view, it's created the Presenter (contract initialization)
        dependencyManagePresenter = new DependencyManagePresenter(dependencyManageFragment);
        dependencyManageFragment.setManagePresenter(dependencyManagePresenter);

        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(R.id.frameContent, dependencyManageFragment, DependencyManageFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }


    /**
     * This method shows the DependencyEditFragment
     */
    @Override
    public void onManageDependency(Dependency dependency) {
        showManageFragment(dependency);
    }
}
