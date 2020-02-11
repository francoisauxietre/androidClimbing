package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import frox.world.com.R;
import frox.world.com.controller.fragment.DetailCardFragment;

public class CardDetailActivity extends AppCompatActivity {
    private DetailCardFragment detailCardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_detail);
        this.configureAndShowCardFragment();
    }

    private  void configureAndShowCardFragment(){
        //test si le fragment existe deja dans notre manager
        detailCardFragment = (DetailCardFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);

        //cas ou il n'y a pas de fragment
        if (detailCardFragment == null){
            detailCardFragment =  new DetailCardFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailCardFragment)
                    .commit();
        }
    }
}
