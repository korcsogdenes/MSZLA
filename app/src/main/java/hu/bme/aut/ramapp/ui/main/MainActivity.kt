package hu.bme.aut.ramapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.testApiAll()
        viewModel.testDbInsert(1,2, "TesztRev1")
        viewModel.testDbInsert(2,3, "TesztRev2")
        viewModel.testDbInsert(3,4, "TesztRev3")
        viewModel.testDbGet(3)
    }

}