package com.alitv.mvvmtrainning.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alitv.mvvmtrainning.api.RetrofitInstance
import com.alitv.mvvmtrainning.model.RegisterResponseModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class RegisterViewModel: ViewModel() {
    private val registerResponseModel = MutableLiveData<RegisterResponseModel>()
    private val errorListener = MutableLiveData<Boolean>()
    private val compositeDisposable = CompositeDisposable()

    fun register(email: String, userName:String, address:String, dob: String, password: String){
        compositeDisposable.add(RetrofitInstance.apiInterface.register(email, userName, address, dob, password)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<RegisterResponseModel>() {
                    override fun onSuccess(t: RegisterResponseModel) {
                        if("success" == t.status){
                            registerResponseModel.value = t
                        }else{
                            errorListener.value = true
                        }
                    }

                    override fun onError(e: Throwable) {
                        errorListener.value = true
                    }
                }))
    }

    fun getRegisterResponseModel(): MutableLiveData<RegisterResponseModel>{
        return registerResponseModel
    }

    fun getErrorListener(): MutableLiveData<Boolean>{
        return errorListener
    }

    override fun onCleared(){
        super.onCleared()
        compositeDisposable.clear()
    }
}