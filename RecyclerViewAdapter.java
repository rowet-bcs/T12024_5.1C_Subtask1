package com.example.task51c_subtask1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    // Declare variables
    private final RecyclerViewInterface recyclerViewInterface;
    private Context context;
    private String newsArea;

    public RecyclerViewAdapter(String newsArea, Context context, RecyclerViewInterface recyclerViewInterface){
        this.newsArea = newsArea;
        this.context = context;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;

        // Display recycler view with the correct layout to match the area that is being displayed
        switch(this.newsArea){
            case "News":
            default:
                itemView = LayoutInflater.from(context).inflate(R.layout.news_item_layout, parent, false);
                break;
            case "Top":
                itemView = LayoutInflater.from(context).inflate(R.layout.news_item_layout_top_stories, parent, false);
                break;
            case "Related":
                itemView = LayoutInflater.from(context).inflate(R.layout.news_item_layout_related_stories, parent, false);
                break;
        }

        return new ViewHolder(itemView, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        // Set values for display depending on news area
        if (newsArea == "Top"){
            holder.newsItemHeadLine.setText(NewsItem.newsItemListTop.get(position).getTitle());
            holder.image.setImageResource(NewsItem.newsItemListTop.get(position).getImage());
        } else {
            holder.newsItemHeadLine.setText(NewsItem.newsItemList.get(position).getTitle());
            holder.image.setImageResource(NewsItem.newsItemList.get(position).getImage());
            holder.newsItemDescription.setText(NewsItem.newsItemList.get(position).getContents());
        }
    }

    @Override
    public int getItemCount() {
        // Get item count of standard or top news list
        if (newsArea == "Top"){
            return NewsItem.newsItemListTop.size();
        }
        return NewsItem.newsItemList.size();
    }

    public class ViewHolder  extends RecyclerView.ViewHolder{
        TextView newsItemHeadLine;
        TextView newsItemDescription;
        ImageView image;
        public ViewHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            // Set values to be displayed based on which news area is being populated
            switch(newsArea){
                case "News":
                default:
                    newsItemHeadLine = itemView.findViewById(R.id.newsItemHeadline);
                    newsItemDescription = itemView.findViewById(R.id.newsItemDescription);
                    image = itemView.findViewById(R.id.newsItemImage);
                    break;
                case "Top":
                    newsItemHeadLine = itemView.findViewById(R.id.newsItemHeadlineTop);
                    image = itemView.findViewById(R.id.newsItemImageTop);
                    break;
                case "Related":
                    newsItemHeadLine = itemView.findViewById(R.id.newsItemHeadlineRelated);
                    newsItemDescription = itemView.findViewById(R.id.newsItemDescriptionRelated);
                    image = itemView.findViewById(R.id.newsItemImageRelated);
                    break;
            }

            // Set on click listener for news articles
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if (position != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(position, newsArea);
                        }
                    }

                }
            });
        }
    }
}
