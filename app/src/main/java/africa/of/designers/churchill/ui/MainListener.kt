package africa.of.designers.churchill.ui

import africa.of.designers.churchill.data.model.Lesson

interface MainListener {
    fun onGoToLessonDetail(data: Lesson)
    fun onGoToPractice()
    fun onGoToComingSoon()
}
