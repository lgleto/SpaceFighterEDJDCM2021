package ipca.game.example.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.util.*

//
// Created by lourencogomes on 12/2/20.
//
 class Star  {

    var x = 0
    var y = 0
    var speed = 0
    var maxY = 0
    var minY = 0
    var maxX = 0
    var minX = 0

    constructor(context: Context, width: Int, height: Int){

        maxX = width
        minX = 0
        maxY = height
        minY = 0

        val generator =  Random()
        x = generator.nextInt(maxX)
        y = generator.nextInt(maxY)

        speed = generator.nextInt(15)
    }

    fun update(playerSpeed:Int){
        x -= playerSpeed
        x -= speed

        if (x<0){
            x = maxX
            val generator =  Random()
            y = generator.nextInt(maxY)
            speed = generator.nextInt(15)
        }
    }

    fun getStarWidth() : Float{
        val generator =  Random()
        return ((generator.nextFloat()*4)+1)
    }

 }