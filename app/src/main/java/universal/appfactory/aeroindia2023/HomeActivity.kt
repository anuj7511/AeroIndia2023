package universal.appfactory.aeroindia2023

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    var TAG:String ="MainActivity "
    var ERROR_DIALOG_REQUEST:Int = 9001

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        supportActionBar?.hide()

        if(isServicesOk()){
            initMaps();
        }
    }

    fun initMaps(){
        BtnMap.setOnClickListener {
            var intent = Intent(this,MapsActivity::class.java)
            startActivity(intent)
        }
    }

    fun isServicesOk():Boolean{
        Log.d(TAG,"isServiesOk: checking google services version")

        var available:Int = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)

        if(available== ConnectionResult.SUCCESS){
            //everthing is fine and the user can make map requests
            Log.d(TAG,"isServicesOk: Google play services is working")
            return true
        }else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG,"isServicesOk: an error occured but we can fix it")
            var dialog: Dialog? = GoogleApiAvailability.getInstance().getErrorDialog(this,available,ERROR_DIALOG_REQUEST)
            dialog?.show()
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show()
        }
        return false
    }
}