package com.jhonprieto.melifinder

import android.util.Log
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import tools.fastlane.screengrab.Screengrab
import tools.fastlane.screengrab.locale.LocaleTestRule

@RunWith(AndroidJUnit4::class)
class ScreenshotTest {
    /**
     * This test captures a screenshot of the main screen of the app.
     * Make sure to run this test on a device or emulator with a suitable screen size.
     */
    @Rule @JvmField
    val localeTestRule = LocaleTestRule()

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun captureScreenshot() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)
        scenario.onActivity {
            Log.d("ScreenshotTest", "🚀 Test iniciado")
            Thread.sleep(2000)
            Screengrab.screenshot("main_screen")
            Log.d("ScreenshotTest", "✅ Screenshot capturado")
        }
    }
}
