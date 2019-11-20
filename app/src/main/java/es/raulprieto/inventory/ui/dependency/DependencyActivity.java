package es.raulprieto.inventory.ui.dependency;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.databinding.ActivityDependencyBinding;

public class DependencyActivity extends AppCompatActivity implements DependencyListFragment.OnAddDependencyListener {

    ActivityDependencyBinding binding;

    // Fragments controlled by the Activity
    DependencyListFragment dependencyListFragment;
    DependencyAddFragment dependencyAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dependency);
        showListFragment();
    }

    private void showListFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyListFragment = (DependencyListFragment) fragmentManager.findFragmentByTag(DependencyListFragment.TAG);
        if (dependencyListFragment == null) {
            dependencyListFragment = (DependencyListFragment) DependencyListFragment.newInstance(null);
            fragmentManager.beginTransaction().add(android.R.id.content, dependencyListFragment, DependencyListFragment.TAG).commit();
        }
    }

    private void showAddFragment() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        dependencyAddFragment = (DependencyAddFragment) DependencyAddFragment.newInstance(null);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        fragmentTransaction.replace(android.R.id.content, dependencyAddFragment, DependencyAddFragment.TAG);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    /**
     * This method shows the DependencyEditFragment
     */
    @Override
    public void onAddDependency() {
        showAddFragment();
    }
}
