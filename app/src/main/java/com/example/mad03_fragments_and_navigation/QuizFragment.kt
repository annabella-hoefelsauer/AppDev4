package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizBinding
import com.example.mad03_fragments_and_navigation.viewmodels.QuizViewModel


class QuizFragment : Fragment() {

    private lateinit var binding: FragmentQuizBinding
    private lateinit var viewModelQuiz: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false)
        viewModelQuiz = ViewModelProvider(this).get(QuizViewModel::class.java) //ViewModelProvider to declare Owner and get the ViewModelClass
        binding.viewModelQuiz = viewModelQuiz
        binding.lifecycleOwner = this
        binding.btnNext.setOnClickListener {
            nextQuestion()
        }
        endQuiz()
        return binding.root
    }
    private fun selectedId(): Int? {
        return when (binding.answerBox.checkedRadioButtonId) {
            R.id.answer1 -> 0
            R.id.answer2 -> 1
            R.id.answer3 -> 2
            R.id.answer4 -> 3
            else -> {
                Toast.makeText(requireContext(), "Please choose an answer!", Toast.LENGTH_LONG).show()
                null
            }
        }
    }
    private fun nextQuestion(){
        val chosenAnswer = selectedId();
        if (chosenAnswer != null) {
            viewModelQuiz.viewModelNextQuestion(chosenAnswer)
            binding.answerBox.clearCheck()    // clear the checked buttons for new question
        }
    }
    private fun endQuiz() {
        viewModelQuiz.endQuiz.observe(viewLifecycleOwner, Observer {//represents a class that has Android lifecycle, like Activity, Fragment or Service.
            if (it == true) {// "it" is used inside a lambda to refer to its parameter implicitly //Check if it's last question and it's true in the function in the ViewModel which has a boolean value.
                val switchFragment = QuizFragmentDirections.actionQuizFragmentToQuizEndFragment(
                    viewModelQuiz.score.value!!,
                    viewModelQuiz.questionsCount.value!!
                )
                findNavController().navigate(switchFragment) //navigate to quizendfragment
            }
        })
    }
}
