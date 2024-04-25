package com.example.task51c_subtask1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class NewsItemFragment extends Fragment implements RecyclerViewInterface{
    // Declare variables
    private TextView newsItemTitle;
    private TextView newsItemContents;
    private ImageView newsItemImage;
    Button closeFragmentButton;
    RecyclerView recyclerViewRelated;
    RecyclerViewAdapter recyclerViewAdapterRelated;
    View fragmentView;
    int position;
    String newsArea;

    public NewsItemFragment(int position, String newsArea) {
        // Required empty public constructor
        this.position = position;
        this.newsArea = newsArea;
    }

    public NewsItemFragment newInstance(int position, String newsArea) {
        // Create new fragment
        NewsItemFragment fragment = new NewsItemFragment(position, newsArea);
        Bundle args = new Bundle();
        this.position = position;
        this.newsArea = newsArea;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Get UI elements nad update display values for clicked news article
        View view = inflater.inflate(R.layout.fragment_news_item_detail, container, false);
        newsItemTitle = view.findViewById(R.id.fragmentArticleTitle);
        newsItemContents = view.findViewById(R.id.fragmentArticleContents);
        newsItemImage = view.findViewById(R.id.fragmentArticleImage);

        if (newsArea == "Top"){
            newsItemTitle.setText(NewsItem.newsItemListTop.get(position).getTitle());
            newsItemContents.setText(NewsItem.newsItemListTop.get(position).getContents());
            newsItemImage.setImageResource(NewsItem.newsItemListTop.get(position).getImage());

        } else {
            newsItemTitle.setText(NewsItem.newsItemList.get(position).getTitle());
            newsItemContents.setText(NewsItem.newsItemList.get(position).getContents());
            newsItemImage.setImageResource(NewsItem.newsItemList.get(position).getImage());
        }

        // Set recycler view adapter for related stories
        setRelatedStoriesAdapter(view);

        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        this.fragmentView = view;

        // Set close button and on click listener to close the fragment
        closeFragmentButton = view.findViewById(R.id.closeArticle);
        closeFragmentButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                getParentFragmentManager().beginTransaction().remove(NewsItemFragment.this).commit();
            }
        });
    }

    public void setRelatedStoriesAdapter(View view) {
        // Set recycler view for related stories within full article view fragment
        recyclerViewRelated = view.findViewById(R.id.recycleViewRelated);
        recyclerViewAdapterRelated = new RecyclerViewAdapter("Related", view.getContext(), this);
        recyclerViewRelated.setAdapter(recyclerViewAdapterRelated);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewRelated.setLayoutManager(layoutManager);
    }

    @Override
    public void onItemClick(int position, String newsArea) {
        // Create a new fragment if another news article is selected from the current fragment
        Fragment fragment = new NewsItemFragment(position, newsArea);
        FragmentManager fragmentManager = getParentFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment).commit();
    }
}