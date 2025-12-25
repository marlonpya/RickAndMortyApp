// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.android.library) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false
}

// Tarea agregada para ejecutar tests y generar reportes de JaCoCo de todos los módulos
tasks.register("jacocoTestReportAll") {
    group = "verification"
    description = "Genera reportes de cobertura de código JaCoCo para todos los módulos"
    
    dependsOn(
        ":app:jacocoTestReport",
        ":data:jacocoTestReport",
        ":domain:jacocoTestReport"
    )
    
    doLast {
        println("✅ Reportes de cobertura generados:")
        println("   - App:    app/build/reports/jacoco/jacocoTestReport/html/index.html")
        println("   - Data:   data/build/reports/jacoco/jacocoTestReport/html/index.html")
        println("   - Domain: domain/build/reports/jacoco/jacocoTestReport/html/index.html")
    }
}
