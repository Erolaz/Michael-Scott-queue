package org.example

import org.jetbrains.kotlinx.lincheck.annotations.*
import org.jetbrains.kotlinx.lincheck.check
import org.jetbrains.kotlinx.lincheck.strategy.stress.StressOptions
import org.jetbrains.kotlinx.lincheck.strategy.managed.modelchecking.ModelCheckingOptions
import kotlin.test.Test
import java.util.*

class MSQueueTest2 {
    private val q = MichaelScottQueue<Int>()

    @Operation
    fun enqueue() = q.enqueue(1)

    @Operation
    fun dequeue() = q.dequeue()

    @Test
    fun stressOptionsOneThread() =
        StressOptions()
            .threads(1)
            .check(this::class)

    @Test
    fun stressOptionsMultiThread() =
        StressOptions()
            .threads(4)
            .iterations(10)
            .check(this::class)

}
