package com.example.feature_list_workout.detail.presenter

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Environment
import android.util.Log
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.example.domain.Workout
import com.example.domain.WorkoutList
import com.example.feature_list_workout.converter.ImageJpgToPngConverter
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.SingleSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.lang.Exception
import java.util.*
import java.util.concurrent.Callable


@InjectViewState
class WorkoutDetailPresenter : MvpPresenter<ViewProtocolWorkoutDetailScreen>() {
    private var numberExercise = 0
    private lateinit var workout: Workout
    private val disposableBag = CompositeDisposable()

    private val FILE_NAME = "IMG_20190923_143242.jpg"
    private val FILE_DIR = "/storage/emulated/0/DCIM/Camera/"

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        receivedIndex(numberExercise)
        getWorkout()
        showWorkoutDetail()
    }

    private fun showWorkoutDetail() {
        viewState.showDetail(workout)
    }

    private fun getWorkout(): Workout {
        workout = WorkoutList.getInstance().getWorkouts()[numberExercise]
        return workout
    }

    private fun receivedIndex(index: Int) {
        //пока рандом, потом норм пробросить индекс
        val random = Random()
        numberExercise = random.nextInt(100)
    }

    fun downloadDescriptionExercise() {
        val observable = getDescriptionExercise()
        val subscription = observable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { index: Int ->
                    viewState.updateDescription(index)
                },
                { e ->
                    Log.i("MYTAG", "Error: $e")
                })
        disposableBag.add(subscription)
    }

    private fun getDescriptionExercise(): Observable<Int> {
        return Observable.create { emitter ->
            val res = requestNetwork()
            emitter.onNext(res)
            emitter.onComplete()
        }
    }

    private fun requestNetwork(): Int {
        //имитируем запрос в сеть
        var res = 0
        for (i in 0..10000000000) {
            res = i.toInt()
        }
        return res
    }

    override fun onDestroy() {
        super.onDestroy()
        disposableBag.clear()
    }

    fun convertImageToPng() {
        val converter = ImageJpgToPngConverter(FILE_DIR, FILE_NAME)

        val converterOmageObserver = converter.createBitmapFromPath()
            .flatMap{bitmap: Bitmap -> converter.compressBitmap(bitmap) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .ignoreElement()
            .subscribe(
                {
                    viewState.updateInfoConvert("Converting completed")
                },
                { e ->
                    viewState.updateInfoConvert("Converting failed")
                    Log.i("MYTAG", "Error: $e")
                }
            )

        disposableBag.add(converterOmageObserver)




//        val imageJpeg = getImageAndConvert()
//
//        val subscription = imageJpeg
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe(
//                { image: Bitmap ->
//
//                },
//                { e ->
//                    Log.i("MYTAG", "Error: $e")
//                })
//        disposableBag.add(subscription)

//        val imageJpeg = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20190923_143242.jpg")
//        val bytes = ByteArrayOutputStream()
//        imageJpeg.compress(Bitmap.CompressFormat.PNG, 100, bytes)
//        val f = File("/storage/emulated/0/DCIM/Camera/new.png")
//        f.createNewFile()
//        val fo = FileOutputStream(f)
//        fo.write(bytes.toByteArray())
//        fo.close()
    }

    fun getImageAndConvert(): Single<Bitmap> {
        return Single.create {
            val imageJpeg =
                BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20190923_143242.jpg")
        }
    }
//        Single.fromCallable(object: Callable<Bitmap>) {
//            val imageJpeg = BitmapFactory.decodeFile("/storage/emulated/0/DCIM/Camera/IMG_20190923_143242.jpg")
//        }.map {
//            val bytes = ByteArrayOutputStream()
//            it.compress(Bitmap.CompressFormat.PNG, 100, bytes)
//            val f = File("/storage/emulated/0/DCIM/Camera/new.png")
//            f.createNewFile()
//            val fo = FileOutputStream(f)
//            fo.write(bytes.toByteArray())
//            fo.close()
//        }
}
