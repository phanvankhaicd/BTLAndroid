package com.example.corona.ViewController.Home.FragmentHome;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.corona.Model.Global.CoronaGlobal;
import com.example.corona.Model.Global.DataG;
import com.example.corona.Model.MapNcovi.MapNcovi;
import com.example.corona.Model.VN.CoronaVN;
import com.example.corona.Model.VN.Data;
import com.example.corona.Network.DataServices;
import com.example.corona.R;
import com.example.corona.Util.LoadingDialog;
import com.example.corona.ViewController.Home.AnalyticActivity;
import com.mapbox.android.core.permissions.PermissionsListener;
import com.mapbox.android.core.permissions.PermissionsManager;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.mapboxsdk.Mapbox;
import com.mapbox.mapboxsdk.camera.CameraPosition;
import com.mapbox.mapboxsdk.geometry.LatLng;
import com.mapbox.mapboxsdk.location.LocationComponent;
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions;
import com.mapbox.mapboxsdk.location.modes.CameraMode;
import com.mapbox.mapboxsdk.location.modes.RenderMode;
import com.mapbox.mapboxsdk.maps.MapView;
import com.mapbox.mapboxsdk.maps.MapboxMap;
import com.mapbox.mapboxsdk.maps.MapboxMapOptions;
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback;
import com.mapbox.mapboxsdk.maps.Style;
import com.mapbox.mapboxsdk.maps.SupportMapFragment;
import com.mapbox.mapboxsdk.style.layers.PropertyFactory;
import com.mapbox.mapboxsdk.style.layers.SymbolLayer;
import com.mapbox.mapboxsdk.style.sources.GeoJsonSource;

import java.util.ArrayList;
import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.corona.Util.AppConfig.numberWithComas;
import static com.mapbox.mapboxsdk.style.layers.PropertyFactory.iconOffset;


