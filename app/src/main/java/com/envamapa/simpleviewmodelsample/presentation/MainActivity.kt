package com.envamapa.simpleviewmodelsample.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import com.envamapa.simpleviewmodelsample.R
import com.envamapa.simpleviewmodelsample.SampleApplication
import com.envamapa.simpleviewmodelsample.databinding.ActivityMainBinding
import com.envamapa.simpleviewmodelsample.di.HasModule

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding

    private lateinit var viewModel: ViewModelA
    private lateinit var viewModelFactory: ViewModelAFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        setUpButtons()
        initViewModel()
        observeText()

        val navController = findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) ||
            super.onSupportNavigateUp()
    }

    private fun initViewModel() {
        println("MainActivity: viewModel initialized")
        viewModelFactory = ViewModelAFactory(application = application as SampleApplication)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ViewModelA::class.java)
    }

    private fun setUpButtons() {
        binding.buttonSubmit.setOnClickListener {
            println("MainActivity: execute viewModel.submitSomething()")
            viewModel.submitSomething("bla bla")
        }
        binding.buttonRequest.setOnClickListener {
            println("MainActivity: execute viewModel.getInformation()")
            viewModel.getInformation()
        }
        binding.buttonTrack.setOnClickListener {
            println("MainActivity: execute viewModel.track()")
            Toast.makeText(baseContext, "Tracking", Toast.LENGTH_SHORT).show()
            viewModel.track()
        }
    }

    private fun observeText() {
        viewModel.state.observe(
            this,
            Observer<String> { text ->
                println("MainActivity: text received -> $text")
                binding.textviewData.text = text
            }
        )
    }

    class ViewModelAFactory(
        private val application: SampleApplication
    ) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ViewModelA::class.java)) {
                return (application as HasModule).provideViewModel() as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
    }
}
