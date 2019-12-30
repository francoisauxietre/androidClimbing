package frox.world.com.controller.cartography;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.Criteria;
import android.location.LocationProvider;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.ItemizedIconOverlay;
import org.osmdroid.views.overlay.ItemizedOverlayWithFocus;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.views.overlay.OverlayItem;
import org.osmdroid.views.overlay.Polyline;
import org.osmdroid.views.overlay.ScaleBarOverlay;
import org.osmdroid.views.overlay.compass.CompassOverlay;
import org.osmdroid.views.overlay.compass.InternalCompassOrientationProvider;
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay;
import org.osmdroid.views.overlay.infowindow.BasicInfoWindow;
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider;
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay;

import frox.world.com.BuildConfig;
import frox.world.com.R;

public class CartographyActivity extends AppCompatActivity {

    //gps
    private FusedLocationProviderClient fusedLocationClient;
    private LocationManager locationManager = null;
    private Criteria criteria;
    private Location location;
    private int etat;
    private String bestProvider;
    private TextView latitude;
    private TextView longitude;
    private TextView address;
    private ImageButton locate;
    private ArrayList<GeoPoint> path;
    private double zoom = 5.0;

    private MapView myOpenMapView;

    ScaleBarOverlay myScaleBarOverlay;
    CompassOverlay mCompassOverlay;
    MyLocationNewOverlay mLocationOverlay;
    RotationGestureOverlay mRotationGestureOverlay;
    Geocoder geocoder = new Geocoder(this, Locale.getDefault());



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartography);

        latitude = findViewById(R.id.activity_cartography_latitude);
        longitude = findViewById(R.id.activity_cartography_longitude);
        address = findViewById(R.id.activity_cartography_adress);
        //test
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        Log.d("GPS", "fused!");
                        if (location != null) {
                            latitude.setText("latitude :"+location.getLatitude());
                            longitude.setText("longitude :"+location.getLongitude());
                            address.setText("altitude :"+location.getAltitude());
                        }
                    }
                });

        //TODO cette ligne est hyper importante sinon on a pas la possibilite de voir la map
        Configuration.getInstance().setUserAgentValue(BuildConfig.APPLICATION_ID);

        locate = findViewById(R.id.activity_cartography_locate);
        locate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initialiserLocalisation();
            }
        });

        etat = 0;


        myOpenMapView = findViewById(R.id.mapview);
        myOpenMapView.setBuiltInZoomControls(true);
        myOpenMapView.setClickable(true);
        //controll du zoom 1 carte entiere on pourra ici afficher des groupement de puces
        myOpenMapView.getController().setZoom(zoom);


        myScaleBarOverlay = new ScaleBarOverlay(myOpenMapView);
        myOpenMapView.getOverlays().add(myScaleBarOverlay);

        mCompassOverlay = new CompassOverlay(getApplicationContext(), new InternalCompassOrientationProvider(getApplicationContext()), myOpenMapView);
        mCompassOverlay.enableCompass();
        myOpenMapView.getOverlays().add(mCompassOverlay);

        mRotationGestureOverlay = new RotationGestureOverlay(getApplicationContext(), myOpenMapView);
        mRotationGestureOverlay.setEnabled(true);
        myOpenMapView.setMultiTouchControls(true);
        myOpenMapView.getOverlays().add(this.mRotationGestureOverlay);

        path = new ArrayList<GeoPoint>();

        Log.d("GPS", "onCreate");

        initialiserLocalisation();

        mLocationOverlay = new MyLocationNewOverlay(new GpsMyLocationProvider(getApplicationContext()), myOpenMapView);
        mLocationOverlay.enableMyLocation();
        myOpenMapView.setMultiTouchControls(true);
        myOpenMapView.getOverlays().add(mLocationOverlay);
    }


    LocationListener locationListener = new LocationListener() {
        //
        @Override
        public void onLocationChanged(Location localisation) {
            Toast.makeText(getApplicationContext(), bestProvider + " localisation", Toast.LENGTH_SHORT).show();
            if (location != null) {
                Log.d("GPS", "localisation : ");
                String coordonnees = String.format("Latitude : %f - Longitude : %f\n", localisation.getLatitude(), localisation.getLongitude());
                Log.d("GPS", coordonnees);
                String autres = String.format("Vitesse : %f - Altitude : %f - Cap : %f\n", localisation.getSpeed(), localisation.getAltitude(), localisation.getBearing());
                Log.d("GPS", autres);
                //String timestamp = String.format("Timestamp : %d\n", localisation.getTime());
                //Log.d("GPS", "timestamp : " + timestamp);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
                Date date = new Date(localisation.getTime());
                Log.d("GPS", sdf.format(date));

                String strLatitude = String.format("Latitude : %f", localisation.getLatitude());
                String strLongitude = String.format("Longitude : %f", localisation.getLongitude());

                Log.w("______________entre_______", "test");


                latitude.setText("test");
                longitude.setText(strLongitude);

                myOpenMapView.getController().setCenter(new GeoPoint(localisation.getLatitude(), localisation.getLongitude()));
                //pour tourner la carte dans le sens du tel ?
                myOpenMapView.setMapOrientation(localisation.getBearing());

                path.add(new GeoPoint(localisation.getLatitude(), localisation.getLongitude()));

                // Un tracé à base de lignes rouges
                Polyline line = new Polyline(myOpenMapView);
                line.setTitle("Un trajet");
                line.setSubDescription(Polyline.class.getCanonicalName());
                line.setWidth(10);
                line.setColor(Color.RED);
                line.setPoints(path);
                line.setGeodesic(true);
                line.setInfoWindow(new BasicInfoWindow(R.layout.bonuspack_bubble, myOpenMapView));
                myOpenMapView.getOverlayManager().add(line);

                Marker marker = new Marker(myOpenMapView);
                marker.setPosition(new GeoPoint(localisation.getLatitude(), localisation.getLongitude()));
                marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
                marker.setIcon(getResources().getDrawable(R.drawable.marker));
                marker.setTitle("TEC");
                myOpenMapView.getOverlays().add(marker);

                ArrayList<OverlayItem> items = new ArrayList<OverlayItem>();
                items.add(new OverlayItem("Title", "Description", new GeoPoint(localisation.getLatitude(), localisation.getLongitude())));

                ItemizedOverlayWithFocus<OverlayItem> mOverlay = new ItemizedOverlayWithFocus<>(getApplicationContext(), items,
                        new ItemizedIconOverlay.OnItemGestureListener<OverlayItem>() {
                            @Override
                            public boolean onItemSingleTapUp(final int index, final OverlayItem item) {
                                Toast.makeText(getApplicationContext(), "Overlay Titled: " +
                                        item.getTitle() + " Single Tapped" + "\n" + "Description: " +
                                        item.getSnippet(), Toast.LENGTH_LONG).show();
                                return true;
                            }

                            @Override
                            public boolean onItemLongPress(final int index, final OverlayItem item) {
                                return false;
                            }
                        });
                mOverlay.setFocusItemsOnTap(true);
                myOpenMapView.getOverlays().add(mOverlay);

                myOpenMapView.invalidate();

                List<Address> adresses = null;
                try {
                    adresses = geocoder.getFromLocation(localisation.getLatitude(), localisation.getLongitude(), 1);
                } catch (IOException ioException) {
                    Log.e("GPS", "erreur", ioException);
                } catch (IllegalArgumentException illegalArgumentException) {
                    Log.e("GPS", "erreur " + coordonnees, illegalArgumentException);
                }

                if (adresses == null || adresses.size() == 0) {
                    Log.e("GPS", "erreur aucune adresse !");
                } else {
                    Address adresse = adresses.get(0);
                    ArrayList<String> addressFragments = new ArrayList<String>();

                    String strAdresse = adresse.getAddressLine(0) + ", " + adresse.getLocality();
                    Log.d("GPS", "adresse : " + strAdresse);

                    for (int i = 0; i <= adresse.getMaxAddressLineIndex(); i++) {
                        addressFragments.add(adresse.getAddressLine(i));
                    }
                    Log.d("GPS", TextUtils.join(System.getProperty("line.separator"), addressFragments));
                    address.setText(TextUtils.join(System.getProperty("line.separator"), addressFragments));
                }
            }
        }

        @Override
        public void onProviderDisabled(String fournisseur) {
            Toast.makeText(getApplicationContext(), fournisseur + " désactivé !", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onProviderEnabled(String fournisseur) {
            Toast.makeText(getApplicationContext(), fournisseur + " activé !", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onStatusChanged(String fournisseur, int status, Bundle extras) {
            if (etat != status) {
                switch (status) {
                    case LocationProvider.AVAILABLE:
                        Toast.makeText(getApplicationContext(), fournisseur + " état disponible", Toast.LENGTH_SHORT).show();
                        break;
                    case LocationProvider.OUT_OF_SERVICE:
                        Toast.makeText(getApplicationContext(), fournisseur + " état indisponible", Toast.LENGTH_SHORT).show();
                        break;
                    case LocationProvider.TEMPORARILY_UNAVAILABLE:
                        Toast.makeText(getApplicationContext(), fournisseur + " état temporairement indisponible", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), fournisseur + " état : " + status, Toast.LENGTH_SHORT).show();
                }
            }
            etat = status;
        }
    };


    @Override
    protected void onDestroy() {
        super.onDestroy();
        arreterLocalisation();
    }

    private void initialiserLocalisation() {
        Log.w("GPS--------------------", "initialiser location ");


        if (locationManager == null) {
            locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
            Criteria criteres = new Criteria();

            // la précision  : (ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision)
            criteres.setAccuracy(Criteria.ACCURACY_FINE);

            // l'altitude
            criteres.setAltitudeRequired(false);

            // la direction
            criteres.setBearingRequired(false);

            // la vitesse
            criteres.setSpeedRequired(false);

            // la consommation d'énergie demandée
            criteres.setCostAllowed(false);
            //criteres.setPowerRequirement(Criteria.POWER_HIGH);
            criteres.setPowerRequirement(Criteria.POWER_MEDIUM);

            bestProvider = locationManager.getBestProvider(criteres, false);
            Log.d("GPS", "providers --------------: " + bestProvider);
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        //locationManager.requestLocationUpdates(bestProvider, 1000, 2, locationListener);
        if (bestProvider != null) {
            Log.w("GPS--------------------", bestProvider.toString());
            // dernière position connue
            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                Log.d("GPS", "no permissions !");
                return;
            }

            Location localisation = locationManager.getLastKnownLocation(bestProvider);
            if (localisation == null) {
                // on notifie la localisation
                Log.d("GPS", "derniere localisation!");
                locationListener.onLocationChanged(localisation);
            }

            // on configure la mise à jour automatique : au moins 10 mètres et 15 secondes
            locationManager.requestLocationUpdates(bestProvider, 1000, 0, locationListener);
        } else {
            Log.w("GPS--------------------", "pas de provider ");
        }
    }

    private void arreterLocalisation() {
//        if (locationManagerTel != null) {
//            locationManagerTel.removeUpdates(ecouteurGPS);
//            ecouteurGPS = null;
//        }
    }

    //essai de trouver la position propre du tel
    // triangulation moins couteuse pour en ville
    //gps plus couteuse mais mieux pour falaise
    private void gps() {
//
        Log.i("GPS", "enter in activity cartography gps");
        initialiserLocalisation();
//
//        //on va essayerde trouver la position courant du telephone
//        locationManagerTel = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//        //liste de tous les fournisseur disponible meme si on n'a pas le droit aux acces
//        //providers = fournisseurs iniitialise la liste
//        ArrayList<LocationProvider> providers = new ArrayList<>();
//        //citères pour les acces gps
//        Criteria criteria = new Criteria();
//
//        // Pour indiquer la précision voulue
//        // On peut mettre ACCURACY_FINE pour une haute précision ou ACCURACY_COARSE pour une moins bonne précision
//        criteria.setAccuracy(Criteria.ACCURACY_FINE);
//
//        // Est-ce que le fournisseur doit être capable de donner une altitude ?
//        criteria.setAltitudeRequired(true);
//
//        // Est-ce que le fournisseur doit être capable de donner une direction ?
//        criteria.setBearingRequired(true);
//
//        // Est-ce que le fournisseur peut être payant ?
//        criteria.setCostAllowed(false);
//
//        // Pour indiquer la consommation d'énergie demandée
//        // Criteria.POWER_HIGH pour une haute consommation, Criteria.POWER_MEDIUM pour une consommation moyenne et Criteria.POWER_LOW pour une basse consommation
//        criteria.setPowerRequirement(Criteria.POWER_HIGH);
//
//
//        // Est-ce que le fournisseur doit être capable de donner une vitesse ?
//        criteria.setSpeedRequired(true);
//        //liste des fournisseurs disponibles sans criteres
//        List<String> providerNames = locationManagerTel.getProviders(true);
//        //avec tous
//        List<String> providerAllCriteriaNames = locationManagerTel.getProviders(criteria, true);
//        //meilleur par rapport aux criteres
//        String bestProvider = locationManagerTel.getBestProvider(criteria, true);
//
//        for (String name : providerNames) {
//            Log.w("GPSwarn", name.toLowerCase().toString());
//            providers.add(locationManagerTel.getProvider(name));
//        }
//
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling  en cas de refus du gps
//            //    Activity#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for Activity#requestPermissions for more details.
//            Toast info = new Toast(this);
//            info.setText(R.string.activity_cartography_enable_gps);
//            return;
//        }
//        locationManagerTel.requestLocationUpdates(locationManagerTel.GPS_PROVIDER, 60000, 150, new LocationListener() {
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//            }
//
//            @Override
//            public void onProviderEnabled(String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(String provider) {
//
//            }
//
//            @Override
//            public void onLocationChanged(Location location) {
//                latitude.setText("" + location.getLatitude());
//                longitude.setText("" + location.getLongitude());
//                Log.d("GPS", "Latitude " + location.getLatitude() + " et longitude " + location.getLongitude());
//            }
//        });
//        //on affiche la position courante
//        Location location = locationManagerTel.getLastKnownLocation(locationManagerTel.getBestProvider(criteria, false));
//        if(location.getLongitude()!=0.0){
//            latitude.setText(""+location.getLatitude());
//            longitude.setText(""+location.getLongitude());
//        }else
//        {
//            latitude.setText("error");
//            longitude.setText("error");
    }

    ;
}

//   private void getLocation() {
//        try {
//            locationManagerTel = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//            criteria = new Criteria();
//            if (locationManagerTel == null) {
//                Log.d("GPS-getLocation", "locationManager == null");
//            }
//            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    Activity#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for Activity#requestPermissions for more details.
//                return;
//            }
//            location = locationManagerTel.getLastKnownLocation(locationManagerTel
//                    .getBestProvider(criteria, false));
//            locationManagerTel.requestLocationUpdates(LocationManager.GPS_PROVIDER,
//                    6000, 1, (LocationListener) this);
//            if (location == null) { //gps provider error try passive
//                location = locationManagerTel.getLastKnownLocation(locationManagerTel
//                        .getBestProvider(criteria, false));
//                locationManagerTel.requestLocationUpdates(LocationManager.PASSIVE_PROVIDER,
//                        2000, 1, (LocationListener) this);
//            }
//            if (location == null) {
//                if (map != null) {
//                    location = map.getMyLocation();
//                }
//            }
//        } finally {
//            Log.d("getLocation","Unable load getLcation()");
//        }
//    }

//}

//    private String LastKnownLocation() {
//        Criteria criteria = new Criteria();
//        String provider = locationManager.getBestProvider(criteria, false);
//        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    Activity#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for Activity#requestPermissions for more details.
//            return TODO;
//        }
//        Location location = locationManager.getLastKnownLocation(provider);
//    protected void onResume() {
//        super.onResume();
//        locationManager.requestLocationUpdates(provider, 400, 1, this);
//    }
//}


