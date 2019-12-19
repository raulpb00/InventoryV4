package es.raulprieto.inventory.ui.dependency;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.FragmentDependencyListBinding;
import es.raulprieto.inventory.ui.adapter.DependencyAdapter;
import es.raulprieto.inventory.ui.base.BaseDialogFragment;
import es.raulprieto.inventory.ui.base.BaseFragment;

public class DependencyListFragment extends BaseFragment implements DependencyListContract.View, BaseDialogFragment.onFinishDialogListener {

    public static final String TAG = "dependencylistfragment";
    public static final int CODE_DELETE = 300;

    private static final int SPAN_COUNT = 3;
    private FragmentDependencyListBinding binding;
    private FloatingActionButton fab;

    private DependencyAdapter adapter;
    private DependencyListContract.Presenter presenter;
    private DependencyAdapter.OnManageDependencyClickListener adapterListener; // Delegate to collect Adapter events
    private OnManageDependencyListener onManageDependencyListener; // Delegate to collect button events

    private Dependency deleted; // Stored when deleting and might be used when restoring
    private Dependency undoDeleted; // Stored when deleting
    private int deletedPosition; // Dependency's index at adapter's List<Dependency> used at UndoAction

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
        // The next line should be named in order to call the Menu Options Creation
        setHasOptionsMenu(true);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        inflater.inflate(R.menu.menu_fragment_dependency_list, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_orderByName:
                Toast.makeText(getActivity(), "Order by Name", Toast.LENGTH_SHORT).show();
                break;
            case R.id.action_orderByInventory:
                Toast.makeText(getActivity(), "Order by Inventory", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dependency_list, container, false);

        View view = binding.getRoot();

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle("Dependency List");


        AppBarLayout appBarLayout = getActivity().findViewById(R.id.app_bar);
        appBarLayout.setLiftOnScrollTargetViewId(binding.rvDependency.getId());

        fab = getActivity().findViewById(R.id.fab);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeRecyclerViewDependency();

        initializeFab();
    }

    /**
     * Requests the data load from the presenter
     */
    @Override
    public void onResume() {
        super.onResume();
        clearOutList();
        presenter.load();
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
    //endregion

    //region Initialization
    private void initializeFab() {
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
        adapter = new DependencyAdapter();

        // 2. Create Recycler's design
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), SPAN_COUNT, RecyclerView.VERTICAL, false);

        // 3. Link model to view (Recycler --> Adapter)
        binding.rvDependency.setAdapter(adapter);

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
            public void onDeleteDependency(Dependency dependency, int position) {
                showDeleteDialog(dependency, position);
            }
        };
        adapter.setOnManageDependencyClickListener(adapterListener);
    }

    /**
     * Method which shows a DialogBox to confirm or dismiss deleting a Dependency
     *
     * @param dependency to be deleted
     */
    private void showDeleteDialog(Dependency dependency, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(BaseDialogFragment.TITLE, "Deleting Dependency");
        bundle.putString(BaseDialogFragment.MESSAGE, "Do you wish to delete \"" + dependency.getShortName() + "\" dependency?");
        BaseDialogFragment baseDialogFragment = BaseDialogFragment.newInstance(bundle);
        baseDialogFragment.setTargetFragment(this, CODE_DELETE);
        baseDialogFragment.show(getFragmentManager(), BaseDialogFragment.TAG);
        deleted = dependency;
        deletedPosition = position;
    }

    private void showSnackBarDelete() {
        Snackbar.make(fab, getString(R.string.action_delete), Snackbar.LENGTH_LONG)
                .setAnchorView(fab)
                .setAction(getString(R.string.action_undo), new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        undoDelete(undoDeleted, deletedPosition);
                    }
                }).setActionTextColor(getResources().getColor(R.color.colorPrimary, null))
                .show();
        // TODO undoDeleted = null; deletedPosition = null; después de desaparecer la barra

    }

    private void undoDelete(Dependency dependency, int deletedPosition) {
        presenter.undoDelete(dependency, deletedPosition);
    }

    //endregion

    //region Contract
    @Override
    public void showProgressBar() {
        binding.pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        binding.pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showImageNoData() {
        binding.ivNoDataFound.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImageNoData() {
        binding.ivNoDataFound.setVisibility(View.GONE);
    }

    @Override
    public boolean isImageNoDataVisible() {
        return binding.ivNoDataFound.getVisibility() == View.VISIBLE;
    }

    @Override
    public void clearOutList() {
        adapter.clear();
        adapter.notifyDataSetChanged();
    }

    /**
     * Update adapter in order to show data.
     *
     * @param dependencyList from presenter
     */
    @Override
    public void onSuccess(List<Dependency> dependencyList) {
        adapter.clear();
        adapter.loadAll(dependencyList);
        // Updates the view
        adapter.notifyDataSetChanged();
    }

    @Override
    public void setManagePresenter(DependencyListContract.Presenter dependencyManagePresenter) {
        this.presenter = dependencyManagePresenter;
    }

    @Override
    public void showError(String error) {

    }

    @Override
    public void onSuccess() {

    }

    /**
     * Method executed when the item is deleted from the repository
     */
    @Override
    public void onSuccessDeleted() {
        adapter.delete(deleted);
        adapter.notifyDataSetChanged();
        undoDeleted = deleted;
        deleted = null;
        // Show Undo option as Snackbar
        showSnackBarDelete();
    }

    @Override
    public void onSuccessUndo() {
        // Used deleted dependency position to insert it back on the same position of the adapter list
        adapter.undo(deletedPosition, undoDeleted);
        adapter.notifyDataSetChanged();
    }

    /**
     * BaseDialogFragment interface
     */
    @Override
    public void onFinishDialog() {
        deleteDependency();
    }

    /**
     * Delete action will delete from repository and adapter, skipping the loading part.
     */
    private void deleteDependency() {
        presenter.delete(deleted);
    }

    //endregion
}
