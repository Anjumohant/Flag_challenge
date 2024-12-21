package com.example.flags_challenge.ui.challenge

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import com.example.flags_challenge.R
import com.example.flags_challenge.data.model.FlagsQuestion
import com.example.flags_challenge.ui.viewmodel.FlagViewModel

class ChallengeActivity : AppCompatActivity() {

    private val flagViewModel: FlagViewModel by viewModels()

    private lateinit var questionTextView: TextView
    private lateinit var flagImageView: ImageView
    private lateinit var option1Button: Button
    private lateinit var option2Button: Button
    private lateinit var option3Button: Button
    private lateinit var option4Button: Button
    private lateinit var option1Result: TextView
    private lateinit var option2Result: TextView
    private lateinit var option3Result:TextView
    private lateinit var option4Result: TextView
    private lateinit var timerTextview:TextView
private lateinit var cardview:CardView
private lateinit var game_over:TextView
private lateinit var score:TextView


    private var currentQuestionIndex = 0
    private var selectedOption=0
    private var correctAnswers=0
    private var questionsList: List<FlagsQuestion> = emptyList()

    private val handler = Handler()
    private var isAnswerChecked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_challenge)

        // Initialize UI components
        questionTextView = findViewById(R.id.questionNumberText)
        flagImageView = findViewById(R.id.flagImageView)
        option1Button = findViewById(R.id.option1Button)
        option2Button = findViewById(R.id.option2Button)
        option3Button = findViewById(R.id.option3Button)
        option4Button = findViewById(R.id.option4Button)
        option1Result=findViewById(R.id.option1Result)
        option2Result=findViewById(R.id.option2Result)
        option3Result=findViewById(R.id.option3Result)
        option4Result=findViewById(R.id.option4Result)
        timerTextview=findViewById(R.id.timerTextView)
        cardview=findViewById(R.id.card_view)
        game_over=findViewById(R.id.game_over)
        score=findViewById(R.id.score)
        // Observe the LiveData for questions
        flagViewModel.questions.observe(this, Observer { questions ->
            questionsList = questions
            displayQuestion(currentQuestionIndex)
        })

        // Set up button clicks for options
        option1Button.setOnClickListener {
            if (!isAnswerChecked) selectedOption=1
        }

        option2Button.setOnClickListener {
            if (!isAnswerChecked) selectedOption=2
        }

        option3Button.setOnClickListener {
            if (!isAnswerChecked) selectedOption=3
        }

        option4Button.setOnClickListener {
            if (!isAnswerChecked) selectedOption=4
        }
    }

    private fun displayQuestion(questionIndex: Int) {
        resetOptionStyles()
        val question = questionsList[questionIndex]
        questionTextView.text = "${questionIndex + 1}"

        // Set flag image (Placeholder for now, set it dynamically in real case)
        val flagResId = resources.getIdentifier(
            "flag_${question.country_code.toLowerCase()}",
            "drawable",
            packageName
        )
        flagImageView.setImageResource(flagResId)

        // Set option texts
        option1Button.text = question.countries[0].country_name
        option2Button.text = question.countries[1].country_name
        option3Button.text = question.countries[2].country_name
        option4Button.text = question.countries[3].country_name


startTimer()
//        // Start a timer of 30 seconds to check the answer
//        handler.postDelayed({
//
//            isAnswerChecked = true
//            checkAnswer(selectedOption) // Check the answer after 30 seconds
//        }, 30000) // 30 seconds
    }
    private fun startTimer() {
        // Define a 30-second countdown timer
        object : CountDownTimer(30000, 1000) { // 20000ms (20 seconds) and 1000ms (1 second intervals)
            override fun onTick(millisUntilFinished: Long) {
                // Update the TextView with the remaining time (in seconds)
                val seconds = (millisUntilFinished / 1000) % 60
                val minutes = (millisUntilFinished / 1000) / 60
                timerTextview.text = String.format("%02d:%02d", minutes, seconds)
            }

            override fun onFinish() {
                // When the timer finishes, you can do something (e.g., show a message)
//                timerTextView.text = "Time's up!"
//                val intent = Intent(this@Schedule_TimerActivity, ChallengeActivity::class.java)
//                startActivity(intent)
                checkAnswer(selectedOption)
            }

        }.start()
    }
    private fun checkAnswer(selectedOption: Int) {
        if (isAnswerChecked) return // Prevent checking once the answer is already evaluated
if (selectedOption!=0) {
    val currentQuestion = questionsList[currentQuestionIndex]
    val correctAnswerId = currentQuestion.answer_id
    val selectedAnswerId = currentQuestion.countries[selectedOption - 1].id
    Log.d("App Logger", "selected option $selectedOption selected answerId $selectedAnswerId")
    if (selectedAnswerId == null) {
        handler.postDelayed({
            currentQuestionIndex++
            if (currentQuestionIndex < questionsList.size) {
                // Show next question after 10 seconds
                displayQuestion(currentQuestionIndex)
            } else {
//                Toast.makeText(
//                    this,
//                    "Game completed score $correctAnswers:",
//                    Toast.LENGTH_SHORT
//                ).show()
            }
        }, 10000)
    }
    if (selectedAnswerId == correctAnswerId) {
        // Correct answer
        highlightSelectedCorrect(selectedOption)
    } else {
        // Wrong answer
        highlightSelectedWrong(selectedOption)
    }
}
    // After 10 seconds, move to the next question
    handler.postDelayed({
        currentQuestionIndex++
        if (currentQuestionIndex < questionsList.size) {
            // Show next question after 10 seconds
            displayQuestion(currentQuestionIndex)
        } else {
cardview.visibility=View.GONE
            game_over.visibility=View.VISIBLE
            score.visibility=View.VISIBLE
            score.text="Score: $correctAnswers/${questionsList.size}"
        }
    }, 10000)

    }

    private fun evaluateAnswer() {
        val currentQuestion = questionsList[currentQuestionIndex]
        val correctAnswerId = currentQuestion.answer_id

        // Highlight the correct answer after 30 seconds
        for (i in 1..4) {
            val button = when (i) {
                1 -> option1Button
                2 -> option2Button
                3 -> option3Button
                4 -> option4Button
                else -> null
            }


        }

        // 10 seconds interval
    }

    private fun resetOptionStyles() {
        option1Button.setBackgroundColor(resources.getColor(R.color.transparent))
        option2Button.setBackgroundColor(resources.getColor(R.color.transparent))
        option3Button.setBackgroundColor(resources.getColor(R.color.transparent))
        option4Button.setBackgroundColor(resources.getColor(R.color.transparent))
//        option1Button.text = "Option 1"
//        option2Button.text = "Option 2"
//        option3Button.text = "Option 3"
//        option4Button.text = "Option 4"
        option1Result.visibility=View.GONE
        option2Result.visibility=View.GONE
        option3Result.visibility=View.GONE
        option4Result.visibility=View.GONE
        selectedOption=0
    }

    private fun highlightSelectedCorrect(option: Int) {

        when(option){
            1->{option1Result.visibility=View.VISIBLE
                option1Result.setText(R.string.correct)
                option1Result.setTextColor(resources.getColor(R.color.crrectoption_color))
                option1Button.setBackground(resources.getDrawable(R.drawable.correctoption_bg))
            correctAnswers=correctAnswers+1}

                2->{
                    option2Result.visibility=View.VISIBLE
                    option2Result.setText(R.string.correct)
                    option2Result.setTextColor(resources.getColor(R.color.crrectoption_color))
                    option2Button.setBackground(resources.getDrawable(R.drawable.correctoption_bg))
                    correctAnswers=correctAnswers+1
                }
            3->{
                option3Result.visibility=View.VISIBLE
                option3Result.setText(R.string.correct)
                option3Result.setTextColor(resources.getColor(R.color.crrectoption_color))
                option3Button.setBackground(resources.getDrawable(R.drawable.correctoption_bg))
                correctAnswers=correctAnswers+1
            }
            4->{
                option4Result.visibility=View.VISIBLE
                option4Result.setText(R.string.correct)
                option4Result.setTextColor(resources.getColor(R.color.crrectoption_color))
                option4Button.setBackground(resources.getDrawable(R.drawable.correctoption_bg))
                correctAnswers=correctAnswers+1
            }
        }

       // button?.setBackground(resources.getDrawable(R.drawable.correctoption_bg))
    }
    private fun highlightSelectedWrong(option: Int) {
        when(option){
            1->{option1Result.visibility=View.VISIBLE
                option1Result.setText(R.string.wrong)
                option1Result.setTextColor(resources.getColor(R.color.wrongtext))
                option1Button.setBackground(resources.getDrawable(R.drawable.wrongoption_bg))
                }

            2->{
                option2Result.visibility=View.VISIBLE
                option2Result.setText(R.string.wrong)
                option2Result.setTextColor(resources.getColor(R.color.wrongtext))
                option2Button.setBackground(resources.getDrawable(R.drawable.wrongoption_bg))

            }
            3->{
                option3Result.visibility=View.VISIBLE
                option3Result.setText(R.string.wrong)
                option3Result.setTextColor(resources.getColor(R.color.wrongtext))
                option3Button.setBackground(resources.getDrawable(R.drawable.wrongoption_bg))

            }
            4->{
                option4Result.visibility=View.VISIBLE
                option4Result.setText(R.string.wrong)
                option4Result.setTextColor(resources.getColor(R.color.wrongtext))
                option4Button.setBackground(resources.getDrawable(R.drawable.wrongoption_bg))

            }
        }
    }
}
