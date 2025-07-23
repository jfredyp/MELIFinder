package com.jhonprieto.logger

object Logger {
    enum class LogLevel { DEBUG, INFO, WARN, ERROR }

    var enabled = true

    private val isAndroid: Boolean by lazy { isAndroidRuntime() }

    fun init(isDebug: Boolean) {
        enabled = isDebug
    }

    fun d(tag: String, message: String) = log(LogLevel.DEBUG, tag, message)
    fun i(tag: String, message: String) = log(LogLevel.INFO, tag, message)
    fun w(tag: String, message: String) = log(LogLevel.WARN, tag, message)
    fun e(tag: String, message: String, throwable: Throwable? = null) = log(LogLevel.ERROR, tag, message, throwable)

    fun log(level: LogLevel, tag: String, message: String, throwable: Throwable? = null) {
        if (!enabled) return

        if (isAndroid) {
            try {
                logAndroid(level, tag, message, throwable)
            } catch (ex: ClassNotFoundException) {
                println("Logger fallback: ClassNotFoundException -> ${ex.message}")
                logJVM(level, tag, message, throwable)
            } catch (ex: NoSuchMethodException) {
                println("Logger fallback: NoSuchMethodException -> ${ex.message}")
                logJVM(level, tag, message, throwable)
            } catch (ex: ReflectiveOperationException) {
                println("Logger fallback: ReflectiveOperationException -> ${ex.message}")
                logJVM(level, tag, message, throwable)
            }
        } else {
            logJVM(level, tag, message, throwable)
        }
    }

    private fun logAndroid(
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        val logClass = Class.forName("android.util.Log")
        val method = when (level) {
            LogLevel.DEBUG -> logClass.getMethod("d", String::class.java, String::class.java)
            LogLevel.INFO -> logClass.getMethod("i", String::class.java, String::class.java)
            LogLevel.WARN -> logClass.getMethod("w", String::class.java, String::class.java)
            LogLevel.ERROR -> {
                if (throwable != null) {
                    logClass.getMethod("e", String::class.java, String::class.java, Throwable::class.java)
                } else {
                    logClass.getMethod("e", String::class.java, String::class.java)
                }
            }
        }
        if (level == LogLevel.ERROR && throwable != null) {
            method.invoke(null, tag, message, throwable)
        } else {
            method.invoke(null, tag, message)
        }
    }

    private fun logJVM(
        level: LogLevel,
        tag: String,
        message: String,
        throwable: Throwable?
    ) {
        println("[${level.name}] $tag: $message" + (throwable?.let { "\n${it.stackTraceToString()}" } ?: ""))
    }

    private fun isAndroidRuntime(): Boolean {
        return try {
            Class.forName("android.os.Build")
            true
        } catch (e: ClassNotFoundException) {
            println("Logger fallback: ClassNotFoundException -> ${e.message}")
            false
        }
    }
}
