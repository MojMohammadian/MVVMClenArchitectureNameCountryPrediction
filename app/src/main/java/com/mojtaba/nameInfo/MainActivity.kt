package com.mojtaba.nameInfo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mojtaba.nameInfo.feature_name_info.presentation.NameInfoItem
import com.mojtaba.nameInfo.feature_name_info.presentation.NameInfoViewModel
import com.mojtaba.nameInfo.ui.theme.NameCountryPredictionTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NameCountryPredictionTheme {

                val viewModel: NameInfoViewModel = hiltViewModel()
                val state = viewModel.state.value
                val scaffoldState = rememberScaffoldState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {

                    LaunchedEffect(key1 = true) {
                        viewModel.eventFlow.collectLatest { event ->
                            when (event) {
                                is NameInfoViewModel.UIEvent.ShowSnackbar -> {
                                    scaffoldState.snackbarHostState.showSnackbar(
                                        event.message
                                    )
                                }
                            }
                        }
                    }
                    Scaffold(
                        scaffoldState = scaffoldState
                    ) {
                        Box(
                            modifier = Modifier
                                .background(MaterialTheme.colors.background)
                        )
                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(16.dp)
                        ) {
                            TextField(
                                value = viewModel.searchQuery.value,
                                onValueChange = viewModel::onSearch,
                                modifier = Modifier
                                    .fillMaxWidth(),
                                placeholder = {
                                    Text(text = "Search...")
                                }
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            if (state.nameInfoItem != null) {
                                LazyColumn(modifier = Modifier.fillMaxSize()) {
                                    items(state.nameInfoItem.countries.size) { i ->
                                        val nameInfo = state.nameInfoItem.countries[i]
                                        if (i > 0) {
                                            Spacer(modifier = Modifier.height(8.dp))
                                        }
                                        NameInfoItem(nameInfo = nameInfo)
                                        if (i < state.nameInfoItem.countries.size - 1){
                                            Divider()
                                        }

                                    }
                                }
                            }
                            if (state.isLoading){
                                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
                            }
                        }
                    }
                }
            }
        }
    }
}
