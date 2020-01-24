package es.raulprieto.inventory.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;
import java.util.List;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.data.db.repository.DependencyRepository;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    private ArrayList<Dependency> list;
    private OnManageDependencyClickListener dependencyListener;

    // Defined a own Listener
    public interface OnManageDependencyClickListener {
        // If pressed, a dependency would be edited
        void onEditDependency(Dependency dependency);

        // If longpressed, a dependency would be deleted
        void onDeleteDependency(Dependency dependency, int position);
    }

    public DependencyAdapter() {
        this.list = new ArrayList<>();
    }

    /**
     * Method that inflates from the XML as many ViewHolder objects as they visualize
     *
     * @param parent   Recycler
     * @param viewType viewType
     * @return ViewHolder
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());

        View view = layoutInflater.inflate(R.layout.item_dependency, parent, false);

        return new ViewHolder(view);
    }

    /**
     * Links data to each component of the ViewHolder when scrolling at the RecyclerView
     *
     * @param holder   views's holder
     * @param position at the parent (recycler)
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvName.setText(getItem(position).getName());
        setLetters(holder, position);
        if (dependencyListener != null)
            holder.bind(position, dependencyListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void clear() {
        list.clear();
    }

    public void loadAll(List<Dependency> list) {
        this.list.addAll(list);
    }

    public void delete(Dependency deleted) {
        list.remove(deleted);
    }

    public void add(Dependency undoDeleted) {
        list.add(undoDeleted);
    }

    public void undo(Dependency undoDeleted) {
        list.add(undoDeleted);
    }

    /**
     * Returns the Dependency object based on the position given
     *
     * @param position index
     * @return Dependency Item
     */
    private Dependency getItem(int position) {
        return list.get(position);
    }

    /**
     * Method used to show as digits as the shortname has.
     * 1 digit = 1 letter shown. 2 digits = 2 letters shown.
     *
     * @param holder   of the materialLetterIcon
     * @param position of the holder
     */
    private void setLetters(ViewHolder holder, int position) {
        String letters;

        letters = getItem(position).getShortName();
        if (letters.substring(1).matches("^[0-9].*"))
            holder.mliDependency.setLettersNumber(2);
        else
            holder.mliDependency.setLettersNumber(1);

        holder.mliDependency.setLetter(letters);

    }

    /**
     * The OnManageDependencyClickListener is set by the following method
     *
     * @param dependencyListener OnManageDependencyClickListener
     */
    public void setOnManageDependencyClickListener(OnManageDependencyClickListener dependencyListener) {
        this.dependencyListener = dependencyListener;
    }

    /**
     * Stores the views or elements/items View which compose the RecyclerView
     */
    class ViewHolder extends RecyclerView.ViewHolder {
        MaterialLetterIcon mliDependency;
        TextView tvName;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            mliDependency = itemView.findViewById(R.id.materialLetterIcon);
            tvName = itemView.findViewById(R.id.tvName);
        }

        /**
         * Option 2: This method establish the listener to an event of one of the holder components
         *
         * @param dependencyListener listener
         */
        void bind(final int position, final OnManageDependencyClickListener dependencyListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dependencyListener.onEditDependency(getItem(position));
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    dependencyListener.onDeleteDependency(getItem(position), position);
                    return true; // Return true in order to consume the onClick event
                }
            });
        }
    }
}
