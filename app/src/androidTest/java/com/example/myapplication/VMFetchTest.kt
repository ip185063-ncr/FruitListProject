package com.example.myapplication

import android.content.Context
import com.example.myapplication.data.model.Fruit
import com.example.myapplication.data.repository.FruitRepository
import com.example.myapplication.ui.viewmodel.FruitViewModel
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

class VMFetchTest {
    @Test
    fun testFetchFruits() {
        val context = mock<Context>()
        val repository = mock<FruitRepository>()
        val fakeFruits = listOf(Fruit("apple", "url"),)

        whenever(repository.getFruits(any())).thenReturn(fakeFruits)

        val viewModel = FruitViewModel(context, repository)
        viewModel.fetchFruits()
        Thread.sleep(100)

        assertEquals(fakeFruits, viewModel.fruits)
    }
}