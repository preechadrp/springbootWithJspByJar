-springboot 4.0.3
-jsp
-@WebServlet
-เป็น .jar
 ต้อง copy ไฟล์ข้างใน src/main/webapp  ไปวางใน src/main/resources/META-INF/resources
 เพราะใน .jar จะใช้ src/main/resources/META-INF/resources แทน src/main/webapp ของ .war
 มันเป็นกฎของ Servlet Container เวลามี JAR อยู่ใน classpath เช่น jar:file:/xxx.jar!/META-INF/resources/
 จำง่ายๆ
  WAR → webapp/
  JAR → META-INF/resources/

===ตัวอย่างการ Dockerfile mvn แบบ offline==
==ติดตั้ง  lib ที่ไม่มีใน maven repo
mvn install:install-file "-Dmaven.repo.local=repo" ^
 -Dfile=mylib-1.jar  ^
 -DgroupId=org.example  ^
 -DartifactId=mylib ^
 -Dversion=1  ^
 -Dpackaging=jar ^
 -DgeneratePom=true

==รันเพื่อเอา  lib  มาเก็บที่เดียวกับ code  ต้องรันที่ project folder ที่เครื่อง dev
mvn "-Dmaven.repo.local=repo" dependency:go-offline
#mvn "-Dmaven.repo.local=repo" clean package -DskipTests ไม่ต้องก็ได้ยกเว้นต้องการทดสอบว่า build ผ่านไหม 
และเพิ่มข้อความลงใน .gitignore ก่อน commit ไปใน gitlab
repo/**/*.sha1
repo/**/*.md5
repo/**/*.lastUpdated
repo/**/_remote.repositories

==สำหรับรันตอนอยู่ใน  Dockerfile
mvn  -o "-Dmaven.repo.local=repo" clean package -DskipTests  

==ตัวอย่างการเขียน Dockerfile mvn แบบ offline 
WORKDIR /opt/myapp
ENV JAVA_HOME=/usr/lib/jvm/java-21-temurin-jdk
ENV M2_HOME=/opt/maven/apache-maven-3.9.11
ENV PATH=$M2_HOME/bin:$JAVA_HOME/bin:$PATH
 
COPY pom.xml .
COPY src .
COPY repo .

RUN mvn -o "-Dmaven.repo.local=repo" clean package -DskipTests
...
==
