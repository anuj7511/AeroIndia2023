package universal.appfactory.aeroindia2023

import android.annotation.SuppressLint
import android.app.Application
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import universal.appfactory.aeroindia2023.agendas.AgendaActivity
import universal.appfactory.aeroindia2023.agendas.AgendaViewModel
import universal.appfactory.aeroindia2023.delegate.*
import universal.appfactory.aeroindia2023.exhibitors.ExhibitorViewModel
import universal.appfactory.aeroindia2023.exhibitors.ExhibitorsActivity
import universal.appfactory.aeroindia2023.faqs.FaqsViewModel
import universal.appfactory.aeroindia2023.faqs.QuestionsActivity
import universal.appfactory.aeroindia2023.liaison_officer.*
import universal.appfactory.aeroindia2023.liaison_officer.trail.TrailActivity
import universal.appfactory.aeroindia2023.liaison_officer.trail.trailhome.TrailViewModel
import universal.appfactory.aeroindia2023.products.ProductViewModel
import universal.appfactory.aeroindia2023.products.ProductsActivity
import universal.appfactory.aeroindia2023.speakers.SpeakerViewModel
import universal.appfactory.aeroindia2023.speakers.SpeakersActivity

class HomepageActivity : AppCompatActivity() {

    private var backpress: Int = 0
    var navigableBundle = Bundle()
    private lateinit var agendaViewModel: AgendaViewModel
    private lateinit var productViewModel: ProductViewModel
    private lateinit var speakerViewModel: SpeakerViewModel
    private lateinit var questionsViewModel : FaqsViewModel
    private lateinit var liaisonViewModel: LiaisonViewModel
    private lateinit var delegateViewModel: DelegateViewModel
    private lateinit var trailViewModel: TrailViewModel
    private lateinit var exhibitorViewModel: ExhibitorViewModel
    private lateinit var userType: String
    private lateinit var foreignKeyId : String

//    0 -> "Unknown role"
//    1 -> "Attendee"
//    2 -> "Delegates"
//    3 -> "Liaison officer"
//    4 -> "Washroom zonal manager"
//    5 -> "Washroom super manager"
//    6 -> "Exhibitor"
//    7 -> "Media"

    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        supportActionBar?.hide()

        navigableBundle = intent.extras!!
        userType = navigableBundle.getString("userType", "0")
        foreignKeyId = navigableBundle.getString("foreignKeyId","0")
        findViewById<TextView>(R.id.userNameView).text = navigableBundle.getString("name", "DEFAULT USER")

        Log.i("Homepage activity msg", "User type: $userType")

        // Icons changed according to usertype
        when(userType){
            "2" -> {
                    findViewById<ImageView>(R.id.resources).setImageResource(R.drawable.check_complaints) //DelegateVehicleActivity
                    findViewById<ImageView>(R.id.videos).setImageResource(R.drawable.check_complaints) // DelegateHotelActivity
                    findViewById<ImageView>(R.id.twitter).setImageResource(R.drawable.check_complaints) // DelegateTravelActivity
                    findViewById<ImageView>(R.id.faq).setImageResource(R.drawable.check_complaints) // AssignedLOActivity
                    findViewById<ImageView>(R.id.lodging_complaint).setImageResource(R.drawable.check_complaints) // ETicketActivity
                    }
            "3" -> {
                    findViewById<ImageView>(R.id.resources).setImageResource(R.drawable.check_complaints) // VehicleActivity
                    findViewById<ImageView>(R.id.videos).setImageResource(R.drawable.check_complaints) // HotelActivity
                    findViewById<ImageView>(R.id.twitter).setImageResource(R.drawable.check_complaints) // TravelActivity
                    findViewById<ImageView>(R.id.driving).setImageResource(R.drawable.check_complaints) //DelegationActivity
                    findViewById<ImageView>(R.id.lodging_complaint).setImageResource(R.drawable.check_complaints) // TrailActivity
                    }
            "4" -> {
                    findViewById<ImageView>(R.id.lodging_complaint).setImageResource(R.drawable.check_complaints) // ZonalManagerActivity
                    }
            "5" -> {
                    findViewById<ImageView>(R.id.lodging_complaint).setImageResource(R.drawable.check_complaints) // ManagerActivity
                    }
            else -> Log.i("Homepage activity msg", "No changes done")
        }

        agendaViewModel = ViewModelProvider(this)[AgendaViewModel::class.java]
        agendaViewModel.init((this as AppCompatActivity).applicationContext as Application)
        agendaViewModel.loadAllAgendas(true)

        productViewModel = ViewModelProvider(this)[ProductViewModel::class.java]
        productViewModel.init((this as AppCompatActivity).applicationContext as Application)
        productViewModel.loadAllProducts(true)

        speakerViewModel = ViewModelProvider(this)[SpeakerViewModel::class.java]
        speakerViewModel.init((this as AppCompatActivity).applicationContext as Application)
        speakerViewModel.loadAllSpeakers(true)

        exhibitorViewModel = ViewModelProvider(this)[ExhibitorViewModel::class.java]
        exhibitorViewModel.init((this as AppCompatActivity).applicationContext as Application)
        exhibitorViewModel.loadAllExhibitors(true)