/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment implements PermissionsListener, View.OnClickListener {

    private LinearLayout VN, GLOBAL;
    private MapView mapView;
    private MapboxMap mapboxMap;
    private PermissionsManager permissionsManager;
    private TextView tvDead, tvInfected, tvRecover, tvTimeUpdate, tvLiveDeath, tvLiveInfected, tvLiveRecover, tvInfo;
    private List<Feature> symbolLayerIconFeatureList;
    private static final String ICON_SOURCE_ID = "ICON_SOURCE_ID";
    private static final String ICON_ID = "ICON_ID";
    private static final String ICON_LAYER_ID = "ICON_LAYER_ID";
    LoadingDialog loadingDialog;
    private Button btnAnalytic;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        init(view);
        initIconCoordinates();
        VN.setOnClickListener(this);
        GLOBAL.setOnClickListener(this);
        Mapbox.getInstance(getContext(), "pk.eyJ1IjoicGhhbnZhbmtoYWljZCIsImEiOiJjazlrNHp4ZzAwMWtjM2VsYm5qdnZvZ3gxIn0.3-bzqDyNVJ9vxYgSodPeHA");
        SupportMapFragment mapFragment;
        if (savedInstanceState == null) {

// Create fragment
            final FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();

// Build mapboxMap
            MapboxMapOptions options = MapboxMapOptions.createFromAttributes(getContext(), null);
            options.camera(new CameraPosition.Builder()
                    .target(new LatLng(21.0228161, 105.801944))
                    .zoom(9)
                    .build());

// Create map fragment
            mapFragment = SupportMapFragment.newInstance(options);

// Add map fragment to parent container
            transaction.add(R.id.container, mapFragment, "com.mapbox.map");
            transaction.commit();
        } else {
            mapFragment = (SupportMapFragment) getActivity().getSupportFragmentManager().findFragmentByTag("com.mapbox.map");
        }

        if (mapFragment != null) {
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(@NonNull MapboxMap mapboxMap) {
                    HomeFragment.this.mapboxMap = mapboxMap;
//                    mapboxMap.setStyle(Style.MAPBOX_STREETS, new Style.OnStyleLoaded() {
//                        @Override
//                        public void onStyleLoaded(@NonNull Style style) {
//
//// Map is set up and the style has loaded. Now you can add data or make other map adjustments
//                            enableLocationComponent(style);
//
//                        }
//                    });
                    mapboxMap.setStyle(new Style.Builder().fromUri(Style.MAPBOX_STREETS)
                                    .withImage(ICON_ID,
                                            BitmapFactory.decodeResource(getActivity().getResources(), R.mipmap.ic_vietnam))
                                    .withSource(new GeoJsonSource(ICON_SOURCE_ID,
                                            FeatureCollection.fromFeatures(symbolLayerIconFeatureList)))
                                    .withLayer(new SymbolLayer(ICON_LAYER_ID, ICON_SOURCE_ID)
                                            .withProperties(PropertyFactory.iconImage(ICON_ID),
                                                    iconOffset(new Float[]{0f, -9f}))),
                            new Style.OnStyleLoaded() {
                                @Override
                                public void onStyleLoaded(@NonNull Style style) {

                                    enableLocationComponent(style);

                                }
                            });
                }
            });
        }
        getDataVN();
        return view;
    }
    private void init(View view) {
        btnAnalytic = view.findViewById(R.id.btn_analytic);
        tvDead = view.findViewById(R.id.tv_dead);
        tvInfected = view.findViewById(R.id.tv_infected);
        tvRecover = view.findViewById(R.id.tv_recover);
        tvLiveDeath = view.findViewById(R.id.tv_live_death);
        tvLiveInfected = view.findViewById(R.id.tv_live_infected);
        tvLiveRecover = view.findViewById(R.id.tv_live_recover);
        tvTimeUpdate = view.findViewById(R.id.tv_time_update);
        tvInfo = view.findViewById(R.id.tv_info);
        VN = view.findViewById(R.id.ln_vietnam);
        GLOBAL = view.findViewById(R.id.ln_globle);
        loadingDialog = new LoadingDialog(getActivity());
    }
    private void initIconCoordinates() {
        symbolLayerIconFeatureList = new ArrayList<>();
        getLonLat();

//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(10.338784, 49.481615)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(15.081775, 49.957444)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(11.810747, 50.53269)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(16.308411, 51.35705)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(19.661215, 49.161803)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(16.799065, 46.864746)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(13.364485, 52.764672)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(8.457943, 51.203595)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(12.873831, 51.459068)));
//        symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(14.836448, 52.814126)));
    }

    private void getLonLat() {
        DataServices.getAPIServiceBoYTe().getLonLat()
                .enqueue(new Callback<MapNcovi>() {
                    @Override
                    public void onResponse(Call<MapNcovi> call, Response<MapNcovi> response) {
                        if (response.isSuccessful()) {
                            for (com.example.corona.Model.MapNcovi.Data i : response.body().getData()) {
                                symbolLayerIconFeatureList.add(Feature.fromGeometry(Point.fromLngLat(i.getLng(), i.getLat())));

                            }
                        }
                        else{
                        }
                    }

                    @Override
                    public void onFailure(Call<MapNcovi> call, Throwable t) {

                    }
                });
    }



    @SuppressWarnings({"MissingPermission"})
    private void enableLocationComponent(@NonNull Style loadedMapStyle) {
// Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(getContext())) {

// Get an instance of the LocationComponent.
            LocationComponent locationComponent = mapboxMap.getLocationComponent();

// Activate the LocationComponent
            locationComponent.activateLocationComponent(
                    LocationComponentActivationOptions.builder(getContext(), loadedMapStyle).build());

// Enable the LocationComponent so that it's actually visible on the map
            locationComponent.setLocationComponentEnabled(true);

// Set the LocationComponent's camera mode
            locationComponent.setCameraMode(CameraMode.TRACKING);

// Set the LocationComponent's render mode
            locationComponent.setRenderMode(RenderMode.NORMAL);
        } else {
            permissionsManager = new PermissionsManager(this);
            permissionsManager.requestLocationPermissions(getActivity());
        }
    }

    @Override
    public void onExplanationNeeded(List<String> permissionsToExplain) {

    }

    @Override
    public void onPermissionResult(boolean granted) {

    }

    void getDataVN() {
        loadingDialog.startLoadingDialog();
        DataServices.getAPIServiceOutsite().getCoronaVN().enqueue(new Callback<CoronaVN>() {
            @Override
            public void onResponse(Call<CoronaVN> call, Response<CoronaVN> response) {
                if (response.isSuccessful()) {
                    Data data = response.body().getData();
                    String time = timeConverse(data.getUpdatedAt());
                    tvInfo.setText("Thông tin Việt Nam");
                    tvDead.setText(data.getLatestData().getDeaths().toString());
                    tvInfected.setText(data.getLatestData().getConfirmed().toString());
                    tvRecover.setText(data.getLatestData().getRecovered().toString());
                    tvLiveDeath.setText("+ " + data.getToday().getDeaths());
                    tvLiveRecover.setText("+ " + data.getToday().getDeaths());
                    tvLiveInfected.setText("+ " + data.getToday().getConfirmed());
                    tvTimeUpdate.setText("Cập nhật: " + time);
                    loadingDialog.dismissLoadingDialog();

                }
                else{
                    loadingDialog.dismissLoadingDialog();
                }
            }

            @Override
            public void onFailure(Call<CoronaVN> call, Throwable t) {
                loadingDialog.dismissLoadingDialog();
            }
        });
    }

    void getDataGlobal() {

        DataServices.getAPIServiceOutsite().getCoronaGlobal().enqueue(new Callback<CoronaGlobal>() {
            @Override
            public void onResponse(Call<CoronaGlobal> call, Response<CoronaGlobal> response) {
                if (response.isSuccessful()) {
                    List<DataG> data = response.body().getData();
                    String time = timeConverse(data.get(0).getUpdatedAt());
                    tvInfo.setText("Thông tin thế giới");
                    tvDead.setText(numberWithComas(data.get(0).getDeaths().toString()));
                    tvInfected.setText(numberWithComas(data.get(0).getConfirmed().toString()));
                    tvRecover.setText(numberWithComas(data.get(0).getRecovered().toString()));
                    tvLiveDeath.setText("+ " + numberWithComas(data.get(0).getNewDeaths().toString()));
                    tvLiveRecover.setText("+ " + numberWithComas(data.get(0).getNewRecovered().toString()));
                    tvLiveInfected.setText("+ " + numberWithComas(data.get(0).getNewConfirmed().toString()));
                    tvTimeUpdate.setText("Cập nhật: " + time);
                }
                else{

                }
            }

            @Override
            public void onFailure(Call<CoronaGlobal> call, Throwable t) {

            }
        });
    }

    String timeConverse(String time) {
        String[] strTime = time.split("T");
        String[] date = strTime[0].split("-");

        return date[2] + "/" + date[1] + "/" + date[0] + " " + strTime[1].split("\\.")[0];
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_analytic:
                startActivity(new Intent(getContext(), AnalyticActivity.class));
                break;
            case R.id.ln_vietnam:
                getDataVN();
                break;
            case R.id.ln_globle:
                getDataGlobal();
                break;
        }
    }
}
