package com.example.mad03_fragments_and_navigation.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mad03_fragments_and_navigation.models.Question
import com.example.mad03_fragments_and_navigation.models.QuestionCatalogue

class QuizViewModel : ViewModel() {
    private var questions: List<Question> = QuestionCatalogue().defaultQuestions.shuffled()
    val questionActive = MutableLiveData<Question>()
    val index =  MutableLiveData<Int>()
    val questionsCount = MutableLiveData<Int>()
    val score = MutableLiveData<Int>()
    val endQuiz= MutableLiveData<Boolean>()

    init { //initialize
        index.value = 0
        questionsCount.value = questions.size
        score.value = 0
        questionActive.value = questions[index.value!!]
        endQuiz.value = false
    }
    fun viewModelNextQuestion(selectedAnswer: Int){
        if (index.value!! < questionsCount.value!!.minus(1)){
            if (correctAnswer(selectedAnswer)){
                score.value = score.value?.plus(1)
                switchToNextQuestion()
            }else{
                switchToNextQuestion()
            }
        }else {
            if (selectedAnswer.let { correctAnswer(it) }){
                score.value = score.value?.plus(1)
            }
            quizEnd()
        }
    }
    private fun correctAnswer(id: Int): Boolean {
        return questions[index.value!!].answers[id].isCorrectAnswer
    }
    private fun switchToNextQuestion() {
        index.value = (index.value)?.plus(1)
        questionActive.value = questions[index.value!!]
    }
    private fun quizEnd(): MutableLiveData<Int>{
        endQuiz.value = true
        return score
    }
    override fun onCleared() {
        super.onCleared()
        Log.i("QuizViewModel", "I am on OnCleared")
    }
}

