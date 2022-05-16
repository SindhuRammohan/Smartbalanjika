package com.template.SmartBalanjika.ui.photos.view

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.template.SmartBalanjika.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.template.SmartBalanjika.R
@AndroidEntryPoint
class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val navView: BottomNavigationView = binding.bottomNavigation
        loadFragment(PhotosFragment())
        navView.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.homed-> {
                    loadFragment(PhotosFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.info-> {
                    loadFragment(PdfFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.contactus-> {
                    loadFragment(AboutFragment())
                    return@setOnNavigationItemSelectedListener true
                }

            }
            false

        }
    }
    private fun loadFragment(fragment: Fragment) {
        // load fragment
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.nav_fragment, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}


