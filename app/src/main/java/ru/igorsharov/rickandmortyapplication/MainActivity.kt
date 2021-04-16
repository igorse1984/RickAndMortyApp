package ru.igorsharov.rickandmortyapplication

import android.os.Bundle
import android.view.MenuItem
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.igorsharov.rickandmortyapplication.core.platform.ActionBarActivity
import ru.igorsharov.rickandmortyapplication.core.platform.BottomNavigationActivity
import ru.igorsharov.rickandmortyapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), BottomNavigationActivity, ActionBarActivity {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNavView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.Theme_RickAndMortyApplication)
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        bottomNavView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.locations,
                R.id.characters,
                R.id.episodes
            )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        bottomNavView.setupWithNavController(navController)
    }

    override fun setActionBarTitle(@StringRes stringResId: Int) {
        supportActionBar?.title = resources.getString(stringResId)
    }

    override fun setBottomNavigationVisibility(visibility: Int) {
        bottomNavView.visibility = visibility
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
            return true
        }

        return super.onOptionsItemSelected(item)
    }
}