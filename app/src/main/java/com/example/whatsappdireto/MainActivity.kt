package com.example.whatsappdireto

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.whatsappdireto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var navController : NavController
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        //inicializar navegação Menu Bottom
        navController = Navigation.findNavController(this, R.id.fragmentContainerView)
        setupWithNavController(binding.bottomNavigation,navController)

        configToolbar()






    }

    private fun configToolbar() {

        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        if (Build.VERSION.SDK_INT >= 24) {
            supportActionBar?.title = Html.fromHtml("<b>ZapDireto</b>", 0)
        } else {
            supportActionBar?.title = Html.fromHtml("<b>ZapDireto</b>")
        }
    }
}