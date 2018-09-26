package strattons.com.hospital.Activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import strattons.com.hospital.R

class MainActivity : AppCompatActivity() {
    var new: Button? = null
    var existButton: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        new = findViewById(R.id.ButtonTop)

        new?.setOnClickListener({
            var clickIntent = Intent(this@MainActivity, DetailActivity::class.java)
            startActivity(clickIntent)
        })


    }
}
