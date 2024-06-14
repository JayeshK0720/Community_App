package com.jktech.community_app;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class FaqActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private FaqAdapter faqAdapter;
    private List<FAQ> faqList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

//        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        faqList = new ArrayList<>();
        faqList.add(new FAQ("How do I reset my password?", "If you have forgotten your password, click on the 'Forgot Password' link on the login screen and follow the instructions to reset your password."));
        faqList.add(new FAQ("How do I create an account?", "To create an account, click on the 'Sign Up' button on the login screen and fill in the required details."));
        faqList.add(new FAQ("How can I join a community?", "To join a community, go to the 'Communities' section, search for the community you are interested in, and click on the 'Join' button."));
        faqList.add(new FAQ("How do I report inappropriate content?", "To report inappropriate content, click on the 'Report' button next to the content and provide a reason for reporting."));
        faqList.add(new FAQ("How do I change my profile picture?", "To change your profile picture, go to your profile, click on the current profile picture, and select a new picture from your gallery."));
        faqList.add(new FAQ("How can I contact support?", "To contact support, go to the 'Help & Support' section in the app and submit a support request or email us at support@example.com."));

        faqAdapter = new FaqAdapter(faqList);
        recyclerView.setAdapter(faqAdapter);
    }
}
