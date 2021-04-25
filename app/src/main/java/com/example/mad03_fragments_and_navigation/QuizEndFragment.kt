package com.example.mad03_fragments_and_navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mad03_fragments_and_navigation.databinding.FragmentQuizEndBinding
import com.example.mad03_fragments_and_navigation.viewmodels.QuizEndViewModel


class QuizEndFragment : Fragment() {
    private lateinit var binding: FragmentQuizEndBinding
    private lateinit var viewModelQuizEnd: QuizEndViewModel
    private lateinit var factoryQuizEnd: QuizEndFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz_end, container, false)

        val argumentScore = QuizEndFragmentArgs.fromBundle(requireArguments()).score
        val argQuestionsCount = QuizEndFragmentArgs.fromBundle(requireArguments()).numberQuestions

        factoryQuizEnd =
            QuizEndFactory(requireActivity().application, argumentScore, argQuestionsCount)
        viewModelQuizEnd = ViewModelProvider(this, factoryQuizEnd).get(QuizEndViewModel::class.java)
        binding.textView7.text = "${viewModelQuizEnd.Score.value}/${viewModelQuizEnd.NumberOfQuestions.value}"
        binding.btnRestart.setOnClickListener {
            val switchToFragment = QuizEndFragmentDirections.actionQuizEndFragmentToQuizFragment()
            findNavController().navigate(switchToFragment)
        }
        return binding.root
    }
}
