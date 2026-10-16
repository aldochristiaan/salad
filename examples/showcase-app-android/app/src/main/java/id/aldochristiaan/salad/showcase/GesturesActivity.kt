package id.aldochristiaan.salad.showcase

import android.os.Bundle
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class GesturesActivity : AppCompatActivity() {

    private var scaleFactor = 1f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gestures)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        // Pinch-to-zoom image
        val zoomImage = findViewById<ImageView>(R.id.zoom_image)
        val scaleDetector = ScaleGestureDetector(this,
            object : ScaleGestureDetector.SimpleOnScaleGestureListener() {
                override fun onScale(detector: ScaleGestureDetector): Boolean {
                    scaleFactor *= detector.scaleFactor
                    scaleFactor = scaleFactor.coerceIn(0.5f, 4f)
                    zoomImage.scaleX = scaleFactor
                    zoomImage.scaleY = scaleFactor
                    return true
                }
            })
        zoomImage.setOnTouchListener { _, event ->
            scaleDetector.onTouchEvent(event)
            true
        }

        // Long press button
        findViewById<Button>(R.id.btn_long_press).setOnLongClickListener {
            Toast.makeText(this, R.string.gestures_long_pressed, Toast.LENGTH_SHORT).show()
            true
        }

        // Fling-able list (50 rows)
        val recyclerView = findViewById<RecyclerView>(R.id.fling_recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = ListActivity.LabelAdapter(
            (1..50).map { getString(R.string.list_item_format, it) },
            onClick = { },
            onLongClick = { false }
        )
    }
}
