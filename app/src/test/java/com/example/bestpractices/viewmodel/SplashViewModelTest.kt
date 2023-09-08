package com.example.bestpractices.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.test_utils.MainDispatcherRule
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@ExperimentalCoroutinesApi
class SplashViewModelTest {

    private val testDelay = 3000L
    private val mockkObserver = mockk<Observer<SplashCommand>>() {
        every { onChanged(any()) } answers {}
    }
    lateinit var viewModelInTest: SplashViewModel

    @get:Rule
    val rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule: MainDispatcherRule =
        MainDispatcherRule()

    private fun setup() {
        viewModelInTest = SplashViewModel()
        viewModelInTest.observableCommand.observeForever(mockkObserver)
    }

    @Test
    fun `should send the dashboard command after delay`() = runTest {
        setup()
        delay(testDelay)
        verify { mockkObserver.onChanged(SplashCommand.NavigateToDashboard) }
    }
}
