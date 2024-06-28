package com.example.whatsappdireto.view

import android.content.res.Resources
import android.graphics.Color
import android.graphics.drawable.InsetDrawable
import android.os.Build
import android.os.Bundle
import android.text.Html
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI.setupWithNavController
import com.example.whatsappdireto.R
import com.example.whatsappdireto.controller.ContatoController
import com.example.whatsappdireto.controller.SharedController
import com.example.whatsappdireto.database.RoomDB
import com.example.whatsappdireto.databinding.ActivityMainBinding
import com.example.whatsappdireto.sharedPreferences.PreferencesManager

class MainActivity : AppCompatActivity() {


    private lateinit var navController : NavController

    private lateinit var sharedController: SharedController


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
        iniciarSharedPreferences()

    }

    private fun iniciarSharedPreferences() {
        val preferencesManager = PreferencesManager(this)

        // Inicialize o MainController com o PreferencesManager
        sharedController = SharedController(preferencesManager)

        // Inicialize o aplicativo (verifica a primeira execução)
        sharedController.initializeApp()
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

        // Carrega o drawable e cria um InsetDrawable com padding
        val originalDrawable = ContextCompat.getDrawable(this, R.drawable.spy_24)
        val insetDrawable = InsetDrawable(originalDrawable, 0, 0, 16, 0)

        supportActionBar?.setIcon(insetDrawable)

        fun Int.dpToPx(): Int {
            val density = Resources.getSystem().displayMetrics.density
            return (this * density).toInt()
        }
    }
}