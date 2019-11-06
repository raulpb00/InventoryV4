package es.raulprieto.inventory.ui.dependency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.ActivityDependencyListBinding;
import es.raulprieto.inventory.ui.adapter.DependencyAdapter;

public class DependencyListActivity extends AppCompatActivity {

    private ActivityDependencyListBinding binding;

    private DependencyAdapter dependencyAdapter;
    private DependencyAdapter.OnDependencyClickListener dependencyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dependency_list);

        dependencyAdapter = new DependencyAdapter();

        binding.rvDependency.setAdapter(dependencyAdapter);
        binding.rvDependency.setLayoutManager(new GridLayoutManager(this, 1));

        initializeListener();
    }

    private void initializeListener() {
        dependencyListener = new DependencyAdapter.OnDependencyClickListener() {
            @Override
            public void onClick(Dependency dependency) {
                Toast.makeText(DependencyListActivity.this, dependency.getShortName(), Toast.LENGTH_SHORT).show();
            }
        };
        dependencyAdapter.setOnDependencyClickListener(dependencyListener);
    }
}
