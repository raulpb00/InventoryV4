package es.raulprieto.inventory.ui.dependency;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.FragmentDependencyListBinding;
import es.raulprieto.inventory.ui.adapter.DependencyAdapter;

public class DependencyListFragment extends Fragment implements DependencyListContract.View {

    public static final String TAG = "dependencylistfragment";
    private static final int SPAN_COUNT = 3;
    private FragmentDependencyListBinding binding;
    private FloatingActionButton fab;

    private DependencyAdapter dependencyAdapter;
    private DependencyListContract.Presenter presenter;
    private DependencyAdapter.OnManageDependencyClickListener adapterListener; // Delegate to collect Adapter events
    private OnManageDependencyListener onManageDependencyListener; // Delegate to collect button events

    /**
     * Interface which communicates to the listener that the ManageButton was pressed
     */
    interface OnManageDependencyListener {
        void onManageDependency(Dependency dependency);
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
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dependency_list, container, false);

        View view = binding.getRoot();

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle("Dependency List");
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back, null));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecyclerViewDependency();

        initializeFab();
    }

    private void initializeFab() {
        fab = getActivity().findViewById(R.id.fab);
        fab.show();
        fab.setImageResource(R.drawable.ic_action_add);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onManageDependencyListener.onManageDependency(null);
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

    /**
     * Method that initializes the listener which
     */
    private void initializeListener() {
        adapterListener = new DependencyAdapter.OnManageDependencyClickListener() {

            /**
             * An item from the list is clicked and ManageDependencyFragment should replace
             * the actual fragment with the Dependency object to be edited.
             * @param dependency object pressed
             */
            @Override
            public void onEditDependency(Dependency dependency) {
                onManageDependencyListener.onManageDependency(dependency);
            }

            /**
             * This method shows a dialog box asking for confirmation to delete the Dependency
             * @param dependency object longpressed
             */
            @Override
            public void onDeleteDependency(Dependency dependency) {
                Toast.makeText(getActivity(), "DEP " + dependency.getShortName(), Toast.LENGTH_SHORT).show();
                // TODO DELETE window
            }
        };
        dependencyAdapter.setOnManageDependencyClickListener(adapterListener);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            onManageDependencyListener = (OnManageDependencyListener) context;
        } catch (Exception e) {
            throw new ClassCastException(context.toString() + " must implement OnManageDependencyListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        adapterListener = null;
        onManageDependencyListener = null;
    }

    //region Contract
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showNoData() {

    }

    @Override
    public void showData(List<Dependency> dependencyList) {

    }

    @Override
    public void setDependencyManagePresenter(DependencyListContract.Presenter dependencyManagePresenter) {
        this.presenter = dependencyManagePresenter;
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onSuccess() {

    }
    //endregion
}
