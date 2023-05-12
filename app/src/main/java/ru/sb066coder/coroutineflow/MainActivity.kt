package ru.sb066coder.coroutineflow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ru.sb066coder.coroutineflow.databinding.ActivityMainBinding
import ru.sb066coder.coroutineflow.lesson2.UsersActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.buttonUsersActivity.setOnClickListener {
            startActivity(UsersActivity.newIntent(this))
        }
    }
}