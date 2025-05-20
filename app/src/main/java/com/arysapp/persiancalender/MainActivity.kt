package com.arysapp.persiancalender

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.arysapp.persiandate.core.JalaliDate
import com.arysapp.persiandate.ui.presentation.HorizontalDatePicker
import com.arysapp.persiandate.ui.presentation.JalaliDatePickerDialog
import com.arysapp.persiandate.ui.theme.PersianDateTheme
import com.arysapp.persiandate.ui.theme.rememberDatePickerColors
import com.arysapp.persiandate.ui.theme.rememberHorizontalDatePickerColors

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PersianDateTheme {
                var selectedDate by remember { mutableStateOf(JalaliDate.today()) }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding).fillMaxSize(),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally

                    )
                    {
                        Text(
                            text = "تاریخ انتخاب شده:  $selectedDate",
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.Center,
                            modifier = Modifier.padding(16.dp).fillMaxWidth()
                        )

                        HorizontalDatePicker(
                            selectedDate = selectedDate,
                            onDateSelected = { selectedDate = it },
                            daysCount = 30,
                            shape = RoundedCornerShape(20.dp),
                            fontSize = 15.sp,
                            showTodayAtStart = false,
                            colors = rememberHorizontalDatePickerColors(),
                        )
                        var selectedDate by remember { mutableStateOf(JalaliDate.today()) }
                        var showDialog by remember { mutableStateOf(false) }

                        Column(
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(text = " تاریخ تولد: $selectedDate", fontSize = 18.sp)

                            Button(onClick = { showDialog = true }) {
                                Text("انتخاب تاریخ تولد")
                            }

                            if (showDialog) {
                                JalaliDatePickerDialog(
                                    initialDate = selectedDate,
                                    maxYear = 1404,
                                    minYear = 1400,
                                    minDate = JalaliDate(1400, 1, 1),
                                    maxDate = JalaliDate.today(),
                                    onDismissRequest = { showDialog = false },
                                    onDateSelected = { date ->
                                        selectedDate = date
                                        showDialog = false
                                    },
                                    colors = rememberDatePickerColors(),
                                    showTodayAtStart = false,
                                )
                            }
                        }
                    }


                }



                        }

                    }
                }
            }








