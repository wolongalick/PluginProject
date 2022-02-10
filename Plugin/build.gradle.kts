plugins {
    id ("java-library")
    id ("kotlin")
    id ("maven-publish")
}

java {
    sourceCompatibility = JavaVersion.VERSION_1_7
    targetCompatibility = JavaVersion.VERSION_1_7
}

dependencies{
    implementation("com.google.code.gson:gson:2.6.2")
    implementation(gradleApi())
    implementation("com.android.tools.build:gradle:4.1.3")
}

publishing {
    publications {
        create<MavenPublication>("jiaGuLibrary") {
            groupId = "com.alick.library"
            artifactId = "jiagu"
            version = "1.0.4"

            from(components["java"])
        }
    }

    repositories {
        maven {
            url = uri(file("D:/Repo"))
        }
    }
}