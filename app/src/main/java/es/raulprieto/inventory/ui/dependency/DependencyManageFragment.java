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
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.List;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.FragmentDependencyManageBinding;

/**
 * Style:
 * https://material.io/develop/android/components/text-input-layout/
 */
public class DependencyManageFragment extends Fragment {
    public static final String TAG = "dependencyaddfragment";

    private OnDependencySaveListener onDependencySaveListener;

    private FragmentDependencyManageBinding binding;
    private FloatingActionButton fab;
    ConstraintSet constraintSet;
    private Dependency dependency;

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnDependencySaveListener {
        void onSaveDependency(Dependency dependency);
    }

    public DependencyManageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param bundle Arguments if they exists
     * @return A new instance of fragment DependencyManageFragment.
     */
    public static Fragment newInstance(Bundle bundle) {
        DependencyManageFragment fragment = new DependencyManageFragment();
        if (bundle != null)
            fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dependency_manage, container, false);

        View view = binding.getRoot();

        String title = "Add Dependency";
        Bundle bundle = getArguments();
        if (bundle != null) {
            dependency = (Dependency) bundle.getSerializable("dependency");
            binding.setDependency(dependency);
            setSpinnerSelection();
            title = "Edit Dependency";
        }

        Toolbar toolbar = getActivity().findViewById(R.id.toolbar);
        ((AppCompatActivity) getActivity()).setSupportActionBar(toolbar);

        toolbar.setTitle(title);
        toolbar.setNavigationIcon(getResources().getDrawable(R.drawable.ic_action_back, null));
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        fab = getActivity().findViewById(R.id.fab);

        changeDescriptionTilConstraint();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initializeFab();
    }

    private void changeDescriptionTilConstraint() {
        // TODO terminar constraint
        // Boton no está en el constraint
        constraintSet = new ConstraintSet();
        constraintSet.clone(binding.constraintLayout);

        constraintSet.connect(binding.tilDependencyDescription.getId(), ConstraintSet.BOTTOM, fab.getId(), ConstraintSet.TOP, 0);
        constraintSet.constrainDefaultHeight(fab.getId(), 200);
        constraintSet.applyTo(binding.constraintLayout);
    }

    private void initializeFab() {
        fab.show();
        fab.setImageResource(R.drawable.ic_action_save);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Guardado correctamente(MENTIRA)", Toast.LENGTH_SHORT).show();
                getActivity().getSupportFragmentManager().popBackStack();
                //TODO avisar (presenter) activity para cambiar fragment y recargar los datos del adapter
            }
        });
    }

    /**
     * Locates the position of the Dependency's Inventory at the string-array from resources
     * so the spinner selection is set to the correct position
     */
    private void setSpinnerSelection() {
        List<String> inventoryArray = Arrays.asList(getResources().getStringArray(R.array.inventoryArray));
        int position = inventoryArray.indexOf(dependency.getInventory());

        if (position != -1) // Check if dependency hasn't got an inventory defined
            binding.spInventory.setSelection(position);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onSaveDependency(Dependency dependency) {
        if (onDependencySaveListener != null) {
            onDependencySaveListener.onSaveDependency(dependency);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*try {
            onDependencySaveListener = (OnDependencySaveListener) context;
        } catch (Exception e) {
            throw new RuntimeException(context.toString()
                    + " must implement OnDependencySaveListener");
        }*/
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onDependencySaveListener = null;
    }
}