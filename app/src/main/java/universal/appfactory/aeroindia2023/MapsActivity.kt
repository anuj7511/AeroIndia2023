package universal.appfactory.aeroindia2023

import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import kotlinx.android.synthetic.main.activity_maps.*
import kotlinx.android.synthetic.main.info_window_layout.*
import kotlinx.android.synthetic.main.info_window_layout.view.*
import universal.appfactory.aeroindia2023.databinding.ActivityMapsBinding

private data class LocationParameters(
    var latLng: LatLng,
    var zoom : Float,
    var title : String
)

private data class placeLocation(
    var latLng: LatLng,
    var title: String
)

class MapsActivity : AppCompatActivity(), OnMapReadyCallback , GoogleMap.OnInfoWindowClickListener,GoogleMap.InfoWindowAdapter {

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        mMap.setInfoWindowAdapter(this)
        mMap.setOnInfoWindowClickListener(this)

        Toast.makeText(this,"Map is ready", Toast .LENGTH_SHORT).show()
        // Add a marker in Sydney and move the camera


        if(mLocationPermissionGranted){
            currentLocationWithGpsCheck()
            if (ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                    this,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {

                return
            }


            mMap.isMyLocationEnabled = true

            mMap.uiSettings.isMyLocationButtonEnabled = false




            var ImportantLocationInEvent = arrayOf(placeLocation(LatLng(12.972442, 77.580643),"Bengalore"),
                placeLocation(LatLng(13.107568, 77.571198),"Yelahanka"),
                placeLocation(LatLng(13.1008329,77.5793724),"Yelahanka Old Town"),
                placeLocation(LatLng(13.1037608,77.5837306),"NavaChethana Hospital"),
                placeLocation(LatLng(13.1049802,77.5917985),"Yelahanka Railway Station"),
            )

            for(it in ImportantLocationInEvent){
                mMap.addMarker(MarkerOptions().position(it.latLng).title(it.title))
                mMap.moveCamera(CameraUpdateFactory.newLatLng(it.latLng))
            }
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(13.1037608,77.5837306),DEFAULT_ZOOM))
            init()
        }
    }

    override fun onInfoWindowClick(p0: Marker) {
        Toast.makeText(this, "Info window clicked",
            Toast.LENGTH_SHORT).show();
        var position = p0.position
        var intent = Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+position.latitude.toString()+","+position.longitude.toString()+"&mode=d"))
        intent.setPackage("com.google.android.apps.maps")

        startActivity(intent)

    }

    override fun getInfoContents(p0: Marker): View? {
        texttitle.text= p0.title
        textsub.text = p0.title

        return null
    }

    override fun getInfoWindow(p0: Marker): View? {
        var infoView : View = layoutInflater.inflate(R.layout.info_window_layout,null)

        infoView.texttitle.text= p0.title
        infoView.textsub.text = p0.title
        return infoView
    }

    var TAG = "MapsActivity"

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    var FINE_LOCATION = android.Manifest.permission.ACCESS_FINE_LOCATION
    var COARSE_LOCATION = android.Manifest.permission.ACCESS_COARSE_LOCATION
    var LOCATION_PERMISSION_REQUEST_CODE = 1234

    //vars
    private var mLocationPermissionGranted : Boolean = false
    private lateinit var mFusedLocationProviderClient: FusedLocationProviderClient
    private var DEFAULT_ZOOM = 14f

    //Location Suggestions
    var locationSuggestions = arrayOf("Bangalore","delhi","Bihar","dehradun","Chennai","Raipur","Raigard")

    //temporary Location markers
    private var locationMarkers : MutableList<Marker> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        getLocationPermission()
    }

    private fun init() {
        ic_gps.setOnClickListener {
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(LatLng(13.1037608,77.5837306),DEFAULT_ZOOM))
        }
    }

    private fun currentLocationWithGpsCheck(){
        var locationRequest : com.google.android.gms.location.LocationRequest = com.google.android.gms.location.LocationRequest()
        locationRequest.priority = com.google.android.gms.location.LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 3000

        var builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest).setAlwaysShow(true)

        var locationSettingsResponseTask : Task<LocationSettingsResponse> = LocationServices.getSettingsClient(applicationContext).checkLocationSettings(builder.build())

        locationSettingsResponseTask.addOnCompleteListener(OnCompleteListener(){ task ->
            try{
                var response: LocationSettingsResponse = task.getResult(ApiException::class.java)

                //request location from device
                getDeviceLocation()

            }catch (e : ApiException){
                if(e.statusCode == LocationSettingsStatusCodes.RESOLUTION_REQUIRED){
                    var resolvableApiException : ResolvableApiException = e as ResolvableApiException

                    try {
                        resolvableApiException.startResolutionForResult(this, 101)
                    }catch (sendIntentException : IntentSender.SendIntentException){

                    }

                }

                if(e.statusCode == LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE){
                    Toast.makeText(this,"Setting not available", Toast.LENGTH_SHORT).show()
                }
            }

        })
    }

    private fun getDeviceLocation(){

        mFusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        try{
            if(mLocationPermissionGranted){
                var location  = mFusedLocationProviderClient.lastLocation
                location.addOnCompleteListener(OnCompleteListener { task ->
                    if(task.isSuccessful){
                        var currentLocation = task.result as? Location
                        if (currentLocation != null) {
//                            moveCameraMulti(arrayOf(LocationParameters(LatLng(currentLocation.latitude,currentLocation.longitude),DEFAULT_ZOOM,"me")))
                        }
                    }else{
                        Toast.makeText(this,"Unable to get current location", Toast.LENGTH_SHORT).show()
                    }
                })
            }
        }catch(e : SecurityException){
            Log.e(TAG,"getDeviceLoaction: SecurityException"+e.message)
        }
    }

    private fun moveCameraMulti(location : Array<LocationParameters>){
        for(it in locationMarkers){
            it.remove()
        }
        locationMarkers.clear()
        for(it in location){
            moveCamera(it.latLng,it.zoom,it.title)
        }
    }


    private fun moveCamera(latLng: LatLng, zoom : Float,title:String){
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng,zoom))
        var options = MarkerOptions()
            .position(latLng)
            .title(title)
        mMap.addMarker(options)?.let { locationMarkers.add(it) }
        hideSoftKeyboard()
    }

    private fun initMap(){
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun getLocationPermission(){
        var permissions = arrayOf<String>(FINE_LOCATION,COARSE_LOCATION )

        if(ContextCompat.checkSelfPermission(this.applicationContext,
                FINE_LOCATION)== PackageManager.PERMISSION_GRANTED){
            if(ContextCompat.checkSelfPermission(this.applicationContext,
                    COARSE_LOCATION)== PackageManager.PERMISSION_GRANTED){
                mLocationPermissionGranted = true
                initMap()
            }else{
                ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE)
            }
        }else{
            ActivityCompat.requestPermissions(this,permissions,LOCATION_PERMISSION_REQUEST_CODE)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        mLocationPermissionGranted = false

        when(requestCode){
            LOCATION_PERMISSION_REQUEST_CODE -> {
                if(grantResults.size>0 ){
                    for(i in grantResults){
                        if(i != PackageManager.PERMISSION_GRANTED){
                            mLocationPermissionGranted = false
                            return
                        }
                    }
                    mLocationPermissionGranted = true
                    //initialize the map
                    initMap()
                }
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==101) {
            if(resultCode== RESULT_OK) {
                getDeviceLocation()
                Toast.makeText(this, "Location is enabled", Toast.LENGTH_SHORT).show()
            }
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Denied Location", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun hideSoftKeyboard(){
        this.window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN)
    }


}