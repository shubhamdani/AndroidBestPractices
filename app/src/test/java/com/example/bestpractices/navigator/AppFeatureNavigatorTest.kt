package com.example.bestpractices.navigator

import android.content.Context
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.Test

class AppFeatureNavigatorTest {

    private val featureNavigator: AppFeatureNavigator = AppFeatureNavigator()
    private val mockContext = mockk<Context>()

    @Test
    fun `navigate to dashboard screen when navigateToDashboardScreen is called`() {
        every { mockContext.startActivity(any()) } returns Unit
        featureNavigator.navigateToDashboardScreen(mockContext)
        verify { mockContext.startActivity(any()) }
    }
}