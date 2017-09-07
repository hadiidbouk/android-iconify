package com.joanzapata.iconify.sample;

import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import butterknife.Bind;
import butterknife.ButterKnife;
import com.joanzapata.iconify.Icon;

import java.util.ArrayList;

public class IconAdapter extends RecyclerView.Adapter<IconAdapter.ViewHolder> {

    private Icon[] icons;
    private final EditText mSearchEditTxt;
    private Icon[] originalIcons;
    private int oldLength =0;

    public IconAdapter(Icon[] icons, EditText searchEditTxt) {
        this.icons = icons;
        originalIcons = icons;
        mSearchEditTxt = searchEditTxt;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.item_icon, parent, false);

        mSearchEditTxt.addTextChangedListener(new TextWatcher() {
            @Override public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String txt = charSequence.toString();
                int length = txt.length();
                if(oldLength == 0)
                    oldLength = length;
                if(length < oldLength) {
                    icons = originalIcons;
                    notifyDataSetChanged();
                }
                ArrayList<Icon> searchedIcons = new ArrayList<Icon>();

                for(Icon icon : icons) {
                    if(icon.key().contains(txt)) {
                        searchedIcons.add(icon);
                    }
                }
                icons = (Icon[]) searchedIcons.toArray(new Icon[searchedIcons.size()]);
                notifyDataSetChanged();
            }

            @Override public void afterTextChanged(Editable editable) {

            }
        });


        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Icon icon = icons[position];
        viewHolder.icon.setText("{" + icon.key() + "}");
        viewHolder.name.setText(icon.key());
    }

    @Override
    public int getItemCount() {
        return icons.length;
    }



    public static class ViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.icon) TextView icon;
        @Bind(R.id.name) TextView name;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
