package com.seniorproject.patrick.ksugo;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.maps.android.PolyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class InteractiveMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private int REQUEST_PERMISSION = 1;
    private double currentLat;
    private double currentLon;
    private int currentCampus = 0;
    private Spinner DestinationPicker;
    private Button FindPath;
    private Button SwitchCampus;
    private Dictionary<String, Lalo> maMarkers = new Hashtable<>();
    private Dictionary<String, Lalo> kMarkers = new Hashtable<>();
    Context context;
    List<LatLng> pathList = new ArrayList<LatLng>();

    private String[] mariettaLocations = new String[]{
            "Please Select Destination",
            "Atrium", "Engineering Building", "Stingers", "Academic Building", "Design Building",
            "Lawrence V. Johnson Library", "Engineering Lab Building", "W. Clair Harris Textile Center",
            "Architecture Building", "Hornet Village Suites", "Civil Engineering Technology Building",
            "Mathematics Building", "Joe Mack Wilson Student Center", "Recreation and Wellness Center",
            "Gym", "Courtyard", "Commons Apartment", "Howell Hall", "Norton"};
    private String[] kennesawLocations = new String[]{
            "Please Select Destination", "Austin Residence Complex", "Fred Stillwell Stadium",
            "Convocation Center", "Math and Statistics Building", "Kennesaw State University Book Store",
            "Math and Statistics Building", "KSU Horace W. Sturgis Library",
            "Center for Student Leadership", "Coles College of Business", "English Building - Kennesaw State University",
            "The Commons", "WellStar College of Health and Human Services",
            "Wilson Building", "Kennesaw State University School of Art and Design",
            "Zuckerman Museum of Art", "Dr. Bobbie Bailey & Family Performance Center",
            "University Village", "KSU Place", "Dr. Betty L. Siegel Student Recreation and Activities Center",
            "Math and Science Building"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        DestinationPicker = (Spinner) findViewById(R.id.dropDestinationPicker);
        FindPath = (Button) findViewById(R.id.btnFindPath);
        SwitchCampus = (Button) findViewById(R.id.btnSwitchCampus);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mariettaLocations);
        DestinationPicker.setAdapter(adapter);

        FindPath.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDirectionsFromAPI();
            }
        });

        SwitchCampus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCampusMethod();
            }
        });

        creatingTheDictionaryOfMarkers();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng Marietta = new LatLng(33.938297, -84.519800);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Marietta, 17.0f));
        LatLngBounds MareittaCampus = new LatLngBounds(
                new LatLng(33.936611, -84.523467), new LatLng(33.941344, -84.517206)
        );

        mMap.setLatLngBoundsForCameraTarget(MareittaCampus);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION);
        } else {
            updatePosition();
            mMap.setMyLocationEnabled(true);
        }
    }

    private void updatePosition() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQUEST_PERMISSION);
        } else {
            LocationManager loc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = loc.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (location != null) {
                currentLon = location.getLongitude();
                currentLat = location.getLatitude();
            }
        }
    }

    private void getDirectionsFromAPI() {
        String Destination = DestinationPicker.getSelectedItem().toString();
        if (Destination == "Please Select Destination") {
            Toast.makeText(this, "Please Select a Destination!", Toast.LENGTH_SHORT).show();
            return;
        }
        updatePosition();

        Lalo dest = new Lalo();

        if (currentCampus == 0) {
            dest.equal(maMarkers.get(Destination));
        } else {
            dest.equal(kMarkers.get(Destination));
        }

        double lat = dest.returnLat();
        double longi = dest.returnLong();
        String OrginString = "origin=" + String.valueOf(currentLat) + "," + String.valueOf(currentLon);
        String DestinationString = "destination=" + String.valueOf(lat) + "," + String.valueOf(longi);
        DestinationClient(OrginString, DestinationString);

    }

    private void switchCampusMethod() {

        if (currentCampus == 0) {
            currentCampus++;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, kennesawLocations);
            DestinationPicker.setAdapter(adapter);
        } else {
            currentCampus--;
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, mariettaLocations);
            DestinationPicker.setAdapter(adapter);
        }

        if (currentCampus == 0) {
            LatLng Marietta = new LatLng(33.938297, -84.519800);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Marietta, 17.0f));
            LatLngBounds MareittaCampus = new LatLngBounds(
                    new LatLng(33.936611, -84.523467), new LatLng(33.941344, -84.517206)
            );

            mMap.setLatLngBoundsForCameraTarget(MareittaCampus);

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION);
            } else {
                updatePosition();
                mMap.setMyLocationEnabled(true);
            }
        } else {
            LatLng Kennesaw = new LatLng(34.038289, -84.581722);

            LatLngBounds KennesawCampus = new LatLngBounds(
                    new LatLng(34.032073, -84.584278), new LatLng(34.044868, -84.581324)
            );

            mMap.setLatLngBoundsForCameraTarget(KennesawCampus);
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Kennesaw, 17.0f));
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION);
            } else {
                updatePosition();
                mMap.setMyLocationEnabled(true);
            }
        }
    }

    private void creatingTheDictionaryOfMarkers() {
        //All the Longitudes and Latitudes for Key Locations
        Lalo Atrium = new Lalo(33.937658, -84.520161);
        Lalo EngineeringBuilding = new Lalo(33.938316, -84.522646);
        Lalo Stringers = new Lalo(33.937497, -84.522043);
        Lalo AcademicBuilding = new Lalo(33.938279, -84.520368);
        Lalo DesignBuidling = new Lalo(33.938092, -84.521058);
        Lalo LVJLibrary = new Lalo(33.939260, -84.519966);
        Lalo EngineeringLab = new Lalo(33.938503, -84.521035);
        Lalo TextileBuilding = new Lalo(33.936797, -84.519847);
        Lalo ArchitectureBuilding = new Lalo(33.936288, -84.519068);
        Lalo HV = new Lalo(33.936907, -84.521799);
        Lalo CETBuilding = new Lalo(33.935823, -84.520300);
        Lalo MathematicsBuilding = new Lalo(33.939997, -84.520444);
        Lalo JMStudentCenter = new Lalo(33.940486, -84.520493);
        Lalo RnWCenter = new Lalo(33.941217, -84.517830);
        Lalo Gym = new Lalo(33.935866, -84.518374);
        Lalo Courtyard = new Lalo(33.939171, -84.516601);
        Lalo Commons = new Lalo(33.937995, -84.517377);
        Lalo Howell = new Lalo(33.937989, -84.518623);
        Lalo Norton = new Lalo(33.939184, -84.519152);
        maMarkers.put("Atrium", Atrium);
        maMarkers.put("Engineering Building", EngineeringBuilding);
        maMarkers.put("Stingers", Stringers);
        maMarkers.put("Academic Building", AcademicBuilding);
        maMarkers.put("Design Building", DesignBuidling);
        maMarkers.put("Lawrence V. Johnson Library", LVJLibrary);
        maMarkers.put("Engineering Lab Building", EngineeringLab);
        maMarkers.put("W. Clair Harris Textile Center", TextileBuilding);
        maMarkers.put("Architecture Building", ArchitectureBuilding);
        maMarkers.put("Hornet Village Suites", HV);
        maMarkers.put("Civil Engineering Technology Building", CETBuilding);
        maMarkers.put("Mathematics Building", MathematicsBuilding);
        maMarkers.put("Joe Mack Wilson Student Center", JMStudentCenter);
        maMarkers.put("Recreation and Wellness Center", RnWCenter);
        maMarkers.put("Gym", Gym);
        maMarkers.put("Courtyard", Courtyard);
        maMarkers.put("Commons Apartment", Commons);
        maMarkers.put("Howell Hall", Howell);
        maMarkers.put("Norton", Norton);
        Lalo AustinComplex = new Lalo(34.033344, -84.583447);
        Lalo Stadium = new Lalo(34.033919, -84.581705);
        Lalo ConvocationCenter = new Lalo(34.036885, -84.580312);
        Lalo MathStatBuidling = new Lalo(34.037757, -84.583938);
        Lalo KSULibrary = new Lalo(34.038268, -84.583769);
        Lalo StudentLeadership = new Lalo(34.038987, -84.583060);
        Lalo BookStore = new Lalo(34.037902, -84.582924);
        Lalo ColesBusiness = new Lalo(34.039432, -84.582113);
        Lalo EnglishBuilding = new Lalo(34.039400, -84.583928);
        Lalo TheCommons = new Lalo(34.039852, -84.581953);
        Lalo Wellstar = new Lalo(34.040872, -84.582293);
        Lalo Wilson = new Lalo(34.040122, -84.583531);
        Lalo ArtnDesign = new Lalo(34.040084, -84.585198);
        Lalo Zuckerman = new Lalo(34.041137, -84.583394);
        Lalo Performance = new Lalo(34.041027, -84.583892);
        Lalo Village = new Lalo(34.043360, -84.582485);
        Lalo KSUPlace = new Lalo(34.044633, -84.583523);
        Lalo RecGym = new Lalo(34.036962, -84.582289);
        Lalo MSBuilding = new Lalo(34.036029, -84.583897);
        kMarkers.put("Austin Residence Complex", AustinComplex);
        kMarkers.put("Fred Stillwell Stadium", Stadium);
        kMarkers.put("Math and Statistics Building", MathStatBuidling);
        kMarkers.put("Kennesaw State University Book Store", BookStore);
        kMarkers.put("Convocation Center", ConvocationCenter);
        kMarkers.put("KSU Horace W. Sturgis Library", KSULibrary);
        kMarkers.put("Center for Student Leadership", StudentLeadership);
        kMarkers.put("Coles College of Business", ColesBusiness);
        kMarkers.put("English Building - Kennesaw State University", EnglishBuilding);
        kMarkers.put("The Commons", TheCommons);
        kMarkers.put("WellStar College of Health and Human Services", Wellstar);
        kMarkers.put("Wilson Building", Wilson);
        kMarkers.put("Kennesaw State University School of Art and Design", ArtnDesign);
        kMarkers.put("Zuckerman Museum of Art", Zuckerman);
        kMarkers.put("Dr. Bobbie Bailey & Family Performance Center", Performance);
        kMarkers.put("University Village", Village);
        kMarkers.put("KSU Place", KSUPlace);
        kMarkers.put("Dr. Betty L. Siegel Student Recreation and Activities Center", RecGym);
        kMarkers.put("Math and Science Building", MSBuilding);


    }

    private String API_Key = "AIzaSyAbVTym6Qf4N__TyHLT_iXNL24dJw14iPk";

    public void DestinationClient(String OrginString, String DestinationString) {
        RequestQueue rq = Volley.newRequestQueue(this);
        context = this;
        String URL_FOR_API = "https://maps.googleapis.com/maps/api/directions/json?" + OrginString + "&" +
                DestinationString + "&" + "mode=walking" + "&" + "key=" + API_Key;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL_FOR_API,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray route = response.getJSONArray("routes");
                    JSONObject routes = route.getJSONObject(0);
                    JSONObject ovPolylines = routes.getJSONObject("overview_polyline");
                    String encodedPoly = ovPolylines.getString("points");
                    pathList.clear();
                    pathList = PolyUtil.decode(encodedPoly);
                    putPolyOnMap();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(context, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });
        rq.add(jsonObjectRequest);
    }


    public void putPolyOnMap() {

        //Remove old Path
        mMap.clear();
        PolylineOptions options = new PolylineOptions();

        //Create new path
        for (int i = 0; i < pathList.size() - 1; i++) {
            LatLng start = pathList.get(i);
            LatLng end = pathList.get(i + 1);

            options.add(new LatLng(start.latitude, start.longitude),
                    new LatLng(end.latitude, end.longitude));
        }
        mMap.addPolyline(options);

    }
}


