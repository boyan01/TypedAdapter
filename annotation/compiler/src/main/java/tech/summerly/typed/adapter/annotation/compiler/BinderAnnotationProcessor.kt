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

        if (typeElements.isEmpty()) {
            return false
        }

        val element = typeElements.iterator().next()

        val packageName = element.qualifiedName.toString().substringBeforeLast('.')
        val file = processingEnv.filer.createSourceFile("$packageName.TestCreated")
        val writer = PrintWriter(file.openWriter())

        val template = """
            package $packageName;

            public class TestCreated {
                public static void test() {
                    System.out.println("hello world!");
                }
            }

        """.trimIndent()

        writer.print(template)
        writer.flush()
        return true
    }

}