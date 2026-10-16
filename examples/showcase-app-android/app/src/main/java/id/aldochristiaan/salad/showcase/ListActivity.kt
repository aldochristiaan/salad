package id.aldochristiaan.salad.showcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListActivity : AppCompatActivity() {

    private val items = mutableListOf<String>()
    private lateinit var adapter: LabelAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        items.addAll((1..30).map { getString(R.string.list_item_format, it) })

        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = LabelAdapter(
            items,
            onClick = { label ->
                Toast.makeText(this, getString(R.string.list_item_clicked, label), Toast.LENGTH_SHORT).show()
            },
            onLongClick = { label ->
                Toast.makeText(this, getString(R.string.list_item_long_clicked, label), Toast.LENGTH_LONG).show()
                true
            }
        )
        recyclerView.adapter = adapter

        findViewById<Button>(R.id.btn_add_item).setOnClickListener {
            items.add(getString(R.string.list_item_format, items.size + 1))
            adapter.notifyItemInserted(items.size - 1)
        }
    }

    class LabelAdapter(
        private val data: List<String>,
        private val onClick: (String) -> Unit,
        private val onLongClick: (String) -> Boolean
    ) : RecyclerView.Adapter<LabelAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val label: TextView = view.findViewById(R.id.item_label)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_row, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val label = data[position]
            holder.label.text = label
            holder.itemView.setOnClickListener { onClick(label) }
            holder.itemView.setOnLongClickListener { onLongClick(label) }
        }

        override fun getItemCount(): Int = data.size
    }
}
