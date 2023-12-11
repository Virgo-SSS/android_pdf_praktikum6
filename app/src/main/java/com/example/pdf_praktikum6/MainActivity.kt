package com.example.pdf_praktikum6

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Html
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.pdf_praktikum6.adapter.ImageAdapter
import com.example.pdf_praktikum6.databinding.ActivityMainBinding
import com.example.pdf_praktikum6.model.ImageDataModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: ImageAdapter
    private val listGambar = ArrayList<ImageDataModel>()
    private lateinit var dots: ArrayList<TextView>
    private val slideHandler = Handler()
    private val slideRun = Runnable {
        binding.viewPager.currentItem = binding.viewPager.currentItem + 1
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageLinks: Array<String> = arrayOf(
            "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
            "https://images.unsplash.com/photo-1682687981603-ae874bf432f2?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1682695797873-aa4cb6edd613?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDF8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://plus.unsplash.com/premium_photo-1661326416666-f8dff6c5bbe4?q=80&w=2070&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D",
            "https://images.unsplash.com/photo-1682687980976-fec0915c6177?w=500&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1yZWxhdGVkfDR8fHxlbnwwfHx8fHw%3D"
        )

        for (i in imageLinks.indices) {
            listGambar.add(ImageDataModel(imageLinks[i]))
        }

        adapter = ImageAdapter(listGambar)
        binding.viewPager.adapter = adapter
        dots = ArrayList()
        setIndicator()

        binding.viewPager.registerOnPageChangeCallback(object: androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                selectDot(position)
                super.onPageSelected(position)

                slideHandler.removeCallbacks(slideRun)
                slideHandler.postDelayed(slideRun, 3000)
            }
        })
    }

    private fun selectDot(position: Int) {
        for (i in 0 until adapter.itemCount) {
            if(i == position)
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_on_primary))
            else
                dots[i].setTextColor(ContextCompat.getColor(this, com.google.android.material.R.color.design_default_color_on_secondary))
        }
    }

    private fun setIndicator() {
        for (i in 0 until adapter.itemCount) {
            dots.add(TextView(this))
            dots[i].text = Html.fromHtml("&#9679", Html.FROM_HTML_MODE_LEGACY).toString()
            dots[i].textSize = 18f
            binding.dotsIndicator.addView(dots[i])
        }
    }
}