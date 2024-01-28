package com.borwe.counter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import com.borwe.counter.domain.DecrementUseCase
import com.borwe.counter.domain.IncrementUseCase
import com.borwe.counter.ui.theme.CounterTheme

class MainActivity : ComponentActivity() {
    lateinit var viewModel: CounterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = CounterViewModel.create(this , IncrementUseCase(), DecrementUseCase())
        setContent {
            CounterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    Counter(viewModel)
                }
            }
        }
    }
}

@Composable
fun Counter(viewModel: CounterViewModel) {
    val count = viewModel.counterValue.collectAsState()
    Column {
        Text("Press a button, see what happens")
        Spacer(modifier = Modifier.height(10.dp))

        Row {
            Text(text = "${count.value}")
        }
        Row {
            Button(onClick = {
                viewModel.increment.invoke(viewModel)
            }){
                Text("ADD")
            }
            Button(onClick = {
                viewModel.decrement.invoke(viewModel)
            }){
                Text("MINUS")
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    CompositionLocalProvider {
        CounterTheme {
            Counter(viewModel = CounterViewModel.create(LocalContext.current as LifecycleOwner,IncrementUseCase(), DecrementUseCase()) )
        }
    }
}