        questionsViewModel = ViewModelProvider(this)[FaqsViewModel::class.java]
        questionsViewModel.init((this as AppCompatActivity).applicationContext as Application)
        questionsViewModel.loadAllFaqs(true)

        if(userType=="3"){
            liaisonViewModel = ViewModelProvider(this)[LiaisonViewModel::class.java]
            liaisonViewModel.init((this as AppCompatActivity).applicationContext as Application)
            liaisonViewModel.loadAllLiaisonOfficers(true,foreignKeyId.toInt())
        }

        delegateViewModel = ViewModelProvider(this)[DelegateViewModel::class.java]
        delegateViewModel.init((this as AppCompatActivity).applicationContext as Application)
        delegateViewModel.loadAllDelegates(true)

        trailViewModel = ViewModelProvider(this)[TrailViewModel::class.java]
        trailViewModel.init((this as AppCompatActivity).applicationContext as Application)
        trailViewModel.loadAllTrail(true)

    }

    fun iconClicked(view: View) {
        val tag = view.tag.toString()
        var navigateIntent = Intent(this@HomepageActivity,  DummyActivity::class.java)

        when(tag.toInt()){
            0 -> {navigateIntent = Intent(this@HomepageActivity, AgendaActivity::class.java)
                    backpress=0}         // Agenda
            1 -> {navigateIntent = Intent(this@HomepageActivity, SpeakersActivity::class.java)
                    backpress=0}         // Speakers
            2 -> {navigateIntent = Intent(this@HomepageActivity, DummyActivity::class.java)
                    backpress=0}         // Venue maps
            3 -> {
                    navigateIntent = when(userType){
                    "3" -> Intent(this@HomepageActivity, DelegationActivity::class.java)
                    else -> Intent(this@HomepageActivity, MapsActivity::class.java)
                    }
                    backpress=0
                }         // Driving directions
            4 -> {
                    navigateIntent = when(userType){
                    "2" -> Intent(this@HomepageActivity, DelegateVehicleActivity::class.java)
                    "3" -> Intent(this@HomepageActivity, VehicleActivity::class.java)
                    else -> Intent(this@HomepageActivity, ExhibitorsActivity::class.java)
                    }
                    backpress=0
                }                        // Resources
            5 -> {
                    navigateIntent = when(userType){
                    "2" -> Intent(this@HomepageActivity, DelegateHotelActivity::class.java)
                    "3" -> Intent(this@HomepageActivity, HotelActivity::class.java)
                    else -> Intent(this@HomepageActivity, DummyActivity::class.java)
                    }
                    backpress=0
                }                       // Videos
            6 -> {
                    navigateIntent = when(userType){
                    "2" -> Intent(this@HomepageActivity, DelegateTravelActivity::class.java)
                    "3" -> Intent(this@HomepageActivity, TravelActivity::class.java)
                    else -> Intent(this@HomepageActivity, ProductsActivity::class.java)
                    }
                    backpress=0
                }         // Twitter or products
            7 -> {
                    navigateIntent = when(userType){
                    "2" -> Intent(this@HomepageActivity, AssignedLOActivity::class.java)
                    else -> Intent(this@HomepageActivity, QuestionsActivity::class.java)
                    }
                    backpress=0
                }         // FAQ
            8 -> {
                    navigateIntent = when(userType){
                        "2" -> Intent(this@HomepageActivity, DummyActivity::class.java) // My ETicket
                        "3" -> Intent(this@HomepageActivity, TrailActivity::class.java)
                        "4" -> Intent(this@HomepageActivity, ZonalManagerActivity::class.java)
                        "5" -> Intent(this@HomepageActivity, ManagerActivity::class.java)
                        else -> Intent(this@HomepageActivity, Feedback::class.java)
                    }
                    backpress=0
                }                        // Lodging complaints (or) View zonal complaints (or) View super complaints

            9 -> {navigateIntent = Intent(this@HomepageActivity, ProfileActivity::class.java)
                    backpress=0}         // Profile view
            else -> { Log.i("Homepage msg", "Nothing was clicked") }
        }

        navigateIntent.putExtras(navigableBundle)
        startActivity(navigateIntent)

    }

    fun refreshPage(view: View) {
        agendaViewModel.loadAllAgendas(true)
        productViewModel.loadAllProducts(true)
        speakerViewModel.loadAllSpeakers(true)
        exhibitorViewModel.loadAllExhibitors(true)
        questionsViewModel.loadAllFaqs(true)
        if(userType=="3"){
            liaisonViewModel.loadAllLiaisonOfficers(true,foreignKeyId.toInt())
        }
        delegateViewModel.loadAllDelegates(true)
        trailViewModel.loadAllTrail(true)

        Log.i("Homepage activity message", "Home page refreshed")
        Toast.makeText(this, "Page refreshed", Toast.LENGTH_SHORT).show()
    }

    override fun onBackPressed(){
        backpress += 1
        if(backpress > 1){
            finishAffinity()
        }
        else{
            Toast.makeText(this, "Press back once again to exit", Toast.LENGTH_SHORT).show()
        }
    }
}