package ratmach.workshop.beacontransmitter;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class TouristActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tourist);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        final Intent intent = getIntent();
        int id = intent.getIntExtra("id", 1);

        final TouristInfo touristInfo = TouristInfo.get(id);
        assert touristInfo!=null;

        getSupportActionBar().setTitle(touristInfo.getTitle());

        TextView description = (TextView) findViewById(R.id.description);

        if (description!=null){
            description.setText(touristInfo.getDescription());
        }

        ImageView image = (ImageView) findViewById(R.id.image);
        image.setBackgroundResource(getImageId(id));


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent1 = new Intent(Intent.ACTION_VIEW, Uri.parse(touristInfo.getActionLink()));
                startActivity(intent1);
            }
        });

    }

    private int getImageId(int id ){
        switch (id){
            case 1:
                return R.drawable.opera;
            case 2:return R.drawable.museum;
        }
        return 0;
    }
}
