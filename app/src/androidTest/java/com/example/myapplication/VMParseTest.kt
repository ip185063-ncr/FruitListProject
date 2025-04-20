package com.example.myapplication

import com.example.myapplication.ui.viewmodel.FruitViewModel
import junit.framework.TestCase
import org.junit.Test

class VMParseTest {

    @Test
    fun testFetchFruits() {
//        val viewModel = FruitViewModel(mockContext, mockRepository)
        val json = """
            "list": ["apple", "banana"],
            "fruits": {
                "apple": {"url": "http://images.unsplash.com/photo-1678942946279-c83e37f32304?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YXBwbGV8ZW58MHwyfDB8fHww"},
                "banana": {"url": "http://images.unsplash.com/photo-1587132137056-bfbf0166836e?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YmFuYW5hfGVufDB8MnwwfHx8MA%3D%3D"}
             }
        """.trimIndent()

        val result = FruitViewModel.Companion.parseFruits(json)
        TestCase.assertEquals(2, result.size)
        TestCase.assertEquals("apple", result[0].name)
        TestCase.assertEquals(
            "http://images.unsplash.com/photo-1678942946279-c83e37f32304?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YXBwbGV8ZW58MHwyfDB8fHww",
            result[0].imgURL
        )
    }
}