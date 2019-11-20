package es.raulprieto.inventory.ui.dependency;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.databinding.FragmentDependencyAddBinding;

/**
 *
 * Style:
 * https://material.io/develop/android/components/text-input-layout/
 */
public class DependencyAddFragment extends Fragment {
    public static final String TAG = "dependencyaddfragment";

    private OnDependencySaveListener onDependencySaveListener;

    private FragmentDependencyAddBinding binding;

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

    public DependencyAddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param bundle Arguments if they exists
     * @return A new instance of fragment DependencyAddFragment.
     */
    public static Fragment newInstance(Bundle bundle) {
        DependencyAddFragment fragment = new DependencyAddFragment();
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
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_dependency_add, container, false);

        View view = binding.getRoot();

        Bundle bundle = getArguments();
        if (bundle != null)
            binding.setDependency((Dependency) bundle.getSerializable("dependency"));

        return view;
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
