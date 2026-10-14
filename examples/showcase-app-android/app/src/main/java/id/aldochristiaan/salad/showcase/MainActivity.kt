package id.aldochristiaan.salad.showcase

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView

class MainActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar,
            R.string.open_navigation_drawer,
            R.string.open_navigation_drawer
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        handleDeeplink(intent)

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener {
            Toast.makeText(this, R.string.fab_clicked, Toast.LENGTH_SHORT).show()
        }

        findViewById<android.widget.Button>(R.id.btn_open_list).setOnClickListener {
            startActivity(Intent(this, ListActivity::class.java))
        }
        findViewById<android.widget.Button>(R.id.btn_open_forms).setOnClickListener {
            startActivity(Intent(this, FormsActivity::class.java))
        }
        findViewById<android.widget.Button>(R.id.btn_open_toasts).setOnClickListener {
            startActivity(Intent(this, ToastActivity::class.java))
        }
        findViewById<android.widget.Button>(R.id.btn_open_gestures).setOnClickListener {
            startActivity(Intent(this, GesturesActivity::class.java))
        }
        findViewById<android.widget.Button>(R.id.btn_open_viewpager).setOnClickListener {
            startActivity(Intent(this, ViewPagerActivity::class.java))
        }
        findViewById<android.widget.Button>(R.id.btn_open_webview).setOnClickListener {
            startActivity(Intent(this, WebViewActivity::class.java))
        }

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> drawerLayout.closeDrawer(GravityCompat.START)
                R.id.nav_list -> startActivity(Intent(this, ListActivity::class.java))
                R.id.nav_forms -> startActivity(Intent(this, FormsActivity::class.java))
                R.id.nav_toasts -> startActivity(Intent(this, ToastActivity::class.java))
                R.id.nav_gestures -> startActivity(Intent(this, GesturesActivity::class.java))
                R.id.nav_viewpager -> startActivity(Intent(this, ViewPagerActivity::class.java))
                R.id.nav_webview -> startActivity(Intent(this, WebViewActivity::class.java))
            }
            true
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        handleDeeplink(intent)
    }

    private fun handleDeeplink(intent: Intent?) {
        val data: Uri? = intent?.data
        if (data != null && data.scheme == "saladshowcase" && data.host == "main") {
            Toast.makeText(this, "Deeplink: ${data.path}", Toast.LENGTH_LONG).show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_settings -> {
                Toast.makeText(this, R.string.settings_clicked, Toast.LENGTH_SHORT).show()
                true
            }
            R.id.menu_about -> {
                Toast.makeText(this, getString(R.string.app_name), Toast.LENGTH_SHORT).show()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
