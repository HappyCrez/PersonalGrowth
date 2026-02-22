# Project "Personal Growth"

## Description
StarUml scheme of classes relationships in "uml.mdj"  

## Build&Run project

If you use java v.21 then, just type
```
mvn build
call mvn exec:java "-Dexec.mainClass=plannerApp.Launcher"
```

 If you use java lower than 21 version try change version in "pom.xml", but it is not guarantee that project will work stable. (Actually, you can't use version lower than 10)
 ```mvn
 <plugin>
    <artifactId>maven-compiler-plugin</artifactId>
    <version>3.11.0</version>
    <configuration>
        <release>21</release>	# java version to change
        <source>21</source>		# here too
    </configuration>
</plugin>
```

## Tricks
To create association with jar file and jre use cmd command 
```
assoc .jar=javafile && ftype jarfile=%JAVA_PATH%\javaw.exe -jar "%1" %*
```