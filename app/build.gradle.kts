tasks.getByName("bootJar") {
    enabled = true
}

tasks.getByName("jar") {
    enabled = false
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.Embeddable")
    annotation("javax.persistence.MappedSuperclass")
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    testImplementation("org.springframework.restdocs:spring-restdocs-mockmvc")
    runtimeOnly("mysql:mysql-connector-java:8.0.33")
    implementation("org.springframework.boot:spring-boot-starter-parent:3.0.4")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation(project(":core:common"))
    implementation(project(":core:model"))
    implementation(project(":storage:database"))
    implementation(project(":support:monitoring"))
}
