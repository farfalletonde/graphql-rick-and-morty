package com.example.rickandmorty

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.launchActivity
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.rickandmorty.ui.MainActivity
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ActivityTest {

    @Test
    fun createActivity() {
        val activity = launchActivity<MainActivity>()
    }

    @Test
    fun activityChangeState() {
        val activity = launchActivity<MainActivity>()
        activity.moveToState(Lifecycle.State.CREATED)
    }

    @Test
    fun activityRecreate() {
        val activity = launchActivity<MainActivity>()
        activity.recreate()
    }
}