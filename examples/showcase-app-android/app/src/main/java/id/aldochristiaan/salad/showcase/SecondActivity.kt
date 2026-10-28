package id.aldochristiaan.salad.showcase

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        handleDeeplink(intent)

        findViewById<Button>(R.id.btn_back_to_main).setOnClickListener { finish() }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeeplink(intent)
    }

    private fun handleDeeplink(intent: android.content.Intent?) {
        val data = intent?.data
        if (data != null && data.scheme == "saladshowcase" && data.host == "second") {
            val result = findViewById<TextView>(R.id.second_deeplink_result)
            result.text = getString(R.string.second_deeplink_result, data.path ?: "/")
            result.visibility = View.VISIBLE
        }
    }
}
