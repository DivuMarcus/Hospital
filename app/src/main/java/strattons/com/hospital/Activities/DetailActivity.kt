package strattons.com.hospital.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds
import strattons.com.hospital.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_login.*


class DetailActivity : AppCompatActivity() {

    private lateinit var mInterstitialAd: InterstitialAd

    private val activity = this@DetailActivity

    var mAuth = FirebaseAuth.getInstance()
    lateinit var mDatabase: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val adRequest = AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build()

        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd!!.adUnitId = "ca-app-pub-5003436173149844/4656226540"
        mInterstitialAd!!.loadAd(adRequest)
        mInterstitialAd!!.adListener = object : AdListener() {
            override fun onAdClosed() {
                super.onAdClosed()
                startActivity(Intent(this@DetailActivity, LoginActivity::class.java))

            }
        }

        val registrationButton = findViewById<View>(R.id.appCompatButtonRegister) as Button

        mDatabase = FirebaseDatabase.getInstance().getReference("Names")

        registrationButton.setOnClickListener(View.OnClickListener { view ->
            registerUser()
        })

    }

    private fun registerUser() {
        val emailTxt = findViewById<View>(R.id.textInputEditTextEmail) as EditText
        val passwordtxt = findViewById<View>(R.id.textInputEditTextPassword) as EditText
        val mobiletxt = findViewById<View>(R.id.textInputEditTextMobile) as EditText
        val nameTxt = findViewById<View>(R.id.textInputEditTextName) as EditText
        val addresstxt = findViewById<View>(R.id.textInputEditTextAddress) as EditText

        var email = emailTxt.text.toString()
        var password = passwordtxt.text.toString()
        var mobile = mobiletxt.text.toString()
        var name = nameTxt.text.toString()
        var address = addresstxt.text.toString()




        if (!email.isEmpty() && !password.isEmpty() && !mobile.isEmpty() && !name.isEmpty() && !address.isEmpty()) {
            mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, { task ->
                if (task.isSuccessful) {
                    val user = mAuth.currentUser
                    val uid = user!!.uid
                    mDatabase.child(uid).child("Name").setValue(name)
                    if (mInterstitialAd.isLoaded() || mInterstitialAd.isLoading()) {
                        mInterstitialAd.show()
                    } else {
                        startActivity(Intent(this, LoginActivity::class.java))
                    }
                    Toast.makeText(this, "Successfully Registered", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error Registering, Try Again", Toast.LENGTH_LONG).show()
                }
            })
        } else {
            Toast.makeText(this, "Please Fill Up the Credentials", Toast.LENGTH_LONG).show()
        }


    }

}


