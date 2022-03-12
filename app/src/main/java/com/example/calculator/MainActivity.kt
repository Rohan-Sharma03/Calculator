package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    var lastNumeric =false
    var lastDot=false
    lateinit var input :TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        input = findViewById<TextView>(R.id.tvInputScreen)

    }
    fun OnClick(view: View){
//        Toast.makeText(this,"Btn clicked ",Toast.LENGTH_SHORT).show()
            input.append((view as Button).text)
            lastNumeric=true;
        }
    fun OnCLR(view: View){
            input.text=""
            lastNumeric =false
            lastDot=false
        }
    fun OnDecimalPoint(view: View){
            if(lastNumeric && !lastDot){
                input.append(".")
                lastDot=true
                lastNumeric=false
            }
        }
    fun OnEqual(view: View){
        if(lastNumeric){

            var tvinput = input.text.toString();
            var prefix=""

            try {
                if(tvinput.startsWith("-")){
                    prefix="-"
                    tvinput=tvinput.substring(1)
                }
                if(tvinput.contains("-")){
                    val splitevalue = tvinput.split("-")
                    var one = splitevalue[0]
                    var two = splitevalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    input.text= (one.toDouble() - two.toDouble()).toString()
                }else if (tvinput.contains("+")){
                    val splitevalue = tvinput.split("+")
                    var one = splitevalue[0]
                    var two = splitevalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    input.text= (one.toDouble() + two.toDouble()).toString()
                }else if (tvinput.contains("*")){
                    val splitevalue = tvinput.split("*")
                    var one = splitevalue[0]
                    var two = splitevalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    input.text= (one.toDouble() * two.toDouble()).toString()
                }else{
                    val splitevalue = tvinput.split("/")
                    var one = splitevalue[0]
                    var two = splitevalue[1]

                    if(!prefix.isEmpty()){
                        one=prefix+one
                    }

                    input.text= (one.toDouble() / two.toDouble()).toString()
                }

            }catch (e:ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    fun OnOperator(view: View){
        if (lastNumeric && !isOperatorAdded(input.text.toString())){
            input.append((view as Button).text)
            lastNumeric=false
            lastDot=false
        }
    }
    private fun isOperatorAdded(value:String):Boolean{
        return if(value.startsWith("-")){
            false;
        }else{
            value.contains("/")||value.contains("+")||value.contains("*")||value.contains("-")
        }
    }


    }