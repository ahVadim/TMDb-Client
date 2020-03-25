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
                Then("index of a should be 1") {
                    assertThat(sutList.indexOf(a)).isEqualTo(1)
                }
                And("index of b should be 0") {
                    assertThat(sutList.indexOf(b)).isEqualTo(0)
                }
                And("index of c should be 2") {
                    assertThat(sutList.indexOf(c)).isEqualTo(2)
                }
                And("size of list should be same (3)") {
                    assertThat(sutList.indexOf(c)).isEqualTo(2)
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

