package id.aldochristiaan.salad.showcase

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class ViewPagerActivity : AppCompatActivity() {

    private val pageCount = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_viewpager)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener { finish() }

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        viewPager.adapter = PageAdapter(
            pageCount,
            titleFor = { pageNumber -> getString(R.string.viewpager_page_format, pageNumber) },
            contentFor = { pageNumber -> getString(R.string.viewpager_content_format, pageNumber) }
        )

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = getString(R.string.viewpager_page_format, position + 1)
        }.attach()
    }

    class PageAdapter(
        private val pageCount: Int,
        private val titleFor: (Int) -> String,
        private val contentFor: (Int) -> String
    ) : RecyclerView.Adapter<PageAdapter.ViewHolder>() {

        class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
            val title: TextView = view.findViewById(R.id.page_title)
            val content: TextView = view.findViewById(R.id.page_content)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_page, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val pageNumber = position + 1
            holder.title.text = titleFor(pageNumber)
            holder.content.text = contentFor(pageNumber)
        }

        override fun getItemCount(): Int = pageCount
    }
}
