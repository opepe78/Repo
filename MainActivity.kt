package hu.novodata.nsmobilmanagerk

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_main_menu.*


class MainActivity : AppCompatActivity() {
    var mAuth: FirebaseAuth? = null
    var mUser : FirebaseUser? = null
    var mAuthListener: FirebaseAuth.AuthStateListener? = null
    //val textViewVersion = findViewById<TextView>(R.id.textViewVersion)
    //var mDatabase: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);

        /*
        var mess = "aaaaaaaaaaaaaaaaaaaa"
        var e = Global.AESencrypt(mess)
        //var message64 = "sfwbrsMXMHEpbR8j3h2NOg=="
        var d = Global.AESdecrypt(e)
        */

        mAuth = FirebaseAuth.getInstance()
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth: FirebaseAuth ->
            mUser = firebaseAuth.currentUser
            if (mUser != null) {
                startActivity(Intent(this, LoginActivity2::class.java))
                finish()
            } else {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        mAuth!!.addAuthStateListener(mAuthListener!!)
    }

    override fun onStop() {
        super.onStop()
        if (mAuthListener != null) {
            mAuth!!.removeAuthStateListener(mAuthListener!!)
        }
    }
}