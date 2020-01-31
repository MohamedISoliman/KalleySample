package io.github.mohamedisoliman.kalleysample

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import com.squareup.picasso.RequestCreator
import io.github.mohamedisoliman.kalley.Kalley
import io.github.mohamedisoliman.kalley.sampleImage
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import okhttp3.*
import java.io.IOException
import java.util.logging.Logger
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {


    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Kalley.instance().loadImage(sampleImage, {
            image.setImageBitmap(it)
        }, {
            Log.e("Error loading image:", it.toString())
        }, this)
    }
}
