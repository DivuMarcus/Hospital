package strattons.com.hospital.Activities

import android.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatTextView
import android.view.View
import strattons.com.hospital.Databases.UserDatabase
import strattons.com.hospital.Helpers.inputValidation
import strattons.com.hospital.R
import strattons.com.hospital.User
import com.google.firebase.auth.FirebaseAuth



class DetailActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@DetailActivity

    private lateinit var nestedScrollView: NestedScrollView

    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputLayoutConfirmPassword: TextInputLayout
    private lateinit var textInputLayoutMobile: TextInputLayout
    private lateinit var textInputLayoutaddress: TextInputLayout
    private lateinit var progressDialog: AlertDialog

    private lateinit var textInputEditTextName: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var textInputEditTextConfirmPassword: TextInputEditText
    private lateinit var textInputEditTextMobile: TextInputEditText
    private lateinit var textInputEditTextaddress: TextInputEditText

    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var appCompatTextViewLoginLink: AppCompatTextView

    private lateinit var inputValidation: inputValidation
    private lateinit var databaseHelper: UserDatabase

    private var mAuth: FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        mAuth = FirebaseAuth.getInstance()

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()


    }


    /* private lateinit var inputValidation: InputValidation
     private lateinit var databaseHelper: DatabaseHelper*/

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        nestedScrollView = findViewById<View>(R.id.nestedScrollView) as NestedScrollView

        textInputLayoutName = findViewById<View>(R.id.textInputLayoutName) as TextInputLayout
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as TextInputLayout
        textInputLayoutMobile = findViewById<View>(R.id.textInputLayoutmobile) as TextInputLayout
        textInputLayoutaddress = findViewById<View>(R.id.textInputLayoutAddress) as TextInputLayout
        textInputLayoutPassword = findViewById<View>(R.id.textInputLayoutPassword) as TextInputLayout
        textInputLayoutConfirmPassword = findViewById<View>(R.id.textInputLayoutConfirmPassword) as TextInputLayout

        textInputEditTextName = findViewById<View>(R.id.textInputEditTextName) as TextInputEditText
        textInputEditTextEmail = findViewById<View>(R.id.textInputEditTextEmail) as TextInputEditText
        textInputEditTextMobile = findViewById<View>(R.id.textInputEditTextMobile) as TextInputEditText
        textInputEditTextaddress = findViewById<View>(R.id.textInputEditTextAddress) as TextInputEditText
        textInputEditTextPassword = findViewById<View>(R.id.textInputEditTextPassword) as TextInputEditText
        textInputEditTextConfirmPassword = findViewById<View>(R.id.textInputEditTextConfirmPassword) as TextInputEditText

        appCompatButtonRegister = findViewById<View>(R.id.appCompatButtonRegister) as AppCompatButton

        appCompatTextViewLoginLink = findViewById<View>(R.id.appCompatTextViewLoginLink) as AppCompatTextView

        progressDialog.setMessage("Registering User....")
        progressDialog.show()

    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        appCompatButtonRegister!!.setOnClickListener(this)
        appCompatTextViewLoginLink!!.setOnClickListener(this)
    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        inputValidation = inputValidation(activity)
        databaseHelper = UserDatabase(activity)


    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {

            R.id.appCompatButtonRegister -> postDataToSQLite()

            R.id.appCompatTextViewLoginLink -> finish()
        }
    }


    /**
     * This method is to validate the input text fields and post data to SQLite
     */
    private fun postDataToSQLite() {
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextMobile, textInputLayoutMobile, getString(R.string.error_message_mobile))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextaddress, textInputLayoutaddress, getString(R.string.error_message_address))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation!!.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                        textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return
        }

        if (!databaseHelper!!.checkUser(textInputEditTextEmail!!.text.toString().trim())) {

            var user = User(name = textInputEditTextName!!.text.toString().trim(),
                    email = textInputEditTextEmail!!.text.toString().trim(),
                    mobile = textInputEditTextMobile!!.text.toString().trim(),
                    address = textInputEditTextaddress!!.text.toString().trim(),
                    password = textInputEditTextPassword!!.text.toString().trim())

            databaseHelper!!.addUser(user)

            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()


        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }


    }


    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextName!!.text = null
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
        textInputEditTextConfirmPassword!!.text = null
    }



}

