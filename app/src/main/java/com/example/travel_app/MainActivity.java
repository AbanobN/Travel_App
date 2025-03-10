package com.example.travel_app;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.travel_app.databinding.ActivityMainBinding;
import com.example.travel_app.model.CategoryModel;
import com.example.travel_app.model.ItemModel;
import com.example.travel_app.view.adapter.CategoryAdapter;
import com.example.travel_app.view.adapter.PopularAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ActivityMainBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        initCategory();
        initPopular();
    }

    private void initPopular() {
        DatabaseReference myRef = database.getReference("Popular");
        binding.progressBarPopular.setVisibility(View.VISIBLE);

        ArrayList<ItemModel> popularList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        popularList.add(issue.getValue(ItemModel.class));
                    }

                    if(!popularList.isEmpty()){
                        binding.recViewPopular.setLayoutManager(
                                new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL,false)

                        );

                        RecyclerView.Adapter popularAdapter = new PopularAdapter(popularList);
                        binding.recViewPopular.setAdapter(popularAdapter);
                    }

                    binding.progressBarPopular.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

    private void initCategory() {
        DatabaseReference myRef = database.getReference("Category");
        binding.progressBarCategory.setVisibility(View.VISIBLE);

        ArrayList<CategoryModel> categoriesList = new ArrayList<>();
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){
                    for(DataSnapshot issue:snapshot.getChildren()){
                        categoriesList.add(issue.getValue(CategoryModel.class));
                    }

                    if(!categoriesList.isEmpty()){
                        binding.reViewCategory.setLayoutManager(
                                new GridLayoutManager(MainActivity.this,4)
                        );

                        RecyclerView.Adapter categoryAdapter = new CategoryAdapter(categoriesList);
                        binding.reViewCategory.setAdapter(categoryAdapter);
                    }

                    binding.progressBarCategory.setVisibility(View.GONE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}