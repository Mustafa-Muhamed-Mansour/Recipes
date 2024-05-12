package com.recipes.app.activities.xml

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.recipes.R
import com.recipes.databinding.ActivityXmlBinding

class XMLActivity : AppCompatActivity() {

    private lateinit var _binding: ActivityXmlBinding
    private val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = DataBindingUtil.setContentView(this, R.layout.activity_xml)
    }
}