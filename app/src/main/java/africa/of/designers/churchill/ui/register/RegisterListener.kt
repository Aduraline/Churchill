package africa.of.designers.churchill.ui.register

interface RegisterListener {
    fun onStarted()
    fun onSuccess()
    fun onError(message : String, isProcessing: Boolean)
    fun onGotoLogin()
}
