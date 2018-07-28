package tech.summerly.typed.adapter.annotation.compiler

import com.google.auto.service.AutoService
import java.io.PrintWriter
import javax.annotation.processing.AbstractProcessor
import javax.annotation.processing.Processor
import javax.annotation.processing.RoundEnvironment
import javax.annotation.processing.SupportedAnnotationTypes
import javax.lang.model.element.TypeElement

@AutoService(Processor::class)
@SupportedAnnotationTypes(value = ["tech.summerly.typed.adapter.annotation.Binder"])
class BinderAnnotationProcessor : AbstractProcessor() {

    override fun process(typeElements: MutableSet<out TypeElement>, roundEnvironment: RoundEnvironment): Boolean {
        //do nothing yet
        return true
    }

}