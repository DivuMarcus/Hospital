package strattons.com.hospital.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import kotlinx.android.synthetic.main.activity_check.*
import strattons.com.hospital.R

class checkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check)

        val fab: View =findViewById<View>(R.id.fab)
        fab.setOnClickListener({ view ->
            Snackbar.make(view,"Here's a Snackbar",Snackbar.LENGTH_LONG)
                    .setAction("Action",null)
                    .show()
        })
    }
}
