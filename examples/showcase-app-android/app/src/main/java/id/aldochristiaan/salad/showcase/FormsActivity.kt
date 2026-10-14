package id.aldochristiaan.salad.showcase

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.google.android.material.checkbox.MaterialCheckBox
import com.google.android.material.materialswitch.MaterialSwitch
import com.google.android.material.textfield.TextInputEditText

class FormsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forms)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val inputName = findViewById<TextInputEditText>(R.id.input_name)
        val inputEmail = findViewById<TextInputEditText>(R.id.input_email)
        val checkboxTerms = findViewById<MaterialCheckBox>(R.id.checkbox_terms)
        val switchNotifications = findViewById<MaterialSwitch>(R.id.switch_notifications)
        val result = findViewById<TextView>(R.id.forms_result)

        findViewById<Button>(R.id.btn_submit).setOnClickListener {
            val name = inputName.text?.toString().orEmpty()
            val email = inputEmail.text?.toString().orEmpty()
            result.text = getString(R.string.forms_submitted, name, email)
            result.visibility = View.VISIBLE
            if (checkboxTerms.isChecked) {
                Toast.makeText(this, R.string.forms_terms_checked, Toast.LENGTH_SHORT).show()
            }
            // Hide keyboard so tests can continue without interference
            currentFocus?.clearFocus()
        }

        // Keep switch state observable for validateSelected-style checks
        switchNotifications.setOnCheckedChangeListener { _, isChecked ->
            result.text = if (isChecked) "Notifications enabled" else "Notifications disabled"
            result.visibility = View.VISIBLE
        }
    }
}
