package com.example.task51c_subtask1;

import java.util.ArrayList;

// Custom class for storing news article data
public class NewsItem {
    // Declare variables
    public static ArrayList<NewsItem> newsItemList = new ArrayList<>();
    public static ArrayList<NewsItem> newsItemListTop = new ArrayList<>();
    private String title;
    private String contents;
    private int image;
    private int id;
    private boolean topStoryFlag;

    public NewsItem(int id, boolean topStoryFlag, int image, String title, String contents)
    {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.image = image;
        this.topStoryFlag = topStoryFlag;
    }

    public static void setTopNewsList(){
        // Populate top news list from articles flagged as top stories

        // Empty task list before repopulating
        newsItemListTop.clear();

        // Populate list of top news stories
        for (NewsItem newsItem : newsItemList){
            if(newsItem.getTopStoryFlag()){
                newsItemListTop.add(newsItem);
            }
        }
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getContents(){ return contents; }
    public int getImage(){ return image; }
    public boolean getTopStoryFlag(){ return topStoryFlag; }

    public void setId(int id){ this.id = id; }
    public void setTitle(String title){ this.title = title; }
    public void setContents(String contents){ this.contents = contents; }
    public void setImage(int image){ this.image = image; }
    public void setTopStoryFlag(boolean topStoryFlag){ this.topStoryFlag = topStoryFlag; }
}
