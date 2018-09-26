package strattons.com.hospital.Activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.activity_detail.view.*
import strattons.com.hospital.R

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        eye1.setOnClickListener {
            if (showhideButton.eye1.text.toString().equals(" ")) {
                editpassword.transformationMethod = HideReturnsTransformationMethod.getInstance()
                showhideButton.eye1.text = ""
                eye1?.setBackgroundResource(R.drawable.ic_custom_hide)


            } else {
                editpassword.transformationMethod = PasswordTransformationMethod.getInstance()
                showhideButton.eye1.text = " "
                eye1?.setBackgroundResource(R.drawable.ic_custom_show)
            }
        }
    }
}

