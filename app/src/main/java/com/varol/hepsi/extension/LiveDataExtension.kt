import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer

inline fun <T> LiveData<T>.observe(owner: LifecycleOwner, crossinline observer: (t: T?) -> Unit) {
    observe(owner, Observer { observer(it) })
}

operator fun <T> MutableLiveData<MutableList<T>>.plusAssign(values: MutableList<T>) {
    val value = this.value ?: mutableListOf()
    value.addAll(values)
    this.value = value
}