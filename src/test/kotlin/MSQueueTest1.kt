package org.example

import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import kotlin.test.Test
import java.util.*

class MSQueueTest1 {
    private val q = MichaelScottQueue<Int>()

    @Operation
    fun enqueue() = q.enqueue(1)

    @Operation
    fun dequeue() = q.dequeue()

    @Test
    fun stressTest() = StressOptions().check(this::class)
}
