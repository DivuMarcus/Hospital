package strattons.com.hospital.Activities

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import strattons.com.hospital.Adapters.UserRecyclerAdapter
import strattons.com.hospital.Databases.UserDatabase
import strattons.com.hospital.R
import strattons.com.hospital.User

class UserListActivity : AppCompatActivity() {

    private val activity = this@UserListActivity
    private lateinit var textViewName: AppCompatTextView
    private lateinit var recyclerViewUsers: RecyclerView
    private lateinit var listUsers: MutableList<User>
    private lateinit var usersRecyclerAdapter: UserRecyclerAdapter
    private lateinit var databaseHelper: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        supportActionBar!!.title = ""
        initViews()
        initObjects()

    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        textViewName = findViewById<View>(R.id.textViewName) as AppCompatTextView
        recyclerViewUsers = findViewById<View>(R.id.recyclerViewUsers) as RecyclerView
    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        listUsers = ArrayList()
        usersRecyclerAdapter = UserRecyclerAdapter(listUsers)

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerViewUsers.layoutManager = mLayoutManager
        recyclerViewUsers.itemAnimator = DefaultItemAnimator()
        recyclerViewUsers.setHasFixedSize(true)
        recyclerViewUsers.adapter = usersRecyclerAdapter
        databaseHelper = UserDatabase(activity)

        val emailFromIntent = intent.getStringExtra("EMAIL")
        textViewName.text = emailFromIntent

        var getDataFromSQLite = GetDataFromSQLite()
        getDataFromSQLite.execute()
    }

    /**
     * This class is to fetch all user records from SQLite
     */
    inner class GetDataFromSQLite : AsyncTask<Void, Void, List<User>>() {

        override fun doInBackground(vararg p0: Void?): List<User> {
            return databaseHelper.getAllUser()
        }

        override fun onPostExecute(result: List<User>?) {
            super.onPostExecute(result)
            listUsers.clear()
            listUsers.addAll(result!!)
        }

    }
}