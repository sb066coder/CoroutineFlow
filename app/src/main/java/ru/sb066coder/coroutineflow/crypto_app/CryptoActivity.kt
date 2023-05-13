package ru.sb066coder.coroutineflow.crypto_app

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import ru.sb066coder.coroutineflow.databinding.ActivityCryptoBinding

class CryptoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityCryptoBinding.inflate(layoutInflater)
    }

    private val viewModel by lazy {
        ViewModelProvider(this)[CryptoViewModel::class.java]
    }

    private val adapter = CryptoAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerView()
        observeViewModel()
        binding.btnRefresh.setOnClickListener {
            binding.progressBarLoading.isVisible = true
            viewModel.refreshList()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerViewCurrencyPriceList.adapter = adapter
        binding.recyclerViewCurrencyPriceList.itemAnimator = null
    }

    private fun observeViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) { // OR .flowWithLifecycle(lifecycle, Lifecycle.State.RESUMED)
                viewModel.state.collect {
                    when (it) {
                        is State.Initial -> {
                            binding.progressBarLoading.isVisible = false
                        }
                        is State.Loading -> {
                            binding.progressBarLoading.isVisible = true
                        }
                        is State.Content -> {
                            binding.progressBarLoading.isVisible = false
                            adapter.submitList(it.currencyList)
                        }
                    }
                }
            }
        }
    }

    companion object {

        fun newIntent(context: Context) = Intent(context, CryptoActivity::class.java)
    }
}