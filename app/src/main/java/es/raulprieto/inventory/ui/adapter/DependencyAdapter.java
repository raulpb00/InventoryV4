package es.raulprieto.inventory.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.ivbaranov.mli.MaterialLetterIcon;

import java.util.ArrayList;

import es.raulprieto.inventory.R;
import es.raulprieto.inventory.data.db.model.Dependency;
import es.raulprieto.inventory.data.db.repository.DependencyRepository;

public class DependencyAdapter extends RecyclerView.Adapter<DependencyAdapter.ViewHolder> {

    private ArrayList<Dependency> list;
    private OnDependencyClickListener dependencyListener;

    // Defined a own Listener
    public interface OnDependencyClickListener{
        void onClick(Dependency dependency);
    }

    public DependencyAdapter() {
        this.list = (ArrayList<Dependency>) DependencyRepository.getInstance().getAll();
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
            holder.bind(position,dependencyListener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    /**
     * Returns the Dependency object based on the position given
     *
     * @param position index
     * @return Dependency Item
     */
    public Dependency getItem(int position) {
        return list.get(position);
    }

    /**
     * Method used to show as digits as the shortname has.
     * 1 digit = 1 letter shown. 2 digits = 2 letters shown.
     * @param holder of the materialLetterIcon
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
     * The OnDependencyClickListener is set by the following method
     * @param dependencyListener OnDependencyClickListener
     */
    public void setOnDependencyClickListener(OnDependencyClickListener dependencyListener){
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
         * @param dependencyListener listener
         */
        void bind(final int position, final OnDependencyClickListener dependencyListener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dependencyListener.onClick(getItem(position));
                }
            });
        }
    }
}
