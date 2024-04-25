// This assignment was completed with assistance from
// https://www.youtube.com/watch?v=1ne6jfvriNs
// https://www.youtube.com/watch?v=7GPUpvcU1FE


package com.example.task51c_subtask1;


import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity_News extends AppCompatActivity implements RecyclerViewInterface{
    // Declare variables
    RecyclerView recyclerView;
    RecyclerView recyclerViewTop;
    RecyclerViewAdapter recyclerViewAdapterNews;
    RecyclerViewAdapter recyclerViewAdapterTopStories;
    Fragment fragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main_news);

        // Setup recycler views and populate dummy news articles
        setTopStoriesAdapter();;
        setNewsStoriesAdapter();
        setDummyNewsValues();;

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void setDummyNewsValues(){
        // Includes android drawable files as stand in news images and generated lorem ipsum as news article contents
        NewsItem newsItem1 = new NewsItem(0, true, android.R.drawable.ic_menu_gallery,"News Article 1", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aliquam rutrum neque sit amet varius aliquet. Pellentesque rhoncus quam nunc. Mauris hendrerit dolor sit amet suscipit porttitor. Etiam lacinia pharetra elit, vel pretium urna viverra ut. Suspendisse dictum libero ac tincidunt fringilla. Phasellus lacinia placerat libero sit amet efficitur. Praesent sodales nibh non quam cursus bibendum.\n" +
                "\n" +
                "Ut lacus nisi, dignissim et lectus in, blandit rutrum augue. Curabitur placerat tortor at lacus blandit, in facilisis sem tristique. Curabitur suscipit congue risus nec maximus. Vivamus efficitur tortor ligula, non sollicitudin ante lacinia eu.");
        NewsItem newsItem2 = new NewsItem(1, false,android.R.drawable.ic_menu_crop,"News Article 2", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi quis orci velit. Sed mattis sollicitudin odio nec porttitor. Etiam enim tellus, tempor at justo sed, scelerisque aliquam nulla. Curabitur placerat sem vel metus pulvinar sodales. Curabitur ut facilisis risus. In sit amet justo interdum, facilisis felis eget, feugiat sapien. Nam eget arcu maximus, venenatis lorem hendrerit, iaculis lorem. Vivamus nec elementum ligula. Aenean mollis lacinia mauris in semper. Sed eget mattis metus. Donec eget suscipit purus. Ut feugiat nulla pulvinar luctus rutrum. Duis vestibulum urna et condimentum mollis. Donec at ante sit amet leo convallis egestas a eget felis.\n" +
                "\n" +
                "Nulla convallis elit ac nisl aliquam, congue iaculis enim rhoncus. Aliquam vulputate ligula ipsum, id posuere neque dictum eget. Donec auctor, elit at tempor fermentum, sem ligula luctus urna, at interdum purus dolor et magna. Mauris posuere metus venenatis congue blandit. Vestibulum vulputate dui et arcu bibendum aliquam. Vestibulum porta imperdiet fermentum. Pellentesque efficitur, urna ut iaculis dictum, urna ipsum aliquam urna, ut fermentum ipsum elit id erat. Ut rutrum posuere erat at pretium.");
        NewsItem newsItem3 = new NewsItem(2, false, android.R.drawable.ic_menu_day,"News Article 3", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed maximus ex turpis, non interdum leo placerat euismod. Proin aliquet nisl id libero malesuada, imperdiet congue purus malesuada. Sed dapibus sagittis ante, in porta est aliquet id. Duis condimentum quam justo. Ut non lacinia velit. Nullam sagittis tortor quam, pretium tristique ligula laoreet sed. Nullam molestie sed leo sit amet rhoncus. Vestibulum imperdiet massa augue, nec mollis diam cursus a. Quisque imperdiet dolor a odio consequat, et consequat metus tempus. Ut dolor mi, suscipit et purus a, dapibus dignissim lectus.\n" +
                "\n" +
                "Proin quis scelerisque turpis, a varius risus. Duis ullamcorper vitae orci quis commodo. Aliquam erat volutpat. Nullam blandit id neque aliquam accumsan. Phasellus magna dolor, ultricies a mi vel, pellentesque consectetur elit. Donec tincidunt risus sed est fringilla rutrum. Nulla lectus dui, varius a est vel, fermentum tristique augue. Pellentesque ultricies semper leo, eget euismod tortor tristique id. Nam vitae felis ut nunc scelerisque lobortis quis in dolor. Interdum et malesuada fames ac ante ipsum primis in faucibus. In hac habitasse platea dictumst. Curabitur molestie blandit augue. Suspendisse sollicitudin vitae massa non bibendum.");
        NewsItem newsItem4 = new NewsItem(3, true, android.R.drawable.ic_menu_compass,"News Article 4", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean efficitur eu lacus ac porta. Mauris placerat est vel dolor sagittis, id bibendum nisi condimentum. Quisque in elit luctus, porttitor risus suscipit, scelerisque libero. Nulla imperdiet non sapien quis aliquet. Duis iaculis orci massa, ut congue velit euismod a. Quisque varius aliquet leo, a consequat quam bibendum vel. Sed tristique pulvinar purus, eu ultricies tortor fermentum eu. Duis fermentum tempor vehicula. Sed imperdiet turpis ac blandit dapibus. Interdum et malesuada fames ac ante ipsum primis in faucibus. In convallis dui dignissim hendrerit feugiat. Nullam sed molestie arcu. Suspendisse ut elementum enim.\n" +
                "\n" +
                "Phasellus commodo est commodo dapibus lacinia. In finibus turpis quam. Proin pharetra a ligula eu sollicitudin. Etiam sollicitudin odio vel lectus ultrices, a ultricies lacus consequat. Suspendisse ut lectus ipsum. Integer nunc nunc, tristique ac turpis id, lobortis ultrices ante. Donec sit amet congue dolor. Quisque eros erat, pharetra vitae elementum nec, egestas eu nisi. Nunc suscipit ante quam, ut maximus diam sodales quis. Nullam pulvinar facilisis blandit.");
        NewsItem newsItem5 = new NewsItem(4, true, android.R.drawable.ic_menu_info_details,"News Article 5", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Vivamus et tortor vitae libero porta dapibus eget a leo. Sed vestibulum tempor sem ac tristique. Aenean quis viverra odio, et ullamcorper velit. Suspendisse facilisis fringilla tellus, eget tempor magna vestibulum nec. Suspendisse tincidunt, justo nec varius fermentum, massa sem tincidunt leo, vestibulum pretium sem nisi nec dolor. Vivamus est nisi, imperdiet at nisi a, aliquet accumsan nunc. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Morbi pulvinar enim eu neque consequat cursus. Mauris mollis leo sed arcu suscipit ornare. Proin nec pharetra ex.\n" +
                "\n" +
                "Ut facilisis sed turpis quis consequat. Vivamus vel lacinia magna, vel convallis eros. Vivamus dignissim dui sit amet purus lacinia sodales. Sed facilisis nibh et ultricies maximus. Nullam dapibus, felis non dictum consequat, purus nunc tempus dui, ut pharetra lectus lacus a enim. Donec mollis neque non purus dapibus porta. Aenean at vulputate quam. Mauris a egestas lorem. Sed dapibus leo tellus, porttitor scelerisque dui tempor id.");
        NewsItem newsItem6 = new NewsItem(5, false, android.R.drawable.ic_menu_mapmode,"News Article 6", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean dictum porta nunc, et sagittis risus facilisis ut. Nulla facilisi. Aliquam erat volutpat. Integer finibus justo at tempor consectetur. Phasellus consequat dui metus, id sagittis elit rhoncus sed. Duis pharetra condimentum eros eget vehicula. Donec molestie libero sit amet diam rhoncus, ac vestibulum nisl imperdiet. Suspendisse eget urna lobortis, sodales nulla sed, dignissim turpis. In convallis nec nulla at tristique. In a nisi non enim varius suscipit at eu erat. Integer accumsan diam vitae purus auctor, nec vestibulum magna ornare. Donec sed molestie urna.\n" +
                "\n" +
                "Morbi et est elementum, semper massa sed, dignissim lectus. Suspendisse id vehicula ex, eget commodo felis. Praesent porttitor vitae tortor et iaculis. Duis molestie lobortis magna eget auctor. In hac habitasse platea dictumst. Suspendisse rhoncus rhoncus ipsum eu rhoncus. Fusce varius risus risus, vel consectetur purus ullamcorper sit amet. Nunc imperdiet urna ut libero condimentum, eget auctor dui ultrices. Suspendisse bibendum purus nec odio efficitur aliquet. Cras id varius diam. Donec ante lectus, vehicula vel nulla in, pulvinar sollicitudin augue. Phasellus quis cursus nisl. Nam quis massa faucibus mauris efficitur malesuada vel id erat. Cras dolor lacus, bibendum in dapibus ut, posuere vitae risus.");
        NewsItem newsItem7 = new NewsItem(6, true, android.R.drawable.ic_menu_recent_history,"News Article 7", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla eget eros lacus. Aenean ultrices sed lacus sit amet placerat. Mauris nec urna arcu. Quisque elementum gravida dui eleifend pharetra. Suspendisse at risus nec quam varius tempus vitae iaculis orci. Vestibulum et nulla odio. Proin at pharetra elit. Duis venenatis mollis fringilla. Etiam ac urna mi. Morbi placerat, purus in rutrum dapibus, nulla ante congue odio, non interdum velit orci vitae turpis. Sed id tristique tellus. Nulla mattis nibh nec nisl scelerisque maximus. Fusce egestas imperdiet eros et suscipit. Curabitur sed nulla placerat, ullamcorper lectus vitae, placerat enim. Sed rhoncus ex a libero consectetur, eleifend placerat dolor blandit. Nullam id euismod tortor.\n" +
                "\n" +
                "Donec eleifend magna ut ultricies venenatis. Praesent vestibulum eget lorem dignissim mattis. Aliquam blandit lectus a facilisis posuere. Mauris vel suscipit ante. Pellentesque felis augue, tristique eu metus et, vestibulum imperdiet mauris. Suspendisse at accumsan dui. Aliquam porta a massa et mattis. Phasellus at justo eget lorem consectetur viverra eget ut dui. Sed tempus, tortor eu imperdiet porta, metus tortor lobortis lectus, sed pretium ex eros sit amet velit.");
        NewsItem newsItem8 = new NewsItem(7, false, android.R.drawable.ic_menu_report_image,"News Article 8", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Aenean vulputate, felis nec molestie auctor, dolor erat accumsan ligula, at venenatis nibh lectus id nulla. Nulla mattis vitae tortor id laoreet. Aenean imperdiet, risus non rutrum bibendum, elit quam convallis libero, eget fermentum urna ex ac ante. Fusce vulputate nisl a orci gravida imperdiet. Nulla eget metus vulputate lacus lobortis faucibus ac eu dui. Quisque nec elit erat. Cras accumsan massa sed urna placerat, facilisis ultrices velit tincidunt. In dignissim dolor vel dui tincidunt varius. Aliquam elementum lacus purus, at suscipit sapien pulvinar at. Duis quis est nec erat porttitor malesuada. Nam vel hendrerit justo.\n" +
                "\n" +
                "Maecenas odio diam, vehicula ut viverra fringilla, interdum sit amet diam. Fusce dolor velit, vestibulum vel ligula eu, semper gravida tortor. Maecenas feugiat turpis nisi, et faucibus nisi eleifend eu. Suspendisse potenti. Ut sit amet arcu vitae arcu dapibus rhoncus. Nullam viverra tortor vitae nibh posuere, id pellentesque lectus commodo. Fusce ac tincidunt sapien, tempor ornare nulla. Vestibulum finibus fermentum fermentum. Suspendisse nec augue eget urna tincidunt placerat. ");
        NewsItem newsItem9 = new NewsItem(8, true, android.R.drawable.ic_menu_slideshow,"News Article 9", "\n" +
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec non tempus ex. Proin maximus purus quis aliquet laoreet. Sed at sapien sollicitudin, ullamcorper nisl at, fermentum nulla. Proin pellentesque mi non dolor vestibulum, eu semper enim varius. Aliquam pellentesque interdum pellentesque. Sed aliquet dui quis auctor maximus. Nulla commodo, lacus a cursus aliquam, massa felis tempor diam, hendrerit efficitur elit nunc id arcu.\n" +
                "\n" +
                "Duis nec condimentum libero. Mauris tincidunt, turpis quis tincidunt faucibus, erat turpis vestibulum quam, ac consequat ante nisl eu turpis. Donec volutpat nisi vitae vulputate gravida. Ut id eros a arcu volutpat placerat et vitae lacus. Maecenas et laoreet dui. Duis faucibus posuere tortor nec pulvinar. Sed porttitor tincidunt posuere. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Curabitur et quam elit. Maecenas egestas velit non ante facilisis euismod non et ex. Nulla vel erat nec mauris faucibus placerat vitae nec purus.");
        NewsItem newsItem10 = new NewsItem(10, false,android.R.drawable.ic_menu_today,"News Article 10", "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Quisque dictum ligula at ipsum placerat sollicitudin. Nullam nec porta purus, et iaculis enim. Ut fringilla pharetra tortor vel consequat. Fusce quis arcu eu augue consequat aliquam nec ut velit. Integer sit amet ligula finibus, placerat tellus at, consequat mauris. Nullam ut bibendum quam. Suspendisse potenti. Praesent a tincidunt nisi. Morbi vitae aliquam nunc, rhoncus pretium purus. Sed non ex dictum mi blandit iaculis eget vitae odio.\n" +
                "\n" +
                "Quisque in elementum nibh, vel vulputate tellus. Nam at molestie sapien, varius suscipit tellus. Donec vel elit dolor. Quisque urna justo, elementum ut mauris ornare, aliquam iaculis risus. Aenean mattis velit ac quam mattis imperdiet. Phasellus tempor sem vitae ipsum consectetur fringilla id eu metus. In quis metus ipsum.");

        NewsItem.newsItemList.add(newsItem1);
        NewsItem.newsItemList.add(newsItem2);
        NewsItem.newsItemList.add(newsItem3);
        NewsItem.newsItemList.add(newsItem4);
        NewsItem.newsItemList.add(newsItem5);
        NewsItem.newsItemList.add(newsItem6);
        NewsItem.newsItemList.add(newsItem7);
        NewsItem.newsItemList.add(newsItem8);
        NewsItem.newsItemList.add(newsItem9);
        NewsItem.newsItemList.add(newsItem10);

        // Populate top news list with those stories flagged as top stories
        NewsItem.setTopNewsList();

    }

    public void setTopStoriesAdapter(){
        // Set recycler view for top news stories
        recyclerViewTop = findViewById(R.id.recyclerViewTopStories);
        recyclerViewAdapterTopStories = new RecyclerViewAdapter("Top", this, this);
        LinearLayoutManager layoutManagerTop = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewTop.setLayoutManager(layoutManagerTop);
        recyclerViewTop.setAdapter(recyclerViewAdapterTopStories);
    }

    public void setNewsStoriesAdapter(){
        // Set recycler view for news stories
        recyclerView = findViewById(R.id.recyclerViewNews);
        recyclerViewAdapterNews = new RecyclerViewAdapter("News", this, this);
        recyclerView.setAdapter(recyclerViewAdapterNews);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    public void onItemClick(int position, String newsArea) {
        // When a news article is clicked - get the position from the relevant list
        if (newsArea == "Top") {
            String title = NewsItem.newsItemListTop.get(position).getTitle();
        } else {
            String title = NewsItem.newsItemList.get(position).getTitle();
        }

        // Launch a fragment to view the full news article
        fragment = new NewsItemFragment(position, newsArea);
        fragmentManager = getSupportFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container_view, fragment).commit();
    }
}