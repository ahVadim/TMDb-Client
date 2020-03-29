package com.example.core.util

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.catchThrowable
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.gherkin.Feature

object CollectionExtTest : Spek(
    {
        Feature("swap") {

            Scenario("swap elements in non empty list") {
                val sutList by memoized { mutableListOf<String>() }
                val a = "a"
                val b = "b"
                val c = "c"
                Given("list of three elements: a, b, c") {
                    sutList.addAll(listOf(a, b, c))
                }
                When("swap a and b") {
                    sutList.swap(0, 1)
                }
                Then("list should be equal to: b, a, c") {
                    assertThat(sutList).isEqualTo(listOf(b, a, c))
                }
            }

            Scenario("swap elements in empty list") {
                val sutList = mutableListOf<String>()
                lateinit var exception: Throwable
                When("swap elements in empty list") {
                    exception = catchThrowable { sutList.swap(0, 1) }
                }
                Then("throw exception") {
                    assertThat(exception).isNotNull()
                }
            }
        }
    }
)

