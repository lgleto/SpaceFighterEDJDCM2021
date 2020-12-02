package ipca.game.example.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect

//
// Created by lourencogomes on 12/2/20.
//
class Player {

    var x = 0
    var y = 0
    var speed = 0

    var boosting = false
    var maxY = 0
    var minY = 0
    private final val GRAVITY = -10
    private final val MAX_SPEED = 20
    private final val MIN_SPEED = 1

    var bitmap : Bitmap
    var detectCollision : Rect


    constructor(context: Context, width: Int, height: Int){
        x=75
        y=50
        speed=1
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.player)
        maxY = height - bitmap!!.height
        minY = 0
        detectCollision = Rect(x,y,bitmap.width,bitmap.height)
    }

    fun update(){
        if (boosting) speed += 2
        else speed -= 5
        if (speed>MAX_SPEED) speed = MAX_SPEED
        if (speed<MIN_SPEED) speed = MIN_SPEED
        y -= speed + GRAVITY
        if (y > maxY) y = maxY
        if (y < minY) y = minY

        detectCollision.left = x
        detectCollision.top = y
        detectCollision.right = x + bitmap.width
        detectCollision.bottom = y + bitmap.height
    }

}