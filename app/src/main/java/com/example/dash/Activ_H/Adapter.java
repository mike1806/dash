package com.example.dash.Activ_H;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dash.R;
import com.example.dash.model.Item;
import com.squareup.picasso.Picasso;

import java.util.List;

    public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
        LayoutInflater inflater;
        List<Item> items;
        private OnItemClickListener mListener;

        public Adapter(Context ctx, List<Item> items){
            this.inflater = LayoutInflater.from(ctx);
            this.items = items;
        }

        public interface OnItemClickListener {
            void onItemClick(int position);
            void onDeleteClick(int position);

        }

        public void setOnItemClickListener(OnItemClickListener listener) {
            mListener = listener;
        }


        @NonNull
        @Override
        //for custom_list_layout
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = inflater.inflate(R.layout.custom_list_layout,parent,false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // bind the data
            holder.itemName.setText(items.get(position).getName());
            holder.itemDesc.setText(items.get(position).getDescription());
            Picasso.get()
                    .load(items.get(position)
                    .getImage())
                    .into(holder.itemImage);

        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public  class ViewHolder extends  RecyclerView.ViewHolder{
            TextView itemName,itemDesc;
            ImageView itemImage;
            ImageView deleteImage;
            private OnItemClickListener listener;

            //Constructor
            public ViewHolder(@NonNull View itemView) {
                super(itemView);

                itemName = itemView.findViewById(R.id.itemName);
                itemDesc = itemView.findViewById(R.id.itemDescription);
                itemImage = itemView.findViewById(R.id.itemPhoto);
                deleteImage = itemView.findViewById(R.id.image_delete);
                //this.listener = listener;

                // handle onClick

               /* itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onItemClick(position);
                            }
                        }
                    }
                });
*/
                deleteImage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (listener != null) {
                            int position = getAdapterPosition();
                            if (position != RecyclerView.NO_POSITION) {
                                listener.onDeleteClick(position);
                            }
                        }
                    }
                });
            }
        }



        //Toast.makeText(v.getContext(), "Do Something With this Click", Toast.LENGTH_SHORT).show();
                /*   Intent intent = new Intent(this, Adapter.class);
                   startActivity(intent);
                    }

                    ItemTouchHelper.SimpleCallback ithc = new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.RIGHT) {
                        @Override
                        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                            return false;
                        }

                        @Override
                        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                            items.remove(viewHolder.getAdapterPosition());
                            rec
                        }*/
                    }
