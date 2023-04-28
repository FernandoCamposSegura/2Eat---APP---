package com.fcampos.toeatapp.view;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.os.Bundle;

import com.fcampos.toeatapp.R;
import com.fcampos.toeatapp.contract.EstablishmentListContract;
import com.fcampos.toeatapp.domain.Establishment;
import com.fcampos.toeatapp.presenter.EstablishmentMapPresenter;
import com.mapbox.geojson.Point;
import com.mapbox.maps.CameraOptions;
import com.mapbox.maps.MapView;
import com.mapbox.maps.plugin.annotation.AnnotationConfig;
import com.mapbox.maps.plugin.annotation.AnnotationPlugin;
import com.mapbox.maps.plugin.annotation.AnnotationPluginImplKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManagerKt;
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions;

import java.util.List;

public class EstablishmentMapView extends AppCompatActivity implements EstablishmentListContract.View {

    private MapView mapView;
    private PointAnnotationManager pointAnnotationManager;
    private EstablishmentMapPresenter establishmentMapPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_establishment_map_view);

        mapView = findViewById(R.id.mapView);
        establishmentMapPresenter = new EstablishmentMapPresenter(this);
        establishmentMapPresenter.loadEstablishments();
        initializePointManager();
    }

    @Override
    public void showEstablishments(List<Establishment> establishmentList) {
        for (Establishment establishment : establishmentList) {
            Point point = Point.fromLngLat(establishment.getLongitude(), establishment.getLatitude());
            addMarker(point, establishment.getName());
        }

        Establishment lastEstablishment = establishmentList.get(establishmentList.size() - 1);
        setCameraPosition(Point.fromLngLat(lastEstablishment.getLongitude(), lastEstablishment.getLatitude()));
    }

    private void initializePointManager() {
        AnnotationPlugin annotationPlugin = AnnotationPluginImplKt.getAnnotations(mapView);
        AnnotationConfig annotationConfig = new AnnotationConfig();
        pointAnnotationManager = PointAnnotationManagerKt.createPointAnnotationManager(annotationPlugin, annotationConfig);
    }

    private void addMarker(Point point, String title) {
        PointAnnotationOptions pointAnnotationOptions = new PointAnnotationOptions()
                .withPoint(point)
                .withTextField(title)
                .withIconImage(BitmapFactory.decodeResource(getResources(), R.drawable.charging_point_marker));
        pointAnnotationManager.create(pointAnnotationOptions);
    }

    private void setCameraPosition(Point point) {
        CameraOptions cameraPosition = new CameraOptions.Builder()
                .center(point)
                .pitch(0.0)
                .zoom(13.5)
                .bearing(-17.6)
                .build();
        mapView.getMapboxMap().setCamera(cameraPosition);
    }
}