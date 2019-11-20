package es.raulprieto.inventory.ui.dependency;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.FragmentDependencyListBinding;
import es.raulprieto.inventory.ui.adapter.DependencyAdapter;

public class DependencyListFragment extends Fragment {

    public static final String TAG = "dependencylistfragment";
    private static final int SPAN_COUNT = 3;
    private FragmentDependencyListBinding binding;

    private DependencyAdapter dependencyAdapter;
    private DependencyAdapter.OnDependencyClickListener dependencyListener;
    private OnAddDependencyListener onAddDependencyListener;

    /**
     * Interface which communicates to the listener that the ManageButton was pressed
     */
    interface OnAddDependencyListener {
        void onAddDependency();
    }

    /**
     * This method comes from GOOGLE CREATING FACTORY DESIGN PATTERN
     * Method which creates an object from the own FragmentB class
     * Guaranteeing calling setArguments immediately after the creation.
     *
     * @param bundle arguments
     * @return instance of DependencyListFragment with arguments (if they were necessary)
     */
    public static Fragment newInstance(Bundle bundle) {
        DependencyListFragment fragment = new DependencyListFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onAddDependencyListener = (OnAddDependencyListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement OnAddDependencyListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dependency_list, container, false);

        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecyclerViewDependency();

        initializeFab();
    }

    private void initializeFab() {
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onAddDependencyListener.onAddDependency();
            }
        });
    }

    private void initializeRecyclerViewDependency() {
        // 1. Create adapter
        dependencyAdapter = new DependencyAdapter();

        // 2. Create Recycler's design
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);

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
                Toast.makeText(getActivity(), dependency.getShortName(), Toast.LENGTH_SHORT).show();
            }
        };
        dependencyAdapter.setOnDependencyClickListener(dependencyListener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        dependencyListener = null;
        onAddDependencyListener = null;
    }
}
