package com.jigar.android.retrofitcall;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.jigar.android.retrofitcall.Adapter.Adapter_getCandidate;
import com.jigar.android.retrofitcall.Interfaces.Api;
import com.jigar.android.retrofitcall.Models.Get_Candidate;
import com.jigar.android.retrofitcall.Response.GetCandidateResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class MainActivity extends AppCompatActivity implements LocationListener {
    String candidate_id;
    ListView listView;
    TextView tv_location;
    LocationManager locationManager;
    private FusedLocationProviderClient client;
    Criteria criteria;
    String mprovider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listview);
        tv_location = (TextView)findViewById(R.id.tv_location);


        Intent get_intent = getIntent();
        candidate_id = get_intent.getStringExtra("Candidante_id");


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://35.162.89.140:82/GotHireServices.svc/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api api = retrofit.create(Api.class);

        Call<GetCandidateResponse> call = api.Candidte_paramer(candidate_id);

        call.enqueue(new Callback<GetCandidateResponse>() {
            @Override
            public void onResponse(Call<GetCandidateResponse> call, Response<GetCandidateResponse> response) {
                List<Get_Candidate> list_candidate = response.body().getList_candidate();

                Adapter_getCandidate adapter_getCandidate = new Adapter_getCandidate(list_candidate, MainActivity.this, getLayoutInflater());
                listView.setAdapter(adapter_getCandidate);
            }

            @Override
            public void onFailure(Call<GetCandidateResponse> call, Throwable t) {

            }
        });
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        getPermission();
        client = LocationServices.getFusedLocationProviderClient(MainActivity.this);
        if (ActivityCompat.checkSelfPermission(this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {//&& ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED

            return;
        }

        LocationManager manager = (LocationManager) getSystemService(Context.LOCATION_SERVICE );
        boolean statusOfGPS = manager.isProviderEnabled(LocationManager.GPS_PROVIDER);


        if(statusOfGPS==false)
        {
            Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivityForResult(intent,101);
        }
        if(statusOfGPS==true) {
            client.getLastLocation().addOnSuccessListener(MainActivity.this, new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(android.location.Location location) {
                    if (location != null) {
                     //   ioc_function(location);
                        String lati = String.valueOf(location.getLatitude());
                        String logi = String.valueOf(location.getLongitude());
                        tv_location.setText("Lati"+location.getLatitude() + lati +"Logi "+logi);
                    } else {

                        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                        criteria = new Criteria();
                        mprovider = locationManager.getBestProvider(criteria, false);

                        if (mprovider != null && !mprovider.equals("")) {
                            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                                return;
                            }
                            Location location1 = locationManager.getLastKnownLocation(mprovider);
                            locationManager.requestLocationUpdates(mprovider, 15000, 1, MainActivity.this);

                            if (location1 != null)
                                onLocationChanged(location1);
                        }
                    }
                }
            });

        }


    }
    private void getPermission()
    {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);

    }
    @Override
    public void onLocationChanged(Location location) {
        String lati = String.valueOf(location.getLatitude());
        String logi = String.valueOf(location.getLongitude());
        tv_location.setText("Lati"+location.getLatitude() + lati +"Logi "+logi);
        //ioc_function(location);

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

}
