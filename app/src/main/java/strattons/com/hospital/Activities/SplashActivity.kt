package strattons.com.hospital.Activities

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.ActivityCompat
import android.widget.Toast
import strattons.com.hospital.R

class SplashActivity : AppCompatActivity() {

    var permissionString = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        if (!hasPermissions(this@SplashActivity, *permissionString)) {
            ActivityCompat.requestPermissions(this@SplashActivity, permissionString, 831)
        } else {
            Handler().postDelayed({
                val startAct = Intent(this@SplashActivity, LoginActivity::class.java)
                startActivity(startAct)
                this.finish()
            }, 1000)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            831 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Handler().postDelayed({
                        val startAct = Intent(this@SplashActivity, LoginActivity::class.java)
                        startActivity(startAct)
                        this.finish()
                    }, 1000)
                } else {
                    Toast.makeText(this@SplashActivity, "Please Grant all the permissions", Toast.LENGTH_SHORT).show()
                    this.finish()

                }
                return
            }
            else -> {
                Toast.makeText(this@SplashActivity, "Something Went Wrong, Try Again", Toast.LENGTH_SHORT)
                this.finish()
                return
            }
        }
    }

    fun hasPermissions(context: Context, vararg permissions: String): Boolean {
        var hasAllPermission = true
        for (permission in permissions) {
            val res = context.checkCallingOrSelfPermission(permission)
            if (res != PackageManager.PERMISSION_GRANTED) {
                hasAllPermission = false
            }
        }
        return hasAllPermission
    }
}
