package id.aldochristiaan.salad.showcase

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class ToastActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_toasts)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val status = findViewById<TextView>(R.id.toast_status)

        findViewById<Button>(R.id.btn_show_short_toast).setOnClickListener {
            status.text = getString(R.string.toast_short_text)
            Toast.makeText(this, R.string.toast_short_text, Toast.LENGTH_SHORT).show()
        }

        findViewById<Button>(R.id.btn_show_long_toast).setOnClickListener {
            status.text = getString(R.string.toast_long_text)
            Toast.makeText(this, R.string.toast_long_text, Toast.LENGTH_LONG).show()
        }

        findViewById<Button>(R.id.btn_show_error_toast).setOnClickListener {
            status.text = getString(R.string.toast_error_text)
            Toast.makeText(this, R.string.toast_error_text, Toast.LENGTH_LONG).show()
        }
    }
}
