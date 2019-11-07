package es.raulprieto.inventory.ui.dependency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.ActivityDependencyListBinding;
import es.raulprieto.inventory.ui.adapter.DependencyAdapter;

public class DependencyListActivity extends AppCompatActivity {

    private static final int SPAN_COUNT = 3;
    private ActivityDependencyListBinding binding;

    private DependencyAdapter dependencyAdapter;
    private DependencyAdapter.OnDependencyClickListener dependencyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_dependency_list);

        // 1. Create adapter
        dependencyAdapter = new DependencyAdapter();

        // 2. Create Recycler's design
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, SPAN_COUNT,RecyclerView.VERTICAL,false);

        // 3. Link model to view (Recycler --> Adapter)
        binding.rvDependency.setAdapter(dependencyAdapter);

        //4. Assign layoutmanager to view
        binding.rvDependency.setLayoutManager(linearLayoutManager);

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
