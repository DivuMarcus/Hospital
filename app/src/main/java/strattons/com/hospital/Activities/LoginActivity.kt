package strattons.com.hospital.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import strattons.com.hospital.R

class LoginActivity : AppCompatActivity() {
var mAuth=FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val loginBtn=findViewById<View>(R.id.appCompatButtonLogin) as Button
        val registerText=findViewById<View>(R.id.LinkRegisterButton) as TextView

        loginBtn.setOnClickListener(View.OnClickListener {
            view -> login()
        })

        registerText.setOnClickListener(View.OnClickListener {
            view -> register()
        })
    }

    private fun login(){
        val emailTxt=findViewById<View>(R.id.textInputEditTextEmail) as EditText
        var email=emailTxt.text.toString()
        val passwordTxt=findViewById<View>(R.id.textInputEditTextPassword) as EditText
        var password=passwordTxt.text.toString()

        if(!email.isEmpty()&&!password.isEmpty()){
            this.mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(this,{ task->
                if (task.isSuccessful) {
                    startActivity(Intent(this, checkActivity::class.java))
                    Toast.makeText(this, "Successfully Logged in", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Error Logging in", Toast.LENGTH_SHORT).show()
                }
            })

        }else {
            Toast.makeText(this, "Please fill up the Credentials", Toast.LENGTH_SHORT).show()
        }
    }

    private fun register(){
        startActivity(Intent(this,DetailActivity::class.java))
    }
   }