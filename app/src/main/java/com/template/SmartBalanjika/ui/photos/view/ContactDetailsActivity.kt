package com.template.SmartBalanjika.ui.photos.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.template.SmartBalanjika.databinding.PersonalDetailsBinding

class ContactDetailsActivity : AppCompatActivity() {
    private lateinit var binding: PersonalDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PersonalDetailsBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val profileName=intent.getStringExtra("password")
    }
}