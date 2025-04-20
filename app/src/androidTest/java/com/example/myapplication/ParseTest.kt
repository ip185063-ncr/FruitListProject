package com.example.myapplication

import com.example.myapplication.ui.viewmodel.FruitViewModel
import org.junit.Test

class ParseFruitsTest {
    @Test
    fun testParseFruits() {
        val jsonResponse = """{
              "fruits": {
                "apple": {
                  "url": "http://images.unsplash.com/photo-1678942946279-c83e37f32304?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YXBwbGV8ZW58MHwyfDB8fHww"
                },
                "kiwi": {
                  "url": "http://images.unsplash.com/photo-1573066778058-03dbee2c5a0c?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8a2l3aXxlbnwwfDJ8MHx8fDA%3D"
                },
                "mango": {
                  "url": "http://images.unsplash.com/photo-1587486936739-78df7470c7e0?q=80&w=3478&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D"
                },
                "coconut": {
                  "url": "http://images.unsplash.com/photo-1554444510-592779e6e009?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8Y29jb251dHxlbnwwfDJ8MHx8fDA%3D"
                },
                "plum": {
                  "url": "http://images.unsplash.com/photo-1587486913042-5d9362101999?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8OHx8cGx1bXxlbnwwfDJ8MHx8fDA%3D"
                },
                "banana": {
                  "url": "http://images.unsplash.com/photo-1587132137056-bfbf0166836e?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8Mnx8YmFuYW5hfGVufDB8MnwwfHx8MA%3D%3D"
                },
                "guava": {
                  "url": "http://images.unsplash.com/photo-1666613777035-3c2e1ea03b66?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8Z3VhdmF8ZW58MHwyfDB8fHww"
                }
              },
              "list": [
                "apple",
                "kiwi",
                "banana",
                "mango",
                "mango",
                "banana",
                "coconut",
                "apple",
                "plum",
                "banana",
                "guava"
              ]
            }
                """
        val fruits = FruitViewModel.parseFruits(jsonResponse)
        assert(fruits.size == 11)
        assert(fruits[4].name == "apple")
        assert(fruits[4].imgURL == "http://images.unsplash.com/photo-1678942946279-c83e37f32304?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YXBwbGV8ZW58MHwyfDB8fHww)")

    }


    @Test
    fun testNullList(){
        val jsonResponse = "{\"fruits\": {\n" +
                "\"apple\": {\"url\": \"http://images.unsplash.com/photo-1678942946279-c83e37f32304?w=900&auto=format&fit=crop&q=60&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxzZWFyY2h8M3x8YXBwbGV8ZW58MHwyfDB8fHww}," +
                "\"list\": null}"
        val fruits = FruitViewModel.parseFruits(jsonResponse)
        assert(fruits.isEmpty())
    }
}