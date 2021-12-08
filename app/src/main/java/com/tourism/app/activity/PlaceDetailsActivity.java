package com.tourism.app.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.maps.MapView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.tourism.app.R;
import com.tourism.app.models.PlaceListModel;

public class PlaceDetailsActivity extends AppCompatActivity {

    ImageView imageTop, image1, image2, image3;
    TextView placeName, placeRating, placeDetails, placeAddress;
    LinearLayout location;

    //model
    PlaceListModel details_model = null;

    private FirebaseFirestore firestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);


        imageTop = findViewById(R.id.top_image);
        image1 = findViewById(R.id.image_1);
        image2 = findViewById(R.id.image_2);
        image3 = findViewById(R.id.image_3);

        placeName = findViewById(R.id.detailed_name);
        placeAddress = findViewById(R.id.detailed_address);
        placeRating = findViewById(R.id.detailed_rating);
        placeDetails = findViewById(R.id.detailed_details);
        location = findViewById(R.id.locationOnMap);




        firestore = FirebaseFirestore.getInstance();
        final Object obj = getIntent().getSerializableExtra("detailed");

        if(obj instanceof PlaceListModel){
            details_model = (PlaceListModel) obj;
        }


        if(details_model != null){
            Glide.with(getApplicationContext()).load(details_model.getImg_url()).into(imageTop);
            Glide.with(getApplicationContext()).load(details_model.getImg_url1()).into(image1);
            Glide.with(getApplicationContext()).load(details_model.getImg_url2()).into(image2);
            Glide.with(getApplicationContext()).load(details_model.getImg_url3()).into(image3);

            placeName.setText(details_model.getName());
            placeAddress.setText(details_model.getAddress());
            placeRating.setText(details_model.getRating() + "/5");
            placeDetails.setText(details_model.getDescription());

        }


        location.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                openMap();
            }

            private void openMap() {
                String cordination = details_model.getCord1();
                Uri uri = Uri.parse("geo: " + cordination + "?z=18");
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                intent.setPackage("com.google.android.apps.maps");
                startActivity(intent);
            }
        });


    }
}