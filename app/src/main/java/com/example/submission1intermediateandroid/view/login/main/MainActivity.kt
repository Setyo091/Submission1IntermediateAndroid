package com.example.submission1intermediateandroid.view.login.main


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.submission1intermediateandroid.LoadingStateAdapter
import com.example.submission1intermediateandroid.R
import com.example.submission1intermediateandroid.data.Result
import com.example.submission1intermediateandroid.databinding.ActivityMainBinding
import com.example.submission1intermediateandroid.data.ViewModelFactory
import com.example.submission1intermediateandroid.view.login.maps.MapsActivity
import com.example.submission1intermediateandroid.view.login.addstory.AddStoryActivity
import com.example.submission1intermediateandroid.view.login.welcome.WelcomeActivity

class MainActivity : AppCompatActivity() {
    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }
    private lateinit var binding: ActivityMainBinding

    private var storyAdapter = UserAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.getSession().observe(this) {
        }

        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM

        itemDecoration()
        setUpAction()

        binding.rvMain.adapter = storyAdapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                storyAdapter.retry()
            })

        viewModel.getSession().observe(this) { user ->
            Log.d("token", "onCreate: ${user.token}")
            Log.d("user", "onCreate: ${user.isLogin}")
            if (!user.isLogin) {
                startActivity(Intent(this, WelcomeActivity::class.java))
                finish()
            }
        }

        viewModel.story.observe(this) { story ->
            storyAdapter.submitData(lifecycle, story)
        }
        binding.topAppBar.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.upload_story -> {
                    val intent = Intent(this, AddStoryActivity::class.java)
                    startActivity(intent)
                    true
                }
                R.id.change_language -> {
                    startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
                    true
                }
                R.id.tombol_logout -> {
                    viewModel.logout()
                    true
                }
                else -> false
            }
        }
    }
    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }

    private fun setUpAction() {
        binding.storyLocation.setOnClickListener {
            val intent = (Intent(this, MapsActivity::class.java))
            startActivity(intent)
        }
    }

    private fun itemDecoration() {
        val layoutManager = LinearLayoutManager(this)
        binding.rvMain.layoutManager = layoutManager

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvMain.addItemDecoration(itemDecoration)
    }
}


