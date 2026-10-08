package com.llmhub.llmhub.data

/** Worker count, not CPU affinity. Keep legacy Auto behavior for old configurations. */
object CpuThreadSettings {
    fun resolve(requested: Int, availableProcessors: Int): Int {
        val available = availableProcessors.coerceAtLeast(1)
        return if (requested <= 0) {
            (available - 2).coerceIn(2, 8).coerceAtMost(available)
        } else {
            requested.coerceIn(1, minOf(available, 8))
        }
    }
}
