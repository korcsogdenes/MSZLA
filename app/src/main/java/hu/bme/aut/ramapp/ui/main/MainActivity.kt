package hu.bme.aut.ramapp.ui.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import com.google.firebase.analytics.FirebaseAnalytics
import dagger.hilt.android.AndroidEntryPoint
import hu.bme.aut.ramapp.ui.theme.ListTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()
    lateinit var mFirebaseAnalytics: FirebaseAnalytics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        mFirebaseAnalytics.logEvent(FirebaseAnalytics.Event.APP_OPEN, null)

        //viewModel.testApiAll()
        //viewModel.testDbInsert(1,2, 1, "TesztRev1")
        //viewModel.testDbInsert(2,3, 2, "TesztRev2")
        //viewModel.testDbInsert(3,4, 3, "TesztRev3")
        //viewModel.testDbGet(3)

        setContent {
            ListTheme (
                content = {
                    Surface{
                        NavFun(mFirebaseAnalytics)
                    }
                }
            )
        }
    }

}