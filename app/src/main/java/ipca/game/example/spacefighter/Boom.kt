package ipca.game.example.spacefighter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Rect
import java.util.*

//
// Created by lourencogomes on 12/2/20.
//
 class Boom  {

    var x = 0
    var y = 0
    var speed = 0
    var maxY = 0
    var minY = 0
    var maxX = 0
    var minX = 0
    lateinit var bitmap : Bitmap
    lateinit var detectCollision : Rect

    constructor(context: Context, width: Int, height: Int){
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.boom)
        x = -300
        y = -300

    }





 }