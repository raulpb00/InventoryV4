package es.raulprieto.inventory.ui.dependency;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.ActivityDependencyBinding;

public class DependencyActivity extends AppCompatActivity implements DependencyListFragment.OnManageDependencyListener {

    ActivityDependencyBinding binding;

    // Fragments controlled by the Activity
    DependencyListFragment dependencyListFragment;
    DependencyManageFragment dependencyManageFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dependency);

        setSupportActionBar((Toolbar) binding.toolbar);
        ((Toolbar) binding.toolbar).setTitle(R.string.app_name);
        binding.fab.hide();

        showListFragment();
    }

    private void showListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();

        dependencyListFragment = (DependencyListFragment) fragmentManager.findFragmentByTag(DependencyListFragment.TAG);
        if (dependencyListFragment == null) {
            dependencyListFragment = (DependencyListFragment) DependencyListFragment.newInstance(null);
        }

        fragmentManager.beginTransaction().add(R.id.frameContent, dependencyListFragment, DependencyListFragment.TAG).commit();
    }

    private void showManageFragment(Dependency dependency) {
        FragmentManager fragmentManager = getSupportFragmentManager();

        dependencyManageFragment = (DependencyManageFragment) fragmentManager.findFragmentByTag(DependencyManageFragment.TAG);
        Bundle bundle = null;

        if (dependency != null){
            bundle = new Bundle();
            bundle.putSerializable(Dependency.TAG,dependency);
        }

        if (dependencyManageFragment == null)
            dependencyManageFragment = (DependencyManageFragment) DependencyManageFragment.newInstance(bundle);

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
