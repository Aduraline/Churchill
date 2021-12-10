package africa.of.designers.churchill.ui.login

interface LoginListener {
    fun onStarted()
    fun onSuccess()
    fun onError(message : String, isProcessing: Boolean)
    fun onGotoRegister()
}
