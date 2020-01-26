package frox.world.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import frox.world.com.R;
import frox.world.com.controller.fragment.CardFragment;
import frox.world.com.controller.fragment.DetailCardFragment;

public class CardActivity extends AppCompatActivity implements CardFragment.OnButtonClickedListener {

    private CardFragment cardFragment;
    private DetailCardFragment detailCardFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card);
        //ajout du premier fragment
        this.configureAndShowCardFragment();
        //ajout du detail fragment
        this.configureAndShowCardDetailFragment();
    }

    //callback
    @Override
    public void OnButtonClicked(View view) {

        Log.e(getClass().getSimpleName(), "Button clicked");
        if (detailCardFragment == null) {
            startActivity(new Intent(this, CardDetailActivity.class));
        }
    }

    //fragments

    private void configureAndShowCardFragment() {
        //test si le fragment existe deja dans notre manager
        cardFragment = (CardFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_main);

        //cas ou il n'y a pas de fragment
        if (cardFragment == null) {
            cardFragment = new CardFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_main, cardFragment)
                    .commit();
        }
    }

    private void configureAndShowCardDetailFragment() {
        //test si le fragment existe deja dans notre manager
        detailCardFragment = (DetailCardFragment) getSupportFragmentManager().findFragmentById(R.id.frame_layout_detail);

        //cas ou il n'y a pas de fragment et que nous sommes soit dans une tablette soit pas
        //car frame_layout_detail n'existe que sur la version tablelle layout600
        if (detailCardFragment == null && findViewById(R.id.frame_layout_detail) != null) {
            detailCardFragment = new DetailCardFragment();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.frame_layout_detail, detailCardFragment)
                    .commit();
        }
    }
}
